package com.example.oop.solid.isp;

import org.springframework.stereotype.Component;

public class NotISP {
    public interface PasswordEncoder {
        String encryptPassword(final String pw);
    }

    @Component
    public class SHA256PasswordEncoder implements PasswordEncoder {

        @Override
        public String encryptPassword(final String pw)  {
		    return null;
        }

        public boolean isCorrectPassword(final String rawPw, final String pw) {
            final String encryptedPw = encryptPassword(rawPw);
            return encryptedPw.equals(pw);
        }
    }
}
