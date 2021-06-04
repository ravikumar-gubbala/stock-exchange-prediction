package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vit.db.jcomponent.stockexchangepredict.model.GScript;
import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Repository
public interface StockPredictDao {

	public List<GScript> getUnstructuredData();
	
	public void setStockData(StockExchange stock);

	public void postSectors(String filteredName, String filteredID, String filteredLocation);

	public List<StockExchange> getStockData(String name);

}
