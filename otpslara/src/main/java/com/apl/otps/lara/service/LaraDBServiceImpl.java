package com.apl.otps.lara.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apl.otps.lara.constants.ConstantQueries;
import com.apl.otps.lara.exception.BatchExceptionHandler;
import com.apl.otps.lara.helper.Validators;
import com.apl.otps.lara.xml.Commodity;
import com.apl.otps.lara.xml.EssentialTerms;
import com.apl.otps.lara.xml.EssentialTermsHeader;
import com.apl.otps.lara.xml.Scopes;

/**
 * @author naresh.thota
 *
 */

@Repository
public class LaraDBServiceImpl {
	Logger logger = Logger.getLogger(LaraDBServiceImpl.class);
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Qualifier("laraDataSource")
	public void setDataSource(DataSource laraDataSource) {
		this.jdbcTemplate = new JdbcTemplate(laraDataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(laraDataSource);
	}

	public EssentialTerms getHeaderData(String headerOQuery) throws BatchExceptionHandler {
		EssentialTerms essentialTerms = new EssentialTerms();
		List<EssentialTermsHeader> headersList = jdbcTemplate.query(headerOQuery,
				new RowMapper<EssentialTermsHeader>() {
					@Override
					public EssentialTermsHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
						EssentialTermsHeader header = new EssentialTermsHeader();
						try {

							header.setAmdtUID(rs.getString("AMDT_UID"));
							header.setUscHeaderCode(rs.getString("USC_HEADER_CODE"));
							header.setUscDateFrom(rs.getString("USC_DATE_FROM"));
							header.setUscDateTo(rs.getString("USC_DATE_TO"));
							header.setAmdtNumber(rs.getInt("AMDT_NUMBER"));
							header.setCreatedDate(rs.getString("CREATED_DATE"));
							header.setCreatedUser(rs.getString("CREATION_USER"));
							header.setLastModifiedDate(rs.getString("LAST_MODIF_DATE"));
							header.setLastModifiedUser(rs.getString("LAST_MODIF_USER"));
							header.setMqc(rs.getString("MQC"));
							header.setValidFlag(Validators.validateHeader(header).getValidFlag());
							header.setValidMsg(Validators.validateHeader(header).getValidMsg());
							
						} catch (Exception e) {
							logger.error("error in getHeaderData method {} "+ e.getMessage());
						}

						return header;
					}
				});
		essentialTerms.setEssList(headersList);
		return essentialTerms;
	}

	public EssentialTerms getCommodityData(List<String> amdUidslist) {
		EssentialTerms essentialTerms = new EssentialTerms();
		MapSqlParameterSource sqlParams = new MapSqlParameterSource();
		sqlParams.addValue("amdt_uid", amdUidslist);
		List<Commodity> commodityList = namedParameterJdbcTemplate.query(ConstantQueries.COMMODITY_QUERY, sqlParams,
				new RowMapper<Commodity>() {
					@Override
					public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
						Commodity commodity = new Commodity();
						commodity.setAmdtUID(rs.getString("AMDT_UID"));
						commodity.setAppendixCode(rs.getString("APPENDIX_CODE"));
						commodity.setBulletCode(rs.getString("BULLET_CODE"));
						commodity.setLongDesc(rs.getString("LONG_DESCRIPTION"));
						return commodity;
					}
				});
		essentialTerms.setCmdityList(commodityList);
		return essentialTerms;
	}

	public EssentialTerms getScopeData(List<String> amdUidslist) {
		EssentialTerms essentialTerms = new EssentialTerms();
		MapSqlParameterSource sqlParams = new MapSqlParameterSource();
		sqlParams.addValue("amdt_uid", amdUidslist);
		List<Scopes> scopesList = namedParameterJdbcTemplate.query(ConstantQueries.SCOPE_QUERY, sqlParams,
				new RowMapper<Scopes>() {
					@Override
					public Scopes mapRow(ResultSet rs, int rowNum) throws SQLException {
						Scopes scopeData = new Scopes();
						scopeData.setAmdtUID(rs.getString("AMDT_UID"));
						scopeData.setCountryName(rs.getString("COUNTRY_NAME"));
						scopeData.setIdentifier(rs.getString("IDENTIFIER"));
						return scopeData;
					}
				});
		essentialTerms.setScopesList(scopesList);
		return essentialTerms;
	}

}
