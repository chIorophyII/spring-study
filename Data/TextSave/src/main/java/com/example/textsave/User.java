package com.example.textsave;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String region;

    @Builder
    public User(String name, int age, Gender gender, String phone, String region) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.region = region;
    }

    @AllArgsConstructor
    public enum Gender {
        남("man"),
        여("woman");

        public String gender;
    }
}