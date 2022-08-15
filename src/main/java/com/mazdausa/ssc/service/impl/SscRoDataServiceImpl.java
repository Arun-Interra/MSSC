package com.mazdausa.ssc.service.impl;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscRoData;
import com.mazdausa.ssc.repository.impl.SscRoDataRepositoryImpl;

@Service
public class SscRoDataServiceImpl {
	@Autowired
	private SscRoDataRepositoryImpl roRepo;
	
	public Map<String, Integer> getRollTwelveMonth() {
		
		Map<String, Integer> dateMap = new HashMap<String, Integer>();
		
		LocalDate endDate = LocalDate.now().minusMonths(1);
		endDate = endDate.with(lastDayOfMonth());
		LocalDate startDate = endDate.minusYears(1).withDayOfMonth(1);
		
		dateMap.put("startDate", startDate.getDayOfMonth());
		dateMap.put("startMonth", startDate.getMonthValue());
		dateMap.put("startYear", startDate.getYear());
		
		dateMap.put("endDate", endDate.getDayOfMonth());
		dateMap.put("endMonth", endDate.getMonthValue());
		dateMap.put("endyear", endDate.getYear());
		
		
		return dateMap;
	}

	public Map<String, Integer> testDate(){
		/* Test Data to populate the year date value */
		Map<String, Integer> dateMap = new HashMap<String, Integer>();
		
		dateMap.put("startDate", 1);
		dateMap.put("startMonth", 1);
		dateMap.put("startYear", 2021 );
		
		dateMap.put("endDate", 31);
		dateMap.put("endMonth",12);
		dateMap.put("endYear", 2022);
		
		/* Test Data to populate the year date value */
		
		return dateMap;
	}
	
	public List<SscRoData> getRoData(String dlrCd){
		if(dlrCd != null)
			return roRepo.getRoData(dlrCd);
		else
			return roRepo.getRoData(null);
		
	}
	
	
	public Map<String, Integer> getCWIYrRoData() {
		
		Map<String, Integer> CWIYrRoData = new HashMap<String, Integer>();
		
		roRepo.getCPWIRoData(testDate()).stream().forEach(d ->{
			
			String curDlr = d.getDLR_CD();
			if(CWIYrRoData.containsKey(curDlr)) {
				CWIYrRoData.put(curDlr, CWIYrRoData.get(curDlr)+d.getRO_TOTAL_CNT());
			}
			else {
				CWIYrRoData.put(curDlr,d.getRO_TOTAL_CNT());
			}
			
		});
		
		return CWIYrRoData;
	}
	
	public Map<String, Integer> getCWYrRoData() {
		
		Map<String, Integer> CWYrRoData = new HashMap<String, Integer>();
				
				roRepo.getCPWIRoData(testDate()).stream().forEach(d ->{
					
					Integer CW = d.getRO_CP_CNT()+d.getRO_WARR_CNT();
					
					String curDlr = d.getDLR_CD();
					if(CWYrRoData.containsKey(curDlr)) {
						CWYrRoData.put(curDlr, CWYrRoData.get(curDlr) + CW);
					}
					else {
						CWYrRoData.put(curDlr,CW);
					}
					
				});
		
		return CWYrRoData;
	}
	
	
}
