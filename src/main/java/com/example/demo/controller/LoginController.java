//package com.example.demo.controller;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.service.LoginService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//@Controller
//@RequestMapping
//@RequiredArgsConstructor
//public class LoginController {
//    private final LoginService loginService;
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "/login";
//    }
//
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute MemberDTO memberDTO,
//                        HttpSession session) {
//        boolean loginResult = loginService.login(memberDTO);
//        if (loginResult) {
//            session.setAttribute("user_id", memberDTO.getUser_id());
//            return "redirect:/todoList";
//        } else {
//            return "/login";
//        }
//    }
//}
package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session) {
        System.out.println("사용자 ID로 로그인 시도 중:" + memberDTO.getUser_id());

        boolean loginResult = loginService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("user_id", memberDTO.getUser_id());
            System.out.println("로그인 성공, 작업관리 목록으로 리디렉션합니다.");
            return "redirect:/todoList";
        } else {
            System.out.println("로그인 실패, 로그인 페이지로 돌아가기");
            return "/login";
        }
    }
}
