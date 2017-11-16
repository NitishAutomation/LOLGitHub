package com.lol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.lol.util.TestExecutor;
//import test.java.com.lol.utils.*;

public class SalesandCreditorderinquiry_manageordershome_page extends TestExecutor {

//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;


	public String CO_Credrebill_ordernum_txtbox = ".//*[@id='C0_366']";
	public String CO_Credrebill_orderlaststat = ".//*[@id='G0_1_R0']/td[11]/div";
	public String CO_Credrebill_pymtcode_titlecol=".//tr[2]//span[1][@id='GC0_1.101']";
	public String CO_Credrebill_pymtdesc_titlecol = ".//tr[2]//span[1][@id='GC0_1.102']"; 
	public String CO_Credrebill_futureqtycomit_titlecol=".//tr[2]//span[1][@id='GC0_1.78']";
	public String CO_Credrebill_city_titlecol=".//*[@id='GC0_1.20']";
	public String CO_Credrebill_state_titlecol=".//*[@id='GC0_1.21']";
	
	public String CO_Credrebill_pymtcode_datacol=".//*[@id='G0_1_R0']/td[7]/div";
	public String CO_Credrebill_pullsignal_txtbox=".//*[@id='qbeRow0_1']/td[83]/div/nobr/input";
	public String CO_Credrebill_Orderline_checkbox = ".//*[@id='G0_41_R0']/td[1]/div/input"; 
	public String Co_Credrebill_ordernuminGrid=".//*[@id='G0_1_R0']/td[3]/div";//name of the checkbox
	public String CO_Credrebill_rowexit_orders=".//*[@id='SubMenu_HE0_232']/span/nobr";
	public String CO_Credrebill_rowexit_orders_sodates=".//*[@id='HE0_249']/tbody/tr/td[2]/span/nobr";
	//public By order_select_checkbox=".//*[@id='G0_41_R0']/td[1]/div/input";
	public String CO_Credrebill_row_order_detailatt=".//*[@id='HE0_324']/tbody/tr/td[2]/span/nobr";
	public String  CO_Credrebill_row_order_headeratt=".//*[@id='HE0_323']/tbody/tr/td[2]/span/nobr";
 	public String CO_credrebill_laststatus_title=".//*[@id='qbeRow0_41']/td[17]/div/nobr/input";
 	public String CO_credrebill_laststatus_data="(//*[@id='G0_41_R0']//td/div)[last()]";
// validating all the elements in Ecomsit2 Loginpage

//	public void verifyAllElementsInBusinessUnitpage(ExtentTest logger) {
//		logger.log(LogStatus.INFO, "=======Start testngreportsecond========");
//
//		/*
//		 * Assert.assertTrue(common.getObjectByXpath(bookmark).isDisplayed(),
//		 * "bookmark is not there "); logger.log(LogStatus.PASS,
//		 * "bookmark is Present in loginpage ");
//		 */
//		Assert.assertTrue(common.getObjectByXpath(add_plus,"add_plus").isDisplayed(), "Learnmorelogo is not there ");
//		logger.log(LogStatus.PASS, "Add button is Present in Review BUsiness Unit page ");
//		Assert.assertTrue(common.getObjectByXpath(add_BU_txtbox,"add_BU_txtbox").isDisplayed(), "Learnmore is not there ");
//		logger.log(LogStatus.PASS, "Business Unit textbox is Present  in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_more_tab,"BU_more_tab").isDisplayed(), "usfoodlogo is not there ");
//		logger.log(LogStatus.PASS, "BU More tab is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_CatCodes1to10_tab,"BU_CatCodes1to10_tab").isDisplayed(), "useridtext is not there ");
//		logger.log(LogStatus.PASS, "BU_CatCodes1to10_tab is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_CatCodes11to20_tab,"BU_CatCodes11to20_tab").isDisplayed(), "useridtextbox is not there ");
//		logger.log(LogStatus.PASS, "BU_CatCodes11to20_tab is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_CatCodes31to40_tab,"BU_CatCodes31to40_tab").isDisplayed(), "passwordtext is not there ");
//		logger.log(LogStatus.PASS, "BU_CatCodes31to40_tab is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_CatCodes41to50_tab,"BU_CatCodes41to50_tab").isDisplayed(), "passwordtextbox is not there ");
//		logger.log(LogStatus.PASS, "BU_CatCodes41to50_tab is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(add_BU_ok_button,"add_BU_ok_button").isDisplayed(), "loginbutton is not there ");
//		logger.log(LogStatus.PASS, "add_BU_ok_button is Present in Review BUsiness Unit Page ");
//		Assert.assertTrue(common.getObjectByXpath(BU_find_button,"BU_find_button").isDisplayed(), "Forgotuserid is not there ");
//		logger.log(LogStatus.PASS, "BU_find_button is Present in Review BUsiness Unit Page ");
//		
//
//		logger.log(LogStatus.INFO, "=======End testngreportsecond======");
//	}

	
		
	

	
}