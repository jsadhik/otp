package com.apl.otps.lara.helper;

import org.apache.commons.lang3.StringUtils;

import com.apl.otps.lara.xml.EssentialTermsHeader;

/**
 * @author naresh.thota
 *
 */
public class Validators {

	public static EssentialTermsHeader validateHeader(EssentialTermsHeader essesntialTerm) {
		if (StringUtils.isBlank(essesntialTerm.getUscHeaderCode())) {
			essesntialTerm.setValidFlag("X");
			essesntialTerm.setValidMsg("USC number cannot be empty");
		} else if (essesntialTerm.getUscHeaderCode().length() > 20) {
			essesntialTerm.setValidFlag("X");
			essesntialTerm.setValidMsg("USC number cannot be more than 20 characters");
		}
		if (essesntialTerm.getCreatedUser().length() > 30) {
			essesntialTerm.setValidFlag("N");
			essesntialTerm.setValidMsg("Creation user cannot be  more than 30 charecters");
		}
		if (essesntialTerm.getMqc().length() > 12) {
			essesntialTerm.setValidFlag("N");
			essesntialTerm.setValidMsg("MQC cannot be more than 12 digits");
		}
		return essesntialTerm;
	}

}
