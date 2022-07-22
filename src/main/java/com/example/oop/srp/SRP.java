package com.example.oop.srp;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

public class SRP {
    @Component
    public class SimplePasswordEncoder {

        public String encryptPassword(final String pw) {
            final StringBuilder sb = new StringBuilder();

            for(byte b : pw.getBytes(StandardCharsets.UTF_8)) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
    }

    @Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository userRepository;
        private final SimplePasswordEncoder passwordEncoder;

        public void addUser(final String email, final String pw) {
            final String encryptedPassword = passwordEncoder.encryptPassword(pw);

            final User user = User.builder()
                    .email(email)
                    .password(encryptedPassword).build();

            userRepository.save(user);
        }
    }
}
