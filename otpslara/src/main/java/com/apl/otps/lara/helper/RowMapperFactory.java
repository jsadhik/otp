package com.apl.otps.lara.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.apl.otps.lara.entities.ParamDetails;

/**
 * @author naresh.thota
 *
 */

public class RowMapperFactory implements RowMapper<ParamDetails> {
	public ParamDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParamDetails paramDetails = new ParamDetails();
		paramDetails.setParamIdentifier(rs.getString("PARAM_REF_IDENT"));
		paramDetails.setFlag(rs.getString("ACTIVE_FLG"));
		return paramDetails;
	}

}