package com.example.service;

import com.example.entity.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemDB {
    
    // Item클래스에서 지정한 변수를 가져와서 씀
    public Item insertItem(Item item);

}
