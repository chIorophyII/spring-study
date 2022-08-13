package com.example.springsecurity1jwt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"","/"})
    public String index() {
        // 머스테치
        return "index";
    }
}
