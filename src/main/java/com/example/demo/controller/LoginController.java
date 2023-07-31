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
        System.out.println("로그인 폼 접근중");
        return "/login";
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> login(@ModelAttribute MemberDTO memberDTO,
                                     HttpSession session) {
        System.out.println("사용자 ID로 로그인 시도 중: " + memberDTO.getUser_id());

        boolean loginResult = loginService.login(memberDTO);
        Map<String, Object> response = new HashMap<>();
        if (loginResult) {
            session.setAttribute("user_id", memberDTO.getUser_id());
            System.out.println("로그인 성공, 작업 관리 목록으로 리디렉션합니다.");
            response.put("status", "success");
            response.put("message", "로그인 성공. 작업 관리 목록으로 리디렉션합니다.");
        } else {
            System.out.println("로그인 실패, 로그인 페이지로 돌아갑니다.");
            response.put("status", "fail");
            response.put("message", "로그인 실패. 로그인 페이지로 돌아갑니다.");
        }
        return response;
    }
}
