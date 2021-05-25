package com.vit.db.jcomponent.stockexchangepredict.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;
import com.vit.db.jcomponent.stockexchangepredict.service.StockPredictService;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@Service
public class StockPredictController {

	@Autowired
	StockPredictService stockPredictService;	
		
	@PostMapping("/StockExchange/upload") // received file by database (mysql) for XML code
	public ResponseEntity<ArrayList<StockExchange> > uploadFile(@RequestParam("file") MultipartFile file) {
		@SuppressWarnings("unused")
		String message = null;
		try {
			ArrayList<StockExchange> result = stockPredictService.postFileSectors(file);
			message = "Uploaded stock file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

	@GetMapping("/StockExchange") // get data from Mongo DB
	List<StockExchange> getUnstructuredData() {
		return stockPredictService.getUnstructuredData();
	}
	
	@GetMapping("/StockExchangeData") // get single data from Mongo DB
	List<StockExchange> getStockData(@RequestParam String name) {
		return stockPredictService.getStockData(name);
	}
	
	@GetMapping("/StockExchange/loadMongo")
	String Test() throws Exception {
		stockPredictService.loadMongo();
		return "Service is Working Fine";
	}
	
}
