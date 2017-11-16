package com.lol.pages;

import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;
import com.lol.util.TestExecutor;

//import test.java.com.lol.tests.TestExecutor;

public class ReviewOrderPage extends TestExecutor {
	public String review_order_submit_button = "//button[text()='Submit']";
	public String goToQuickEntryButton = ".//*[@id='r1:0:pt1:r1:0:r1:0:cb2']";
	public String reviewOrderTitle = ".//*[@id='r1:0:pt1:pt_of1']";
	public String RO_breadCrumb = ".//*[@id='r1:0:pt1:pt_pgl4']";
	public String RO_learnMoreLink = ".//a[@id='r1:0:pt1:pt_gil8']";
	public String RO_shareLink = ".//a[@id='r1:0:pt1:cil7']";
	public String RO_downloadLink = ".//a[@id='r1:0:pt1:cil5']";
	public String RO_printLink = ".//a[@id='r1:0:pt1:cil6']";
	public String RO_productReadyText = ".//*[@id='r1:0:pt1:pgl3']//span";
	public String RO_compactViewIcon = ".//*[@id='r1:0:pt1:cil1::icon']";
	public String RO_detailedViewIcon = ".//*[@id='r1:0:pt1:cil2::icon']";
	public String RO_summaryViewIcon = ".//*[@id='r1:0:pt1:cil3::icon']";
	public String RO_productImage = ".//*[@id='r1:0:pt1:lv1:0:i8']";
	public String RO_productDetail = ".//*[@id='r1:0:pt1:lv1']";
	public String RO_CSQtyBox = "r1:0:pt1:lv1:0:it3::content";
	public String RO_deleteIcon = ".//*[@id='r1:0:pt1:lv1:0:cil11::icon']";
	public String RO_productDetailSummary = ".//*[@id='r1:0:pt1:pgl34']";

	public void doSubmitOrder() throws TwfException{
		common.getObjectByXpath(this.review_order_submit_button).click();
		System.out.println("Review Order submit button is clicked on Review Order Page");

	}

	public ArrayList<String> get_review_order_page_productids(String TotallineNumber) throws TwfException{

		int Total = Integer.parseInt(TotallineNumber);
		ArrayList<String> review_order_page_productids = new ArrayList<>();
		for (int i = 0; i <= Total - 1; i++) {

			String temp = common.getObjectByXpath("//div[@id='r1:0:pt1:lv1:" + i + ":pgl146']/div/span").getText();
			review_order_page_productids.add(temp.replaceAll("# ", ""));
		}
		System.out.println("Captured Product ids in Review order page");
		return review_order_page_productids;

	}
	
	public boolean Equallist(ArrayList<String> a, ArrayList<String> b){
		if(a==null && b==null){
			return true;
		}
		if((a==null)&& !(b==null) || !(a==null)&& b==null){
			return false;
			
		}
		Collections.sort(a);
		Collections.sort(b);
		return a.equals(b);
	}
	public void validateReviewOrderPage() throws TwfException
	{
		common.validateObjectsDisplayed(reviewOrderTitle, "Review Order title");
		common.verifyBreadCrumb(RO_breadCrumb, "Home>My Orders>Review Order");
		common.validateObjectsDisplayed(RO_learnMoreLink, "Learn More link");
		common.validateObjectsDisplayed(RO_shareLink, "Share link");
		common.validateObjectsDisplayed(RO_downloadLink, "Download link");
		common.validateObjectsDisplayed(RO_printLink, "Print link");
		common.validateObjectsDisplayed(By.cssSelector(".x242"), "Text: The price(s) shown above are "
				+ "estimates only. Actual prices will be reflected on your invoice. Thank you for your order!");
		common.validateObjectsDisplayed(RO_productReadyText, "Text: Product Ready To Be Ordered");
		common.validateObjectsDisplayed(RO_compactViewIcon, "Compact view icon");
		common.validateObjectsDisplayed(RO_detailedViewIcon, "Detailed view icon");
		common.validateObjectsDisplayed(RO_summaryViewIcon, "Summary view icon");
		common.validateObjectsDisplayed(page.currentOrderModule, "Current Order Module");
		common.verifyTextExists(page.currentOrderModule, "Current Order", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Total Cases", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Total Eaches", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Order Total", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Delivery Date", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "PO #", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Submit", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Continue Shopping", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Save Order", "in Current order module");
		common.verifyTextExists(page.currentOrderModule, "Cancel Order", "in Current order module");
		common.validateObjectsDisplayed(page.quickEntryModule, "Current Order Module");
		common.verifyTextExists(page.quickEntryModule, "Quick Entry", "in Quick Entry module");
		common.verifyTextExists(page.quickEntryModule, "Product #", "in Quick Entry module");
		common.verifyTextExists(page.quickEntryModule, "Cust Prod #", "in Quick Entry module");
		common.verifyTextExists(page.quickEntryModule, "Go To Quick Entry", "in Quick Entry module");
		common.verifyTextExists(page.quickEntryModule, "Quick Entry", "in Quick Entry module");
		common.verifyTextExists(page.quickEntryModule, "Quick Entry", "in Quick Entry module");
		common.validateObjectsDisplayed(page.quickEntryProductTxtBox, "in Quick Entry module");
		common.validateObjectsDisplayed(page.quickEntryCustProductTxtBox, "in Quick Entry module");
		common.validateObjectsDisplayed(page.productsInOrderModule, "in Products in Order Module");
		common.verifyTextExists(page.productsInOrderModule, "Products in Order", "in Products in Order Module");
		validateCompactView();
		validateDetailedView();
		validateSummaryView();
	}
	
	public void validateCompactView()throws TwfException
	{
		common.clickByXPath(RO_compactViewIcon, "Compact view icon");
		common.sleep(500);
		if(common.isExists(By.xpath(RO_productImage))){
			//logger.log(LogStatus.FAIL, "Product image got displayed in Compact view which shouldn't");
		}
		else{
			//logger.log(LogStatus.PASS, "Product image not got displayed in Compact view as expected");
		}
		common.validateObjectsDisplayed(RO_productDetail, "Product details");
		common.validateObjectsDisplayed(RO_CSQtyBox, "CS quantity box is displayed");
		common.validateObjectsDisplayed(RO_deleteIcon, "Delete icon is displayed");

	}
	public void validateDetailedView()throws TwfException
	{
		common.clickByXPath(RO_detailedViewIcon, "Detailed view icon");
		common.sleep(500);
		if(common.isExists(By.xpath(RO_productImage))){
			//logger.log(LogStatus.FAIL, "Product image got displayed in Detailed view as expected");
		}
		else{
			//logger.log(LogStatus.PASS, "Product image not got displayed in Detailed view");
		}
		common.validateObjectsDisplayed(RO_productDetail, "Product details");
		common.validateObjectsDisplayed(RO_CSQtyBox, "CS quantity box is displayed");
		common.validateObjectsDisplayed(RO_deleteIcon, "Delete icon is displayed");

	}
	public void validateSummaryView()throws TwfException
	{
		common.clickByXPath(RO_summaryViewIcon, "Summary view icon");
		common.sleep(500);
		if(common.isExists(By.xpath(RO_productImage))){
			//logger.log(LogStatus.FAIL, "Product image got displayed in Summary view which shouldn't");
		}
		else{
			//logger.log(LogStatus.PASS, "Product image not got displayed in Summary view as expected");
		}
		common.validateObjectsDisplayed(RO_productDetailSummary, "Product details");
		common.verifyTextExists(RO_productDetailSummary, "Classes", "as column in Review order summary view");
		common.verifyTextExists(RO_productDetailSummary, "Current Cases", "as column in Review order summary view");
		common.verifyTextExists(RO_productDetailSummary, "Total $", "as column in Review order summary view");
		common.verifyTextExists(RO_productDetailSummary, "Contracted Cases", "as column in Review order summary view");
		common.verifyTextExists(RO_productDetailSummary, "Contracted Cs Dollar", "as column in Review order summary view");
		common.verifyTextExists(RO_productDetailSummary, "Total", "in Review order summary view");
	}
}
