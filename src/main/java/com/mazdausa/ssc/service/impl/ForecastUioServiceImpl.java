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

import com.mazdausa.ssc.dao.SscUioData;
import com.mazdausa.ssc.repository.ForecastUioRepository;
import com.mazdausa.ssc.service.ForecastUioService;


@Service
public class ForecastUioServiceImpl implements ForecastUioService{
	
	@Autowired
	ForecastUioRepository forecastUioRepository;

	@Override
	public List<SscUioData> getDataFromExcel(MultipartFile files) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(files.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet worksheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		ArrayList<SscUioData> sscUioDatas = new ArrayList<SscUioData>();
		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				XSSFRow row = worksheet.getRow(index);
				SscUioData sscUioData = new SscUioData();
				sscUioData.setYEAR(2022);
				sscUioData.setMONTH(6);
				sscUioData.setDLR_CD(dataFormatter.formatCellValue(row.getCell(2)));
				sscUioData.setUIO_TYPE(dataFormatter.formatCellValue(row.getCell(3)));
				sscUioData.setUIO_VAL(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(4))));
				
				sscUioData.setCREATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setCREATE_TM(new java.sql.Timestamp(new java.util.Date().getTime()));
				sscUioData.setLAST_UPDATED_BY(dataFormatter.formatCellValue(row.getCell(0)));
				//sscFacilityData.setLastUpdatedTime((new java.sql.Timestamp(new java.util.Date().getTime())));
				System.out.println(sscUioData);
				System.out.println("index" + index);
				sscUioDatas.add(sscUioData);
			}
		}
		return sscUioDatas;
	}

	@Override
	public List<SscUioData> insertUpdateMultipleDealer(List<SscUioData> sscUioDatas) {
		return forecastUioRepository.addMultipleDataToDb(sscUioDatas);
	}

	@Override
	public List<SscUioData> insertUpdateSingleDealer(SscUioData sscUioData) {
		return forecastUioRepository.addMultipleDataToDb(Arrays.asList(sscUioData));
	}

	@Override
	public List<SscUioData> getDealerDetails(String dlrCd) {
		if(dlrCd.equalsIgnoreCase("all")) {
			return forecastUioRepository.readAllDataFromDb();
		} else {
			return Arrays.asList(forecastUioRepository.readSingleDataFromDb(dlrCd));
		}
	}

	@Override
	public List<SscUioData> getDealersDetailsByMonth(String month, String year) {
		return forecastUioRepository.readAllDataFromDbByMonth(month, year);
	}

}
