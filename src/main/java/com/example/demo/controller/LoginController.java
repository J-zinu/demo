package com.example.demo.controller;

import com.example.demo.config.SessionConfig;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final SessionConfig sessionConfig;  // SessionConfig 객체를 주입받습니다.

    @GetMapping("/login")
    public ModelAndView loginForm() {
        System.out.println("로그인 폼에 접근");
        ModelAndView login_url = new ModelAndView();
        login_url.setViewName("login");
        return login_url;
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object>login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("사용자 ID로 로그인 시도중: " + memberDTO.getUser_id());

        String existingSessionId = sessionConfig.getSessionIdCheck("user_id", memberDTO.getUser_id(), session);

        if (existingSessionId != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "fail");
            response.put("message", "서버에서 알림: 해당 사용자 ID로 이미 로그인이 되어 있습니다.");
            return response;
        }

        boolean loginResult = loginService.login(memberDTO);
        Map<String, Object> response = new HashMap<>();
        if (loginResult) {
            session.setAttribute("user_id", memberDTO.getUser_id());
            System.out.println("컨트롤러 로그인성공 후 세션에 저장");
            response.put("status", "success");
            response.put("message", "서버에서의 알림 : 로그인 성공! 할일 목록 페이지로 이동합니다.");
        } else {
            System.out.println("콘솔에  로그인 실패!!!!! 출력");
            response.put("status", "fail");
            response.put("message", "서버에서의 알림 : 로그인 실패! 로그인 페이지로 돌아갑니다.");
        }
        return response;
    }
}
