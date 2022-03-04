package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

// DB 컬렉션 지정
@Document(collection = "member3")

// entity는 DB 에 들어갈 column 의 객체
public class Member {

    @Id
    private String id = null;

    private String pw = null;

    private String pw1 = null;

    private String name = null;

    private int age = 0;

    // 일시적 (column으로 만들어지지 않음)
    // 개발에서 필요한 정보를 보관하기 위한 용도
    @Transient
    private String newPw = null;
}
