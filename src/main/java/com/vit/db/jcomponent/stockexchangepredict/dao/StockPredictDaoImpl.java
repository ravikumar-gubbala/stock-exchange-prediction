
package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Repository
public class StockPredictDaoImpl implements StockPredictDao {
	
	@Autowired
	StockRepository stockrepository;

	public List<StockExchange> getUnstructuredData() {
		List<StockExchange> stlist = stockrepository.findAll();
		return stlist;
	}

	@Override
	public void postSectors(String filteredName, String filteredID, String filteredLocation) {
	
	}

	@Override
	public void setStockData(StockExchange stock) {
		stockrepository.save(stock);
	}

	@Override
	public ArrayList<StockExchange> getStockData(String name) {

		return stockrepository.findBySymbol(name);
	}

}
