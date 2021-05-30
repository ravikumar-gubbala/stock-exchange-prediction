import http from "./http-common";

class Stockservice {
  getStockService() {
    return http.get("/StockExchange");
  }

  searchStockService(name) {
    return http.get(`/StockExchangeData?name=${name}`);
  }

  loadMongoService(gdate) {
    return http.get(`/StockExchange/loadMongo?gDate=${gdate}`);
  }
}

export default new Stockservice();
