package com.example.entity;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Document(collection = "item4")
public class Item {
    
    @Id
    private long code = 0L;

    // @Field(name = "itemname")
    private String name = null;

    private long price = 0L;
    
    private long quantity = 0L;

    // 이미지
    // byte 배열
    private byte[] filedata = null;
    private String filetype = null;
    private String filename = null;
    private long filesize = 0L;

    // import java.util
    private Date regdate = null;
}
