package LsmwProjectRelease;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import LsmwProjectRelease.ConstantForSapLogon;

public class LsmwProjectVendorMemo {

	public  void Execute(String opco,String env) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml
		ReportGeneration report=new ReportGeneration();
		String column1[]={"Scenario Name","Document Number","Validated","Status"};
		String comment="";
		String status="FAIL";
		String relStatus="";
		String documentNo="";
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
		//rtt.exec("C:\\downloads\\ZenEssential-ZenEssential\\POI\\Winium.Desktop.Driver.exe");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	
	//*****************************************Start Driver**************************************

	
		
		int rowNo=0;


		LsmwProjectVendorMemo demo=new LsmwProjectVendorMemo();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();

		Map<String,String> map=logon.valueSetter(opco, env) ;

		String fileToUpload=System.getProperty("user.dir")+"\\Vendor_memo.csv";
		//String fileToBeUploaded=System.getProperty("user.dir")+"\\New_File_Upload.csv";
		File file=new File(System.getProperty("user.dir")+"\\New_File_Upload.csv");
		try
		{

			if(file.exists())
			{	

				if(file.delete())
				{
					System.out.println("CSV FILE DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		File file1=new File(System.getProperty("user.dir")+"\\Result\\Report.xlsx");
		try
		{

			if(file1.exists())
			{	

				if(file1.delete())
				{
					System.out.println("Excel FILE DELETED");
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		File file8=new File(System.getProperty("user.dir")+"\\Result\\document.txt");
		try
		{

			if(file8.exists())
			{	

				if(file8.delete())
				{
					System.out.println("TXT FILE DELETED");
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

				Thread.sleep(3000);




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

				Thread.sleep(10000);


				if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize()!=null)
				{
					System.out.println("Got itttt");
					System.out.println("size2:::::"+driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())).getSize());

					Actions action=new Actions(driver);
					action.moveToElement(driver.findElement(By.className(ConstantForSapLogon.ConstantForSap.LSMW_EDIT.getValue())));
                    ////////////////////////////////////////////////////////////////////////////////////////////////
					
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_FICO_JOURNAL.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);


					Thread.sleep(6000);
				
					demo.modifyBeforeUpload(fileToUpload);

					documentNo=demo.UploadJournal(driver);
					System.out.println("The Document no.."+documentNo);
					
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(3000);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SAPLOGON_TCODE.getValue(),robot);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					demo.simulateClipBoard(documentNo,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					demo.simulateClipBoard("2018",robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					status="PASS";
					
					
					
					
			
				

			
			}
				
				
			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			
			
			String reportData1[]={"Lsmw Vendor Invoice Upload",documentNo,"The Document posted successfully",status};

			report.generateReport(1,column1,reportData1,"Report.xlsx");



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

	public void modifyBeforeUpload(String fileToUpdate)
	{
              System.out.println("going to modify the csv");
		try {
			
			CSVoperaion.updateCSV(fileToUpdate);
			
		} catch (Exception ioe) {
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

	public String UploadJournal(WiniumDriver driver)
	{
		String extractDocumentNo="";


		try {
			Robot robot=new Robot();




					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_A);
					Thread.sleep(2000);

					this.simulateClipBoard(System.getProperty("user.dir")+"\\New_File_Upload.csv",robot);
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
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					}
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(3000);
					
					
					
					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
						Thread.sleep(3000);
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					}
					//Thread.sleep(2000);
					
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_F3);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(3000);
					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
						Thread.sleep(3000);
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					}
					Thread.sleep(4000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F9);
					robot.keyRelease(KeyEvent.VK_F9);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(4000);
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
					String Path=System.getProperty("user.dir")+"\\Result\\";
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_A);
					String filename="document.txt";
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
					
					//selecting the assign files
					
					File file2 = new File(System.getProperty("user.dir")+"\\Result\\"+filename);
					System.out.println("the file:"+file2);

					BufferedReader reader = new BufferedReader(new FileReader(file2));
					String line = "", oldtext ="";
					while ((line = reader.readLine()) != null) {

						//oldtext += line + "\n"+"\n";
						oldtext += line;

					}
					System.out.println("the complete text::"+oldtext);
					String Splitter1=": ";
					String remainingtext=oldtext.split(Splitter1)[1];
					extractDocumentNo=remainingtext.split("\\s+")[0];
					System.out.println("the date::"+extractDocumentNo);
					reader.close();



			
		}


		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

           return extractDocumentNo;

	}



	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);


	}
	
	public static void pressTab(Robot robot,int count)
	{
		for(int i=1;i<=count;i++)
		{
		robot.keyPress(KeyEvent.VK_TAB);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}

