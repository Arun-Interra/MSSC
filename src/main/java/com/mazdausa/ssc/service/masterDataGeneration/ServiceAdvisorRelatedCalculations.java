package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscEmployeDataServiceImpl;
import com.mazdausa.ssc.util.CalculationUtilsEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceAdvisorRelatedCalculations {
	
	@Autowired
	private SscEmployeDataServiceImpl empServ;
	
	int RO_CIW_CNT_PER_SA_PER_WEEK_POT = 0;
	int RO_CIW_CNT_PER_SA_PER_YEAR_POT = 0;
	double SA_USAGE_ACT_PCT = 0;
	int SA_TO_ADD = 0;

public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		try {
			Map<String, Integer> SaCntAct = empServ.getSaCnt(null);
			
			masterData.keySet().stream().forEach((dlr) -> {
				
				SscReportingMasterData data = masterData.get(dlr);
				
				data.setSA_CNT_ACT( (SaCntAct.containsKey(dlr)) ? SaCntAct.get(dlr) : 0 );
				
				RO_CIW_CNT_PER_SA_PER_WEEK_POT = (int) (data.getRO_CIW_CNT_PER_SA_PER_DAY() * CalculationUtilsEnum.DAYS_PER_WEEK.getvalue() );
				
				RO_CIW_CNT_PER_SA_PER_YEAR_POT = (int) (RO_CIW_CNT_PER_SA_PER_WEEK_POT * CalculationUtilsEnum.WEEK_PER_YEAR.getvalue());
				
				data.setRO_CIW_CNT_PER_SA_PER_WEEK_POT(RO_CIW_CNT_PER_SA_PER_WEEK_POT);
				data.setRO_CIW_CNT_PER_SA_PER_YEAR_POT(RO_CIW_CNT_PER_SA_PER_YEAR_POT);
				
				/*
				 * To find out "[Actual]% usage of potential capacity"
				 * " [Actual]#RO(CP+W+I) /year" divided by "[Potential]#RO (CP+W+I)/year".
				 */
				
				//Check for zero to avoid divide by zero error
				SA_USAGE_ACT_PCT = RO_CIW_CNT_PER_SA_PER_YEAR_POT > 0 ? 
						(data.getRO_CIW_CNT_PER_YEAR_ACT() /RO_CIW_CNT_PER_SA_PER_YEAR_POT) : 0 ;
				
				data.setSA_USAGE_ACT_PCT(SA_USAGE_ACT_PCT);
				
				/*
				 * Calculate "# SA to add"
				 * "[Actual]#RO(CP+W+I) /year" greater than "[Potential]#RO(CP+W+I) /year" 
				 * then ("[Actual] #RO (CP+W+I) /year" minus "[Potential] #RO (CP+W+I) /year") divided by "[Potential] #RO (CP+W+I) /year" multiplied by "[Actual] # SA"
				 */
				
				//data.setSA_TO_ADD(0);	// TODO //SA_TO_ADD column is missing in DB
						
				if(data.getRO_CIW_CNT_PER_YEAR_ACT() > RO_CIW_CNT_PER_SA_PER_YEAR_POT && 
						(RO_CIW_CNT_PER_SA_PER_YEAR_POT * data.getSA_CNT_ACT()) > 0) {
					
					SA_TO_ADD = (data.getRO_CIW_CNT_PER_YEAR_ACT() - RO_CIW_CNT_PER_SA_PER_YEAR_POT) / 
									(RO_CIW_CNT_PER_SA_PER_YEAR_POT * data.getSA_CNT_ACT());
					
					//data.setSA_TO_ADD(SA_TO_ADD);	// TODO
					
				}
				
			});
		}
		catch(Exception e) {
			log.error("Unable to calculate ServiceAdvisor related data - " + e);
		}
		
		return masterData;
	}
}

