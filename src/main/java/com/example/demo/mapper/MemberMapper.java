package com.example.demo.mapper;

import com.example.demo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    int register(MemberDTO memberDTO);

    MemberDTO id_check(String user_id);
    MemberDTO login (MemberDTO memberDTO);


}
