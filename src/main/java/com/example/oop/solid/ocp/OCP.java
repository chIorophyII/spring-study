package com.example.oop.solid.ocp;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class OCP {
    public interface PasswordEncoder {
        String encryptPassword(final String pw);
    }

    @Component
    public class SHA256PasswordEncoder implements PasswordEncoder {

        @Override
        public String encryptPassword(final String pw) {
            return null;
        }
    }

    @Service
    @RequiredArgsConstructor
    public class UserService {

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
