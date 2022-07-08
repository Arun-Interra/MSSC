package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SscReportingMasterData {
	
	private int YEAR;
	private int MONTH;
	private String DLR_CD;
	private String RGN_CD;
	private String MKT_CD;
	
	private double ALT_DLR_ACT;
	private double ALT_RGN_ACT;
	private double ALT_DLR_VS_RGN_PCT;
	private String ISSUE_OPPRT_AREA_SPLY;
	
	private int TECH_CNT_ACT;
	private int AVAIL_HOUR_PER_TECH_PER_WEEK; //configValue
	private int AVAIL_HOUR_PER_WEEK;
	private double PROFICIENCY_PCT; //configValue
	private int FLAT_RATE_HOUR_PER_WEEK;
	
	private double FLAT_RATE_HOUR_PER_RO; //configValue
	private int RO_CIW_CNT_PER_WEEK_POT;
	private int RO_CIW_CNT_PER_YEAR_POT;
	private int RO_CIW_CNT_PER_YEAR_ACT;
	private int RO_CW_CNT_PER_YEAR_ACT;
	private double RO_CW_TO_CIW_PCT;
	private int RO_CW_CNT_PER_YEAR_POT;
	private double TECH_USAGE_ACT_PCT;
	private int TECH_TO_ADD;
	

	private int STALLS_CNT_REQ;
	private int STALLS_CNT_ACT;
	private double STALLS_USAGE_ACT_PCT;
	private int SLTALLS_TO_ADD;
	
	private int SA_CNT_ACT;
	private int RO_CIW_CNT_PER_SA_PER_DA; //configValue
	private int RO_CIW_CNT_PER_SA_PER_WEEK_POT;
	private int RO_CIW_CNT_PER_SA_PER_YEAR_POT;
	private double SA_USAGE_ACT_PCT;
	
	private double MAZ_LABOR_GP_R12_AMT;
	private double MAZ_LABOR_GP_R12_PER_STALL_AMT;
	
	private int UIO_ACT_CNT;
	private int UNIQUE_VIN_CNT;
	private double DLR_RETENSION_PCT;
	private double RGN_RETENTION_PCT;
	private double DLR_AVG_RO_AMT;
	private double RGN_AVG_RO_AMT;
	
	private String ISSUE_OPPRT_AREA_DMND;
	private int UIO_TOTAL_FCST;
	private double YRS3_DLR_RETENTION_PCT;
	private double YRS7_DLR_RETENTION_PCT;
	private double AVG_VISIT_PER_YEAR;
	private int RO_CW_PER_YEAR_FCST;
	private double RO_CIW_TO_CW_PCT;
	private int RO_CIW_PER_YEAR_FCST;
	
	private int GAP_RO_CIW_CNT_PER_YEAR;
	private int GAP_RO_CIW_CNT_PER_WEEK;
	private int GAP_FLAT_RATE_HOUR_PER_WEEK;
	private int GAP_AVAIL_HOUR_PER_WEEK;
	
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;

}
