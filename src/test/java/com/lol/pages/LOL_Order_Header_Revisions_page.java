package com.lol.pages;

import java.util.ArrayList;

import com.lol.util.TestExecutor;
import com.tavant.utils.TwfException;

public class LOL_Order_Header_Revisions_page extends TestExecutor{
	
	ArrayList<String> order_confirmation_productids = new ArrayList<>();
	
	public String order_header_revision_page_title=".//*[@id='jdeFormTitle0']";
	public String ship_to_filed="C0_21_48";
	public String branch_plant="C0_21_35";
	public String customer_po="C0_21_53";
	public String save_and_continue_button="C0_20";
	public String Create_date = ".//*[@id='C0_21_31']";
	public String cancel_date= ".//*[@id='C0_21_267']";
	public String requested_date= ".//*[@id='C0_21_29']";
	public String Scheduled_date= ".//*[@id='C0_21_357']";
	public String Promised_ship_date= ".//*[@id='C0_21_359']";
	public String promised_delivery_date = ".//*[@id='C0_21_361']";
	
	
	
	
	
	

	
	
	
	public boolean isElementisVisible(String value)throws TwfException{
		try{
			return common.getObjectByXpath(value).isDisplayed();
			
		}
		catch (org.openqa.selenium.NoSuchElementException e)
		{
			return false;
		}
		
	}
}
