package com.apl.otps.lara.service;

import java.io.StringWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apl.otps.lara.batch.BatchProcessor;
import com.apl.otps.lara.constants.ConstantQueries;
import com.apl.otps.lara.entities.BatchDetails;
import com.apl.otps.lara.entities.ParamDetails;
import com.apl.otps.lara.exception.BatchExceptionHandler;
import com.apl.otps.lara.helper.MarshallingFactory;
import com.apl.otps.lara.helper.QueryHelper;
import com.apl.otps.lara.xml.EssentialTerms;
import com.apl.otps.lara.xml.EssentialTermsHeader;

/**
 * @author naresh.thota
 *
 */
@Component
public class BatchServiceImpl {
	Logger logger = Logger.getLogger(BatchProcessor.class);

	@Autowired
	OtpsDBServiceImpl otpsDBServiceImpls;

	@Autowired
	LaraDBServiceImpl laraDBServiceImpl;

	public BatchDetails createBatch(BatchDetails batchDetails) throws BatchExceptionHandler {
		try {
			otpsDBServiceImpls.createBatch(batchDetails);
		} catch (Exception e) {
			logger.error("error in createBatch method {} "+e.getMessage());
			throw new BatchExceptionHandler("Getting exception while connecting to Database ");
		}
		return batchDetails;
	}

	public void startRegularProcess(BatchDetails batchDetails) {
		EssentialTerms essentialTerms;
		String header = null;
		String commodity = null;
		String scope = null;
		List<String> amdUidslist = new ArrayList<String>();
		Instant batchStart = Instant.now();
		logger.info(" ============== Regular Batch Process Started =============" + batchStart);
		try {
			essentialTerms = laraDBServiceImpl.getHeaderData(ConstantQueries.HEADER_R_QUERY);
			header = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

			for (EssentialTermsHeader amdUid : essentialTerms.getEssList()) {
				amdUidslist.add(amdUid.getAmdtUID());
			}

			essentialTerms = laraDBServiceImpl.getCommodityData(amdUidslist);
			commodity = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

			essentialTerms = laraDBServiceImpl.getScopeData(amdUidslist);
			scope = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

			otpsDBServiceImpls.executeLaraStoredProcedure(batchDetails, header, commodity, scope);
			Instant batchEnd = Instant.now();
			logger.info(" ============== Regular Batch Process End =============" + batchEnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in startRegularProcess method {} " + e.getMessage());
		}

	}

	public void startManualProcess(ParamDetails paramDetails, BatchDetails batchDetails)
			throws BatchExceptionHandler, JAXBException {
		logger.info(" ============== inside startManual Process in bacthserviceimpl =============");
		EssentialTerms essentialTerms = null;
		String header = null;
		String commodity = null;
		String scope = null;
		List<String> amdUidslist = new ArrayList<String>();

		try {
			essentialTerms = new QueryHelper().getEssentialTermsForManualProcess(essentialTerms, paramDetails);
			if (essentialTerms != null) {
				header = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

				for (EssentialTermsHeader amdUid : essentialTerms.getEssList()) {
					amdUidslist.add(amdUid.getAmdtUID());
				}
				essentialTerms = laraDBServiceImpl.getCommodityData(amdUidslist);
				commodity = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

				essentialTerms = laraDBServiceImpl.getScopeData(amdUidslist);
				scope = MarshallingFactory.getMarshallingObj(new StringWriter(), essentialTerms);

				otpsDBServiceImpls.executeLaraStoredProcedure(batchDetails, header, commodity, scope);
			}

		} catch (Exception e) {
			logger.error("error occured in startManualProcess method {} "+ e.getMessage());
		}
		// otpsDBServiceImpls.updateFlag();
	}
}
