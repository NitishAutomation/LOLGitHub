package com.lol.pages;

import com.relevantcodes.extentreports.ExtentTest;

import com.lol.util.TestExecutor;

public class AP_NonProductVoucher extends TestExecutor {

//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;


	
	public String input_discountduedate=".//input[@title='Discount Due Date']";
	public String input_netduedate=".//input[@title='Net Due Date']";
	
	public String label_itemhelpDDJ=".//*[text()='Alias: DDJ']";
	public String label_itemhelpDDNJ=".//*[text()='Alias: DDNJ']";
}
