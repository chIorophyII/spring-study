package com.example.springsecurity1jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"","/"})
    public String index() {
        // 머스테치 기본폴더 src/main/resources/
        // view resolver 설정 : templates (prefix), .mustache (suffirx)
        return "index"; // src/main/resources/templates/index.mustache
    }
}
