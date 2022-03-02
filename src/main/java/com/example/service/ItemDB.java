package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ItemDB {
    
    // 물품등록 (추가할 물품정보가 처리 후에 int(-1, 0, 1)로 반환)
    public int insertItem(Item item);

    // 물품목록 (페이지 정보 1,2,3)
    public List<Item> selectListItem(Pageable page);

    // 이미지 1개 조회
    public Item selectOneItemImage(long code);

    // 물품 상세조회
    public Item selectOneItem(long code);
}
