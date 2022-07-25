package com.example.oop.property;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public class Encapsulation {
    public interface PasswordEncoder {
        String encryptPassword(final String pw);
    }

    @Service
    @RequiredArgsConstructor
    public static class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public void addUser(final String email, final String pw) {
            final String encryptedPassword = passwordEncoder.encryptPassword(pw);

            final User user = User.builder()
                    .email(email)
                    .password(encryptedPassword).build();

            userRepository.save(user);
        }
    }
}
