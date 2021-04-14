package com.vit.db.jcomponent.stockexchangepredict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;
import com.vit.db.jcomponent.stockexchangepredict.service.StockPredictService;

@RestController
@Service
public class StockPredictController {

	@Autowired
	StockPredictService stockPredictService;	
		
	@PostMapping("/StockExchange/upload") // received file by database (mysql) for XML code
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		@SuppressWarnings("unused")
		String message = null;
		try {
			stockPredictService.postFileSectors(file);
			message = "Uploaded stock file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new String("Uploaded Successfully"));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String("Operation Failed"));
		}
	}

	@GetMapping("/StockExchange/getUnstructeredData") // get data from Mongo DB
	List<StockExchange> getUnstructuredData() {
		return stockPredictService.getUnstructuredData();
	}
	
	@GetMapping("/StockExchange/test")
	String Test() {
		return "Test";
	}
	
}
