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

    @Transactional
    public void updateUser(MemberDTO memberDTO){
        manageMapper.updateUser(memberDTO);
    }

    @Transactional
    public void deleteUser(String userId){
        manageMapper.deleteUser(userId);
    }
}
