package com.vit.db.jcomponent.stockexchangepredict.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class load2Mongo {

	public static void downloadFile(URL url, String outputFileName) throws IOException {
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("Referer",
				"https://www1.nseindia.com/products/content/equities/equities/archieve_eq.htm");
		connection.connect();
		try (InputStream in = connection.getInputStream();
				ReadableByteChannel rbc = Channels.newChannel(in);
				FileOutputStream fos = new FileOutputStream(outputFileName)) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
	}

	public static String theMonth(int month) {
		String[] monthNames = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
		return monthNames[month];
	}

	public String loadMain(String gDate) throws Exception {

		Scanner scanner = new Scanner(System.in);
		String gDatabaseName = "StockNSE";
		String gCollectionName = "C1";
		System.out.printf("Enter Date for Prediction [DD/MM/YYYY]: ");
		String sDate1 = "31/12/1998";
		Date gdatePrediction = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		try {
			String gDateStr = gDate;
			gdatePrediction = new SimpleDateFormat("dd/MM/yyyy").parse(gDateStr);
		} catch (Exception ex) {
			System.out.println("Error: Invalide Date!!!");
			System.out.println("Exiting...");
			ex.printStackTrace();
			System.exit(0);
		}

		System.out.println("Database Name: " + gDatabaseName);
		System.out.println("Collection Name: " + gCollectionName);
		System.out.println("Prediction Date: " + gdatePrediction);

		try {
			MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("empdb");
			MongoCollection<Document> collection = database.getCollection("employee");
			Document myDoc = collection.find().first();
			System.out.println(myDoc.toJson());
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
		while (g10Days < 10) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			gDay = cal.get(Calendar.DAY_OF_MONTH);
			gMonth = cal.get(Calendar.MONTH);
			gYear = cal.get(Calendar.YEAR);
			gMonthStr = theMonth(gMonth);
			if (gDay.toString().length() == 1) {
				gUrlStr5 = "0";
			} else {
				gUrlStr5 = "";
			}
			gUrlStr2 = gYear.toString() + "/" + gMonthStr + "/" + "cm" + gUrlStr5 + gDay.toString() + gMonthStr
					+ gYear.toString() + gUrlStr3;
			gUrlStr4 = gUrlStr1 + gUrlStr2;
			gUrlStr6 = "cm" + gUrlStr5 + gDay.toString() + gMonthStr + gYear.toString() + gUrlStr3;

			boolean saturday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
			boolean sunday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
			if (saturday == true || sunday == true) {
				continue;
			}
			System.out.println(gUrlStr4);
			System.out.println(gUrlStr6);
			URL myURL1 = new URL(gUrlStr4);
			// System.out.println("Press Enter Key...to Proceed.... ");
			// String gTMPName = scanner.nextLine();
			try {
				System.out.println("Downloading file: " + gUrlStr4);
				downloadFile(myURL1, gUrlStr6);
				DldFiles.add(gUrlStr6);
			} catch (Exception ex) {
				System.out.println("Error: File Not Found!!!");
				ex.printStackTrace();
				continue;
			}
			g10Days++;

		}
		g10Days = 0;
		while (g10Days < 10) {
		//	System.out.println("Press Enter Key...to Proceed.... ");
		//	String gTMPName = scanner.nextLine();
			String destDirectory = currentDirectory;
			String zipFilePath = currentDirectory + "/" + DldFiles.get(g10Days);
			System.out.println("Unzipping file: " + zipFilePath);
			UnzipUtility unzipper = new UnzipUtility();
			try {
				unzipper.unzip(zipFilePath, destDirectory);
			} catch (Exception ex) {
				System.out.printf("Error: Unzip Error!!!");
				ex.printStackTrace();
			}
			g10Days++;
		}

		/**** Upload2DB ****/

		g10Days = 0;
		while (g10Days < 10) {
			StringBuffer sb = new StringBuffer(DldFiles.get(g10Days));
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			File inpFile = new File(currentDirectory + "/" + sb);
			// System.out.println("Uploading File Press Enter Key...to Proceed....
			// "+inpFile.toString());
			// String gTMPName = scanner.nextLine();
			CSVParser parser = null;
			try {
				parser = CSVParser.parse(inpFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
				List<CSVRecord> listCsvRecord = new ArrayList<>();
				listCsvRecord.addAll(parser.getRecords());
				List Scripts = new ArrayList();
				Map<String, Object> Dmap = new HashMap<String, Object>();
				MongoClient mongoC = new MongoClient("localhost", 27017);
				DB db = mongoC.getDB(gDatabaseName);
				List<String> listofDB = mongoC.getDatabaseNames();
				for (String dbName : listofDB) {
					// System.out.println(dbName);
				}

				DBCollection clx = db.getCollection(gCollectionName);
				if(g10Days==0) { clx.drop(); }
				SimpleDateFormat Dformat = new SimpleDateFormat("dd-MMM-yyyy");
				Date Tdate;
				Integer HeaderFlag = 1;
				Integer FirstRecordFlag = 1;
				Float PrevCloseVal = Float.parseFloat("1.0");
				Float CurCloseVal;
				Float ResCloseVal;
				Double LogCloseVal;
				List<Double> LogList = new ArrayList<Double>();
				for (CSVRecord record : listCsvRecord) {

					if (HeaderFlag == 1) {
						HeaderFlag = 2;
						continue;
					}
					Dmap.put("SYMBOL", record.get(0));
					Dmap.put("SERIES", record.get(1));
					Dmap.put("OPEN", Float.parseFloat(record.get(2)));
					Dmap.put("HIGH", Float.parseFloat(record.get(3)));
					Dmap.put("LOW", Float.parseFloat(record.get(4)));
					Dmap.put("CLOSE", Float.parseFloat(record.get(5)));
					Dmap.put("LAST", Float.parseFloat(record.get(6)));
					Dmap.put("PREVCLOSE", Float.parseFloat(record.get(7)));
					Dmap.put("TOTTRDQTY", Integer.parseInt(record.get(8)));
					Dmap.put("TOTTRDVAL", Float.parseFloat(record.get(9)));
					Tdate = Dformat.parse(record.get(10));
					Dmap.put("TIMESTAMP", Tdate);
					Dmap.put("TOTALTRADES", Integer.parseInt(record.get(11)));
					Dmap.put("ISIN", record.get(12));

					if (FirstRecordFlag == 1) {
						Dmap.put("LOGCLOSE", Math.log(Float.parseFloat(record.get(5))));
						PrevCloseVal = Float.parseFloat(record.get(5));
						HeaderFlag = 2;
					}
					if (FirstRecordFlag == 2) {
						CurCloseVal = Float.parseFloat(record.get(5));
						ResCloseVal = CurCloseVal / PrevCloseVal;
						LogCloseVal = Math.log(ResCloseVal);
						LogList.add(LogCloseVal);
						Dmap.put("LOGCLOSE", LogCloseVal);
					}

					clx.insert(new BasicDBObject(Dmap));
				}
			} catch (Exception ex) {
				System.out.printf("Error: Unable to upload to MongoDB !!!");
				ex.printStackTrace();
				System.exit(0);
			}
			g10Days++;
		}
		return "Upload to MongoDB Success, End of Program!!!";
	}
}
