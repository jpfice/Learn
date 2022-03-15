package com.jv.key
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
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

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class JvDate {
	/**
	 * Refresh browser
	 */
	@Keyword
	def WebDriverCheck() {
		KeywordUtil.logInfo("WebDriverCheck")
		try {
			WebDriver webDriver = DriverFactory.getWebDriver()
		} catch (Exception e) {
			e.printStackTrace()
			KeywordUtil.markFailed("Browser is not opened")
			return false
		}
		return true
	}

	
	
//	/**
//	 * Function C_Object.C_提案日
//	 * @param  ymdType
//	 * @return 無し
//	 */
//	def static ymdSet(String ymdType) {
//		
//		Date date = new Date();
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//
//		switch (ymdType) {
//			case "01":
//			
//				
//				break
//			case "02":
//				'開催日時To_時の設定'
//				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時To_時)
//				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_to/Hort_13')
//
//				'開催日時To_分の設定'
//				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時To_分)
//				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_to/min_05')
//				break
//			case "03":
//			
//				c.add(Calendar.DATE, 2);
//				WebUiBuiltInKeywords.comment('開催日時From>> 月 ' + c.get(Calendar.WEEK_OF_MONTH))
//				WebUiBuiltInKeywords.comment('開催日時From>> 日 ' + c.get(Calendar.DATE))
//				WebUiBuiltInKeywords.comment('開催日時From>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
//				WebUiBuiltInKeywords.comment('開催日時From>> 分 ' + c.get(Calendar.MINUTE))
//			
//				String xpath = '//div[@class="pika-single is-bound"]/div[@class="pika-lendar"]//td[@data-day="'+ c.get(Calendar.DATE) +'"]'
//				TestObject to1 = new TestObject('objectname')
//				to1.addProperty('xpath', ConditionType.EQUALS, xpath)
//			
//				'提案日の設定'
//				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_提案日のカレンダーをクリック)
//				TestObject to = findTestObject(to1)
//				WebUiBuiltInKeywords.click(to)
//				//JvUtil.clickClickAbleAndVisibleElement("S_Object.S_日付_" + c.get(Calendar.DATE) )
//				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_OKボタン)
//				break
//			case C_Object.C_決議日:
//				'決議日の設定'
//				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_決議日のカレンダーをクリック)
//				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_日付_20)
//				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_OKボタン)
//				break
//			case C_Object.C_議題登録期限:
//				'議題登録期限のカレンダーをクリック'
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_btn_task_land_day')
//				'日付選択'
//				JvUtil.click('Page_MeetingLandDepend/calendar_ymd/cal_20')
//				'日付選択後、OKボタンを押下'
//				JvUtil.click('Page_MeetingLandDepend/calendar_meet_start_from/button_OK')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_Hort')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_TaskInputLimit/H_12')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_Minute')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_TaskInputLimit/M_12')
//				break
//			case C_Object.C_資料提出期限:
//				'資料提出期限のカレンダーをクリック'
//				JvUtil.click('Object Repository/Page_MeetingLandDepend/calendar_file_output_day/calendar')
//				'日付選択'
//				JvUtil.click('Page_MeetingLandDepend/calendar_ymd/cal_20')
//				'日付選択後、OKボタンを押下'
//				JvUtil.click('Page_MeetingLandDepend/calendar_meet_start_from/button_OK')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_file_Hort')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_FileOutPutLimit/file_H_12')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_file_Minute')
//
//				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_FileOutPutLimit/file_M_12')
//				break
//			default:
//				break
//		}
//	}


}