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
import com.jv.constants.SK_Object
import com.jv.constants.C_Object
import com.jv.constants.L_Object
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
import com.jv.key.JvOpt as JvOpt
import com.jv.key.JvUtil as JvUtil


class JvLOpt {

	/**
	 * Function 議題登録依頼
	 * @param action
	 * @return 無し
	 */
	def static taskInputDepend(String meetType) {
		'事務局１の議題登録'
		JvOpt.taskInputDepend(meetType);
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
	 * Function リマインド送信の処理
	 * @param action
	 * @return 無し
	 */
	def static remindSend(String action) {
		if(false) {
			WebUiBuiltInKeywords.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_remaindSend'))
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
	 * Function パスワードチェックの処理
	 * @param action
	 * @return 無し
	 */
	def static passwordCheck(String action) {

		switch (action) {
			case C_Object.C_ON:
				JvUtil.clickElement(L_Object.L_メールパスワード);
				break
			case C_Object.C_OFF:
				break
			default:
				break
		}
	}

	/**
	 * Function 開催中止の処理
	 * @param action
	 * @return 無し
	 */
	def static meetStop(String action) {

		'開催中止ボタン'
		switch (action) {
			case C_Object.C_はい:
				JvUtil.clickElement(L_Object.L_開催中止);
				JvUtil.clickElement(L_Object.L_開催中止_はい)
				break
			case C_Object.C_いいえ:
				JvUtil.clickElement(L_Object.L_開催中止);
				JvUtil.clickElement(L_Object.L_開催中止_いいえ)
				break
			default:
				break
		}
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
	 * Function 招集通知送付待ちの処理
	 * @param action
	 * @return 無し
	 */
	def static conveneInformSendWait(String action) {
		'招集通知送付待ちへ'
		JvUtil.clickElement(L_Object.L_招集通知送付待ちへ);
		'議題回答率_はいボタン'
		JvUtil.clickElement(L_Object.L_回答率100);
	}

	/**
	 * Function 招集通知送付への処理
	 * @param action
	 * @return 無し
	 */
	def static conveneInformSend(String action) {
		'招集通知送付へ'
		JvUtil.clickElement(L_Object.L_招集通知送付);
		switch (action) {
			case C_Object.C_送信:
				'招集通知修正－＞　送信ボタン'
				JvUtil.clickElement(L_Object.L_送信ボタン_臨場);
				break
			case C_Object.C_戻る:
				'招集通知修正－＞　戻る'
				JvUtil.clickElement(L_Object.L_戻る);
				break
			default:
				break
		}
	}

	/**
	 * Function 招集通知修正の処理
	 * @param action
	 * @return 無し
	 */
	def static conveneInformUpdate(String action) {

		'招集通知修正'
		JvUtil.clickElement(L_Object.L_招集通知修正);

		switch (action) {
			case C_Object.C_送信:
				'招集通知修正－＞　送信ボタン'
				JvUtil.clickElement(L_Object.L_送信ボタン_臨場);
				break
			case C_Object.C_戻る:
				'招集通知修正－＞　戻る'
				JvUtil.clickElement(L_Object.L_戻る);
				break
			default:
				break
		}
	}

	/**
	 * Function 資料送付待ちへの処理
	 * @param action
	 * @return 無し
	 */
	def static materialSendWait(String action) {

		'資料送付待ちへボタン'
		JvUtil.clickElement(L_Object.L_資料送付待ちへ);
		switch (action) {
			case C_Object.C_はい:
				'資料送付待ちステータスにはいボタン'
				JvUtil.clickElement(L_Object.L_資料送付待ちへ_はいボタン);
				break
			case C_Object.C_いいえ:
				'資料送付待ちステータスにいいえボタン'
				JvUtil.clickElement(L_Object.L_資料送付待ちへ_いいえボタン);
				break
			case C_Object.C_いいえandはい:
				'資料送付待ちステータスにいいえボタン'
				JvUtil.clickElement(L_Object.L_資料送付待ちへ_いいえボタン);
				'資料送付待ちへボタン'
				JvUtil.clickElement(L_Object.L_資料送付待ちへ);
				'資料送付待ちステータスにいいえボタン'
				JvUtil.clickElement(L_Object.L_資料送付待ちへ_はいボタン);
				break
			default:
				break
		}
	}

	/**
	 * Function 資料送付への処理
	 * @param action
	 * @return 無し
	 */
	def static materialSend(String action) {
		'資料送付へボタン'
		JvUtil.clickVisibleElement(L_Object.L_資料送付);
	}

	/**
	 * Function 資料再送ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static materialAgainSend(String action) {
		'資料再送ボタン'
		JvUtil.clickVisibleElement(L_Object.L_資料再送);
	}

	/**
	 * Function 参加者の処理
	 * @param action
	 * @return 無し
	 */
	def static participants(String number, String action) {
	}


	/**
	 * Function 臨場場合の開催ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static openMeet(String action) {

		'MD_開催しましたボタン'
		JvUtil.clickElement(L_Object.L_開催しましたボタン);

		switch (action) {
			case C_Object.C_はい:
				'MD_開催しました_はいボタン'
				JvUtil.clickElement(L_Object.L_開催しました_はいボタン);
				break
			case C_Object.C_いいえ:
				'MD_開催しました_いいえボタン'
				JvUtil.clickElement(L_Object.L_開催しました_いいえボタン);
				break
			case C_Object.C_いいえandはい:
				'MD_開催しました_いいえボタン'
				JvUtil.clickElement(L_Object.L_開催しました_いいえボタン);
				'MD_開催しましたボタン'
				JvUtil.clickElement(L_Object.L_開催しましたボタン);
				'MD_開催しました_はいボタン'
				JvUtil.clickElement(L_Object.L_開催しました_はいボタン);
				break
			default:
				break
		}
	}

	/**
	 * Function 議事録押印無登録ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static hansardInNotLand(String action) {

		'MD_議事録押印無登録ボタン'
		JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_TaskIn')

		switch (action) {
			case C_Object.C_はい:
				'MD_議事録押印無し確認_はいボタン'
				JvUtil.clickElement('Page_MeetingOfDetails/L_Component/Alert/btn_TaskIn_Yes')
				break
			case C_Object.C_いいえ:

				break
			case C_Object.C_いいえandはい:
				'MD_開催しました_いいえボタン'
			//JvUtil.clickElement(L_Object.L_開催しました_はいボタン);
				'MD_議事録押印無登録ボタン'
				JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_TaskIn')
				'MD_議事録押印無し確認_はいボタン'
				JvUtil.clickElement('Page_MeetingOfDetails/L_Component/Alert/btn_TaskIn_Yes')
				break
			default:
				break
		}
	}

	/**
	 * Function ロックボタンとロック解除ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static lock(String action) {

		switch (action) {
			case C_Object.C_ロックボタン:
				'ロックボタン'
				JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/SK_btn_lock')
				WebUiBuiltInKeywords.delay(3)
				break
			case C_Object.C_ロック解除ボタン:
				'ロック解除ボタン'
				JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/SK_btn_lock')
				WebUiBuiltInKeywords.delay(3)
				break
			default:
				break
		}
	}

	/**
	 * Function 完了ボタンの処理
	 * @param action
	 * @return 無し
	 */
	def static over(String action) {

		'完了'
		JvUtil.clickElement(L_Object.L_完了ボタン);

		switch (action) {
			case C_Object.C_はい:
				'取締完了'
				JvUtil.clickElement(L_Object.L_取締完了ボタン);
				break
			case C_Object.C_いいえ:
				'閉じる'
				JvUtil.clickElement(L_Object.L_閉じるボタン);
				break
			case C_Object.C_いいえandはい:
				'閉じる'
				JvUtil.clickElement(L_Object.L_閉じるボタン);
				'完了'
				JvUtil.clickElement(L_Object.L_完了ボタン);
				'取締完了'
				JvUtil.clickElement(L_Object.L_取締完了ボタン);
				break
			default:
				break
		}
	}

	/**
	 * Function 資料ファイルのUpdate
	 * @param action
	 * @return 無し
	 */
	def static fileUpdate(String action) {

		String fileName = "";

		switch (action) {
			case C_Object.C_招集通知書:
				'招集通知書'
				fileName = "招集通知書.docx"

				'DropDownをクリック'
				JvUtil.clickElement(L_Object.L_資料_DropDown);
				'招集通知を選択'
				JvUtil.clickElement(L_Object.L_資料_招集通知);
				'招集通知ファイルを追加'
				WebUiBuiltInKeywords.delay(3)

				WebUiBuiltInKeywords.comment('Path :' + TestCaseFactory.getProjectDirPath() + File.separator + 'File\\' + fileName)
				WebUiBuiltInKeywords.uploadFile(findTestObject(L_Object.L_添付ファイル), TestCaseFactory.getProjectDirPath() + File.separator + 'File\\' + fileName)
				'ファイルをアップロード'
				WebUiBuiltInKeywords.delay(3)
				JvUtil.clickElement(L_Object.L_ファイルをアップロード);

				break
			case C_Object.C_資料:
				'資料'
				fileName = "資料.docx"

				'DropDownをクリック'
				JvUtil.clickElement(L_Object.L_資料_DropDown);
				'資料を選択'
				WebUiBuiltInKeywords.delay(3)
				JvUtil.clickElement(L_Object.L_資料);
				'資料ファイルを追加'
				WebUiBuiltInKeywords.delay(3)
				WebUiBuiltInKeywords.uploadFile(findTestObject(L_Object.L_添付ファイル), TestCaseFactory.getProjectDirPath() + File.separator + 'File\\' + fileName)
				'ファイルをアップロード'
				WebUiBuiltInKeywords.delay(3)
				JvUtil.clickElement(L_Object.L_ファイルをアップロード);
				break
			case C_Object.C_議事録押印有り:
				'議事録押印有り'
				fileName = "議事録_押印あり.docx"

				'DropDownをクリック'
				JvUtil.clickElement(L_Object.L_資料_DropDown);

				'DropDown議事録押印有'
				JvUtil.clickElement(L_Object.L_DropDown議事録押印有);

				'資料ファイルを追加'
				WebUiBuiltInKeywords.delay(5)
				WebUiBuiltInKeywords.uploadFile(findTestObject(L_Object.L_添付ファイル), TestCaseFactory.getProjectDirPath() + File.separator + 'File\\' + fileName)

				JvUtil.clickElement(L_Object.L_ファイルをアップロード);
				WebUiBuiltInKeywords.delay(5)
				break
			case C_Object.C_議事録押印無し:
				'議事録押印無し'
				fileName = "議事録_押印なし.docx"

				'DropDownをクリック'
				JvUtil.clickElement(L_Object.L_資料_DropDown);

				'DropDown議事録押印無'
				JvUtil.clickElement(L_Object.L_DropDown議事録押印無);

				'資料ファイルを追加'
				WebUiBuiltInKeywords.delay(5)
				WebUiBuiltInKeywords.uploadFile(findTestObject(L_Object.L_添付ファイル), TestCaseFactory.getProjectDirPath() + File.separator + 'File\\' + fileName)

				'ファイルをアップロード'
				WebUiBuiltInKeywords.delay(7)
				JvUtil.clickElement(L_Object.L_ファイルをアップロード);
				break
			default:
				break
		}
	}


	/**
	 * Function log
	 * @param caseNumber
	 * @param action
	 * @return 無し
	 */
	def static log(String caseNumber , String action) {
		WebUiBuiltInKeywords.comment('自動化テスト：法定会議システム　臨場' + caseNumber + ' ' + action)
	}
}