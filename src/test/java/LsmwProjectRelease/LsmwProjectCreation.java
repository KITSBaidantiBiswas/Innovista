package LsmwProjectRelease;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class LsmwProjectCreation {

	public  void Execute(String opco,String env) throws MalformedURLException, InterruptedException {
		//\\vf0013.gha.kfplc.com\Shared\Group\NWBC_Config\FullSAPUILandscape.xml

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
			rtt.exec("C:\\downloads\\Winium.Desktop.Driver.exe");

		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}///*****************************************Start Driver**************************************

		/*String opco=args[0].toUpperCase();
		System.out.println("opco::"+opco);
		String env=args[1].toUpperCase();
		System.out.println("env::"+env);*/
		int rowNo=0;


		LsmwProjectCreation demo=new LsmwProjectCreation();

		//**************************Retrieve Opco and Environment Specific Data***************************

		ConstantForSapLogon logon=new ConstantForSapLogon();

		Map<String,String> map=logon.valueSetter(opco, env) ;

		/*String FilePath=System.getProperty("user.dir")+map.get("LSMW_EXCELNAME");
		System.out.println("Excel file path::"+FilePath);*/
		//*********************************Retrieve Total Row No**************************************************

		//rowNo=demo.readExcelData(FilePath);
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

////Text file preparation///////////
		
   String ProjectName= demo.extractProjectNumber(); 
   System.out.println("The project name is::"+ProjectName);
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
					e1.printStackTrace();
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
					
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_PROJECT_UPLOAD.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_SUBPROJECT_UPLOAD.getValue(),robot);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_TAB);
					demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_OBJECT_UPLOAD.getValue(),robot);
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
						
						demo.simulateClipBoard(System.getProperty("user.dir")+"\\Test_File_New1.txt",robot);
						try {
							String processName = "notepad.exe";
							if (isProcessRunning(processName)) {

								killProcess(processName);
							}
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
						
						//demo.simulateClipBoard(System.getProperty("user.dir")+"\\TEST_01.11.2018_LSMW1",robot);
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
			  demo.simulateClipBoard(strDate,robot);
			  Thread.sleep(2000);
			  robot.keyPress(KeyEvent.VK_TAB);
			  Thread.sleep(2000);
			  demo.simulateClipBoard(strDate,robot);
			  Thread.sleep(2000);
			  robot.keyPress(KeyEvent.VK_TAB);
			  Thread.sleep(2000);
			  robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_A);
				Thread.sleep(2000);
			  demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_USERID_FRZ.getValue(),robot);
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
						System.out.println("Starting 10 times....");
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CREATE_PROJECT_CONTINUE.getValue())).click();
						Thread.sleep(4000);
						System.out.println("Ending 10 times....");
						
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
					  demo.simulateClipBoard(strDate1,robot);
					  Thread.sleep(2000);
					  robot.keyPress(KeyEvent.VK_TAB);
					  Thread.sleep(2000);
					  demo.simulateClipBoard(strDate1,robot);
					  Thread.sleep(2000);
					  robot.keyPress(KeyEvent.VK_TAB);
					  Thread.sleep(2000);
					  robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_A);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_A);
						Thread.sleep(2000);
					  demo.simulateClipBoard(ConstantForSapLogon.ConstantForSap.LSMW_USERID_FRZ.getValue(),robot);
					  Thread.sleep(2000);
					  robot.keyPress(KeyEvent.VK_ENTER);
					  Thread.sleep(2000);
			     
			     robot.keyPress(KeyEvent.VK_F7);
			     Thread.sleep(3000);
			     robot.keyPress(KeyEvent.VK_DOWN);
			     Thread.sleep(2000);
			    
			     robot.keyPress(KeyEvent.VK_F2);
			     Thread.sleep(6000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     demo.simulateClipBoard("/ncj20n",robot);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_ENTER);
			     Thread.sleep(6000);
			    /* try{
			    	 
			     
			     if(driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_SETOPTION.getValue())).getSize()!=null)
					{
						driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_SETOPTION.getValue())).click();
						Thread.sleep(4000);

					}
			     }
			     catch(Exception exc)
			     {
			    	 exc.printStackTrace();
			     }*/
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
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     Thread.sleep(2000);
			     robot.keyPress(KeyEvent.VK_ENTER);
			     Thread.sleep(2000);
			   /*  try{
			    	 		

						if(driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CJ20N_OPEN_FOLDER.getValue())).getSize()!=null)
						{
							driver.findElement(By.id(ConstantForSapLogon.ConstantForSap.LSMW_CJ20N_OPEN_FOLDER.getValue())).click();
							Thread.sleep(4000);

						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}*/
			     
			     
			     demo.simulateClipBoard(ProjectName,robot);
			    // demo.simulateClipBoard("CP01MA11162916",robot);
			     Thread.sleep(2000);
			     driver.findElement(By.name(ConstantForSapLogon.ConstantForSap.LSMW_CJ20N_OPEN.getValue())).click();
			     Thread.sleep(6000);
					}
					//notepad upload path
					//String excelPath=System.getProperty("user.dir")+map.get("LSMW_EXCELNAME");
					//System.out.println("ExcelPath::::"+excelPath);


					//demo.simulateClipBoard(excelPath,robot);

					
					Thread.sleep(3000);
					

				} 
			}
			//driver.close();

			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}



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
	
	public String extractProjectNumber()
	{
		String randkey="";
	try {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MMHHmmss");
		DateFormat df1 = new SimpleDateFormat("mmssMMHH");
		// Random rand=new Random();
		 randkey = "CP01MA"+df.format(date) ;
		//String randkey1 = "CP01MA"+df1.format(date) ;
		System.out.println("Random key" + randkey);
		//System.out.println("Random key" + randkey1);
		Map<String,String> map=new HashMap<String,String>();
        File file1 = new File(System.getProperty("user.dir")+"\\Test_File.txt");
      
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        String line = "", oldtext ="";
        while ((line = reader.readLine()) != null) {
        	
        	//oldtext += line + "\n"+"\n";
        	oldtext += line + "\n";
        }
        reader.close();
       
        // replace a word in a file
        // String newtext = oldtext.replaceAll("boy", "Love");

        // To replace a line in a file
        String newtext = oldtext.replace("$x",randkey);
        //String nwwtext = newtext.replace("$y",randkey1);
        
        System.out.println(newtext);

        FileWriter writer = new FileWriter(
        		System.getProperty("user.dir")+"\\Test_File_New1.txt");
        
        writer.write(newtext);
        writer.close();
       
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
	return randkey;
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

	/*public int readExcelData(String FilePath) {

		int totalRowNumber = 0;
		try {

			// file = new FileInputStream(new File(FILE_PATH));
			System.out.println(" ******** Obtained file");
			XSSFWorkbook workbook;
			System.out.println(" ******** Inside read file");

			workbook = new XSSFWorkbook(new FileInputStream(new File(FilePath).getAbsolutePath()));
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(" ******** Inside sheet file" + sheet);
			int lastCellNo = sheet.getRow(0).getLastCellNum();
			System.out.println("****************lastcellno" + lastCellNo);
			totalRowNumber = sheet.getLastRowNum();
			System.out.println("****************roww" + totalRowNumber);

			if (totalRowNumber >= 7) {

				for (int i = 7; i <= totalRowNumber; i++) {
					try {

						String key = "";
						DateFormat df = new SimpleDateFormat("YYYYMMdd");
						Date date = new Date();
						String todayDate = df.format(date);

						System.out.println("****************i value" + i + sheet.getPhysicalNumberOfRows());



						key = generateRandKey() + i;

						try {

							sheet.getRow(i).getCell(1).setCellValue(key);

						} catch (Exception e) {
							sheet.getRow(i).createCell(1).setCellValue(key);

						}



						System.out.println("Available frm:::" + todayDate);
						try {

							sheet.getRow(i).getCell(78).setCellValue(todayDate);

						} catch (Exception e) {
							sheet.getRow(i).createCell(78).setCellValue(todayDate);

						}

					} catch (Exception e) {
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
			System.out.println("****************In Exception2 ::" + e.getMessage());
			e.printStackTrace();

		}

		return totalRowNumber;

	}*/


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

	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);
		

	}
}

