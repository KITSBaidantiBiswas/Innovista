package LsmwProjectRelease;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class ConstantForSapLogon {

	public enum ConstantForSap {

		SAP_LOGON_PATH("C:\\Program Files (x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe"),
		DESKTOP_DRIVER_URL("http://localhost:9999"),
		LSMW_TITLE("TitleBar"),
		LSMW_LOGIN("Log On"),
		LSMW_VRZ("02.20 VRZ - ERP Pre-prod "),
		LSMW_QRZ("02.02 QRZ - ERP BAU QA "),
		LSMW_FRZ("02.12 FRZ - ERP QA "),
		LSMW_USERID_VRZ("dasank03"),
		LSMW_PASSWORD_VRZ("Kolkata@2020"),
		LSMW_USERID_QRZ("dasank03"),
		LSMW_PASSWORD_QRZ("Kolkata@2020"),
		LSMW_EDIT("Edit"),
		LSMW_TCODE("/nlsmw"),
		LSMW_PROJECT_LSMW("Z_FI_PROJ_ST"),
		LSMW_PROJECT_AUC_LSMW("Z_FI_AUC_MIGR"),
		LSMW_SUBPROJECT_AUC_LSMW("Z_FI_AUC_MIGR"),
		LSMW_SUBPROJECT_LSMW("Z_FI_PROJ_ST"),
		LSMW_OBJECT_LSMW("Z_FI_PROJ_ST"),
		LSMW_OBJECT_AUC_LSMW("Z_FI_PROJ_ST"),
		LSMW_SAPLOGON_TCODE("/nfb03"),
		LSMW_EXCELNAME_BNQ("\\BNQ ARTICLE.xlsx"),
		LSMW_EXCELNAME_CASTO("\\CASTO ARTICLE.xlsx"),
		LSMW_PURCHASEORG_BNQ("BQ10"),
		LSMW_PURCHASEORG_CASTO("CF01"),
		LSMW_EXECUTE_BUTTON("Execute"),
		LSMW_SECURITY_MODAL("Remember My Decision"),
		LSMW_SECURITY_MODAL_ALLOW("Allow"),
		LSMW_POST_CONFIRMATION("post(YES/NO)"),
		LSMW_MULTIPLE_USER("License Information for Multiple Logons"),
		LSMW_TERMINATE_LOGON("Confirm Selection"),
		LSMW_MICROSOFT_EXCEL("Microsoft Excel"),
		LSMW_SECURITY_POPUP("Microsoft Excel Security Notice"),			
		LSMW_ENABLE_MICRO("Enable Macros"),
		LSMW_LOCALE_FILE("Local file..."),
		LSMW_FILE_SAVE_CONTINUE("Continue"),
		LSMW_GENERATE_FILE("Generate"),
		LSMW_EXCEL_DOWNLOAD_REMEMBER("Remember My Decision"),
		LSMW_EXCEL_DOWNLOAD_ALLOW("Allow"),
		INVOICE_DOCUMENT_CUSTOMER("1800169688"),
		INVOICE_DOCUMENT_VENDOR("1900195588"),
		LSMW_SUBPROJECT("CP01"),
		LSMW_OBJECT("2018"),
		LSMW_DISPLAY_DOCUMENT_HEADER("Display Document Header"),
		LSMW_USERID_FRZ("Reg_TestFRZ"),
		LSMW_PASSWORD_FRZ("London@1"),
		LSMW_DISPLAYCHANGE("Display <-> Change"),
		LSMW_CONTINUE("Continue"),
		LSMW_CASTOPL_TEXTFILE("\\Test_File.txt"),
		LSMW_DATASAVED("Data saved"),
		LSMW_ALLOW("Allow"),
		LSMW_PROCESS("Process"),
		LSMW_CREATE_PROJECT_CONTINUE("1"),
		LSMW_SESSION_OVERVIEW("Session overview"),
		LSMW_LOG("Log"),
		LSMW_SETOPTION("Set options"),
		//cj20n open is identified as id
		LSMW_CJ20N_OPEN_FOLDER("Item 1"),
		LSMW_CJ20N_OPEN("Open"),
		LSMW_NOTEPAD_EDIT("Edit"),
		LSMW_NOTEPAD_LOCATION("Previous Locations"),
		LSMW_NOTEPAD_FILENAME("File name:"),
		LSMW_MENU("Menu"),
		LSMW_PRINTPREVIEW("Print Preview"),
		LSMW_USERMENU("User Menu"),
		LSMW_CJ20N("/ncj20n"),
		LSMW_CJ20N_PROJECTNAME("CP01"+new SimpleDateFormat("HHmmss").format(new Date())),
		LSMW_CJ20N_INVESTMENT_PROFILENAME("BQ77"),
		//LSMW_CJ20N_PROJECT_STATUS_AREA("//*[contains(@className,'Afx:')"),
		LSMW_CJ20N_PROJECT_STATUS_AREA("100"),
		SAP_FRZ_URL("https://rqaecc.b-and-q.com/nwbc_login/"),
		SAP_WEB_OPEN_ICON("//*[@title='Open ']"),
		LSMW_PROJECT_DOES_NOT_EXISTS("Project CP01ABCDZYX123 does not exist"),
		LSMW_FICO_JOURNAL("/nzfi004"),
		LSMW_PROJECT("modification"),
		F110_TCODE("/nf110"),
		F110_SCHEDULE("Schedule"),
		NBANK_VENDOR("//input[@id='M0:U:1:2B264::0:16']"),
		NBANK_VENDOR_VALUE("509298"),
	    NBANK_INVOICE_DATE("//input[@id='M0:U:1:2B264::1:16']"),
		NBANK_REFRENCE_NO("//input[@id='M0:U:1:2B264::1:47']"),
		NBANK_REFRENCE_NO_VALUE("TEST_9"),
		NBANK_VENDOR_VALUE_FOREIGN("811502"),
		NBANK_VENDOR_VALUE_FOREIGN_HSBC("509282"),
		NBANK_VENDOR_VALUE_DOMESTIC_HSBC("509210"),

		NBANK_AMOUNT("//input[@id='M0:U:1:2B264::4:16']"),
		NBANK_AMOUNT_VALUE("100"),

		NBANK_CURRENCY_KEY("//input[@id='M0:U:1:2B264::4:36']"),
		NBANK_CURRENCY_KEY_FOREIGN("GBP"),

		NBANK_GL_ACCOUNT("//input[starts-with(@id,'tbl')][contains(@id,'[1,2]')]"),
		NBANK_GL_ACCOUNT_VALUE("41700000"),

		NBANK_AMMOUNT_IN_DOC("//input[starts-with(@id,'tbl')][contains(@id,'[1,5]')]"),
		NBANK_AMMOUNT_IN_DOC_VALUE("100"),

		NBANK_PROFIT_CENTER("//input[starts-with(@id,'tbl')][contains(@id,'[1,28]')]"),
		NBANK_PROFIT_CENTER_VALUE("CP011524OP"),

		NBANK_PAYMENT_TAB("//div[@id='M0:U:1::0:1']"),
		NBANK_PMT_METHOD("//*[@id='M0:U:1:2B265::4:11']"),
		NBANK_PMT_METHOD_VALUE("P"),
		NBANK_PMT_METHOD_VALUE_FOREIGN("F"),

		NBANK_HOUSE_BANK1("//*[@id='M0:U:1:2B265::6:49']"),
		NBANK_HOUSE_BANK1_VALUE("BRE1"),
		NBANK_HOUSE_BANK1_VALUE_HSBC("HSBC2"),
		NBANK_HOUSE_BANK1_VALUE_HSBC_DOMESTIC1("HSBC3"),
		NBANK_HOUSE_BANK2("//*[@id='M0:U:1:2B265::6:57']"),
		NBANK_HOUSE_BANK2_VALUE("00002"),
		NBANK_HOUSE_BANK2_VALUE_DOMESTIC("00001"),
		//*[@id="M1:U:::0:17"]
		NBANK_DOCUMENT_NO("//span[@id='wnd[0]/sbar_msg-txt']"),
		NBANK_RIGHT_SIDE_ARROW("//div[@id='M0:U:4:1_hscroll-Nxt']"),
		NBANK_DOCUMENT_NO_VALIDATION_MESSEGE("//span[@id='wnd[0]/sbar_msg-txt']"),
		NBANK_TEXT_FIELD("//input[@id='M0:U:1:2B264::6:16']"),
		NBANK_POST("//*[@id='M0:D:10::btn[11]']"),
		
		//Fb03 Xpaths
		DOCUMENT_NUMBER_FIELD("//input[@id='M0:U:::2:25']"),
		VENDOR_NUMBER_FIELD("(//input[@class='lsTblEdf3 lsTblEdf3NoEllipsis urBorderBox lsControl--explicitwidth lsField__input'])[8]"),
		AMOUNT_ELIGIBLE_FIELD("//input[@title='Amount Eligible for Cash Discount in Document Currency']"),
		GROUP_CRC_AMMOUNT("//input[@id='M1:U:::8:17']"),
	    IFRAME_URLSPW_0("//iframe[@id='URLSPW-0']"),
	    CALCULATE_TAX_CHECKBOX("//img[@id='M0:U:1:2B264::4:47-img']"),
	    CALCULATE_TAX_DROPDOWN("//input[@type='text' and @title='Tax on Sales/Purchases Code']"),

	    ING_CURRENCY_KEY_FOREIGN("EUR"),
	    ING_HOUSE_BANK1_VALUE("ING"),
	    ING_HOUSE_BANK2_VALUE_FOREIGN("ING02"),
	    ING_PMT_METHOD_VALUE("F"),
	    ING_HOUSE_BANK2_VALUE_DOMESTIC("ING01"),
	    ING_PMT_METHOD_VALUE_DOMESTIC("P"),
	    ING_VENDOR_VALUE_DOMESTIC("509282"),
	    NFB60_COMPANY_CODE_TAB("//div[@id='M0:D:13::btn[7]']"),
	  
	    INPUT_COMPANY_CODE_TAB("//input[@id='M1:U:::0:18']"),
	    INPUT_COMPANY_CODE_OK_TAB("//div[@id='M1:D:13::btn[0]']"),
	    
	    FRAME_1("//iframe[starts-with(@id,'iFrameId_1546601646021')]"),
	  
	  
		
		

		
		


		;


		private String value;

		ConstantForSap(String value)
		{

			this.value=value;

		}


		public String getValue() {
			return value;
		}
	}
	public Map<String,String> valueSetter(String opco,String env)
	{
		Map<String,String> map=new HashMap<String,String>();

		if(opco.length()>0)
		{


			if(opco.equals("BNQ"))
			{
				map.put("LSMW_EXCELNAME", ConstantForSap.LSMW_EXCELNAME_BNQ.getValue());
				map.put("LSMW_PURCHASEORG", ConstantForSap.LSMW_PURCHASEORG_BNQ.getValue());

			}
			else if(opco.equals("CASTO"))
			{
				map.put("LSMW_EXCELNAME", ConstantForSap.LSMW_EXCELNAME_CASTO.getValue());
				map.put("LSMW_PURCHASEORG", ConstantForSap.LSMW_PURCHASEORG_CASTO.getValue());
			}
			else if(opco.equals("CASTOPL"))
			{
				map.put("LSMW_CASTOPL_TEXTFILE", ConstantForSap.LSMW_CASTOPL_TEXTFILE.getValue());
				//map.put("LSMW_PURCHASEORG", ConstantForSap.LSMW_PURCHASEORG_CASTO.getValue());
			}

		}

		if(env.length()>0)
		{
			if(env.equals("VRZ"))
			{
				map.put("LSMW_SERVER", ConstantForSap.LSMW_VRZ.getValue());
				map.put("LSMW_USERID", ConstantForSap.LSMW_USERID_VRZ.getValue());
				map.put("LSMW_PASSWORD", ConstantForSap.LSMW_PASSWORD_VRZ.getValue());

			}
			else if(env.equals("QRZ"))
			{
				map.put("LSMW_SERVER", ConstantForSap.LSMW_QRZ.getValue());
				map.put("LSMW_USERID", ConstantForSap.LSMW_USERID_QRZ.getValue());
				map.put("LSMW_PASSWORD", ConstantForSap.LSMW_PASSWORD_QRZ.getValue());

			}

			else if(env.equals("FRZ"))
			{
				map.put("LSMW_SERVER", ConstantForSap.LSMW_FRZ.getValue());
				map.put("LSMW_USERID", ConstantForSap.LSMW_USERID_FRZ.getValue());
				map.put("LSMW_PASSWORD", ConstantForSap.LSMW_PASSWORD_FRZ.getValue());

			}


		}
		
		return map;

	}
	
	
	


}
