package com.example.jwtspringsecurity.login.dto;

import com.example.jwtspringsecurity.login.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,10}$", message = "아이디는 영문과 숫자만 입력해 주세요.")
    String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,10}$", message = "비밀번호는 영문/숫자/특수문자(!@#$%^&*)를 포함해 6~10자로 입력해 주세요.")
    String password;

    User.UserRole userRole;
}
