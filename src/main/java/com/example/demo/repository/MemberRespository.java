package com.example.demo.repository;

import com.example.demo.dto.MemberDTO;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRespository {
    public int register(MemberDTO memberDTO) {
        System.out.println("memberDTO: " + memberDTO);
        return 0;
    }

}
