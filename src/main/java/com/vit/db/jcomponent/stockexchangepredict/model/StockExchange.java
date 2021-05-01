package com.vit.db.jcomponent.stockexchangepredict.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StockExchange")
public class StockExchange {
	String symbol;
	String series;
	float open;
	float high;
	float low;
	float close;
	float last;
	float prevclose;
	float tottrdoty;
	float tottrdval;
	String timestamp;
	float totaltrades;
	String isin;
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}
	/**
	 * @return the open
	 */
	public float getOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(float open) {
		this.open = open;
	}
	/**
	 * @return the high
	 */
	public float getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(float high) {
		this.high = high;
	}
	/**
	 * @return the low
	 */
	public float getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(float low) {
		this.low = low;
	}
	/**
	 * @return the close
	 */
	public float getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(float close) {
		this.close = close;
	}
	/**
	 * @return the last
	 */
	public float getLast() {
		return last;
	}
	/**
	 * @param last the last to set
	 */
	public void setLast(float last) {
		this.last = last;
	}
	/**
	 * @return the prevclose
	 */
	public float getPrevclose() {
		return prevclose;
	}
	/**
	 * @param prevclose the prevclose to set
	 */
	public void setPrevclose(float prevclose) {
		this.prevclose = prevclose;
	}
	/**
	 * @return the tottrdoty
	 */
	public float getTottrdoty() {
		return tottrdoty;
	}
	/**
	 * @param tottrdoty the tottrdoty to set
	 */
	public void setTottrdoty(float tottrdoty) {
		this.tottrdoty = tottrdoty;
	}
	/**
	 * @return the tottrdval
	 */
	public float getTottrdval() {
		return tottrdval;
	}
	/**
	 * @param tottrdval the tottrdval to set
	 */
	public void setTottrdval(float tottrdval) {
		this.tottrdval = tottrdval;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the totaltrades
	 */
	public float getTotaltrades() {
		return totaltrades;
	}
	/**
	 * @param totaltrades the totaltrades to set
	 */
	public void setTotaltrades(float totaltrades) {
		this.totaltrades = totaltrades;
	}
	/**
	 * @return the isin
	 */
	public String getIsin() {
		return isin;
	}
	/**
	 * @param isin the isin to set
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}
	/**
	 * @param symbol
	 * @param series
	 * @param open
	 * @param high
	 * @param low
	 * @param close
	 * @param last
	 * @param prevclose
	 * @param tottrdoty
	 * @param tottrdval
	 * @param timestamp
	 * @param totaltrades
	 * @param isin
	 */
	public StockExchange(String symbol, String series, float open, float high, float low, float close, float last,
			float prevclose, float tottrdoty, float tottrdval, String timestamp, float totaltrades, String isin) {
		super();
		this.symbol = symbol;
		this.series = series;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.last = last;
		this.prevclose = prevclose;
		this.tottrdoty = tottrdoty;
		this.tottrdval = tottrdval;
		this.timestamp = timestamp;
		this.totaltrades = totaltrades;
		this.isin = isin;
	}
	/**
	 * 
	 */
	public StockExchange() {
		super();
	}
	@Override
	public String toString() {
		return "StockExchange [symbol=" + symbol + ", series=" + series + ", open=" + open + ", high=" + high + ", low="
				+ low + ", close=" + close + ", last=" + last + ", prevclose=" + prevclose + ", tottrdoty=" + tottrdoty
				+ ", tottrdval=" + tottrdval + ", timestamp=" + timestamp + ", totaltrades=" + totaltrades + ", isin="
				+ isin + "]";
	}
	

}
