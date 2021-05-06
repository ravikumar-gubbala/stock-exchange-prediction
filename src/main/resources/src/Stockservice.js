import http from './http-common';

class Stockservice{
    getStockService(){
        return http.get("/StockExchange");
    }

    searchStockService(name){
        return http.get(`/StockExchangeData?name=${name}`);
    }

    
}

export default new Stockservice();