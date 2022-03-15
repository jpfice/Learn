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
import com.kms.katalon.core.webui.constants.StringConstants
import internal.GlobalVariable
import groovy.transform.CompileStatic
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
import com.jv.key.JvUtil as JvUtil
import com.jv.key.JvOpt as JvOpt
import com.jv.constants.C_Object as C_Object
import com.jv.constants.L_Object as L_Object
import com.jv.constants.SK_Object as SK_Object

class JvKOpt {

	/**
	 * Function 可決報告と否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static ReportBook(String ReportType){
		switch (ReportType) {
			case C_Object.C_可決報告:
				WebUiBuiltInKeywords.comment('可決報告の場合')
				canReportBook();
				break
			case C_Object.C_否決報告:
				WebUiBuiltInKeywords.comment('否決報告の場合')
				canNotReportBook();
				break
			default:
				break
		}
	}

	/**
	 * Function 可決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static canReportBook(){

		WebUiBuiltInKeywords.delay(3)
		String[] opts= [
			SK_Object.S_可決報告,
			SK_Object.SK_可決報告はいボタン,
			SK_Object.SK_決議待ちの送信ボタン,
		];

		for (int i=0; i<opts.length; i++) {
			WebUiBuiltInKeywords.delay(3)
			JvUtil.clickElement(opts[i], C_Object.C_可点击)
		}
	}

	/**
	 * Function 否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static canNotReportBook(){

		WebUiBuiltInKeywords.delay(3)
		String[] opts= [
			SK_Object.SK_否決報告,
			SK_Object.SK_否決報告はいボタン,
			SK_Object.SK_決議待ちの送信ボタン,
		];

		for (int i=0; i<opts.length; i++) {
			WebUiBuiltInKeywords.delay(3)
			JvUtil.clickElement(opts[i], C_Object.C_可点击)
		}
	}

	/**
	 * Function 自動リマインドの処理
	 * @param action
	 * @return 無し
	 */
	def static autoRemind(String action) {

		TestObject fto = findTestObject('Page_MeetingOfDetails/SK_Component/SK_switch_autoRemaindstatus');
		List<WebElement> listElement = WebUiBuiltInKeywords.findWebElements(fto, 10)
		for(int i=0; i< listElement.size(); i++ ) {
			WebElement we = listElement.get(0);
			WebUiBuiltInKeywords.comment('AutoRemind Properties' + we.getProperties().toMapString())
			String clazz = we.getAttribute("class")
			WebUiBuiltInKeywords.comment('clazz' + clazz)
		}
		//JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_switch_remaind')
	}

	/**
	 * Function 会議情報更新の処理
	 * @param action
	 * @return 無し
	 */
	def static meetInfoUpdate(String action) {
		'会議情報更新ボタン'
		JvUtil.clickElement(L_Object.L_会議情報更新);
		WebUiBuiltInKeywords.delay(5)
	}

	/**
	 * Function 共通ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static commontBtn(String action) {

		switch (action) {
			case C_Object.C_スキップ:
				JvUtil.clickElement(L_Object.L_スキップ);
				break
			case C_Object.C_戻る:
				JvUtil.clickElement(L_Object.L_戻る);
				break
			case C_Object.C_送信:
				JvUtil.clickElement(L_Object.L_送信ボタン_臨場);
				break
			default:
				break
		}
	}
	
	/**
	 * Function 同意書の押印無し、押印あり
	 * @param fileList
	 * @return 無し
	 */
	def static agreeBook(String[] fileList) {

		WebUiBuiltInKeywords.delay(8)
		
		for(int i=1; i<=fileList.length; i++) {
			'アップロードボタン'
			JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_R'+i+'_upLoad', C_Object.C_可点击)
			JvOpt.dialogFileUpdate(fileList[i-1]);
		}
	}
	
	
	/**
	 * Function 同意書の押印無し、押印あり
	 * @param fileList
	 * @return 無し
	 */
	def static meanShow(String[] agreeList) {

		WebUiBuiltInKeywords.delay(8)
		
		for(int i=1; i<=agreeList.length; i++) {
			WebUiBuiltInKeywords.delay(3)
			switch (agreeList[i-1]) {
				case C_Object.C_同意:
					'アップロードボタン'
					JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_R'+i+'_upLoad', C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvOpt.dialogFileUpdate(C_Object.C_同意書押印有り);
					break
				case C_Object.C_不同意:
					'dropdownをクリック'
					JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_dropdown_meanshow'+i, C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Common/K_agree_no', C_Object.C_可点击)
					break
				default:
					break
			}
		}
	}
}