package com.mazdausa.ssc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mazdausa.ssc.dao.SscConfigData;
import com.mazdausa.ssc.repository.ConfigDataRepository;
import com.mazdausa.ssc.service.ConfigDataService;

@Service
public class ConfigDataServiceImpl  implements ConfigDataService {
	
	@Autowired
	ConfigDataRepository configDataRepository;

	@Override
	public List<SscConfigData> getDealerDetails(String dlrCd) {
		if(dlrCd.equalsIgnoreCase("all")) {
			return configDataRepository.readAllDataFromDb();
		} else {
			return Arrays.asList(configDataRepository.readSingleDataFromDb(dlrCd));
		}
	}

	@Override
	public List<SscConfigData> getDataFromExcel(MultipartFile files) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(files.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet worksheet = workbook.getSheetAt(6);
		DataFormatter dataFormatter = new DataFormatter();
		ArrayList<SscConfigData> sscConfigDatas = new ArrayList<SscConfigData>();
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				XSSFRow row = worksheet.getRow(index);
				SscConfigData sscConfigData = new SscConfigData();
				sscConfigData.setTYPE(null);
				sscConfigData.setSUB_TYPE(null);
				sscConfigData.setKEY_CD(null);
				sscConfigData.setVALUE(null);
				sscConfigData.setDESCRIPTION(null);
				sscConfigData.setSTATUS(null);

				sscConfigData.setCREATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setCREATE_TM(new java.sql.Timestamp(new java.util.Date().getTime()));
				sscConfigData.setLAST_UPDATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setLastUpdatedTime((new java.sql.Timestamp(new java.util.Date().getTime())));
				System.out.println(sscConfigData);
				sscConfigDatas.add(sscConfigData);
			}
		}
		return sscConfigDatas;
	}

	@Override
	public List<SscConfigData> insertUpdateMultipleDealer(List<SscConfigData> sscConfigDatas) {
		return configDataRepository.addMultipleDataToDb(sscConfigDatas);    
	}

	@Override
	public List<SscConfigData> insertUpdateSingleDealer(SscConfigData sscConfigData) {
		return configDataRepository.addMultipleDataToDb(Arrays.asList(sscConfigData));
	}

	@Override
	public SscConfigData updateAllDealers(SscConfigData sscConfigData) {
		return configDataRepository.updateAllDealerToDb(sscConfigData);
	}

	@Override
	public List<SscConfigData> getDealersDetailsByMonth(String month, String year) {
		return configDataRepository.readAllDataFromDbByMonth(month, year);
	}

}
