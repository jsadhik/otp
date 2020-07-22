package com.apl.otps.lara.constants;

/**
 * @author naresh.thota
 *
 */
public class ConstantQueries {

	public static final String COMMODITY_QUERY = "WITH USCselection AS( SELECT DISTINCT ua.appendix_code ,a.amdt_uid ,ua.appendix_uid FROM USC_AMENDMENT a ,usc_appendix ua ,USC_DETAIL UDd WHERE a.amdt_uid in(:amdt_uid) " + 
			"  AND a.amdt_uid = ua.amdt_uid AND UDd.APPENDIX_UID= ua.appendix_uid ) SELECT lst.amdt_uid ,lst.appendix_code, bl.BULLET_CODE, LISTAGG(uc.DESCRIPTION, ',') WITHIN GROUP ( " + 
			"ORDER BY uc.DESCRIPTION) AS LONG_DESCRIPTION FROM USCselection lst,USc_Bullet bl,usc_cdty_grp_det cgrp,usc_commodity uc " + 
			"WHERE bl.BULLET_TYPE  = 'STD' " + 
			"AND lst.appendix_uid  = bl.appendix_uid " + 
			"AND bl.BULLET_UID     = cgrp.BULLET_UID " + 
			"AND cgrp.USC_CDTY_UID = uc.USC_CDTY_UID " + 
			"GROUP BY (lst.amdt_uid ,lst.appendix_code, bl.BULLET_CODE)";

	public static final String SCOPE_QUERY = "WITH USC_RATE_CONDITIONS AS\r\n" + 
			"(\r\n" + 
			"SELECT a.AMDT_UID,ap.appendix_uid,RECEIPT_POINT_CODE,POL_CODE,POL_GRP_TYPE,POL_GRP_UID,POD_CODE,POD_GRP_TYPE,POD_GRP_UID,DELIV_POINT_CODE\r\n" + 
			"FROM usc_amendment a,\r\n" + 
			"usc_appendix ap,\r\n" + 
			"usc_detail ud\r\n" + 
			"WHERE \r\n" + 
			"a.amdt_uid in(:amdt_uid) and \r\n" + 
			"ap.amdt_uid = a.amdt_uid \r\n" + 
			" and  ud.appendix_uid =ap.appendix_uid\r\n" + 
			"),\r\n" + 
			"USC_ARBITRARY_D AS(\r\n" + 
			"select a.AMDT_UID,CH_POINT_CODE,IMPORT_EXPORT_TYPE FROM \r\n" + 
			"usc_amendment a,\r\n" + 
			"usc_appendix ap,\r\n" + 
			"usc_arbitrary uarb\r\n" + 
			"WHERE \r\n" + 
			"a.amdt_uid in(:amdt_uid) and \r\n" + 
			"ap.amdt_uid = a.amdt_uid and \r\n" + 
			"uarb.appendix_uid =ap.appendix_uid\r\n" + 
			")\r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME AS COUNTRY_NAME,'DEST' AS IDENTIFIER from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"usc_port_grp pg,\r\n" + 
			"usc_port_grp_det pgd,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			"rc.POD_GRP_TYPE='GRP' \r\n" + 
			"and pg.APPENDIX_UID =rc.APPENDIX_UID\r\n" + 
			"and pg.PORT_GRP_UID =pgd.PORT_GRP_UID\r\n" + 
			"and rc.POD_CODE =pg.PORT_GRP_CODE\r\n" + 
			"and pgd.point_code = prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"UNION \r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME,'ORIG' from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"usc_port_grp pg,\r\n" + 
			"usc_port_grp_det pgd,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" rc.POL_GRP_TYPE='GRP' \r\n" + 
			"and pg.APPENDIX_UID =rc.APPENDIX_UID\r\n" + 
			"and pg.PORT_GRP_UID =pgd.PORT_GRP_UID\r\n" + 
			"and pgd.point_code = prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"and rc.pol_code =pg.PORT_GRP_CODE\r\n" + 
			"UNION \r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME,'DEST' from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" rc.POD_GRP_TYPE='UNI' \r\n" + 
			"and rc.POD_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"UNION \r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME,'ORIG' from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			"rc.POL_GRP_TYPE='UNI' \r\n" + 
			"and rc.POL_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"UNION \r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME,'ORIG' from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" rc.RECEIPT_POINT_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"UNION \r\n" + 
			"\r\n" + 
			"select  rc.AMDT_UID,cntry.FULL_NAME,'DEST' from \r\n" + 
			"usc_rate_conditions rc,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" rc.DELIV_POINT_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"UNION\r\n" + 
			"\r\n" + 
			"select  uarb.AMDT_UID,cntry.FULL_NAME,'ORIG' from \r\n" + 
			"USC_ARBITRARY_D uarb,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" uarb.CH_POINT_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"and uarb.IMPORT_EXPORT_TYPE ='E'\r\n" + 
			"\r\n" + 
			"UNION\r\n" + 
			"select  uarb.AMDT_UID,cntry.FULL_NAME,'DEST' from \r\n" + 
			"USC_ARBITRARY_D uarb,\r\n" + 
			"ports prts,\r\n" + 
			"countries cntry\r\n" + 
			"where\r\n" + 
			" uarb.CH_POINT_CODE =prts.point_code\r\n" + 
			"and prts.country_code =cntry.COUNTRY\r\n" + 
			"and uarb.IMPORT_EXPORT_TYPE ='I'";

	public static final String HEADER_R_QUERY = "select " + "ua.amdt_uid, " + "uh.USC_HEADER_CODE, "
			+ "TO_CHAR(TRUNC(USC_DATE_FROM),'DD-MON-RR') AS USC_DATE_FROM , "
			+ "TO_CHAR(TRUNC(USC_DATE_TO),'DD-MON-RR') AS USC_DATE_TO, "
			+ "TO_CHAR(TRUNC(AMDT_DATE_FROM),'DD-MON-RR') AS AMDT_DATE_FROM , " + "AMDT_NUMBER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.CREATED_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') AS CREATED_DATE , "
			+ "ua.CREATION_USER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.LAST_MODIF_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') as LAST_MODIF_DATE, "
			+ " " + "ua.LAST_MODIF_USER, " + "MQC AS MQC " + "from usc_amendment ua,usc_header uh,usc_mqc um, "
			+ "usc_amdt_status ams " + "where " + "uh.SHIPCOMP_CODE ='0015' "
			+ "and uh.usc_header_uid =ua.usc_header_uid and "
			+ "trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) <=trunc(TO_DATE('31-MAR-20','DD_MON-RR')) " + " "
			+ "and trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) >trunc(TO_DATE('01-MAR-20','DD_MON-RR')) "
			+ "            and " + "ua.amdt_uid =um.amdt_uid " + "and ua.amdt_uid =ams.amdt_uid "
			+ "and ams.status_code='FCS'";

	public static final String HEADER_O_QUERY = "select  " + "ua.amdt_uid, " + "uh.USC_HEADER_CODE, "
			+ "TO_CHAR(TRUNC(USC_DATE_FROM),'DD-MON-RR') AS USC_DATE_FROM , "
			+ "TO_CHAR(TRUNC(USC_DATE_TO),'DD-MON-RR') AS USC_DATE_TO, "
			+ "TO_CHAR(TRUNC(AMDT_DATE_FROM),'DD-MON-RR') AS AMDT_DATE_FROM , " + "AMDT_NUMBER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.CREATED_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') AS CREATED_DATE , "
			+ "ua.CREATION_USER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.LAST_MODIF_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') as LAST_MODIF_DATE, "
			+ "--ua.last_modif_date , " + "ua.LAST_MODIF_USER, " + "MQC AS MQC "
			+ "from usc_amendment ua,usc_header uh,usc_mqc um, " + "usc_amdt_status ams  " + "where  "
			+ "uh.SHIPCOMP_CODE ='0015' " + "and uh.usc_header_uid =ua.usc_header_uid and  "
			+ "trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) =trunc(:ctoffdate)  "
			+ "--and ua.last_modif_date <sysdate   " + " " + "			and " + "ua.amdt_uid =um.amdt_uid  "
			+ "and ua.amdt_uid =ams.amdt_uid " + "and ams.status_code='FCS' ";

	public static final String HEADER_OA_QUERY = "select  " + "ua.amdt_uid, " + "uh.USC_HEADER_CODE, "
			+ "TO_CHAR(TRUNC(USC_DATE_FROM),'DD-MON-RR') AS USC_DATE_FROM , "
			+ "TO_CHAR(TRUNC(USC_DATE_TO),'DD-MON-RR') AS USC_DATE_TO, "
			+ "TO_CHAR(TRUNC(AMDT_DATE_FROM),'DD-MON-RR') AS AMDT_DATE_FROM , " + "AMDT_NUMBER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.CREATED_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') AS CREATED_DATE , "
			+ "ua.CREATION_USER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.LAST_MODIF_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') as LAST_MODIF_DATE, "
			+ "--ua.last_modif_date , " + "ua.LAST_MODIF_USER, " + "MQC AS MQC "
			+ "from usc_amendment ua,usc_header uh,usc_mqc um, " + "usc_amdt_status ams  " + "where  "
			+ "uh.SHIPCOMP_CODE ='0015' " + "and uh.usc_header_uid =ua.usc_header_uid and  "
			+ "trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) >=trunc(:ctoffdate)  "
			+ "--and ua.last_modif_date <sysdate   " + " " + "			and " + "ua.amdt_uid =um.amdt_uid  "
			+ "and ua.amdt_uid =ams.amdt_uid " + "and ams.status_code='FCS' ";
	public static final String HEADER_DR_QUERY = "select  " + "ua.amdt_uid, " + "uh.USC_HEADER_CODE, "
			+ "TO_CHAR(TRUNC(USC_DATE_FROM),'DD-MON-RR') AS USC_DATE_FROM , "
			+ "TO_CHAR(TRUNC(USC_DATE_TO),'DD-MON-RR') AS USC_DATE_TO, "
			+ "TO_CHAR(TRUNC(AMDT_DATE_FROM),'DD-MON-RR') AS AMDT_DATE_FROM , " + "AMDT_NUMBER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.CREATED_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') AS CREATED_DATE , "
			+ "ua.CREATION_USER, "
			+ "TO_CHAR(FROM_TZ(CAST(ua.LAST_MODIF_DATE as timestamp),'UTC') at time zone 'America/Los_Angeles','DD-MON-RR hh24:mi') as LAST_MODIF_DATE, "
			+ "--ua.last_modif_date , " + "ua.LAST_MODIF_USER, " + "MQC AS MQC "
			+ "from usc_amendment ua,usc_header uh,usc_mqc um, " + "usc_amdt_status ams  " + "where  "
			+ "uh.SHIPCOMP_CODE ='0015' " + "and uh.usc_header_uid =ua.usc_header_uid and  "
			+ "trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) <=trunc(:ctoffdate)  "
			+ "--and ua.last_modif_date <sysdate   "
			+ "and trunc( CAST((  FROM_TZ(CAST(ams.CHG_DATE_STATUS AS TIMESTAMP) ,'America/New_York') "
			+ "            AT TIME ZONE 'Asia/Singapore') AS DATE)) >=trunc(:lctoffdate)  " + "			and "
			+ "ua.amdt_uid =um.amdt_uid  " + "and ua.amdt_uid =ams.amdt_uid " + "and ams.status_code='FCS' ";
	public static final String FLAG_QUERY = "SELECT PARAM_REF_IDENT,ACTIVE_FLG FROM APL_OTPS.OTPS_LARA_PARAM WHERE ACTIVE_FLG=?";
	//select param_identifier,active_flag from param_table where param_identifier  in ('O','OA','DR');
	public static final String FLAG_UPDATE_QUERY = "UPDATE APL_OTPS.FLAG_TABLE SET FLAG=? WHERE FLAG=?";
	
	public static final String BATCH_QUERY="INSERT INTO APL_OTPS.OTPS_BATCH(BATCH_NBR,START_TIME,STATUS_CD,CUTOFF_DT) VALUES(?,?,?,?)";
	public static final String BATCH_SEQUENCE="SELECT APL_OTPS.OTPS_BATCH_SEQ.NEXTVAL FROM DUAL";
}
