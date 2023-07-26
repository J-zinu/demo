package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성 (생성자 주입) (lombok)
public class MemberService {
    private final MemberRespository memberRespository;

    public int register(MemberDTO memberDTO) {
        String idCheckResult = id_Check(memberDTO.getUser_id());
        // 이미 존재하는 id 일경우 -1 반환
        if (idCheckResult.equals("fail")) {
            return -1;
        }
        // 중복되지 않은 id 일경우 자동적으로 1을 반환하므로
        //MemberController.java 에서
        //  @PostMapping("/register") //회원가입(데이터베이스에 회원정보 저장)
        //    public String register(@ModelAttribute MemberDTO memberDTO) {
        //        int registerResult = memberService.register(memberDTO);
        //        System.out.println("registerResult = " + registerResult);
        //        if(registerResult >0 ) {
        //            return "redirect:/login";
        //        }
        //        else {
        //            return "redirect:/register";
        //        }
        //    해당 부분에서 회원가입 진행한다.
        else {
            return memberRespository.register(memberDTO);
        }
    }
    // return memberRespository.register(memberDTO);

    public String id_Check(String user_id) {
        MemberDTO memberDTO = memberRespository.findById(user_id);
        if (memberDTO != null) {
            return "fail";
        } else {
            return "success";
        }
    }
}
