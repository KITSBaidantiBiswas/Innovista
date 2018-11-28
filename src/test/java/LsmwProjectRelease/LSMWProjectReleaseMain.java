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

public class LSMWProjectReleaseMain {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		String opco=args[0].toUpperCase();
		System.out.println("opco::"+opco);
		String env=args[1].toUpperCase();
		System.out.println("env::"+env);
		String type=args[2];
		if(type.equals("positive"))
				{
		LSMWProjectRelease release=new LSMWProjectRelease();
		
		release.Release(opco,env);
				}
		else
		{
			LSMWProjectReleaseNegative negative=new LSMWProjectReleaseNegative();
			negative.Release(opco,env);
		}
		
	}
}

	