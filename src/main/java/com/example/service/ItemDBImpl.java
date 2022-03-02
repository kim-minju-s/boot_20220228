package com.example.service;

import java.util.Date;
import java.util.List;


import com.example.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ItemDBImpl implements ItemDB {

    @Autowired
    private MongoTemplate mongoDB;

    // 내가 직접 만든 SERVICE 모든 시퀀스의 값을 가져올 수 있게 구현함.
    @Autowired
    private SequenceService sequence;

    @Override
    public int insertItem(Item item) {
        try {
            // item의 id에 seq 값 넣기
            // 자동으로 물품번호 업데이트
            long seq = sequence.generatorSequence("SEQ_ITEM4_CODE");
            item.setCode(seq);

            // import java.util.Data;
            Date date = new Date(); // 현재시간만들기
            item.setRegdate(date);

            Item item1 = mongoDB.insert(item);
            if (item1.getCode() == seq) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }

    // 물품 목록 조회
    @Override
    public List<Item> selectListItem(Pageable pageable) {
        try {
            Query query = new Query();
            // 페이지
            query.with(pageable);   // skip().limit()
            query.fields().exclude("filedata", "filetype", "filesize", "filename"); //projection
            // 정렬
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);
            
            // 필요한 정보들을 전부 query 에 담아서 전달
            return mongoDB.find(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 이미지 1개 조회
    @Override
    public Item selectOneItemImage(long code) {
        try {
            // 조건 물품번호가 일치하는 것 1개 가져오기
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(code);
            query.addCriteria(criteria);
            
            // projection
            query.fields().include("filedata","filetype","filesize");

            return mongoDB.findOne(query, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item selectOneItem(long code) {
        // TODO Auto-generated method stub
        return null;
    }

}
