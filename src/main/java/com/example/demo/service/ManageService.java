// Service
package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.ManageMapper;
import org.springframework.dao.DataIntegrityViolationException;
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


    @Transactional
    public int updateUser(MemberDTO memberDTO) {
        int pw_upDate = manageMapper.updateUser(memberDTO);
        if (pw_upDate > 0) {
            return pw_upDate;
        } else {
            return 0;
        }
    }

//    @Transactional
//    public int deleteUser(MemberDTO memberDTO) {
//        try {
//            int user_Delete = manageMapper.deleteUser(memberDTO);
//            if (user_Delete <= 0) {
//                throw new RuntimeException("계정 삭제에 실패하였습니다.");
//            }
//            return user_Delete;
//        } catch (Exception e) {
//            // 여기서 추가적인 로그 기록 또는 처리를 할 수 있습니다.
//            throw e; // 예외를 다시 던져서 트랜잭션 롤백을 트리거합니다.
//        }
//    }

    @Transactional
    public int deleteUser(MemberDTO memberDTO) {
        try {
            int user_Delete = manageMapper.deleteUser(memberDTO);
            if (user_Delete <= 0 ) {
                throw new RuntimeException("계정삭제에 실패하였습니다.");
            }
            return user_Delete;
        } catch (DataIntegrityViolationException e) {
            //외래키 제약조건으로 인하여 TodoList를 참조하고 있으므로 계정 삭제에 대해서 예외처리를 합니다.
            System.out.println("외래키 제약조건으로 인하여 TodoList를 참조하고 있으므로 계정 삭제에 대해서 예외처리를 합니다.");
            return 0;
        } catch (Exception e) {
            throw e;
        }
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

//뷰페이지를 반환 하는 방법은 3가지가있음 ModelAndView, String, ResponseBody