package com.jv.key
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.jv.constants.C_Object
import com.jv.constants.L_Object
import com.jv.constants.S_Object
import com.jv.constants.K_Object
import com.jv.constants.SK_Object
import com.jv.util.DataReadin
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import groovy.transform.CompileStatic
import internal.GlobalVariable
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.constants.CoreWebuiMessageConstants
import com.kms.katalon.core.webui.constants.StringConstants
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

class JvUtil {

	/**
	 * click方法
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	def static boolean elementCheck(TestObject to, int timeout, String clickType) {

		String ResultMessage = "";
		try {
			switch (clickType) {
				case C_Object.C_可点击:
					if (WebUiBuiltInKeywords.waitForElementClickable(to, timeout)) {
						ResultMessage = ResultMessage + "　Clickable　"
					}
					break
				case C_Object.C_可见:
					if (WebUiBuiltInKeywords.waitForElementVisible(to, timeout)) {
						ResultMessage = ResultMessage + "　Visibled　"
					}
					break
				case C_Object.C_可见可点击:
					if (WebUiBuiltInKeywords.waitForElementVisible(to, timeout)) {
						ResultMessage = ResultMessage + "　Visibled　"
					}
					if (WebUiBuiltInKeywords.waitForElementClickable(to, timeout)) {
						ResultMessage = ResultMessage + "　Clickable　"
					}
					break
				default:
					break
			}
			return true
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop("JV Error 元素確認失敗")
			return false
		}finally {
			KeywordUtil.logInfo('元素確認の結果　' + ResultMessage)
		}
	}

	/**
	 * click方法(default timeout 10)
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	def static elementCheck(TestObject to) {
		elementCheck(to, 10, "")
	}

	/**
	 * Check元素の存在
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean isElementPresent(TestObject to, int timeout){

		List<WebElement> elements = null;
		elements = WebUiBuiltInKeywords.findWebElements(to, timeout)
		if(elements.size() > 0) {
			KeywordUtil.markPassed("元素が見つかれました。[" + to.getObjectId() + "]")
		}
		//KeywordUtil.logInfo("WebElementの数量 " + elements.size() + "個")
		return elements.size() > 0
	}


	/**
	 * Click元素
	 * @param TestObjectID
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static click(String TestObjectID){
		TestObject to = findTestObject(TestObjectID)
		WebUiBuiltInKeywords.click(to);
	}



	/**
	 * Click元素
	 * @param TestObjectID
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean clickElement(String TestObjectID){
		boolean clickFlag = true
		TestObject to = findTestObject(TestObjectID)
		try{
			if(isElementPresent(to, 10)) {
				WebUiBuiltInKeywords.click(to)
				KeywordUtil.logInfo("元素がクリックされました。")
				clickFlag = false
			}
		}catch(StepFailedException se) {
			String errorMessage = se.getMessage();
			if (errorMessage.contains("Unable to click on object")) {
				println "JVError --------------------------------------------"
				String[] objectId = errorMessage.split("'");
				println "JVError 元素のobjectId" + objectId.toArrayString()
				KeywordUtil.logInfo("JVError 元素のobjectId " + objectId[1])

				try{
					WebUiBuiltInKeywords.enhancedClick(to)
				}catch(Exception s) {
					try{
						WebElement element = WebUiBuiltInKeywords.findWebElement(to, 2)
						element.click();
					}catch(Exception se1) {
						KeywordUtil.markFailed("Unable to click on object")
					}
				}
			}
		}catch(Exception e) {
			println "JVError ----------------Fail to click on element"
			while(clickFlag) {
				if(isElementPresent(to, 1)) {
					WebUiBuiltInKeywords.enhancedClick(to)
					clickFlag = false
				}
			}
			//KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Click元素
	 * @param TestObjectID
	 * @param ClickType 可点击、可见、可见可点击
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean clickElement(String TestObjectID, String ClickType){

		switch (ClickType) {
			//To　－＞　臨場の場合'
			case C_Object.C_可点击:
				clickClickAbleElement(TestObjectID);
				break
			case C_Object.C_可见:
				clickVisibleElement(TestObjectID);
				break
			case C_Object.C_可见可点击:
				clickClickAbleAndVisibleElement(TestObjectID);
				break
			default:
				break
		}
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean clickClickAbleElement(String TestObjectID){
		TestObject to = findTestObject(TestObjectID)
		try{
			if(elementCheck(to, 30, C_Object.C_可点击)) {
				WebUiBuiltInKeywords.click(to)
				KeywordUtil.logInfo("元素がクリックされました。")
			}
		}catch(StepFailedException se) {
			String errorMessage = se.getMessage();
			if (errorMessage.contains("Unable to click on object")) {
				String[] objectId = errorMessage.split("'");
				KeywordUtil.logInfo("JVError 元素のobjectId " + objectId[1])
				WebUiBuiltInKeywords.enhancedClick(to)
			}
			//KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean clickVisibleElement(String TestObjectID){
		TestObject to = findTestObject(TestObjectID)
		try{
			if(elementCheck(to, 30, C_Object.C_可见)) {
				WebUiBuiltInKeywords.click(to)
				KeywordUtil.logInfo("元素がクリックされました。")
			}
		}catch(StepFailedException se) {
			String errorMessage = se.getMessage();
			if (errorMessage.contains("Unable to wait for object to be Visible")) {
				String[] objectId = errorMessage.split("'");
				KeywordUtil.logInfo("JVError 元素のobjectId " + objectId[1])
				WebUiBuiltInKeywords.enhancedClick(to)
			}
			//KeywordUtil.markFailed("Fail to Visible on element")
		}
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static boolean clickClickAbleAndVisibleElement(String TestObjectID){
		TestObject to = findTestObject(TestObjectID)
		try{
			if(elementCheck(to, 30, C_Object.C_可见可点击)) {
				WebUiBuiltInKeywords.click(to)
				KeywordUtil.logInfo("元素がクリックされました。")
			}
		}catch(StepFailedException se) {
			String errorMessage = se.getMessage();
			String[] objectId = errorMessage.split("'");
			KeywordUtil.logInfo("JVError 元素のobjectId " + objectId[1])
			WebUiBuiltInKeywords.enhancedClick(to)
			//KeywordUtil.markFailed("Fail to click and Visible on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static getJvHtmlTableRows(TestObject table, String meetName, String status) {

		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./tr"))
		for(WebElement we : selectedRows) {
			Map map = we.getProperties();
			String text = map.get("text")
			if (text.indexOf(meetName) >= 0) {
				we.click();
			}
		}
	}

	/**
	 * setGlobalVariable
	 * @param Variable name
	 * @param Variable value
	 * @return なし
	 */
	def static setGlobalVariable(String name, def value) {
		GroovyShell shell1 = new GroovyShell()
		MetaClass mc = shell1.evaluate("internal.GlobalVariable").metaClass
		String getterName = "get" + name.capitalize()
		mc.'static'."$getterName" = { -> return value }
		mc.'static'."$name" = value
	}



	@Keyword
	def String getMailUrl(TestObject to, int timeout){
		List<WebElement> elements = WebUiBuiltInKeywords.findWebElements(to, timeout)
		String text = "";
		String sub1 = "";
		String mUrl = "";
		for (WebElement el : elements) {
			text = el.getAttribute("value")
			println text

			int a = text.indexOf("<a href")
			println "<a index" + text.indexOf("<a href")

			int b = text.indexOf("異議なし", text.indexOf("<a href"))
			println "異議なし index" + text.indexOf("異議なし", text.indexOf("<a href"))

			sub1 = text.substring(a,b)
			println sub1
			int c = sub1.indexOf("https");
			println "https index" + sub1.indexOf("https");
			int d = sub1.indexOf("style=");
			println "style=" + sub1.indexOf("style=");
			mUrl = sub1.substring(c, d-2)
			println "mUrl <" + mUrl
		}

		return mUrl
	}


	/**
	 * Get all cells of HTML table row
	 * @param row a WebElement instance represent for HTML table row
	 * @param tagName HTML column tag name, usually is TD/TH
	 * @return All cells inside HTML table row
	 */
	@Keyword
	def List<WebElement> getHtmlTableColumns(WebElement row, String tagName) {
		List<WebElement> selectedColumns = row.findElements(By.tagName(tagName))
		return selectedColumns
	}

	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get SysDate
	 * @param null
	 * @return SysDate(yyyyMMddHHmmss)
	 */
	@Keyword
	def static String getSystemDate() {

		String ymd = "";
		try {
			Date date = new Date();
			String time = date.getTimeString()
			String day = date.getDateString()
			println "システム時間　" + time
			println "システム日付　" + day
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			ymd = dataFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace()
		}
		return ymd;
	}

	/**
	 * Get clickUsingJS
	 * @param TestObject
	 * @return null
	 */
	@Keyword
	def clickUsingJS(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to, 30)
			WebDriver webDriver = DriverFactory.getWebDriver()
			JavascriptExecutor executor = (WebDriver)JavascriptExecutor
			executor.executeScript('arguments[0].click()', element)
		} catch (Exception e) {
			e.printStackTrace()
		}
	}


	/**
	 * getSData
	 * @param なし
	 * @return Map<String, String>
	 */
	@Keyword
	def static Map<String, String> getSData() {
		Map<String, String> sData = new HashMap<String, String>();
		S_Object so = new S_Object();
		Class clazz = so.getClass();
		Field[]  fields = clazz.declaredFields;
		for (Field f : fields) {
			sData.put(f.name, f.get(so))
		}
		return sData;
	}
}