package com.example.oop.solid.isp;

import org.springframework.stereotype.Component;

public class ISP {
    public interface PasswordEncoder {
        String encryptPassword(final String pw);
    }

    public interface PasswordChecker {
        boolean isCorrectPassword(final String rawPw, final String pw);
    }

    @Component
    public class SHA256PasswordEncoder implements PasswordEncoder, PasswordChecker {

        @Override
        public String encryptPassword(final String pw)  {
            return null;
        }

        @Override
        public boolean isCorrectPassword(final String rawPw, final String pw) {
            final String encryptedPw = encryptPassword(rawPw);
            return encryptedPw.equals(pw);
        }
    }
}
