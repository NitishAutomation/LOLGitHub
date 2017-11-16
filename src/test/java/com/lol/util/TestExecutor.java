 package com.lol.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.utils.TwfException;

import com.lol.pages.*;

public class TestExecutor  extends WebPage{

	// public static Properties CONFIG=null;
	// public static Properties OR=null;

	// public static WebDriver dr = null;
	// public static EventFiringWebDriver driver = null;
//	public String userDir = System.getProperty("user.dir");
	
	public static WebDriver driver;
	public static String userDir = System.getProperty("user.dir");
	public static DesiredCapabilities cap = new DesiredCapabilities();
	//public static Navigate Navigate ;--->Nitish
	public static Page page = new Page();
	public static Common common = new Common() ;
	public static Common1 common1;
	public static Login login = new Login();
	public static HomePage hmPg = new HomePage();
	public static HomePage1 hmPg1 = new HomePage1();
	public static LOL_OrderConfirmation_page qoepage = new LOL_OrderConfirmation_page();
	public static ReviewOrderPage review_order = new ReviewOrderPage();
	public static LOL_Order_Header_Revisions_page orderConfirmationPage = new LOL_Order_Header_Revisions_page();
	//public static ExtentTest logger;
	public static DataUtils dataUtils ;
	public static Properties prop = new Properties();
	public static JavascriptExecutor  jscript = (JavascriptExecutor) driver;;
	public static LOL_OrderManagement_Creation_RSO create_order = new LOL_OrderManagement_Creation_RSO();
	public static LOL_Manage_Existing_Order_page manage_order = new LOL_Manage_Existing_Order_page();
	public static LOL_Enter_New_Order_page new_order = new LOL_Enter_New_Order_page();
	public static LOL_OrderConfirmation_page oc_page=new LOL_OrderConfirmation_page();
	public static Media_object_viewer_Page mediaobject=new Media_object_viewer_Page();
	public static CO_Massload_nonstockdebit_importpage massloadimportpage= new CO_Massload_nonstockdebit_importpage();
	public static CO_ViewJob_Status viewjobstatpage=new CO_ViewJob_Status();
	public static CO_Massload_nonstock_workwithcreditndrebill massloadhomepage= new CO_Massload_nonstock_workwithcreditndrebill();
	public static SalesandCreditorderinquiry_manageordershome_page salesandcreditinquiryhmpg=new SalesandCreditorderinquiry_manageordershome_page();
	public static OrderPrepay_contractreports_VersionPromoting versionpromoting=new OrderPrepay_contractreports_VersionPromoting();
	public static OrderPrepay_contractreports_DataSelection dataselectionpage= new OrderPrepay_contractreports_DataSelection();
	public static OrderPrepay_contractreports_ProcessingOption processingoptions=new OrderPrepay_contractreports_ProcessingOption();
	public static Action_SubmitJob actionsubmitjobs=new Action_SubmitJob();
	
	public static AP_NonProductVoucher apnonvouchinquiry=new AP_NonProductVoucher();
	public static AP_NonProductVoucher_Enternew_Page apnonvouchenter=new AP_NonProductVoucher_Enternew_Page();
	
	
	
	public static ShoppingLists sl = new ShoppingLists();
	
//	public WebDriverWait wait = new WebDriverWait(driver, 10);
	public DataFormatter formatter = new DataFormatter();
	public FileInputStream File;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	
	//static ExtentReports report;---->Nitish
	FileInputStream fis = null;

	
	/*public static ExtentReports launch() {-->Nitish Nayak
		String destFile = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
		destFile = userDir + "\\test-output\\extentreports\\ecom";
		String destDir = dateFormat.format(new Date()) + ".html";
		report = new ExtentReports(destFile + "\\" + destDir, true);
		report.loadConfig(new File(userDir + "\\src\\test\\java\\com\\lol\\config\\reports.xml"));
		return report;
	}*/
	
	public Map<String, String> map = new HashMap<String, String>();
	public void Start() throws TwfException, IOException {
		
		driver=DriverFactory.getDriver();
		driver.manage().window().maximize();
		//driver=new Augmenter().augment(driver);
		common = new Common();
		dataUtils = new DataUtils();
		
	}
	
	
public void startSingle(String k) throws TwfException, IOException {
		
		driver=DriverFactory.getDriver();
		driver=new Augmenter().augment(driver);
		

}

	/*
	public void startTest(Method method) throws IOException {---->Nitish
		//initialize();
		//driver.navigate().to("http://winfielduspy.ent.lolcentral.com/jde/E1Menu.maf");
		
		initialize();
		driver.get(prop.getProperty("URL"));
		System.out.println("Successfully Opened ECOM R3 application ");
		driver.manage().window().maximize();
		// pass the sheet name corresponding to the test case
	//	dataUtils.setSheetName("TD_ProductDetailsValidation");
//		System.out.println("inside start test");
//		logger = report.startTest("");
		
	}*/
	
	
	/*public void reportclosewindows() throws IOException, InterruptedException {---->Nitish
		report.endTest(logger);
		report.flush();
		
	}
	*/
	
	/*public void result() {---->Nitish
		driver.close();
	}


	public static void loadPropertiesFile() throws IOException---->Nitish
	{
		FileInputStream fn = null;
		fn = new FileInputStream(userDir
				+ "\\src\\test\\java\\com\\lol\\config\\config.properties");		
		prop.load(fn);		
	}
	*/
	
	/*public static void initialize() throws IOException {---->Nitish
		

		if (driver == null) {

			// Initialize CONFIG property file
			loadPropertiesFile();		
			
			if (prop.getProperty("browser", "Firefox").equals("Firefox")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				File pathBinary = new File(
						"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//				"C:\Program Files (x86)\Mozilla Firefox\firefox.exe"
				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				driver = new FirefoxDriver(firefoxBinary, firefoxProfile, cap);
				// driver= new FirefoxDriver(cap);
				System.out.println("launched firefox");
				cap.setPlatform(Platform.ANY);
			} else if (prop.getProperty("browser", "Firefox").equals("iexplore")) {
				System.out.println(prop.getProperty("browser"));
				System.setProperty("webdriver.ie.driver",
						"C:\\Users\\ProfileDisk\\k256026\\Downloads\\IEDriverServer.exe");
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(cap);
				cap.setPlatform(Platform.ANY);

			} else if (prop.getProperty("browser", "Firefox").equals("chrome")) {
				System.out.println(prop.getProperty("browser"));
				System.setProperty("webdriver.chrome.driver", "U:\\permanent\\Information_System\\SQA\\Selenium\\AutomationScripts\\JDE - Winfield\\Abinaya-Scripts\\sabarish folder JDE winfeild file\\LOL_Winfield_Automation\\Exe driver\\chromedriver.exe");
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				driver = new ChromeDriver(cap);
				cap.setPlatform(Platform.ANY);
			}
			// driver = new RemoteWebDriver(
			// new URL(System.getProperty("hub")), cap);
		}
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		//Navigate = new Navigate(driver);---->Nitish
		common = new Common();
		dataUtils = new DataUtils();
		
		// webTable = new Table(driver);---->Nitish
		jscript = (JavascriptExecutor) driver;
	}*/
	
	public String getData(String columnName)
	{
		return dataUtils.getMapValue(columnName);
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
}
