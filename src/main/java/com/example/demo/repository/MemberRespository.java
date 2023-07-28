    package com.example.demo.repository;

import com.example.demo.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository
public class MemberRespository {
    private final SqlSessionTemplate sqlSessionTemplate;

    public MemberRespository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public int register(MemberDTO memberDTO) {

        return sqlSessionTemplate.insert("Member.register", memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sqlSessionTemplate.selectOne("Member.login", memberDTO);
    }

    public MemberDTO findById(String user_id) {
        return sqlSessionTemplate.selectOne("Member.findById", user_id);

    }
} // 수정 후
