package com.mazdausa.ssc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscDfsBaseData;
import com.mazdausa.ssc.repository.impl.SscDfsBaseDataRepositoryImpl;
import com.mazdausa.ssc.util.CalculationUtilsEnum;
import com.mazdausa.ssc.util.Utils;

@Service
public class SscDfsBaseDataServiceImpl {

	@Autowired
	private SscDfsBaseDataRepositoryImpl dfsRepo;
	
	@Autowired
	private Utils utils;
	
	public List<SscDfsBaseData> getDfsData(String dlrCd) {
		if(dlrCd != null)
			return dfsRepo.getDfsData(dlrCd);
		else
			return dfsRepo.getDfsData(null);
	}
	
	
	public Map<String, Long> getDfsRollTwlMnths() {
		
		int ACCT_POS_NO = (int) CalculationUtilsEnum.DFS_ACCT_POS_NO.getvalue();
		
		List<SscDfsBaseData> result = dfsRepo.getDfsRollTwlMnths(
				utils.getROLL_TWL_MNTH_STARTDATE(), utils.getROLL_TWL_MNTH_ENDDATE(), ACCT_POS_NO);
		
		//Key-DlrCd	value-ACCT_VALU_QT
		Map<String, Long>  data= result.stream().collect(Collectors.toMap(SscDfsBaseData::getDLR_CD, SscDfsBaseData::getACCT_VALU_QT));
		
		
		System.out.println(utils.getROLL_TWL_MNTH_STARTDATE() + "------" + utils.getROLL_TWL_MNTH_ENDDATE());
		
		return data;
	}

}
