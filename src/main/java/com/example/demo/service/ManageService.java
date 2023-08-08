// Service
package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.ManageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageService {
    private final ManageMapper manageMapper;


    public ManageService(ManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }

//    public void updateUser(MemberDTO memberDTO){
//        manageMapper.updateUser(memberDTO);
//    }


    public int updateUser(MemberDTO memberDTO) {
        int pw_upDate = manageMapper.updateUser(memberDTO);
        if (pw_upDate > 0){
            return pw_upDate;
        }
        else {
            return 0;
        }
    }


    public int deleteUser(MemberDTO memberDTO){
        int user_Delete = manageMapper.deleteUser(memberDTO);
        if(user_Delete > 0 ){
            return user_Delete;
        }
        else {
            return 0;
        }
    }

//
//    public void deleteUser(MemberDTO memberDTO) {
//        System.out.println("ManageService = " + memberDTO);
//        manageMapper.deleteUser(memberDTO);
//    }
//    @Transactional
//    public void deleteUser(MemberDTO memberDTO) {
//        try {
//            manageMapper.deleteUser(memberDTO);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
////        manageMapper.deleteUser(memberDTO);
////        throw new RuntimeException("롤백을 테스트하기 위한 강제 RuntimeException");
//    }
}
//뷰페이지를 반환 하는 방법은 3가지가있음 ModelAndView, String, ResponseBody