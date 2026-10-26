package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName , int rowNum , int cellNum) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./testdata/testdataCRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/testdataCRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName , int rowNum , int cellNum, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/testdataCRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis); 
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos = new FileOutputStream("./testdata/testdataCRM.xlsx");
		wb.write(fos);
		wb.close();
		
	}
}
