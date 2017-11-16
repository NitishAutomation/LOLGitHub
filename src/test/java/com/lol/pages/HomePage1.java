package com.lol.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;
import com.lol.util.TestExecutor;

public class HomePage1 extends TestExecutor {

	WebElement element = null;
	List<WebElement> divs = null;
	List<WebElement> rows = null;

	//old
	public String create_Product_Specification = "//a[text()='Create Product Specification']";
	public String create_order_options_link = "dgfSPT:cl1";
	public String create_order_button = "//button[text()='Create']";
	public String quick_entry_icon = ".//*[contains(@id,'pt_r2:0:cil4')]";
	public String create_order_dropdown_icon = "//a[@class='jqTransformSelectOpen']";
	public String date_picker_text_field = "//input[@id='dgfSPT:pt_r2:0:id1::content']";
	public String po_text = "//input[@id='dgfSPT:pt_r2:0:it1::content']";
	
	public String Unity_Menu = "table[title=\"Task Type: Folder, Fastpath Code: JDE030649\"] > tbody > tr > td";
	public String accontPayable = "table[title=\"Task Type: Folder, Fastpath Code: JDE030661\"] > tbody > tr > td";
	public String AP_voucherEntry = "table[title=\"Task Type: Folder, Fastpath Code: JDE030896\"] > tbody > tr > td";
	public String AP_voucher_nonproduct = "table[title=\"Application: P0411, Form: W0411G, Version: UFN001\"] > tbody > tr > td";
	

	
	public String logout_arrow=".//*[@id='userSessionDropdownArrow']";
	public String logout_button=".//*[@id='signOutLinkDiv']/div[2]";
	
	//new
	public String homepage_icon=".//*[@id='drop_home']";
	public String drop_main_menu=".//*[@id='drop_mainmenu']";
	public String drop_open_app=".//*[@id='drop_openapps']";
	public String report_menu=".//*[@id='drop_reportmenu']";
	public String drop_fav_menu= ".//*[@id='drop_fav_menus']";
	public String Rec_menu_item=".//*[@id='recRptsMenuItem']";
	
	public String homepage_image=".//*[@id='listOCL_0']/table/tbody/tr/td[1]/img";
	public String submitted_job_search_image=".//*[@id='listOCL_1']/table/tbody/tr/td[1]/img";
	public String view_job_status_image=".//*[@id='listRRpt_WSJ']/table/tbody/tr/td[1]/img";
	public String manage_fav_image=".//*[@id='listFav_manageFavs']/table/tbody/tr/td[1]/img";

	public String LOL_Menu="table[title=\"Task Type:Task View, Fastpath Code: TV:80\"]";
	public String Winfield_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE031804\"] > tbody > tr > td";
	public String Action_Menu=".//*[@id='ActionsSubMenuTable']/tbody/tr/td[1]";
public String View_Job_Status=".//a[text()='View Job Status']";//table[title=\"Application: P986116, Form: W986116A, Version: ZJDE0001\"] > tbody > tr > td";
public String Action_submitJob=".//a[text()='Submit Job']";
public String Order_Management_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE031019\"] > tbody > tr > td";
	public String Order_Management_Inquiry_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE031091\"] > tbody > tr > td";
	public String Saler_order_processing_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE031267\"] > tbody > tr > td";
	public String Saler_order_SO_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE031044\"] > tbody > tr > td"; 
	public String SO_slip_NO_Commit="table[title=\"Application: P5542101, Form: W5542101C, Version: UCS001\"] > tbody > tr > td";
	public String Counter_SO_Menu="table[title=\"Task Type: Folder, Fastpath Code: JDE030824\"] > tbody > tr > td";
	public String SO_Counter_enter_SO_S$="table[title=\"Application: P5542101, Form: W5542101C, Version: UCS050\"] > tbody > tr > td";
	public String Creditandreturnmenu="table[title=\"Task Type: Folder, Fastpath Code: JDE031486\"] > tbody > tr > td";
	public String Creditandrebillmenu="table[title=\"Task Type: Folder, Fastpath Code: JDE031093\"] > tbody > tr > td";
	public String CAcreditwithRebill="table[title=\"Application: P4210, Form: W4210E, Version: UCS102\"] > tbody > tr > td";
	public String massnostockSN="table[title=\"Application: P5547001, Form: W5547001B, Version: UCS012\"] > tbody > tr > td";
	public String SalesandCreditOrderEnquiry="table[title=\"Application: P5542101, Form: W5542101C, Version: UCS299\"] > tbody > tr > td";
	public String PrepayContractManagement="table[title=\"Task Type: Folder, Fastpath Code: JDE030676\"] > tbody > tr > td";
	public String PrepayContractReport="table[title=\"Task Type: Folder, Fastpath Code: JDE031446\"] > tbody > tr > td";
	public String PrepayCPPreport="table[title=\"Report: R5538030, Version: UCS002\"] > tbody > tr > td";
	public String prepayEquipreport="table[title=\"Report: R5538030, Version: UCS003\"] > tbody > tr > td";
	
	
	public void click_create_order_options_link() throws TwfException {
		element = common1.getObjectById(create_order_options_link); 
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, "Create Order Option link is displayed and clicked");
		} else {
			//logger.log(LogStatus.FAIL, "Create Order Option link is not displayed");
			//common1.captureSS("Create Order Option link", LogStatus.FAIL);
		}

	}

	public void click_create_order() throws TwfException {
		element = common1.getObjectByXpath(create_order_button,"createorder");
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, "Create Order button is displayed and clicked");
		} else {

			//logger.log(LogStatus.FAIL, "Create Order button is not displayed");
			//common1.captureSS("Create Order Option link", LogStatus.FAIL);
		}

	}

	public void click_icon_in_createorder(String option) throws TwfException {
		element = common1.getObjectByXpath(".//*[text()='" + option + "']","Createordericon");
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, option + " icon is displayed and clicked");
		} else {
			//logger.log(LogStatus.FAIL, option + " icon is not displayed");
			//common1.captureSS(option + "not displayed", LogStatus.FAIL);
		}
	}
	
	public void change_delivery_date(String date) throws TwfException {

		element = common1.getObjectByXpath(this.date_picker_text_field,"changeDeliveryDate");

		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(date);
			//logger.log(LogStatus.PASS, "Entered delivery date as : "+date);
		} else {
			//logger.log(LogStatus.FAIL, "Delivery date option is not displayed");
			//common1.captureSS("Delivery date option", LogStatus.FAIL);
		}
	}
	public void createOrderFromHomepage(String crtOrdrOption) throws InterruptedException, TwfException
	{
		click_create_order_options_link();
		common1.sleep(1000);
		click_icon_in_createorder(crtOrdrOption);
		common1.sleep(3000);
		common1.selectOptionsInADropDown(getData("ListName"), create_order_dropdown_icon);
		if(getData("DeliveryDate").isEmpty() == false)
			change_delivery_date(getData("DeliveryDate"));
		common1.getObjectByXpath(po_text,crtOrdrOption).sendKeys(getData("PONumber"));
		//logger.log(LogStatus.INFO, "PO# Number Entered as :"+getData("PONumber"));
		click_create_order();
	}
}
