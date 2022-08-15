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

import com.mazdausa.ssc.dao.SscFacilityData;
import com.mazdausa.ssc.repository.StallAndLiftsRepository;
import com.mazdausa.ssc.service.StallAndLiftsCountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StallAndLiftsCountServiceImpl implements StallAndLiftsCountService {

	@Autowired
	StallAndLiftsRepository stallAndLiftsRepository;

	@Override
	public List<SscFacilityData> getDataFromExcel(MultipartFile files) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(files.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug(">> preparing DTO from xcel sheet");
		XSSFSheet worksheet = workbook.getSheetAt(6);
		DataFormatter dataFormatter = new DataFormatter();
		ArrayList<SscFacilityData> sscFacilityDatas = new ArrayList<SscFacilityData>();
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				XSSFRow row = worksheet.getRow(index);
				SscFacilityData sscFacilityData = new SscFacilityData();
				sscFacilityData.setYEAR(2022);
				sscFacilityData.setMONTH(6);
				sscFacilityData.setDLR_CD(dataFormatter.formatCellValue(row.getCell(0)));
				sscFacilityData.setSTALLS_CNT(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(1))));
				sscFacilityData.setMMA_STALLS_CNT(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(2))));
				sscFacilityData.setLIFTS_CNT(Integer.parseInt((dataFormatter.formatCellValue(row.getCell(3)))));
				sscFacilityData.setMMA_LIFTS_CNT(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(4))));

				sscFacilityData.setCREATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setCREATE_TM(new java.sql.Timestamp(new java.util.Date().getTime()));
				sscFacilityData.setLAST_UPDATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setLastUpdatedTime((new java.sql.Timestamp(new java.util.Date().getTime())));
				System.out.println(sscFacilityData);
				sscFacilityDatas.add(sscFacilityData);
			}
		}
		return sscFacilityDatas;
	}

	@Override
	public List<SscFacilityData> insertUpdateSingleDealer(SscFacilityData sscFacilityData) {
		return stallAndLiftsRepository.addMultipleDataToDb(Arrays.asList(sscFacilityData));

	}

	@Override
	public List<SscFacilityData> insertUpdateMultipleDealer(List<SscFacilityData> sscFacilityDatas) {
		return stallAndLiftsRepository.addMultipleDataToDb(sscFacilityDatas);    

	}

	@Override
	public SscFacilityData updateAllDealers(SscFacilityData sscFacilityData) {
		return stallAndLiftsRepository.updateAllDealerToDb(sscFacilityData);
	}

	@Override
	public List<SscFacilityData> getDealerDetails(String dlrCd) {
		if(dlrCd.equalsIgnoreCase("all")) {
			return stallAndLiftsRepository.readAllDataFromDb();
		} else {
			return Arrays.asList(stallAndLiftsRepository.readSingleDataFromDb(dlrCd));
		}
	}

	@Override
	public List<SscFacilityData>  getDealersDetailsByMonth(String month, String year) {
		return stallAndLiftsRepository.readAllDataFromDbByMonth(month, year);
		
	}
}
