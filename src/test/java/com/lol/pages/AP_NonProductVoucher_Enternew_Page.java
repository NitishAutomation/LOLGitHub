package com.lol.pages;

import com.relevantcodes.extentreports.ExtentTest;

import com.lol.util.TestExecutor;

public class AP_NonProductVoucher_Enternew_Page  extends TestExecutor {

	
//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;


	
	public String input_discountduedate=".//*[@id='G0_1_R0']/td[8]/div/input";
	public String input_netduedate=".//*[@id='G0_1_R0']/td[9]/div/input";
	
	public String input_companyNum=".//span[text()='Company']//following::input[1]";
	public String input_supplierNum=".//span[text()='Supplier Number']//following::input[1]";
	public String input_gLdate=".//span[text()='G/L Date']//following::input[1]";
	public String input_Invoice=".//span[text()='Invoice Number']//following::input[1]";
	
	public String label_itemhelpDDJ=".//*[text()='Alias: DDJ']";
	public String label_itemhelpDDNJ=".//*[text()='Alias: DDNJ']";
	
	
}
