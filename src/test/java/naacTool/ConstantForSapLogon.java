package naacTool;

import java.util.HashMap;
import java.util.Map;
public class ConstantForSapLogon {

public enum ConstantForSap {

	SAP_LOGON_PATH("C:\\Program Files (x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe"),
	DESKTOP_DRIVER_URL("http://localhost:9999"),
	SAPLOGON_TITLE("TitleBar"),
	SAPLOGON_LOGIN("Log On"),
	SAPLOGON_VRZ("02.20 VRZ - ERP Pre-prod "),
	SAPLOGON_QRZ("02.02 QRZ - ERP BAU QA "),
	SAPLOGON_USERID_VRZ("dasank03"),
	SAPLOGON_PASSWORD_VRZ("Kolkata@2020"),
	SAPLOGON_USERID_QRZ("dasank03"),
	SAPLOGON_PASSWORD_QRZ("Kolkata@2020"),
	SAPLOGON_EDIT("Edit"),
	SAPLOGON_UPLOAD_TCODE("/nzrmd021"),
	SAPLOGON_EXCELNAME_BNQ("\\BNQ ARTICLE.xlsx"),
	SAPLOGON_EXCELNAME_CASTO("\\CASTO ARTICLE.xlsx"),
	SAPLOGON_PURCHASEORG_BNQ("BQ10"),
	SAPLOGON_PURCHASEORG_CASTO("CF01"),
	SAPLOGON_EXECUTE_BUTTON("Execute"),
	SAPLOGON_SECURITY_MODAL("Remember My Decision"),
	SAPLOGON_SECURITY_MODAL_ALLOW("Allow"),
	SAPLOGON_POST_CONFIRMATION("post(YES/NO)"),
	SAPLOGON_MULTIPLE_USER("License Information for Multiple Logons"),
	SAPLOGON_TERMINATE_LOGON("Confirm Selection"),
	SAPLOGON_MICROSOFT_EXCEL("Microsoft Excel"),
	SAPLOGON_SECURITY_POPUP("Microsoft Excel Security Notice"),
	SAPLOGON_ENABLE_MICRO("Enable Macros"),
	SAPLOGON_LOCALE_FILE("Local file..."),
	SAPLOGON_FILE_SAVE_CONTINUE("Continue"),
	SAPLOGON_GENERATE_FILE("Generate"),
	SAPLOGON_EXCEL_DOWNLOAD_REMEMBER("Remember My Decision"),
	SAPLOGON_EXCEL_DOWNLOAD_ALLOW("Allow"),






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
				map.put("SAPLOGON_EXCELNAME", ConstantForSap.SAPLOGON_EXCELNAME_BNQ.getValue());
				map.put("SAPLOGON_PURCHASEORG", ConstantForSap.SAPLOGON_PURCHASEORG_BNQ.getValue());

			}
			else if(opco.equals("CASTO"))
			{
				map.put("SAPLOGON_EXCELNAME", ConstantForSap.SAPLOGON_EXCELNAME_CASTO.getValue());
				map.put("SAPLOGON_PURCHASEORG", ConstantForSap.SAPLOGON_PURCHASEORG_CASTO.getValue());
			}
		}

		if(env.length()>0)
		{
			if(env.equals("VRZ"))
			{
				map.put("SAPLOGON_SERVER", ConstantForSap.SAPLOGON_VRZ.getValue());
				map.put("SAPLOGON_USERID", ConstantForSap.SAPLOGON_USERID_VRZ.getValue());
				map.put("SAPLOGON_PASSWORD", ConstantForSap.SAPLOGON_PASSWORD_VRZ.getValue());

			}
			else if(env.equals("QRZ"))
			{
				map.put("SAPLOGON_SERVER", ConstantForSap.SAPLOGON_QRZ.getValue());
				map.put("SAPLOGON_USERID", ConstantForSap.SAPLOGON_USERID_QRZ.getValue());
				map.put("SAPLOGON_PASSWORD", ConstantForSap.SAPLOGON_PASSWORD_QRZ.getValue());

			}
		}
		return map;


	}
	
}
