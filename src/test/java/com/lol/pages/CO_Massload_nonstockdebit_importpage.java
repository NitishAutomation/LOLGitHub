package com.lol.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tavant.kwutils.TestCaseExecuter;

import com.lol.util.TestExecutor;


public class CO_Massload_nonstockdebit_importpage extends TestExecutor {

//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;


	public String massimport_creditorrebillcode = ".//*[@id='G0_1_R0']/td[3]/div/input";
	public String massimport_itemnumber = ".//*[@id='G0_1_R0']/td[7]/div/input";
	public String massimport_uom=".//*[@id='G0_1_R0']/td[9]/div/input";
	public String massloadimport_grid_scroll=".//*[@id='jdeGridBack0_1']";
	
	//massloadimportpage.massimport_quant_data
	public String massimport_quant_title=".//*[@id='GC0_1.9']";
	public String massimport_creditprice_title="(.//span[contains(., 'Credit')][contains(., 'Price')])[2]";
	public String massimport_returnreasoncode_title="(.//span[contains(., 'Return')][contains(., 'Reason Code')])[2]";
	public String massimport_paymentterms_title="(.//span[contains(., 'Payment')][contains(., 'Term')])[2]";
	public String massimport_customerPOtest_title="(.//span[contains(., 'Customer')][contains(., 'PO')])[2]";
	public String massimport_branchplant_title="(.//span[contains(., 'Branch')][contains(., 'Plant')])[2]";
	public String massimport_detailcomment_title="(.//span[contains(., 'Detail')][contains(., 'Comment')])[2]";
	public String massimport_headercomment_title="(.//span[contains(., 'Header')][contains(., 'Comment')])[2]";
	public String massimport_custPO_title="(.//span[contains(., 'Customer')][contains(., 'Number')])[2]";
	public String massimport_quant_data="(//*[@id='G0_1_R0']//td/div/input)[last()-1]";
	public String massimport_creditprice_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_returnreasoncode_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_paymentterms_data="(//*[@id='G0_1_R0']//td/div/input)[last()-1]";
	public String massimport_customerPOtest_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_branchplant_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_detailcomment_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_headercomment_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_custPO_data="(//*[@id='G0_1_R0']//td/div/input)[last()]";
	public String massimport_firstcol_titl=".//*[@id='hdrRow0_1']/td[1]/div[1]";
	public String massimport_radiobut_titl=".//*[@id='G0_1_R1']/td[1]/div/input";
	
// validating all the elements in Ecomsit2 Loginpage

//	public void verifyAllElementsInBusinessUnitpage(ExtentTest logger) {
//		logger=".log(LogStatus.INFO, "=======Start testngreportsecond========");
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