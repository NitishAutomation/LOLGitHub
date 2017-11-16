package com.lol.pages;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lol.util.TestExecutor;
import com.tavant.utils.TwfException;
import com.lol.util.Common;


public class Specification_Page extends TestExecutor {

		
		WebElement table =null;
		List<WebElement> rows = null;
		
		//inputMainTab Properties 
		
		//General info
		public String inputMainTab="//td[@id='link1']";
		public String pIMDiscription="ucAttributes_tabs_tabMAIN_txtPRODUCT_DESC_USF__SPECIFICATION";
		public String product_Identity ="ucAttributes_tabs_tabMAIN_txtPRODUCT_IDENTITY__SPECIFICATION";
		public String secondaryDescription="ucAttributes_tabs_tabMAIN_txtSEC_DESCRIPTOR__SPECIFICATION";
		public String marketingName= "ucAttributes_tabs_tabMAIN_txtMARKETING_NAME__SPECIFICATION";
		public String add_LONG_DESC="ucAttributes_tabs_tabMAIN_txtADD_LONG_DESC__SPECIFICATION";
		public String usf_SPC="ucAttributes_tabs_tabMAIN_txtSOURCE_USF_SPC__SPECIFICATION";
		public String aSYS="ucAttributes_tabs_tabMAIN_txtASYS__SPECIFICATION";
		public String pSYS="ucAttributes_tabs_tabMAIN_txtPSYS__SPECIFICATION";		
		public String material="ucAttributes_tabs_tabMAIN_txtMATERIAL__SPECIFICATION";		
		public String misc_Features="ucAttributes_tabs_tabMAIN_txtMISC_FEATURES__SPECIFICATION";
		public String prod_Number="ucAttributes_tabs_tabMAIN_txtMFG_PROD_NUMBER__SPECIFICATION";		
		public String uPC="ucAttributes_tabs_tabMAIN_txtUPC__SPECIFICATION";		
		public String sCC="ucAttributes_tabs_tabMAIN_txtSCC__SPECIFICATION";		
		public String launchVehicle= "ucAttributes_tabs_tabMAIN_txtLAUNCH_VEHICLE__SPECIFICATION";
		public String productBrad="//*[@id='ucAttributes_tabs_tabMAIN_ddlPRODUCT_BRAND__SPECIFICATIONContainer']/a/div/b";		
		public String productMerchandising = "//*[@id='ucAttributes_tabs_tabMAIN_ddlPROD_CAT_DESC__SPECIFICATIONContainer']/a/div/b";
		public String productMerchandisingselection="ucAttributes_tabs_tabMAIN_ddlPROD_CAT_DESC__SPECIFICATIONSpan";
		public String canDiscripton="ucAttributes_tabs_tabMAIN_txtDESCRIPTION__SPECIFICATION";
		public String vendor="ucAttributes_tabs_tabMAIN_txtSUPPLIER__SPECIFICATION";
		
		
		//Breakerinfo
		public String breaker_SOLD_EACH ="//*[@id='ucAttributes_tabs_tabMAIN_ddlBREAKER_SOLD_EACH__SPECIFICATIONContainer']/a/div/b";
		public String breakerASYS="ucAttributes_tabs_tabMAIN_txtBREAKER_ASYS__SPECIFICATION";
		
		//Country of Origin Information
		
		
		public String coolHarv="//*[@id='ucAttributes_tabs_tabMAIN_attCOOL_GROWN_HARV__SPECIFICATION_TBContainer']/button";
		public String processed="//*[@id='ucAttributes_tabs_tabMAIN_attCOOL_PROCESSED__SPECIFICATION_TBContainer']/button";
		public String cOO_STATEMENT ="ucAttributes_tabs_tabMAIN_txtCOO_STATEMENT__SPECIFICATION"; 
		
		//internal Packing Coding
		
		public String codingExampleInternal="ucAttributes_tabs_tabMAIN_txtCODING_EXAMPLE_INT__SPECIFICATION";
		
		public String applicationMethodInternal="//*[@id='ucAttributes_tabs_tabMAIN_ddlAPP_METHOD_INT__SPECIFICATIONContainer']/a/div/b";
		
		
		//External Packing Coding
		
		public String codingExampleExternal="ucAttributes_tabs_tabMAIN_txtCODING_EXAMPLE_EXT__SPECIFICATION";
		public String applicationMethodExternal=".//*[@id='ucAttributes_tabs_tabMAIN_ddlAPP_METHOD_EXT__SPECIFICATIONContainer']/a/div/b";
		//Parameters Tab Properties
		
		public String parametersTab="//td[@id='link2']";
		
		
		public String inputDropdownField="//*[@id='ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID_selFilterContainer']/a/div/b";
		
		//Out side Frame properties
		public String edit="//*[@id='ibnEdit']";
		public String SaveAs="//*[@id='ibnSaveAs']";
		public String open="//*[@id='ibnOpen']";
		public String greateNewObject="//*[@id='ibnCreateNew']";
		public String workFlow="//*[@id='ibnWorkflow']";
		public String genealogy="//*[@id='btnGenealogy']";

		
		//edit Food Spec
		public boolean editFoodSpec()throws TwfException{
			Common common= new Common();		
			common.jsclick(common.getObjectByXpath(this.edit));
			return false;
			}
		
		//select Parameter name
		public String parametersTab(String text) throws InterruptedException, TwfException{
			Common common= new Common();
			
			 WebElement Parameters=common.getObjectByXpath(this.parametersTab);		
			if(Parameters.isDisplayed())
			{
				Parameters.click();		
			}	
			common.getObjectByXpath(this.inputDropdownField).click();;
					
			Thread.sleep(2000);
			Actions action = new Actions(driver);
			WebElement clictheobject= common.getObjectByXpath("html/body/form/div[4]/div/div[4]/span/div/span/span[2]/div/div/ul/li[text()='"+text+"']");
			action.moveToElement(clictheobject).perform();
			action.moveToElement(clictheobject).click();
			clictheobject.click();
			Thread.sleep(2000);
			return text;
			
		}
            //inputMainTab 
		public void mainInputTab() throws InterruptedException, TwfException{
			Common common= new Common();
			
		if(common.getObjectByXpath(this.inputMainTab).isDisplayed()){		
			
			common.getObjectByXpath(this.inputMainTab).click();		
			}		
			
		}
		
		
		public boolean validateSpecificFieldWithRightData() throws InterruptedException,TwfException{
			
			Common common= new Common();
			
			//WebElement desenddingorder= common.getObjectByXpath("//*[@class='slick-sort-indicator slick-sort-indicator-desc']");
			/*WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			
			html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
			Thread.sleep(3000);	


	       html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	       Thread.sleep(3000);*/
	      
	      /* html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	       Thread.sleep(3000);*/
	       //html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	      // html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			WebElement clickOnParameterFilter= common.getObjectByXpath("html/body/form/div[4]/div/div[4]/span/div/div/div[2]/div/div[2]/div[5]/span[1]");
			clickOnParameterFilter.click();
							
			Thread.sleep(3000);
			String ACESULFAME_K=common.getObjectByXpath("//*[@id='ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID']/div[4]/div[3]/div/div[1]/div[5]/div[1]").getText().trim();
			if(ACESULFAME_K.contains("ACESULFAME_K")){
			WebElement target= common.getObjectByXpath("//*[@id='ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID']/div[4]/div[3]/div/div[1]/div[10]");
			Actions action = new Actions(driver);
				//WebElement clictheobject= common.getObjectByXpath("html/body/form/div[4]/div/div[4]/span/div/span/span[2]/div/div/ul/li[text()='"+text+"']");
				action.moveToElement(target).perform();
				Thread.sleep(3000);
				action.moveToElement(target).click();
				common.jsclick(target);
				
			
				JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
				myExecutor.executeScript("arguments[0].value='123';", target);
				//myExecutor.executeScript("document.getElementsByXpath('//*[@id='ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID']/div[4]/div[3]/div/div[1]/div[10]')[0].value='123'", target);
				
				
				
				
				//action.doubleClick(target).sendKeys("123");
				
				//target.sendKeys("123");
				
				/*try {
					Robot rb= new Robot();
					
					rb.keyPress(KeyEvent.VK_1);
					rb.keyRelease(KeyEvent.VK_1);
					rb.keyPress(KeyEvent.VK_2);
					rb.keyRelease(KeyEvent.VK_2);
					rb.keyPress(KeyEvent.VK_3);
					rb.keyRelease(KeyEvent.VK_3);
					rb.keyPress(KeyEvent.VK_3);
					rb.keyRelease(KeyEvent.VK_3);
					rb.keyPress(KeyEvent.VK_3);
					rb.keyRelease(KeyEvent.VK_3);
					rb.keyPress(KeyEvent.VK_3);
					rb.keyRelease(KeyEvent.VK_3);
					
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(3000);*/
				/* WebElement save= common.getObjectByXpath(".//*[@id='ibnSave']/span");
				 common.jsclick(save);
				 */
				
			
				//common.save();
				
				
				String nameadd="123456";
				
				
				WrapsDriver wrappedElement = (WrapsDriver) target;
				 
		        JavascriptExecutor driver = (JavascriptExecutor)wrappedElement.getWrappedDriver();
		        driver.executeScript("arguments[0].setAttribute('value', '" + 123 +"')", target);
		 
		        driver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", target, "inforDataGrid298487TARGET", nameadd);
		 
		        driver.executeScript("document.getElementById('ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID').setAttribute('value', '123')");
		        
		       
				//C:\Users\ProfileDisk\k256026\Downloads\
				
	             JavascriptExecutor jse = (JavascriptExecutor) driver;
				 
				 //jse.executeScript("arguments[0].setAttribute('value', '" + nameadd +"')", target);
				 
				 jse.executeScript("arguments[0].value='123456'","arguments[1]."+target+"");
				 
				 System.out.println("enterd successfully");
				Thread.sleep(3000);
				//target.sendKeys("12345");
				// WrapsDriver wrappedElement = (WrapsDriver) target;
				// JavascriptExecutor driver = (JavascriptExecutor)wrappedElement.getWrappedDriver();
				 
				 //driver.executeScript("arguments[0].value='123456'","arguments[1]."+target+"");
				 driver.executeScript("arguments[0].setAttribute('value', '" + nameadd +"')", target);
				// html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
				 Thread.sleep(3000);
				
				 driver.executeScript("arguments[0].value='"+nameadd+"'","arguments[1]"+target+"'");
				 
				 
				 
				 
				/*JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("document.getElementByXpath('//*[@id='ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID']/div[4]/div[3]/div/div[1]/div[8]').focus();");
				target.sendKeys("12345");
				
				common.jsclick(target);*/
				
				//jse.executeScript("document.getElementById('ucAttributes_tabs_tabPARAMETERS_SPECPARAM_GRID').value = '12345'");
				/* WrapsDriver wrappedElement = (WrapsDriver) target;
					JavascriptExecutor driver = (JavascriptExecutor)wrappedElement.getWrappedDriver();
					driver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", target,"", "12345");
				//target.clear();
				
				//target.sendKeys("12345");
				String texteddata=target.getText().trim();
				if (texteddata.equals("12345"));*/
				
			}
			
			return true;
			
		}
		public boolean validateSpecificFieldwithRightData(String textData) throws InterruptedException,TwfException{
		
			Common common= new Common();
			WebElement USF_SPC = common.getObjectById("ucAttributes_tabs_tabMAIN_txtSOURCE_USF_SPC__SPECIFICATION");
			
			common.jsclick(USF_SPC);
			USF_SPC.sendKeys(textData);
			
			Thread.sleep(2000);
			common.save();
			/* Thread.sleep(3000);
			 WebElement save= common.getObjectByXpath(".//*[@id='ibnSave']/span");
			 common.jsclick(save);  
			*/
			return true;
		}
		public boolean validateSpecificFieldwithWrongData(String textData) throws InterruptedException, TwfException{
			
			Thread.sleep(2000);
			this.editFoodSpec();

			Thread.sleep(2000);
			Common common= new Common();
			WebElement USF_SPC = common.getObjectById("ucAttributes_tabs_tabMAIN_txtSOURCE_USF_SPC__SPECIFICATION");
			
			//common.jsclick(USF_SPC);
			USF_SPC.clear();	
			Thread.sleep(2000);
			USF_SPC.sendKeys(textData);	
			Thread.sleep(2000);
			/*WebElement save= common.getObjectByXpath(".//*[@id='ibnSave']/span");
			common.jsclick(save); 
			*/
			
			common.save();
			try {
		        WebDriverWait wait = new WebDriverWait(driver, 2);
		        wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = driver.switchTo().alert();
		        alert.accept();
		    } catch (Exception e) {
		        //exception handling
		    }
			//String alertText = simpleAlert.getText();
		/*	Alert simpleAlert = driver.switchTo().alert();
			String alertText = simpleAlert.getText();
			System.out.println("Alert text is " + alertText);*/
		
			
		    /* Thread.sleep(3000);
		     USF_SPC.clear();	     
		     WebElement save1= common.getObjectByXpath(".//*[@id='ibnSave']/span");
			 common.jsclick(save1);    
			*/
			
			return true;
		}
		
		
		

}



		
