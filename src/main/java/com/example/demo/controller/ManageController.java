// Controller
package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage")
public class ManageController {
    private final ManageService manageService;

    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }

    @GetMapping
    public String managePage() {
        return "manage";
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody MemberDTO memberDTO) {
        manageService.updateUser(memberDTO);
        return "redirect:/manage";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody MemberDTO memberDTO) {
        manageService.deleteUser(memberDTO.getUserId());
        return "redirect:/login";
    }
}
