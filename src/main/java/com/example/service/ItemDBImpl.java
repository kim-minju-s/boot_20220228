package com.example.service;

import java.util.Date;
import java.util.List;


import com.example.entity.Item;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    // 물품 상세 조회
    @Override
    public Item selectOneItem(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));

            return mongoDB.findOne(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();;
            return null;
        }
    }

    // 물품 개수 조회
    @Override
    public long selectItemCount() {
        try {
            Query query = new Query();
            return mongoDB.count(query, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 물품 1개 삭제
    @Override
    public int deleteItemOne(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));

            DeleteResult result = mongoDB.remove(query, Item.class);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 물품 수정
    @Override
    public int updateItemOne(Item item) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(item.getCode()));

            Update update = new Update();
            update.set("name", item.getName());
            update.set("price", item.getPrice());
            update.set("quantity", item.getQuantity());
            if (item.getFilesize() > 0) {   // 파일 첨부되면
                update.set("filedata", item.getFiledata());
                update.set("filesize", item.getFilesize());
                update.set("filetype", item.getFiletype());
                update.set("filename", item.getFilename());
            }

            UpdateResult result = mongoDB.updateFirst(query, update, Item.class);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
