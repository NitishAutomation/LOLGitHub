package com.lol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lol.util.TestExecutor;

public class Create_Product_SpecificationThroughWizard extends TestExecutor {

	WebElement table = null;
	List<WebElement> divs = null;
	List<WebElement> rows = null;

	public String create_Product_Specification = "//a[text()='Create Product Specification']";
	public String referenced_Project = "//*[@id='FsCtlLkp_lkpPROJECT__G_ACTIONSETSTARTDATAContainer']/button";
	public String reference001 = "//*[@id='fetchedData']/tbody/tr[4]/td[1]";
	public String reference_Product = "//*[@id='fetchedData']/tbody/tr[2]/td[1]";
	public String referenceTemplete = "//*[@id='FsCtlLkp_lkpPROD_TEMPLATE__G_ACTIONSETSTARTDATAContainer']/button";
	public String vendor = "//*[@id='FsCtlLkp_lkpVENDOR__G_ACTIONSETSTARTDATAContainer']/button";
	public String referece_Vedor = "//*[@id='fetchedData']/tbody/tr[2]/td[1]";
	
	
	
	

	public String create_Product_Spec() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ifrmBody");
		
		common.clickable(referenced_Project);
		//WebElement reference_project = driver.findElement(By.xpath(referenced_Project));
		//reference_project.click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrm_FIND_Dialog");

		common.clickable(reference001);
		//common.getObjectByXpath(reference001).click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrmBody");
		common.clickable(referenceTemplete);
		/*WebElement input_field1 = driver.findElement(By.xpath(referenceTemplete));
		input_field1.click();*/
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrm_FIND_Dialog");
		common.clickable(reference_Product);

		//common.getObjectByXpath(reference_Product).click();
		

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrmBody");

		common.clickable(vendor);
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrm_FIND_Dialog");
		common.clickable(referece_Vedor);
		//common.getObjectByXpath(referece_Vedor).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		driver.switchTo().frame("ifrmBody");
		//Thread.sleep(2000);
		driver.findElement(By.id("txtPROD_DESC__G_ACTIONSETSTARTDATA")).click();
		driver.findElement(By.id("txtPROD_DESC__G_ACTIONSETSTARTDATA")).sendKeys("TESTAUTOMATION");
		
		driver.findElement(By.xpath("//*[@id='COMPLETE']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='COMPLETE']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='COMPLETE']")).click();
		return create_Product_Specification;

	}

}
