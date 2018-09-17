package naacTool;

import java.util.Scanner;

public class Fixture {
	Scanner sc=new Scanner(System.in);

	private String environment="";
	private String opco="";

	public String getEnvironment() {
		System.out.println("Enter the Environment::");
		environment=sc.next();
		
		return environment;
	}

	public String getOpco() {
		System.out.println("Enter the Opco::");
		opco=sc.next();
		return opco;
	}



	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setOpco(String opco) {
		this.opco = opco;
	}




}

