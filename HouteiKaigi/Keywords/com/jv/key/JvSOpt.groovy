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

import groovy.transform.CompileStatic
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.constants.StringConstants
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.jv.constants.SK_Object
import com.jv.constants.C_Object
import com.jv.constants.S_Object
import com.jv.constants.L_Object

class JvSOpt {

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
	 * Function メールのパスワード設定
	 * @param action
	 * @return 無し
	 */
	def static emailPassWordCheck() {
		'メールのパスワード設定'
		for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
			try {
				String mailPassWordStr = 'Page_MeetingOfDetails/S_Component/S_checkbox_mailPassWord'+ i
				WebUiBuiltInKeywords.comment("mailPassWordStr " + mailPassWordStr)
				JvUtil.clickElement(mailPassWordStr, C_Object.C_可点击)
			}catch (Exception e) {
				e.printStackTrace()
			}
		}
	}

	/**
	 * Function 共通ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static commontBtn(String action) {

		switch (action) {
			case C_Object.C_スキップ:
				JvUtil.clickElement("スキップ");
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
	 * Function 役員部品の回答ボタンが全て「同意書」に変更
	 * @param 無し
	 * @return 無し
	 */
	def static changeToAgreeBook() {
		while(JvSOpt.checkAnswerType("S_Component" , "ボタン回答")) {
			for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
				try {
					String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
					WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
					WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
					String jobName = jobelement.getAttribute("value")

					String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
					WebUiBuiltInKeywords.comment("answerTypeStr " + answerTypeStr)
					WebElement answerTypeElement = WebUiBuiltInKeywords.findWebElement(findTestObject(answerTypeStr), 20)
					String answerType = answerTypeElement.getText()

					WebUiBuiltInKeywords.comment("第"+i+"行 jobName " + jobName + " AnswerType " + answerType)

					if(i == 3) {
						WebUiBuiltInKeywords.scrollToElement(findTestObject(jobStr), 10)
					}

					if (answerType == "ボタン回答") {
						WebUiBuiltInKeywords.waitForElementClickable(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_ansower'+ i), 20)
						WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_ansower'+ i))
						'同意書を選択'
						WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_radio_agree'))
						'回答方法を登録'
						WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_btn_ansowe_save'))
						WebUiBuiltInKeywords.delay(3)
						break
					}
				}catch (Exception e) {
					e.printStackTrace()
				}
			}
		}
	}


	/**
	 * Function 異議なし
	 * @param action
	 * @return 無し
	 */
	def static allAgree() {

		for (int i=1; i<=GlobalVariable.G_JobCount; i++) {

			String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
			WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
			WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
			String jobName = jobelement.getAttribute("value")

			String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
			WebUiBuiltInKeywords.comment("answerTypeStr " + answerTypeStr)
			WebElement answerTypeElement = WebUiBuiltInKeywords.findWebElement(findTestObject(answerTypeStr), 20)
			String answerType = answerTypeElement.getText()

			if(jobName == C_Object.C_監査役 && answerType == C_Object.C_同意書) {
				JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击);
				WebUiBuiltInKeywords.delay(3)
				JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_diff_no' , C_Object.C_可点击)
				WebUiBuiltInKeywords.delay(3)
			}

			if(jobName == C_Object.C_取締役 && answerType == C_Object.C_同意書) {
				JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击);
				WebUiBuiltInKeywords.delay(2)
				JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_agree_have', C_Object.C_可点击)
				WebUiBuiltInKeywords.delay(3)
			}
		}
	}


	/**
	 * Function 異議なし
	 * @param action
	 * @return 無し
	 */
	def static allAgree(String role, String action) {

		for (int i=1; i<=4; i++) {

			String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
			WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
			WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
			String jobName = jobelement.getAttribute("value")

			if(!role.equals(jobName)) {
				continue;
			}

			String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
			WebUiBuiltInKeywords.comment("answerTypeStr " + answerTypeStr)
			WebElement answerTypeElement = WebUiBuiltInKeywords.findWebElement(findTestObject(answerTypeStr), 20)
			String answerType = answerTypeElement.getText()

			if(role.equals(C_Object.C_監査役) && jobName.equals(C_Object.C_監査役)) {
				WebUiBuiltInKeywords.comment('監査役の場合')
				if (action == C_Object.C_異議なし) {
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_diff_no', C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
				}
				else {
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_diff_have', C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
				}
			}

			if(role.equals(C_Object.C_取締役) && jobName.equals(C_Object.C_取締役)) {
				WebUiBuiltInKeywords.comment('取締役の場合')
				if (action == C_Object.C_同意) {
					WebUiBuiltInKeywords.scrollToElement(findTestObject(jobStr), 10)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_agree_have', C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
				}
				else {
					WebUiBuiltInKeywords.scrollToElement(findTestObject(jobStr), 10)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i, C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
					JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_Job_Common/S_agree_no', C_Object.C_可点击)
					WebUiBuiltInKeywords.delay(3)
				}
			}
		}
	}

	/**
	 * Function Uploadファイルのチェック
	 * @param action
	 * @return 無し
	 */
	def static fileUploadCheck(String fileType){

		WebUiBuiltInKeywords.delay(2)

		switch (fileType) {
			case C_Object.C_確認書押印無し:
				fileUploadCheck(C_Object.C_監査役, C_Object.C_確認書押印無し)
				break
			case C_Object.C_確認書押印有り:
				fileUploadCheck(C_Object.C_監査役, C_Object.C_確認書押印有り)
				break
			case C_Object.C_同意書押印無し:
				fileUploadCheck(C_Object.C_取締役, C_Object.C_同意書押印無し)
				break
			case C_Object.C_同意書押印有り:
				fileUploadCheck(C_Object.C_取締役, C_Object.C_同意書押印有り)
				break
			default:
				break
		}
	}

	/**
	 * Function Uploadファイルのチェック
	 * @param action
	 * @return 無し
	 */
	def static fileUploadCheck(String jobType, String fileType){

		for(int i=1; i < 5; i++) {

			WebUiBuiltInKeywords.delay(3)

			GlobalVariable.G_RowNum = String.valueOf(i)
			WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(S_Object.S_役職), 20)
			String jobName = jobelement.getAttribute("value")
			WebUiBuiltInKeywords.comment("役職 " + jobName)

			if (jobName != jobType) {
				continue;
			}

			'DropDownをクリック'
			JvUtil.clickElement(S_Object.S_ファイル種別, C_Object.C_可点击)
			WebUiBuiltInKeywords.comment("DropDownをクリック over")

			WebUiBuiltInKeywords.delay(2)
			switch (fileType) {
				case C_Object.C_確認書押印無し:
				//WebUiBuiltInKeywords.scrollToElement(findTestObject(S_Object.S_ファイル名_確認書押印無し), 10)
					JvUtil.clickElement(S_Object.S_ファイル名_確認書押印無し, C_Object.C_可见)
					break
				case C_Object.C_確認書押印有り:
				//WebUiBuiltInKeywords.scrollToElement(findTestObject(S_Object.S_ファイル名_確認書押印有り), 10)
					JvUtil.clickElement(S_Object.S_ファイル名_確認書押印有り, C_Object.C_可见)
					break
				case C_Object.C_同意書押印無し:
				//WebUiBuiltInKeywords.scrollToElement(findTestObject(S_Object.S_ファイル名_同意書押印無し), 10)
					JvUtil.clickElement(S_Object.S_ファイル名_同意書押印無し, C_Object.C_可见)
					break
				case C_Object.C_同意書押印有り:
				//WebUiBuiltInKeywords.scrollToElement(findTestObject(S_Object.S_ファイル名_同意書押印有り), 10)
					JvUtil.clickElement(S_Object.S_ファイル名_同意書押印有り, C_Object.C_可见)
					break
				default:
					break
			}
			WebUiBuiltInKeywords.comment("FileType " + fileType + "click over")

			//JvUtil.clickElement(S_Object.S_ファイル名, C_Object.C_可点击)
			//JvUtil.clickElement(S_Object.S_ファイル名, C_Object.C_可点击)
			//WebUiBuiltInKeywords.enhancedClick(findTestObject(S_Object.S_ファイル名))
			WebUiBuiltInKeywords.delay(3)
			'ファイル名前を取得'
			WebElement fileNameElement = WebUiBuiltInKeywords.findWebElement(findTestObject(S_Object.S_ファイル), 20)
			String fileName = fileNameElement.getText()
			WebUiBuiltInKeywords.comment("FileName " + fileName)

			if (fileName != null || fileName != "") {
				WebUiBuiltInKeywords.comment("FileName <" + fileName+ "> UpLoad Success")
			}
			else {
				JvOpt.dialogFileUpdate(fileType)
			}
		}
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */
	def Map<Integer, String> getMemberRole(String model){

		Map<Integer, String> dataMap = new HashMap<Integer, String>();
		for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
			String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
			WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
			WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
			String jobName = jobelement.getAttribute("value")
			dataMap.put(i, jobName);
		}
		return dataMap
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */
	@Keyword
	def static Map<Integer, String> getAnswerType(String model){

		Map<Integer, String> dataMap = new HashMap<Integer, String>();
		for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
			String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
			WebUiBuiltInKeywords.comment("answerTypeStr " + answerTypeStr)
			WebElement answerTypeElement = WebUiBuiltInKeywords.findWebElement(findTestObject(answerTypeStr), 20)
			String answerType = answerTypeElement.getText()
			dataMap.put(i, answerType);
		}
		return dataMap
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */
	@Keyword
	def static boolean checkAnswerType(String model, String AnswerType){

		Map<Integer, String> AnswerTypeMap = getAnswerType(model);
		for(int i=1; i<=GlobalVariable.G_JobCount; i++) {
			if (AnswerTypeMap.get(i) == AnswerType) {
				return true
			}
		}
		return false
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */
	@Keyword
	def boolean checkRole(String model, String AnswerType){

		Map<Integer, String> RoleMap = getAnswerType(model);
		for(int i=1; i<=GlobalVariable.G_JobCount; i++) {
			if (RoleMap.get(i) == AnswerType) {
				return true
			}
		}
		return false
	}

	@Keyword
	def getAnswerTypeClick(){

		WebUiBuiltInKeywords.waitForElementVisible(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R2/S_btn_R2_job'), 20)
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R2/S_btn_R2_job'))
		'同意書を選択'
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_radio_agree'))
		'回答方法を登録'
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_btn_ansowe_save'))

		WebUiBuiltInKeywords.delay(3)

		WebUiBuiltInKeywords.waitForElementVisible(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R4/S_btn_R4_job'), 20)
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R4/S_btn_R4_job'))
		'同意書を選択'
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_radio_agree'))
		'回答方法を登録'
		WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_btn_ansowe_save'))

		WebUiBuiltInKeywords.delay(3)
	}

	//	@CompileStatic
	//	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static updateFileInHave(){
		try {
			for (int i = 1; i <= 4; i++) {
				String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
				WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
				WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
				String jobName = jobelement.getAttribute("value")

				if (jobName == "取締役") {

					'アップロードをクリック'
					WebUiBuiltInKeywords.waitForElementVisible(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'), 20)
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'))
					WebUiBuiltInKeywords.delay(3)

					'DropDownをクリック'
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_dropdown'))
					WebUiBuiltInKeywords.delay(3)

					'同意書押印有り'
					WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_agree_in_have'))
					WebUiBuiltInKeywords.delay(1)

					'同意書押印ファイルを添付'
					WebUiBuiltInKeywords.uploadFile(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_addFile'), (RunConfiguration.getProjectDir() + "\\File\\" + "確認書_あり.docx"))
					WebUiBuiltInKeywords.delay(3)

					'同意書押印無しファイルのアップロード'
					WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_btn_upload'))
					WebUiBuiltInKeywords.delay(3)

					'閉じる'
					WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_btn_close'))
					WebUiBuiltInKeywords.delay(3)

					//TestObject -> 同意書
					//String xpath1 = '//div[@data-control-name="MD_書_役員ギャラリー"]//div[@aria-posinset="2"]//div[text()="アップロード"]'
					//TestObject to1 = new TestObject('objectname')
					//to1.addProperty('xpath', ConditionType.EQUALS, xpath1)
				}
				if (jobName == "監査役") {

					//TestObject -> 確認書
					WebUiBuiltInKeywords.delay(3)
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'))
					WebUiBuiltInKeywords.delay(3)

					'DropDownをクリック'
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_dropdown'))
					WebUiBuiltInKeywords.delay(3)

					'確認書押印あり'
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_confirm_in_have'))
					WebUiBuiltInKeywords.delay(3)

					'確認書押印ァイルを添付'
					WebUiBuiltInKeywords.scrollToElement(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_addFile'), 3)
					WebUiBuiltInKeywords.uploadFile(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_addFile'), (RunConfiguration.getProjectDir() + "\\File\\" + "確認書_あり.docx"))
					WebUiBuiltInKeywords.delay(3)

					'確認書押印ファイルのアップロード'
					WebUiBuiltInKeywords.scrollToElement(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_btn_upload'), 3)
					WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_btn_upload'))
					WebUiBuiltInKeywords.delay(3)

					'閉じる'
					WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/SK_Component/Dialog/SK_upload_dialog_btn_close'))
					WebUiBuiltInKeywords.delay(3)
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace()
		}
	}




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
			JvUtil.clickElement(opts[i])
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
			JvUtil.clickElement(opts[i])
		}
	}
}