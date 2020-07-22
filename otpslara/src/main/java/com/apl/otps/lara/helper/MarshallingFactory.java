package com.apl.otps.lara.helper;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.apl.otps.lara.xml.EssentialTerms;

/**
 * @author naresh.thota
 *
 */

public class MarshallingFactory {

	public static String getMarshallingObj(StringWriter stringWriter, EssentialTerms essentialTerms)
			throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(EssentialTerms.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshaller.marshal(essentialTerms, new File(stringWriter + ".xml"));
		marshaller.marshal(essentialTerms, stringWriter);
		return stringWriter.toString();
	}

}
