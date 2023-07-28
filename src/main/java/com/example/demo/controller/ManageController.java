// Controller
package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage")
public class ManageController {
    private final ManageService manageService;

    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }

    @GetMapping
    public String managePage(HttpSession session) {
        String user_id = (String) session.getAttribute("user_id");
        if (user_id == null) {
            // 로그인 페이지 또는 적절한 다른 페이지로 리다이렉트
            return "redirect:/login"; // "/login"을 실제 로그인 페이지의 URL로 대체하세요
        } else {
            return "manage";
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody MemberDTO memberDTO) {
        manageService.updateUser(memberDTO);
        return "redirect:/manage";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        manageService.deleteUser(memberDTO);
        return "redirect:/login";
    }
}
