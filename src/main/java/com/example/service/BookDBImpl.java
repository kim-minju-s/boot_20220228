package com.example.service;

import java.util.Collection;
import java.util.List;



import com.example.entity.Book;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// 1. 서비스
@Service
public class BookDBImpl implements BookDB{  //2. 설계 인터페이스 구현
    // DB연결 객체 생성
    @Autowired
    MongoTemplate mongoDB;


    // 일괄추가
    @Override
    public int insertBatchBook(List<Book> list) {
        try {

            // 실제 수행
            // collection 인터페이스, 상속 관계를 따져볼 것
            Collection<Book> retList = mongoDB.insert(list, Book.class);

            System.out.println("Collection 확인---->" + retList.size());

            if (retList.size() == list.size()) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    // 페이지네이션 & 검색으로 조회
    @Override
    public List<Book> selectListPageSearchBook(int page, String text) {
        try {
            Query query = new Query();

            // 검색패턴 (.*a.* => a 가 포함된 것 해당), 정규식(regex)
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            // 페이지네이션 (0부터 시작)
            Pageable pageable = PageRequest.of(page-1, 10);
            query.with(pageable);

            // 정렬(_id 기준 내림차순)
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);

            return mongoDB.find(query, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public long countSearchBook(String text) {
        try {
            Query query = new Query();

            // 검색패턴 (.*a.* => a 가 포함된 것 해당), 정규식(regex)
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            return mongoDB.count(query, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 일괄 삭제
    @Override
    public long deleteBatchBook(List<Long> code) {
        try {
        	// long[] 	=> List<Long>
        	// code 	=> [2, 5, 3] => collection<Long>
            Query query = new Query();
            // is 와 in 의 차이
            query.addCriteria(Criteria.where("_id").in(code));
            
//            System.out.println("쿼리 ---> " + query);

            DeleteResult result = mongoDB.remove(query, Book.class);

//            System.out.println("삭제 결과값--->" + result);
//            System.out.println(code.length);

            if (result.getDeletedCount() == (long)code.size()) {
                return 1L;
            }
            
            return 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    // 코드에 해당하는 목록 가져오기
	@Override
	public List<Book> selectListWhereIn(List<Long> code) {
		try {
			Query query = new Query();
            // is 와 in 의 차이
            query.addCriteria(Criteria.where("_id").in(code));
            
            // 내림차순 DESC, 오름차순 ASC
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);
			
			return mongoDB.find(query, Book.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 일괄수정
	@Override
	public long updateBatchBook(List<Book> list) {
		try {
			long updateCount = 0;
			for(Book tmp : list) {
				
				Query query = new Query();
				query.addCriteria(Criteria.where("_id").is(tmp.getCode()));
				
				Update update = new Update();
				update.set("title", tmp.getTitle());
				update.set("price", tmp.getPrice());
				update.set("writer", tmp.getWriter());
				update.set("category", tmp.getCategory());
				
				UpdateResult result = mongoDB.updateFirst(query, update, Book.class);
				updateCount += result.getMatchedCount();
				// getMatchedCount 는 변경을 안해도 일치하는 개수를 셈
			}
			if(updateCount == list.size()) {
				return 1;
			}
			
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}



    
}
