package naacTool;

public enum ConstantForSapLogon {
	
	SAP_LOGON_PATH("C:\\Program Files (x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe"),
	DESKTOP_DRIVER_URL("http://localhost:9999"),
	SAPLOGON_TITLE("TitleBar"),
	SAPLOGON_LOGIN("Log On"),
	//SAPLOGON_VRZ("02.20 VRZ - ERP Pre-prod "),
	SAPLOGON_VRZ("02.02 QRZ - ERP BAU QA "),
	SAPLOGON_USERID_VRZ("dasank03"),
	SAPLOGON_PASSWORD_VRZ("Kolkata@2020"),
	SAPLOGON_EDIT("Edit"),
	SAPLOGON_UPLOAD_TCODE("/nzrmd021"),
	SAPLOGON_EXCELNAME("\\BNQ ARTICLE.xlsx"),
	//SAPLOGON_EXCELNAME("\\BNQ ARTICLE.xlsx"),
	SAPLOGON_PURCHASEORG("BQ10"),
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
	
	ConstantForSapLogon(String value)
	{
		
		this.value=value;
				
	}
	
	
	public String getValue() {
		return value;
	}

}
