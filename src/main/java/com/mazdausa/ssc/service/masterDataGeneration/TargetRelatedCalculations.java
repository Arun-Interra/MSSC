package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.dao.SscUioData;
import com.mazdausa.ssc.repository.impl.SscUioDataRepositoryImpl;
import com.mazdausa.ssc.service.impl.SscAvgVisitDataServiceImpl;
import com.mazdausa.ssc.util.CalculationUtilsEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TargetRelatedCalculations {
	
	@Autowired
	private SscUioDataRepositoryImpl uioDataServ;
	
	@Autowired
	private SscAvgVisitDataServiceImpl avgVisitServ;
	
	double YRS7_DLR_RETENTION_PCT = 0;
	double AVG_VISIT_PER_YEAR = 0;
	int RO_CW_PER_YEAR_FCST = 0;
	int RO_CIW_PER_YEAR_FCST = 0;
	int GAP_RO_CIW_CNT_PER_YEAR = 0;
	int GAP_RO_CIW_CNT_PER_WEEK = 0;
	int GAP_FLAT_RATE_HOUR_PER_WEEK = 0;
	
	public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		
		try {
			
			List<SscUioData> uioData = uioDataServ.getUioData(null);
			
			//key->DLR_CD, value->AVG_VISIT_VAL
			Map<String, Double> avgVisitData = avgVisitServ.getAvgVistDataMap();  //return a map of DLR_CD->AVG_VISIT_VAL
			
			uioData.stream().forEach(ud -> {
				
				String curDlr = ud.getDLR_CD();
				
				if(masterData.containsKey(curDlr)) {
				
					SscReportingMasterData data = masterData.get(curDlr);
					
					data.setUIO_TOTAL_FCST(ud.getUIO_VAL());
					
					YRS7_DLR_RETENTION_PCT = data.getDLR_RETENSION_PCT() + (data.getDLR_RETENSION_PCT() * 0.01);
					data.setYRS7_DLR_RETENTION_PCT(YRS7_DLR_RETENTION_PCT);
					
					//check the Map has the dealer to avoid null pointer exception
					AVG_VISIT_PER_YEAR = avgVisitData.containsKey(curDlr) ? avgVisitData.get(curDlr) : 0;
					data.setAVG_VISIT_PER_YEAR(AVG_VISIT_PER_YEAR);
					
					RO_CW_PER_YEAR_FCST = (int)( YRS7_DLR_RETENTION_PCT * ud.getUIO_VAL() );
					data.setRO_CW_PER_YEAR_FCST(RO_CW_PER_YEAR_FCST);
					
					RO_CIW_PER_YEAR_FCST = (int)(ud.getUIO_VAL() * data.getRO_CIW_TO_CW_PCT());
					data.setRO_CIW_PER_YEAR_FCST(RO_CIW_PER_YEAR_FCST);
					
					GAP_RO_CIW_CNT_PER_YEAR = data.getRO_CIW_CNT_PER_YEAR_POT() - RO_CIW_PER_YEAR_FCST ;
					data.setGAP_RO_CIW_CNT_PER_YEAR(GAP_RO_CIW_CNT_PER_YEAR);
					
					GAP_RO_CIW_CNT_PER_WEEK = (int) (GAP_RO_CIW_CNT_PER_YEAR / CalculationUtilsEnum.WEEK_PER_YEAR.getvalue());
					data.setGAP_RO_CIW_CNT_PER_WEEK(GAP_RO_CIW_CNT_PER_WEEK);
					
					GAP_FLAT_RATE_HOUR_PER_WEEK = (int)(GAP_RO_CIW_CNT_PER_WEEK * data.getFLAT_RATE_HOUR_PER_RO());
					data.setGAP_FLAT_RATE_HOUR_PER_WEEK(GAP_FLAT_RATE_HOUR_PER_WEEK);
					
					data.setGAP_AVAIL_HOUR_PER_WEEK((int)(GAP_FLAT_RATE_HOUR_PER_WEEK * data.getPROFICIENCY_PCT()));
					
				}
			});
			
		}
		
		catch(Exception e) {
			log.error("unable to calculate Target - UIO Related Data " + e );
		}
		
		return masterData;
	}
}
