package LsmwVatUpdate;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class LSMWProjectCreation {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml
		ReportGeneration report=new ReportGeneration();
		String column1[]={"Scenario Name","Invoice Type","Document No","Validation","Status"};
		String comment="";
		String status="FAIL";
		try {
			String processName = "notepad.exe";
			if (isProcessRunning(processName)) {

				killProcess(processName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Runtime rtt=Runtime.getRuntime();
		try {

			rtt.exec(System.getProperty("user.dir")+"\\Winium.Desktop.Driver.exe");
			//"C:\\downloads\\Winium.Desktop.Driver\\Winium.Desktop.Driver.exe");

		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}///*****************************************Start Driver**************************************

		String opco=args[0].toUpperCase();
		System.out.println("opco::"+opco);
		String env=args[1].toUpperCase();
		System.out.println("env::"+env);
		String invoiceType=args[2];
		System.out.println("env::"+invoiceType);
		int rowNo=0;


		LSMWProjectCreation demo=new LSMWProjectCreation();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();

		Map<String,String> map=logon.valueSetter(opco, env, invoiceType) ;

		/*String FilePath=System.getProperty("user.dir")+map.get("LSMW_EXCELNAME");
		System.out.println("Excel file path::"+FilePath);*/
		//*********************************Retrieve Total Row No**************************************************

		//rowNo=demo.readExcelData(FilePath);

		File file=new File(System.getProperty("user.dir")+"\\Result\\BeforeTaxDatechange.txt");
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
		File file3=new File(System.getProperty("user.dir")+"\\Result\\AfterTaxDatechange.txt");
		try
		{

			if(file3.exists())
			{	

				if(file3.delete())
				{
					System.out.println("TEXT FILE of after Tax date  DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}

		File file1=new File(System.getProperty("user.dir")+"\\Test_File_New1.txt");
		try
		{

			if(file1.exists())
			{	

				if(file1.delete())
				{
					System.out.println(" New modified TEXT FILE DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}

		////Text file preparation///////////

		//String ProjectName= demo.extractProjectNumber(); 
		// System.out.println("The project name is::"+ProjectName);
		WiniumDriver driver=null;

		DesktopOptions option=new DesktopOptions();
		String path=ConstantForSapLogon.ConstantForSap.SAP_LOGON_PATH.getValue();
		option.setApplicationPath(path);
		option.setDebugConnectToRunningApp(false);
		option.setLaunchDelay(2);

		driver=new WiniumDriver(new URL(ConstantForSapLogon.ConstantForSap.DESKTOP_DRIVER_URL.getValue()), option);
		Thread.sleep(5000);
		if(driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_TITLE.getValue())).getSize()!=null)
		{
			WebElement e=driver.findElement(By.name(map.get("LSMW_SERVER")));


			Thread.sleep(1000);
			e.click();

			driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_LOGIN.getValue())).click();
			Thread.sleep(6000);




			try {
				Robot robot=new Robot();
				System.out.println("*****"+map.get("LSMW_USERID"));

				demo.simulateClipBoard(map.get("LSMW_USERID"),robot);



				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				Thread.sleep(10000);




				demo.simulateClipBoard(map.get("LSMW_PASSWORD"),robot);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				try{



					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_MULTIPLE_USER.getValue())).getSize()!=null)
					{
						System.out.println("size1:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_MULTIPLE_USER.getValue())).getSize());
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_TERMINATE_LOGON.getValue())).click();
					}
				}
				catch(Exception e1)
				{
					System.out.println("In multi exception::");

					e1.getMessage();
				}



				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize()!=null)
				{
					System.out.println("Got itttt");
					System.out.println("size2:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize());

					Actions action=new Actions(driver);
					action.moveToElement(driver.findElement(By.className(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())));



					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SAPLOGON_TCODE.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);


					Thread.sleep(6000);

					demo.simulateClipBoard(map.get("LSMW_PROJECT"),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SUBPROJECT.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_OBJECT.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(10000);
					String filename="BeforeTaxDatechange.txt";
					String oldTaxDate=demo.performTaxInfoExtraction(driver, filename);
					System.out.println("The old tax date::"+oldTaxDate);
					demo.changeTaxDate(oldTaxDate,invoiceType);

					demo.Upload(driver);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);


					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SAPLOGON_TCODE.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);


					/*demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_PROJECT.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SUBPROJECT.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_OBJECT.getValue(),robot);*/
					
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(10000);
					String filename1="AfterTaxDatechange.txt";
					String changedTaxDate=demo.performTaxInfoExtraction(driver, filename1);
					if(!changedTaxDate.equals(oldTaxDate))
					{
						System.out.println("Date changed::"+oldTaxDate+"from"+changedTaxDate);
						
						comment="Date changed::"+oldTaxDate+"from"+changedTaxDate;
						status="PASS";
					}
					else
					{
						comment="Date did not change::"+oldTaxDate+"from"+changedTaxDate;
						status="FAIL";
					}
					


					


				} 
			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			String reportData1[]={"VAT Update",invoiceType,map.get("LSMW_PROJECT"),comment,status};

			report.generateReport(1,column1, reportData1, "Report.xlsx");



		}
		driver.close();


		//System.out.print(isProcessRunning(processName));

		try {
			String processName = "Winium.Desktop.Driver.exe";
			if (isProcessRunning(processName)) {

				killProcess(processName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeTaxDate(String dt,String type)
	{

		try {
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));
				c.add(Calendar.DATE, 1);  // number of days to add
				dt = sdf.format(c.getTime()); 

				System.out.println("***"+dt);
				
			} catch (ParseException e) {

				e.printStackTrace();
			}




			File file1 = new File(System.getProperty("user.dir")+"\\"+type+"_Invoice_Document_LSMW.txt");
			System.out.println("the upload file:"+file1);
			BufferedReader reader = new BufferedReader(new FileReader(file1));
			String line = "", oldtext ="";
			while ((line = reader.readLine()) != null) {

				//oldtext += line + "\n"+"\n";
				oldtext += line + "\n";
			}
			reader.close();


			String newtext = oldtext.replace("$x",dt);


			System.out.println(newtext);

			FileWriter writer = new FileWriter(
					System.getProperty("user.dir")+"\\Test_File_New1.txt");

			writer.write(newtext);
			writer.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}



	public void simulateClipBoard(String tobeCopied, Robot robot) {
		try {
			StringSelection stringSelection = null;

			stringSelection = new StringSelection(tobeCopied);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String generateRandKey() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MMHHmmss");

		// Random rand=new Random();
		String randkey = "CP01MA"+df.format(date) ;

		System.out.println("Random key" + randkey);
		return randkey;

	}

	public String performTaxInfoExtraction(WiniumDriver driver,String filename)
	{
		String extractDate="";
		try 
		{
			Robot robot=new Robot();
			System.out.println("going to perform alt");
			driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_SAPLOGON_TCODE.getValue())).click();
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_ALT);
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			System.out.println("Hiiiiiiii");

			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_F9);
			Thread.sleep(3000);
			try
			{
				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_FILE_SAVE_CONTINUE.getValue())).getSize()!=null)
				{
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_FILE_SAVE_CONTINUE.getValue())).click();
					Thread.sleep(3000);
				}
			}
			catch(Exception ec)
			{
				ec.printStackTrace();
			}
			//String filename="BeforeTaxDatechange.txt";
			String Path=System.getProperty("user.dir")+"\\Result\\";
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			this.simulateClipBoard(filename,robot);
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
			this.simulateClipBoard(Path,robot);
			Thread.sleep(2000);
			try
			{
				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_GENERATE_FILE.getValue())).getSize()!=null)
				{
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_GENERATE_FILE.getValue())).click();
					Thread.sleep(3000);
				}
			}
			catch(Exception ec)
			{
				ec.printStackTrace();
			}

			try
			{
				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).getSize()!=null)
				{
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					Thread.sleep(3000);
				}
			}
			catch(Exception ec)
			{
				ec.printStackTrace();
			}
			Thread.sleep(3000);




	

			File file2 = new File(System.getProperty("user.dir")+"\\Result\\"+filename);
			System.out.println("the file:"+file2);

			BufferedReader reader = new BufferedReader(new FileReader(file2));
			String line = "", oldtext ="";
			while ((line = reader.readLine()) != null) {

				//oldtext += line + "\n"+"\n";
				oldtext += line;

			}
			System.out.println("the complete text::"+oldtext);
			String Splitter1="Tax Report Date ";
			String remainingtext=oldtext.split(Splitter1)[1];
			extractDate=remainingtext.split("\\s+")[0];
			System.out.println("the date::"+extractDate);
			reader.close();


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return extractDate;

	}



	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public static boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;

	}

	public void Upload(WiniumDriver driver)
	{



		try {
			Robot robot=new Robot();


			if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize()!=null)
			{
				System.out.println("Got itttt");
				System.out.println("size2:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize());

				Actions action=new Actions(driver);
				action.moveToElement(driver.findElement(By.className(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())));



				this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_TCODE.getValue(),robot);

				robot.keyPress(KeyEvent.VK_ENTER);


				Thread.sleep(6000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
				this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_PROJECT_LSMW.getValue(),robot);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
				this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SUBPROJECT_LSMW.getValue(),robot);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
				this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_OBJECT_LSMW.getValue(),robot);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_USERMENU.getValue())).getSize()!=null)
				{

					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);

					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(5000);
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_DISPLAYCHANGE.getValue())).click();
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);	
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);	
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(5000);


					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_A);
					Thread.sleep(2000);

					this.simulateClipBoard(System.getProperty("user.dir")+"\\Test_File_New1.txt",robot);
					try {
						String processName = "notepad.exe";
						if (isProcessRunning(processName)) {

							killProcess(processName);
						}
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}



					Thread.sleep(5000);

					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).click();
					Thread.sleep(5000);

					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(3000);

					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_DATASAVED.getValue())).getSize()!=null)
					{
						robot.keyPress(KeyEvent.VK_F3);
						Thread.sleep(3000);
					}
					//selecting the assign files
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(5000);
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_DISPLAYCHANGE.getValue())).click();
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_CONTROL);

					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_DATASAVED.getValue())).getSize()!=null)
					{
						robot.keyPress(KeyEvent.VK_F3);
						Thread.sleep(3000);
					}
					//Read Data
					System.out.println("Read data.........");
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(4000);
					robot.keyPress(KeyEvent.VK_F8);
					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(5000);

					//Display Read Data
					System.out.println("Display read data");
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);

					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(3000);
					//Convert Data
					System.out.println("convert data");
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(5000);
					//Display Converted Data
					System.out.println("Display converted data");
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(2000);
					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}

					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(3000);

					//Create Batch Input session
					System.out.println("Create Batch Input session");

					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(3000);
					Thread.sleep(2000);
					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CONTINUE.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}

					//Run Batch Input session
					System.out.println("Run Batch Input session");

					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(3000);
					Date date = new Date();  
					SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");  
					String strDate= formatter.format(date);  
					System.out.println(strDate);  
					this.simulateClipBoard(strDate,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					this.simulateClipBoard(strDate,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_A);
					Thread.sleep(2000);
					this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_USERID_FRZ.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(3000);
					try{	


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_PROCESS.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_PROCESS.getValue())).click();
							Thread.sleep(6000);
							System.out.println("Starting 4 times....");
							driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
							Thread.sleep(4000);
							driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
							Thread.sleep(4000);
							driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
							Thread.sleep(4000);
							driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
							
							System.out.println("Ending 4 times....");

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}

					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);

					try{


						if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_SESSION_OVERVIEW.getValue())).getSize()!=null)
						{
							driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_SESSION_OVERVIEW.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}

					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_LOG.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_LOG.getValue())).click();
						Thread.sleep(4000);

					}
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(3000);
					Date date1 = new Date();  
					SimpleDateFormat formatt = new SimpleDateFormat("dd.MM.yyyy");  
					String strDate1= formatt.format(date1);  
					System.out.println(strDate1);  
					this.simulateClipBoard(strDate1,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					this.simulateClipBoard(strDate1,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_A);
					Thread.sleep(2000);
					this.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_USERID_FRZ.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);

					robot.keyPress(KeyEvent.VK_F7);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);

					robot.keyPress(KeyEvent.VK_F2);
					Thread.sleep(6000);



				}



				Thread.sleep(3000);


			} 
		}


		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}






		//System.out.print(isProcessRunning(processName));




	}



	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);


	}
}

