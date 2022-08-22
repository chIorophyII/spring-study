package com.example.springsecurity1jwt.repository;

import com.example.springsecurity1jwt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고 있음
// JpaRepository를 상속했기 때문에 @Repository 어노테이션이 없어도 IoC가 됨.
public interface UserRepository extends JpaRepository<Users, Integer> {
}
