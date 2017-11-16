package com.lol.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.lol.util.TestExecutor;


public class CO_Massload_nonstock_workwithcreditndrebill extends TestExecutor {

//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;


	public String Unprocessed_radiobutton = ".//*[@id='C0_106']";
	public String processed_radiobutton = ".//*[@id='C0_107']";
	public String massload_grid_scroll=".//*[@id='jdeGridBack0_1']";
	public String grid_checkbox = ".//*[@id='G0_1_R0']/td[1]/div/input"; 
	// public String bookmark =".//*[@id='gil1::icon']";
	public String previous_batch = ".//*[@id='C0_33']"; //name of the checkbox
	//public By order_select_checkbox=".//*[@id='G0_41_R0']/td[1]/div/input";
	public String batch_num_texbox = ".//*[@id='C0_27']"; //.//*[@id='outer0_33']
	public String massload_dataudpated_title = ".//*[@id='GC0_1.41']"; // .//*[@id='outer0_14']
	public String processcode_title=".//*[@id='selectAll0_1']";//.//*[@id='hdrRow0_1']/td[2]/div/a/img";
	public String massload_dataudpated_titlesearch=".//*[@id='qbeRow0_1']/td[36]/div/nobr/input";
	public String massloadponum=  ".//*[@id='G0_1_R0']/td[5]/div";
	// (//*[@id='G0_1_R0']//td/div/input)[first()+2]
	public String massloadprocesscode=".//*[@id='G0_1_R0']/td[3]/div";
	
	//(//*[@id='G0_1_R0']//td/div/input)[last()-9]"

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