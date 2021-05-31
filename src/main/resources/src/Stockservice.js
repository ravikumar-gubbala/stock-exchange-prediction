import http from "./http-common";

class Stockservice {
  getStockService() {
    return http.get(null);
    //return null;
  }

  searchStockService(name) {
    return http.get(`/StockExchangeData?name=${name}`);
  }

  loadMongoService(gdate) {
    return http.get(`/StockExchange/loadMongo?gDate=${gdate}`);
  }

  loadpredictionService(symbol, gdate) {
    return http.get(
      `/StockExchange/prediction?gDate=${gdate}&symbol=${symbol}`
    );
  }
}

export default new Stockservice();
