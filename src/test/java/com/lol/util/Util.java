package com.lol.util;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.KWVariables;
import com.tavant.kwutils.PageObject;
import com.tavant.kwutils.PageObjectLoader;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;


import com.lol.pages.*;
/*import test.java.com.lol.pages.LOL_Enter_New_Order_page;
import test.java.com.lol.pages.LOL_Manage_Existing_Order_page;
import test.java.com.lol.pages.LOL_OrderManagement_Creation_RSO;
import test.java.com.lol.pages.LOL_Order_Header_Revisions_page;*/

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class Util extends TestExecutor {

	static String ndItem;
	static String itemDesc;
	static String reasonCode;
	static String ediDOCNo;
	static String orderedQuan;
	static String DocTy;
	static String allocLn;
	static String soldTo;
	static String shipTo;
	static String uM;
	static String brachPlant;
	static String userId;
	static String programId;
	static String workstnId; 
	static String dateUpdated;
	static String timeOfDay;
	static String originalSalesOrderNo;
	static String locations;
	static String lotSerialNo;
	static String allocStatus;
	
	
//-------------------------------------------------------------------------------------------------------------------------------
	/*@POC methods 
	 * @Author-Vivek.Prasad
	 */
//-------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Add Failure Reason to Report
	 * 
	 * @param description
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 * @throws TwfException
	 */
	/*public static void addExceptionToReport(String description, String actualValue, String expectedValue)
			throws TwfException {
		throw new TwfException(description + " :<font color=\"solid orange\">  Actual :[" + actualValue
				+ "]</font><font color=\"EE7600\"> Expected :[" + expectedValue + "]</font><br> <b>Step Details:</b> "
				+ "<br>");
	}*/

	/**
	 * 
	 * @param dummyArg
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws  
	 * 
	 * @description downloads the pdf and verifies the name in the downloads directory
	 */

	public boolean checkDownloadOrder(String dummyArg) throws TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();

		if(DriverFactory.getBrowserAsString().contains("iexplore")){

			try{
				System.out.println("In this method....");
				Thread.sleep(3000);
				Robot robo=new Robot();
				robo.keyPress(KeyEvent.VK_ALT);
				robo.keyPress(KeyEvent.VK_S);
				robo.keyRelease(KeyEvent.VK_S);
				robo.keyRelease(KeyEvent.VK_ALT);
				Thread.sleep(10000);System.out.println("Done");
			}catch(Exception e){

				System.out.println(e.getMessage());
			}


		}

		WebElement job = driver.findElement(By.xpath(".//*[@id='G0_1_R0']/td[6]/div/a"));
		WebElement jobnumber = driver.findElement(By.xpath(".//*[@id='G0_1_R0']/td[7]/div"));
		String jobname = getText(job);
		String[] param = jobname.split("_");
		String jobno = getText(jobnumber);
		String pdfname = "*_EN_" + jobno;

		driver.findElement(By.xpath(".//*[@id='G0_1_R0']/td[2]/div/a/img")).click();
		Thread.sleep(5000);

		File dir = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/");
		String pdf = pdfname + "*.pdf";
		FileFilter fileFilter = new WildcardFileFilter(pdf);
		int i = 0;
		File[] files = dir.listFiles(fileFilter);
		for (i = 0; i < files.length; i++) {
			System.out.println("pdf files   " + files[i]);
		}

		if (files.length > 0)
			return true;
		else
			//	addExceptionToReport("Expected PDF file not found in download directory", actualValue, expectedValue);

			throw new TwfException("Expected PDF file not found in download directory");

	}


	/**
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InterruptedException
	 * 
	 * @description enter the current date in the input field
	 */
	public void currentDate(String args)
			throws TwfException, InvalidFormatException, IOException, BiffException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();
		DateFormat newDate = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String currentdate = (newDate.format(date));
		getElementByUsing(args).sendKeys(currentdate);
	}

	/**
	 * 
	 * @param dummyArg
	 * @return
	 * @throws TwfException
	 * @throws InterruptedException
	 * 
	 * @description downloads the pdf and verifies the name in the downloads directory
	 */
	public boolean checkDownloadUpdate(String dummyArg) throws TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement job = driver.findElement(By.xpath(".//*[@id='G0_1_R0']/td[6]/div/a"));
		WebElement jobnumber = driver.findElement(By.xpath(".//*[@id='G0_1_R0']/td[7]/div"));
		String jobname = getText(job);
		String jobno = getText(jobnumber);
		String pdfname = jobname + "_" + jobno;
		driver.findElement(By.xpath(".//*[@id='hc_Select']")).click();
		Thread.sleep(5000);

		File dir = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/");
		String pdf = pdfname + "*.pdf";
		FileFilter fileFilter = new WildcardFileFilter(pdf);
		int i = 0;
		File[] files = dir.listFiles(fileFilter);
		for (i = 0; i < files.length; i++) {
			System.out.println("pdf files   " + files[i]);
		}

		if (files.length > 0)
			return true;
		else
			throw new TwfException("Expected PDF file not found in download directory");

	}
	public void handleAlert(String args) throws TwfException, BiffException, IOException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing(args).click();		
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

	}

	public boolean isAlertPresent() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		try {
			driver.switchTo().alert();

			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
//-------------------------------------------------------------------------------------------------------------------------
	/*@POC methods ends here
	 * @Author- Vivek.Prasad
	 */
//-------------------------------------------------------------------------------------------------------------------------

	
	
//-------------------------------------------------------------------------------------------------------------------------
	/*@ Starts Integrated Scripts old LOL to FIRE
	 * @Author- Nitish.Nayak
	 */
//--------------------------------------------------------------------------------------------------------------------------


	
//---------------------------------------------------------------------------------------------------------------------------	
	/*@homeGeneralVerification method without any code change to the original framework
	 */

	public void homePageGeneralVerification(String k) throws TwfException, IOException{

		System.out.println("In the custom method.....-->");

		Start();

		System.out.println("----> title of the page is---> "+common.getTitle());


		
		dataUtils.setSheetName("TD_ProductDetailsValidation");

		try {
			int i = 1;
			System.out.println("---> "+dataUtils.noOfRows());
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					//logger = report.startTest(getData("Scenario Description"));
					//logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {						
						//logger.log(LogStatus.PASS, "Home page is displayed");
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");

						// demo
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(
								"table[title=\"Task Type:Task View, Fastpath Code: TV:80\"]",
								"LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE030649\"] > tbody > tr > td",
								"LOL:: Win Field: Unity Menus").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Unity Menus");
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE030661\"] > tbody > tr > td",
								"LOL:: Win Field: Account payable").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected-Account payable");
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE030897\"] > tbody > tr > td",
								"LOL:: Win Field:AP Account ").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected-AP Account");
						common.getObjectByCssSelector(
								"table[title=\"Application: P0413M, Form: W0413MB, Version: UFN001\"] > tbody > tr > td",
								"LOL:: Win Field:Void Payment").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Void Payment");
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						// common.getObjectByXpath(hmPg.logout_button).click();
					} else{


						//logger.log(LogStatus.FAIL, "Home page is not displayed");
						//logger.log(LogStatus.INFO, "=======End Execution Report======");
						i++;
						//reportclosewindows();

					}


				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			} 
		} catch (Exception e) {
			//common.captureSS("Error found", LogStatus.FAIL);
			//reportclosewindows();
			System.out.println("In the second exception");
			addExceptionToReport(e.getMessage(), "","");
			/*System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);*/
		}

		
	}
	
	
	/*  Vikas
	 * BO21_Back_Order,
	 * C320_Work_Order_to_Bag_seed_then_Seed
	 * C321_CrossDock_ST.java
	 * CR80_multiple_stop_fleet_shipment.java
	 * DesktopExecutor.java
	 * DesktopLogin.java
	 * java_mail.java
	 * JavaEmail.java
	 * LOL_homepage_Generalinfo_verification.java
	 * LOL_OM_Create_order_counter_sales.java
	 */
	
	
	//element identification problem...
	public void C18476_counter_sales_order(String k) throws IOException, InterruptedException, TwfException {
		
		
		System.out.println("In the custom method.....-->");

		Start();

		System.out.println("----> title of the page is---> "+common.getTitle());
		
		
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					//logger = report.startTest(getData("Scenario Description"));
					//logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();

						// LOL_OrderManagement_Creation_RSO create_order = new
						// LOL_OrderManagement_Creation_RSO();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.order_management,
								"LOL:: Win Field: Order Management").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected-Order Management");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.counter_sales_order_management,
								"LOL:: Win Field: Counter sales Order management ").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Counter sales Order management");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.counter_sales_enter_order,
								"LOL:: Win Field: Counter Sale Enter Order ").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Counter Sale Enter Order");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));
						// LOL_Manage_Existing_Order_page manage_order = new
						// LOL_Manage_Existing_Order_page();
						// Selceting Add Image
						common.getObjectByXpath(manage_order.add_image).click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Add ");
						Thread.sleep(2000);

			
						common.validateObjectsDisplayed(new_order.enter_new_order_page_title,
								"Enter new Order page displayed");

						// Entering BP value

						common.getObjectById(new_order.enter_new_order_page_break_point_number).clear();
						common.getObjectById(new_order.enter_new_order_page_break_point_number).sendKeys("601312");
						//logger.log(LogStatus.INFO, "LOL:: Win Field: Entered BP value");

						Thread.sleep(1000);

						// Entering customer PO value

						// common.getObjectById("div0_214_360").click();
						common.getObjectById(new_order.enter_new_order_page_customer_po_field).clear();
						common.getObjectById(new_order.enter_new_order_page_customer_po_field).sendKeys("1234");

						// Entering Ship To field value

						common.getObjectByXpath(new_order.enter_new_order_page_ship_to_field).clear();
						common.getObjectByXpath(new_order.enter_new_order_page_ship_to_field).sendKeys("7297831");
						//logger.log(LogStatus.INFO, "LOL:: Win Field: Entered ship TO value value");
						Thread.sleep(2000);
						
						common.getObjectByCssSelector(new_order.enter_new_order_page_input_field, "Quantity Field").clear();
						common.getObjectByCssSelector(new_order.enter_new_order_page_input_field, "Quantity Field").sendKeys("1");					
						
						common.getObjectByXpath(new_order.enter_new_order_page_item_number).clear();
						common.getObjectByXpath(new_order.enter_new_order_page_item_number).sendKeys("1455787");
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Enter Quantity and item Number ");

						Thread.sleep(2000);
						common.getObjectByXpath(new_order.enter_new_order_page_out_of_state_rup).click();

						Thread.sleep(6000);

						common.getObjectByXpath(new_order.enter_new_order_price_histry).click();

						// Validating ERROR
						common.EqualIgnoreCase1(
								common.getObjectByXpath(new_order.enter_new_order_error_message).getText().trim(),
								"Cannot Access P4074 for Line Number 1.000");

						// Validating AG line
						System.out.println(common.getObjectByXpath(new_order.enter_new_order_get_line_type).getText());
						
						common.EqualIgnoreCase1(
								common.getObjectByXpath(new_order.enter_new_order_get_line_type).getText().trim(),
								"AG");

						// selecting submit and close button
						common.clickmultiple(2, new_order.enter_new_order_page_submit_close_button);
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected: Submit and close button ");

						// Selecting Row Exit button
						common.getObjectByXpath(oc_page.oc_row_exit_button).click();
						//logger.log(LogStatus.INFO, "LOL::Selected: Row Exit button");
						common.getObjectByXpath(oc_page.oc_row_price_histry).click();
						//logger.log(LogStatus.INFO, "LOL::Selected: Price Histry button");
						common.validateObjectsDisplayed(oc_page.oc_row_detail_grid, "Detail Grid ");

						// Back to Default frame
						driver.switchTo().defaultContent();
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						// common.getObjectByXpath(hmPg.logout_button).click();
					} else
						//logger.log(LogStatus.FAIL, "Home page is not displayed");
					//logger.log(LogStatus.INFO, "=======End Execution Report======");
					i++;
					//reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {
			//common.captureSS("Error found", LogStatus.FAIL);
			//reportclosewindows();
			//System.out.println("Exception occured during execution: " + e.getMessage());
			//Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");
		}
	}
	
	
	
	public void T154771_P5542101_Pick_slip_called_after_order_complaince(String k) throws IOException, InterruptedException, TwfException {
		
		Start();

		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					/*
					logger = report.startTest("P5542101 Pick slip called after order complaince ");
					logger.setDescription("P5542101 Pick slip called after order complaince ");
					logger.log(LogStatus.INFO, "=======Start Execution Report========");*/
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting supply
						common.getObjectByCssSelector(create_order.win_field_solution_supply, "LOL :: WinField supply")
								.click();
						;
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Supply");
						Thread.sleep(2000);

						// selecting supply reports
						common.getObjectByCssSelector(create_order.win_field_soultion_supply_reports,
								"LOL : Win Field : Supply selected").click();
						;
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Supply Reports");
						Thread.sleep(2000);

						// selecting po compliance
						common.getObjectByCssSelector(create_order.win_field_solution_po_compliance,
								"LOL : Win Field : PO selected").click();
						
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected PO compliance");
						Thread.sleep(2000);

						common.checkTitle("VersionÂ Prompting");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully on Version Prompting Screen");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");
						Thread.sleep(2000);

						System.out.println(
								"1st operent " + common.get_Options_selected_Text_value(".//*[@id='LeftOperand1']"));

						// common.Select_getSelected_option(".//*[@id='RightOperand2']");
						common.selectOption("RightOperand2", "Literal");

						common.getObjectByXpath("//input[@id='LITtf']").sendKeys("1234");
						Thread.sleep(1000);

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
						Thread.sleep(2000);
						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						/*
						 * //verify printer scressn
						 * common.EqualIgnoreCase(common.getObjectByXpath(
						 * ".//*[@id='jdeFormTitle0']").getText(),
						 * " Printer Selection");
						 * 
						 * Thread.sleep(2000);
						 */

						common.getObjectByXpath(".//*[@id='hc_OK']").click();
						Thread.sleep(2000);

						driver.switchTo().defaultContent();
						common.getObjectById("drop_mainmenu").click();

						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("View Job Status")).click();

						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- View Job status");

						Thread.sleep(4000);
						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						System.out.println(
								common.getObjectByXpath(".//*[@id='G0_1_R0']/td[8]/div").getText() + "dsadasd");

						System.out.println(common.getObjectByXpath(".//*[@id='G0_1_R0']/td[6]/div/a").getText());

						// System.out.println(common.getObjectByXpath(".//*[@id='G0_1_R1']/td[6]/div/a").getText());

						// R5542520_TEST005

						/*
						 * common.EqualIgnoreCase(common.getObjectByXpath(
						 * ".//*[@id='G0_1_R0']/td[6]/div/a").getText(),
						 * "R5543001_UPM001");
						 */
						/*String compare=common.getObjectByXpath(".//*[@id='G0_1_R0']/td[8]/div").getText();
						common.EqualIgnoreCase(compare, "D");*/
						
						// common.EqualIgnoreCase(common.getObjectByXpath(".//*[@id='G0_1_R1']/td[6]/div/a").getText(),
						// "R5542011_TEST005");

						System.out.println(driver.getTitle());

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} 
					else
						//logger.log(LogStatus.FAIL, "Home page is not displayed");
					//logger.log(LogStatus.INFO, "=======End Execution Report======");
					//reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

			//common.captureSS("Errors found", LogStatus.FAIL);
			//reportclosewindows();
			//System.out.println("Exception occured during execution: " + e.getMessage());
			//Assert.assertTrue(false);
			System.out.println("Exception occured..."+e.getMessage());
			addExceptionToReport(e.getMessage(), "", "");

		}
	}

	
	
	public void T202836_Order_Entry_Hard_Commit(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("P5542101 Pick slip called after order complaince ");
//					logger.setDescription("P5542101 Pick slip called after order complaince ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE032672\"] > tbody > tr > td",
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE032651\"] > tbody > tr > td",
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  order management");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE032657\"] > tbody > tr > td",
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field inventery sales");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(
								"table[title=\"Application: P5542101, Form: W5542101C, Version: UCS030\"] > tbody > tr > td",
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field sales order hard commit");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						common.getObjectByXpath(manage_order.add_image).click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Add ");
						Thread.sleep(2000);

						common.getObjectById(new_order.enter_new_order_page_break_point_number).clear();
						common.getObjectById(new_order.enter_new_order_page_break_point_number).sendKeys("601256");
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Entered branch point number");

						common.getObjectByXpath(new_order.enter_new_order_page_ship_to_field).click();
						common.getObjectByXpath(new_order.enter_new_order_page_ship_to_field).clear();
						common.getObjectByXpath(new_order.enter_new_order_page_ship_to_field).sendKeys("7027888");

						common.getObjectByXpath(new_order.enter_new_order_page_requested_date).click();
						common.getObjectByXpath(new_order.enter_new_order_page_requested_date).clear();
						common.getObjectByXpath(new_order.enter_new_order_page_requested_date).sendKeys("06/29/2017");

						common.getObjectByXpath(new_order.enter_new_order_page_item_number).click();

						// second Quantitry value in grid
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[3]/div/input").click();
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[3]/div/input").clear();
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[3]/div/input").sendKeys("10");

						//
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[7]/div/input").click();
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[7]/div/input").clear();
						common.getObjectByXpath(".//*[@id='G0_215_20_R1']/td[7]/div/input").sendKeys("10");

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
						//logger.log(LogStatus.PASS,
						//		"LOL:: Win Field: Navigated succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");
						Thread.sleep(2000);

						System.out.println(
								"1st operent " + common.get_Options_selected_Text_value(".//*[@id='LeftOperand1']"));

						// common.Select_getSelected_option(".//*[@id='RightOperand2']");
						common.selectOption("RightOperand2", "Literal");

						common.getObjectByXpath("//input[@id='LITtf']").sendKeys("1234");
						Thread.sleep(1000);

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
						Thread.sleep(2000);
						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						/*
						 * //verify printer scressn
						 * common.EqualIgnoreCase(common.getObjectByXpath(
						 * ".//*[@id='jdeFormTitle0']").getText(),
						 * " Printer Selection");
						 * 
						 * Thread.sleep(2000);
						 */

						common.getObjectByXpath(".//*[@id='hc_OK']").click();
						Thread.sleep(2000);

						driver.switchTo().defaultContent();
						common.getObjectById("drop_mainmenu").click();

						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("View Job Status")).click();

						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- View Job status");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						System.out.println(
								common.getObjectByXpath(".//*[@id='G0_1_R0']/td[8]/div").getText() + "dsadasd");

						System.out.println(common.getObjectByXpath(".//*[@id='G0_1_R0']/td[6]/div/a").getText());

						// System.out.println(common.getObjectByXpath(".//*[@id='G0_1_R1']/td[6]/div/a").getText());

						// R5542520_TEST005

						/*
						 * common.EqualIgnoreCase(common.getObjectByXpath(
						 * ".//*[@id='G0_1_R0']/td[6]/div/a").getText(),
						 * "R5543001_UPM001");
						 */
						common.EqualIgnoreCase(common.getObjectByXpath(".//*[@id='G0_1_R0']/td[8]/div").getText(), "D");

						// common.EqualIgnoreCase(common.getObjectByXpath(".//*[@id='G0_1_R1']/td[6]/div/a").getText(),
						// "R5542011_TEST005");

						System.out.println(driver.getTitle());

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
					} else
						//logger.log(LogStatus.FAIL, "Home page is not displayed");
					//logger.log(LogStatus.INFO, "=======End Execution Report======");
					//reportclosewindows();
						i++;
						
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {
//
//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	public void T266337_Pick_Confirm(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T266337 Pick Confirm");
//					logger.setDescription("T266337 Pick Confirm ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field picking and shipping
						common.getObjectByCssSelector(create_order.win_field_picking_and_chipping,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  picking and shipping");
						Thread.sleep(2000);

						// selecting win field pick confirmation
						common.getObjectByCssSelector(create_order.win_field_picking_confirmation,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field pick confirmation");
						Thread.sleep(2000);

						// selecting win field selscted pick confirm ASC
						common.getObjectByCssSelector(create_order.win_field_pick_confirm_ASC,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Pick confirm ASC");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						System.out.println(driver.getTitle());

						common.checkTitle("PickÂ ConfirmÂ ASCÂ (526)Â -Â WorkÂ withÂ PickÂ Confirmation");

						// providing order number
						common.getObjectByXpath(create_order.pick_confirm_page_order_number).click();
						common.getObjectByXpath(create_order.pick_confirm_page_order_number).clear();
						common.getObjectByXpath(create_order.pick_confirm_page_order_number).sendKeys("*");
						//logger.log(LogStatus.PASS, "LOL:: Win Field: successfullly entered Order Number");

						common.Find();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected find");

						Thread.sleep(1000);

						common.PDP_validateObjectsDisplayed(create_order.win_field_sales_order_line,
								"Sales Order line will appear");

						common.Form_Exit();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Form Exit");

						common.getObjectByXpath(create_order.confirm_shows_rows).click();

						Thread.sleep(8000);

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected COnfirm shown Rows");

						// common.getObjectByXpath(".//*[@id='G0_1_R0']/td[10]/div/input").sendKeys("DAM");

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Succesfully entered location");

						common.OK();
						common.OK();

						Thread.sleep(1000);

						common.select();
						Thread.sleep(1000);

						common.checkTitle("Pick Confirm ASC (526) - Pick Confirmation");

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	public void T246190_Put_Order_on_MH_hold_Manual(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T246190_Put_Order_on_MH_hold_Manual ");
//					logger.setDescription("T246190_Put_Order_on_MH_hold_Manual");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
//						logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_ware_house_user,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_order_management,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  order management");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_ware_inventryu,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field inventery sales");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_ware_order_hard_commit,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field sales order hard commit");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// second order number
						common.getObjectByXpath(create_order.win_field_order_number).click();
						common.getObjectByXpath(create_order.win_field_order_number).clear();
						common.getObjectByXpath(create_order.win_field_order_number).sendKeys("10");

						Thread.sleep(1000);

						common.Find();

						Thread.sleep(1000);

						common.IsObjectsDisplayed(create_order.win_field_The_order_number_in_grid,
								"LOL:: Win Field: The order number in grid ");

						common.getObjectByXpath(create_order.win_field_The_order_number_in_grid).click();

						common.select();

						Thread.sleep(1000);

						common.getObjectByXpath(create_order.win_field_edit_full_header).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Succesfully selected Edit full Header");

						//common.getObjectByXpath(create_order.win_field_edit_full_header).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Succesfully selected Edit full Header");

						common.checkTitle("Order Header Revisions");

						// Entering Hold code
						common.getObjectByXpath(create_order.win_field_hold_code).clear();
						common.getObjectByXpath(create_order.win_field_hold_code).click();
						common.getObjectByXpath(create_order.win_field_hold_code).sendKeys("MH");

						// selecting order number
						//common.getObjectByXpath(create_order.win_field_orde_number).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Navigated succesfuly selected order number");

						// seleting save and close
						common.getObjectByXpath(create_order.win_field_save_and_close).click();
//						logger.log(LogStatus.PASS,
//								"LOL:: Win Field: Navigated succesfully selected save and close button");

						// selecting submit and close
						common.getObjectByXpath(create_order.win_field_submit_and_close).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Navigated succesfuly submit and close");

						// blocked

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
//						logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");
		}
	}
	
	
	public void T203144_Print_Pick_ticket_Manual(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T203144_Print_Pick_ticket_Manual");
//					logger.setDescription("T203144_Print_Pick_ticket_Manual ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
//						logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field picking and shipping
						common.getObjectByCssSelector(create_order.win_field_picking_and_chipping,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  picking and shipping");
						Thread.sleep(2000);

						// selecting win field pick Ticket manual print
						common.getObjectByCssSelector(create_order.win_field_pick_manual,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field pick Ticket manual print");
						Thread.sleep(2000);

						// selecting win field selscted pick confirm ASC
						common.getObjectByCssSelector(create_order.win_field_pick_manual_hard_commit,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Pick manual hard commit");
						Thread.sleep(2000);

						common.checkTitle("VersionÂ Prompting");
//						logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully on Version Prompting Screen");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
//						logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");
						Thread.sleep(2000);

						// common.Select_getSelected_option(".//*[@id='RightOperand2']");
						common.selectOption("RightOperand1", "Literal");

						common.getObjectByXpath("//input[@id='LITtf']").sendKeys("1234");
						Thread.sleep(1000);

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: selected ");

						Thread.sleep(2000);
						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						common.checkTitle("Printer Selection");
						common.OK();
						Thread.sleep(2000);

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
//						logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						
						try{
							driver.switchTo().alert().accept();
						}catch(NoAlertPresentException e){
							
							System.out.println("No alert present....");
						}
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	public void T248981_Release_Sales_Order_from_MH_Hold(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T248981_Release_Sales_Order_from_MH_Hold");
//					logger.setDescription("T248981_Release_Sales_Order_from_MH_Hold ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
//						logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting operations
						common.getObjectByCssSelector(create_order.win_field_operation, "LOL:: Win Field:  Operation")
								.click();
						
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field operation");
						Thread.sleep(2000);

						// selecting Marketing operations
						common.getObjectByCssSelector(create_order.win_field_marketing_operation,
								"LOL:: Win Field: Marketing  Operation").click();
						;
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Marketing operation");
						Thread.sleep(2000);

						// selecting Sales order holds
						common.getObjectByCssSelector(create_order.win_field_sales_order_holds,
								"LOL:: Win Field: Sales Order holds").click();
						;
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Sales order holds");
						Thread.sleep(2000);

						// selecting Manual hold order release
						common.getObjectByCssSelector(create_order.win_field_manual_hold_order_release,
								"LOL:: Win Field:Sales Order holds").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Sales order holds");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// selecting Order Number
						common.getObjectByXpath(create_order.manual_order_number).click();
						common.getObjectByXpath(create_order.manual_order_number).clear();
						common.getObjectByXpath(create_order.manual_order_number).sendKeys("30528880");
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Entered Order Number");
						Thread.sleep(2000);
						// 30806176

						// selecting find
						common.Find();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected find");

						// selecting order line
						common.getObjectByXpath(create_order.win_field_sales_order_line).click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: selected order line from GRID");

						// select option
						common.select();
						Thread.sleep(1000);

						common.checkTitle("Manual Hold (MH) Order Release - Password Confirmation");

						common.OK();

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
//						logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
			// Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	public void T204240_P3111_Parts_List_Create_Work_Order(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T204240_P3111_Parts_List_Create_Work_Order");
//					logger.setDescription("T204240_P3111_Parts_List_Create_Work_Order ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field manufacturing
						common.getObjectByCssSelector(create_order.winf_manaufacturing,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected manaufacturing");
						Thread.sleep(2000);

						// selecting win Field ASC
						common.getObjectByCssSelector(create_order.winf_asc,
								"LOL:: Win Field: Win Field Solution Winf ASC").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected winf asc");
						Thread.sleep(2000);

						// selecting win Field ASC
						common.getObjectByCssSelector(create_order.winf_asc,
								"LOL:: Win Field: Win Field Solution Winf ASC").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected winf asc");
						Thread.sleep(2000);

						// Application: P48013, Form: W48013J, Version: UPM001

						// selecting win Field Work Order Entry
						common.getObjectByCssSelector(create_order.Work_Order_Entry,
								"LOL:: Win Field: Win Field Solution win Field Work Order Entry").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected win Field Work Order Entry");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// validating work with shipment inquiry page
						common.checkTitle("Work Order Entry (WO) - Work With Manufacturing Work Orders");
						Thread.sleep(2000);

						// selecting find
						common.Find();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected find");

						Thread.sleep(1000);

						common.PDP_validateObjectsDisplayed(create_order.win_field_sales_order_line,
								"Sales Order line will appear");

						// selecting orderline form grid

						common.getObjectByXpath(create_order.win_field_sales_order_line).click();
						Thread.sleep(1000);

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Order line form GRID");

						// selecting Row Exit button
						common.Row();
						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Row Exit Button");

						// Selecting Parts List
						common.getObjectByXpath(".//*[@id='HE0_67']/tbody/tr/td[2]/span/nobr").click();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Send to Parts List");

						common.checkTitle("Work Order Entry (WO) - Work Order Parts List");

						common.Close();
						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Close");

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
					//reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {
//			logger.log(LogStatus.FAIL, "LOL:: Win Field: Unexpected Failure");
//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), ""	, "");

		}
	}
	
	
	public void T307481_P5643250_Receipt_Routing(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T245526_P5643250_Receipt_Routing");
//					logger.setDescription("T245526_P5643250_Receipt_Routing");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution houlse user
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field winf_inbound_inventry
						common.getObjectByCssSelector(create_order.winf_inbound_inventry,
								"LOL:: Win Field: Win Field INbound inventry").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Inbound Inventry");
						Thread.sleep(2000);

						// selecting win field Enter transfer OT
						common.getObjectByCssSelector(create_order.winf_enter_transfer_ot,
								"LOL:: Win Field: Win Field Enter transsfer ot").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Enter transfer OT");
						Thread.sleep(2000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Work With Routing Statuses");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// selecting find
						common.Find();

						// Win Field sales Order Line
						common.getObjectByXpath(create_order.win_field_sales_order_line).click();
						Thread.sleep(1000);

						// selecting form
						common.Form();
						Thread.sleep(2000);

						// selecting move shown ROws
						common.getObjectByXpath(".//*[@id='HE0_115']/tbody/tr/td[2]/span/nobr").click();
						Thread.sleep(3000);
						common.checkTitle(
								"nter Transfer (OT, OL) Receipts - Receipt Routing Movement - Fetching Data Rows");

						Thread.sleep(50000);

						Thread.sleep(20000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);
						Thread.sleep(500000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Receipt Routing Movement");

						common.getObjectByXpath(".//*[@id='G0_1_R0']/td[3]/div/input").sendKeys("1");

						// selecting ok
						common.OK();

						// selecting ok
						common.OK();

						Thread.sleep(2000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Work With Routing Statuses");

						// selecting close
						common.Close();

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	public void T307480_P4205_Ship_Confirmation(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T269353_Fleet_Planning_for_Credit_Orders");
//					logger.setDescription("T269353_Fleet_Planning_for_Credit_Orders ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field picking and shipping
						common.getObjectByCssSelector(create_order.win_field_picking_and_chipping,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  picking and shipping");
						Thread.sleep(2000);

						// selecting work with shipment Confirmation
						common.getObjectByCssSelector(create_order.win_field_work_with_shipment_confirmation,
								"LOL:: Win Field: Win Field  work with shipments").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win field work with shipment COnfirmation");
						Thread.sleep(2000);
	
						
						// selecting win field pick confirmation
						common.getObjectByCssSelector(create_order.win_field_work_with_ship_confirm,
								"LOL:: Win Field: Win Field Fleet shiping processing").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field fleet ship Confirm");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));
					
						

						// validating work with shipment Conformation Page
						common.checkTitle("Ship Confirm (Standard) - Work with Shipment Confirmation");
						Thread.sleep(2000);

						// selecting find
						common.Find();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected find");

						Thread.sleep(1000);

						common.PDP_validateObjectsDisplayed(create_order.win_field_sales_order_line,
								"Sales Order line will appear");

						// selecting orderline form grid

						common.getObjectByXpath(create_order.win_field_sales_order_line).click();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Order line form GRID");

						// selecting form
						common.Form();
						Thread.sleep(2000);
						
						// selecting move shown ROws
						common.getObjectByXpath(".//*[@id='HE0_80']/tbody/tr/td[2]/span/nobr").click(); 
						Thread.sleep(3000);
						
						Thread.sleep(50000);

						Thread.sleep(20000);

						Thread.sleep(50000);
						
						common.checkTitle("Ship Confirm (Standard) - Shipment Confirmation");
						
						//select okey
						common.OK();
						
						//verifying Ship Confirm (Standard) - Enter Shipment Tracking Numbers
						common.checkTitle("Ship Confirm (Standard) - Enter Shipment Tracking Numbers");
						
						
						common.getObjectByXpath(".//*[@id='G0_1_R0']/td[3]/div/input").sendKeys("1234");
						
						Thread.sleep(2000);
						
						Thread.sleep(2000);
						
						common.checkTitle("Ship Confirm (Standard) - Work with Shipment Confirmation");
						
					
						/*// Selecting Send To OTM button
						common.getObjectByXpath(".//*[@id='HE0_79']/tbody/tr/td[2]/span/nobr").click();

						logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Send to OTM option");*/

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "","");

		}
	}
	
	
	public void T245520_R5543001_PO_Compliance_OT(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T245520_R5543001_PO_Compliance_OT ");
//					logger.setDescription("T245520_R5543001_PO_Compliance_OT ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting supply
						common.getObjectByCssSelector(create_order.win_field_solution_supply, "LOL :: WinField supply")
								.click();
						;
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Supply");
						Thread.sleep(2000);

						// selecting supply reports
						common.getObjectByCssSelector(create_order.win_field_soultion_supply_reports,
								"LOL : Win Field : Supply selected").click();
						
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Supply Reports");
						Thread.sleep(2000);

						// selecting po compliance
						common.getObjectByCssSelector(create_order.win_field_solution_po_compliance,
								"LOL : Win Field : PO selected").click();
						
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected PO compliance");
						Thread.sleep(2000);

						common.checkTitle("VersionÂ Prompting");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully on Version Prompting Screen");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");
						Thread.sleep(2000);

						common.selectOption("RightOperand2", "Literal");

						common.getObjectByXpath("//input[@id='LITtf']").sendKeys("1234");
						Thread.sleep(1000);

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
						Thread.sleep(2000);
						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						common.OK();

						Thread.sleep(2000);

						driver.switchTo().defaultContent();

						common.getObjectById("drop_mainmenu").click();

						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("View Job Status")).click();

						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- View Job status");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						common.getObjectByXpath(".//*[@id='qbeRow0_1']/td[6]/div/nobr/input")
								.sendKeys("R5543001_UPM001");

						common.Find();

						Thread.sleep(3000);

						common.validateFirstRow(5, "R5543001_UPM001", 7, "D");

						System.out.println(driver.getTitle());
						common.checkTitle("View Job Status - Submitted Job Search");

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	public void T246993_R5542520_Pick_Slip(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T246993_R5542520_Pick_Slip ");
//					logger.setDescription("T246993_R5542520_Pick_Slip ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						//logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution houlse user
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field picking and shipping
						common.getObjectByCssSelector(create_order.win_field_picking_and_chipping,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  picking and shipping");
						Thread.sleep(2000);

						// selecting winfield pick manual ticket
						common.getObjectByCssSelector(create_order.winF_pick_manual_ticket,
								"LOL : Win Field : pick manual ticket selected").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Maual ticket");
						Thread.sleep(2000);

						// selecting print pick ticket hard commit
						common.getObjectByCssSelector(create_order.print_pick_ticket_hard_commit,
								"LOL : Win Field : PO selected").click();
						
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Print pick Ticket hard commit");
						Thread.sleep(2000);

						common.checkTitle("VersionÂ Prompting");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully on Version Prompting Screen");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
						//logger.log(LogStatus.PASS,
								//"LOL:: Win Field: Navigated succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");
						Thread.sleep(2000);

						common.selectOption("RightOperand1", "Literal");

						common.getObjectByXpath("//input[@id='LITtf']").sendKeys("1234");
						Thread.sleep(1000);

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
						Thread.sleep(2000);
						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						common.OK();

						Thread.sleep(2000);

						driver.switchTo().defaultContent();

						common.getObjectById("drop_mainmenu").click();

						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("View Job Status")).click();

						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- View Job status");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						common.getObjectByXpath(".//*[@id='qbeRow0_1']/td[6]/div/nobr/input")
								.sendKeys("R5542520_UPM002");

						common.Find();

						Thread.sleep(3000);

						common.validateFirstRow(5, "R5542520_UPM002", 7, "D");

						System.out.println(driver.getTitle());
						common.checkTitle("View Job Status - Submitted Job Search");

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	
	public void T245526_P5643250_Receipt_Routing(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T245526_P5643250_Receipt_Routing");
//					logger.setDescription("T245526_P5643250_Receipt_Routing");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
//						logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution houlse user
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field winf_inbound_inventry
						common.getObjectByCssSelector(create_order.winf_inbound_inventry,
								"LOL:: Win Field: Win Field INbound inventry").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Inbound Inventry");
						Thread.sleep(2000);

						// selecting win field Enter transfer OT
						common.getObjectByCssSelector(create_order.winf_enter_transfer_ot,
								"LOL:: Win Field: Win Field Enter transsfer ot").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Enter transfer OT");
						Thread.sleep(2000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Work With Routing Statuses");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// selecting find
						common.Find();

						// Win Field sales Order Line
						common.getObjectByXpath(create_order.win_field_sales_order_line).click();
						Thread.sleep(1000);

						// selecting form
						common.Form();
						Thread.sleep(2000);

						// selecting move shown ROws
						common.getObjectByXpath(".//*[@id='HE0_115']/tbody/tr/td[2]/span/nobr").click(); 
						Thread.sleep(3000);
						common.checkTitle(
								"nter Transfer (OT, OL) Receipts - Receipt Routing Movement - Fetching Data Rows");

						Thread.sleep(50000);

						Thread.sleep(20000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						Thread.sleep(50000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Receipt Routing Movement");

						common.getObjectByXpath(".//*[@id='G0_1_R0']/td[3]/div/input").sendKeys("1");

						// selecting ok
						common.OK();

						// selecting ok
						common.OK();

						Thread.sleep(2000);

						common.checkTitle("Enter Transfer (OT, OL) Receipts - Work With Routing Statuses");

						// selecting close
						common.Close();

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
//						logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	public void T269353_Fleet_Planning_for_Credit_Orders(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {

//					logger = report.startTest("T269353_Fleet_Planning_for_Credit_Orders");
//					logger.setDescription("T269353_Fleet_Planning_for_Credit_Orders ");
//					logger.log(LogStatus.INFO, "=======Start Execution Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
//						logger.log(LogStatus.PASS, "Home page is displayed");

						// validating home page elements
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected- LOL:Menu");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						// selecting win field solution
						common.getObjectByCssSelector(create_order.win_field_house_user,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected ware house user");
						Thread.sleep(2000);

						// selecting win field picking and shipping
						common.getObjectByCssSelector(create_order.win_field_picking_and_chipping,
								"LOL:: Win Field: Win Field Solution").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field  picking and shipping");
						Thread.sleep(2000);

						// selecting work with shipments
						common.getObjectByCssSelector(create_order.win_field_work_with_shipment,
								"LOL:: Win Field: Win Field  work with shipments").click();
//						logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win field work with shipments");
						Thread.sleep(2000);

						// selecting win field pick confirmation
						common.getObjectByCssSelector(create_order.win_field_fleet_shipping_processing,
								"LOL:: Win Field: Win Field Fleet shiping processing").click();
					//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field fleet ship processing");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// validating work with shipment inquiry page
						common.checkTitle("Fleet Shipment Processing (530) - Work with Shipment Inquiry");
						Thread.sleep(2000);

						// selecting find
						common.Find();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected find");

						Thread.sleep(1000);

						common.PDP_validateObjectsDisplayed(create_order.win_field_sales_order_line,
								"Sales Order line will appear");

						// selecting orderline form grid

						common.getObjectByXpath(create_order.win_field_sales_order_line).click();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Order line form GRID");

						// selecting Row Exit button
						common.Row();
						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Row Exit Button");

						// Selecting Send To OTM button
						common.getObjectByXpath(".//*[@id='HE0_79']/tbody/tr/td[2]/span/nobr").click();

						//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected Send to OTM option");

						// Back to Default frame
						driver.switchTo().defaultContent();

						common.getObjectByXpath(hmPg.logout_arrow).click();
						//logger.log(LogStatus.PASS, "LOL::Selected: Log Out Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						common.getObjectByXpath(hmPg.logout_button).click();
						driver.switchTo().alert().accept();
					} else
//						logger.log(LogStatus.FAIL, "Home page is not displayed");
//					logger.log(LogStatus.INFO, "=======End Execution Report======");
//					reportclosewindows();
						i++;
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {

//			common.captureSS("Errors found", LogStatus.FAIL);
//			reportclosewindows();
//			System.out.println("Exception occured during execution: " + e.getMessage());
//			Assert.assertTrue(false);
			addExceptionToReport(e.getMessage(), "", "");

		}
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------
	/*Vivek utils
	 */
	//-------------------------------------------------------------------------------------------------------------------------
	
	public void C18484_kit_selection(String k) throws IOException, InterruptedException, TwfException {
		
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					// logger = report.startTest(getData("Scenario
					// Description"));
					// logger.log(LogStatus.INFO, "=======Start Execution
					// Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						// logger.log(LogStatus.PASS, "Home page is displayed");
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();

						LOL_OrderManagement_Creation_RSO create_order = new LOL_OrderManagement_Creation_RSO();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- LOL:Menu");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						//logger.log(LogStatus.PASS, "LOL:: Win Field: Selected Win Field Solution");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.order_management,
								"LOL:: Win Field: Order Management").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected-Order Management");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.sales_order_processing,
								"LOL:: Win Field: Salaes Order Processing ").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Sales Order Processing");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.sales_order, "LOL:: Win Field: Salaes Order(SO) ")
								.click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Sales Order");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.enter_sales_order,
								"LOL:: Win Field: Enter Sales Order (SO) W/ Pick Slip NO HARD COMMIT ").click();
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Selected-
						// Enter Sales Order (SO) W/ Pick Slip NO HARD COMMIT");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));
						LOL_Manage_Existing_Order_page manage_order = new LOL_Manage_Existing_Order_page();

						common.getObjectByXpath(manage_order.add_image).click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Add ");
						Thread.sleep(2000);
						LOL_Order_Header_Revisions_page revision_order = new LOL_Order_Header_Revisions_page();

						common.isExists(revision_order.order_header_revision_page_title);
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Displayed: Order Header Revision page");

						// Entering Ship To number
						common.getObjectById(revision_order.ship_to_filed).click();
						common.getObjectById(revision_order.ship_to_filed).clear();
						common.getObjectById(revision_order.ship_to_filed).sendKeys(getData("ShipToNumber"));
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Entered
						// Ship To Number and error messages are not
						// displayed");

						// Entering Branch Plant
						common.getObjectById(revision_order.branch_plant).click();
						common.getObjectById(revision_order.branch_plant).clear();
						common.getObjectById(revision_order.branch_plant).sendKeys(getData("BranchPlant"));
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Entered
						// Branch Plant ");

						common.getObjectById(revision_order.customer_po).click();

						// selecting Save and continue
						common.getObjectById(revision_order.save_and_continue_button).click();
						Thread.sleep(2000);
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected: Save and Continue button ");

						LOL_Enter_New_Order_page new_order = new LOL_Enter_New_Order_page();

						common.validateObjectsDisplayed(new_order.enter_new_order_page_title,
								"Enter new Order page displayed");

						// Getting value from SOLDTO Field
						String value = driver.findElement(By.xpath(new_order.enter_new_order_page_sold_to_field))
								.getAttribute("value");
						Assert.assertEquals(getData("SoldToNumber"), value);
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Veriied:
						// Sold To of 7028356 is automatically populated");
						common.getObjectByXpath(new_order.enter_new_order_page_item_number).click();
						common.getObjectByXpath(new_order.enter_new_order_page_item_number)
								.sendKeys(getData("ItemNumber"));
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Enter
						// Quantity and item Number ");

						common.getObjectByXpath(new_order.enter_new_order_page_out_of_state_rup).click();

						Thread.sleep(5000);
						common.clickmultiple(2, new_order.enter_new_order_page_ok_image);

						// selecting submit and close button
						common.getObjectByXpath(new_order.enter_new_order_page_submit_close_button).click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected: Submit and close button ");

						// selecting close button
						common.getObjectByXpath(new_order.enter_new_order_page_cancel_button).click();
						common.getObjectByXpath(new_order.enter_new_order_page_cancel_accept).click();

						// Back to Default frame
						driver.switchTo().defaultContent();
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						// logger.log(LogStatus.PASS, "LOL::Selected: Log Out
						// Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						// common.getObjectByXpath(hmPg.logout_button).click();
					} else
						// logger.log(LogStatus.FAIL, "Home page is not
						// displayed");
						// logger.log(LogStatus.INFO, "=======End Execution
						// Report======");
						i++;
					//reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
			i++;
			}
		} catch (Exception e) {
			//common.captureSS("Error found", LogStatus.FAIL);
			//reportclosewindows();
			/*System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);*/
			
			addExceptionToReport(e.getMessage(), "", "");
		}
	}
	
	
	
	
	public void C18487_P42101_sales_order_entry_3(String k) throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_ProductDetailsValidation");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					// logger = report.startTest(getData("Scenario
					// Description"));
					// logger.log(LogStatus.INFO, "=======Start Execution
					// Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						// logger.log(LogStatus.PASS, "Home page is displayed");
						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector("table[title=\"Task Type:Task View, Fastpath Code: TV:80\"]",
								"LOL:: Win Field: LOL:Menu").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- LOL:Menu");
						Thread.sleep(2000);
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE031804\"] > tbody > tr > td",
								"LOL:: Win Field: Win Field Solution").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Selected
						// Win Field Solution");
						Thread.sleep(2000);
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE031019\"] > tbody > tr > td",
								"LOL:: Win Field: Order Management").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected-Order Management");
						Thread.sleep(2000);
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE031267\"] > tbody > tr > td",
								"LOL:: Win Field: Salaes Order Processing ").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Sales Order Processing");
						Thread.sleep(2000);
						common.getObjectByCssSelector(
								"table[title=\"Task Type: Folder, Fastpath Code: JDE031044\"] > tbody > tr > td",
								"LOL:: Win Field: Salaes Order(SO) ").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Sales Order");
						Thread.sleep(2000);
						common.getObjectByCssSelector(
								"table[title=\"Application: P5542101, Form: W5542101C, Version: UCS001\"] > tbody > tr > td",
								"LOL:: Win Field: Enter Sales Order (SO) W/ Pick Slip NO HARD COMMIT ").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Enter Sales Order (SO) W/ Pick Slip NO HARD
						// COMMIT");
						Thread.sleep(2000);

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));
						common.getObjectByXpath(".//*[@id='C0_366']").sendKeys(getData("OrderNumber"));
						common.getObjectByXpath(".//*[@id='C0_170']").click();
						common.getObjectByXpath(".//*[@id='G0_41_R0']/td[3]/div").isDisplayed();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Displayed Searched order item ");

						driver.switchTo().defaultContent();
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						// logger.log(LogStatus.PASS, "LOL::Selected: Log Out
						// Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						// common.getObjectByXpath(hmPg.logout_button).click();
					} else
						// logger.log(LogStatus.FAIL, "Home page is not
						// displayed");
						// logger.log(LogStatus.INFO, "=======End Execution
						// Report======");
						i++;
					//reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
				i++;
			}
		} catch (Exception e) {
			/*common.captureSS("Error found", LogStatus.FAIL);
			//reportclosewindows();
			System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);*/
			addExceptionToReport(e.getMessage(), "", "");
		}
	}
	
	
	
	
	
	public void C18034_Standard_Cost_Update_Standard_Raw_Materials(String k) throws IOException, InterruptedException, TwfException {
		Start();
		dataUtils.setSheetName("TD_OrderManagement_main");
		try {
			int i = 1;
			int row=dataUtils.noOfRows();
			
			System.out.println("Row--> "+row);
			while (i <= dataUtils.noOfRows()) {
				
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					// logger = report.startTest(" Exclude Agreement
					// commitment");
					// logger.setDescription("R5541056 Standard Cost Update
					// Standard Raw Materials - UFN001");
					// logger.log(LogStatus.INFO, "=======Start Execution
					// Report========");
					login.asuser(getData("UserName"), getData("Password"));
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						// logger.log(LogStatus.PASS, "Home page is displayed");

						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");

						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector(create_order.lol_menu, "LOL:: Win Field: LOL:Menu").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- LOL:Menu");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.win_field_solution,
								"LOL:: Win Field: Win Field Solution").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Selected
						// Win Field Solution");
						Thread.sleep(2000);

						common.getObjectByCssSelector(create_order.product_costing, "LOL:: Win_Field: Product costing")
								.click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Selected
						// Product costing");
						Thread.sleep(2000);
						common.getObjectByCssSelector(create_order.month_end_casting_task,
								"LOL:: Win_Field: MOnth End casting Task ").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field: Selected
						// MOnth End casting Task");
						Thread.sleep(2000);
						common.getObjectByCssSelector(create_order.National_average_calculation_07_costed_item,
								"LOL:: Win Field: National average Calculation o7 Costed item").click();
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Selected
						// National average Calculation o7 Costed item");
						Thread.sleep(2000);

						common.checkTitle("VersionÂ Prompting");
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Navigated
						// succesfully on Version Prompting Screen");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						// check bos selecting
						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Check box From Tool bar");
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Navigated
						// succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");

						Thread.sleep(2000);
						// Verify the data selection screen
						System.out.println(common.getObjectByXpath(".//*[@id='jdeFormTitle']").getText());
						common.EqualIgnoreCase1(common.getObjectByXpath(".//*[@id='jdeFormTitle']").getText(),
								"Data Selection ");

						common.select_option_from_toolbar(common.getObjectByXpath(create_order.select_ok_from_tool_bar),
								"LOL:: Win Field: ok from tool bar");
						Thread.sleep(2000);
						// Verify the process option

						common.EqualIgnoreCase1(common.getObjectByXpath(".//*[@id='jdeFormTitle']").getText(),
								"Processing Options ");

						// selecting ok
						common.select_option_from_toolbar(common.getObjectByXpath(create_order.select_ok_from_tool_bar),
								"LOL:: Win Field: ok from tool bar");
						Thread.sleep(2000);

						// Verify printer selection Room

						common.EqualIgnoreCase1(common.getObjectByXpath(".//*[@id='jdeFormTitle0']").getText(),
								" Printer Selection");

						// selecting Document Setup Tab
						common.select_option_from_toolbar(common.getObjectByXpath(".//*[@id='CT0_30.2']"),
								"LOL:: Win Field: Document setup Tab");

						// checked casv option
						common.select_CheckBox(common.getObjectByXpath(".//*[@id='C0_78']"),
								"LOL::WinField: CSV checkbox");

						Thread.sleep(2000);

						// selceting ok
						common.select_option_from_toolbar(common.getObjectByXpath(".//*[@id='hc_OK']"),
								"LOL:: Win Field: ok from tool bar");

						// logger.log(LogStatus.INFO,"LOL:: Win Field:Standard
						// Cost Update Standard Raw Materials is Submitted
						// succesfully");

						// Navigating to action menu
						driver.navigate().refresh();
						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("View Job Status")).click();

						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- View Job status");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						common.select_CheckBox(common.getObjectByXpath(".//*[@id='G0_1_R0']/td[1]/div/input"),
								"LOL:: WinField: appropriate serve");

						common.select_option_from_toolbar(common.getObjectByXpath(create_order.select_ok_from_tool_bar),
								"LOL:: Win Field: ok from tool bar");

						Thread.sleep(2000);
						common.select_option_from_toolbar(common.getObjectByXpath(".//*[@id='hc_OK']"),
								"LOL:: Win Field: ok from tool bar");

						common.select_option_from_toolbar(common.getObjectByXpath("//*[@id='ROW_EXIT_BUTTON']"),
								"LOL:: WinField: Rows");

						
						System.out.println("Reached here....");
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// submitted job screen opens");

						// logger.log(LogStatus.PASS, "LOL:: Win Field: Report
						// begins downloading");

						Thread.sleep(2000);

						// Back to Default frame
						driver.switchTo().defaultContent();
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						// logger.log(LogStatus.PASS, "LOL::Selected: Log Out
						// Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");
						
					} else{
						
						i++;
					}
						// logger.log(LogStatus.FAIL, "Home page is not
						// displayed");
						// logger.log(LogStatus.INFO, "=======End Execution
						// Report======");
						i++;
					//reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
			}
		} catch (Exception e) {
			/*common.captureSS("Error found", LogStatus.FAIL);
			reportclosewindows();
			System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);*/
			addExceptionToReport(e.getMessage(), "", "");
		}
	}
	
	
	
	
	
	
	public void T158182_R5542051_Build_Order_Allocation_Ledger_Sales_Allocation(String k)
			throws IOException, InterruptedException, TwfException {
		
		Start();
		dataUtils.setSheetName("TD_OrderManagement_main");
		try {
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					// logger = report.startTest(" Exclude Agreement
					// commitment");
					// logger.setDescription("R5541056 Standard Cost Update
					// Standard Raw Materials - UFN001");
					// logger.log(LogStatus.INFO, "=======Start Execution
					// Report========");
					// login.asuser(getData("UserName"), getData("Password"));

					login.asuser("50909", "Welcome12");
					if (common.getObjectByXpath(hmPg.homepage_icon).isDisplayed()) {
						// logger.log(LogStatus.PASS, "Home page is displayed");

						common.validateObjectsDisplayed(hmPg.drop_main_menu, "Drop main menu");
						common.validateObjectsDisplayed(hmPg.drop_open_app, "Drop open app");
						common.validateObjectsDisplayed(hmPg.report_menu, "Report Menu");
						common.validateObjectsDisplayed(hmPg.drop_fav_menu, "drop fav menu");

						common.getObjectById("drop_mainmenu").click();
						common.getObjectByCssSelector("#ActionsSubMenuTable > tbody > tr > td",
								"LOL:: Win Field: Actions").click();
						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Actions");

						Thread.sleep(2000);

						driver.findElement(By.linkText("Submit Job")).click();

						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Selected- Submit Job status");

						Thread.sleep(1000);

						System.out.println(driver.getTitle());

						common.EqualIgnoreCase1(driver.getTitle(),
								"SubmitÂ JobÂ -Â WorkÂ WithÂ BatchÂ VersionsÂ -Â AvailableÂ Versions");

						driver.switchTo().frame(driver.findElement(By.id("e1menuAppIframe")));

						common.getObjectByXpath(".//*[@id='C0_11']").sendKeys("R5542050");

						// logger.log(LogStatus.PASS, "LOL:: Win Field:
						// Successfully entered batch number");

						common.Find();

						// logger.log(LogStatus.INFO, "LOL:: Win Field: Selected
						// find");

						Thread.sleep(1000);

						common.select_CheckBox(common.getObjectByXpath(".//*[@id='G0_1_R0']/td[1]/div/input"),
								"LOL:WinField: Sales allocation build");

						Thread.sleep(1000);

						common.select();

						common.select_CheckBox(common.getObjectByXpath(create_order.checkboxfrom_tool_bar),
								"LOL:WinField: Data selection Check box");
						// logger.log(LogStatus.PASS,"LOL:: Win Field: Navigated
						// succesfully checked Data Selection checkbox");

						// selecting submit form
						common.select_option_from_toolbar(
								common.getObjectByXpath(create_order.select_submit_from_tool_bar),
								"LOL:: Win Field: Selected submit from tool bar");

						System.out.println("gfdgdfg");
						System.out.println(common.get_Options_selected_Text_value(".//*[@id='LeftOperand1']"));
						// System.out.println(common.get_Options_selected_Text_value(".//*[@id='RightOperand1']"));
						// left Operant

						
						System.out.println("----------------------");
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='LeftOperand1']"),
								"Order Type (F4211) (DCTO) [BC]");

						
						
						// Comparion
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='Comparison1']"),
								"is equal to");
						System.out.println("Broke here--->");
						// Right Operant
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='RightOperand1']"),
								"\"S$,S3,S9,SD,SK,SO\"");

						// Left Operent 2
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='LeftOperand2']"),
								"Status Code - Next (F4211) (NXTR) [BC]");

						// Comparison
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='Comparison2']"),
								"is equal to");

						// Right Operant
						common.EqualIgnoreCase(common.get_Options_selected_Text_value(".//*[@id='RightOperand2']"),
								"\"620\"");

						common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();
						Thread.sleep(2000);

						// common.getObjectByXpath(create_order.select_ok_from_tool_bar).click();

						common.EqualIgnoreCase(common.getObjectByXpath(".//*[@id='jdeFormTitle0']").getText(),
								" Printer Selection");

						// common.getObjectByXpath(".//*[@id='hc_OK']").click();
						common.OK();
						;

						// common.getObjectByXpath(create_order.form_exit_button).click();

						/*
						 * common.getObjectById("drop_mainmenu").click();
						 * common.getObjectByCssSelector(create_order.lol_menu,
						 * "LOL:: Win Field: LOL:Menu").click();
						 * //logger.log(LogStatus.PASS,
						 * "LOL:: Win Field: Selected- LOL:Menu");
						 * Thread.sleep(2000);
						 */

						// Back to Default frame
						driver.switchTo().defaultContent();
						common.validateObjectsDisplayed(hmPg.logout_arrow, "Logout arrow");
						common.getObjectByXpath(hmPg.logout_arrow).click();
						// logger.log(LogStatus.PASS, "LOL::Selected: Log Out
						// Arrow");
						Thread.sleep(2000);
						common.validateObjectsDisplayed(hmPg.logout_button, "Logout button");

					} else
						// logger.log(LogStatus.FAIL, "Home page is not
						// displayed");
						// logger.log(LogStatus.INFO, "=======End Execution
						// Report======");
						i++;
					//reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));
				}
			i++;
			}
		} catch (Exception e) {
			
			
			addExceptionToReport(e.getMessage(), "", "");
			
			/*common.captureSS("Error found", LogStatus.FAIL);
			reportclosewindows();
			System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);*/
		}
	}
	
	

	//-------------------------------------------------------------------------------------------------------------------------
		/*@ Ends Integrated Scripts old LOL to FIRE
		 * @Author- Nitish.Nayak
		 */
	//--------------------------------------------------------------------------------------------------------------------------




	/*@Click on the first record to change the reason code
	 * 
	 */

	public void editReasonCode(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{


		driver=DriverFactory.getDriver();
		System.out.println("----> "+PageObjectLoader.getPageObject("LOL_Table").getTargetId());
		List<WebElement> elements=driver.findElements(By.xpath(PageObjectLoader.getPageObject("LOL_Table").getTargetId()));
		System.out.println("-->"+elements);
		if(!(elements.size()==0)){

			//System.out.println(elements);

			//get the item description nummber and reason code and 2nd item number

			ndItem=getElementByUsing("LOL_2ndItemCol").getText();
			//driver.findElement(By.xpath(PageObjectLoader.getPageObject("LOL_2ndItemCol").getTargetId())).getText();
			itemDesc=getElementByUsing("LOL_ItemDescriptionCol").getText();
			//driver.findElement(By.xpath(PageObjectLoader.getPageObject("LOL_ItemDescriptionCol").getTargetId())).getText();
			reasonCode=getElementByUsing("LOL_ReasonCodeCol").getText();
			//driver.findElement(By.xpath(PageObjectLoader.getPageObject("LOL_ReasonCodeCol").getTargetId())).getText();
			ediDOCNo=getElementByUsing("LOL_EDIDOCNumber1").getText();

			orderedQuan=getElementByUsing("LOLOrderedQuantityCol").getText();
			System.out.println("ndItem "+ndItem+"dsc--> "+itemDesc+"reasoncode "+reasonCode+" edino"+ediDOCNo+"Ordered"+orderedQuan);
			//click on the first record.
			Thread.sleep(3000);

			getElementByUsing("LOL_SelectCheckBox").click();
			//driver.findElement(By.xpath(PageObjectLoader.getPageObject("LOL_SelectCheckBox").getTargetId())).click();
			//driver.findElement(By.xpath(PageObjectLoader.getPageObject("LOL_Select").getTargetId())).click();

			//check whether the correct record has been selected for change.


		}else{

			addExceptionToReport("The table doesnot contains order for return and refusals...", "", "");
		}

	}

	/*Method to verify whether the correct record is selected for change and change the reason code and verify...
	 * 	
	 */

	public void changereasonCode(String data) throws TwfException, BiffException, InvalidFormatException, IOException, InterruptedException{

		System.out.println("-->val "+reasonCode);
		Thread.sleep(4000);
		System.out.println("--->val "+getElementByUsing("LOL_ReasonCodeTextBox").getAttribute("value"));
		System.out.println("-----------------------------------------------");
		System.out.println("--->2nd item value "+ndItem);
		System.out.println("--->2nd item value "+ getElementByUsing("LOL_2ndItemCol2").getAttribute("value"));
		System.out.println("-----------------------------------------------");
		System.out.println("--->item Description "+itemDesc);
		System.out.println("--->item Description "+ getElementByUsing("LOL_ItemDescriptionCol2").getAttribute("value"));
		System.out.println("-----------------------------------------------");

		if((reasonCode.equals(getElementByUsing("LOL_ReasonCodeTextBox").getAttribute("value")))
				&& ndItem.equals(getElementByUsing("LOL_2ndItemCol2").getAttribute("value"))
				&& itemDesc.equals(getElementByUsing("LOL_ItemDescriptionCol2").getAttribute("value"))){

			System.out.println("The record selected is correct");
			getElementByUsing("LOL_ReasonCodeTextBox").clear();
			getElementByUsing("LOL_ReasonCodeTextBox").sendKeys(data);
			Thread.sleep(5000);
			getElementByUsing("LOL_OKButton").click();

		}else{

			addExceptionToReport("The Record selected is not correct", "", "");

		}

	}


	/*filter the record with edidocnumber and check whether the record updated is found or not...
	 * 
	 */
	public void recordForComparisonWithoutSearchingEDINo(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{

		int i;
		String ediDOCNumber="",secondItem="",itemDescription="",reasonCo="",orderedQuantity="", allLn="", allStat=""
				, soldto="",shipto="", um="", branchplant="",userid="", programid="",workstationid="",dateupdated="",
				timeofday="", originalsalesn0="",location="",lotserialno="",docty="";
		boolean found=false;
		/*Actions build=new Actions(driver);
		build.click(getElementByUsing("LOLEDIDOCNumberTextBox")).sendKeys(ediDOCNo).sendKeys(Keys.ENTER).build().perform();*/

		driver=DriverFactory.getDriver();
		Thread.sleep(4000);
		List<WebElement> elements=driver.findElements(By.xpath(PageObjectLoader.getPageObject("LOL_Table").getTargetId()));
		System.out.println("List size is--> "+elements.size());
		for( i=0;i<1;i++){

			List<WebElement> col=elements.get(i).findElements(By.tagName("td"));

			
			for(int j=0;j<col.size();j++){

				String value=col.get(j).getText();
			
				switch(j){

				case 3: ediDOCNumber=value;
				break;
				
				case 4: docty=value;
				break;
				
				case 5: allLn=value;
				break;
				
				case 6: allStat=value;
				break;
				
				case 7: soldto=value;
				break;
				
				case 8: shipto=value;
				break;

				case 9: secondItem=value;
				break;

				case 10: itemDescription=value;
				break;

				case 11: orderedQuantity=value;
				break;
				
				case 12: um=value;
				break;

				case 13: reasonCo=value;
				break;
				
				case 14: branchplant=value;
				break;
				
				case 15: userid=value;
				break;
				
				case 16: programid=value;
				break;
				
				case 17: workstationid=value;
				break;
				
				case 18: dateupdated=value;
				break;
				
				case 19: timeofday=value;
				break;
				
				case 20: originalsalesn0=value;
				break;
				
				case 21: location=value;
				break;
				
				case 22: lotserialno=value;
				break;

				default: ;
			}
				

				if(ediDOCNumber.equals(ediDOCNo)&& docty.equals(DocTy)&& allLn.equals(allocLn)&& allStat.equals(allocStatus)&& soldto.equals(soldTo)
						&&shipto.equals(shipTo)&&secondItem.equals(ndItem)&& itemDescription.equals(itemDesc)&& orderedQuantity.equals(orderedQuan)
						&&um.equals(uM)&& reasonCo.equals(k)&& branchplant.equals(brachPlant)&& userid.equals(userId)&&programid.equals(programId)
						&&workstationid.equals(workstnId)&& dateupdated.equals(dateUpdated)&& timeofday.equals(timeOfDay)&&originalsalesn0.equals(originalSalesOrderNo)
						&&location.equals(locations)&&lotserialno.equals(lotSerialNo)){

					found=true;
					break;

				}

			}//nested for
			if(found){
				break;
			}else{

				continue;
			}

		}

		if(found){

			System.out.println("Updated record found....");
		}else{

			addExceptionToReport("The record updated with the return code is not populated...", "", "");

		}

		//for
		System.out.println(ediDOCNumber+" "+secondItem+" "+itemDescription+" "+orderedQuantity+" "+reasonCo);


	}
	
	
	/* @Capture all the column data for a particular EDI
	 * @Author-Nitish Nayak
	 */
	
	public WebElement captureColumnValueForAParticularEDIAndSelectTheEDI(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{
		
		WebElement rightClick=null;
		List<WebElement> cols=new LinkedList<WebElement>();
		driver=DriverFactory.getDriver();
		System.out.println("In the method...");
		//Thread.sleep(4000);
		System.out.println("---> "+PageObjectLoader.getPageObject("LOL_Table").getTargetId());
		List<WebElement> elements=driver.findElements(By.xpath(PageObjectLoader.getPageObject("LOL_Table").getTargetId()));
		if(!(elements.size()==0)){
			
			for(int i=0;i<1;i++){
				System.out.println("Row size--> "+elements.size());
				 cols=elements.get(i).findElements(By.tagName("td"));
				System.out.println("Cols size--> "+cols.size());
				for(int j=0;j<cols.size();j++){
					
					//capture all the data for selected allocation status...
						ediDOCNo=cols.get(3).getText();
						System.out.println("EDIDOC--> "+ediDOCNo);
						DocTy=cols.get(4).getText();
						System.out.println("DocTy --> "+DocTy);
						allocLn=cols.get(5).getText();
						System.out.println("allocLn --> "+allocLn);
						allocStatus=cols.get(6).getText();
						System.out.println("allocStatus --> "+allocStatus);
						soldTo=cols.get(7).getText();
						System.out.println("soldTo --> "+soldTo);
						shipTo=cols.get(8).getText();
						System.out.println("shipTo --> "+shipTo);
						ndItem=cols.get(9).getText();
						System.out.println("ndItem --> "+ndItem);
						itemDesc=cols.get(10).getText();
						System.out.println("itemDesc --> "+itemDesc);
						orderedQuan=cols.get(11).getText();
						System.out.println("orderedQuan --> "+orderedQuan);
						uM=cols.get(12).getText();
						System.out.println("um --> "+uM);
						reasonCode=cols.get(13).getText();
						System.out.println("reasonCode --> "+reasonCode);
						brachPlant=cols.get(14).getText();
						System.out.println("brachPlant --> "+brachPlant);
						userId=cols.get(15).getText();
						System.out.println("userId --> "+userId);
						programId=cols.get(16).getText();
						System.out.println("programId --> "+programId);
						workstnId=cols.get(17).getText();
						System.out.println("workstnId --> "+workstnId);
						dateUpdated=cols.get(18).getText();
						System.out.println("dateUpdated --> "+dateUpdated);
						timeOfDay=cols.get(19).getText();
						System.out.println("timeOfDay --> "+timeOfDay);
						originalSalesOrderNo=cols.get(20).getText();
						System.out.println("originalSalesOrderNo --> "+originalSalesOrderNo);
						locations=cols.get(21).getText();
						System.out.println("location --> "+locations);
						lotSerialNo=cols.get(22).getText();
						System.out.println("lotSerialNo --> "+lotSerialNo);
						
						//click on the found allocation status....
						cols.get(0).click();
						rightClick=cols.get(0);
						break;
						
				}//nested loop...
			
				
			}//for loop....
			
		}else{
			
			addExceptionToReport("The table doest not contains any record to use...", "", "");
		}
		return rightClick;
		
	}
	
	
	
	
public void captureColumnValueForAParticularEDISelectTheEDI(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{
		
		
		List<WebElement> cols=new LinkedList<WebElement>();
		driver=DriverFactory.getDriver();
		System.out.println("In the method...");
		//Thread.sleep(4000);
		System.out.println("---> "+PageObjectLoader.getPageObject("LOL_Table").getTargetId());
		List<WebElement> elements=driver.findElements(By.xpath(PageObjectLoader.getPageObject("LOL_Table").getTargetId()));
		if(!(elements.size()==0)){
			
			for(int i=0;i<1;i++){
				System.out.println("Row size--> "+elements.size());
				 cols=elements.get(i).findElements(By.tagName("td"));
				System.out.println("Cols size--> "+cols.size());
				for(int j=0;j<cols.size();j++){
					
					//capture all the data for selected allocation status...
						ediDOCNo=cols.get(3).getText();
						System.out.println("EDIDOC--> "+ediDOCNo);
						DocTy=cols.get(4).getText();
						System.out.println("DocTy --> "+DocTy);
						allocLn=cols.get(5).getText();
						System.out.println("allocLn --> "+allocLn);
						allocStatus=cols.get(6).getText();
						System.out.println("allocStatus --> "+allocStatus);
						soldTo=cols.get(7).getText();
						System.out.println("soldTo --> "+soldTo);
						shipTo=cols.get(8).getText();
						System.out.println("shipTo --> "+shipTo);
						ndItem=cols.get(9).getText();
						System.out.println("ndItem --> "+ndItem);
						itemDesc=cols.get(10).getText();
						System.out.println("itemDesc --> "+itemDesc);
						orderedQuan=cols.get(11).getText();
						System.out.println("orderedQuan --> "+orderedQuan);
						uM=cols.get(12).getText();
						System.out.println("um --> "+uM);
						reasonCode=cols.get(13).getText();
						System.out.println("reasonCode --> "+reasonCode);
						brachPlant=cols.get(14).getText();
						System.out.println("brachPlant --> "+brachPlant);
						userId=cols.get(15).getText();
						System.out.println("userId --> "+userId);
						programId=cols.get(16).getText();
						System.out.println("programId --> "+programId);
						workstnId=cols.get(17).getText();
						System.out.println("workstnId --> "+workstnId);
						dateUpdated=cols.get(18).getText();
						System.out.println("dateUpdated --> "+dateUpdated);
						timeOfDay=cols.get(19).getText();
						System.out.println("timeOfDay --> "+timeOfDay);
						originalSalesOrderNo=cols.get(20).getText();
						System.out.println("originalSalesOrderNo --> "+originalSalesOrderNo);
						locations=cols.get(21).getText();
						System.out.println("location --> "+locations);
						lotSerialNo=cols.get(22).getText();
						System.out.println("lotSerialNo --> "+lotSerialNo);
						
						//click on the found allocation status....
						cols.get(0).click();
						Thread.sleep(3000);
						getElementByUsing("LOL_Select").click();
						break;
						
				}//nested loop...
			
				
			}//for loop....
			
		}else{
			
			addExceptionToReport("The table doest not contains any record to use...", "", "");
		}
		
		
	}
	
	
	/*Search the EDI records according to the Allocation status....
	 * Author-Nitish Nayak
	 */
	
	
	public void filterRecordsAccordingToAllocationStatus(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{
		
		driver=DriverFactory.getDriver();
		Actions builder=new Actions(driver);
		builder.click(getElementByUsing("LOL_AllocatioStatusTexBox")).sendKeys(k).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(5000);
		
	}

	
	
	/*@Method will search the filtered record according to EDI doc number to verify whether record is present or not..
	 * Author- Nitish Nayak..
	 */
	
	public void searchWhetherRecordExists(String k) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{

		int i;
		String ediDOCNumber="",secondItem="",itemDescription="",reasonCo="",orderedQuantity="", allLn="", allStat=""
				, soldto="",shipto="", um="", branchplant="",userid="", programid="",workstationid="",dateupdated="",
				timeofday="", originalsalesn0="",location="",lotserialno="",docty="";
		
		boolean found=false;
		Actions build=new Actions(driver);
		build.click(getElementByUsing("LOLEDIDOCNumberTextBox")).sendKeys(ediDOCNo).sendKeys(Keys.ENTER).build().perform();

		driver=DriverFactory.getDriver();
		Thread.sleep(4000);
		if((wait(PageObjectLoader.getPageObject("LOL_Table").getTargetId(),10))==false){
			
			
			System.out.println("Record deleted sucessfully...");
			
		}else{
		
		List<WebElement> elements=driver.findElements(By.xpath(PageObjectLoader.getPageObject("LOL_Table").getTargetId()));
		
		for( i=0;i<elements.size();i++){

			List<WebElement> col=elements.get(i).findElements(By.tagName("td"));

			
			for(int j=0;j<col.size();j++){

				String value=col.get(j).getText();
			
				switch(j){

				case 3: ediDOCNumber=value;
				break;
				
				case 4: docty=value;
				break;
				
				case 5: allLn=value;
				break;
				
				case 6: allStat=value;
				break;
				
				case 7: soldto=value;
				break;
				
				case 8: shipto=value;
				break;

				case 9: secondItem=value;
				break;

				case 10: itemDescription=value;
				break;

				case 11: orderedQuantity=value;
				break;
				
				case 12: um=value;
				break;

				case 13: reasonCo=value;
				break;
				
				case 14: branchplant=value;
				break;
				
				case 15: userid=value;
				break;
				
				case 16: programid=value;
				break;
				
				case 17: workstationid=value;
				break;
				
				case 18: dateupdated=value;
				break;
				
				case 19: timeofday=value;
				break;
				
				case 20: originalsalesn0=value;
				break;
				
				case 21: location=value;
				break;
				
				case 22: lotserialno=value;
				break;

				default: ;
			}
				

				if(ediDOCNumber.equals(ediDOCNo)&& docty.equals(DocTy)&& allLn.equals(allocLn)&& allStat.equals(allocStatus)&& soldto.equals(soldTo)
						&&shipto.equals(shipTo)&&secondItem.equals(ndItem)&& itemDescription.equals(itemDesc)&& orderedQuantity.equals(orderedQuan)
						&&um.equals(uM)&& reasonCo.equals(reasonCode)&& branchplant.equals(brachPlant)&& userid.equals(userId)&&programid.equals(programId)
						&&workstationid.equals(workstnId)&& dateupdated.equals(dateUpdated)&& timeofday.equals(timeOfDay)&&originalsalesn0.equals(originalSalesOrderNo)
						&&location.equals(locations)&&lotserialno.equals(lotSerialNo)
						){

					found=true;
					break;

				}

			}//nested for
			if(found){
				break;
			}else{

				continue;
			}

		}

		if(found==false){

			System.out.println("Record Deleted Ducessfully....");
			Thread.sleep(5000);
		}else{

			addExceptionToReport("The record is not deleted successfully...", "", "");

		}

		System.out.println(ediDOCNumber+" "+secondItem+" "+itemDescription+" "+orderedQuantity+" "+reasonCo);
	}//end else part if table has some records...

	}
	

/*
 * @Wait for an element
 * @Author-Nitish Nayak
 */
 
public boolean wait(String ele, long timeout){
	boolean isPresent=false;
	try{
	WebDriverWait wait=new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(ele))));
	isPresent=true;
	}catch(Exception e){
		isPresent=false;
		System.out.println(e.getMessage());
		
	}
	return isPresent;
}


//--------------------------------------------------------------------------------------------------------------------------------------------------------
	/*@Starts All custom methods that are called in the Actions......
	 * @Author-Nitish Nayak
	*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	 /* @Verify delete link method...
	 * @Author-Nitish Nayak.
	 */
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void verifyDelete(Map<String,String> data) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{
		
		System.out.println("in the verify delete method...");
		filterRecordsAccordingToAllocationStatus(data.get("AllocationStatus"));
		WebElement ele=captureColumnValueForAParticularEDIAndSelectTheEDI("");
		System.out.println("returned webelement-->  "+ele);
		Actions builder=new Actions(driver);
		builder.contextClick(ele).click(getElementByUsing("LOL_EraseLink")).build().perform();
		//Thread.sleep(2000);
		searchWhetherRecordExists("");
		
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------	
	

//------------------------------------------------------------------------------------------------------------------------------------------------------------
	/*@Verify reason change method
	 * @Author-Nitish Nayak.
	 */
//-------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public void verifyReasonCodeChange(Map<String, String> data) throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException{
		
		System.out.println("In the verify reason code method..");
		filterRecordsAccordingToAllocationStatus(data.get("AllocationStatus"));
		captureColumnValueForAParticularEDISelectTheEDI("");
		getElementByUsing("LOL_Select").click();
		changereasonCode(data.get("ReasonCode"));
		recordForComparisonWithoutSearchingEDINo(data.get("ReasonCode"));
		
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
		/*@ENDS All custom methods that are called in the Actions......
		 * @Author-Nitish Nayak
		*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------


	
	

	
	
}




