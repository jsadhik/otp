package com.apl.otps.lara.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESSENTIAL_TERMS_SCOPE")
public class Scopes {
	
	private String amdtUID;
	private String countryName;
	private String identifier;
	
	
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
	 * @return the countryName
	 */
	@XmlElement(name="COUNTRY_NAME")
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the identifier
	 */
	@XmlElement(name="IDENTIFIER")
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
