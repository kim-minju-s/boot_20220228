package com.example.entity;


import java.util.Date;

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

@Document(collection = "item3")
public class Item {
    
    @Id
    private long code = 0L;

    @Field(name = "itemname")
    private String name = null;

    private long price = 0L;
    
    private long quantity = 0L;

    // import java.util
    private Date regdate = null;
}
