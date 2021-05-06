package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;
@Repository
public interface StockRepository extends MongoRepository<StockExchange, String> {

	 default  ArrayList<StockExchange> getstockdata(String name) {
		ArrayList<StockExchange> stockexchangelist = new ArrayList<StockExchange>();
		
		GroupOperation groupOperation = Aggregation.group("symbol");
		MatchOperation matchOperation =Aggregation.match(new Criteria("symbol").elemMatch(new Criteria(name)));
		return stockexchangelist;
	};
	
	
	ArrayList<StockExchange> findBySymbol(String name);
	
}
