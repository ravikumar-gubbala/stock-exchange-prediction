package com.vit.db.jcomponent.stockexchangepredict.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vit.db.jcomponent.stockexchangepredict.model.StockExchange;

@Service
public interface StockPredictService {
	
	public ArrayList<StockExchange> postFileSectors(MultipartFile imageFile) throws FileNotFoundException, IOException;
	
	public List<StockExchange> getUnstructuredData();

	public List<StockExchange> getStockData(String name);

	public String loadMongo(String gDate) throws Exception;

	public String dataPrediction(String gDate, String symbol) throws JsonProcessingException;
}
