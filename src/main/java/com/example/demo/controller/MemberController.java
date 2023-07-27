package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor

public class  MemberController {
    private final MemberService memberService;
    @GetMapping("/register")
    public String registerForm() {
        return "register"; // register.jsp 파일을 리턴
    }
    // 회원 가입 요청을 받아 처리하는 메소드

    @PostMapping("/register") //회원가입(데이터베이스에 회원정보 저장)
    public String register(@ModelAttribute MemberDTO memberDTO) {
        int registerResult = memberService.register(memberDTO);
        System.out.println("registerResult = " + registerResult);
        if(registerResult >0 ) {
            return "redirect:/login";
        }
        else {
            return "redirect:/register";
        }
    }
    @PostMapping("member/id_check") //회원가입 시 id 중복 체크
    public @ResponseBody String id_Check(@RequestParam("user_id") String user_id) {
        System.out.println("user_id = " + user_id);
        String checkResult = memberService.id_Check(user_id);
        return checkResult;
    }

    //-----------------로그아웃 원본-----------------
//    @GetMapping("/logout") //로그아웃
//    public String logout(HttpSession session){
//        session.invalidate();
////        return "redirect:/";
//        return "redirect:/login";
//    }
//-----------------로그아웃 원본-----------------
    @GetMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "로그아웃되었습니다.");

        return response;
    }




    //    @GetMapping("/login")
//    public String loginForm() {
//        return "/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute MemberDTO memberDTO,
//                        HttpSession session) {
//       boolean loginResult = memberService.login(memberDTO);
//       if (loginResult) {
//              session.setAttribute("user_id", memberDTO.getUser_id());
//              return "redirect:/todoList";
//         } else {
//              return "/login";
//       }
//    }

}
