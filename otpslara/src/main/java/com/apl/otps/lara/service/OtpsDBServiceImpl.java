package com.apl.otps.lara.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apl.otps.lara.constants.ConstantQueries;
import com.apl.otps.lara.entities.BatchDetails;
import com.apl.otps.lara.entities.ParamDetails;
import com.apl.otps.lara.exception.BatchExceptionHandler;
import com.apl.otps.lara.helper.RowMapperFactory;

/**
 * @author naresh.thota
 *
 */
@Repository
public class OtpsDBServiceImpl {
	Logger logger = Logger.getLogger(OtpsDBServiceImpl.class);

	private JdbcTemplate JdbcTemplate;
	
	/*@Autowired
	EmailSender emailSender;*/

	@Autowired
	@Qualifier("otpsDataSource")
	public void setDataSource(DataSource otpsDataSource) {
		this.JdbcTemplate = new JdbcTemplate(otpsDataSource);
	}

	@Transactional
	public BatchDetails createBatch(BatchDetails batchDetails) {
		long batchNo = JdbcTemplate.queryForObject(ConstantQueries.BATCH_SEQUENCE, Long.class);
		batchDetails.setBatchNo(batchNo);
		JdbcTemplate.update(ConstantQueries.BATCH_QUERY, batchDetails.getBatchNo(), batchDetails.getStartDate(),
				batchDetails.getBatchStatusCode(), batchDetails.getCutOffDate());
		return batchDetails;
	}

	public ParamDetails getBatchFlag() throws BatchExceptionHandler {
		logger.info("getBatchFlag method called");
		ParamDetails paramDetails = null;
		try {
			paramDetails = JdbcTemplate.queryForObject(ConstantQueries.FLAG_QUERY, new RowMapperFactory(), "Y");
		} catch (Exception e) {
			logger.error("error in getBatchFlag method {}: " + e.getMessage());
			throw new BatchExceptionHandler("Getting exception while connecting to Database");
		}
		return paramDetails;
	}

	public void updateFlag() {
		JdbcTemplate.update(ConstantQueries.FLAG_UPDATE_QUERY, "N", "Y");
	}

	public void executeLaraStoredProcedure(BatchDetails batchDetails, String headerString, String commodity, String scope) {
		try {
			SimpleJdbcCall simpleJdbcCall;
			JdbcTemplate.setResultsMapCaseInsensitive(true);
			simpleJdbcCall = new SimpleJdbcCall(JdbcTemplate).withSchemaName("APL_OTPS")
					.withCatalogName("OTPS_LARA_PKG").withProcedureName("PR_OTPS_LARA_OPERATION")
					.declareParameters(new SqlParameter("i_nbr_batch_nbr", Types.NUMERIC),
							new SqlParameter("i_clob_header", Types.CLOB),
							new SqlParameter("i_clob_commodity", Types.CLOB),
							new SqlParameter("i_clob_scope", Types.CLOB),
							new SqlOutParameter("o_num_err", Types.NUMERIC),
							new SqlOutParameter("o_char_mesg", Types.VARCHAR));

			Map<String, Object> paramaters = new HashMap<String, Object>();
			paramaters.put("i_nbr_batch_nbr", batchDetails.getBatchNo());
			paramaters.put("i_clob_header", new SqlLobValue(headerString));
			paramaters.put("i_clob_commodity", new SqlLobValue(commodity));
			paramaters.put("i_clob_scope", new SqlLobValue(scope));
			
			Map<String, Object> out = simpleJdbcCall.execute(paramaters);
			batchDetails.setBatchRespCode((Integer)out.get("o_num_err"));
			batchDetails.setBatchRespMsg((String)out.get("o_char_mesg"));
			//emailSender.sendEmail(batchDetails);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error occured while executing pr_otps_lara_operation"
					+ " in executeLaraStoredProcedure method {} "+e.getMessage());
		}

	}
}
