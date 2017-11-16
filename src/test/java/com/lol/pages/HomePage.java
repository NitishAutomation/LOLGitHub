package com.lol.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;
import com.lol.util.TestExecutor;

public class HomePage extends TestExecutor {

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
	

	
	public String logout_arrow=".//*[@id='userSessionDropdownArrow']";
	public String logout_button=".//*[@id='signOutLinkDiv']/div[2]";
	
	//new
	public String homepage_icon=".//*[@id='drop_home']";
	public String drop_main_menu=".//*[@id='drop_mainmenu']";
	public String drop_open_app=".//*[@id='drop_openapps']";
	public String report_menu=".//*[@id='drop_reportmenu']";
	public String drop_fav_menu= ".//*[@id='drop_fav_menus']";
	public String Rec_menu_item=".//*[@id='recRptsMenuItem']";
	public String navigator = ".//*[@id='drop_mainmenu']";
	public String openapps = ".//*[@id='drop_openapps']";
	public String reportmenu =".//*[@id='drop_reportmenu']";
	public String favorites = ".//*[@id='drop_fav_menus']";
	public String watchlists = ".//*[@id='drop_watchlist_menus']";
	public String username = ".//*[@id='usernameDiv']";
	public String carousel = ".//*[@id='caroBar']";
	public String homepage_image=".//*[@id='listOCL_0']/table/tbody/tr/td[1]/img";
	public String submitted_job_search_image=".//*[@id='listOCL_1']/table/tbody/tr/td[1]/img";
	public String view_job_status_image=".//*[@id='listRRpt_WSJ']/table/tbody/tr/td[1]/img";
	public String manage_fav_image=".//*[@id='listFav_manageFavs']/table/tbody/tr/td[1]/img";

	
	//Abinaya Script Page ICons:
	

	public void click_create_order_options_link() throws TwfException {
		element = common.getObjectById(create_order_options_link);
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, "Create Order Option link is displayed and clicked");
		} else {
			//logger.log(LogStatus.FAIL, "Create Order Option link is not displayed");
			//common.captureSS("Create Order Option link", LogStatus.FAIL);
		}

	}

	public void click_create_order() throws TwfException {
		element = common.getObjectByXpath(create_order_button);
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, "Create Order button is displayed and clicked");
		} else {

			//logger.log(LogStatus.FAIL, "Create Order button is not displayed");
			//common.captureSS("Create Order Option link", LogStatus.FAIL);
		}

	}

	public void click_icon_in_createorder(String option) throws TwfException {
		element = common.getObjectByXpath(".//*[text()='" + option + "']");
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, option + " icon is displayed and clicked");
		} else {
			//logger.log(LogStatus.FAIL, option + " icon is not displayed");
			//common.captureSS(option + "not displayed", LogStatus.FAIL);
		}
	}
	
	public void change_delivery_date(String date) throws TwfException{

		element = common.getObjectByXpath(this.date_picker_text_field);

		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(date);
			///logger.log(LogStatus.PASS, "Entered delivery date as : "+date);
		} else {
			//logger.log(LogStatus.FAIL, "Delivery date option is not displayed");
			//common.captureSS("Delivery date option", LogStatus.FAIL);
		}
	}
	public void createOrderFromHomepage(String crtOrdrOption) throws InterruptedException, TwfException
	{
		click_create_order_options_link();
		common.sleep(1000);
		click_icon_in_createorder(crtOrdrOption);
		common.sleep(3000);
		common.selectOptionsInADropDown(getData("ListName"), create_order_dropdown_icon);
		if(getData("DeliveryDate").isEmpty() == false)
			change_delivery_date(getData("DeliveryDate"));
		common.getObjectByXpath(po_text).sendKeys(getData("PONumber"));
		//logger.log(LogStatus.INFO, "PO# Number Entered as :"+getData("PONumber"));
		click_create_order();
	}
}
