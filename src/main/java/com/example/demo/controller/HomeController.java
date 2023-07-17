package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
        public String test() {
        return "test";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.jsp 파일을 리턴
    }

}
