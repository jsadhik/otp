package com.apl.otps.lara.entities;

import java.util.Date;

/**
 * @author naresh.thota
 *
 */
public class BatchDetails {
	
	private long batchNo;
	private Date startDate;
	private String batchStatusMsg;
	private String batchStatusCode;
	private Date cutOffDate;
	private String batchMsg;
	private int batchRespCode;
	private String batchRespMsg;
	
	
	/**
	 * @return the batchNo
	 */
	public long getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(long batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the cutOffDate
	 */
	public Date getCutOffDate() {
		return cutOffDate;
	}
	/**
	 * @param cutOffDate the cutOffDate to set
	 */
	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
	}
	/**
	 * @return the batchMsg
	 */
	public String getBatchMsg() {
		return batchMsg;
	}
	/**
	 * @param batchMsg the batchMsg to set
	 */
	public void setBatchMsg(String batchMsg) {
		this.batchMsg = batchMsg;
	}
	/**
	 * @return the batchStatusMsg
	 */
	public String getBatchStatusMsg() {
		return batchStatusMsg;
	}
	/**
	 * @param batchStatusMsg the batchStatusMsg to set
	 */
	public void setBatchStatusMsg(String batchStatusMsg) {
		this.batchStatusMsg = batchStatusMsg;
	}
	/**
	 * @return the batchStatusCode
	 */
	public String getBatchStatusCode() {
		return batchStatusCode;
	}
	/**
	 * @param batchStatusCode the batchStatusCode to set
	 */
	public void setBatchStatusCode(String batchStatusCode) {
		this.batchStatusCode = batchStatusCode;
	}
	
	/**
	 * @return the batchRespCode
	 */
	public int getBatchRespCode() {
		return batchRespCode;
	}
	/**
	 * @param batchRespCode the batchRespCode to set
	 */
	public void setBatchRespCode(int batchRespCode) {
		this.batchRespCode = batchRespCode;
	}
	/**
	 * @return the batchRespMsg
	 */
	public String getBatchRespMsg() {
		return batchRespMsg;
	}
	/**
	 * @param batchRespMsg the batchRespMsg to set
	 */
	public void setBatchRespMsg(String batchRespMsg) {
		this.batchRespMsg = batchRespMsg;
	}
	
	

}
