package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
//import com.example.demo.repository.MemberRespository;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성 (생성자 주입) (lombok)
public class MemberService {

    private final MemberMapper memberMapper;

    public int register(MemberDTO memberDTO){
        MemberDTO idCheckResult = memberMapper.findById(memberDTO.getUser_id());
        //이미 존재하는 경우 -1을 반환한다.
        if(idCheckResult != null){
            return -1;
        }
        else{
            return memberMapper.register(memberDTO);
        }
    }

    public String id_Check(String user_id) {
        MemberDTO memberDTO = memberMapper.findById(user_id);
        if (memberDTO != null) {
            return "fail";
        } else {
            return "success";
        }
    }



// 레파지토리 시절
//    private final MemberRespository memberRespository;
//
//    public int register(MemberDTO memberDTO) {
//        String idCheckResult = id_Check(memberDTO.getUser_id());
//        // 이미 존재하는 id 일경우 -1 반환
//        if (idCheckResult.equals("fail")) {
//            return -1;
//        }
//        else {
//            return memberRespository.register(memberDTO);
//        }
//    }
//    // return memberRespository.register(memberDTO);
//
//    public String id_Check(String user_id) {
//        MemberDTO memberDTO = memberRespository.findById(user_id);
//        if (memberDTO != null) {
//            return "fail";
//        } else {
//            return "success";
//        }
//    }

}
