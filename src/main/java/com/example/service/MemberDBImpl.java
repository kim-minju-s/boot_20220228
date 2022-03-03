package com.example.service;

import java.util.List;

import com.example.entity.Member;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// DB 연동을 실제로 수행하는 구현부
// 구현부는 프레임워크에 따라서 안만듬..
@Service
public class MemberDBImpl implements MemberDB {

    // 환경설정으로 생성된 객체를 가져옴
    // MongoTemplate mongodb = new MongoTemplate();
    @Autowired
    private MongoTemplate mongodb;

    @Override
    public Member insertMember(Member member) {
        try {
            return mongodb.insert(member);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Member> selectListMember() {
        try {
            Query query = new Query();
            return mongodb.find(query, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteMember(String id) {
        try {
            Member member = new Member();
            member.setId(id);
            
            DeleteResult result = mongodb.remove(member);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Member selectOneMember(String id) {
        try {
            Query query = new Query();
            // where 은 조건에 해당함("_id"가 id 인 것)
            Criteria criteria = Criteria.where("_id").is(id);
            query.addCriteria(criteria);

            return mongodb.findOne(query, Member.class);
        } catch (Exception e) {
            e.printStackTrace();    // 개발자를 위한 출력(debug용)
            return null;
        }
    }

    @Override
    public int updateMember(Member member) {
        try {
            // query 조건
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(member.getId());
            query.addCriteria(criteria);

            // update 수정 내용
            Update update = new Update();
            update.set("name", member.getName());
            update.set("age", member.getAge());

            UpdateResult result = 
                mongodb.updateFirst(query, update, Member.class);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 로그인
    @Override
    public Member selectLogin(Member member) {
        try {
            Query query = new Query();
            
            query.addCriteria(Criteria.where("_id").is(member.getId()));
            query.addCriteria(Criteria.where("pw").is(member.getPw()));
            
            return mongodb.findOne(query, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
