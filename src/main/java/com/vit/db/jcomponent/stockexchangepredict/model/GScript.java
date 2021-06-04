package com.vit.db.jcomponent.stockexchangepredict.model;

import java.util.Date;

public class GScript {
	String symbol;
	Double open;
	Double high;
	Double low;
	Double close;
	Double last;
	Double prevclose;
	Integer tottrdqty;
	Double tottrdval;
	Date timestamp;
	Integer tottrades;
	Double LogVal;
	Double StdDevVal;

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
	 * @return the open
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * @return the last
	 */
	public Double getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(Double last) {
		this.last = last;
	}

	/**
	 * @return the prevclose
	 */
	public Double getPrevclose() {
		return prevclose;
	}

	/**
	 * @param prevclose the prevclose to set
	 */
	public void setPrevclose(Double prevclose) {
		this.prevclose = prevclose;
	}

	/**
	 * @return the tottrdqty
	 */
	public Integer getTottrdqty() {
		return tottrdqty;
	}

	/**
	 * @param tottrdqty the tottrdqty to set
	 */
	public void setTottrdqty(Integer tottrdqty) {
		this.tottrdqty = tottrdqty;
	}

	/**
	 * @return the tottrdval
	 */
	public Double getTottrdval() {
		return tottrdval;
	}

	/**
	 * @param tottrdval the tottrdval to set
	 */
	public void setTottrdval(Double tottrdval) {
		this.tottrdval = tottrdval;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the tottrades
	 */
	public Integer getTottrades() {
		return tottrades;
	}

	/**
	 * @param tottrades the tottrades to set
	 */
	public void setTottrades(Integer tottrades) {
		this.tottrades = tottrades;
	}

	/**
	 * @return the logVal
	 */
	public Double getLogVal() {
		return LogVal;
	}

	/**
	 * @param logVal the logVal to set
	 */
	public void setLogVal(Double logVal) {
		LogVal = logVal;
	}

	/**
	 * @return the stdDevVal
	 */
	public Double getStdDevVal() {
		return StdDevVal;
	}

	/**
	 * @param stdDevVal the stdDevVal to set
	 */
	public void setStdDevVal(Double stdDevVal) {
		StdDevVal = stdDevVal;
	}

	/**
	 * 
	 */
	public GScript() {
		super();
	}

	public GScript(String symbol, Double open, Double high, Double low, Double close, Double last, Double prevclose,
			Integer tottrdqty, Double tottrdval, Date timestamp, Integer tottrades, Double LogVal, Double StdDevVal) {
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.last = last;
		this.prevclose = prevclose;
		this.tottrdqty = tottrdqty;
		this.tottrdval = tottrdval;
		this.timestamp = timestamp;
		this.tottrades = tottrades;
		this.LogVal = LogVal;
		this.StdDevVal = StdDevVal;
	}

	@Override
	public String toString() {
		return "GScript [symbol=" + symbol + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", last=" + last + ", prevclose=" + prevclose + ", tottrdqty=" + tottrdqty + ", tottrdval="
				+ tottrdval + ", timestamp=" + timestamp + ", tottrades=" + tottrades + ", LogVal=" + LogVal
				+ ", StdDevVal=" + StdDevVal + "]";
	}

}
