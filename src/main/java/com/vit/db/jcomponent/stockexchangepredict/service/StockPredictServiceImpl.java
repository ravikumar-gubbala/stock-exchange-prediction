package com.vit.db.jcomponent.stockexchangepredict.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.vit.db.jcomponent.stockexchangepredict.dao.StockPredictDao;
import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Component
public class StockPredictServiceImpl implements StockPredictService {

	public static Log log;

//	@Autowired
//	DomainRepository domainRepository;

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

		try (Scanner sc = new Scanner(content)) {

			Workbook workbook = new XSSFWorkbook(content);
			Sheet sheet = workbook.getSheet("Customers");
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
						stock.setSymbol(String.valueOf(currentCell.getNumericCellValue()));
					} else if (cellIndex == 1) {
						stock.setSeries(String.valueOf(currentCell.getNumericCellValue()));
					} else if (cellIndex == 2) {
						stock.setOpen(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 3) {
						stock.setHigh(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 4) {
						stock.setLow(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 5) {
						stock.setClose(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 6) {
						stock.setLast(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 7) {
						stock.setPrevclose(Float.valueOf((float) currentCell.getNumericCellValue()));
					} else if (cellIndex == 8) {
						stock.setTottrdoty(Long.valueOf((long) currentCell.getNumericCellValue()));
					} else if (cellIndex == 9) {
						stock.setTottrdval(Long.valueOf((long) currentCell.getNumericCellValue()));
					} else if (cellIndex == 10) {
						stock.setTimestamp(String.valueOf(currentCell.getNumericCellValue()));
					} else if (cellIndex == 11) {
						stock.setTotaltrades(Long.valueOf((long) currentCell.getNumericCellValue()));
					} else if (cellIndex == 12) {
						stock.setIsin(String.valueOf(currentCell.getNumericCellValue()));
					}
					cellIndex++;
				}
				lstStock.add(stock);
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
}
