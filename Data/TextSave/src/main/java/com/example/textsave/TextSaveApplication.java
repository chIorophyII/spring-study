package com.example.textsave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TextSaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextSaveApplication.class, args);
    }

}
