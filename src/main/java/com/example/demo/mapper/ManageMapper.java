// Mapper
package com.example.demo.mapper;

import com.example.demo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {
//    void updateUser(MemberDTO memberDTO);

//    void deleteUser(MemberDTO memberDTO);

    int updateUser(MemberDTO memberDTO);

    int deleteUser(MemberDTO memberDTO);

}
