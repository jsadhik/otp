package com.apl.otps.lara.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * @author naresh.thota
 *
 */
@XmlRootElement(name = "ESSENTIAL_TERMS")
public class EssentialTerms {
	
	List<EssentialTermsHeader> essList;
	List<Commodity> cmdityList;
	List<Scopes> scopesList;

	/**
	 * @return the essList
	 */
	@XmlElement(name = "ESSENTIAL_TERMS_HEADER")
	public List<EssentialTermsHeader> getEssList() {
		return essList;
	}

	/**
	 * @param essList the essList to set
	 */
	public void setEssList(List<EssentialTermsHeader> essList) {
		this.essList = essList;
	}

	/**
	 * @return the cmdityList
	 */
	@XmlElement(name = "ESSENTIAL_TERMS_COMMODITY")
	public List<Commodity> getCmdityList() {
		return cmdityList;
	}

	/**
	 * @param cmdityList the cmdityList to set
	 */
	public void setCmdityList(List<Commodity> cmdityList) {
		this.cmdityList = cmdityList;
	}
	
	

	/**
	 * @return the scopesList
	 */
	@XmlElement(name = "ESSENTIAL_TERMS_SCOPE")
	public List<Scopes> getScopesList() {
		return scopesList;
	}

	/**
	 * @param scopesList the scopesList to set
	 */
	public void setScopesList(List<Scopes> scopesList) {
		this.scopesList = scopesList;
	}

	public static void generateXml() throws JAXBException {
		EssentialTerms essms = new EssentialTerms();

		EssentialTermsHeader essentialTermsHeader1 = new EssentialTermsHeader();
		essentialTermsHeader1.setAmdtUID("12345");
		EssentialTermsHeader essentialTermsHeader2 = new EssentialTermsHeader();
		essentialTermsHeader2.setAmdtUID("12345");

		List<EssentialTermsHeader> list = new ArrayList<EssentialTermsHeader>();
		list.add(essentialTermsHeader1);
		list.add(essentialTermsHeader2);
		essms.setEssList(list);
		
		Commodity cmdty=new Commodity();
		cmdty.setAmdtUID("12345");
		cmdty.setAppendixCode("Electronics");
		cmdty.setBulletCode("A");
		cmdty.setLongDesc("Electronics goods");
		List<Commodity> cmdityList = new ArrayList<Commodity>();
		cmdityList.add(cmdty);
		essms.setCmdityList(cmdityList);
		
		Scopes scope=new Scopes();
		scope.setAmdtUID("12345");
		scope.setCountryName("CHINA");
		scope.setIdentifier("DEST");
		List<Scopes> scopeList=new ArrayList<Scopes>();
		scopeList.add(scope);
		essms.setScopesList(scopeList);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(EssentialTerms.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		// marshaller.marshal(essentialTermsHeader, new File("product.xml"));
		marshaller.marshal(essms, System.out);
	}

}
