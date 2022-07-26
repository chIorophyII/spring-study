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

class publicA{
    public void run() {
        B b = new B();
        b.publicMethod();
    }
}

class protectedA extends B {
    public void run() {
        protectedMethod();
    }
}

class defaultA {
        void defaultMethod() {
        }
}

class privateA extends B {
    public void run() {
        B b = new B();
        b.privateMethod();
        privateMethod();
    }
}

class B {
    public void publicMethod()
    {
        System.out.println("public 메소드 접근");
    }
    void defaultMethod()
    {
        System.out.println("default 메소드 접근");
    }
    protected void protectedMethod()
    {
        System.out.println("protected 메소드 접근");
    }
    private void privateMethod()
    {
        System.out.println("private 메소드 접근");
    }
}