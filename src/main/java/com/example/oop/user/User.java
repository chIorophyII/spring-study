package com.example.oop.user;

import lombok.*;
import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String email;
    private String password;
}
