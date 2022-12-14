package com.example.oop.solid.srp;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

public class NotSRP {

    @Service
    @RequiredArgsConstructor
    public class UserService{
        private final UserRepository userRepository;

        public void addUser(final String email, final String pw) {
            final StringBuilder sb = new StringBuilder();

            for(byte b : pw.getBytes(StandardCharsets.UTF_8)) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            final String encryptedPassword = sb.toString();
            final User user = User.builder()
                    .email(email)
                    .password(encryptedPassword).build();

            userRepository.save(user);
        }
    }
}
