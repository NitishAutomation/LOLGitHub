package com.lol.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.junit.Assert;
import org.testng.Assert;
import org.testng.Reporter;

import com.tavant.utils.TwfException;



public class Common extends TestExecutor {

	// WebDriver driver = null;
	WebElement element = null;
	List<WebElement> rows = null;
	Select select = null;

	public void sleep(int timeInMillisecs) {
		try {
			Thread.sleep(timeInMillisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// switch to frame
	public void switchToframe(String framename) {
		driver.switchTo().frame(driver.findElement(By.id(framename)));
		//logger.log(LogStatus.INFO, "LOL:: Win Field: Switching into "+framename+" frame");
	}

	// switch out of frame
	public void switchOutframe() {
		driver.switchTo().defaultContent();
	}

	// Select button in any page
	public void select() throws TwfException {
		getObjectByXpath("//img[@name='hc_Select']").click();
	}

	// OK button in any page
	public void OK() throws TwfException{
		getObjectByXpath(".//img[@id='hc_OK']").click();
	}

	// Form_Exit button in any page
	public void Form_Exit() throws TwfException {
		getObjectByXpath("//img[@id='FORM_EXIT_BUTTON']").click();
	}

	// Find button in any page
	public void Find() throws TwfException {
		getObjectByXpath("//img[@name='hc_Find']").click();
	}
	
	
	//find ROW EXIT BUtton
	public void Row() throws TwfException{
		getObjectByXpath(".//*[@id='ROW_EXIT_BUTTON']").click();
		}
	
	// Find button in any page
		public void Form() throws TwfException {
			getObjectByXpath(".//*[@id='FORM_EXIT_BUTTON']").click();
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
	public void CaptureScreenShot(WebDriver driver, String fileName) {
		// EventFiringWebDriver efwd = new EventFiringWebDriver(driver);
		TakesScreenshot efwd = ((TakesScreenshot) driver);
		File src = efwd.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./ScreenShotLib/" + fileName));
			// FileUtils.copyFile(src, new
			// File("./ScreenShotLib/"+System.currentTimeMillis()+fileName));
		} catch (Exception e) {

		}

	}

	// TBD
	public void tableValue(String tableXpath, String keyFieldValue, String valueToCheck) throws TwfException {
		element = driver.findElement(By.xpath(tableXpath));
		rows = element.findElements(By.tagName("tr"));
		for (int i = 2; i < rows.size(); i++) {
			if (getObjectByXpath(tableXpath).equals(keyFieldValue)) {

			}
		}
	}

	// Check element present or not
	public boolean isExists(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isExists(String order_Confirmation_message) {

		try {
			return driver.findElement(By.xpath(order_Confirmation_message)).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// selecting multiple times for particular element
	public void clickmultiple(int num, String xpath) throws InterruptedException, TwfException {

		for (int i = 0; i < num; i++) {
			// click the button

			common.getObjectByXpath(xpath).click();
			// wait 2 seconds
			Thread.sleep(1000);
			// check that data is being generated correctly

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

	public boolean IsObjectsDisplayed(String object) throws TwfException {
		try {
			if (driver.findElement(By.xpath(object)).isDisplayed() == true) {
				//logger.log(LogStatus.INFO, "LOL:: Win Field: ");
				return true;
			}
		} catch (Exception e) {
			addExceptionToReport("object  is not found"+object+"", "", "");
			return false;
		}
		return false;
	}
	
	
	//verify object is displayed ot not 
	public boolean IsObjectsDisplayed(String object,String msg) throws TwfException {
		try {
			if (driver.findElement(By.xpath(object)).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, "LOL:: Win Field: "+ msg+"is displayed");
				return true;
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, "LOL:: Win Field: "+ msg+"is not displayed");
			addExceptionToReport("Object is not found"+object+"", "", "");
			return false;
		}
		return false;
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
			} catch (Exception e1)
			{
				addExceptionToReport("Object is not found"+by+"", "", "");
				return false;
			}
		}
		return false;
	}

	public void select_CheckBox(WebElement element, String str) throws TwfException {

		try {

			if (element.isSelected()) {
				//logger.log(LogStatus.INFO, str + " is alrady checked");

			} else {
				element.click();
				//logger.log(LogStatus.INFO, str + " is checked successfully");
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, str + " is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(str);
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("object is not found"+element+"", "", "");

		}
	}

	public void select_submit() throws TwfException {
		WebElement element = common.getObjectByXpath(".//*[@id='divC0_30']/span");
		try {
			if (element.isDisplayed()) {
				element.click();
				//logger.log(LogStatus.INFO, "LOL:: Win Field: Selected submit from tool bar");
			} else {
				//logger.log(LogStatus.INFO, "LOL:: Win Field: Submit not displayed in tool bar");
				
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, "LOL:: Win Field: UNable To select Submit form in tool barr");
		}

	}

	public void select_option_from_toolbar(WebElement element, String str) throws TwfException {

		try {
			if (element.isDisplayed()) {
				element.click();
				//logger.log(LogStatus.INFO, str + "Selected sucessfully");
			} else {
				//logger.log(LogStatus.INFO, "LOL:: Win Field: not displayed ");

			}
		} catch (Exception e) {
			// logger.log(LogStatus.FAIL, "LOL:: Win Field: UNable To select in
			// tool barr");
			//logger.log(LogStatus.FAIL, str + " is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(str);
			String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			 * 
			
					}

	}

	//// validate Editable field or not
	public void validateObjectEditable(String xPath, String msg) {
		try {
			String ss = getObjectByXpath(xPath).getAttribute("readonly");
			if (ss.equalsIgnoreCase("true")) {
				//logger.log(LogStatus.PASS, msg + " is not Editable");
				// Assert.assertTrue(true);
				System.out.println("pass");
			} else {
				//logger.log(LogStatus.PASS, msg + " is  Editable");
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, msg + " is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+element+"", "", "");
		}
	}

	// MenuAdd
	/*
	 * public void ClickOnMenuOK() { try { if
	 * (driver.findElement(By.xpath("//*[@id='hc_OK']")).isDisplayed() == true)
	 * { driver.findElement(By.xpath("//*[@id='hc_OK']")).click();
	 * logger.log(LogStatus.PASS, "LOL:: Win Field: Click on the Ok Option"); //
	 * Assert.assertTrue(true); } } catch (Exception e) {
	 * logger.log(LogStatus.FAIL, " Ok button is not displayed"); String
	 * Screenshotpath = ExtentReportNG.captureScreenshot("OKButton"); String
	 * image = logger.addScreenCapture(Screenshotpath);
	 * logger.log(LogStatus.FAIL, image); } }
	 */

	// MenuAdd
	public void ClickOnAddMenu() {
		try {
			if (driver.findElement(By.xpath("//*[@id='hc_Add']")).isDisplayed() == true) {
				driver.findElement(By.xpath(".//*[@id='hc_Add']")).click();
				//logger.log(LogStatus.PASS, "LOL:: Win Field: Click on the Add button");
				//Assert.assertTrue(true);
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, " Add button is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot("AddButton");
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
		}
	}

	// Inputbyxpath
	public static void inputbyxpath(String objects, String description, String testData) throws TwfException {
		try {
			int count = driver.findElements(By.xpath(objects)).size();
			if (count == 1) {
				driver.findElement(By.xpath(objects)).sendKeys(testData);
				
				//logger.log(LogStatus.PASS, "LOL:: Win Field: " + description);
			} else {
				//logger.log(LogStatus.FAIL, " Add button is not displayed");
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, " Add button is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot("AddButton");
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+objects+"", "", "");
		}
	}

	// MenuClaose
	public void ClickOnCloseMenu() {
		try {
			if (driver.findElement(By.xpath(".//*[@id='hc_Close']")).isDisplayed() == true) {
				driver.findElement(By.xpath(".//*[@id='hc_Close']")).click();
				//logger.log(LogStatus.PASS, "LOL:: Win Field: Click on the Close button");
				// Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, " close button is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot("CloseButton");
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
		}
	}

	// cancel
	// MenuClaose
	public void ClickOnCancelMenu() {
		try {
			if (driver.findElement(By.xpath(".//*[@id='hc_Cancel']")).isDisplayed() == true) {
				driver.findElement(By.xpath(".//*[@id='hc_Cancel']")).click();
				//logger.log(LogStatus.PASS, " LOL:: Win Field: Click on the Cancel button");
				//Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, " close button is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot("CloseButton");
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
		}
	}

	// MenuOk
	/*
	 * public void ClickOnMenuOK() { try { if
	 * (driver.findElement(By.xpath("//*[@id='hc_OK']")).isDisplayed() == true)
	 * { driver.findElement(By.xpath("//*[@id='hc_OK']")).click();
	 * logger.log(LogStatus.PASS, "LOL:: Win Field: Click on the Ok Option"); //
	 * Assert.assertTrue(true); } } catch (Exception e) {
	 * logger.log(LogStatus.FAIL, " Ok button is not displayed"); String
	 * Screenshotpath = ExtentReportNG.captureScreenshot("OKButton"); String
	 * image = logger.addScreenCapture(Screenshotpath);
	 * logger.log(LogStatus.FAIL, image); } }
	 */

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
		
		if(driver.getTitle().trim().equals(title)){
			//logger.log(LogStatus.PASS, "LOL:: Win Field: "+title+"displayed successfully");
			return true;
		}
		else{
			//logger.log(LogStatus.PASS, "LOL:: Win Field: is not "+title+"displayed successfully");
		}
		return false;
	}
	//gettiltle
	public String gettitle(){
		return driver.getTitle();
		
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
		WebElement saveButton = this.getObjectByXpath(".//*[@id='ibnSave']/span");
		this.jsclick(saveButton);
	}

	// Save button in any page
	public void saveAS() throws TwfException {
		getObjectByXpath(".//*[@id='ibnSave']/span").click();
	}

	// Click on Yes button of Warning dialogue box shown during the reboot of
	// device
	public void clickYes() throws TwfException {
		getObjectByXpath("//*[@id='warnDialog']/tbody/tr[2]/td[1]/input").click();

	}

	// Search button in any page
	public void search() throws TwfException {
		getObjectByXpath("//input[@value='Search' and @class='FormButton']").click();
	}

	public void jsclick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// Cancel button in any page
	public void cancel() throws TwfException {
		getObjectByXpath("//input[@value='Cancel']").click();
	}

	// Back button in any page
	public void back() throws TwfException {
		getObjectByXpath("//a[@href='javascript:history.go(-1);']").click();
	}

	// NA Home page
	public void home() throws TwfException {
		getObjectByLinkText("Home").click();
	}

	// Save and Acquire
	public void saveAndAcquire() throws TwfException {
		getObjectByXpath("//input[@value='Save & Acquire']").click();
	}

	// Save and Release
	public void saveAndRelease() throws TwfException {
		getObjectByXpath("//input[@value='Save & Release']").click();
	}

	// Help Link
	public void help() throws TwfException {
		getObjectByLinkText("Help").click();
	}

	// Returns true if the element is present.
	public boolean isElementExist(By by) {

		this.changeTimeOut(5);
		try {
			driver.findElement(by);
		} catch (Exception e) {
			try {
				driver.findElement(by);
			} catch (Exception e1) {
				this.changeTimeOut(50);
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
		this.getObjectByXpath(elementforDropdown).click();
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		WebElement clictheobject = getObjectByXpath(".//li//a[text()='" + text + "']");
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
		select = new Select(driver.findElement(By.id(optionName)));
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

	// Select drop down option. Gets option name and option to be selected
	public boolean selectOptionbyid(String optionName, String optionToBeSelected) {

		int count = 0;
		select = new Select(driver.findElement(By.id(optionName)));
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

	public String get_Options_selected_Text_value(String optionName) throws TwfException {

		Select sel = new Select(common.getObjectByXpath(optionName));
		WebElement strCurrentValue = sel.getFirstSelectedOption();
		// Print the Currently selected value
		System.out.println(strCurrentValue.getText());
		return strCurrentValue.getText();
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
	public WebElement getObjectByXpath(String xpathKey) throws TwfException {
		// Reporter.log("Trying to Find object having Xpath as :
		// "+xpathKey+"<br>");
		try {
			
			return driver.findElement(By.xpath(xpathKey));
		
		} catch (Throwable t) {
			//Reporter.log("Object having Xpath as " + xpathKey + " not found.");
			//addExceptionToReport("Unable to locate xpath"+xpathKey, "", "");
			System.out.println("In exception");
			addExceptionToReport("Problem in finding xpath "+xpathKey,"" , "");
			return null;
		}
	}

	public void validatePageTitle(String pageName) {
		waitExplicitlyForPresence(".//span[text()='" + pageName + "']", 5);
		String title = driver.getTitle();
		if (title.contains(pageName)) {
			//logger.log(LogStatus.PASS, title + " page is loaded successfully");
		} else {
			//logger.log(LogStatus.FAIL, pageName + " page is not loaded instead user lands on " + title);
			//captureSS("Page Navigation failed", LogStatus.FAIL);
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
			
			addExceptionToReport("object is not found"+name+"", "", "");
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
	
			try{
			addExceptionToReport("object is not found "+id+"", "", "");
			}catch(Exception e){
				
				System.out.println("Caught the exception");
			}
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
	public WebElement getObjectByCssSelector(String selector, String msg) throws TwfException {
		// Reporter.log("Trying to Find object having CssSelector as :
		// "+selector+"<br>");
		WebElement element;
		try {
			element = driver.findElement(By.cssSelector(selector));
			//logger.log(LogStatus.PASS, msg + " is displayed");
			return element;
		} catch (Throwable t) {
//			Reporter.log("Object having CssSelector as " + selector + " not found" + "<br>");
//			Assert.assertTrue(false, "Object having CssSelector as " + selector + " not found");
			addExceptionToReport("Object is not found "+selector+"", "", "");
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
			// System.out.println(common.getObjectByXpath(tableXpath+"//tr[1]/td["+j+"]").getText().trim());
			if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]").getText().trim().equals(columnName)) {
				for (int i = 3; i <= rowElmt.size(); i++) {

					getData = this.getObjectByXpath(tableXpath + "//tr[" + i + "]/td[" + j + "]").getText().trim();
					displayedNames.add(getData);
					SortedNames.add(getData);
					System.out.println(displayedNames);

				}
				break;
			} else {
//				Reporter.log("column with the name " + columnName + " not found" + "<br>");
//				Assert.fail("column with the name " + columnName + " not found");
				addExceptionToReport("Object is not found"+tableXpath+"", "", "");
			}

		}

		this.sleep(2000);
		List<String> sortingOperation = displayedNames;
		this.listSortEx(sortingOperation, descOrder);
		System.out.println(sortingOperation);
		try {
			Assert.assertEquals(SortedNames, sortingOperation);
//			Reporter.log("Sort order is as expected" + "<br>");
		} catch (AssertionError e) {
//			Reporter.log("Sort order is not as expected" + "<br>");
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
			// System.out.println(common.getObjectByXpath(tableXpath+"//tr[1]/td["+j+"]").getText().trim());
			if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]").getText().trim().equals(columnName)) {
				if (descOrder && this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/asc.gif']")
						.isDisplayed()) {
					this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]/a").click();
					Status = true;
				} else if (descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/desc.gif']")
								.isDisplayed()) {
//					Reporter.log(
//							"column with the name " + columnName + " is already sorted in Descending order" + "<br>");
 				Status = true;
				} else if (!descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/desc.gif']")
								.isDisplayed()) {
					this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]/a").click();
					Status = true;
				} else if (!descOrder
						&& this.getObjectByXpath(tableXpath + "//tr[1]/td[1]//span//img[@ src='images/asc.gif']")
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
				System.out.println(this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]").getText().trim());
				if (this.getObjectByXpath(tableXpath + "//tr[1]/td[" + j + "]").getText().trim().equals(columnName)) {
					for (int i = 2; i <= rowElmt.size(); i++) {

						System.out.println(this.getObjectByXpath(tableXpath + "//tbody//tr[" + i + "]/td[" + j + "]/a")
								.getText().trim());
						String actual = this.getObjectByXpath(tableXpath + "//tbody//tr[" + i + "]/td[" + j + "]/a")
								.getText().trim();
						// int check = actual.compareToIgnoreCase(element);
						if (actual.equalsIgnoreCase(element)) {
							return true;
							// common.getObjectByXpath(tableXpath+"//tbody//tr["+i+"]/td["+j+"]/a").click();
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
			if (getObjectByXpath(xPath).isDisplayed() == true) {
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

	public static void send(final String from, final String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static void SendMail()

	{

		try {

			WebDriver driver = new FirefoxDriver();

			WebDriverWait wait = new WebDriverWait(driver, 90);

			driver.get("https://outlook.com/owa/landolakes.com");

			Thread.sleep(6000);

			// Enter User Name and Password

			driver.findElement(By.xpath(".//input[@name='UserName']")).sendKeys("sprabhu@landolakes.com");
			// driver.findElement(By.xpath(".//input[@name='UserName']")).sendKeys("pgangadharan@landolakes.com");

			driver.findElement(By.xpath(".//input[@name='Password']")).sendKeys("Feb@2017");

			driver.findElement(By.xpath(".//span[text()='Sign in']")).click();

			driver.manage().window().maximize();

			Thread.sleep(5000);

			// babyfunction.waitbyxpathvisibility(".//button[@title='Write a new
			// message (N)']", 1500);

			driver.findElement(By.xpath(".//button[@title='Write a new message (N)']")).click();

			Thread.sleep(5000);

			// babyfunction.waitbyxpathvisibility("(.//button[@title='Send'])[2]",
			// 1500);

			Thread.sleep(3000);

			// Mail Subject

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");

			Date currentdate = new Date();

			String onlydate = dateFormat.format(currentdate);

			System.out.println(onlydate);

			// driver.findElement(By.xpath("//input[@aria-labelledby='MailCompose.SubjectWellLabel']")).sendKeys("QA
			// execution report "+onlydate+"");

			// driver.findElement(By.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[5]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[6]/div[2]/input")).sendKeys("QA
			// execution report "+onlydate+"");
			driver.findElement(By
					.xpath("//div[@id='primaryContainer']/div[5]/div/div/div/div[4]/div[3]/div/div[4]/div/div/div[3]/div[5]/div/div/div[2]/div[2]/div/div/div[2]/div[6]/div[2]/input"))
					.sendKeys("Evanios Notification - MPIS Automated Selenium Test");

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p"))
					.sendKeys("Result: Pass");

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p"))
					.sendKeys(Keys.ENTER);

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p[2]"))
					.sendKeys("Details:");

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p[2]"))
					.sendKeys(Keys.ENTER);

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p[3]"))
					.sendKeys("User logged in successfully. System took " + "" + "millisec to login");

			driver.findElement(By
					.xpath(".//*[@id='primaryContainer']/div[5]/div/div[1]/div/div[4]/div[3]/div/div[4]/div[1]/div/div[3]/div[5]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div[3]/div/p[3]"))
					.sendKeys(Keys.ENTER);

			WebElement we = driver.findElement(By.xpath("//span[text()='To']//following::form/input"));

			// we.sendKeys("ajmohammed@landolakes.com");
			we.sendKeys("sprabhu@landolakes.com");

			// }

			/*
			 * if(driver.findElement(By.xpath(
			 * "//div[@aria-label='Message body']")).isDisplayed()){
			 * 
			 * System.out.println("Content");
			 * 
			 * driver.findElement(By.xpath("//div[@aria-label='Message body']"
			 * )).click();
			 * 
			 * Thread.sleep(3000);
			 * 
			 * 
			 * 
			 * //BABYRUN obj= new BABYRUN();
			 * 
			 * driver.findElement(By.xpath("//div[@aria-label='Message body']"
			 * )).sendKeys("QA automation execution status\n\n");
			 * 
			 * driver.findElement(By.xpath("//div[@aria-label='Message body']"
			 * )).sendKeys("Netsuite(https://system.sandbox.netsuite.com)- "+
			 * "Validate login credentials");
			 * 
			 * 
			 * 
			 * }
			 */

			Thread.sleep(3000);

			driver.findElement(By.xpath("(//button[@title='Send'])[2]")).click();

			Thread.sleep(5000);

			// driver.findElement(By.xpath("//button[@class='_ho2_2 _ho2_4
			// ms-fwt-r ms-fcl-ns ms-bcl-nl o365button _ho2_9']")).click();

			/*
			 * Thread.sleep(2000);
			 * 
			 * driver.findElement(By.xpath(".//button[@autoid='_ho2_0']")).click
			 * ();
			 * 
			 * Thread.sleep(3000);
			 * 
			 * driver.findElement(By.xpath("//span[text()='Sign out']"
			 * )).click();
			 * 
			 * Thread.sleep(5000);
			 * 
			 * driver.close();
			 */
			driver.close();

		} catch (Exception e) {
			//common.captureSS("Error found", LogStatus.FAIL);
		}

	}

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void waitForLoad(int i) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(pageLoadCondition);

	}

	public void validateObjectsDisplayed(String xPath, String msg) throws TwfException {
		try {
			if (getObjectByXpath(xPath).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is displayed");
				 Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, msg + " is not displayed");
			//String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			//String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object having xpath "+xPath+" is not displayed..", "", "");
		}
	}
	//Validate object displayed or not
	public void validateObjectsDisplayedbyid(String id, String msg) throws TwfException {
		try {
			if (getObjectById(id).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is displayed");
				Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, msg + " is not displayed");
			//String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			//String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+id+"", "", "");
		}
	}

	public void validateObjectsNotDisplayed(String xPath, String msg) throws TwfException {
		try {
			if (getObjectByXpath(xPath).isDisplayed() == false) {
				//logger.log(LogStatus.PASS, msg + " is not displayed");
				 Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, msg + " is displayed");
			//String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			//String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+xPath+"", "", "");
			
		}
	}

	public void validateObjectsDisplayed(By by, String msg) throws TwfException {
		try {
			if (driver.findElement(by).isDisplayed() == true) {
				//logger.log(LogStatus.PASS, msg + " is displayed");
				Assert.assertTrue(true);
			}
		} catch (AssertionError e) {
			 //logger.log(LogStatus.FAIL, msg + " is not displayed");
			//String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			//String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("Object is not found"+by+"", "", "");
		}
	}

	public boolean validateObjectsDisplayedWOReport(String xPath) throws TwfException {
		try {
			if (getObjectByXpath(xPath).isDisplayed() == true) {
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

	public String getCustomerNumberSelected() throws TwfException {
		String custNbr;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// (new WaitForPageToLoad()).setTimeToWait(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(page.selectedCustomerText))));
		custNbr = getObjectByXpath(page.selectedCustomerText).getText();
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
		if (!getCustomerNumberSelected().contains(customerNumber)) {
			String id, xPath, xPathForDept;
			clickable(page.customerDropdownIMG);
			// getObjectByXpath(pg.customerDropdownIMG).click();
			//logger.log(LogStatus.PASS, "User able to click Customer list dropdown");
			if (getObjectByXpath(page.customerDropdownSearch).isDisplayed()) {
				getObjectByXpath(page.customerDropdownSearch).click();
				getObjectByXpath(page.customerDropdownSearch).clear();
				getObjectByXpath(page.customerDropdownSearch).sendKeys(customerNumber);
				//logger.log(LogStatus.PASS, "User able to Enter customer number in the Search box of Customer dropdown");
				if (departmentNumber.equals("") || departmentNumber.isEmpty() == true || departmentNumber.equals("0")) {
					// getObjectByXpath(pg.customerDropdownSearch).sendKeys(customerNumber);
					// logger.log(LogStatus.PASS, "User able to Enter customer
					// number in the Search box of Customer dropdown");
					page.getObjectByCustomerNumber(customerNumber).click();
					sleep(1500);
					if (getCustomerNumberSelected().contains(customerNumber)){
						///logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber);
					}	else{
						//logger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber);
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
							if (getCustomerNumberSelected().contains(customerNumber))
							{
								/*//logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber
										+ "with " + "department: " + departmentNumber);*/
							}
							else{
								/*//logger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber
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
							if (getCustomerNumberSelected() == customerNumber)
							{
								//logger.log(LogStatus.PASS, "User is able to select customer: " + customerNumber
										//+ "with " + "department: " + departmentNumber);
							}
							else{
								///logger.log(LogStatus.FAIL, "User is not able to select customer: " + customerNumber
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

	/*
	 * public void validateIconsAndMsg(String previousDiv, String typeOfProduct)
	 * { switch (typeOfProduct) { case "SpecialOrder":
	 * iconCheck("Special-Order", previousDiv);
	 * validateIconHoverMessage(previousDiv, "Special-Order");
	 * common.validateObjectsDisplayed(PDPg.PDP_BlueBannerSOIconImg,
	 * "Blue banner icon image"); String msg =
	 * driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText(); String
	 * expectedMsg =
	 * "Call your customer service representative to order this product"; if
	 * (msg.equals(expectedMsg)) logger.log(LogStatus.PASS, "Message " +
	 * expectedMsg + " is displayed for Special order product"); else {
	 * logger.log(LogStatus.FAIL, "Message " + expectedMsg +
	 * " is not displayed for Special order product"); captureSS(
	 * "Special Order blue banner message not displayed", LogStatus.FAIL); }
	 * break; case "CES": iconCheck("CES-Item", previousDiv);
	 * validateIconHoverMessage(previousDiv, "CES-Item"); break; case
	 * "OrderGuide": iconCheck("Order-Guide", previousDiv);
	 * validateIconHoverMessage(previousDiv, "Order-Guide"); break; case
	 * "LocallySourced": iconCheck("Locally-Sourced", previousDiv);
	 * validateIconHoverMessage(previousDiv, "Locally-Sourced"); break; case
	 * "Scoop": iconCheck("scoop", "pt1:r1:0:r1:0:pt1:sic1:dc_pgl1");
	 * clickLinkByXPath(".//*[@id='pt1:r1:0:r1:0:pt1:sic1:dc_cil1::icon']",
	 * "Scoop icon is clicked");
	 * waitExplicitlyForClickable(".//*[@id='pt1:pt_of1']", 3); String title =
	 * driver.getTitle(); if (title.equals("Scoop | US Foods")) {
	 * logger.log(LogStatus.PASS, "Clicking Scoop icon navigates user to " +
	 * title + " as expected"); driver.navigate().back(); } else {
	 * logger.log(LogStatus.FAIL, "Clicking Scoop icon navigates user to " +
	 * title + " page"); captureSS("Scoop icon validation", LogStatus.FAIL); }
	 * break; case "JIT": iconCheck("Just-in-Time", previousDiv);
	 * validateIconHoverMessage(previousDiv, "Just-in-Time"); break; case "DWO":
	 * iconCheck("dwo", previousDiv); validateIconHoverMessage(previousDiv,
	 * "dwo"); common.validateObjectsDisplayed(PDPg.PDP_BlueBannerDWOIconImg,
	 * "Blue banner icon image"); String msg1 =
	 * driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText(); String
	 * expectedMsg1 =
	 * "This product will be discontinued when all inventory is depleted."; if
	 * (msg1.equals(expectedMsg1)) logger.log(LogStatus.PASS, "Message " +
	 * expectedMsg1 + " is displayed for DWO product"); else {
	 * logger.log(LogStatus.FAIL, "Message " + expectedMsg1 +
	 * " is not displayed for DWO product"); captureSS(
	 * "DWO blue banner message not displayed", LogStatus.FAIL); } break; case
	 * "Contracted": iconCheck("https://media.usfood.com/2/images/partner/",
	 * previousDiv); validateIconHoverMessage(previousDiv,
	 * "https://media.usfood.com/2/images/partner/"); break; case
	 * "Discontinued":
	 * common.validateObjectsDisplayed(PDPg.PDP_BlueBannerDiscIconImg,
	 * "Blue banner icon image"); String msg2 =
	 * driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText(); String
	 * expectedMsg2 =
	 * "This product has been discontinued. Please  find a replacement"; if
	 * (msg2.equals(expectedMsg2)) logger.log(LogStatus.PASS, "Message " +
	 * expectedMsg2 + " is displayed for Discontinued product"); else {
	 * logger.log(LogStatus.FAIL, "Message " + expectedMsg2 +
	 * " is not displayed for Discontinued product"); captureSS(
	 * "Discontinued product blue banner message not displayed",
	 * LogStatus.FAIL); }
	 * common.validateObjectsDisplayed(PDPg.PDP_BlueBannerDisclinkImg,
	 * "Blue banner link image"); clickLinkByXPath(PDPg.PDP_BlueBannerDisclink,
	 * "Blue banner find a replacement link is clicked");
	 * waitExplicitlyForClickable(".//*[@id='r1:0:pt1:r1:0:pt1:cl37']", 5);
	 * title = driver.getTitle(); if (title.equals("Shop Products | US Foods"))
	 * { logger.log(LogStatus.PASS,
	 * "Clicking find a replacement link navigates user to " + title +
	 * " as expected"); clickLinkByLinkText("Back to Product Detail");
	 * waitExplicitlyForClickable(".//*[@id='pt1:r1:0:r1:0:pt1:gl1']", 5); title
	 * = driver.getTitle(); if (title.equals("Product Detail | US Foods")) {
	 * logger.log(LogStatus.PASS,
	 * "Clicking Back to Product Details link navigates user to " + title +
	 * " as expected"); } else { logger.log(LogStatus.FAIL,
	 * "Clicking Back to Product Details link navigates user to " + title);
	 * captureSS("Discontinued product link validation", LogStatus.FAIL); } }
	 * else { logger.log(LogStatus.FAIL,
	 * "Clicking Back to Product Details link not navigates user to " + title);
	 * captureSS("Discontinued product link validation", LogStatus.FAIL); }
	 * break; case "Suggested":
	 * common.validateObjectsDisplayed(PDPg.PDP_BlueBannerSPIconImg,
	 * "Blue banner icon image"); msg =
	 * driver.findElement(By.xpath(PDPg.PDP_BlueBannerMsg)).getText();
	 * expectedMsg = "Save with Corporate Suggested.  View Suggested Product";
	 * if (msg.equals(expectedMsg)) logger.log(LogStatus.PASS, "Message " +
	 * expectedMsg + " is displayed for Suggested product"); else {
	 * logger.log(LogStatus.FAIL, "Message " + expectedMsg +
	 * " is not displayed for Suggested product"); captureSS(
	 * "Suggested blue banner message not displayed", LogStatus.FAIL); }
	 * common.validateObjectsDisplayed(PDPg.PDP_BlueBannerSPlinkImg,
	 * "Blue banner link image"); clickLinkByXPath(PDPg.PDP_BlueBannerSPlink,
	 * "Blue banner View Suggested Product link is clicked");
	 * waitExplicitlyForClickable(
	 * ".//*[@id='r1:0:pt1:j_id__ctru5pc2:0:pt1:cl4']", 5); title =
	 * driver.getTitle(); if (title.equals("Product Comparison | US Foods")) {
	 * logger.log(LogStatus.PASS,
	 * "Clicking View Suggested Product link navigates user to " + title +
	 * " as expected"); clickLinkByLinkText("Back to Product Details");
	 * waitExplicitlyForClickable(".//*[@id='pt1:r1:0:r1:0:pt1:gl1']", 5); title
	 * = driver.getTitle(); if (title.equals("Product Detail | US Foods")) {
	 * logger.log(LogStatus.PASS,
	 * "Clicking Back to Product Details link navigates user to " + title +
	 * " as expected"); } else { logger.log(LogStatus.FAIL,
	 * "Clicking Back to Product Details link navigates user to " + title);
	 * captureSS("Discontinued product link validation", LogStatus.FAIL); } }
	 * else { logger.log(LogStatus.FAIL,
	 * "Clicking Back to Product Details link not navigates user to " + title);
	 * captureSS("Discontinued product link validation", LogStatus.FAIL); }
	 * break; default: break; } }
	 */
	public void iconCheck(String icon, String previousDiv) {
		if (!(icon.equals("Contracted"))) {
			if (driver.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + icon + "')]"))
					.isDisplayed() == true) {
				//logger.log(LogStatus.PASS, icon + " icon is displayed");
			} else {
				//captureSS(icon + " icon validation", LogStatus.FAIL);
				//logger.log(LogStatus.FAIL, icon + " icon is not displayed");
			}
		} else {
			if (driver.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + icon + "')]"))
					.isDisplayed() == true) {
				//logger.log(LogStatus.PASS, "Contracted product icon is displayed");
			} else {
				//captureSS("Contracted icon validation", LogStatus.FAIL);
				//logger.log(LogStatus.FAIL, "Contracted product icon is not displayed");
			}
		}

	}
	
	
	public void validateFirstRow(int i, String s, int j, String s1) {

		WebElement htmltable = driver.findElement(By.xpath(".//*[@id='jdeGridData0_1']/tbody"));

		List<WebElement> rows = htmltable.findElements(By.tagName("tr"));

		List<WebElement> columns = rows.get(0).findElements(By.xpath(".//*[@id='G0_1_R0']/td"));
		// System.out.println("Number of columns:"+columns.size());

		common.EqualIgnoreCase1(columns.get(i).getText(), s);

		common.EqualIgnoreCase1(columns.get(j).getText(), s1);

	}
	
	
	
	
	
	

	public void EqualIgnoreCase1(String actual, String msg) {

		if (actual.equalsIgnoreCase(msg)) {
			//logger.log(LogStatus.PASS, msg + " Displayed ");
		} else {
			//logger.log(LogStatus.FAIL, msg + "not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);

			// logger.log(LogStatus.FAIL, Expected + "NoT Displayed ");
		}

	}

	public void ClickOnMenuOK() {
		try {
			if (driver.findElement(By.xpath("//*[@id='hc_OK']")).isDisplayed() == true) {
				driver.findElement(By.xpath("//*[@id='hc_OK']")).click();
				//logger.log(LogStatus.PASS, "LOL:: Win Field: Click on the Ok Option");
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			//logger.log(LogStatus.FAIL, " Ok button is not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot("OKButton");
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);
		}
	}

	//

	// compare
	public void Compare(String str1, String msg, String Compstr) {

		// String act_msg=driver.findElement(By.xpath(actual)).getText();
		System.out.println(str1);
		if (str1.equalsIgnoreCase(Compstr)) {
			//logger.log(LogStatus.PASS, msg + " is " + str1);
			System.out.println("Pass");
		} else {
			//logger.log(LogStatus.FAIL, msg + " not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);*/
			//logger.log(LogStatus.FAIL, image);

			// logger.log(LogStatus.FAIL, Expected + "NoT Displayed ");
		}

	}

	/*public void captureSS(String titleImg, LogStatus status) {
		//String Screenshotpath = ExtentReportNG.captureScreenshot(titleImg);
		//String image = logger.addScreenCapture(Screenshotpath);
		//logger.log(status, image);
	}*/
	
	
	
	   public void switchToNewWindow(int windowNumber) {
     	    java.util.Set<String> s = driver.getWindowHandles();   
     	  java.util.Iterator < String > ite = s.iterator();
     	    int i = 1;
     	    while (ite.hasNext() && i < 10) {
     	        String popupHandle = ite.next().toString();
     	        driver.switchTo().window(popupHandle);
     	        System.out.println("Window title is : "+driver.getTitle());
     	        if (i == s.size()) break;
     	        i++;
     	    }
     	} 
	   
	// add method
	public void add() throws TwfException {

		getObjectByXpath("//img[@name='hc_Add']").click();
		//logger.log(LogStatus.PASS, "LOL::Selected: Add sign clicked");
	}

	// close method:
	public void Close() throws TwfException {
		getObjectByXpath("//img[@name='hc_Close']").click();
		//logger.log(LogStatus.PASS, "LOL::Selected: Close sign clicked");

	}



	// Inputbyxpath Keys
	public static void inputbyxpath(String objects, String description, Keys testData) throws TwfException {
		try {
			int count = driver.findElements(By.xpath(objects)).size();
			if (count == 1) {
				driver.findElement(By.xpath(objects)).sendKeys(testData);
				;
				//logger.log(LogStatus.PASS, "LOL:: Win Field: " + description);
			} else {
				//logger.log(LogStatus.FAIL, " Add button is not displayed");
			}
		} catch (AssertionError e) {
			//logger.log(LogStatus.FAIL, " Add button is not displayed");
			//String Screenshotpath = ExtentReportNG.captureScreenshot("AddButton");
			//String image = logger.addScreenCapture(Screenshotpath);
			//logger.log(LogStatus.FAIL, image);
			addExceptionToReport("object is not found"+objects+"", "", "");
		}
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
					actionMoveJSClick(driver.findElement(By.xpath("//a[@id='r1:0:pt1:lv1:" + i + ":cl15']")));
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

	public void save_current_order(String total_quantity) throws TwfException {
		HashMap<String, String> current_order_details = getCurrentOrderDetails();
		if (current_order_details.get("Total Cases").equals("0")) {
			//logger.log(LogStatus.PASS, "Before Save Order: Verified Current order panel");

		} else if (current_order_details.get("Total Cases").equals(total_quantity)) {
			//logger.log(LogStatus.PASS, "After Save order: Verified Current order panel");
		} else {
			//captureSS("Save Order", LogStatus.FAIL);
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

		if (a.equals(b)) {
			//logger.log(LogStatus.PASS, "Both lists are Equal");
			return true;
		} else {
			//String Screenshotpath = ExtentReportNG.captureScreenshot("Lists are not equal");

			//String image = logger.addScreenCapture(Screenshotpath);
//
			//logger.log(LogStatus.FAIL, image);

			//logger.log(LogStatus.FAIL, "The two lists are not equal");

			return false;
		}

	}

	public void getProductsOnOrderModule() {

	}

	public HashMap<String, String> getCurrentOrderDetails() throws TwfException {

		WebElement element = this.getObjectByXpath(page.currentOrderModule);
		HashMap<String, String> currentOrderDtls = new HashMap<String, String>();
		if (element.isDisplayed()) {
			map.put("Total Cases",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl21']/tbody/tr/td[2]/span").getText());
			map.put("Total Eaches",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl46']/tbody/tr/td[2]/span").getText());
			map.put("Order Total",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl23']/tbody/tr/td[2]").getText());
			map.put("Delivery Date",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl26']/tbody/tr/td[2]/a").getText());
			map.put("PO#", common.getObjectByXpath("//a[@id='r1:0:pt1:r1:0:cl9']/span").getText());
			// map.put("save message",
			// common.getObjectByXpath("//span[@class='x275']").getText());
			///logger.log(LogStatus.PASS, "Current order details panel displayed and its details obtained");
		}

		else {
			///logger.log(LogStatus.FAIL, "Current order panel not displayed");
			//captureSS("Current order panel not displayed", LogStatus.FAIL);
		}

		return currentOrderDtls;

	}

	public String getCurrentOrderDetails(String key) throws TwfException {

		WebElement element = this.getObjectByXpath(page.currentOrderModule);
		HashMap<String, String> currentOrderDtls = new HashMap<String, String>();
		if (element.isDisplayed()) {
			currentOrderDtls.put("Total Cases",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl21']/tbody/tr/td[2]/span").getText());
			currentOrderDtls.put("Total Eaches",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl46']/tbody/tr/td[2]/span").getText());
			currentOrderDtls.put("Order Total",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl23']/tbody/tr/td[2]").getText());
			currentOrderDtls.put("Delivery Date",
					common.getObjectByXpath("//table[@id='r1:0:pt1:r1:0:pgl26']/tbody/tr/td[2]/a").getText());
			currentOrderDtls.put("PO#", common.getObjectByXpath("//a[@id='r1:0:pt1:r1:0:cl9']/span").getText());
			// map.put("save message",
			// common.getObjectByXpath("//span[@class='x275']").getText());
			//logger.log(LogStatus.PASS, "Current order details panel displayed and its details obtained");
		}

		else {
			//logger.log(LogStatus.FAIL, "Current order panel not displayed");
			//captureSS("Current order panel not displayed", LogStatus.FAIL);
		}

		return currentOrderDtls.get(key);

	}

	public ArrayList<String> getProductsInOrderModuleDtls() throws TwfException {
		String str = driver.findElement(By.xpath(".//*[@id='r1:0:pt1:r1:0:pgl32']/div[1]/span")).getText();
		int TotallineNumber = getIntegerFromString(str);
		ArrayList<String> productIDs = new ArrayList<>();
		for (int i = 0; i <= TotallineNumber - 1; i++) {
			String temp = common.getObjectByXpath("//div[@id='r1:0:pt1:r1:0:i3:" + i + ":pgl69']/div[2]/span")
					.getText();
			productIDs.add(temp.replaceAll("# ", ""));
		}
		//logger.log(LogStatus.PASS, "Products displayed in Products in Order module are " + productIDs);
		System.out.println("Captured Product ids products in order module");
		return productIDs;
	}

	public void clickButton(String buttonName) throws TwfException {
		element = common.getObjectByXpath("//button[text()='" + buttonName + "']");
		if (element.isDisplayed()) {
			element.click();
			//l/ogger.log(LogStatus.PASS, buttonName + "button is clicked");
		}

		else {
			//logger.log(LogStatus.FAIL, buttonName + "button is not displayed");
			//captureSS("Button not displayed", LogStatus.FAIL);
		}
	}

	public void verifyCurrentOrderDtlsCSCount(String count) throws TwfException {
		if (getCurrentOrderDetails("Total Cases").equals(count)){
			//logger.log(LogStatus.PASS, "Total CS quantity value is displayed as " + count);
		}
		else {
			//logger.log(LogStatus.FAIL, "Total CS quantity value is not displayed as " + count);
			//captureSS("CSQty value", LogStatus.FAIL);
		}
	}
	/*
	 * public void validateAndClickPDPBreadCrumb(String breadCrumb) { String
	 * brCrumbTxt = driver.findElement(By.xpath(PDPg.PDP_BreadCrumb)).getText();
	 * if (brCrumbTxt.equals(breadCrumb)) { logger.log(LogStatus.PASS,
	 * "Bread Crumb link is displayed with text: " + brCrumbTxt);
	 * driver.findElement(By.xpath(PDPg.PDP_BreadCrumb)).click();
	 * logger.log(LogStatus.PASS, "Bread Crumb " + brCrumbTxt +
	 * " link is clicked"); if
	 * (driver.findElement(By.xpath(".//*[@id='r1:0:pt1:pt_of1']")).getText().
	 * equals("Create Order")) { logger.log(LogStatus.PASS, "Clicking " +
	 * brCrumbTxt + " takes user to " + brCrumbTxt.substring(8,
	 * brCrumbTxt.length()) + " page"); } } else { logger.log(LogStatus.FAIL,
	 * "Bread Crumb link is not displayed with text: " + brCrumbTxt);
	 * captureSS("BreadCrumbNotDisplayed", LogStatus.FAIL); } }
	 */

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
		actions.moveToElement(driver
				.findElement(By.xpath(".//*[@id='" + previousDiv + "']//*[contains(@src, '" + typeOfProd + "')]")));
		actions.build().perform();
		checkIconMessage(msg);
	}

	public void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();
	}

	public void moveToXPath(String xPath) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(xPath)));
		actions.build().perform();
	}

	public void clickByXPath(String xPath, String msg) {
		driver.findElement(By.xpath(xPath)).click();
		//logger.log(LogStatus.INFO, msg + " is clicked");
	}

	public void clickByXPath(String xPath) {
		driver.findElement(By.xpath(xPath)).click();
	}

	public void clickById(String Id, String msg) {
		driver.findElement(By.id(Id)).click();
		//logger.log(LogStatus.INFO, msg + " is clicked");
	}

	public void checkIconMessage(String message) {
		if (driver.findElement(By.xpath(".//*[contains(text(),'" + message + ")]")).isDisplayed()) {
			//logger.log(LogStatus.PASS, "Icon message is displayed as: \"" + message + "\" as expected");
		} else {
			//logger.log(LogStatus.PASS, "Icon message is displayed as: \"" + message + "\" which is not as expected");
			//captureSS("Icon message validation", LogStatus.FAIL);
		}
	}

	/*
	 * public void navigateToPage(String pageName) {
	 * switch(pageName.toLowerCase()) { case "homepage":
	 * moveToElement(driver.findElement(By.xpath(page.topUSFLogoIcon)));
	 * clickByXPath(page.topUSFLogoIcon, "Home page icon");
	 * waitExplicitlyForPresence(page.welcomeBackMsg, 8);
	 * if(driver.findElement(By.xpath(page.welcomeBackMsg)).getText().contains(
	 * "Welcome back")) logger.log(LogStatus.PASS, "User landed on to HomePage"
	 * ); else { logger.log(LogStatus.FAIL, "User not landed on to HomePage");
	 * captureSS("Home page not landed", LogStatus.FAIL); } break; case
	 * "viewalllists": actionMoveJSClick(driver
	 * .findElement(By.xpath(page.listDropdownImg)));
	 * actionMoveJSClick(driver.findElement(By.xpath(
	 * "//a[text()='View All Lists']")));
	 * if(driver.findElement(By.id("r1:0:pt1:pt_of1")).getText().equals("Lists")
	 * ) logger.log(LogStatus.PASS, "User landed on to View All Lists page");
	 * else { logger.log(LogStatus.FAIL,
	 * "User not landed on to View All Lists Page"); captureSS(
	 * "View All Lists page not landed", LogStatus.FAIL); } case "shopproducts":
	 * clickByXPath(page.shopProductsLink, "Shop Products link");
	 * waitExplicitlyForPresence(".//*[@id='r1:0:pt1:r1:0:pt1:pt_of1']", 8);
	 * if(driver.findElement(By.xpath(".//*[@id='r1:0:pt1:r1:0:pt1:pt_of1']")).
	 * getText().equals("Shop Products")) logger.log(LogStatus.PASS,
	 * "User landed on to Shop Products page"); else {
	 * logger.log(LogStatus.FAIL, "User not landed on to Shop Products Page");
	 * captureSS("Shop Products page not landed", LogStatus.FAIL); } break; case
	 * "ourexclusives": clickByXPath(page.ourExclusivesLink,
	 * "Our Exclusives link"); waitExplicitlyForPresence(oe.OE_Title, 8);
	 * if(driver.findElement(By.xpath(oe.OE_Title)).getText().equals(
	 * "Our Exclusives")) logger.log(LogStatus.PASS,
	 * "User landed on to Our Exclusives page"); else {
	 * logger.log(LogStatus.FAIL, "User not landed on to Our Exclusives Page");
	 * captureSS("Our Exclusives page not landed", LogStatus.FAIL); } break;
	 * case "mybusiness": clickByXPath(page.myBusinessLink, "My Business link");
	 * waitExplicitlyForPresence(mb.MB_Title, 8);
	 * if(driver.findElement(By.xpath(mb.MB_Title)).getText().equals(
	 * "My Business")) logger.log(LogStatus.PASS,
	 * "User landed on to My Business page"); else { logger.log(LogStatus.FAIL,
	 * "User not landed on to My Business Page"); captureSS(
	 * "My Business page not landed", LogStatus.FAIL); } break; } }
	 */
	public void verifyTextExists(String xPath, String txtToCheck) {
		String txt = driver.findElement(By.xpath(xPath)).getText();
		if (txt.contains(txtToCheck)) {
			//logger.log(LogStatus.PASS, "\"" + txtToCheck + "\" is displayed");
			System.out.println(txtToCheck + " is verified");
		} else {
			//logger.log(LogStatus.FAIL, "\"" + txtToCheck + "\" is not displayed");
			System.out.println(txtToCheck + " is failed");
		}
	}

	public void verifyTextExists(String xPath, String txtToCheck, String msg) {
		String txt = driver.findElement(By.xpath(xPath)).getText();
		if (txt.contains(txtToCheck)) {
			//logger.log(LogStatus.PASS, "\"" + txtToCheck + "\" is displayed " + msg);
			System.out.println(txtToCheck + " is verified");
		} else {
			//logger.log(LogStatus.FAIL, "\"" + txtToCheck + "\" is not displayed " + msg);
			System.out.println(txtToCheck + " is failed");
		}
	}

	public void verifyBreadCrumb(String xPath, String breadCrumbTxts) {
		String txt = driver.findElement(By.xpath(xPath)).getText();
		String splitTxt[] = breadCrumbTxts.split(">");
		for (String i : splitTxt) {
			if (txt.contains(i)) {
				//logger.log(LogStatus.PASS, "\"" + i + "\" is displayed in bread crumb");
			} else {
				//logger.log(LogStatus.FAIL, "\"" + i + "\" is not displayed in bread crumb");
			}
		}
	}

	public void verifyLinkWithNameExists(String xPath, String linkToCheck) {
		String isDisabled = driver.findElement(By.xpath(xPath)).getAttribute("disabled");
		if (isDisabled == null || isDisabled.equals("disabled")) {
			//logger.log(LogStatus.PASS, "\"" + linkToCheck + "\" is enabled as link");
			System.out.println(linkToCheck + " is verified");
		} else {
			//logger.log(LogStatus.FAIL, "\"" + linkToCheck + "\" is not enabled as link");
			System.out.println(linkToCheck + " is failed");
		}
	}

	public void verifyLinksInDropdown(String xPathToHover, String xPath, String txtToCheck) {
		moveToElement(driver.findElement(By.xpath(xPathToHover)));
		String txt[] = txtToCheck.split(":");
		for (String str : txt) {
			sleep(250);
			verifyTextExists(xPath, str, "in global dropdown");
		}
	}

	public void jsScrollToElement(String xPath) {
		WebElement element = driver.findElement(By.xpath(xPath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		sleep(500);
	}

	public void noOfLinksForXPath(String xPath, int noOfLinksToBeVerified) {
		int noOfLinks = common.getSizeOfElementsSelected(xPath);
		if (noOfLinks == noOfLinksToBeVerified){
			//logger.log(LogStatus.PASS, "All the left side links are displayed");
		}
		else {
			//logger.log(LogStatus.FAIL, "Some links are not displayed in the left side");
			//common.captureSS("Left side links not displayed", LogStatus.FAIL);
		}
	}

	public String Select_getSelected_option(String xpath) {
		Select ss2 = new Select(driver.findElement(By.xpath(xpath)));
		WebElement option = ss2.getFirstSelectedOption();
		String opt_text = option.getText();
		return opt_text;
	}

	// Extract Text
	public static String ExtractText(String objects) {
		try {

			WebElement Exp_str = driver.findElement(By.xpath(objects));
			String txt = Exp_str.getText();
			return txt;

		} catch (Exception e) {
			System.out.println(e);
		}
		return objects;
	}

	// Verify the Section title
	public void EqualIgnoreCase(String actual, String msg) {

		String act_msg=actual.trim();
		//String act_msg = driver.findElement(By.xpath(actual)).getText().trim();
		System.out.println(act_msg);
		if (act_msg.equalsIgnoreCase(msg)) {
			//logger.log(LogStatus.PASS, msg + " Page is Displayed ");
			System.out.println("Pass");
		} else {
			//logger.log(LogStatus.FAIL, msg + " not displayed");
			/*String Screenshotpath = ExtentReportNG.captureScreenshot(msg);
			String image = logger.addScreenCapture(Screenshotpath);
			logger.log(LogStatus.FAIL, image);*/

			// logger.log(LogStatus.FAIL, Expected + "NoT Displayed ");
		}

	}

	// Extract Text
	public String ExtractAttributeValue(String objects) {
		try {

			WebElement Exp_str = driver.findElement(By.xpath(objects));
			String txt = Exp_str.getText();
			return txt;

		} catch (Exception e) {
			System.out.println(e);
		}
		return objects;
	}

	// validate the Pdf
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		System.out.println(" Count :" + dir_contents.length);
		System.out.println(dir_contents);

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equalsIgnoreCase("fileName"))
				System.out.println(dir_contents[i].getName());
			return flag = true;
		}

		return flag;
	}

	// * Get the latest file from a specific directory
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	// Validate PDF Text present or Not
	public void Validate_pdf_text(String text) throws FileNotFoundException, IOException {

		File getLatestFile = common.getLatestFilefromDir("C:\\Automation\\DownloadWinfield");
		String fileName = getLatestFile.getName();
		System.out.println("Lates file :" + fileName);
		Boolean download_file = common.isFileDownloaded("C:\\Automation\\DownloadWinfield", fileName);
		System.out.println(download_file);
		if (download_file == true) {
			/*logger.log(LogStatus.PASS, " PDF is downloaded sucessfully");
			logger.log(LogStatus.INFO, " Downloaded Pdf as " + fileName);*/
		} else {
			//logger.log(LogStatus.FAIL, " PDF is not downloaded ");
		}
		File file = new File("C:\\Automation\\DownloadWinfield\\" + fileName);
		System.out.println(file);
		common.sleep(1000);
		//logger.log(LogStatus.INFO, " Validate the downloaded PDF content");
		PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String parsedText = pdfStripper.getText(pdDoc);
		System.out.println(parsedText);
		if (parsedText
				.contains("The number of days between From and To dates cannot be greater than 'Max No of Days'")) {
			System.out.println("Genarated Pdf contains the expected fields");
			/*logger.log(LogStatus.PASS,
					" Pdf contains the Value:: The number of days between From and To dates cannot be greater than 'Max No of Days'");*/
		} else {
			System.out.println("The number of days between From and To dates cannot be greater than 'Max No of Days'");
			//logger.log(LogStatus.FAIL, " Downloaded Pdf doesnt contains the Expected Field value");
		}

	}

	public void validateLinkNavigation(String xPath, String pageToNavigate) {/*
		String str = pageToNavigate;
		WebElement element = driver.findElement(By.xpath(xPath));
		pageToNavigate = pageToNavigate + " | US Foods";
		moveToElement(element);
		jsScrollWindowUp();
		// jsScrollToElement(xPath);
		if (element.isDisplayed()) {
			// common.jsclick(driver.findElement(By.xpath(xPath)));
			// click able(xPath);
			// noOfLinksForXPath(oe.OE_LeftSideLinksAll, 5);
			element.click();
			sleep(1500);
			// if(pageToNavigate.equals("Our Exclusives | US Foods"))
			// sleep(1500);
			waitExplicitlyForPresence(".//span[text()='" + str + "']", 10);
			String title = driver.getTitle();
			if (title.contains(pageToNavigate)) {
				//logger.log(LogStatus.PASS, "User landed on to \"" + pageToNavigate + "\" page");
				System.out.println("Verified: " + pageToNavigate);
			} else {
				//logger.log(LogStatus.FAIL,
						"User not landed on to \"" + pageToNavigate + "\" page instead landed to " + title);
				System.out.println("FAILED: " + pageToNavigate);
			}
		}
	*/}
	
	//get title of the page.....
	public String getTitle(){
		
		return driver.getTitle();
		
		
		
		
	}
	
}