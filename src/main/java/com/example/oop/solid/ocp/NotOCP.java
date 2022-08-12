package com.example.oop.solid.ocp;

import com.example.oop.user.User;
import com.example.oop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NotOCP {
    @Component
    public static class SHA256PasswordEncoder_ {

        private final static String SHA_256 = "SHA-256";

        public String encryptPassword(final String pw)  {
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
    public static class UserService_notocp {

        private final UserRepository userRepository;
        // UserService에서 암호화 알고리즘을 다시 수정해야 하고, 이것은 수정에 대해 닫혀있지 않음
        private final SHA256PasswordEncoder_ passwordEncoder;

        public void addUser(final String email, final String pw) {
            final String encryptedPassword = passwordEncoder.encryptPassword(pw);

            final User user = User.builder()
                    .email(email)
                    .password(encryptedPassword).build();

            userRepository.save(user);
        }
    }
}