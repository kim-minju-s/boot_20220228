package com.example.service;

import java.util.List;

import com.example.entity.Member;

import org.springframework.stereotype.Service;

// DB 와 연동하는 부분
// 구현하는 설계 부분
@Service
public interface MemberDB {

    
    // 추가할 내용을 member로 주면 추가한 후에
    // 실제 추가된 내용을 반환
    public Member insertMember(Member member);

    // 회원 전체목록(page, search X)
    public List<Member> selectListMember();

    // 회원 1명 삭제(회원 아이디가 오면 삭제후 -1, 0 또는 1로 리턴)
    public int deleteMember(String id);

}
