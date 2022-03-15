package com.jv.key
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration

import com.jv.constants.C_Object
import com.jv.constants.L_Object
import com.jv.constants.S_Object
import com.jv.constants.K_Object
import com.jv.constants.SK_Object
import com.jv.util.DataReadin
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
import internal.GlobalVariable as GlobalVariable
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.constants.StringConstants
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.Keys as Keys
import com.jv.key.JvUtil as JvUtil


class JvOpt {

	/**
	 * Function アカウントログインンの処理
	 * @param action
	 * @return 無し
	 */
	def static accountLogin() {
		'アカウントログイン'
		WebUiBuiltInKeywords.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)
	}


	/**
	 * Function 議題登録依頼
	 * @param action
	 * @return 無し
	 */
	def static taskInputDepend(String meetType) {

		GlobalVariable.G_MeetType = meetType
		'事務局１の議題登録'
		WebUiBuiltInKeywords.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : meetType], FailureHandling.STOP_ON_FAILURE)

		WebUiBuiltInKeywords.delay(5)

		WebUiBuiltInKeywords.switchToFrame(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 1)

		WebUiBuiltInKeywords.verifyElementNotPresent(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 20)
	}

	/**
	 * Function 会議一覧画面へ遷移
	 * @param action
	 * @return 無し
	 */
	def static meetList(String meetName) {

		'会議一覧画面へ遷移'
		JvUtil.clickElement('Page_Top/company01', C_Object.C_可点击)
		WebUiBuiltInKeywords.delay(5)

		if (GlobalVariable.G_MeetType == C_Object.C_書面 || GlobalVariable.G_MeetType == C_Object.C_株主) {
			JvUtil.clickElement(SK_Object.SK_会議一覧のソート, C_Object.C_可点击)
			WebUiBuiltInKeywords.delay(2)
			JvUtil.clickElement(SK_Object.SK_決議日降順, C_Object.C_可点击)
			WebUiBuiltInKeywords.delay(2)
		}
		if (GlobalVariable.G_MeetType == C_Object.C_臨場) {
			JvUtil.clickElement(SK_Object.SK_会議一覧のソート, C_Object.C_可点击)
			WebUiBuiltInKeywords.delay(2)
			JvUtil.clickElement(SK_Object.SK_開催日時降順, C_Object.C_可点击)
			WebUiBuiltInKeywords.delay(2)
		}

		WebUiBuiltInKeywords.switchToFrame(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 1)
		WebUiBuiltInKeywords.verifyElementNotPresent(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 20)

		//Search 会議名により会議を探します。
		String xpath1 = '//div[@data-control-name="ML_議題一覧"]//div/span[@title="'+ meetName +'"]'
		TestObject to1 = new TestObject('objectname')
		to1.addProperty('xpath', ConditionType.EQUALS, xpath1)

		boolean scrollFlag = true;
		List<WebElement> listElement = new ArrayList<WebElement>();

		try {
			//Case 直接に見つかれる場合、そのまま利用
			listElement = WebUiBuiltInKeywords.findWebElements(to1, 10)
			if (listElement.size() > 0) {
				listElement.get(0).click();
				scrollFlag = false;
			}
		}
		catch(Exception e) {
			//Case 直接に見つかれない場合、「データをロードしています」を関鍵字を探します。
			while(scrollFlag) {
				WebUiBuiltInKeywords.scrollToElement(findTestObject('Object Repository/Page_MeetingList/scroll_last'),10)
				WebUiBuiltInKeywords.delay(3)
				listElement = WebUiBuiltInKeywords.findWebElements(to1, 10)
				if (listElement.size() > 0) {
					listElement.get(0).click();
					scrollFlag = false;
				}
			}
		}

	}

	/**
	 * Get SysDate
	 * @param null
	 * @return SysDate(yyyyMMddHHmmss)
	 */
	@Keyword
	def static String getDateTime() {

		String ymd = "";
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 2);
			date = c.getTime();

			WebUiBuiltInKeywords.comment('---->> 月 ' + c.get(Calendar.WEEK_OF_MONTH))
			WebUiBuiltInKeywords.comment('---->> 日 ' + c.get(Calendar.DATE))
			WebUiBuiltInKeywords.comment('---->> 時 ' + c.get(Calendar.HOUR_OF_DAY))
			WebUiBuiltInKeywords.comment('---->> 分 ' + c.get(Calendar.MINUTE))

			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmm");
			ymd = dataFormat.format(date);
			println ymd.toString();

		} catch (Exception e) {
			e.printStackTrace()
		}
		return ymd;
	}

	/**
	 * Function 日時の設定
	 * @param  ymdType
	 * @return 無し
	 */
	def static ymdSet(String ymdType) {

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		switch (ymdType) {
			case C_Object.C_開催日時From:

				c.add(Calendar.DATE, 2);
				WebUiBuiltInKeywords.comment('開催日時From>> 月 ' + c.get(Calendar.WEEK_OF_MONTH))
				WebUiBuiltInKeywords.comment('開催日時From>> 日 ' + c.get(Calendar.DATE))
				WebUiBuiltInKeywords.comment('開催日時From>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('開催日時From>> 分 ' + c.get(Calendar.MINUTE))

				'開催日時From'
				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時From)

				'開催日時Fromの設定'
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_ymd/cal_' + c.get(Calendar.DATE))

				'日付選択後、OKボタンを押下'
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_from/button_OK')

				'開催日時From_時の設定'
				GlobalVariable.G_TimeNum = "2";
				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時From_時)
			//JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_from/Hort_' + c.get(Calendar.HOUR_OF_DAY))
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_hms/H_' + c.get(Calendar.HOUR_OF_DAY))

				'開催日時From_分の設定'
				GlobalVariable.G_TimeNum = "3";
				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時From_分)
			//JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_from/min_09')
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_hms/M_' + c.get(Calendar.MINUTE))
				break
			case C_Object.C_開催日時To:

				c.add(Calendar.HOUR_OF_DAY, 3);
				WebUiBuiltInKeywords.comment('開催日時To>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('開催日時To>> 分 ' + c.get(Calendar.MINUTE))

				'開催日時To_時の設定'
				GlobalVariable.G_TimeNum = "4";
				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時To_時)
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_to/Hort_' + c.get(Calendar.HOUR_OF_DAY))

				'開催日時To_分の設定'
				GlobalVariable.G_TimeNum = "5";
				JvUtil.clickClickAbleAndVisibleElement(L_Object.L_開催日時To_分)
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/calendar_meet_start_to/min_05')
				break
			case C_Object.C_提案日:

				c.add(Calendar.DATE, 2);
				WebUiBuiltInKeywords.comment('提案日>> 月 ' + (c.get(Calendar.MONTH)+1))
				WebUiBuiltInKeywords.comment('提案日>> 日 ' + c.get(Calendar.DATE))
				WebUiBuiltInKeywords.comment('提案日>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('提案日>> 分 ' + c.get(Calendar.MINUTE))

				'提案日の設定'
				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_提案日のカレンダーをクリック)
				JvUtil.clickClickAbleAndVisibleElement("Page_MeetingLandDepend/calendar_ymd/cal_" + c.get(Calendar.DAY_OF_MONTH) )
				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_OKボタン)
				break
			case C_Object.C_決議日:

				c.add(Calendar.DATE, 4);
				WebUiBuiltInKeywords.comment('決議日>> 月 ' + (c.get(Calendar.MONTH)+1))
				WebUiBuiltInKeywords.comment('決議日>> 日 ' + c.get(Calendar.DATE))
				WebUiBuiltInKeywords.comment('決議日>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('決議日>> 分 ' + c.get(Calendar.MINUTE))

				'決議日の設定'
				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_決議日のカレンダーをクリック)
				JvUtil.clickClickAbleAndVisibleElement("Page_MeetingLandDepend/calendar_ymd/cal_" + c.get(Calendar.DAY_OF_MONTH) )
				JvUtil.clickClickAbleAndVisibleElement(S_Object.S_OKボタン)
				break
			case C_Object.C_議題登録期限:

				c.add(Calendar.DATE, 3);
				WebUiBuiltInKeywords.comment('議題登録期限>> 月 ' + c.get(Calendar.WEEK_OF_MONTH))
				WebUiBuiltInKeywords.comment('議題登録期限>> 日 ' + c.get(Calendar.DATE))
				WebUiBuiltInKeywords.comment('議題登録期限>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('議題登録期限>> 分 ' + c.get(Calendar.MINUTE))

				'議題登録期限のカレンダーをクリック'
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_btn_task_land_day')

				'日付選択'
				JvUtil.clickClickAbleAndVisibleElement("Page_MeetingLandDepend/calendar_ymd/cal_" + c.get(Calendar.DAY_OF_MONTH))

				'日付選択後、OKボタンを押下'
				JvUtil.click('Page_MeetingLandDepend/calendar_meet_start_from/button_OK')

				'時の設定'
				GlobalVariable.G_TimeNum = "6";
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_Hort')
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/S_Present_meeting/Cal_TaskInputLimit/H_' + c.get(Calendar.HOUR_OF_DAY))

				'分の設定'
				GlobalVariable.G_TimeNum = "7";
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_Minute')
				JvUtil.clickClickAbleAndVisibleElement('Page_MeetingLandDepend/S_Present_meeting/Cal_TaskInputLimit/M_' + c.get(Calendar.MINUTE))

				break
			case C_Object.C_資料提出期限:

				c.add(Calendar.DATE, 3);
				WebUiBuiltInKeywords.comment('資料提出期限日>> 月 ' + (c.get(Calendar.MONTH)+1))
				WebUiBuiltInKeywords.comment('資料提出期限>> 日 ' + c.get(Calendar.DAY_OF_MONTH))
				WebUiBuiltInKeywords.comment('資料提出期限>> 時 ' + c.get(Calendar.HOUR_OF_DAY))
				WebUiBuiltInKeywords.comment('資料提出期限>> 分 ' + c.get(Calendar.MINUTE))

				'資料提出期限のカレンダーをクリック'
				JvUtil.click('Object Repository/Page_MeetingLandDepend/calendar_file_output_day/calendar')
				'日付選択'
				JvUtil.clickClickAbleAndVisibleElement("Page_MeetingLandDepend/calendar_ymd/cal_" + c.get(Calendar.DAY_OF_MONTH))
				'日付選択後、OKボタンを押下'
				JvUtil.click('Page_MeetingLandDepend/calendar_meet_start_from/button_OK')

				'時の設定'
				GlobalVariable.G_TimeNum = "8";
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_file_Hort')
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_FileOutPutLimit/file_H_' + c.get(Calendar.HOUR_OF_DAY))

				'分の設定'
				GlobalVariable.G_TimeNum = "9";
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/S_dropdown_file_Minute')
				JvUtil.click('Page_MeetingLandDepend/S_Present_meeting/Cal_FileOutPutLimit/file_M_59')
				break
			default:
				break
		}
	}

	/**
	 * Function 機能画面へ遷移
	 * @param action
	 * @return 無し
	 */
	def static pageForward(action) {

		switch (action) {
			case C_Object.C_議題登録依頼:
				'議題登録依頼画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_議題登録依頼)
				break
			case C_Object.C_議題申請:
				'議題申請画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_議題申請)
				break
			case C_Object.C_過去開催閲覧:
				'過去開催閲覧画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_過去開催閲覧)
				break
			case C_Object.C_関係者設定:
				'関係者設定画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_関係者設定)
				break
			case C_Object.C_定型文設定:
				'定型文設定画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_定型文設定)
				break
			case C_Object.C_リマインド設定:
				'リマインド設定画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_リマインド設定)
				break
			case C_Object.C_検索フィルター:
				'検索フィルター画面へ遷移'
				JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_検索フィルター)
				break
			default:
				break
		}

	}

	/**
	 * Function 議題依頼の有りro無し
	 * @param action
	 * @return 無し
	 */
	def static dependHaveOrNo(String action) {
		switch (action) {
			case C_Object.C_依頼あり:
				'依頼あり'
				JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have')
				'閉じる'
				JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_close')
				'依頼あり'
				JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have')
				'続ける'
				JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_continue')
				break
			case C_Object.C_依頼なし:
				'依頼なし'
				JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have')
				'閉じる'
				JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_close')
				'依頼なし'
				JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have')
				'続ける'
				JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_continue')
				break
			default:
				break
		}
	}


	/**
	 * Function 会議日時を取得
	 * @param action
	 * @return 無し
	 */
	def static getYmd(String meetType) {

		String meetname = ""
		'システム時間を取得'
		String ymd = JvUtil.getSystemDate()
		switch (meetType) {
			case C_Object.C_臨場:
				meetname = ('臨場_'+ ymd)
				break
			case C_Object.C_書面:
				meetname = ('書面_'+ ymd)
				break
			case C_Object.C_株主:
				meetname = ('株主_'+ ymd)
				break
			default:
				break
		}
		GlobalVariable.G_MeetName = meetname;
	}

	/**
	 * Function 会議種類の設定
	 * @param action
	 * @return 無し
	 */
	def static setMeetType(String meetType) {
		'会議種類'
		WebUiBuiltInKeywords.click(findTestObject(L_Object.L_会議種類));
		switch (meetType) {
			case C_Object.C_臨場:
				WebUiBuiltInKeywords.click(findTestObject(L_Object.L_取締役会_臨場));
				break
			case C_Object.C_書面:
				WebUiBuiltInKeywords.click(findTestObject(S_Object.S_取締役会_書面));
				break
			case C_Object.C_株主:
				WebUiBuiltInKeywords.click(findTestObject(K_Object.K_取締役会_株主));
				break
			default:
				break
		}
	}

	/**
	 * Function ステータスを取得
	 * @param なし
	 * @return 無し
	 */
	def static getStatus() {
		WebElement element = WebUiBuiltInKeywords.findWebElement(findTestObject('Page_MeetingOfDetails/SK_Component/SK_Status'), 30)
		String status = element.getAttribute("value");
		KeywordUtil.logInfo("画面でステータスは＜ " + status + "＞です。")
	}


	/**
	 * Function 議題登録の処理
	 * @param action
	 * @return 無し
	 */
	def static taskInput(String addType, String meetNumber) {

		String[] Y1 = [
			C_Object.C_議題あり,
			C_Object.C_議題区分_決議,
			"T1",
			C_Object.C_資料登録者_事務局テスト０１,
			C_Object.C_資料添付_あり,
			C_Object.C_Submit_登録
		]
		String[] Y2 = [
			C_Object.C_議題あり,
			C_Object.C_議題区分_報告,
			"T2",
			C_Object.C_資料登録者_議題登録者テスト０１,
			C_Object.C_資料添付_あり,
			C_Object.C_Submit_登録
		]
		String[] Y3 = [
			C_Object.C_議題追加,
			C_Object.C_議題区分_討議,
			"T3",
			C_Object.C_資料登録者_議題登録者テスト０２,
			C_Object.C_資料添付_あり,
			C_Object.C_Submit_登録
		]
		String[] Y4 = [
			C_Object.C_議題追加,
			C_Object.C_議題区分_決議,
			"T4",
			C_Object.C_資料登録者_事務局テスト０２,
			C_Object.C_資料添付_なし,
			C_Object.C_Submit_閉じる
		]

		switch (addType) {
			case C_Object.C_初回_議題追加:
				'初回_議題追加'
				JvOpt.addTask(C_Object.C_議題あり, C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_議題登録者テスト０１,C_Object.C_資料添付_あり, C_Object.C_Submit_登録)
				break
			case C_Object.C_議題追加:
				'議題追加'
				JvOpt.addTask(C_Object.C_議題追加, C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_議題登録者テスト０１,C_Object.C_資料添付_あり, C_Object.C_Submit_登録)
				break
			case C_Object.C_新ユーザー議題追加:

				'議題追加'
				JvOpt.addTask(C_Object.C_議題あり, C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_議題登録者テスト０１,C_Object.C_資料添付_なし, C_Object.C_Submit_登録)
				WebUiBuiltInKeywords.closeBrowser()

				WebUiBuiltInKeywords.comment("会議名 "+ GlobalVariable.G_MeetName)
				JvOpt.reStart(GlobalVariable.G_User_J02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
				WebUiBuiltInKeywords.closeBrowser()

				JvOpt.reStart(GlobalVariable.G_User_G01, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
				WebUiBuiltInKeywords.closeBrowser()

				JvOpt.reStart(GlobalVariable.G_User_G02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
				WebUiBuiltInKeywords.closeBrowser()

				break
			case C_Object.C_議題編集:

				'第一行議題の編集'
				JvOpt.editTask("1", C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_事務局テスト０１,C_Object.C_資料添付_なし, C_Object.C_Submit_登録)
				break
			default:
				break
		}
	}

	/**
	 * Function 議題追加
	 * @param task　議題あり  or 議題無し
	 * @param taskType　議題区分（決議、報告、討議）
	 * @param taskName　議題名
	 * @param inputMember　資料登録者
	 * @param file　添付ファイルの有り or 無し
	 * @param submitType　登録、閉じる、一回閉じる（先に閉じしてから登録）
	 * @return 無し
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static addTask(String task,String taskType,String taskName,String inputMember, String file,String submitType) {

		try {
			//Click 議題あり
			switch (task) {
				case C_Object.C_議題あり:
					WebUiBuiltInKeywords.comment('---->> 議題ありの場合')
					JvUtil.clickClickAbleAndVisibleElement('Page_MeetingOfDetails/L_Component/btn_MeetHave')
					break
				case C_Object.C_議題なし:
					WebUiBuiltInKeywords.comment('---->> 議題なしの場合')
					JvUtil.clickClickAbleAndVisibleElement('Page_MeetingOfDetails/L_Component/btn_MeetNoHave')
					break
				case C_Object.C_議題追加:
					WebUiBuiltInKeywords.comment('---->> 議題追加の場合')
					JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_MeetAdd', C_Object.C_可点击)
					break
				default:
					break
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題失敗")
		}

		try {
			//Edit 議題区分
			switch (taskType) {
				case C_Object.C_議題区分_決議:
					WebUiBuiltInKeywords.comment('議題区分_決議の場合')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_dropdown_TaskType')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_taskType_keyi')
					break
				case C_Object.C_議題区分_報告:
					WebUiBuiltInKeywords.comment('議題区分_報告の場合')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_dropdown_TaskType')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_taskType_hokoku')
					break
				case C_Object.C_議題区分_討議:
					WebUiBuiltInKeywords.comment('議題区分_討議の場合')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_dropdown_TaskType')
					JvUtil.clickVisibleElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_taskType_toyi')
					break
				default:
					break
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題区分の編集が失敗")
		}

		try {
			//Edit 議題名
			if (taskName != null) {
				'議題名'
				WebUiBuiltInKeywords.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), taskName)
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題名の編集が失敗")
		}

		try {
			//Edit 議題資料登録者
			if (inputMember != null) {
				'議題資料登録者の選択'
				JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_taskInputMember')
				switch (inputMember) {
					case C_Object.C_資料登録者_事務局テスト０１:
						JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/Role/J01')
						break
					case C_Object.C_資料登録者_事務局テスト０２:
						JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/Role/J02')
						break
					case C_Object.C_資料登録者_議題登録者テスト０１:
						JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/Role/G01')
						break
					case C_Object.C_資料登録者_議題登録者テスト０２:
						JvUtil.clickElement('Page_MeetingOfDetails/SK_Component/Role/G02')
						break
					default:
						break
				}
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題資料登録者の編集が失敗")
		}

		try {
			//Edit 資料添付
			switch (file) {
				case C_Object.C_資料添付_あり:
					WebUiBuiltInKeywords.comment('資料添付_ありの場合')
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_radio_have')
					WebUiBuiltInKeywords.delay(3)
					WebUiBuiltInKeywords.uploadFile(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_FileAdd'), RunConfiguration.getProjectDir() + "/File/議題資料.docx");
					WebUiBuiltInKeywords.delay(3)
					break
				case C_Object.C_資料添付_なし:
					WebUiBuiltInKeywords.comment('資料添付_なしの場合')
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_radio_nohave')
					break
				default:
					break
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題資料添付失敗")
		}

		try {
			//btn 登録
			switch (submitType) {
				case C_Object.C_Submit_登録:
					WebUiBuiltInKeywords.comment('Submit_登録の場合')
					'登録'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_submit');
					break
				case C_Object.C_Submit_閉じる:
					WebUiBuiltInKeywords.comment('Submit_閉じるの場合')
					'登録'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_close');
					break
				case C_Object.C_Submit_一回閉じる:
					WebUiBuiltInKeywords.comment('Submit_一回閉じるの場合')
				//JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_close');
					'登録'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_submit');
					break
				default:
					break
			}

		}
		catch (Exception e) {
			//			List<WebElement> listElement2 = WebUiBuiltInKeywords.findWebElements(fto, 10);
			//			for(int i=0; i< listElement2.size(); i++ ) {
			//				WebElement we = listElement2.get(0);
			//				WebUiBuiltInKeywords.comment('TestObject Properties' + we.getProperties().toMapString())
			//			}

			KeywordUtil.markFailed("JV Error 議題登録失敗")
		}
		finally {
			WebUiBuiltInKeywords.delay(25)
		}
	}


	/**
	 * Function 議題編集
	 * @param taskNumber　議題番号
	 * @param taskType　議題区分（決議、報告、討議）
	 * @param taskName　議題名
	 * @param inputMember　資料登録者
	 * @param file　添付ファイルの有り or 無し
	 * @param submitType　登録、閉じる、一回閉じる（先に閉じしてから登録）
	 * @return 無し
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static editTask(String taskNumber,String taskType,String taskName,String inputMember, String file,String submitType) {

		WebUiBuiltInKeywords.delay(5)
		'編集ボタンをクリック'
		JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_R' + taskNumber + '_taskEdit',C_Object.C_可见可点击)
		WebUiBuiltInKeywords.delay(3)

		WebElement element = WebUiBuiltInKeywords.findWebElement(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_english'), 30)
		String val = element.getAttribute("value");
		if (!val.isEmpty()) {
			KeywordUtil.logInfo("議題(英語) ＜ " + val + "＞")
			'議題(英語)'
			WebUiBuiltInKeywords.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_english'), (new Date()).time + "BookTest")
			WebUiBuiltInKeywords.delay(3)
		}
		else {
			WebUiBuiltInKeywords.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_english'), "BookTest")
			WebUiBuiltInKeywords.delay(3)
		}

		'登録ボタンをクリック'
		JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_submit');
		WebUiBuiltInKeywords.delay(15)
	}




	/**
	 * Function 議題追加
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */

	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static reStart(String userName, String password, String meetName, String[] meetInfo) {

		//------------------------------------------------------------------------------------------------------------
		// Top画面へ遷移
		//------------------------------------------------------------------------------------------------------------
		'アカウントログイン'
		WebUiBuiltInKeywords.callTestCase(findTestCase('Common Test Cases/AccountLogin'), [('Username') : userName,('Password') : password], FailureHandling.STOP_ON_FAILURE)

		//------------------------------------------------------------------------------------------------------------
		// Page会議一覧画面へ遷移
		//------------------------------------------------------------------------------------------------------------
		'会議一覧画面へ遷移'
		JvOpt.meetList(meetName)

		//meetInfo[0] C_Object.C_議題あり
		//meetInfo[1] C_Object.C_議題区分_報告
		//meetInfo[2] "T2"
		//meetInfo[3] C_Object.C_資料登録者_議題登録者テスト０１
		//meetInfo[4] C_Object.C_資料添付_あり
		//meetInfo[5] C_Object.C_Submit_登録

		'議題追加'
		JvOpt.addTask(meetInfo[0], meetInfo[1], meetInfo[2], meetInfo[3],meetInfo[4], meetInfo[5])

		'ステータス取得'
		JvOpt.getStatus()

	}




	/**
	 * Function 議題追加
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */

	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static addTask(String outerTagName) {
		try {
			for (int i = 0; i < 1; i++) {
				if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_MeetHave'), 3)) {
					'議題有り'
					JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_MeetHave')

					'議題'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDalog_input_task_name')

					WebUiBuiltInKeywords.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), 'T1')

					'なし'
					//WebUI.click(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_radio_nohave'))

					'登録'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDalog_btn_submit');
				} else {
					'議題追加'
					JvUtil.clickElement('Page_MeetingOfDetails/L_Component/btn_taskAdd');
					'議題'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDalog_input_task_name')

					WebUiBuiltInKeywords.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), 'T1')
					'登録'
					JvUtil.clickElement('Page_MeetingOfDetails/Task_Add_Dalog/taskDalog_btn_submit');
				}
			}
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error 議題追加失敗")
		}
	}

	/**
	 * Function 資料に添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */
	def static addMaterialsFile(String outerTagName) {

		String fileType = "";

		switch (outerTagName) {

			case C_Object.C_招集通知テンプレート:
				WebUiBuiltInKeywords.comment('招集通知テンプレートの場合')
				fileType = "招集通知テンプレート.docx";
				break
			case C_Object.C_招集通知書:
				WebUiBuiltInKeywords.comment('招集通知書の場合')
				fileType = "招集通知書.docx";
				break
			case C_Object.C_書面決議提案書テンプレート:
				WebUiBuiltInKeywords.comment('書面決議提案書テンプレートの場合')
				fileType = "書面決議提案書テンプレート.docx";
				break
			case C_Object.C_書面決議提案書:
				WebUiBuiltInKeywords.comment('書面決議提案書の場合')
				fileType = "書面決議提案書.docx";
				break
			case C_Object.C_議事録テンプレート:
				WebUiBuiltInKeywords.comment('議事録テンプレートの場合')
				fileType = "書面取締役会議事録テンプレート.docx";
				break
			case C_Object.C_議事録押印有り:
				WebUiBuiltInKeywords.comment('議事録押印有りの場合')
				fileType = "議事録_押印あり.docx";
				break
			case C_Object.C_議事録押印無し:
				WebUiBuiltInKeywords.comment('議事録押印無しの場合')
				fileType = "議事録_押印なし.docx";
				break
			default:
				fileType = 'ファイル未確定'
				break
		}

		try {
			WebUiBuiltInKeywords.uploadFile(findTestObject(SK_Object.SK_資料ファイル_追加),
					RunConfiguration.getProjectDir() + "\\File\\" + fileType)
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error " + fileType + "の添付ファイルが追加されない。")
		}
	}

	/**
	 * Function Dialogに添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */
	def static addDialogFile(String outerTagName) {

		String fileType = "";

		switch (outerTagName) {
			case C_Object.C_確認書テンプレート:
				WebUiBuiltInKeywords.comment('確認書テンプレートの場合')
				fileType = "確認書テンプレート.docx";
				break
			case C_Object.C_確認書押印無し:
				WebUiBuiltInKeywords.comment('確認書押印無しの場合')
				fileType = "確認書_押印なし.docx";
				break
			case C_Object.C_確認書押印有り:
				WebUiBuiltInKeywords.comment('確認書押印有りの場合')
				fileType = "確認書_押印あり.docx";
				break
			case C_Object.C_同意書テンプレート:
				WebUiBuiltInKeywords.comment('同意書テンプレートの場合')
				fileType = "同意書テンプレート.docx";
				break
			case C_Object.C_同意書押印無し:
				WebUiBuiltInKeywords.comment('同意書押印無しの場合')
				fileType = "同意書_押印なし.docx";
				break
			case C_Object.C_同意書押印有り:
				WebUiBuiltInKeywords.comment('同意書押印有りの場合')
				fileType = "同意書_押印あり.docx";
				break
			default:
				fileType = 'ファイル未確定'
				break
		}

		try {
			WebUiBuiltInKeywords.uploadFile(findTestObject(SK_Object.SK_Dialog_添付ファイル),
					RunConfiguration.getProjectDir() + "\\File\\" + fileType)
		}
		catch (Exception e) {
			KeywordUtil.markFailed("JV Error " + fileType + "の添付ファイルが追加されない。")
		}
	}


	/**
	 * Function 添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static materialsFileUpdate(String outerTagName) {

		String fileType = "";

		'DropDownをクリック'
		JvUtil.clickElement(SK_Object.SK_資料_DropDown,C_Object.C_可见可点击);

		switch (outerTagName) {

			case C_Object.C_招集通知テンプレート:
				WebUiBuiltInKeywords.comment('招集通知テンプレートの場合')
				JvUtil.clickElement(C_Object.C_招集通知テンプレート);
				break
			case C_Object.C_招集通知書:
				WebUiBuiltInKeywords.comment('招集通知書の場合')
				JvUtil.clickElement(C_Object.C_招集通知書);
				break
			case C_Object.C_資料:
				WebUiBuiltInKeywords.comment('資料しの場合')
				JvUtil.clickElement(SK_Object.SK_資料_資料);
				break
			case C_Object.C_書面決議提案書テンプレート:
				WebUiBuiltInKeywords.comment('書面決議提案書テンプレートの場合')
				JvUtil.clickElement(C_Object.C_書面決議提案書テンプレート);
				break
			case C_Object.C_書面決議提案書:
				WebUiBuiltInKeywords.comment('書面決議提案書の場合')
				JvUtil.clickElement(SK_Object.SK_資料_書面決議提案書);
				break
			case C_Object.C_議事録テンプレート:
				WebUiBuiltInKeywords.comment('議事録テンプレートの場合')
				JvUtil.clickElement(C_Object.C_議事録テンプレート);
				break
			case C_Object.C_議事録押印有り:
				WebUiBuiltInKeywords.comment('議事録押印有りの場合')
				JvUtil.clickElement(SK_Object.SK_資料_議事録押印有り);
				break
			case C_Object.C_議事録押印無し:
				WebUiBuiltInKeywords.comment('議事録押印無しの場合')
				JvUtil.clickElement(SK_Object.SK_資料_議事録押印無し);
				break
			default:
				fileType = 'ファイル未確定'
				break
		}

		WebUiBuiltInKeywords.delay(2)
		'資料ファイルを添付'
		this.addMaterialsFile(outerTagName)

		WebUiBuiltInKeywords.delay(2)
		'資料ファイルのアップロード'
		JvUtil.clickElement(SK_Object.SK_資料ファイル_アップロード);
	}

	/**
	 * Function 添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */
	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static dialogFileUpdate(String outerTagName) {

		'DropDownをクリック'
		JvUtil.clickElement(SK_Object.SK_Dialog_DropDown);

		switch (outerTagName) {
			case C_Object.C_同意書押印有り:
				WebUiBuiltInKeywords.comment('同意書押印有りの場合')
				'同意書押印有り'
				JvUtil.clickElement(SK_Object.SK_Dialog_同意書押印有り);
				break
			case C_Object.C_同意書押印無し:
				WebUiBuiltInKeywords.comment('同意書押印無しの場合')
				'同意書押印無し'
				JvUtil.clickElement(SK_Object.SK_Dialog_同意書押印無し);
				break
			case C_Object.C_確認書押印有り:
				WebUiBuiltInKeywords.comment('確認書押印有りの場合')
				'確認書押印有り'
				JvUtil.clickElement(SK_Object.SK_Dialog_確認書押印有り);
				break
			case C_Object.C_確認書押印無し:
				WebUiBuiltInKeywords.comment('確認書押印無しの場合')
				'確認書押印無し'
				JvUtil.clickElement(SK_Object.SK_Dialog_確認書押印無し);
				break
			default:
				break
		}

		WebUiBuiltInKeywords.delay(2)
		'押印ファイルを添付'
		this.addDialogFile(outerTagName)

		WebUiBuiltInKeywords.delay(2)
		'押印ファイルのアップロード'
		JvUtil.clickElement(SK_Object.SK_Dialog_ファイルのアップロード);

		WebUiBuiltInKeywords.delay(2)
		'閉じる'
		JvUtil.clickElement(SK_Object.SK_Dialog_閉じる);
	}

	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static　getStatus(String outerTagName){
		WebElement element = WebUiBuiltInKeywords.findWebElement(findTestObject('Page_MeetingOfDetails/SK_Component/SK_Status'), 30)
		String status = element.getAttribute("value");
		WebUiBuiltInKeywords.comment("今のステータスは＜" + status + "＞です。")
		return status
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
			SK_Object.SK_資料_DropDown,
			SK_Object.SK_資料_議事録押印無し
		];

		for (int i=0; i<opts.length; i++) {
			JvUtil.clickElement(opts[i])
		}

		'議事録押印無しのファイルをアップロード'
		this.materialsFileUpdate(C_Object.C_議事録押印無し);
		'議事録押印無し登録'
		JvUtil.clickElement(SK_Object.SK_議事録押印無し登録)
		'議事録押印無し確認_はいボタン'
		JvUtil.clickElement(SK_Object.SK_議事録押印無し確認_はいボタン)
		'議事録押印有りのファイルをアップロード'
		this.materialsFileUpdate(C_Object.C_議事録押印有り);
		'完了'
		JvUtil.clickElement(SK_Object.SK_完了ボタン)
		'完了確認_完了ボタン'
		JvUtil.clickElement(SK_Object.SK_完了確認_完了ボタン)
	}


	/**
	 * Function 同意書と確認書の押印有り
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */
	def static updateFileInHave(){
		try {
			for (int i = 1; i <= 4; i++) {
				String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
				WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
				WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
				String jobName = jobelement.getAttribute("value")

				if (jobName == C_Object.C_取締役) {

					'アップロードをクリック'
					WebUiBuiltInKeywords.waitForElementVisible(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'), 20)
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'))
					WebUiBuiltInKeywords.delay(3)

					dialogFileUpdate(C_Object.C_同意書押印有り)
				}
				if (jobName == C_Object.C_監査役) {

					//TestObject -> 確認書
					WebUiBuiltInKeywords.delay(3)
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'))
					WebUiBuiltInKeywords.delay(3)

					dialogFileUpdate(C_Object.C_確認書押印有り)
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace()
		}

		'全てアップロードのチェック'
		try {
			for (int i = 1; i <= 4; i++) {
				String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
				WebUiBuiltInKeywords.comment("ObjectStr " + jobStr)
				WebElement jobelement = WebUiBuiltInKeywords.findWebElement(findTestObject(jobStr), 20)
				"取締役　or 監査役"
				String jobName = jobelement.getAttribute("value")

			}
		}
		catch (Exception e) {
			e.printStackTrace()
		}
	}

	@CompileStatic
	@Keyword(keywordObject = StringConstants.KW_CATEGORIZE_ELEMENT)
	def static fileUpdate(){
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

					dialogFileUpdate(C_Object.C_同意書押印無し)
				}
				if (jobName == "監査役") {

					//TestObject -> 確認書
					WebUiBuiltInKeywords.delay(3)
					WebUiBuiltInKeywords.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_upload'))
					WebUiBuiltInKeywords.delay(3)

					dialogFileUpdate(C_Object.C_確認書押印無し)
				}
			}
		}
		catch (WebElementNotFoundException we) {
			we.printStackTrace()
		}
		catch (org.openqa.selenium.StaleElementReferenceException se) {
			se.printStackTrace()
		}
		catch (Exception e) {
			e.printStackTrace()
		}
		finally {
			WebUiBuiltInKeywords.comment('fileUpdate  同意書、確認書完了')
		}
	}

	def jvWrite(){

		//String alertText = WebUI.getAlertText()
		//WebUI.comment("alertText " + alertText)
		//WebUI.verifyMatch(alertText, 'This is an alert', false)

		//		KeywordUtil.logInfo("Clicking element")
		//		element.click()
		//		KeywordUtil.markPassed("Element has been clicked")
		//	    } catch (WebElementNotFoundException e) {
		//		KeywordUtil.markFailed("Element not found")
		//	    } catch (Exception e) {
		//		KeywordUtil.markFailed("Fail to click on element")

		//	List<WebElement> listElement = WebUI.findWebElements(findTestObject('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_R'+i+'_upLoad'), 20)
		//	String jobName = jobelement.getAttribute("value")
		//	listElement.

		//TestObject -> 同意書
		//String xpath1 = '//div[@data-control-name="MD_書_役員ギャラリー"]//div[@aria-posinset="2"]//div[text()="アップロード"]'
		//TestObject to1 = new TestObject('objectname')
		//to1.addProperty('xpath', ConditionType.EQUALS, xpath1)

	}

	/**
	 * Refresh browser
	 */
	@Keyword
	def static setMeetName(String meetName) {
		KeywordUtil.logInfo("MeetName " + meetName)
		try {
			'会議名を入力'
			WebUiBuiltInKeywords.setText(findTestObject(L_Object.L_会議名), GlobalVariable.G_MeetName)
		} catch (Exception e) {
			e.printStackTrace()
			return false
		}
		return true
	}

	/**
	 * Refresh browser
	 */
	@Keyword
	def static WebDriverCheck() {
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
}