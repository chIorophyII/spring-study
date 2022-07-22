package com.example.oop.solid.ocp;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NotOCP {
    @Component
    public class SHA256PasswordEncoder_ {

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
}
