package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Board;

@Repository
public interface BoardRepository extends MongoRepository<Board, Long>{
	
}
