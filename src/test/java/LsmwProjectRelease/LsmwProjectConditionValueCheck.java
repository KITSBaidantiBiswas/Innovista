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
import java.lang.reflect.Method;
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

public class LsmwProjectConditionValueCheck {

	public  void Execute(String opco,String env,String conditionType) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml
		ReportGeneration report=new ReportGeneration();
		String column1[]={"Scenario Name","Project No","Status"};
		String comment="";
		String status="FAIL";
		String relStatus="";
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
			//rtt.exec("C:\\downloads\\Winium.Desktop.Driver.exe");

		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}///*****************************************Start Driver**************************************



		int rowNo=0;


		LsmwProjectConditionValueCheck demo=new LsmwProjectConditionValueCheck();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();

		Map<String,String> map=logon.valueSetter(opco, env) ;

		String projectName="";
		File file=new File(System.getProperty("user.dir")+"\\"+logon.conditionTableSetter(conditionType.toUpperCase()));
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
				Thread.sleep(30000);
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

					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_ZPUR001.getValue(),robot);

					robot.keyPress(KeyEvent.VK_ENTER);


					Thread.sleep(6000);
					//*************Press Tab*********

					demo.modifyBeforeUpload(logon.selectFile(conditionType.toUpperCase()));
					demo.Upload(driver);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F4);
					Thread.sleep(3000);
					pressDown(robot, Integer.parseInt(logon.conditionSetter(conditionType)));
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F4);
					Thread.sleep(3000);
					pressDown(robot, Integer.parseInt(logon.conditionTableSetter(conditionType)));
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(4000);
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					Thread.sleep(4000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F4);
					Thread.sleep(3000);
					pressDown(robot, Integer.parseInt(logon.conditionSetter(conditionType.toUpperCase())));
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F4);
					Thread.sleep(3000);
					pressDown(robot, Integer.parseInt(logon.conditionTableSetter(conditionType)));
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					pressTab(robot,1);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_SPACE);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_F8);
					Thread.sleep(6000);
					driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_ALLOW.getValue())).click();
					Thread.sleep(6000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.CONDITION_TYPE_TCODE.getValue(),robot);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(3000);
					demo.simulateClipBoard(conditionType.toUpperCase(),robot);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(3000);
					robot.keyPress(KeyEvent.VK_ENTER);
					Thread.sleep(2000);

					Class<?> refer = Class.forName("LsmwProjectRelease.LsmwProjectConditionValueCheck");
					Object o=refer.newInstance();
					Method[] method=refer.getDeclaredMethods();
					for(Method m:method)
					{
						if(m.getName().equals(logon.processCondition(conditionType)))
						{
						
							System.out.println("yupppp");
								m.invoke(o,robot,demo);
							}
							
						}
				//	demo.processConditiontype(robot,demo);
					Thread.sleep(6000);
					status="PASS";
					
					driver.close();

				
				}

			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}


			String reportData1[]={"Tac Condition Type Value Check for "+conditionType.toUpperCase(),projectName,status};

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
	
	public void processConditiontypeZ1IN(Robot robot,LsmwProjectConditionValueCheck demo)
	{
		try{
			
		
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_PUR_ORGANIZATION.getValue(),robot);
		pressTab(robot, 1);
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_SITE.getValue(),robot);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_F8);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void processConditiontype(Robot robot,LsmwProjectConditionValueCheck demo)
	{
		try{
			
		
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_PUR_ORGANIZATION.getValue(),robot);
		pressTab(robot, 1);
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_INCOTERMS.getValue(),robot);
		pressTab(robot, 1);
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_INCO2.getValue(),robot);
		pressTab(robot, 1);
		demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.TAC_SITE.getValue(),robot);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_F8);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void modifyBeforeUpload(String fileName)
	{
		System.out.println("filename"+fileName);

		try {
			String formedDate="";

			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");  
			Date date = new Date();  
			formedDate=formatter.format(date);
			System.out.println(formedDate);  

			File file1 = new File(System.getProperty("user.dir")+"\\"+fileName);
			System.out.println("the upload file:"+file1);
			BufferedReader reader = new BufferedReader(new FileReader(file1));
			String line = "", oldtext ="";
			while ((line = reader.readLine()) != null) {

				//oldtext += line + "\n"+"\n";
				oldtext += line + "\n";
			}
			reader.close();


			String newtext = oldtext.replace("$x",formedDate);


			System.out.println(newtext);

			FileWriter writer = new FileWriter(
					System.getProperty("user.dir")+"\\Reformed_Condition.txt");

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



			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(2000);

			this.simulateClipBoard(System.getProperty("user.dir")+"\\Reformed_Condition.txt",robot);
			try {
				String processName = "notepad.exe";
				if (isProcessRunning(processName)) {

					killProcess(processName);
				}
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	
	public static void pressDown(Robot robot,int count)
	{
		for(int i=1;i<=count;i++)
		{
			robot.keyPress(KeyEvent.VK_DOWN);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}

