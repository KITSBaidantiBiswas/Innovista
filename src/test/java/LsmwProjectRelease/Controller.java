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
import java.lang.reflect.InvocationTargetException;
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

public class Controller {
	static String opco1;
	static String env1;

	public Controller(String opco, String env) {
		
		opco=opco1;
		env=env1;
		System.out.println("the values are"+opco+env);
		
	}

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		opco1=args[0].toUpperCase();
		System.out.println("opco::"+opco1);
		env1=args[1].toUpperCase();
		System.out.println("env::"+env1);
		String type=args[2];
		String className="";
		String classPath="LsmwProjectRelease.";
		if(type.length()>0)
		{
			 className="LsmwProject"+type;
			 classPath=classPath+className;
			 try {
			
			 
			 Class<?> refer = Class.forName(classPath);
				 Object o=refer.newInstance();
				 Method[] method=refer.getDeclaredMethods();
				 for(Method m:method)
				 {
					 if(m.getName().startsWith("Release"))
					 {
						 System.out.println("Method Name");
						 m.invoke(o,opco1,env1);
						 break;
					 }
				 }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
			 
		
			
			
		}
/*		{
			LSMWProjectRelease release=new LSMWProjectRelease();

			release.Release(opco,env);
		}
		else if(type.equals("negative"))
		{
			LSMWProjectReleaseNegative negative=new LSMWProjectReleaseNegative();
			negative.Release(opco,env);
		}
		else if(type.equals("auc"))
		{
			LsmwProjectAucUpload auc=new LsmwProjectAucUpload();
			auc.Release(opco, env);
		}
		else
		{
			LsmwProjectAucUploadNegative aucnegative=new LsmwProjectAucUploadNegative();
			aucnegative.Release(opco, env);
		}*/

	}
}

