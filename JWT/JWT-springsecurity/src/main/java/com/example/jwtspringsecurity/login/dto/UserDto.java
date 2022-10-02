package com.example.jwtspringsecurity.login.dto;

import com.example.jwtspringsecurity.login.model.User;
import com.example.jwtspringsecurity.utils.AdminValidGroup;
import com.example.jwtspringsecurity.utils.UserValidGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserDto {
    @NotBlank(groups = {AdminValidGroup.class, UserValidGroup.class}, message = "아이디를 입력해주세요.")
    @Pattern(groups = {AdminValidGroup.class, UserValidGroup.class}, regexp = "^[a-zA-Z0-9]{2,10}$", message = "아이디는 영문과 숫자만 입력해 주세요.")
    String username;

    @NotBlank(groups = {AdminValidGroup.class, UserValidGroup.class}, message = "비밀번호를 입력해주세요.")
    @Pattern(groups = {AdminValidGroup.class, UserValidGroup.class},
            regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,10}", message = "비밀번호는 영문/숫자/특수문자(!@#$%^&*)를 포함해 6~10자로 입력해 주세요.")
    String password;

    User.UserRole userRole;

    @NotNull(groups = AdminValidGroup.class, message = "관리자 code를 입력해주세요.")
    Integer code;
}
