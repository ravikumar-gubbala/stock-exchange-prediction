package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;
@Repository
public interface StockRepository extends MongoRepository<StockExchange, String> {
	
	ArrayList<StockExchange> findBySYMBOL(String name);
	
}
