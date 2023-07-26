package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRespository memberRespository;

    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRespository.login(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }
}
