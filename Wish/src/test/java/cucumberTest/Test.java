package cucumberTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	
	//td[@id='tdAdditionalData_ITC']/table//tr//td[@class='fieldCell']//child::select
	
	
	//td[@id='tdAdditionalData_XDSC']//td[2]
	
	//td[@id='tdAdditionalData_XDSC']//*[contains(text(),'Burea')]/following-sibling::input
	
/*	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

		   String[][] tabArray = null;

		   try {

			   FileInputStream ExcelFile = new FileInputStream(FilePath);

			   // Access the required test data sheet

			   ExcelWBook = new XSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);

			   int startRow = 1;

			   int startCol = 0;

			   int ci,cj;

			   //first row is header so i have substracted one row
			   int totalRows = getLastTestDataRowNum()-1;
			   

			   // you can write a function as well to get Column count

			   int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			   

			   tabArray=new String[totalRows][totalCols];

			   ci=0;

			   boolean flag=false;
			   for (int i=startRow;i<=totalRows;i++, ci++) {           	   

				  cj=0;
				  if(flag==true)
				  {
					  break;
				  }
				  

				  
				   for (int j=startCol;j<totalCols;j++, cj++){

					   tabArray[ci][cj]=getCellData(i,j);
					   if(tabArray[ci][cj].equalsIgnoreCase(""))
					   {
						   flag=true;
						   break;
					   }

					   System.out.println(tabArray[ci][cj]);  

						}

					}

				}

			catch (FileNotFoundException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			catch (IOException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			return(tabArray);

			}

*/

}
