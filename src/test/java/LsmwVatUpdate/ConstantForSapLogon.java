package LsmwVatUpdate;

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
	LSMW_PROJECT_LSMW("ZVAT"),
	LSMW_SUBPROJECT_LSMW("ZVAT-CAPL"),
	LSMW_OBJECT_LSMW("ZVAT-CP01"),
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
	LSMW_PASSWORD_FRZ("Welcome*2"),
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
	public Map<String,String> valueSetter(String opco,String env,String invoiceType)
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
		if(invoiceType.length()>0)
			if(invoiceType.equals("customer"))
		{
			map.put("LSMW_PROJECT", ConstantForSap.INVOICE_DOCUMENT_CUSTOMER.getValue());
		}
			else if(invoiceType.equals("vendor"))
			{
				map.put("LSMW_PROJECT", ConstantForSap.INVOICE_DOCUMENT_VENDOR.getValue());
			}
		return map;


	}
	
}
