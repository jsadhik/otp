package com.apl.otps.lara.batch;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.apl.otps.lara.entities.BatchDetails;
import com.apl.otps.lara.entities.ParamDetails;
import com.apl.otps.lara.exception.BatchExceptionHandler;
import com.apl.otps.lara.service.BatchServiceImpl;
import com.apl.otps.lara.service.OtpsDBServiceImpl;

/**
 * @author naresh.thota
 *
 */
public class BatchProcessor {
	Logger logger = LogManager.getLogger(BatchProcessor.class);

	@Autowired
	OtpsDBServiceImpl otpsServiceImpl;

	@Autowired
	BatchServiceImpl batchServiceImpl;

	public void startBatchProcess() throws JAXBException {
		Instant batchStart = Instant.now();
		logger.info(" ============== Batch Started =============" + batchStart);
		List<String> paramIdentifiers = Arrays.asList("O", "OA", "DR");
		BatchDetails batchDetails = getBatchDetail();
		try {
			ParamDetails paramDetails = otpsServiceImpl.getBatchFlag();
			if (paramDetails != null) {
				if (paramDetails.getFlag().equals("Y")
						&& paramIdentifiers.contains(paramDetails.getParamIdentifier())) {
					batchServiceImpl.createBatch(batchDetails);
					batchServiceImpl.startManualProcess(paramDetails, batchDetails);
				} else {
					logger.info("there is no manual process scheduled {} " +batchStart);
				}
			}
		} catch (BatchExceptionHandler e) {
			logger.error("Exception in startBatchProcess method {} "+e.getMessage());
		}
	}

	public void regularBatchProcess() throws InterruptedException {
		logger.info(" ============== Regular Batch Process Started =============");
		Instant batchStart = Instant.now();
		logger.info("Regular Batch start time :{}" + batchStart);
		try {
			BatchDetails batchDetails = getBatchDetail();
			ParamDetails paramDetails = otpsServiceImpl.getBatchFlag();

			if (StringUtils.equals(paramDetails.getFlag(), "Y")
					&& StringUtils.equals(paramDetails.getParamIdentifier(), "R")) {
				batchServiceImpl.createBatch(batchDetails);
				batchServiceImpl.startRegularProcess(batchDetails);
				logger.info("regular batch process end");
			}
		} catch (Exception e) {
			logger.error("error in getHeaderData method {}:" + e.getMessage());
		}
	}

	private BatchDetails getBatchDetail() {
		BatchDetails batchDetails = new BatchDetails();
		batchDetails.setStartDate(new Date());
		batchDetails.setBatchStatusCode("I");
		batchDetails.setCutOffDate(new Date());
		return batchDetails;
	}
}
