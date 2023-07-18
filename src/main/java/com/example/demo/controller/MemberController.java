package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class  MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // register.jsp 파일을 리턴
    }


    @PostMapping("/register")
    public String register(@ModelAttribute MemberDTO memberDTO) {
        int registerResult = memberService.register(memberDTO);
        if(registerResult >0 ) {
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }
    }
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session) {
       boolean loginResult = memberService.login(memberDTO);
       if (loginResult) {
              session.setAttribute("user_id", memberDTO.getUser_id());
              return "/todoList/todoList";
         } else {
              return "login";
       }
    }


}
