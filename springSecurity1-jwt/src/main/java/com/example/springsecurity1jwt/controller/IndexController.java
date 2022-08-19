package com.example.springsecurity1jwt.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @GetMapping({"","/"})
    public String index() {
        // 머스테치 기본폴더 src/main/resources/
        // view resolver 설정 : templates (prefix), .mustache (suffirx)
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    // spring security가 먼저 낚아 챔
    // SecurityConfig 파일 생성 후 작동 안함
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    @ResponseBody
    public String joinProc() {
        return "회원가입 완료";
    }
}
