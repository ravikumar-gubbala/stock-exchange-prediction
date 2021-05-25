package com.vit.db.jcomponent.stockexchangepredict.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonArray;
import com.vit.db.jcomponent.stockexchangepredict.dao.StockPredictDao;
import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Component
public class StockPredictServiceImpl implements StockPredictService {

	public static Log log;

//	@Autowired
//	DomainRepository domainRepository;
	
	@Autowired
	load2Mongo loadMongo;

	@Autowired
	StockPredictDao stockPredictDao;

	public ArrayList<StockExchange> postFileSectors(MultipartFile imageFile) throws IOException {
		String content = null;
		ArrayList<StockExchange> lstStock = new ArrayList<StockExchange>();

		try {
			content = new String(imageFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> Reader = new ArrayList<String>();
		String filePath = "/home/soumadeepdhar/Applications/VIT Vellore/Masters Curricullum Semester II/No SQL/Stocks/file.xlsx";
		try {

			FileInputStream excelFile = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheet("file");
			Iterator rows = sheet.iterator();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = (Row) rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator cellsInRow = currentRow.iterator();
				int cellIndex = 0;
				StockExchange stock = new StockExchange();
				while (cellsInRow.hasNext()) {
					Cell currentCell = (Cell) cellsInRow.next();

					if (cellIndex == 0) {
						stock.setSymbol(String.valueOf(currentCell));
					} else if (cellIndex == 1) {
						stock.setSeries(String.valueOf(currentCell));
					} else if (cellIndex == 2) {
						stock.setOpen(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 3) {
						stock.setHigh(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 4) {
						stock.setLow(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 5) {
						stock.setClose(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 6) {
						stock.setLast(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 7) {
						stock.setPrevclose(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 8) {
						stock.setTottrdoty(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 9) {
						stock.setTottrdval(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 10) {
						stock.setTimestamp(String.valueOf(currentCell.toString()));
					} else if (cellIndex == 11) {
						stock.setTotaltrades(Float.valueOf(currentCell.toString()));
					} else if (cellIndex == 12) {
						stock.setIsin(String.valueOf(currentCell.toString()));
					}
					cellIndex++;
				}
				lstStock.add(stock);
				stockPredictDao.setStockData(stock);
			}
			// Close WorkBook
			workbook.close();
			
			return lstStock;

		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}

	}

	public List<StockExchange> getUnstructuredData() {
		return stockPredictDao.getUnstructuredData();
	}

	@Override
	public List<StockExchange> getStockData(String name) {

		return stockPredictDao.getStockData(name);
	}
	
	@Override
	public void loadMongo() throws Exception {
		loadMongo.loadMain(null);
	}
}
