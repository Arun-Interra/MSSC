package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscConfigData;
import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscConfigDataServiceImpl;
import com.mazdausa.ssc.util.ConfigDataEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConfigData {

	@Autowired
	private SscConfigDataServiceImpl configServ;
	
	public Map<String, SscReportingMasterData> SetConfigData(Map<String, SscReportingMasterData> masterData){
		
		List<SscConfigData> configData = configServ.getConfigData(null);		
		
		try {
			configData.stream().forEach(cd ->{
				
				String curDlr = cd.getSUB_TYPE();
				
				SscReportingMasterData data = masterData.get(curDlr);
				
				if(cd.getKEY_CD().equals(ConfigDataEnum.AVAIL_HOUR_PER_TECH_PER_WEEK.toString())){
					data.setAVAIL_HOUR_PER_TECH_PER_WEEK(Integer.parseInt(cd.getVALUE()));
				}
				if(cd.getKEY_CD().equals(ConfigDataEnum.PROFICIENCY_PCT.toString())){
					data.setPROFICIENCY_PCT(Float.parseFloat(cd.getVALUE()));
				}
				if(cd.getKEY_CD().equals(ConfigDataEnum.FLAT_RATE_HOUR_PER_RO.toString())){
					data.setFLAT_RATE_HOUR_PER_RO(Integer.parseInt(cd.getVALUE()));
				}
				if(cd.getKEY_CD().equals(ConfigDataEnum.RO_CIW_CNT_PER_SA_PER_DAY.toString())){
					data.setRO_CIW_CNT_PER_SA_PER_DAY(Integer.parseInt(cd.getVALUE()));
				}
			});
			
		}
		catch(Exception e) {
			log.error("unale to set config data " + e);
		}
			
		return masterData;
	}
}
