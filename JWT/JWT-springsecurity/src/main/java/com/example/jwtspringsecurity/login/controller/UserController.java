package com.example.jwtspringsecurity.login.controller;

import com.example.jwtspringsecurity.login.dto.UserDto;
import com.example.jwtspringsecurity.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 관리자 가입
    @PostMapping("/signup/admin")
    @ResponseBody
    public ResponseEntity<String> adminSignup(@Validated @RequestBody UserDto userDto, BindingResult bindingResult) {
        return userService.adminSignup(userDto, bindingResult);
    }

    // User 가입
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<String> userSignup(@Validated @RequestBody UserDto userDto, BindingResult bindingResult) {
        return userService.userSignup(userDto, bindingResult);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@Validated @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
