package LsmwProjectRelease;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Utility {
	
	
	
			
	
	public void procureDownload(String path1,Robot robot)
	{
		try{
			robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyPress(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_ALT);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_RIGHT);
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(5500);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_F7);
        robot.keyRelease(KeyEvent.VK_F7);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_F6);
        robot.keyRelease(KeyEvent.VK_F6);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(2000);
        simulateClipBoard(path1,robot);	
        Thread.sleep(2000);
        pressTab(robot,3);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(5000);
      
        robot.keyPress(KeyEvent.VK_ENTER);
        System.out.println("download completed");
        Thread.sleep(5000);
        
       
	}
		catch(Exception e)
		{
			e.printStackTrace();
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
