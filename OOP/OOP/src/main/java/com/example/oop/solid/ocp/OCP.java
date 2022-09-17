package com.example.oop.solid.ocp;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OCP {
    // 암호화 추상화
    public interface PasswordEncoder {
        String encryptPassword(final String pw);
    }

    @Component
    public static class SHA256PasswordEncoder implements PasswordEncoder {
        private final static String SHA_256 = "SHA-256";

        @Override
        public String encryptPassword(final String pw) {
            final MessageDigest digest;
            try {
                digest = MessageDigest.getInstance(SHA_256);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException();
            }

            final byte[] encodedHash = digest.digest(pw.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedHash);
        }

        private String bytesToHex(final byte[] encodedHash) {
            final StringBuilder hexString = new StringBuilder(2 * encodedHash.length);

            for (final byte hash : encodedHash) {
                final String hex = Integer.toHexString(0xff & hash);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        }
    }

    @Service
    @RequiredArgsConstructor
    public static class UserService_ocp {

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
