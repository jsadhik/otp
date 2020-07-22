package com.apl.otps.lara.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author naresh.thota
 *
 */
@XmlRootElement(name = "ESSENTIAL_TERMS_COMMODITY")
public class Commodity {
	
	private String amdtUID;
	private String appendixCode;
	private String bulletCode;
	private String longDesc;
	
	/**
	 * @return the amdtUID
	 */
	@XmlElement(name="AMDT_UID")
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
	 * @return the appendixCode
	 */
	@XmlElement(name="APPENDIX_CODE")
	public String getAppendixCode() {
		return appendixCode;
	}
	/**
	 * @param appendixCode the appendixCode to set
	 */
	public void setAppendixCode(String appendixCode) {
		this.appendixCode = appendixCode;
	}
	/**
	 * @return the bulletCode
	 */
	@XmlElement(name="BULLET_CODE")
	public String getBulletCode() {
		return bulletCode;
	}
	/**
	 * @param bulletCode the bulletCode to set
	 */
	public void setBulletCode(String bulletCode) {
		this.bulletCode = bulletCode;
	}
	/**
	 * @return the longDesc
	 */
	@XmlElement(name="LONG_DESCRIPTION")
	public String getLongDesc() {
		return longDesc;
	}
	/**
	 * @param longDesc the longDesc to set
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	
	
	
	

}
