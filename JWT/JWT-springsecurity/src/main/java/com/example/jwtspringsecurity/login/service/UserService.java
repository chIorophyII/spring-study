package com.example.jwtspringsecurity.login.service;

import com.example.jwtspringsecurity.login.dto.UserDto;
import com.example.jwtspringsecurity.login.model.User;
import com.example.jwtspringsecurity.login.repository.UserRepository;
import com.example.jwtspringsecurity.login.security.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.jwtspringsecurity.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    // 회원가입
    @Transactional
    public ResponseEntity<String> adminSignup(UserDto adminDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.status(200).body(errors.get(0));
        }
        Optional<User> foundUser = userRepository.findByUsername(adminDto.getUsername());

        if (duplicate(foundUser)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ILLEGAL_USER_NAME_DUPLICATION);
        }

        User user = User.builder()
                        .username(adminDto.getUsername())
                        .password(passwordEncoder.encode(adminDto.getPassword()))
                        .userRole(User.UserRole.ADMIN)
                        .code(adminDto.getCode())
                        .build();
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("관리자 회원가입 성공");
    }

    @Transactional
    public ResponseEntity<String> userSignup(UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.status(200).body(errors.get(0));
        }

        Optional<User> foundUser = userRepository.findByUsername(userDto.getUsername());

        if (duplicate(foundUser)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ILLEGAL_USER_NAME_DUPLICATION);
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userRole(User.UserRole.USER)
                .build();
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("사용자 회원가입 성공");
    }

    public boolean duplicate(Optional<User> foundUser) {
        return foundUser.isPresent();
    }

    // 로그인
    @Transactional
    public ResponseEntity<String> login(UserDto userDto) {
        System.out.println("wa?");
       User foundUser = userRepository.findByUsername(userDto.getUsername())
                .orElse(null);

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ILLEGAL_USER_NOT_EXIST);
        }

        if(!passwordEncoder.matches(userDto.getPassword(), foundUser.getPassword())) {
            throw new IllegalArgumentException(ILLEGAL_PASSWORD_NOT_VALID);
        }

        HttpHeaders headers = headerToken(foundUser);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("로그인 성공");
    }

    // 토큰을 헤더에 담음
    public HttpHeaders headerToken(User user) {
        String token = jwtTokenUtils.generateJwtToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + " " + token);
        return headers;
    }
}
