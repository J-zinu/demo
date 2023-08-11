package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성 (생성자 주입) (lombok)
public class MemberService {

    private final MemberMapper memberMapper;

    //public MemberService(MemberMapper memberMapper) {
    //this.memberMapper = memberMapper; }를 하는것과
    //@RequiredArgsConstructor 사용하는 것과 같습니다.

    public int register(MemberDTO memberDTO){

        MemberDTO idCheckResult = memberMapper.id_check(memberDTO.getUser_id()); //getUser_id()는 Lombok이 자동적으로 생성하므로 메소드를 적을 필요가 없음
        if(idCheckResult != null){ //id를 확인할 때 null 이 아니므로 이미 존재하는 경우 입니다. 그래서 -1을 반환한다.
            return -1;
        }

        else{ //특정 user_id가 존재하지 않으므로 MemberDTO 객체를 반환합니다.
            return memberMapper.register(memberDTO);
        }
    }

    public String id_Check(String user_id) {
        MemberDTO memberDTO = memberMapper.id_check(user_id);
        if (memberDTO != null) {
            return "fail";
        } else {
            return "success";
        }
    }

}

//


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