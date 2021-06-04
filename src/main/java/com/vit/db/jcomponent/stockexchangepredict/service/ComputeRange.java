package com.vit.db.jcomponent.stockexchangepredict.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class ComputeRange {

	public static String theMonth(int month) {
		String[] monthNames = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
		return monthNames[month];
	}

	static class gScript {
		String symbol;
		Double open;
		Double high;
		Double low;
		Double close;
		Double last;
		Double prevclose;
		Integer tottrdqty;
		Double tottrdval;
		Date timestamp;
		Integer tottrades;
		Double LogVal;
		Double StdDevVal;

		gScript(String symbol, Double open, Double high, Double low, Double close, Double last, Double prevclose,
				Integer tottrdqty, Double tottrdval, Date timestamp, Integer tottrades, Double LogVal,
				Double StdDevVal) {
			this.symbol = symbol;
			this.open = open;
			this.high = high;
			this.low = low;
			this.close = close;
			this.last = last;
			this.prevclose = prevclose;
			this.tottrdqty = tottrdqty;
			this.tottrdval = tottrdval;
			this.timestamp = timestamp;
			this.tottrades = tottrades;
			this.LogVal = LogVal;
			this.StdDevVal = StdDevVal;
		}
	}

	public static Integer TestList() {

		String symbol = "SBIN";
		Double open = Double.parseDouble("0.0");
		Double high = Double.parseDouble("0.0");
		Double low = Double.parseDouble("0.0");
		Double close = Double.parseDouble("0.0");
		Double last = Double.parseDouble("0.0");
		Double prevclose = Double.parseDouble("0.0");
		Integer tottrdqty = Integer.parseInt("0");
		Double tottrdval = Double.parseDouble("0.0");
		Double LogVal = Double.parseDouble("0.0");
		Double StdDevVal = Double.parseDouble("0.0");
		Date timestamp = new Date();
		String sDate1 = "31/12/1998";
		try {
			timestamp = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (Exception ex) {
			System.out.println("Error: Invalide Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		Integer tottrades = Integer.parseInt("0");

		gScript s1 = new gScript(symbol, open, high, low, close, last, prevclose, tottrdqty, tottrdval, timestamp,
				tottrades, LogVal, StdDevVal);

		open = Double.parseDouble("1.0");
		high = Double.parseDouble("2.0");
		low = Double.parseDouble("3.0");
		close = Double.parseDouble("4.0");
		last = Double.parseDouble("5.0");
		prevclose = Double.parseDouble("6.0");
		tottrdqty = Integer.parseInt("7");
		tottrdval = Double.parseDouble("8.0");
		tottrades = Integer.parseInt("9");
		sDate1 = "24/05/2021";
		try {
			timestamp = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (Exception ex) {
			System.out.println("Error: Invalide Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		gScript s2 = new gScript(symbol, open, high, low, close, last, prevclose, tottrdqty, tottrdval, timestamp,
				tottrades, LogVal, StdDevVal);

		ArrayList<gScript> sArr = new ArrayList<gScript>();
		sArr.add(s1);
		sArr.add(s2);

		Iterator itr = sArr.iterator();
		while (itr.hasNext()) {
			gScript s3 = (gScript) itr.next();
			// System.out.println(s3.symbol +" "+ s3.close +" "+ s3.timestamp);
		}
		return 0;
	}

	public static double calculateSD(double numArray[]) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = numArray.length;

		for (double num : numArray) {
			sum += num;
		}

		double mean = sum / length;

		for (double num : numArray) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}

	public String Compute(String gDate, String symbol) throws JsonProcessingException {

//		System.out.printf("Enter Name of the Database: ");
		Scanner scanner = new Scanner(System.in);
		String gDatabaseName = "StockNSE";
//	    System.out.printf("Enter Name of the Collection: ");
		String gCollectionName = "C1";
		// System.out.printf("Enter Name of the Script: "); /// Taking from User
		String gScriptName = symbol;
		// System.out.printf("Enter Date for Prediction [DD/MM/YYYY]: "); /// Taking
		// from User
		String sDate1 = "31/12/1998";
		String gDateStr = gDate;
		Date gdatePrediction = new Date();
		try {
			gdatePrediction = new SimpleDateFormat("dd/MM/yyyy").parse(gDate);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		/*
		 * String gDatabaseName = "scripts"; String gCollectionName = "stocks"; String
		 * gScriptName = "SBIN"; Date gdatePrediction = new Date(); String
		 * sDate1="27/05/2021";
		 */

		try {
			gdatePrediction = new SimpleDateFormat("dd/MM/yyyy").parse(gDateStr);
		} catch (Exception ex) {
			System.out.println("Error: Invalide Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		System.out.println("Database: " + gDatabaseName);
		System.out.println("Collection Name: " + gCollectionName);
		System.out.println("Scrtipt Name: " + gScriptName);
		System.out.println("Prediction Date: " + gdatePrediction);

		try {
			MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("empdb");
			MongoCollection<Document> collection = database.getCollection("kid");
			Document myDoc = collection.find().first();
			// System.out.println(myDoc.toJson());
			System.out.println("Mongo Db is up and Running...");
		} catch (Exception ex) {
			System.out.println("Error: Can NOT connect to Mongo");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		String currentDirectory = System.getProperty("user.dir");
		System.out.println("The current working directory is " + currentDirectory);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(gdatePrediction);

		Integer gDay = calendar.get(Calendar.DAY_OF_MONTH);
		Integer gMonth = calendar.get(Calendar.MONTH);
		Integer gYear = calendar.get(Calendar.YEAR);
		String gMonthStr = theMonth(gMonth);
		Integer g10Days = 0;

		String gUrlStr1 = "https://www1.nseindia.com/content/historical/EQUITIES/";
		String gUrlStr3 = "bhav.csv.zip";
		String gUrlStr2 = "";
		String gUrlStr4 = "";
		String gUrlStr5 = "";
		String gUrlStr6 = "";
		List<String> DldFiles = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(gdatePrediction);

		MongoClient mongoC = new MongoClient("localhost", 27017);
		DB db = mongoC.getDB(gDatabaseName);
		List<String> listofDB = mongoC.getDatabaseNames();
		for (String dbName : listofDB) {
			// System.out.println(dbName);
		}

		DBCollection clx = db.getCollection(gCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.put("SYMBOL", new BasicDBObject("$eq", gScriptName));
		query.put("TIMESTAMP", new BasicDBObject("$lte", gdatePrediction));
		query.containsField("SYMBOL");
		clx.find(query).sort(new BasicDBObject("TIMESTAMP", -1));
		DBCollection clxx = clx.find(query).getCollection();
		DBCursor cursor = clx.find(query).limit(10);

		TestList();

		List<gScript> sArr1 = new ArrayList<gScript>();

		Iterator<DBObject> iterator = cursor.iterator();

		List<DBObject> listo = new ArrayList<>();
		List<DBObject> listn = new ArrayList<>();

		for (Iterator<DBObject> it = cursor.iterator(); it.hasNext();) {
			listo.add(it.next());
		}

		int listSizeo = listo.size();
		ArrayList<gScript> sArr = new ArrayList<gScript>();

		for (int x = 0; x < listSizeo; x++) {
			gScript s1 = new gScript((String) listo.get(x).get("SYMBOL"), (Double) listo.get(x).get("OPEN"),
					(Double) listo.get(x).get("HIGH"), (Double) listo.get(x).get("LOW"),
					(Double) listo.get(x).get("CLOSE"), (Double) listo.get(x).get("LAST"),
					(Double) listo.get(x).get("PREVCLOSE"), (Integer) listo.get(x).get("TOTTRDQTY"),
					(Double) listo.get(x).get("TOTTRDVAL"), (Date) listo.get(x).get("TIMESTAMP"),
					(Integer) listo.get(x).get("TOTTRADES"), (Double) listo.get(x).get("LAST"),
					(Double) listo.get(x).get("LAST"));

			sArr.add(s1);
		}

		List<Double> listcal = new ArrayList<Double>();
		Double dc1, dc2, dc3, dc4, dc5;
		Double dcAvg, dcStd, dcBase;
		Double dcavg3rd, dcavg5th, dcavg7th, dcavg9th, dcavg11th, dcavg13th, dcavg15th, dcavg17th;
		Double dcstd3rd, dcstd5th, dcstd7th, dcstd9th, dcstd11th, dcstd13th, dcstd15th, dcstd17th;
		Double dcsum3rd, dcsum5th, dcsum7th, dcsum9th, dcsum11th, dcsum13th, dcsum15th, dcsum17th;
		Double dcsum3rdl, dcsum5thl, dcsum7thl, dcsum9thl, dcsum11thl, dcsum13thl, dcsum15thl, dcsum17thl;
		Double dcsum3rdh, dcsum5thh, dcsum7thh, dcsum9thh, dcsum11thh, dcsum13thh, dcsum15thh, dcsum17thh;

		dc1 = Double.parseDouble("0.0");
		dc2 = Double.parseDouble("0.0");
		dc3 = Double.parseDouble("0.0");
		dc4 = Double.parseDouble("0.0");
		double dArr1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int x = 1; x < listSizeo; x++) {
			// System.out.println(sArr.get(x).close);
			dc1 = sArr.get(x).close / sArr.get(x - 1).close;
			dc2 = Math.log(dc1);
			listcal.add(dc2);
			dArr1[x - 1] = dc2;
			dc3 = dc3 + dc2;
			// System.out.println(dc1 + " " + dc2 + " " +dc3);
		}
		dc4 = dc3 / 9;
		dcAvg = dc4;
		dc5 = calculateSD(dArr1);
		dcStd = dc5;
		dcBase = sArr.get(0).close;

		dcavg3rd = (Math.sqrt(3)) * dcAvg;
		dcavg5th = (Math.sqrt(5)) * dcAvg;
		dcavg7th = (Math.sqrt(7)) * dcAvg;
		dcavg9th = (Math.sqrt(9)) * dcAvg;
		dcavg11th = (Math.sqrt(11)) * dcAvg;
		dcavg13th = (Math.sqrt(13)) * dcAvg;
		dcavg15th = (Math.sqrt(15)) * dcAvg;
		dcavg17th = (Math.sqrt(17)) * dcAvg;

		dcstd3rd = (Math.sqrt(3)) * dcStd;
		dcstd5th = (Math.sqrt(5)) * dcStd;
		dcstd7th = (Math.sqrt(7)) * dcStd;
		dcstd9th = (Math.sqrt(9)) * dcStd;
		dcstd11th = (Math.sqrt(11)) * dcStd;
		dcstd13th = (Math.sqrt(13)) * dcStd;
		dcstd15th = (Math.sqrt(15)) * dcStd;
		dcstd17th = (Math.sqrt(17)) * dcStd;

		dcsum3rd = dcavg3rd + dcstd3rd;
		dcsum5th = dcavg5th + dcstd5th;
		dcsum7th = dcavg7th + dcstd7th;
		dcsum9th = dcavg9th + dcstd9th;
		dcsum11th = dcavg11th + dcstd11th;
		dcsum13th = dcavg13th + dcstd13th;
		dcsum15th = dcavg15th + dcstd15th;
		dcsum17th = dcavg17th + dcstd17th;

		dcsum3rdl = dcBase - (dcsum3rd * dcBase);
		dcsum5thl = dcBase - (dcsum5th * dcBase);
		dcsum7thl = dcBase - (dcsum7th * dcBase);
		dcsum9thl = dcBase - (dcsum9th * dcBase);
		dcsum11thl = dcBase - (dcsum11th * dcBase);
		dcsum13thl = dcBase - (dcsum13th * dcBase);
		dcsum15thl = dcBase - (dcsum15th * dcBase);
		dcsum17thl = dcBase - (dcsum17th * dcBase);

		dcsum3rdh = dcBase + (dcsum3rd * dcBase);
		dcsum5thh = dcBase + (dcsum5th * dcBase);
		dcsum7thh = dcBase + (dcsum7th * dcBase);
		dcsum9thh = dcBase + (dcsum9th * dcBase);
		dcsum11thh = dcBase + (dcsum11th * dcBase);
		dcsum13thh = dcBase + (dcsum13th * dcBase);
		dcsum15thh = dcBase + (dcsum15th * dcBase);
		dcsum17thh = dcBase + (dcsum17th * dcBase);

		Map<String, Object> Dmap = new HashMap<String, Object>();
		MongoClient mongoC1x = new MongoClient("localhost", 27017);
		DB db1x = mongoC1x.getDB(gDatabaseName);
		DBCollection clx1x = db.getCollection(gScriptName);
		clx1x.drop();
		SimpleDateFormat Dformat = new SimpleDateFormat("dd-MMM-yyyy");
		Date Tdate;
		Dmap.put("SYMBOL", gScriptName);
		Dmap.put("C3LOW", dcsum3rdl);
		Dmap.put("C5LOW", dcsum5thl);
		Dmap.put("C7LOW", dcsum7thl);
		Dmap.put("C9LOW", dcsum9thl);
		Dmap.put("C11LOW", dcsum11thl);
		Dmap.put("C13LOW", dcsum13thl);
		Dmap.put("C15LOW", dcsum15thl);
		Dmap.put("C17LOW", dcsum17thl);
		Dmap.put("C3HIGH", dcsum3rdh);
		Dmap.put("C5HIGH", dcsum5thh);
		Dmap.put("C7HIGH", dcsum7thh);
		Dmap.put("C9HIGH", dcsum9thh);
		Dmap.put("C11HIGH", dcsum11thh);
		Dmap.put("C13HIGH", dcsum13thh);
		Dmap.put("C15HIGH", dcsum15thh);
		Dmap.put("C17HIGH", dcsum17thh);
		Dmap.put("TIMESTAMP", gdatePrediction);
		try {
			clx1x.insert(new BasicDBObject(Dmap));
		} catch (Exception ex) {
			System.out.printf("Error: Unable to upload computed values MongoDB !!!");
			ex.printStackTrace();
			System.exit(0);
		}

		HashMap<String, Double> hashMapCycle3 = new HashMap<String, Double>();
		hashMapCycle3.put("Low", (Math.round(dcsum3rdl * 100.0) / 100.0));
		hashMapCycle3.put("High", (Math.round(dcsum3rdh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle5 = new HashMap<String, Double>();
		hashMapCycle5.put("Low", (Math.round(dcsum5thl * 100.0) / 100.0));
		hashMapCycle5.put("High", (Math.round(dcsum5thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle7 = new HashMap<String, Double>();
		hashMapCycle7.put("Low", (Math.round(dcsum7thl * 100.0) / 100.0));
		hashMapCycle7.put("High", (Math.round(dcsum7thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle9 = new HashMap<String, Double>();
		hashMapCycle9.put("Low", (Math.round(dcsum9thl * 100.0) / 100.0));
		hashMapCycle9.put("High", (Math.round(dcsum9thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle11 = new HashMap<String, Double>();
		hashMapCycle11.put("Low", (Math.round(dcsum11thl * 100.0) / 100.0));
		hashMapCycle11.put("High", (Math.round(dcsum11thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle13 = new HashMap<String, Double>();
		hashMapCycle13.put("Low", (Math.round(dcsum13thl * 100.0) / 100.0));
		hashMapCycle13.put("High", (Math.round(dcsum13thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle15 = new HashMap<String, Double>();
		hashMapCycle15.put("Low", (Math.round(dcsum15thl * 100.0) / 100.0));
		hashMapCycle15.put("High", (Math.round(dcsum15thh * 100.0) / 100.0));

		HashMap<String, Double> hashMapCycle17 = new HashMap<String, Double>();
		hashMapCycle17.put("Low", (Math.round(dcsum17thl * 100.0) / 100.0));
		hashMapCycle17.put("High", (Math.round(dcsum17thh * 100.0) / 100.0));

		HashMap<String, HashMap<String, Double>> hashMapFinal = new HashMap<String, HashMap<String, Double>>();
		hashMapFinal.put("Cycle_3", hashMapCycle3);
		hashMapFinal.put("Cycle_5", hashMapCycle5);
		hashMapFinal.put("Cycle_7", hashMapCycle7);
		hashMapFinal.put("Cycle_9", hashMapCycle9);
		hashMapFinal.put("Cycle_11", hashMapCycle11);
		hashMapFinal.put("Cycle_13", hashMapCycle13);
		hashMapFinal.put("Cycle_15", hashMapCycle15);
		hashMapFinal.put("Cycle_17", hashMapCycle17);

		ObjectMapper mapper = new ObjectMapper();
		
		String jsonvalue = mapper.writeValueAsString(hashMapFinal);
		System.out.println(jsonvalue);
		return jsonvalue;
	}

}
