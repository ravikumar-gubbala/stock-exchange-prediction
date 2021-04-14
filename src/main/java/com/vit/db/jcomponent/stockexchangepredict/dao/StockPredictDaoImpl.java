
package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Repository
public class StockPredictDaoImpl implements StockPredictDao {

	public List<StockExchange> getUnstructuredData() {
		List<StockExchange> stlist = new ArrayList<StockExchange>();
		return stlist;
	}

	@Override
	public void postSectors(String filteredName, String filteredID, String filteredLocation) {
	
	}

}
