package com.lol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lol.util.TestExecutor;

//import test.java.com.lol.tests.TestExecutor;


public class Page extends TestExecutor{

	public String customerDropdownIMG = ".//*[contains(@id,':pt_cil4::icon')]";
	public String customerDropdownSearch = ".//*[contains(@id,':pt_s33:pt_it3::content')]";
	public String selectedCustomerText = ".//*[contains(@id,':pt_ot4')]/span";
	public String shopProductsLink = ".//*[contains(@id, 'pt_i7:0:pt_s46:pt_gl1')]";
	public String ourExclusivesLink = ".//*[contains(@id,'pt_i7:1:pt_s46:pt_gl1')]";
	public String myBusinessLink = ".//*[contains(@id,'pt_i7:2:pt_s46:pt_gl1')]";
	public String topUSFLogoIcon = ".//*[contains(@id,':pt_cil10::icon')]";
	public String welcomeBackMsg = ".//*[contains(@id,':pgl10')]//span[@class='x2lr']";
	public String listDropdownImg= "//a//img[contains(@id,':pt_i3:6:pt_sfm1')][@class='x12x']";
	public String currentOrderModule = ".//*[@id='r1:0:pt1:r1:0:pgl33']";
	public String quickEntryModule = ".//*[@id='r1:0:pt1:r1:0:pgl58']";
	public String quickEntryProductTxtBox = ".//*[@id='r1:0:pt1:r1:0:r1:0:it1::content']";
	public String quickEntryCustProductTxtBox = ".//*[@id='r1:0:pt1:r1:0:r1:0:it2::content']";
	public String productsInOrderModule = ".//*[@id='r1:0:pt1:r1:0:pgl32']"; 
	
	//This function will fetch object/element of the customer whichever is passed as input
	public WebElement getObjectByCustomerNumber(String customerNumber)
	{
		return driver.findElement(
				By.xpath(".//*[contains(@id,':pt_pgl117')]//span[text()='("+customerNumber.trim()+")']"));
	}
	
	public String getIndexFromListOfCustomers(String customerNumber)
	{
		WebElement element = driver.findElement(
				By.xpath(".//*[contains(@id,':pt_pgl117')]//span[text()='("+customerNumber.trim()+")']//preceding::a[1]")); 
		String id = element.getAttribute("id");
		String arrID[] = id.split(":");
		return arrID[2];		
	}
}
