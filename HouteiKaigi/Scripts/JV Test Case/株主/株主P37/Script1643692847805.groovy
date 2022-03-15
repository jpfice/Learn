import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import com.jv.key.JvUtil as JvUtil
import com.jv.key.JvOpt as JvOpt
import com.jv.key.JvKOpt as JvKOpt
import com.jv.constants.C_Object as C_Object
import com.jv.constants.L_Object as L_Object
import com.jv.constants.SK_Object as SK_Object

WebUI.comment('自動化テスト：法定会議システム　株主P37 起動')

//------------------------------------------------------------------------------------------------------------
// Page　－＞　アカウントログイン、Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
JvOpt.accountLogin();

//------------------------------------------------------------------------------------------------------------
// Page －＞　議題登録依頼
//------------------------------------------------------------------------------------------------------------
'議題登録依頼'
JvOpt.taskInputDepend(C_Object.C_株主)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （議題・資料登録待ち）議題詳細
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvKOpt.autoRemind(C_Object.C_ON)

'会議情報更新ボタン'
JvKOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'議題あり AND 議題追加'
JvOpt.taskInput(C_Object.C_新ユーザー議題追加, "4")

'アカウントログイン'
JvOpt.accountLogin();

'議題登録依頼'
JvOpt.meetList(GlobalVariable.G_MeetName)

'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "1")

'提案書送付待ちへ'
JvUtil.clickElement(SK_Object.SK_提案書送付待ちへ)

'提案書送付確認_はいボタン'
JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_btn_task_file_update_YES')
WebUI.delay(5)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （書面決議提案書送付待ち）会議詳細
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvKOpt.autoRemind(C_Object.C_ON)

'会議情報更新ボタン'
JvKOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'議題追加'
JvOpt.taskInput(C_Object.C_議題追加, "1")

'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "1")

'UpLoad　同意書押印無し'
String[] fileList = [C_Object.C_同意書押印無し, C_Object.C_同意書押印無し];
JvKOpt.agreeBook(fileList);

'UpLoad　書面決議提案書'
JvOpt.materialsFileUpdate(C_Object.C_書面決議提案書)

'提案書送付へ'
JvUtil.clickElement(SK_Object.SK_提案書送付)

WebUI.delay(5)
'CheckBox パスワード'
for (int i=1; i<=2; i++) {
	try {
		String mailPassWordStr = 'Page_MeetingOfDetails/S_Component/S_checkbox_mailPassWord'+ i
		WebUI.comment("mailPassWordStr " + mailPassWordStr)
		WebUI.click(findTestObject(mailPassWordStr))
	}catch (Exception e) {
		e.printStackTrace()
	}
}

'スキップボタン'
//JvKOpt.commontBtn(C_Object.C_スキップ)

'送信'
JvUtil.clickElement(SK_Object.SK_書面決議提案書_送信ボタン)
WebUI.delay(5)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （決議待ち）会議詳細
//------------------------------------------------------------------------------------------------------------
'同意 or 不同意'
String[] agreeList = [C_Object.C_同意, C_Object.C_同意];
JvKOpt.meanShow(agreeList);

'資料'
//JvOpt.materialsFileUpdate(C_Object.C_資料)

'提案書再送送付'


'可決報告'
JvKOpt.ReportBook(C_Object.C_可決報告);

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （議事録作成中）会議詳細
//------------------------------------------------------------------------------------------------------------
'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "2")
'議事録押印無しのファイルをアップロード'
JvOpt.materialsFileUpdate(C_Object.C_議事録押印無し);
'議事録押印無し登録'
JvUtil.clickElement(SK_Object.SK_議事録押印無し登録)
'議事録押印無し確認_はいボタン'
JvUtil.clickElement(SK_Object.SK_議事録押印無し確認_はいボタン)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （押印依頼中）会議詳細
//------------------------------------------------------------------------------------------------------------
'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "3")
'議事録押印有りのファイルをアップロード'
JvOpt.materialsFileUpdate(C_Object.C_議事録押印有り);
'完了'
JvUtil.clickElement(SK_Object.SK_完了ボタン)
'完了確認_完了ボタン'
JvUtil.clickElement(SK_Object.SK_完了確認_完了ボタン)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （完了）会議詳細
//------------------------------------------------------------------------------------------------------------

WebUI.comment('自動化テスト：法定会議システム　株主P37 終了')