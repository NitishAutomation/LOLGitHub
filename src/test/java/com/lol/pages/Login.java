package com.lol.pages;

import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tavant.utils.TwfException;
import com.lol.util.Common;
import com.lol.util.TestExecutor;

public class Login extends TestExecutor {

//	WebElement table = null;
//	List<WebElement> divs = null;
//	List<WebElement> rows = null;
	ExtentTest logger;
/*
	public String name = "//input[@id='it9::content']";
	public String pwd = ".//*[@id='it1::content'][@class='x25']";
	public String signin = ".//*[@id='cb1']";*/

	public String name = ".//*[@id='User']";
	public String pwd = ".//*[@id='Password']";
	public String signin = "//input[@class='buttonstylenormal margin-top5']";
	// public String bookmark =".//*[@id='gil1::icon']";
	public String learnmorelogo = ".//*[@id='pt_gil7::icon']";
	public String learnmore = ".//*[@id='pt_gil7']/span";
	public String usfoodlogo = ".//*[@id='pt_cil10']";
	public String useridtext = ".//*[@id='plam13']/tbody/tr[1]/td/label";
	public String useridtextbox = ".//*[@id='it9::content']";
	public String passwordtext = ".//*[@id='plam1']/tbody/tr[1]/td/label";
	public String passwordtextbox = ".//*[@id='it1::content']";
	public String logbutton = ".//*[@id='cb1']";
	public String Forgotuserid = ".//*[@id='cl2']";
	public String Forgotpassword = ".//*[@id='cl1']";
	public String TermsofAccess = ".//*[@id='gl2']";
	public String PrivacyPolicy = ".//*[@id='gl3']";
	public String CustomerPolicy = ".//*[@id='gl4']";
	public String AboutThisSite = ".//*[@id='gl5']";
	public String ContactUS = ".//*[@id='gl6']";
	public String USFoodscom = ".//*[@id='gl1']";
	public String facebookicon = ".//*[@id='pt_gil1::icon']";
	public String Twitericon = ".//*[@id='pt_gil2::icon']";
	public String youtubeicon = ".//*[@id='pt_gil3::icon']";
	public String AllRightsReserved = ".//*[@id='panelGroupLayout5']/tbody/tr/td[1]/span";
	public String v2016R36 = ".//*[@id='panelGroupLayout5']/tbody/tr/td[3]/span";
	public String GenericTermsofUse = "html/body/div/div/div[1]/div";

	public boolean asuser(String uname, String pwd) throws TwfException{
		return doLoginAS(uname, pwd);
	}

	private boolean doLoginAS(String username, String password) throws TwfException{

		// Login to Ecomsit2 with user name and password and click on Login
		// button
		try {
			Common common = new Common();

			common.getObjectByXpath(name).sendKeys(username);
			common.getObjectByXpath(pwd).sendKeys(password);
			common.getObjectByXpath(signin).click();
			return true;
		} catch (NullPointerException e) {
			System.out.println("hello");
			//logger.log(LogStatus.FAIL, "ERROR: Unable To login");
			System.out.print("NullPointerException caught");
		}
		return false;

	}

	// validating all the elements in Ecomsit2 Loginpage

	public void verifyAllElementsInLoginpage(ExtentTest logger) throws TwfException{
		//logger.log(LogStatus.INFO, "=======Start testngreportsecond========");

		/*
		 * Assert.assertTrue(common.getObjectByXpath(bookmark).isDisplayed(),
		 * "bookmark is not there "); logger.log(LogStatus.PASS,
		 * "bookmark is Present in loginpage ");
		 */
		Assert.assertTrue(common.getObjectByXpath(learnmorelogo).isDisplayed(), "Learnmorelogo is not there ");
		logger.log(LogStatus.PASS, "learnmorelogo is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(learnmore).isDisplayed(), "Learnmore is not there ");
		logger.log(LogStatus.PASS, "learnmore is Present  in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(usfoodlogo).isDisplayed(), "usfoodlogo is not there ");
		logger.log(LogStatus.PASS, "usfoodlogo is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(useridtext).isDisplayed(), "useridtext is not there ");
		logger.log(LogStatus.PASS, "useridtext is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(useridtextbox).isDisplayed(), "useridtextbox is not there ");
		logger.log(LogStatus.PASS, "useridtextbox is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(passwordtext).isDisplayed(), "passwordtext is not there ");
		logger.log(LogStatus.PASS, "passwordtext is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(passwordtextbox).isDisplayed(), "passwordtextbox is not there ");
		logger.log(LogStatus.PASS, "passwordtextbox is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(logbutton).isDisplayed(), "loginbutton is not there ");
		logger.log(LogStatus.PASS, "logbutton is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(Forgotuserid).isDisplayed(), "Forgotuserid is not there ");
		logger.log(LogStatus.PASS, "Forgotuserid is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(Forgotpassword).isDisplayed(), "Forgotpassword is not there ");
		logger.log(LogStatus.PASS, "Forgotpassword is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(TermsofAccess).isDisplayed(), "TermsofAccess is not there ");
		logger.log(LogStatus.PASS, "TermsofAccess is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(PrivacyPolicy).isDisplayed(), "PrivacyPolicy is not there ");
		logger.log(LogStatus.PASS, "PrivacyPolicy is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(CustomerPolicy).isDisplayed(), "CustomerPolicy is not there ");
		logger.log(LogStatus.PASS, "CustomerPolicy is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(AboutThisSite).isDisplayed(), "AboutThisSite is not there ");
		logger.log(LogStatus.PASS, "AboutThisSite is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(ContactUS).isDisplayed(), "ContactUS is not there ");
		logger.log(LogStatus.PASS, "ContactUS is Present in loginpage ");
		Assert.assertTrue(common.getObjectByXpath(USFoodscom).isDisplayed(), "USFoodscom is not there ");
		logger.log(LogStatus.PASS, "USFoodscom is Present in loginpage ");

		Assert.assertTrue(common.getObjectByXpath(facebookicon).isDisplayed(), " facebookicon is not there ");
		logger.log(LogStatus.PASS, "facebookicon is Present in loginpage ");

		Assert.assertTrue(common.getObjectByXpath(Twitericon).isDisplayed(), " Twitericon is not there ");
		logger.log(LogStatus.PASS, "Twitericon is Present in loginpage ");

		Assert.assertTrue(common.getObjectByXpath(youtubeicon).isDisplayed(), " youtubeicon is not there ");

		logger.log(LogStatus.PASS, "youtubeicon is Present in loginpage ");

		Assert.assertTrue(common.getObjectByXpath(AllRightsReserved).isDisplayed(), " AllRightsReserved is not there ");
		logger.log(LogStatus.PASS, "AllRightsReserved is Present in loginpage ");

		Assert.assertTrue(common.getObjectByXpath(v2016R36).isDisplayed(), " v2016R36 is not there ");
		logger.log(LogStatus.PASS, "v2016R36 is Present in loginpage ");

		logger.log(LogStatus.INFO, "=======End testngreportsecond======");
	}

	public void TermsofAccesslink(ExtentTest logger) throws TwfException{
		common.getObjectByXpath(TermsofAccess).click();
		/*
		 * String url = driver.getCurrentUrl(); System.out.println(url); String
		 * t= driver.getTitle(); System.out.println(t);
		 */
		String handle = driver.getWindowHandle();

		System.out.println(handle);

		// Click on the Button "New Message Window"

		// driver.findElement(By.name("New Message Window")).click();

		// Store and Print the name of all the windows open

		/*Set<String> AllWindowHandles = driver.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		System.out.print("window1 handle code = " + AllWindowHandles.toArray()[0]);
		String window2 = (String) AllWindowHandles.toArray()[1];
		System.out.print("\nwindow2 handle code = " + AllWindowHandles.toArray()[1]);
		driver.switchTo().window(window2);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		String t = driver.getTitle();
		System.out.println(t);
		Assert.assertTrue(
				url.equalsIgnoreCase("https://www.usfoods.com/content/dam/usf/EC/documents/termsofaccess.html"));
		logger.log(LogStatus.PASS, "verified the GetTitle  is......" + url);
		Assert.assertTrue(common.getObjectByXpath(GenericTermsofUse).isDisplayed(), " GenericTermsofUse is not there ");
		logger.log(LogStatus.PASS, "GenericTermsofUse is Present in loginpage ");*/

	}
}