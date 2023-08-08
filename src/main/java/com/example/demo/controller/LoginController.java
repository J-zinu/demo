package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    //프로젝트 향후 추가할 부분
    //checkbox를 통해서 todolist를 일괄 삭제 및 수정
    //member에 대해서 admin(관리자)을 통해서 이를 조회 및 수정
    //Transaction에 대해서 공부하여, 수정 및 삭제에 대해서 롤백이 가능하도록 구현
    //우선적으로 어노테이션 및 다른 부분에 대해서 어떻게 작동을 하는지 공부할 필요가 있음

    @GetMapping("/login")
    public ModelAndView loginForm() {
        System.out.println("로그인 폼에 접근");
        ModelAndView login_url = new ModelAndView();
        login_url.setViewName("login");
        return login_url;
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
            System.out.println("콘솔에  로그인 실패!!!!! 출력");
            response.put("status", "fail");
            response.put("message", "서버에서의 알림 : 로그인 실패! 로그인 페이지로 돌아갑니다.");
        }
        return response;
    }
}
