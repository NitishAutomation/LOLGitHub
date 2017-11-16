package com.lol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;
import com.lol.util.TestExecutor;

public class ShoppingLists extends TestExecutor {
	
	public String CSQtyTextBox = ".//input[@id='r1:0:pt1:lv1:0:it3::content']";
	
	
	public void enterProdQtyInListsWithProdNo(String prodNo, String Qty) throws TwfException{
		int noOfProd = common.getSizeOfElementsSelected(".//*[contains(@id,'r1:0:pt1:lv1:') and contains(@id, ':pgl28')]");
		String prodNbrs;
		for (int i = 0; i < noOfProd; i++) {
			if (driver.findElement(By.id("r1:0:pt1:lv1:" + i + ":pgl23")).getText().contains(" (Replace ")) {
				continue;
			} else {
				prodNbrs = driver.findElement(By.xpath(".//*[@id='r1:0:pt1:lv1:" + i + ":pgl125']/tbody/tr/td[1]/span"))
						.getText();
				if (prodNbrs.contains(prodNo)) {
					common.getObjectByXpath(".//*[@id='r1:0:pt1:lv1:" + i + ":it3::content']").sendKeys(Qty);
					//logger.log(LogStatus.PASS, "Entered Quantity value of "+Qty+" for product "+prodNo);
					common.getObjectByXpath(CSQtyTextBox).sendKeys(Keys.TAB);
					return;
				}
			}
		}
	}
	
	public void changeDeliveryDate(String dlvryDt)throws TwfException
	{
		if(!(dlvryDt.equals("")))
		{
			if(common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl2']").isDisplayed())
			{
				common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl2']").click();
				common.sleep(800);
				common.validateObjectsDisplayed(".//*[@id='r1:0:pt1:r1:0:d3']", "Update Delivery Date modal");
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:id1::content']").clear();
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:id1::content']").sendKeys(dlvryDt);
				common.getObjectByXpath(".//button[@id='r1:0:pt1:r1:0:cb8']").click();
			}
			else{
				//logger.log(LogStatus.INFO, "Date link is not displayed in SL page");
			}
		}
		else
		{
			if(common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl2']").isDisplayed())
			{
				common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl2']").click();
				common.sleep(800);
				common.validateObjectsDisplayed(".//*[@id='r1:0:pt1:r1:0:d3']", "Update Delivery Date modal");
				common.getObjectByXpath(".//button[@id='r1:0:pt1:r1:0:cb8']").click();
			}
			else{
				//logger.log(LogStatus.INFO, "Date link is not displayed in SL page");
			}
		}
	}
	
	public void changePO(String PO)throws TwfException
	{
		if(!(PO.equals("")))
		{
			if(common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl9']").isDisplayed())
			{
				common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl9']").click();
				common.sleep(800);
				common.validateObjectsDisplayed(".//*[@id='r1:0:pt1:r1:0:d1']", "Update PO Number modal");
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:ponumber-it1::content']").clear();
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:ponumber-it1::content']").sendKeys(PO);
				common.getObjectByXpath(".//button[@id='r1:0:pt1:r1:0:cb6']").click();
			}
			else{
				//logger.log(LogStatus.INFO, "PO link is not displayed in SL page");
			}
		}
		else
		{
			if(common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl9']").isDisplayed())
			{
				common.getObjectByXpath(".//a[@id='r1:0:pt1:r1:0:cl9']").click();
				common.sleep(800);
				common.validateObjectsDisplayed(".//*[@id='r1:0:pt1:r1:0:d1']", "Update PO Number modal");
				common.getObjectByXpath(".//button[@id='r1:0:pt1:r1:0:cb6']").click();
			}
			else{
				//logger.log(LogStatus.INFO, "PO link is not displayed in SL page");
				}
		}
		
	}
	public void enterQEproducts(String prodNo, String Qty)throws TwfException
	{
		if(common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:pgl58']").isDisplayed())
		{
			//logger.log(LogStatus.PASS, "Quick Entry section is displayed in SL page");
			common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:it1::content']").clear();
			common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:it1::content']").sendKeys(prodNo);
			common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:it1::content']").sendKeys(Keys.ENTER);
			common.sleep(800);
			if(common.validateObjectsDisplayedWOReport(".//*[@id='r1:0:pt1:r1:0:r1:0:it3::content']"))
			{
				//logger.log(LogStatus.PASS, "Product " + common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:pgl5']/div[1]//span").getText()
						//+ " is displayed");
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:it3::content']").clear();
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:it3::content']").sendKeys(Qty);
				common.getObjectByXpath(".//*[@id='r1:0:pt1:r1:0:r1:0:cb1']").click();
			}
			else{
				//logger.log(LogStatus.FAIL, "Product details is not displayed on entering product number in Quick entry module");
			}
		}
		else
		{
			//logger.log(LogStatus.FAIL, "Quick Entry section is not displayed in SL page");
			//common.captureSS("QE not available", LogStatus.FAIL);
		}
	}
}
