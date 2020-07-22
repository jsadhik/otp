package com.apl.otps.lara.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author naresh.thota
 *
 */
@XmlRootElement(name = "ESSENTIAL_TERMS_HEADER")
public class EssentialTermsHeader {

	private String amdtUID;
	private String uscHeaderCode;
	private String uscDateFrom;
	private String uscDateTo;
	private String amdtDateFrom;
	private int amdtNumber;
	private String createdDate;
	private String createdUser;
	private String lastModifiedDate;
	private String lastModifiedUser;
	private String mqc;
	private String validFlag;
	private String validMsg;
	

	/**
	 * @return the amdtUID
	 */
	@XmlElement(name = "AMDT_UID")
	public String getAmdtUID() {
		return amdtUID;
	}

	/**
	 * @param amdtUID the amdtUID to set
	 */
	public void setAmdtUID(String amdtUID) {
		this.amdtUID = amdtUID;
	}

	/**
	 * @return the uscHeaderCode
	 */
	@XmlElement(name = "USC_HEADER_CODE")
	public String getUscHeaderCode() {
		return uscHeaderCode;
	}

	/**
	 * @param uscHeaderCode the uscHeaderCode to set
	 */
	public void setUscHeaderCode(String uscHeaderCode) {
		this.uscHeaderCode = uscHeaderCode;
	}

	/**
	 * @return the uscDateFrom
	 */
	@XmlElement(name = "USC_DATE_FROM")
	public String getUscDateFrom() {
		return uscDateFrom;
	}

	/**
	 * @param uscDateFrom the uscDateFrom to set
	 */
	public void setUscDateFrom(String uscDateFrom) {
		this.uscDateFrom = uscDateFrom;
	}

	/**
	 * @return the uscDateTo
	 */
	@XmlElement(name = "USC_DATE_TO")
	public String getUscDateTo() {
		return uscDateTo;
	}

	/**
	 * @param uscDateTo the uscDateTo to set
	 */
	public void setUscDateTo(String uscDateTo) {
		this.uscDateTo = uscDateTo;
	}

	/**
	 * @return the amdtDateFrom
	 */
	@XmlElement(name = "AMDT_DATE_FROM")
	public String getAmdtDateFrom() {
		return amdtDateFrom;
	}

	/**
	 * @param amdtDateFrom the amdtDateFrom to set
	 */
	public void setAmdtDateFrom(String amdtDateFrom) {
		this.amdtDateFrom = amdtDateFrom;
	}

	/**
	 * @return the amdtNumber
	 */
	@XmlElement(name = "AMDT_NUMBER")
	public int getAmdtNumber() {
		return amdtNumber;
	}

	/**
	 * @param amdtNumber the amdtNumber to set
	 */
	public void setAmdtNumber(int amdtNumber) {
		this.amdtNumber = amdtNumber;
	}

	/**
	 * @return the createdDate
	 */
	@XmlElement(name = "CREATED_DATE")
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdUser
	 */
	@XmlElement(name = "CREATION_USER")
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the lastModifiedDate
	 */
	@XmlElement(name = "LAST_MODIF_DATE")
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the lastModifiedUser
	 */
	@XmlElement(name = "LAST_MODIF_USER")
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	/**
	 * @param lastModifiedUser the lastModifiedUser to set
	 */
	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	/**
	 * @return the mqc
	 */
	@XmlElement(name = "MQC")
	public String getMqc() {
		return mqc;
	}

	/**
	 * @param mqc the mqc to set
	 */
	public void setMqc(String mqc) {
		this.mqc = mqc;
	}

	/**
	 * @return the validFlag
	 */
	@XmlElement(name = "VALID_FLG")
	public String getValidFlag() {
		return validFlag;
	}

	/**
	 * @param validFlag the validFlag to set
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	/**
	 * @return the validMsg
	 */
	@XmlElement(name = "VALID_MSG")
	public String getValidMsg() {
		return validMsg;
	}

	/**
	 * @param validMsg the validMsg to set
	 */
	public void setValidMsg(String validMsg) {
		this.validMsg = validMsg;
	}

}
