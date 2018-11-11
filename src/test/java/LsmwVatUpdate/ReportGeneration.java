package LsmwVatUpdate;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ReportGeneration {


	public void generateReport(int rowNum,String column[],String reportData[],String FileName){

		File file=new File(System.getProperty("user.dir")+"\\Result\\"+FileName);
		
		/* Delete the file before creating new instances*/
		try
		{

			if(file.exists())
			{	

				if(file.delete())
				{
					System.out.println("FILE before  DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}

		try{




			//file = new FileInputStream(new File(FILE_PATH));
			System.out.println(" ******** Obtained file");
			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			//XSSFWorkbook workbook;
			System.out.println(" ******** Inside read file");
			// Create a Sheet
			Sheet sheet = workbook.createSheet(FileName);


			//workbook = new XSSFWorkbook(new FileInputStream(new File(FILE_PATH).getAbsolutePath()));

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			
			Font passFont = workbook.createFont();
			passFont.setBold(true);
			
			passFont.setColor(IndexedColors.BRIGHT_GREEN1.getIndex());
			// Create a CellStyle with the font
			CellStyle passCellStyle = workbook.createCellStyle();
			passCellStyle.setFont(passFont);
			
			Font failFont = workbook.createFont();
			failFont.setBold(true);
			
			failFont.setColor(IndexedColors.RED.getIndex());
			// Create a CellStyle with the font
			CellStyle failCellStyle = workbook.createCellStyle();
			failCellStyle.setFont(failFont);

			Row headerRow = sheet.createRow(0);
			for(int i = 0; i < column.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(column[i]);
				cell.setCellStyle(headerCellStyle);


			}


			
			for(int x=0;x<rowNum;x++)
			{
				Row row = sheet.createRow(x+1);
			for(int j = 0; j < reportData.length; j++) {
				
				Cell cell = row.createCell(j);
				cell.setCellValue(reportData[j]);
				
				
				
				if(reportData[j].equals("PASS"))
				{
					cell.setCellStyle(passCellStyle);
				}
				else if(reportData[j].equals("FAIL"))
				{
					cell.setCellStyle(failCellStyle);
					
				}
				//row.createCell(j).setCellStyle(style1);
				


			}
			for(int i = 0; i < column.length; i++) {
				sheet.autoSizeColumn(i);
			}
			}
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"\\Result\\"+FileName);
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}






/*	public static void main(String[] args) {
		// TODO Auto-generated method 
		ReportGeneration ob=new ReportGeneration();
		
		String dir=System.getProperty("user.dir");
		System.out.println(dir);
		File file=new File(System.getProperty("user.dir")+"\\Report.xlsx");
		try
		{

			if(file.exists())
			{	

				if(file.delete())
				{
					System.out.println("TEXT FILE of before tax date DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		String column1[]={"Scenario Name","Invoice Type","Document No","Validation","Status"};
		String reportData1[]={"VAT Update","Customer","12121212","Date Changed","PASS"};
		ob.generateReport(1,column1, reportData1, "Report.xlsx");
	}
*/
}


