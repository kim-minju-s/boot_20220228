package com.example.service;

import java.util.Date;

import com.example.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemDBImpl implements ItemDB {

    @Autowired
    private MongoTemplate mongodb;

    // 내가 직접 만든 SERVICE 모든 시퀀스의 값을 가져올 수 있게 구현함.
    @Autowired
    private SequenceService sequenceService;

    @Override
    public Item insertItem(Item item) {
        try {
            item.setCode(sequenceService.generatorSequence("SEQ_ITEM3_NO"));
            item.setRegdate(new Date());
            return mongodb.insert(item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
