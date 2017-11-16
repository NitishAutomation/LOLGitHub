package com.lol.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.junit.Assert;
import org.testng.Assert;
import org.testng.Reporter;



import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;

//import test.java.com.lol.listener.ExtentReportNG;
//import LOL.TestExecutor;

public class Common1 extends TestExecutor {

	// WebDriver driver = null;
	WebElement element = null;
	List<WebElement> rows = null;
	Select select = null;
		public String screenshot="D:\\Users\\abinaya-k\\workspace\\LOL_Winfield_Automation\\ScreenShotLib";
	public void sleep(int timeInMillisecs) {
		try {
			Thread.sleep(timeInMillisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Sleep in seconds
	public void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Check if the element is present in Favorites
	public boolean checkFav(String valueInFav) {

		List<WebElement> numberOfFav = driver
				.findElements(By.xpath("html/body/table[1]/tbody/tr/td[1]/div[2]/div[3]/div"));
		for (int i = 0; i < numberOfFav.size(); i++) {
			System.out.println("value is " + numberOfFav.get(i).getText());
			if (numberOfFav.get(i).getText().equals(valueInFav)) {
				return true;
			}
		}
		return false;
	}

	// Check if alert exists
	public boolean ifAlertPresent() {
		try {
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	// To Capture Screen Shot
	public String CaptureScreenShot(WebDriver driver, String fileName) {
		// EventFiringWebDriver efwd = new EventFiringWebDriver(driver);
		TakesScreenshot efwd = ((TakesScreenshot) driver);
		File src = efwd.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./ScreenShotLib/" + fileName));
			// FileUtils.copyFile(src, new
			// File("./ScreenShotLib/"+System.currentTimeMillis()+fileName));
		} catch (Exception e) {

		}
		return screenshot+fileName+".jpg";
	}

	// TBD
	public void tableValue(String tableXpath, String keyFieldValue, String valueToCheck) throws TwfException 
	{
		element = driver.findElement(By.xpath(tableXpath));
		rows = element.findElements(By.tagName("tr"));
		for (int i = 2; i < rows.size(); i++) {
			if (getObjectByXpath(tableXpath, valueToCheck).equals(keyFieldValue)) {

			}
		}
	}

	// Check element present or not
	public boolean isExists(By by) throws TwfException {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			addExceptionToReport("Object is not found"+by+"", "", "");
			return false;
		}
	}

	public boolean isExists(String order_Confirmation_message) throws TwfException {
		
		try {
			return driver.findElement(By.xpath(order_Confirmation_message)).isDisplayed();
		} catch (NoSuchElementException e) {
			addExceptionToReport("Object is not found"+order_Confirmation_message+"", "", "");
			return false;
		}
		
	}
	// Handling alert to either accept or dismiss
	public boolean handleAlert(boolean acceptOrDismiss) {
		Alert alert = driver.switchTo().alert();
		if (acceptOrDismiss) {
			alert.accept();
			return true;
		}
		alert.dismiss();
		return false;
	}

	// Select the checkbox xpath
		public void selectCheckBoxx(String by) {

//			try {
//				
//				
//				if (driver.findElement(By.xpath(by)).getAttribute("checked").equals("true")) {
//					System.out.println("Checked already");
//					return true;
//				}
//			} catch (Exception e) {
				try {
					getObjectByXpath("//input[@name='"+by+"']","saveandSubmitButton").click();
					System.out.println("Checking now");
//					if (driver.findElement(By.xpath(by)).getAttribute("checked").equals("true")) {
//						System.out.println("Checking now");
//						return true;
//					}
				} catch (Exception e1) {
					System.out.println("Not Checking now");
				}
			//}
			//return false;
		}
	
	// Select the checkbox
	public boolean selectCheckBox(By by) throws TwfException {

		try {
			if (driver.findElement(by).getAttribute("checked").equals("true")) {
				System.out.println("Checked already");
				return true;
			}
		} catch (Exception e) {
			try {
				driver.findElement(by).click();
				if (driver.findElement(by).getAttribute("checked").equals("true")) {
					System.out.println("Checking now");
					return true;
				}
			} catch (Exception e1) {
				addExceptionToReport("Object is not found"+by+"", "", "");
				return false;
			}
		}
		return false;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public void waitForAlert(WebDriver driver) {
		int i = 0;
		while (i++ < 5) {
			try {
				// Alert alert = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
		}
	}

	public String GenerateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}

	// Check if the Title.
	public boolean checkTitle(String title) {
		// System.out.println("Title is :"+driver.getTitle().trim());
		return driver.getTitle().trim().equals(title);
	}

	public boolean checkTitleContains(String title) throws TwfException {

		String pageTitle = this.getObjectById("PageTitleTop").getText().trim();
		System.out.println("Title is " + pageTitle);

		if (pageTitle == null || pageTitle.isEmpty())
			pageTitle = this.getObjectByName("PageTitleTop").getText().trim();

		if (pageTitle == null || !pageTitle.isEmpty())
			return pageTitle.toLowerCase().contains(title.toLowerCase());

		return pageTitle.toLowerCase().contains(title.toLowerCase());
	}

	// Save button in any page
	public void save() throws TwfException {
		WebElement saveButton = this.getObjectByXpath(".//*[@id='hc_OK']","saveButton");
		this.jsclick(saveButton);
	}

	// Save button in any page
	public void saveAS() throws TwfException {
		getObjectByXpath(".//*[@id='ibnSave']/span","saveAsButton").click();
	}

	// Click on Yes button of Warning dialogue box shown during the reboot of
	// device
	public void clickYes() throws TwfException {
		getObjectByXpath("//*[@id='warnDialog']/tbody/tr[2]/td[1]/input","ClickYesButton").click();

	}

	// Select button in any page
		public void select() throws TwfException {
			getObjectByXpath("//img[@name='hc_Select']","SelectButton").click();
		}

		// Row_Exit button in any page
			public void Row_Exit() throws TwfException {
				getObjectByXpath("//img[@id='ROW_EXIT_BUTTON']","RowExitButton").click();
			}
	

			// Form_Exit button in any page
				public void Form_Exit() throws TwfException {
					getObjectByXpath("//img[@id='FORM_EXIT_BUTTON']","FormExitButton").click();
				}
			
	// Search button in any page
	public void search() throws TwfException {
		getObjectByXpath("//input[@value='Search' and @class='FormButton']","SearchButton").click();
	}

	public void jsclick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// Find button in any page
		public void Find() throws TwfException {
			getObjectByXpath("//img[@name='hc_Find']","FindButton").click();
		}
		// OK button in any page
				public void OK() throws TwfException {
					getObjectByXpath(".//img[@id='hc_OK']","OKButton").click();
				}
		
	// Cancel button in any page
	public void cancel() throws TwfException {
		getObjectByXpath("//img[@name='hc_Cancel']","CancelButton").click();
	}

	// add button in any page
		public void add() throws TwfException {
			getObjectByXpath("//img[@name='hc_Add']","AddButton").click();
			//logger.log(LogStatus.PASS, "LOL::Selected: Add sign clicked");
		}
	
	// Back button in any page
	public void back() throws TwfException {
		getObjectByXpath("//a[@href='javascript:history.go(-1);']","BackButton").click();
	}

	// NA Home page
	public void home() throws TwfException {
		getObjectByLinkText("Home").click();
	}

	// Save and Acquire
	public void saveAndAcquire() throws TwfException {
		getObjectByXpath("//input[@value='Save & Acquire']","SaveandacquireButton").click();
	}
	
	// Save and Submit
		public void saveAndSubmit() throws TwfException {
			getObjectByXpath("//button[@name='0_136']","saveandSubmitButton").click();
		}

		// Save and Close
				public void Close() throws TwfException {
					getObjectByXpath("//img[@name='hc_Close']","saveandSubmitButton").click();
				}
		
		// Save and Continue
		public void saveAndContinue() throws TwfException {
			getObjectByXpath("//button[@name='0_20']","SaveandContinueButton").click();
		}
	// Save and Release
	public void saveAndRelease() throws TwfException {
		getObjectByXpath("//input[@value='Save & Release']","saveandReleaseButton").click();
	}

	// Help Link
	public void help() throws TwfException {
		getObjectByLinkText("Help").click();
	}

	//switch to frame
	public void switchToframe(String framename) {
		driver.switchTo().frame(driver.findElement(By.id(framename)));
	}
	
	//switch out of frame
	public void switchOutframe() {
		driver.switchTo().defaultContent(); 
	}
	
	// Returns true if the element is present.
	public boolean isElementExist(By by) throws TwfException {

		this.changeTimeOut(5);
		try {
			driver.findElement(by);
		} catch (Exception e) {
			try {
				driver.findElement(by);
			} catch (Exception e1) {
				this.changeTimeOut(50);
				addExceptionToReport("Object is not found"+by+"", "", "");
				return false;
			}
		}
		this.changeTimeOut(50);
		return true;
	}

	// Change overall timeout settings
	public void changeTimeOut(int timeToWait) {
		driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
	}

	// Info page message comparison
	public boolean pageInfoBoxContains(String str) {

		WebElement wb = driver.findElement(By.id("InfoBox"));
		if (wb == null || !wb.isDisplayed()) {
			wb = driver.findElement(By.name("InfoBox"));
		}
		// System.out.println("info message "+wb.getText());
		return wb.getText().toLowerCase().contains(str.toLowerCase());

	}

	// Alert message comparison

	public boolean pageAlertBoxContains(String str) {
		try {
			WebElement wb = driver.findElement(By.xpath("//div[@class='alert_1']"));
			if (wb == null || !wb.isDisplayed()) {
				wb = driver.findElement(By.xpath("//div[@class='alert_1']"));
			}
			System.out.println("info message " + wb.getText());
			return wb.getText().trim().toLowerCase().contains(str.toLowerCase());
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	// Alert 2 message comparision

	public boolean pageAlert2BoxContains(String str) {
		try {
			WebElement wb = driver.findElement(By.xpath("//div[@class='alert_2']"));
			if (wb == null || !wb.isDisplayed()) {
				wb = driver.findElement(By.xpath("//div[@class='alert_2']"));
			}
			System.out.println("info message " + wb.getText());
			return wb.getText().trim().toLowerCase().contains(str.toLowerCase());
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	// Alert 3 message comparision

	public boolean pageAlert3BoxContains(String str) {
		try {
			WebElement wb = driver.findElement(By.xpath("//div[@class='alert_3']"));
			if (wb == null || !wb.isDisplayed()) {
				wb = driver.findElement(By.xpath("//div[@class='alert_3']"));
			}
			System.out.println("info message " + wb.getText());
			return wb.getText().trim().toLowerCase().contains(str.toLowerCase());
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	// Deselect multiple items

	public boolean deselectOptions(String optionName, String[] optionsToBeDeselected) {

		int count = 0;
		select = new Select(driver.findElement(By.name(optionName)));
		for (int i = 0; i < optionsToBeDeselected.length; i++) {
			select.deselectByVisibleText(optionsToBeDeselected[i]);
			count++;
		}
		if (count == optionsToBeDeselected.length) {
			return true;
		}
		return false;
	}

	public String selectOptionsInADropDown(String text, String elementforDropdown) throws InterruptedException, TwfException {
		this.getObjectByXpath(elementforDropdown,text).click();
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		WebElement clictheobject = getObjectByXpath(".//li//a[text()='" + text + "']",text);
		action.moveToElement(clictheobject).perform();
		action.moveToElement(clictheobject).click();
		clictheobject.click();
		Thread.sleep(2000);
		return text;
	}

	// Select multiple items in a dropdown.
	public boolean selectOptions(String optionName, String[] optionsToBeSelected) {

		int count = 0;
		select = new Select(driver.findElement(By.name(optionName)));
		for (int i = 0; i < optionsToBeSelected.length; i++) {
			// System.out.println("Options are "+optionsToBeSelected[i]);
			select.selectByVisibleText(optionsToBeSelected[i]);
			count++;
		}
		if (count == optionsToBeSelected.length) {
			return true;
		} else {
			return false;
		}
	}

	// Select drop down option. Gets option name and option to be selected
	public boolean selectOption(String optionName, String optionToBeSelected) {

		int count = 0;
		select = new Select(driver.findElement(By.className(optionName)));
		// select.selectByVisibleText(optionToBeSelected);

		List<WebElement> options = select.getOptions();
		// System.out.println("Options size "+options.size());
		if (options.size() > 0) {
			for (WebElement option : options) {
				// System.out.println("selected options are :
				// "+option.getText());
				if (option.getText().equals(optionToBeSelected)) {
					option.click();
					return true;
				} else {
					count++;
				}
			}
			if (count == options.size()) {
				System.out.println(optionToBeSelected + " not available to select.");
				return false;
			}
		} else {
			System.out.println("No options available to select.");
		}
		return false;
	}

	// Get The count of options
	public int getOptionSize(String optionName) {

		select = new Select(driver.findElement(By.name(optionName)));
		// System.out.println("Option size is "+select.getOptions().size());
		return select.getOptions().size();
	}

	// page size option

	public void pageSizeOption(String number) {
		if (driver.findElements(By.name("pageSize")).size() > 0) {
			select = new Select(driver.findElement(By.name("pageSize")));
			select.selectByVisibleText(number);
		}
	}

	// Refresh current page
	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	public WebElement emptyTaskNameField(WebElement fieldName) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", fieldName);

		return fieldName;

	}

	public int generateRandomNumber() {
		return generateRandomNumber(100, 1);
	}

	public int generateRandomNumber(int high, int low) {
		Random r = new Random();
		return r.nextInt(high - low) + low;
	}

	// Split a string
	public String[] splitSting(String toSplit) {
		return toSplit.trim().split("\\s*,\\s*");
	}

	// Return object with xpathKey. xpathKey is mentioned in the OR.properties
	public WebElement getObjectByXpath(String xpathKey, String msg) throws TwfException {
		// Reporter.log("Trying to Find object having Xpath as :
		// "+xpathKey+"<br>");
		try {
			return driver.findElement(By.xpath(xpathKey));
		} catch (Throwable t) {
			//String screenshot_path= CaptureScreenShot(driver, msg);
			  //String image= logger.addScreenCapture(screenshot_path);
			  //test.log(LogStatus.FAIL, "Payment AP11 Failed", image);
			//logger.log(LogStatus.FAIL, "LOL:: Win Field: Not Selected- BU Add button",image);
			
			//Reporter.log("Object having Xpath as " + xpathKey + " not found.");
//			Assert.assertTrue(false, "Object having Xpath as " + xpathKey + " not found.");
			addExceptionToReport("Object is not found"+xpathKey+"", "", "");
			  
			return null;
		}

	}

//	public boolean handleOGConfirmationAlert(String whatToClick) {
//		element = common1.getObjectByXpath(qoepage.OGVersionConfirmationModal,whatToClick);
//		if (element.isDisplayed()) {
//			logger.log(LogStatus.PASS, "Confirm Version modal appears with the below details: \n" + element.getText());
//			common1.clickByXPath(".//*[@id='r1:0:pt1:confirmOGVersion::content']//button[text()='"+ whatToClick +"']", whatToClick);			
//			return true;
//		} else {
//			logger.log(LogStatus.INFO, "Confirm Version modal not appears");
//			return false;
//		}
//	}

	public void validatePageTitle(String pageName)
	{
		waitExplicitlyForPresence(".//span[text()='" + pageName + "']", 5);
		String title = driver.getTitle();
		if (title.contains(pageName)) {
			//logger.log(LogStatus.PASS, title + " page is loaded successfully");
		} else {
			//logger.log(LogStatus.FAIL, pageName + " page is not loaded instead user lands on "+title);
			captureSS("Page Navigation failed", LogStatus.FAIL);
		}
	}
	// Return object with class
	public WebElement getObjectByClass(String objectClass) throws TwfException {
		// Reporter.log("Trying to Find object having Class name as :
		// "+objectClass+"<br>");
		try {
			return driver.findElement(By.className(objectClass));
		} catch (Throwable t) {
//			Reporter.log("Object having class name as " + objectClass + " not found." + "<br>");
//			Assert.assertTrue(false, "Object having class name as " + objectClass + " not found.");
			addExceptionToReport("Object is not found"+objectClass+"", "", "");
			return null;
		}
	}

	// Return object with name
	public WebElement getObjectByName(String name) throws TwfException {
		// Reporter.log("Trying to Find object having name as : "+name+"<br>");
		try {
			return driver.findElement(By.name(name));
		} catch (Throwable t) {
//			Reporter.log("Object having name as " + name + " not found." + "<br>");
//			Assert.assertTrue(false, "Object having name as " + name + " not found.");
			addExceptionToReport("Object is not found"+name+"", "", "");
			return null;
		}
	}

	// Return object with id
	public WebElement getObjectById(String id) throws TwfException {
		// Reporter.log("Trying to Find object having ID as : "+id+"<br>");

		try {
			return driver.findElement(By.id(id));
		} catch (Throwable t) {
//			Reporter.log("Object having id as " + id + " not found." + "<br>");
//			Assert.assertTrue(false, "Object having id as " + id + " not found.");
			addExceptionToReport("Object is not found"+id+"", "", "");
			return null;
		}
	}

	// Return object with linkText
	public WebElement getObjectByLinkText(String linkText) throws TwfException {
		// Reporter.log("Trying to Find Link with the LinkText as :
		// "+linkText+"<br>");
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (Throwable t) {
//			Reporter.log("Link with the text " + linkText + " not found " + "<br>");
//			Assert.assertTrue(false, "Link with the text " + linkText + " not found ");
			addExceptionToReport("Object is not found"+linkText+"", "", "");
			return null;
		}
	}

	// Return object with CSS selector
	public WebElement getObjectByCssSelector(String selector,String msg) throws TwfException {
		// Reporter.log("Trying to Find object having CssSelector as :
		// "+selector+"<br>");
		WebElement element;
		try {
		    element=driver.findElement(By.cssSelector(selector));
			//logger.log(LogStatus.PASS, msg + " is displayed");
			return element;
		} catch (Throwable t) {
//			Reporter.log("Object having CssSelector as " + selector + " not found" + "<br>");
//			Assert.assertTrue(false, "Object having CssSelector as " + selector + " not found");
			addExceptionToReport("Object is not found"+selector+"", "", "");
			return null;
		}
	}

	// Returns the default Password
	public String getDefaultPassword() {
		return "password";
	}

	// Returns the new password
	public String getNewPassword() {
		return "password1";
	}

	public void verifySortingOrder(String tableXpath, String columnName, boolean descOrder) throws TwfException {
		List<String> displayedNames = new ArrayList<String>();
		List<String> SortedNames = new ArrayList<String>();
		this.sleep(1000);
		WebElement tableType = driver.findElement(By.xpath(tableXpath));
		List<WebElement> rowElmt = tableType.findElements(By.xpath(tableXpath + "//tr"));

		String getData;
		this.sleep(1000);
		// System.out.println(rowElmt.size());

		List<WebElement> columnList = tableType.findElements(By.xpath(tableXpath + "//tr[1]/td"));
		// System.out.println(columnList);

		for (int j = 1; j <= columnList.size(); j++) {
			// System.out.println(common1.getObjectByXpath(tableXpath+"//tr[1]/td["+j+"]").getText().trim());
			if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]",columnName).getText().trim().equals(columnName)) {
				for (int i = 3; i <= rowElmt.size(); i++) {

					getData = this.getObjectByXpath(tableXpath + "//tr[" + i + "]/td[" + j + "]",columnName).getText().trim();
					displayedNames.add(getData);
					SortedNames.add(getData);
					System.out.println(displayedNames);

				}
				break;
			} else {
//				Reporter.log("column with the name " + columnName + " not found" + "<br>");
				Assert.fail("column with the name " + columnName + " not found");
			}

		}

		this.sleep(2000);
		List<String> sortingOperation = displayedNames;
		this.listSortEx(sortingOperation, descOrder);
		System.out.println(sortingOperation);
		try {
			Assert.assertEquals(SortedNames, sortingOperation);
			Reporter.log("Sort order is as expected" + "<br>");
		} catch (AssertionError e) {
			Reporter.log("Sort order is not as expected" + "<br>");
			Assert.fail("Sort order is not as expected", e);
			e.printStackTrace();
		}
	}

	public void listSortEx(List<String> list, final Boolean descOrder) {
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (descOrder) {
					return o2.compareTo(o1);
				} else {
					return o1.compareTo(o2);
				}
			}
		});
	}

	public boolean clickOnColumnNameToSort(String tableXpath, String columnName, boolean descOrder) throws TwfException {
		WebElement tableType = driver.findElement(By.xpath(tableXpath));
		// List<WebElement>
		// rowElmt=tableType.findElements(By.xpath(tableXpath+"//tr"));

		List<WebElement> columnList = tableType.findElements(By.xpath(tableXpath + "//tr[1]/td"));
		boolean Status = false;
		for (int j = 1; j <= columnList.size(); j++) {
			// System.out.println(common1.getObjectByXpath(tableXpath+"//tr[1]/td["+j+"]").getText().trim());
			if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]",columnName).getText().trim().equals(columnName)) {
				if (descOrder && this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/asc.gif']",columnName)
						.isDisplayed()) {
					this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]/a",columnName).click();
					Status = true;
				} else if (descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/desc.gif']",columnName)
								.isDisplayed()) {
//					Reporter.log(
//							"column with the name " + columnName + " is already sorted in Descending order" + "<br>");
					Status = true;
				} else if (!descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/desc.gif']",columnName)
								.isDisplayed()) {
					this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]/a",columnName).click();
					Status = true;
				} else if (!descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/asc.gif']",columnName)
								.isDisplayed()) {
//					Reporter.log(
//							"column with the name " + columnName + " is already sorted in Ascending order" + "<br>");
					Status = true;
				}

				break;
			} else {
//				Reporter.log("column with the name " + columnName + " not found" + "<br>");
				Status = false;
				Assert.fail("column with the name " + columnName + " not found");
			}

		}
		return Status;

	}

	public boolean checkExistenceOfOverridelink() {

		try {

			driver.findElement(By.id("overridelink")).isDisplayed();
			System.out.println(driver.findElement(By.id("overridelink")).isDisplayed());
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	public String GetRandomAlphaNumaricChar(int i) {

		String SALTCHARS = "AB123CDEFGHIJKLMNOPQRSTUVWXYZ8910";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < i) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		System.out.println(saltStr);
		return saltStr;

	}

	public String GetRandomNumarics(int i) {

		String SALTCHARS = "123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < i) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		System.out.println(saltStr);
		return saltStr;

	}

	public String GetRandomAlphaNumaricSpecialChar(int i) {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123.#@#@$$#";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < i) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		System.out.println(saltStr);
		return saltStr;

	}

	public String GetRandomIPv4(int min, int max) {
		StringBuilder sb = null;
		Random rand = new Random();

		int randomNum1 = rand.nextInt((max - 1) + 1) + min;
		int randomNum2 = rand.nextInt((max - 1) + 1) + min;
		int randomNum3 = rand.nextInt((max - 1) + 1) + min;
		int randomNum4 = rand.nextInt((max - 1) + 1) + min;

		String ip1 = Integer.toString(randomNum1);
		String ip2 = Integer.toString(randomNum2);
		String ip3 = Integer.toString(randomNum3);
		String ip4 = Integer.toString(randomNum4);
		sb = new StringBuilder();
		sb.append(ip1).append(".").append(ip2).append(".").append(ip3).append(".").append(ip4);
		return sb.toString();
	}

	public String GetRandomString() {
		// makes sure this method does not return
		// the same random string for successive
		// fast calls

		Random rand = new Random();
		char randChar1 = (char) (rand.nextInt(26) + 'a');
		char randChar2 = (char) (rand.nextInt(26) + 'a');
		int randNum1 = rand.nextInt(10000) + 1000;
		int randNum2 = rand.nextInt(10000) + 1000; // 1000-9999

		String TwoChar = new StringBuilder("").append(randChar1).append(randChar2).toString();

		String strRandom = TwoChar.toString() + "_" + randNum1 + "_" + randNum2;

		return strRandom;
	}

	public void uncheckAllCheckboxes(WebElement table) {

		// Find all the input tags inside the mainTable and save it to a list
		// List<WebElement> checkBoxes =
		// table.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> checkBoxes = table.findElements(By.xpath("//input[@type='checkbox']"));

		// iterate through the list of checkboxes and if checked, uncheck them
		for (WebElement checkbox : checkBoxes) {

			if (checkbox.isSelected()) {
				this.jsclick(checkbox);
				checkbox.click();
			}

		}
	}

	public void checkAllCheckboxes(WebElement table) {

		// Find all the input tags inside the mainTable and save it to a list
		List<WebElement> checkBoxes = table.findElements(By.xpath("//input[@type='checkbox']"));

		// iterate through the list of checkboxes and if uncheck , checked them
		for (WebElement checkbox : checkBoxes) {

			if (!checkbox.isSelected()) {
				// checkbox.click();
				this.jsclick(checkbox);
			}

		}
	}

	public boolean checkForExistenceOfAnElementInTable(String tableXpath, String columnName, String element) throws TwfException {

		WebElement tableType;
		try {
			this.pageSizeOption("All");
			this.sleep(3000);
			tableType = driver.findElement(By.xpath(tableXpath));

			List<WebElement> rowElmt = tableType.findElements(By.xpath(tableXpath + "//tr"));

			this.sleep(1000);
			// System.out.println(rowElmt.size());

			List<WebElement> columnList = tableType.findElements(By.xpath(tableXpath + "//tr[1]/td"));

			System.out.println(columnList);

			for (int j = 2; j <= columnList.size(); j++) {
				System.out.println(this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]",columnName).getText().trim());
				if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]",columnName).getText().trim().equals(columnName)) {
					for (int i = 2; i <= rowElmt.size(); i++) {

						System.out.println(this.getObjectByXpath(tableXpath + "//tbody//tr[" + i + "]/td[" + j + "]/a",columnName)
								.getText().trim());
						String actual = this.getObjectByXpath(tableXpath + "//tbody//tr[" + i + "]/td[" + j + "]/a",columnName)
								.getText().trim();
						// int check = actual.compareToIgnoreCase(element);
						if (actual.equalsIgnoreCase(element)) {
							return true;
							// common1.getObjectByXpath(tableXpath+"//tbody//tr["+i+"]/td["+j+"]/a").click();
						}

					}
					break;
				} else {
					System.out.println(("column with the name " + columnName + " not found" + "<br>"));
					// Assert.fail("column with the name "+columnName+" not
					// found");
				}

			}
		} catch (Exception e1) {
			addExceptionToReport("Object is not found"+tableXpath+"", "", "");
			return false;
		}
		return false;

	}

	public void zoomIn() {
		// To zoom In page 4 time using CTRL and + keys.
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}

	public void zoomOut() {
		// To zoom out page 4 time using CTRL and - keys.
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}

	public void zoomdefault() {
		// To set browser to default zoom level 100%
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}

	// public void visable(String name){
	// WebElement myDynamicElement = (new WebDriverWait(driver, 10))
	// .until(ExpectedConditions.presenceOfElementLocated(By.xpath(name)));
	// }
	public void clickable(String name) {

		WebDriverWait wait = new WebDriverWait(driver, 25);

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(name)));
		element.click();

	}

	public void waitExplicitlyForClickable(String xPath, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}
	
	public void waitExplicitlyForPresence(String xPath, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	}
	public void waitExplicitlyForPageToLoad(String title, int timeToWait) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		wait.until(ExpectedConditions.titleIs(title));
	}

	public void chromzoomOut() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_MINUS);
		Thread.sleep(2000);
	}

	public void chromzoomIn() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_PLUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_PLUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_PLUS);
		Thread.sleep(2000);
	}

	public boolean PDP_validateObjectsDisplayed(String xPath, String msg) throws TwfException {
		try {
			if (getObjectByXpath(xPath,msg).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is available");
				return true;
				// Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, msg + " is not available");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+xPath+"", "", "");
		}
		return false;
	}

	public void validateObjectsDisplayed(String xPath, String msg) throws TwfException {
		try {
			if (getObjectByXpath(xPath,msg).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is displayed");
				Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			/*logger.log(LogStatus.FAIL, msg + " is not displayed");
			String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);
			logger.log(LogStatus.FAIL, image);*/
			addExceptionToReport("Object is not found"+xPath+"", "", "");
		}
	}

	public void validateObjectsDisplayed(By by, String msg) throws TwfException {
		try {
			if (driver.findElement(by).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is displayed");
				// Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			/*logger.log(LogStatus.FAIL, msg + " is not displayed");
			String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);
			logger.log(LogStatus.FAIL, image);*/
			addExceptionToReport("Object is not found"+by+"", "", "");
		}
	}
	
	public boolean validateObjectsDisplayedWOReport(String xPath, String msg) throws TwfException {
		try {
			if (getObjectByXpath(xPath,msg).isDisplayed() == true) {
				return true;
			}
		} catch (AssertionError e) {
			addExceptionToReport("Object is not found"+xPath+"", "", "");
			return false;
		}
		return false;
	}

	public int getSizeOfElementsSelected(String xPath) {
		List<WebElement> elementCount = driver.findElements(By.xpath(xPath));
		return elementCount.size();
	}

	public String getCustomerNumberSelected(String msg) throws TwfException {
		String custNbr;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// (new WaitForPageToLoad()).setTimeToWait(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(page.selectedCustomerText))));
		custNbr = getObjectByXpath(page.selectedCustomerText, msg).getText();
		return custNbr.replaceAll("[^0-9]", "");
	}

	public int getIntegerFromString(String str) {
		return Integer.parseInt(str.replaceAll("[^0-9]", ""));
	}
	public void highlightElement(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: red; border: 3px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	public void selectCustomers(String customerNumber, String departmentNumber) throws TwfException {
		if (!getCustomerNumberSelected(customerNumber).contains(customerNumber)) {
			String id, xPath, xPathForDept;
			clickable(page.customerDropdownIMG);
			// getObjectByXpath(pg.customerDropdownIMG).click();
			//logger.log(LogStatus.PASS, "User able to click Customer list dropdown");
			if (getObjectByXpath(page.customerDropdownSearch,customerNumber).isDisplayed()) {
				getObjectByXpath(page.customerDropdownSearch,customerNumber).click();
				getObjectByXpath(page.customerDropdownSearch,customerNumber).clear();
				getObjectByXpath(page.customerDropdownSearch,customerNumber).sendKeys(customerNumber);
				//logger.log(LogStatus.PASS, "User able to Enter customer number in the Search box of Customer dropdown");
				if (departmentNumber.equals("") || departmentNumber.isEmpty() == true || departmentNumber.equals("0")) {
					// getObjectByXpath(pg.customerDropdownSearch).sendKeys(customerNumber);
					// logger.log(LogStatus.PASS, "User able to Enter customer
					// number in the Search box of Customer dropdown");
					page.getObjectByCustomerNumber(customerNumber).click();
					sleep(1500);
					if (getCustomerNumberSelected(customerNumber).contains(customerNumber)){
						//logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber);
					
					}else{
						//ogger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber);
					}
				} else {
					id = page.getIndexFromListOfCustomers(customerNumber);
					xPath = ".//*[contains(@id,':pt_i9:" + id + ":pt_i12:') and contains(@id, 'pt_cl25')]";
					for (int i = 0; i < getSizeOfElementsSelected(xPath); i++) {
						xPathForDept = ".//*[contains(@id,':pt_i9:" + id + ":pt_i12:" + i + ":pt_cl25')]";
						// System.out.println(driver.findElement(By.xpath(xPathForDept)).getText());
						if (driver.findElement(By.xpath(xPathForDept)).getText().contains(departmentNumber)) {
							driver.findElement(
									By.xpath(".//*[contains(@id,':pt_i9:" + id + ":pt_i12:" + i + ":pt_cl25')]"))
									.click();
							sleep(1000);
							if (getCustomerNumberSelected(customerNumber).contains(customerNumber)){
								/*logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber
										+ "with " + "department: " + departmentNumber);*/
							}
							else{
								/*logger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber
										+ "with " + "department: " + departmentNumber);*/
							}
						}
					}
				}
			} else {
				if (departmentNumber == "" || departmentNumber.isEmpty() == true) {
					page.getObjectByCustomerNumber(customerNumber).click();
				} else {
					id = page.getIndexFromListOfCustomers(customerNumber);
					xPath = ".//*[contains(@id,'dgfSPT:pt_i9:" + id + ":pt_i12:') and contains(@id, 'pt_cl25')]";
					for (int i = 0; i < getSizeOfElementsSelected(xPath); i++) {
						if (driver.findElement(By.xpath(".//*[contains(@id,'dgfSPT:pt_i9:" + id + ":pt_i12:"
								+ Integer.toString(i) + ":pt_cl25')]")).getText() == departmentNumber) {
							driver.findElement(
									By.xpath(".//*[contains(@id,'dgfSPT:pt_i9:" + id + ":pt_i12:" + i + ":pt_cl25')]"))
									.click();
							if (getCustomerNumberSelected(customerNumber) == customerNumber){
								//logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber
										//+ "with " + "department: " + departmentNumber);
							}
							else{
								//logger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber
										//+ "with " + "department: " + departmentNumber);
							}
						}
					}
				}
			}
		}
	}

	public void signOut() {
		driver.findElement(By.xpath(".//*[text()='Sign Out']")).click();
	}

//	public void validateIconsAndMsg(String previousDiv, String typeOfProduct) {
//		switch (typeOfProduct) {
//		case "SpecialOrder":
//			iconCheck("Special-Order", previousDiv);
//			validateIconHoverMessage(previousDiv, "Special-Order");
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerSOIconImg, "Blue banner icon image");
//			String msg = driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText();
//			String expectedMsg = "Call your customer service representative to order this product";
//			if (msg.equals(expectedMsg))
//				logger.log(LogStatus.PASS, "Message " + expectedMsg + " is displayed for Special order product");
//			else {
//				logger.log(LogStatus.FAIL, "Message " + expectedMsg + " is not displayed for Special order product");
//				captureSS("Special Order blue banner message not displayed", LogStatus.FAIL);
//			}
//			break;
//		case "CES":
//			iconCheck("CES-Item", previousDiv);
//			validateIconHoverMessage(previousDiv, "CES-Item");
//			break;
//		case "OrderGuide":
//			iconCheck("Order-Guide", previousDiv);
//			validateIconHoverMessage(previousDiv, "Order-Guide");
//			break;
//		case "LocallySourced":
//			iconCheck("Locally-Sourced", previousDiv);
//			validateIconHoverMessage(previousDiv, "Locally-Sourced");
//			break;
//		case "Scoop":
//			iconCheck("scoop", "pt1:r1:0:r1:0:pt1:sic1:dc_pgl1");
//			clickLinkByXPath(".//*[@id='pt1:r1:0:r1:0:pt1:sic1:dc_cil1::icon']", "Scoop icon is clicked");
//			waitExplicitlyForClickable(".//*[@id='pt1:pt_of1']", 3);
//			String title = driver.getTitle();
//			if (title.equals("Scoop | US Foods")) {
//				logger.log(LogStatus.PASS, "Clicking Scoop icon navigates user to " + title + " as expected");
//				driver.navigate().back();
//			} else {
//				logger.log(LogStatus.FAIL, "Clicking Scoop icon navigates user to " + title + " page");
//				captureSS("Scoop icon validation", LogStatus.FAIL);
//			}
//			break;
//		case "JIT":
//			iconCheck("Just-in-Time", previousDiv);
//			validateIconHoverMessage(previousDiv, "Just-in-Time");
//			break;
//		case "DWO":
//			iconCheck("dwo", previousDiv);
//			validateIconHoverMessage(previousDiv, "dwo");
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerDWOIconImg, "Blue banner icon image");
//			String msg1 = driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText();
//			String expectedMsg1 = "This product will be discontinued when all inventory is depleted.";
//			if (msg1.equals(expectedMsg1))
//				logger.log(LogStatus.PASS, "Message " + expectedMsg1 + " is displayed for DWO product");
//			else {
//				logger.log(LogStatus.FAIL, "Message " + expectedMsg1 + " is not displayed for DWO product");
//				captureSS("DWO blue banner message not displayed", LogStatus.FAIL);
//			}
//			break;
//		case "Contracted":
//			iconCheck("https://media.usfood.com/2/images/partner/", previousDiv);
//			validateIconHoverMessage(previousDiv, "https://media.usfood.com/2/images/partner/");
//			break;
//		case "Discontinued":
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerDiscIconImg, "Blue banner icon image");
//			String msg2 = driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText();
//			String expectedMsg2 = "This product has been discontinued. Please  find a replacement";
//			if (msg2.equals(expectedMsg2))
//				logger.log(LogStatus.PASS, "Message " + expectedMsg2 + " is displayed for Discontinued product");
//			else {
//				logger.log(LogStatus.FAIL, "Message " + expectedMsg2 + " is not displayed for Discontinued product");
//				captureSS("Discontinued product blue banner message not displayed", LogStatus.FAIL);
//			}
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerDisclinkImg, "Blue banner link image");
//			clickLinkByXPath(PDPg.PDP_BlueBannerDisclink, "Blue banner find a replacement link is clicked");
//			waitExplicitlyForClickable(".//*[@id='r1:0:pt1:r1:0:pt1:cl37']", 5);
//			title = driver.getTitle();
//			if (title.equals("Shop Products | US Foods")) {
//				logger.log(LogStatus.PASS,
//						"Clicking find a replacement link navigates user to " + title + " as expected");
//				clickLinkByLinkText("Back to Product Detail");
//				waitExplicitlyForClickable(".//*[@id='pt1:r1:0:r1:0:pt1:gl1']", 5);
//				title = driver.getTitle();
//				if (title.equals("Product Detail | US Foods")) {
//					logger.log(LogStatus.PASS,
//							"Clicking Back to Product Details link navigates user to " + title + " as expected");
//				} else {
//					logger.log(LogStatus.FAIL, "Clicking Back to Product Details link navigates user to " + title);
//					captureSS("Discontinued product link validation", LogStatus.FAIL);
//				}
//			} else {
//				logger.log(LogStatus.FAIL, "Clicking Back to Product Details link not navigates user to " + title);
//				captureSS("Discontinued product link validation", LogStatus.FAIL);
//			}
//			break;
//		case "Suggested":
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerSPIconImg, "Blue banner icon image");
//			msg = driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText();
//			expectedMsg = "Save with Corporate Suggested.  View Suggested Product";
//			if (msg.equals(expectedMsg))
//				logger.log(LogStatus.PASS, "Message " + expectedMsg + " is displayed for Suggested product");
//			else {
//				logger.log(LogStatus.FAIL, "Message " + expectedMsg + " is not displayed for Suggested product");
//				captureSS("Suggested blue banner message not displayed", LogStatus.FAIL);
//			}
//			common1.validateObjectsDisplayed(PDPg.PDP_BlueBannerSPlinkImg, "Blue banner link image");
//			clickLinkByXPath(PDPg.PDP_BlueBannerSPlink, "Blue banner View Suggested Product link is clicked");
//			waitExplicitlyForClickable(".//*[@id='r1:0:pt1:j_id__ctru5pc2:0:pt1:cl4']", 5);
//			title = driver.getTitle();
//			if (title.equals("Product Comparison | US Foods")) {
//				logger.log(LogStatus.PASS,
//						"Clicking View Suggested Product link navigates user to " + title + " as expected");
//				clickLinkByLinkText("Back to Product Details");
//				waitExplicitlyForClickable(".//*[@id='pt1:r1:0:r1:0:pt1:gl1']", 5);
//				title = driver.getTitle();
//				if (title.equals("Product Detail | US Foods")) {
//					logger.log(LogStatus.PASS,
//							"Clicking Back to Product Details link navigates user to " + title + " as expected");
//				} else {
//					logger.log(LogStatus.FAIL, "Clicking Back to Product Details link navigates user to " + title);
//					captureSS("Discontinued product link validation", LogStatus.FAIL);
//				}
//			} else {
//				logger.log(LogStatus.FAIL, "Clicking Back to Product Details link not navigates user to " + title);
//				captureSS("Discontinued product link validation", LogStatus.FAIL);
//			}
//			break;
//		default:
//			break;
//		}
//	}

	public void iconCheck(String icon, String previousDiv) {
		if (!(icon.equals("Contracted"))) {
			if (driver.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + icon + "')]"))
					.isDisplayed() == true) {
				//logger.log(LogStatus.PASS, icon + " icon is displayed");
			} else {
				captureSS(icon + " icon validation", LogStatus.FAIL);
				//logger.log(LogStatus.FAIL, icon + " icon is not displayed");
			}
		} else {
			if (driver.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + icon + "')]"))
					.isDisplayed() == true) {
				//logger.log(LogStatus.PASS, "Contracted product icon is displayed");
			} else {
				captureSS("Contracted icon validation", LogStatus.FAIL);
				//logger.log(LogStatus.FAIL, "Contracted product icon is not displayed");
			}
		}

	}

	public void captureSS(String titleImg, LogStatus status) {
		/*String Screenshotpath = ExtentReportNG.captureScreenshot(titleImg);
		String image = logger.addScreenCapture(Screenshotpath);
		logger.log(status, image);*/
	}

	public void clickPDLinkwithProductNo(String prodNo) {
		int noOfProd = getSizeOfElementsSelected(".//*[contains(@id,'r1:0:pt1:lv1:') and contains(@id, ':pgl0')]");
		String prodNbrs;
		for (int i = 0; i < noOfProd; i++) {
			if (driver.findElement(By.id("r1:0:pt1:lv1:" + i + ":pgl23")).getText().contains(" (Replace ")) {
				continue;
			} else {
				prodNbrs = driver.findElement(By.xpath(".//*[@id='r1:0:pt1:lv1:" + i + ":pgl125']/tbody/tr/td[1]/span"))
						.getText();
				if (prodNbrs.contains(prodNo)) {
					actionMoveJSClick(
							driver.findElement(By.xpath("//a[@id='r1:0:pt1:lv1:" + i + ":cl15']")));
					return;
				}
			}
		}
	}
	
	public void actionMoveToElement(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element);
		act.build().perform();
		
	}

	public void actionMoveJSClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Actions act = new Actions(driver);
		act.moveToElement(element);
		act.build().perform();
	}
	public void jsScrollWindowDown() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,500)", "");
	}
	public void jsScrollWindowUp() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,-500)", "");
	}
	public void jsScrollPanelRight(String scrollpath, int position) throws TwfException {
		
		WebElement scollpanel=common1.getObjectByXpath(scrollpath,"Scrollpanelpath");
		Actions action = new Actions(driver);
		action.moveToElement(scollpanel).perform();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy("+position+",0)", "");
	}
	
public void jsScrollPanelLeft(String scrollpath, int position) throws TwfException {
		
		WebElement scollpanel=common1.getObjectByXpath(scrollpath,"Scrollpanelpath");
		Actions action = new Actions(driver);
		action.moveToElement(scollpanel).perform();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(
			    "document.getElementById('gvLocationHorizontalRail').scrollLeft += "+position+"", "");
		//executor.executeScript("window.scrollBy("+position+",0)", "");
	}

public void jsScrolltillelementview(String scrollpath) throws TwfException
{
	WebElement element = common1.getObjectByXpath(scrollpath,"elementtrageted");
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	//Thread.sleep(500); 
}
	
	public void jsScrollWindowRight() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(500,0)", "");
	}
	public void jsScrollWindowLeft() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(-500,0)", "");
	}
	
	public void save_current_order(String total_quantity) throws TwfException {
		HashMap<String, String> current_order_details = getCurrentOrderDetails(total_quantity);
		if (current_order_details.get("Total Cases").equals("0")) {
			//logger.log(LogStatus.PASS, "Before Save Order: Verified Current order panel");

		} 
		else if (current_order_details.get("Total Cases").equals(total_quantity)) {
			//logger.log(LogStatus.PASS, 	"After Save order: Verified Current order panel");
		} 
		else {
			captureSS("Save Order", LogStatus.FAIL);
		}
	}
	public boolean equalList(ArrayList<String> a, ArrayList<String> b) {
		if (a == null && b == null) {
			return true;
		}
		if ((a == null) && !(b == null) || !(a == null) && b == null) {
			return false;

		}
		Collections.sort(a);
		Collections.sort(b);
		
		if(a.equals(b))
		{
			//logger.log(LogStatus.PASS, "Both lists are Equal");
			return true;
		}
		else{
			/*String Screenshotpath = ExtentReportNG
					.captureScreenshot("Lists are not equal");

			String image = logger.addScreenCapture(Screenshotpath);

			logger.log(LogStatus.FAIL, image);

			logger.log(LogStatus.FAIL, "The two lists are not equal");*/
			
			return false;
		}
		
	}

	public void getProductsOnOrderModule()
	{
		
	}
	public HashMap<String, String> getCurrentOrderDetails(String msg) throws TwfException {

		WebElement element = this.getObjectByXpath(page.currentOrderModule,msg);
		HashMap<String, String> currentOrderDtls = new HashMap<String, String>();
		if (element.isDisplayed()) {
			map.put("Total Cases",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl21']/tbody/tr/td[2]/span",msg).getText());
			map.put("Total Eaches",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl46']/tbody/tr/td[2]/span",msg).getText());
			map.put("Order Total",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl23']/tbody/tr/td[2]",msg).getText());
			map.put("Delivery Date",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl26']/tbody/tr/td[2]/a",msg).getText());
			map.put("PO#", common1.getObjectByXpath("//a[@id='r1:0:pt1:r1:0:cl9']/span",msg).getText());
			// map.put("save message",
			// common1.getObjectByXpath("//span[@class='x275']").getText());
			//logger.log(LogStatus.PASS, "Current order details panel displayed and its details obtained");
		}

		else {
			//logger.log(LogStatus.FAIL, "Current order panel not displayed");
			//captureSS("Current order panel not displayed", LogStatus.FAIL);
		}

		return currentOrderDtls;

	}
	
	public String getCurrentOrderDetails1(String key) throws TwfException {

		WebElement element = this.getObjectByXpath(page.currentOrderModule,key);
		HashMap<String, String> currentOrderDtls = new HashMap<String, String>();
		if (element.isDisplayed()) {
			currentOrderDtls.put("Total Cases",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl21']/tbody/tr/td[2]/span",key).getText());
			currentOrderDtls.put("Total Eaches",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl46']/tbody/tr/td[2]/span",key).getText());
			currentOrderDtls.put("Order Total",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl23']/tbody/tr/td[2]",key).getText());
			currentOrderDtls.put("Delivery Date",
					common1.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl26']/tbody/tr/td[2]/a",key).getText());
			currentOrderDtls.put("PO#", common1.getObjectByXpath("//a[@id='r1:0:pt1:r1:0:cl9']/span",key).getText());
			// map.put("save message",
			// common1.getObjectByXpath("//span[@class='x275']").getText());
			//logger.log(LogStatus.PASS, "Current order details panel displayed and its details obtained");
		}

		else {
			//logger.log(LogStatus.FAIL, "Current order panel not displayed");
			captureSS("Current order panel not displayed", LogStatus.FAIL);
		}

		return currentOrderDtls.get(key);

	}
	
	public ArrayList<String> getProductsInOrderModuleDtls(String msg) throws TwfException {
		String str = driver.findElement(By.xpath(".//*[@id='r1:0:pt1:r1:0:pgl32']/div[1]/span")).getText();
		int TotallineNumber = getIntegerFromString(str);
		ArrayList<String> productIDs = new ArrayList<>();
		for (int i = 0; i <= TotallineNumber - 1; i++) {
			String temp = common1.getObjectByXpath("//div[@id='r1:0:pt1:r1:0:i3:"+i+":pgl69']/div[2]/span",msg).getText();
			productIDs.add(temp.replaceAll("# ", ""));
		}
		//logger.log(LogStatus.PASS, "Products displayed in Products in Order module are "+productIDs);	
		System.out.println("Captured Product ids products in order module");
		return productIDs;
	}
	
	public void clickButton(String buttonName) throws TwfException {
		element = common1.getObjectByXpath("//button[text()='" + buttonName + "']",buttonName);
		if (element.isDisplayed()) {
			element.click();
			//logger.log(LogStatus.PASS, buttonName + "button is clicked");
		}

		else {
			//logger.log(LogStatus.FAIL, buttonName + "button is not displayed");
			//captureSS("Button not displayed", LogStatus.FAIL);
		}
	}
	
	public void verifyCurrentOrderDtlsCSCount(String count) throws TwfException
	{
		if(getCurrentOrderDetails("Total Cases").equals(count)){
			//logger.log(LogStatus.PASS, "Total CS quantity value is displayed as "+ count);
		}else
		{
			//logger.log(LogStatus.FAIL, "Total CS quantity value is not displayed as "+ count);
			//captureSS("CSQty value", LogStatus.FAIL);
		}
	}
	
//	public void validateAndClickPDPBreadCrumb(String breadCrumb) {
//		String brCrumbTxt = driver.findElement(By.xpath(PDPg.PDP_BreadCrumb)).getText();
//		if (brCrumbTxt.equals(breadCrumb)) {
//			logger.log(LogStatus.PASS, "Bread Crumb link is displayed with text: " + brCrumbTxt);
//			driver.findElement(By.xpath(PDPg.PDP_BreadCrumb)).click();
//			logger.log(LogStatus.PASS, "Bread Crumb " + brCrumbTxt + " link is clicked");
//			if (driver.findElement(By.xpath(".//*[@id='r1:0:pt1:pt_of1']")).getText().equals("Create Order")) {
//				logger.log(LogStatus.PASS, "Clicking " + brCrumbTxt + " takes user to "
//						+ brCrumbTxt.substring(8, brCrumbTxt.length()) + " page");
//			}
//		} else {
//			logger.log(LogStatus.FAIL, "Bread Crumb link is not displayed with text: " + brCrumbTxt);
//			captureSS("BreadCrumbNotDisplayed", LogStatus.FAIL);
//		}
//	}

	public void clickLinkByLinkText(String linkText) {
		driver.findElement(By.xpath(".//a[contains(text(),'" + linkText + "')]")).click();
		//logger.log(LogStatus.INFO, linkText + " is clicked");
	}

	public void clickLinkByXPath(String xPath, String msg) {
		driver.findElement(By.xpath(xPath)).click();
		//logger.log(LogStatus.INFO, msg);
	}

	public void validateIconHoverMessage(String previousDiv, String typeOfProd) {
		String msg = new String();
		Actions actions = new Actions(driver);
		switch (typeOfProd) {
		case "Special-Order":
			msg = "This product is a special order.  Contact your local sales representative to order this product.";
			break;
		case "CES-Item":
			msg = "This is a Culinary Equipment and Supply product. Orders may be split and delivered separately. The lead time is 3 days.";
			break;
		case "Order-Guide":
			msg = "This product is line number";
			break;
		case "Locally-Sourced":
			msg = "This product is locally sourced.";
			break;
		case "Just-in-Time":
			msg = "This product must be ordered by a specific time. Contact your local division for cutoff schedule.";
			break;
		case "dwo":
			msg = "Discontinued When Out";
			break;
		case "https://media.usfood.com/2/images/partner/":
			msg = "This product is valid for: Contracted Product";
			break;
		default:
			break;
		}
		actions.moveToElement(driver.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + typeOfProd + "')]")));
		actions.build().perform();
		checkIconMessage(msg);
	}
	public void moveToElement(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();
	}
	public void moveToXPath(String xPath)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(xPath)));
		actions.build().perform();
	}
	public void clickByXPath(String xPath, String msg)
	{
		driver.findElement(By.xpath(xPath)).click();
		//logger.log(LogStatus.INFO, msg + " is clicked");
	}
	public void clickByXPath(String xPath)
	{
		driver.findElement(By.xpath(xPath)).click();
	}
	public void clickById(String Id, String msg)
	{
		driver.findElement(By.id(Id)).click();
		//logger.log(LogStatus.INFO, msg + " is clicked");
	}
	public void checkIconMessage(String message)
	{
		if (driver.findElement(By.xpath(".//*[contains(text(),'"+ message +")]")).isDisplayed())
		{
			//logger.log(LogStatus.PASS, "Icon message is displayed as: \""+message+"\" as expected");
		}
		else
		{
			//logger.log(LogStatus.PASS, "Icon message is displayed as: \""+message+"\" which is not as expected");
			//captureSS("Icon message validation", LogStatus.FAIL);
		}		
	}
//	public void navigateToPage(String pageName)
//	{		
//		switch(pageName.toLowerCase())
//		{
//			case "homepage":
//				moveToElement(driver.findElement(By.xpath(page.topUSFLogoIcon)));
//				clickByXPath(page.topUSFLogoIcon, "Home page icon");
//				waitExplicitlyForPresence(page.welcomeBackMsg, 8);
//				if(driver.findElement(By.xpath(page.welcomeBackMsg)).getText().contains("Welcome back"))
//					logger.log(LogStatus.PASS, "User landed on to HomePage");
//				else
//				{
//					logger.log(LogStatus.FAIL, "User not landed on to HomePage");
//					captureSS("Home page not landed", LogStatus.FAIL);
//				}
//			break;
//			case "viewalllists":
//				actionMoveJSClick(driver
//						.findElement(By.xpath(page.listDropdownImg)));
//				actionMoveJSClick(driver.findElement(By.xpath("//a[text()='View All Lists']")));
//				if(driver.findElement(By.id("r1:0:pt1:pt_of1")).getText().equals("Lists"))
//					logger.log(LogStatus.PASS, "User landed on to View All Lists page");
//				else
//				{
//					logger.log(LogStatus.FAIL, "User not landed on to View All Lists Page");
//					captureSS("View All Lists page not landed", LogStatus.FAIL);
//				}
//			case "shopproducts":
//				clickByXPath(page.shopProductsLink, "Shop Products link");
//				waitExplicitlyForPresence(".//*[@id='r1:0:pt1:r1:0:pt1:pt_of1']", 8);
//				if(driver.findElement(By.xpath(".//*[@id='r1:0:pt1:r1:0:pt1:pt_of1']")).getText().equals("Shop Products"))
//					logger.log(LogStatus.PASS, "User landed on to Shop Products page");
//				else
//				{
//					logger.log(LogStatus.FAIL, "User not landed on to Shop Products Page");
//					captureSS("Shop Products page not landed", LogStatus.FAIL);
//				}
//			break;
//			case "ourexclusives":
//				clickByXPath(page.ourExclusivesLink, "Our Exclusives link");
//				waitExplicitlyForPresence(oe.OE_Title, 8);
//				if(driver.findElement(By.xpath(oe.OE_Title)).getText().equals("Our Exclusives"))
//					logger.log(LogStatus.PASS, "User landed on to Our Exclusives page");
//				else
//				{
//					logger.log(LogStatus.FAIL, "User not landed on to Our Exclusives Page");
//					captureSS("Our Exclusives page not landed", LogStatus.FAIL);
//				}
//			break;
//			case "mybusiness":
//				clickByXPath(page.myBusinessLink, "My Business link");
//				waitExplicitlyForPresence(mb.MB_Title, 8);
//				if(driver.findElement(By.xpath(mb.MB_Title)).getText().equals("My Business"))
//					logger.log(LogStatus.PASS, "User landed on to My Business page");
//				else
//				{
//					logger.log(LogStatus.FAIL, "User not landed on to My Business Page");
//					captureSS("My Business page not landed", LogStatus.FAIL);
//				}
//			break;
//		}
//	}
	public void verifyTextExists(String xPath, String txtToCheck)
	{
		String txt = driver.findElement(By.xpath(xPath)).getText();
		if(txt.contains(txtToCheck))
		{
			//logger.log(LogStatus.PASS, "\"" + txtToCheck + "\" is displayed");
			System.out.println(txtToCheck + " is verified");
		}			
		else
		{
			//logger.log(LogStatus.FAIL,  "\"" + txtToCheck + "\" is not displayed");
			System.out.println(txtToCheck + " is failed");
		}			
	}
	public void verifyTextExists(String xPath, String txtToCheck, String msg)
	{
		String txt = driver.findElement(By.xpath(xPath)).getText();
		if(txt.contains(txtToCheck))
		{
			//logger.log(LogStatus.PASS, "\"" + txtToCheck + "\" is displayed " + msg);
			System.out.println(txtToCheck + " is verified");
		}			
		else
		{
			//logger.log(LogStatus.FAIL,  "\"" + txtToCheck + "\" is not displayed " + msg);
			System.out.println(txtToCheck + " is failed");
		}			
	}
	public void verifyBreadCrumb(String xPath, String breadCrumbTxts)
	{
		String txt = driver.findElement(By.xpath(xPath)).getText();
		String splitTxt[] = breadCrumbTxts.split(">");
		for(String i:splitTxt)
		{
			if(txt.contains(i))
			{
				//logger.log(LogStatus.PASS,  "\"" + i + "\" is displayed in bread crumb");
			}
			else
			{
				//logger.log(LogStatus.FAIL,  "\"" + i + "\" is not displayed in bread crumb");
			}
		}
	}
	public void verifyLinkWithNameExists(String xPath, String linkToCheck)
	{
		String isDisabled = driver.findElement(By.xpath(xPath)).getAttribute("disabled");
		if(isDisabled==null || isDisabled.equals("disabled"))
		{
			//logger.log(LogStatus.PASS, "\"" + linkToCheck + "\" is enabled as link");
			System.out.println(linkToCheck + " is verified");
		}			
		else
		{
			//logger.log(LogStatus.FAIL,  "\"" + linkToCheck + "\" is not enabled as link");
			System.out.println(linkToCheck + " is failed");
		}
	}
	public void verifyLinksInDropdown(String xPathToHover, String xPath, String txtToCheck)
	{
		moveToElement(driver.findElement(By.xpath(xPathToHover)));
		String txt[] = txtToCheck.split(":");
		for(String str:txt)
		{
			sleep(250);
			verifyTextExists(xPath, str, "in global dropdown");
		}
	}
	public void jsScrollToElement(String xPath)
	{
		WebElement element = driver.findElement(By.xpath(xPath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		sleep(500); 
	}
	public void noOfLinksForXPath(String xPath, int noOfLinksToBeVerified)
	{
		int noOfLinks = common1.getSizeOfElementsSelected(xPath);
		if (noOfLinks == noOfLinksToBeVerified){
			//logger.log(LogStatus.PASS, "All the left side links are displayed");
		
		}else
		{
			//logger.log(LogStatus.FAIL, "Some links are not displayed in the left side");
			common1.captureSS("Left side links not displayed", LogStatus.FAIL);
		}
	}
	public void validateLinkNavigation(String xPath, String pageToNavigate)
	{
		String str = pageToNavigate;
		WebElement element = driver.findElement(By.xpath(xPath));
		pageToNavigate = pageToNavigate + " | US Foods";
		moveToElement(element);
		jsScrollWindowUp();
//		jsScrollToElement(xPath);		
		if (element.isDisplayed())
		{
//			common1.jsclick(driver.findElement(By.xpath(xPath)));
//			click able(xPath);
//			noOfLinksForXPath(oe.OE_LeftSideLinksAll, 5);
			element.click();
			sleep(1500);
//			if(pageToNavigate.equals("Our Exclusives | US Foods"))
//				sleep(1500);
			waitExplicitlyForPresence(".//span[text()='"+str+"']", 10);
			String title = driver.getTitle();
			if(title.contains(pageToNavigate))
			{
				//logger.log(LogStatus.PASS,  "User landed on to \"" + pageToNavigate + "\" page");
				System.out.println("Verified: "+pageToNavigate);
			}				
			else{
				//logger.log(LogStatus.FAIL,  "User not landed on to \"" + pageToNavigate + "\" page instead landed to "+title);
				System.out.println("FAILED: "+pageToNavigate);
			}
		}				
	}
}