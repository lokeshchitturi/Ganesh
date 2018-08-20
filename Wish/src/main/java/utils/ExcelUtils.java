package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	

	private static XSSFWorkbook ExcelWBook;

	

public static void initializeExcelFile(String FilePath) throws Exception
{
	   FileInputStream ExcelFile = new FileInputStream(FilePath);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

}

public static XSSFSheet getWorkSheet(String sheetName)
{
	return ExcelWBook.getSheet(sheetName);
}
	
public static int getLastTestDataRowNum(XSSFSheet ExcelWSheet)
{
	try {
		int i;
		for(i=0;i<ExcelWSheet.getLastRowNum();i++)
		{
			String val=ExcelWSheet.getRow(i).getCell(0).getStringCellValue();
			if(val.equalsIgnoreCase(""))
			{
				break;
			}
		}
		return i;
		
	} catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
}

public static String getCellData(XSSFSheet ExcelWSheet,int RowNum, int ColNum) throws Exception {

	try{

		XSSFCell Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		
		int dataType = Cell.getCellType();

		if  (dataType == 3) {

			return "";

		}else{

			DataFormatter formatter = new DataFormatter();
			String CellData = formatter.formatCellValue(Cell);	
			return CellData;

		}
	}
		catch (Exception e){
		throw (e);
		}

	}
	
public static void main(String[] args) throws Exception {
	System.out.println(System.getProperty("user.dir"));
	initializeExcelFile(System.getProperty("user.dir")+"//ApplicationForm_TextData.xlsx");
	XSSFSheet sheet1=getWorkSheet("Application Form");
	System.out.println(getCellData(sheet1,1, 4));
	String a=sheet1.getRow(1).getCell(4).getStringCellValue();
	String[] checks=a.split(";");
	XSSFSheet sheet2=getWorkSheet(checks[0]);
	System.out.println(getCellData(sheet2,1, 2));
}

}
