package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성 (생성자 주입) (lombok)
public class MemberService {
    private final MemberRespository memberRespository;

    public int register(MemberDTO memberDTO) {
        return memberRespository.register(memberDTO);
    }


    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRespository.login(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }
}
