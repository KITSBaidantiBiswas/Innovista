package LsmwProjectRelease;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVoperaion {
	public static void updateCSV(String fileToUpdate) throws IOException {
		try{
		File file1 = new File(fileToUpdate);
		System.out.println("the upload file:"+file1);
		String new1="";
		String new2="";
		String new3="";
		String new4="";
		String date1=getsysdate();
		BufferedReader reader = new BufferedReader(new FileReader(file1));
		String line = "", oldtext ="";
		while ((line = reader.readLine()) != null) {

			//oldtext += line + "\n"+"\n";
			oldtext += line + "\n";
		}
		reader.close();
		System.out.println(oldtext);
		if(oldtext.contains("$x"))
		{
			new1=oldtext.replace("$x",date1);
		}
		System.out.println("After removing $x:::"+new1);
		if(new1.contains("$y"))
		{
			new2=new1.replace("$y",date1);
		}
		if(new2.contains("$w"))
		{
			new3=new2.replace("$w",date1);
		}
		if(new3.contains("$z"))
		{
			new4=new3.replace("$z","TEST-"+new SimpleDateFormat("HHmmss").format(new Date()));
		}
		System.out.println("the modified file**********");
		System.out.println(new4);

		FileWriter writer = new FileWriter(
				System.getProperty("user.dir")+"\\New_File_Upload.csv");

		writer.write(new4);
		
		writer.close();

	}
	
	catch (IOException ioe) {
		ioe.printStackTrace();
	}
	}
	public static String getsysdate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		System.out.println("***********date*****"+dateFormat.format(date));
		return dateFormat.format(date);
	}
	
	/*public static void main(String args[])
	{
		String fileToUpload=System.getProperty("user.dir")+"\\Upload_Journal.csv";
		String fileToBeUploaded=System.getProperty("user.dir")+"\\New_File_Upload.csv";
		try {
			CSVoperaion.updateCSV(fileToUpload);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String fileToUpload=System.getProperty("user.dir")+"\\Upload_Journal.csv";
		//String fileToBeUploaded=System.getProperty("user.dir")+"\\New_File_Upload.csv";
		try {
			CSVoperaion.updateCSV(fileToUpload);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
    

