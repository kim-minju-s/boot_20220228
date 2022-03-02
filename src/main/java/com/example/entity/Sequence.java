package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Document(collection = "sequence")
public class Sequence {
    
    // 필드이름이 _id
    @Id
    // @Field(name = "_id")
    private String seqName = null;

    @Field(name = "seq")
    private long seq = 0L;
}
