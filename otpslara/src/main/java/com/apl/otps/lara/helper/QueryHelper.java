package com.apl.otps.lara.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apl.otps.lara.constants.ConstantQueries;
import com.apl.otps.lara.entities.ParamDetails;
import com.apl.otps.lara.service.LaraDBServiceImpl;
import com.apl.otps.lara.xml.EssentialTerms;

/**
 * @author naresh.thota
 *
 */

@Component
public class QueryHelper {
	Logger logger = Logger.getLogger(QueryHelper.class);

	@Autowired
	LaraDBServiceImpl laraDBServiceImpl;

	public EssentialTerms getEssentialTermsForManualProcess(EssentialTerms essentialTerms, ParamDetails paramDetails) {
		try {
			if (StringUtils.equals(paramDetails.getParamIdentifier(), "O")) {
				essentialTerms = laraDBServiceImpl.getHeaderData(ConstantQueries.HEADER_O_QUERY);
			} else if (StringUtils.equals(paramDetails.getParamIdentifier(), "OA")) {
				essentialTerms = laraDBServiceImpl.getHeaderData(ConstantQueries.HEADER_OA_QUERY);
			} else {
				essentialTerms = laraDBServiceImpl.getHeaderData(ConstantQueries.HEADER_DR_QUERY);
			}

		} catch (Exception e) {
			logger.error("error in getEssentialTermsForManualProcess {} "+e.getMessage());
		}
		return essentialTerms;
	}

}
