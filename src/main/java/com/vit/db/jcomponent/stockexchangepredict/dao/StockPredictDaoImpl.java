
package com.vit.db.jcomponent.stockexchangepredict.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.vit.db.jcomponent.stockexchangepredict.model.GScript;
import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Repository
public class StockPredictDaoImpl implements StockPredictDao {

	@Autowired
	StockRepository stockrepository;
	

	public List<GScript> getUnstructuredData() {
		Date date = new Date();
		Date gdatePrediction = new Date();

		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	      LocalDate localDate = LocalDate.now();
	      System.out.println("date is "+dtf.format(localDate.minusDays(1)));  

	      String dateprev = localDate.minusDays(1).format(dtf);
		try {
			gdatePrediction = new SimpleDateFormat("dd/MM/yyyy").parse(dateprev);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		System.out.println("gdatePrediction Date is " + gdatePrediction);

		MongoClient mongoC = new MongoClient("localhost", 27017);
		DB db = mongoC.getDB("StockNSE");
		DBCollection clx = db.getCollection("C1");
		BasicDBObject query = new BasicDBObject();
		query.put("TIMESTAMP", new BasicDBObject("$lte", gdatePrediction));
		query.containsField("SYMBOL");
		clx.find(query).sort(new BasicDBObject("TIMESTAMP", -1));
		DBCollection clxx = clx.find(query).getCollection();
		DBCursor cursor = clx.find(query).limit(50);

		
		Iterator<DBObject> iterator = cursor.iterator();

		List<DBObject> listo = new ArrayList<>();
		for (Iterator<DBObject> it = cursor.iterator(); it.hasNext();) {
			listo.add(it.next());
		}

		int listSizeo = listo.size();
		ArrayList<GScript> sArr = new ArrayList<GScript>();

		for (int x = 0; x < listSizeo; x++) {
			GScript s1 = new GScript((String) listo.get(x).get("SYMBOL"), (((Double) listo.get(x).get("OPEN")*100)/100),
					(Double) listo.get(x).get("HIGH"), (Double) listo.get(x).get("LOW"),
					(Double) listo.get(x).get("CLOSE"), (Double) listo.get(x).get("LAST"),
					(Double) listo.get(x).get("PREVCLOSE"), (Integer) listo.get(x).get("TOTTRDQTY"),
					(Double) listo.get(x).get("TOTTRDVAL"), (Date) listo.get(x).get("TIMESTAMP"),
					(Integer) listo.get(x).get("TOTTRADES"), (Double) listo.get(x).get("LAST"),
					(Double) listo.get(x).get("LAST"));

			sArr.add(s1);
		}
		
		System.out.println(sArr);
		return sArr;
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

		return stockrepository.findBySYMBOL(name);
	}

}
