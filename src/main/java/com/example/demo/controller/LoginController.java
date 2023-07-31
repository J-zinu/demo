package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm() {
        System.out.println("로그인 폼에 접근");
        return "/login";
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> login(@ModelAttribute MemberDTO memberDTO,
                                     HttpSession session) {
        System.out.println("사용자 ID로 로그인 시도중: " + memberDTO.getUser_id());

        boolean loginResult = loginService.login(memberDTO);
        Map<String, Object> response = new HashMap<>();
        if (loginResult) {
            session.setAttribute("user_id", memberDTO.getUser_id());
            System.out.println("컨트롤러 로그인성공 후 세션에 저장");
            response.put("status", "success");
            response.put("message", "서버에서의 알림 : 로그인 성공! 할일 목록 페이지로 이동합니다.");
        } else {
            System.out.println("Login failed, returning to login page");
            response.put("status", "fail");
            response.put("message", "서버에서의 알림 : 로그인 실패! 로그인 페이지로 돌아갑니다.");
        }
        return response;
    }
}
