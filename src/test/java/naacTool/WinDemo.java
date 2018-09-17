package naacTool;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class WinDemo {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml
		int rowNo=0;

		WinDemo demo=new WinDemo();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();
		Fixture fix=new Fixture();
		String opco=fix.getOpco().toUpperCase();
		String env=fix.getEnvironment().toUpperCase();
		Map<String,String> map=logon.valueSetter(opco, env) ;

		String FilePath=System.getProperty("user.dir")+map.get("SAPLOGON_EXCELNAME");
		System.out.println("Excel file path::"+FilePath);
		//*********************************Retrieve Total Row No**************************************************

		rowNo=demo.readExcelData(FilePath);
		File file=new File(System.getProperty("user.dir")+"\\Result\\output.txt");
		try
		{

			if(file.exists())
			{

				if(file.delete())
				{
					System.out.println("TEXT FILE DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		WiniumDriver driver=null;

		DesktopOptions option=new DesktopOptions();
		String path=ConstantForSapLogon.ConstantForSap.SAP_LOGON_PATH.getValue();
		option.setApplicationPath(path);
		option.setDebugConnectToRunningApp(false);
		option.setLaunchDelay(2);

		driver=new WiniumDriver(new URL(ConstantForSapLogon.ConstantForSap.DESKTOP_DRIVER_URL.getValue()), option);
		Thread.sleep(5000);
		if(driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.SAPLOGON_TITLE.getValue())).getSize()!=null)
		{
			WebElement e=driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_VRZ.getValue()));


			Thread.sleep(1000);
			e.click();

			driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_LOGIN.getValue())).click();
			Thread.sleep(6000);




			try {
				Robot robot=new Robot();
				System.out.println("*****"+map.get("SAPLOGON_USERID"));

				demo.simulateClipBoard(map.get("SAPLOGON_USERID"),robot);



				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				Thread.sleep(10000);




				demo.simulateClipBoard(map.get("SAPLOGON_PASSWORD"),robot);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				try{



					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_MULTIPLE_USER.getValue())).getSize()!=null)
					{
						System.out.println("size1:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_MULTIPLE_USER.getValue())).getSize());
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_TERMINATE_LOGON.getValue())).click();
					}
				}
				catch(Exception e1)
				{
					System.out.println("In multi exception::");
					e1.getMessage();
				}



				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EDIT.getValue())).getSize()!=null)
				{
					System.out.println("Got itttt");
					System.out.println("size2:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EDIT.getValue())).getSize());

					Actions action=new Actions(driver);
					action.moveToElement(driver.findElement(By.className(ConstantForSapLogon.ConstantForSap.SAPLOGON_EDIT.getValue())));



					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.SAPLOGON_UPLOAD_TCODE.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);


					Thread.sleep(6000);


					String excelPath=System.getProperty("user.dir")+map.get("SAPLOGON_EXCELNAME");
					System.out.println("ExcelPath::::"+excelPath);


					demo.simulateClipBoard(excelPath,robot);

					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					demo.simulateClipBoard(map.get("SAPLOGON_PURCHASEORG"),robot);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);

					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					demo.simulateClipBoard(map.get("SAPLOGON_PURCHASEORG"),robot);
					Thread.sleep(2000);
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EXECUTE_BUTTON.getValue())).click();
					//robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(4000);
					try{
						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_SECURITY_MODAL.getValue()))!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_SECURITY_MODAL.getValue())).click();
							Thread.sleep(2000);
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_SECURITY_MODAL_ALLOW.getValue())).click();
							Thread.sleep(rowNo*10000);
						}
						else
						{
							Thread.sleep(rowNo*7000);
						}
					}
					catch(Exception ex)
					{
						ex.getMessage();
						Thread.sleep(rowNo*7000);
					}

					Thread.sleep(3000);
					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_POST_CONFIRMATION.getValue()))!=null)
						{
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(4000);
							/*driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_MICROSOFT_EXCEL.getValue())).click();
							Thread.sleep(15000);
							if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_SECURITY_POPUP.getValue()))!=null)
							{
								Thread.sleep(180000);
								driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_ENABLE_MICRO.getValue())).click();*/
							Thread.sleep(7000);
							if( driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_LOCALE_FILE.getValue())).getSize()!=null)
							{
								driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_LOCALE_FILE.getValue())).click();
							}
							Thread.sleep(7000);
							if( driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_FILE_SAVE_CONTINUE.getValue())).getSize()!=null)
							{
								driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_FILE_SAVE_CONTINUE.getValue())).click();
							}

							String filename="output.txt";
							String Path=System.getProperty("user.dir")+"\\Result\\";
							Thread.sleep(2000);
							robot.keyPress(KeyEvent.VK_CONTROL);
							robot.keyPress(KeyEvent.VK_A);
							robot.keyRelease(KeyEvent.VK_CONTROL);
							robot.keyRelease(KeyEvent.VK_A);
							demo.simulateClipBoard(filename,robot);
							Thread.sleep(2000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(1000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(1000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(1000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(1000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(1000);
							robot.keyPress(KeyEvent.VK_TAB);
							Thread.sleep(2000);
							robot.keyPress(KeyEvent.VK_CONTROL);
							robot.keyPress(KeyEvent.VK_A);
							robot.keyRelease(KeyEvent.VK_CONTROL);
							robot.keyRelease(KeyEvent.VK_A);
							demo.simulateClipBoard(Path,robot);
							if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_GENERATE_FILE.getValue())).getSize()!=null)
							{
								driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_GENERATE_FILE.getValue())).click();

							}
							try {

								if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EXCEL_DOWNLOAD_REMEMBER.getValue())).getSize()!=null)

								{
									driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EXCEL_DOWNLOAD_REMEMBER.getValue())).click();
									Thread.sleep(5000);
								}
							}
							catch(Exception exe)
							{
								exe.getMessage();
							}
							if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EXCEL_DOWNLOAD_ALLOW.getValue())).getSize()!=null)

							{
								driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.SAPLOGON_EXCEL_DOWNLOAD_ALLOW.getValue())).click();
								Thread.sleep(5000);
							}

							//}
						}






					}
					catch(Exception e2)
					{
						e2.getMessage();
					}

				} 
			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.close();


		}
	}



	public void simulateClipBoard(String tobeCopied,Robot robot)
	{
		try {
			StringSelection stringSelection=null;

			stringSelection=new StringSelection(tobeCopied);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(3000);




		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String generateRandKey()
	{
		Date date=new Date();
		DateFormat df= new SimpleDateFormat("ddMMYYYYHHmmss");

		//Random rand=new Random();
		String randkey=df.format(date)+"BQ";

		System.out.println("Random key"+randkey);
		return randkey;



	}

	public int readExcelData(String FilePath)
	{

		int totalRowNumber = 0;
		try{




			//file = new FileInputStream(new File(FILE_PATH));
			System.out.println(" ******** Obtained file");
			XSSFWorkbook workbook;
			System.out.println(" ******** Inside read file");

			workbook = new XSSFWorkbook(new FileInputStream(new File(FilePath).getAbsolutePath()));
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(" ******** Inside sheet file"+sheet);
			int lastCellNo = sheet.getRow(0).getLastCellNum();
			System.out.println("****************lastcellno" + lastCellNo);
			totalRowNumber = sheet.getLastRowNum();
			System.out.println("****************roww" + totalRowNumber);


			if(totalRowNumber>=7)
			{



				for (int i = 7; i <= totalRowNumber; i++) {
					try{



						String key="";
						DateFormat df= new SimpleDateFormat("YYYYMMdd");
						Date date=new Date();
						String todayDate=df.format(date);



						System.out.println("****************i value" + i
								+ sheet.getPhysicalNumberOfRows());



						//*************************Unique Key Input****************************


						key=generateRandKey()+i;

						try
						{


							sheet.getRow(i).getCell(1).setCellValue(key);

						}
						catch(Exception e)
						{
							sheet.getRow(i).createCell(1).setCellValue(key);

						}

						//***********************Available From***************************




						System.out.println("Available frm:::"+todayDate);
						try
						{


							sheet.getRow(i).getCell(78).setCellValue(todayDate);

						}
						catch(Exception e)
						{
							sheet.getRow(i).createCell(78).setCellValue(todayDate);

						}


					}
					catch(Exception e)
					{
						e.printStackTrace();
					}

				}
				FileOutputStream outputStream = new FileOutputStream(FilePath);
				workbook.write(outputStream);
				workbook.close();
				outputStream.close();
			}

		}



		catch (Exception e) {
			System.out.println("****************In Exception2 ::"+e.getMessage());
			e.printStackTrace();

		}

		return totalRowNumber;

	}
}

