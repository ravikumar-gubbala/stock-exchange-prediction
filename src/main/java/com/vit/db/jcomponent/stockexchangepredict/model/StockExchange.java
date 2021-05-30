package com.vit.db.jcomponent.stockexchangepredict.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "C1")
public class StockExchange {
	@Id
	String SYMBOL;
	String SERIES;
	float OPEN;
	float HIGH;
	float LOW;
	float CLOSE;
	float LAST;
	float PREVCLOSE;
	float TOTTRDQTY;
	float TOTTRDVAL;
	String TIMESTAMP;
	float TOTALTRADES;
	String ISIN;
	/**
	 * @return the sYMBOL
	 */
	public String getSYMBOL() {
		return SYMBOL;
	}
	/**
	 * @param sYMBOL the sYMBOL to set
	 */
	public void setSYMBOL(String sYMBOL) {
		SYMBOL = sYMBOL;
	}
	/**
	 * @return the sERIES
	 */
	public String getSERIES() {
		return SERIES;
	}
	/**
	 * @param sERIES the sERIES to set
	 */
	public void setSERIES(String sERIES) {
		SERIES = sERIES;
	}
	/**
	 * @return the oPEN
	 */
	public float getOPEN() {
		return OPEN;
	}
	/**
	 * @param oPEN the oPEN to set
	 */
	public void setOPEN(float oPEN) {
		OPEN = oPEN;
	}
	/**
	 * @return the hIGH
	 */
	public float getHIGH() {
		return HIGH;
	}
	/**
	 * @param hIGH the hIGH to set
	 */
	public void setHIGH(float hIGH) {
		HIGH = hIGH;
	}
	/**
	 * @return the lOW
	 */
	public float getLOW() {
		return LOW;
	}
	/**
	 * @param lOW the lOW to set
	 */
	public void setLOW(float lOW) {
		LOW = lOW;
	}
	/**
	 * @return the cLOSE
	 */
	public float getCLOSE() {
		return CLOSE;
	}
	/**
	 * @param cLOSE the cLOSE to set
	 */
	public void setCLOSE(float cLOSE) {
		CLOSE = cLOSE;
	}
	/**
	 * @return the lAST
	 */
	public float getLAST() {
		return LAST;
	}
	/**
	 * @param lAST the lAST to set
	 */
	public void setLAST(float lAST) {
		LAST = lAST;
	}
	/**
	 * @return the pREVCLOSE
	 */
	public float getPREVCLOSE() {
		return PREVCLOSE;
	}
	/**
	 * @param pREVCLOSE the pREVCLOSE to set
	 */
	public void setPREVCLOSE(float pREVCLOSE) {
		PREVCLOSE = pREVCLOSE;
	}
	/**
	 * @return the tOTTRDQTY
	 */
	public float getTOTTRDQTY() {
		return TOTTRDQTY;
	}
	/**
	 * @param tOTTRDQTY the tOTTRDQTY to set
	 */
	public void setTOTTRDQTY(float tOTTRDQTY) {
		TOTTRDQTY = tOTTRDQTY;
	}
	/**
	 * @return the tOTTRDVAL
	 */
	public float getTOTTRDVAL() {
		return TOTTRDVAL;
	}
	/**
	 * @param tOTTRDVAL the tOTTRDVAL to set
	 */
	public void setTOTTRDVAL(float tOTTRDVAL) {
		TOTTRDVAL = tOTTRDVAL;
	}
	/**
	 * @return the tIMESTAMP
	 */
	public String getTIMESTAMP() {
		return TIMESTAMP;
	}
	/**
	 * @param tIMESTAMP the tIMESTAMP to set
	 */
	public void setTIMESTAMP(String tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}
	/**
	 * @return the tOTALTRADES
	 */
	public float getTOTALTRADES() {
		return TOTALTRADES;
	}
	/**
	 * @param tOTALTRADES the tOTALTRADES to set
	 */
	public void setTOTALTRADES(float tOTALTRADES) {
		TOTALTRADES = tOTALTRADES;
	}
	/**
	 * @return the iSIN
	 */
	public String getISIN() {
		return ISIN;
	}
	/**
	 * @param iSIN the iSIN to set
	 */
	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}
	/**
	 * 
	 */
	public StockExchange() {
		super();
	}
	@Override
	public String toString() {
		return "StockExchange [SYMBOL=" + SYMBOL + ", SERIES=" + SERIES + ", OPEN=" + OPEN + ", HIGH=" + HIGH + ", LOW="
				+ LOW + ", CLOSE=" + CLOSE + ", LAST=" + LAST + ", PREVCLOSE=" + PREVCLOSE + ", TOTTRDQTY=" + TOTTRDQTY
				+ ", TOTTRDVAL=" + TOTTRDVAL + ", TIMESTAMP=" + TIMESTAMP + ", TOTALTRADES=" + TOTALTRADES + ", ISIN="
				+ ISIN + "]";
	}
	
	
}
