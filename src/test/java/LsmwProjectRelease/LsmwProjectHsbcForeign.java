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
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import LsmwProjectRelease.ConstantForSapLogon;

public class LsmwProjectHsbcForeign {

	public  void Execute(String opco,String env) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml
		ReportGeneration report=new ReportGeneration();
		String column1[]={"Scenario Name","Vendor Invoice No","Sap Project Status","Validated","Status"};
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

		    //rtt.exec(System.getProperty("user.dir")+"\\Winium.Desktop.Driver.exe");
			//rtt.exec("C:\\downloads\\ZenEssential-ZenEssential\\POI\\Winium.Desktop.Driver.exe");
			rtt.exec("C:\\downloads\\Winium.Desktop.Driver.exe");

			

		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}///*****************************************Start Driver**************************************

	
		
		int rowNo=0;


		LsmwProjectHsbcForeign demo=new LsmwProjectHsbcForeign();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();

		Map<String,String> map=logon.valueSetter(opco, env) ;

		String projectName="";
		File file=new File(System.getProperty("user.dir")+"\\Test_File_New1.txt");
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
		//selenium
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","C:\\downloads\\ZenEssential-ZenEssential\\POI\\chromedriver.exe");

		WebDriver chrome= new ChromeDriver();

		chrome.manage().window().maximize();
		chrome.navigate().to(ConstantForSapLogon.ConstantForSap.SAP_FRZ_URL.getValue());
		Thread.sleep(4000);

		if (chrome.findElement(By.xpath("//*[@id='sap-user']")).getSize()!=null) {


		chrome.findElement(By.xpath("//*[@id='sap-user']")).sendKeys(ConstantForSapLogon.ConstantForSap.LSMW_USERID_FRZ.getValue());
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//*[@id='sap-password']")).sendKeys(ConstantForSapLogon.ConstantForSap.LSMW_PASSWORD_FRZ.getValue());

		chrome.findElement(By.xpath("//*[@id='LOGON_BUTTON-txt']/span")).click();
		Thread.sleep(2000);

		try {



		if (chrome.findElement(By.xpath("//*[@id='SESSION_QUERY_CONTINUE_BUTTON-txt']/span")).getSize()!=null) {

		chrome.findElement(By.xpath("//*[@id='SESSION_QUERY_CONTINUE_BUTTON-txt']/span")).click();

		Thread.sleep(4000);
		}
		}
		catch(Exception exc)
		{
		exc.printStackTrace();
		}

		chrome.switchTo().frame(0);
		Thread.sleep(2000);
		chrome.switchTo().frame("ITSFRAME1");
		System.out.println("***********Switched Frame ITSFRAME1*********");
		Thread.sleep(6000);

		if (chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).getSize()!=null) {

		chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys("/nfb60");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys(Keys.ENTER);
		}

		// getDriver().findElement(By.xpath("//*[@name='ToolbarOkCode']")).sendKeys(Keys.);

		Thread.sleep(7000);
		if (chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::0:16']")).getSize()!=null)
		{
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::0:16']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_VENDOR_VALUE_FOREIGN_HSBC.getValue());
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::0:16']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		System.out.println("Vendor Number Entered");

		}

		if (chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:16']")).getSize()!=null)
		{
			
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			DateFormat timeSpan = new SimpleDateFormat("mmss");
			Date date = new Date();
			Date now = new Date();
			String todayDate=dateFormat.format(date);
			String executionTime=dateFormat.format(now);
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:16']")).sendKeys(todayDate);
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:16']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("System Date Entered");
		}

		if (chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:47']")).getSize()!=null)
		{
		//Random number has to be given here chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:47']")).sendKeys(dateFormat.format(date));
		Random rand = new Random();

		int n = rand.nextInt(50) + 1;
		String str = Integer.toString(n);
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:47']")).sendKeys(str);

		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::1:47']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Reference Text Entered");
		}

		if (chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::4:16']")).getSize()!=null)
		{
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::4:16']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_AMOUNT_VALUE.getValue());

		Thread.sleep(5000);
		System.out.println("Amount Entered");
		}
		
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::4:36']")).clear();
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::4:36']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_CURRENCY_KEY_FOREIGN.getValue());
		Thread.sleep(3000);
        System.out.println("Currency Key Entered");
		

		if (chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,2]')]")).getSize()!=null)
		{
		chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,2]')]")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_GL_ACCOUNT_VALUE.getValue());   
		Thread.sleep(5000);
		System.out.println("GL Account Value Entered");
		}

		if (chrome.findElement(By.xpath("//div[@id='M0:U:4:1_hscroll-Nxt']")).getSize()!=null)
		{
		chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,5]')]")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_AMMOUNT_IN_DOC_VALUE.getValue());
		Thread.sleep(3000);
		System.out.println("Amount in Document Value Entered");

		for (int i=0;i<=24;i++){
		chrome.findElement(By.xpath("//div[@id='M0:U:4:1_hscroll-Nxt']")).click();
		}



		chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,28]')]")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_PROFIT_CENTER_VALUE.getValue());   

		Thread.sleep(5000);
		System.out.println("Profit Center Value Entered");
		chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,28]')]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		chrome.findElement(By.xpath("//input[starts-with(@id,'tbl')][contains(@id,'[1,2]')]")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		System.out.println("***********NBANK_PAYMENT_TAB*********");
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//table[@id='M0:U:1:2B264']")).click();
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::6:16']")).click();
		Thread.sleep(5000);
		chrome.findElement(By.xpath("//input[@id='M0:U:1:2B264::6:16']")).sendKeys(Keys.PAGE_UP);
		Thread.sleep(5000);
		System.out.println("***********PAGE UP*********");
		chrome.findElement(By.xpath("//div[@id='M0:U:1::0:1']")).click();
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys(Keys.ENTER);
		System.out.println("**********1st enter*********");
		Thread.sleep(5000);

		}else{
			System.out.println("not find element ");
		}

		if (chrome.findElement(By.xpath("//*[@id='M0:U:1:2B265::4:11']")).getSize()!=null)
		{
		chrome.findElement(By.xpath("//*[@id='M0:U:1:2B265::4:11']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_PMT_METHOD_VALUE_FOREIGN.getValue());   
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//*[@id='M0:U:1:2B265::6:49']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_HOUSE_BANK1_VALUE_HSBC.getValue());  
		Thread.sleep(3000);
		chrome.findElement(By.xpath("//*[@id='M0:U:1:2B265::6:57']")).sendKeys(ConstantForSapLogon.ConstantForSap.NBANK_HOUSE_BANK1_VALUE_HSBC.getValue());   
		Thread.sleep(5000);
		chrome.findElement(By.xpath("//*[@id='M0:D:10::btn[11]']")).click();
		Thread.sleep(5000);
		chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys(Keys.ENTER);
		System.out.println("**********1st enter*********");
		Thread.sleep(5000);

		}

		if (chrome.findElement(By.xpath("//span[@id='wnd[0]/sbar_msg-txt']")).getSize()!=null)
		{
		System.out.println("***********S1*********"+chrome.findElement(By.xpath("//span[@id='wnd[0]/sbar_msg-txt']")).getText());
		documentNo=chrome.findElement(By.xpath("//span[@id='wnd[0]/sbar_msg-txt']")).getText().split("\\s+")[1];
		System.out.println("***********S*********"+documentNo);

		
			System.out.println("Check the Vendor No in /nfb03 page");
			chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys("/nfb03");
			Thread.sleep(5000);
			chrome.findElement(By.xpath("//*[@id='M0:D:10::okcd']")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

            chrome.findElement(By.xpath("//input[@id='M0:U:::2:25']")).clear();
            chrome.findElement(By.xpath("//input[@id='M0:U:::2:25']")).sendKeys(documentNo);
			Thread.sleep(3000);
            chrome.findElement(By.xpath("//input[@id='M0:U:::2:25']")).sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			//if (chrome.findElement(By.xpath("(//input[@class='lsTblEdf3 lsTblEdf3NoEllipsis urBorderBox lsControl--explicitwidth lsField__input'])[8]")).getText()==ConstantForSapLogon.ConstantForSap.VENDOR_NUMBER_FIELD.getValue())
					  System.out.println("Double Click on the Vendor Box");
					  Thread.sleep(2000);
				       Actions action = new Actions(chrome);
				       action.doubleClick(chrome.findElement(By.xpath("(//input[@class='lsTblEdf3 lsTblEdf3NoEllipsis urBorderBox lsControl--explicitwidth lsField__input'])[8]")));
				       action.perform();
					   Thread.sleep(5000);
					   if (chrome.findElement(By.xpath("//input[@title='Amount Eligible for Cash Discount in Document Currency']")).isDisplayed())
					   {
						   chrome.findElement(By.xpath("//input[@title='Amount Eligible for Cash Discount in Document Currency']")).sendKeys(Keys.F8);
						   Thread.sleep(3000);
                           chrome.switchTo().defaultContent();
                           Thread.sleep(2000);
						   chrome.switchTo().frame("URLSPW-0");
						   Thread.sleep(2000);
						   System.out.println("Switched to Frame URLSW-0");
						   Thread.sleep(3000);
					         
							  System.out.println("NBANK_AMOUNT_VALUE::::"+chrome.findElement(By.xpath("//input[@id='M1:U:::8:17']")).getText());
							  Thread.sleep(3000);
							  String S3=chrome.findElement(By.xpath("//*[@id='M1:U:::0:17']")).getAttribute("value");
							  System.out.println(S3);
							  Thread.sleep(3000);
							  System.out.println("succcesfful::::");
							  Thread.sleep(3000);
							 

						  }else{
							  System.out.println("unsucccesfful::::");
						  }
						   

					
					   

					}
          

			
		}
		
		
		
		
		

		chrome.close();

      
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
					
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.F110_TCODE.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.F110_TCODE.getValue(),robot);

					Thread.sleep(6000);
					//*************Press Tab*********
					demo.simulateClipBoard(getsysdate(),robot);
					pressTab(robot,1);
					Thread.sleep(2000);
					Random rand = new Random();

			        int n = rand.nextInt(50) + 1;
			        String str = Integer.toString(n);
					demo.simulateClipBoard("T"+str,robot);
					pressTab(robot,1);
					robot.keyPress(KeyEvent.VK_RIGHT);
					Thread.sleep(2000);
					
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(5000);
					pressTab(robot,4);
					demo.simulateClipBoard("CP01",robot);
					Thread.sleep(2000);
					pressTab(robot,1);
					Thread.sleep(2000);
					demo.simulateClipBoard("P",robot);
					Thread.sleep(2000);
					pressTab(robot,1);
					Thread.sleep(2000);
					demo.simulateClipBoard("31.03.2019",robot);
					Thread.sleep(2000);
					pressTab(robot,7);
					Thread.sleep(2000);
					demo.simulateClipBoard("509282",robot);
					Thread.sleep(2000);
					pressTab(robot,1);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					pressTab(robot,4);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_RIGHT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					pressTab(robot,2);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_UP);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F4);
					Thread.sleep(2000);
					/*robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);*/
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					//pressTab(robot,2);
					Thread.sleep(2000);
					//provide the Document no here
					demo.simulateClipBoard(documentNo,robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					pressTab(robot,2);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_RIGHT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					pressTab(robot,1);
					
					
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					
					
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					pressTab(robot,1);
					demo.simulateClipBoard("509282",robot);
					
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					pressTab(robot,2);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_RIGHT);
					Thread.sleep(2000);

					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					pressTab(robot,1);
					Thread.sleep(2000);
					demo.simulateClipBoard("PRINT",robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					pressTab(robot,3);
					Thread.sleep(2000);
					//for status tab visit and save 
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F1);
					robot.keyRelease(KeyEvent.VK_F1);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					pressTab(robot,1);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.F110_SCHEDULE.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.F110_SCHEDULE.getValue())).click();
						Thread.sleep(2000);
					}
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F2);
					robot.keyRelease(KeyEvent.VK_F2);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F7);
					Thread.sleep(2000);
					pressTab(robot,4);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(2000);
					if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.F110_SCHEDULE.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.F110_SCHEDULE.getValue())).click();
						Thread.sleep(6000);
					}
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F2);
					robot.keyRelease(KeyEvent.VK_F2);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					
					Thread.sleep(4000);
					/*robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F1);
					robot.keyRelease(KeyEvent.VK_F1);
					robot.keyRelease(KeyEvent.VK_DOWN);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_F12);*/
										

					
				
					
			}
				
				
			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			
			
			String reportData1[]={"Lsmw HSBC Foreign",documentNo,relStatus,"The project has been Released",status};

			report.generateReport(1,column1,reportData1,"Report.xlsx");



		}
		driver.close();


		//System.out.print(isProcessRunning(processName));

		try {
			String processName = "notepad.exe";
			if (isProcessRunning(processName)) {

				killProcess(processName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/*public void modifyBeforeUpload(String projectName)
	{

		try {




			File file1 = new File(System.getProperty("user.dir")+"\\project release.txt");
			System.out.println("the upload file:"+file1);
			BufferedReader reader = new BufferedReader(new FileReader(file1));
			String line = "", oldtext ="";
			while ((line = reader.readLine()) != null) {

				//oldtext += line + "\n"+"\n";
				oldtext += line + "\n";
			}
			reader.close();


			String newtext = oldtext.replace("$x",projectName);


			System.out.println(newtext);

			FileWriter writer = new FileWriter(
					System.getProperty("user.dir")+"\\Test_File_New1.txt");

			writer.write(newtext);
			
			writer.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
*/


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

	/*public String generateRandKey() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MMHHmmss");

		// Random rand=new Random();
		String randkey = "CP01MA"+df.format(date) ;

		System.out.println("Random key" + randkey);
		return randkey;

	}*/

	/*public String performTaxInfoExtraction(WiniumDriver driver,String filename)
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

*/

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
	{/*



		try {
			Robot robot=new Robot();



			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SLASH);
			robot.keyRelease(KeyEvent.VK_SLASH);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
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
		


		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}






		//System.out.print(isProcessRunning(processName));




	*/}



	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);


	}
	public static String getsysdate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		System.out.println("***********date*****"+dateFormat.format(date));
		return dateFormat.format(date);
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

