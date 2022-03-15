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
import com.jv.constants.C_Object
import com.jv.constants.SK_Object


WebUI.comment('自動化テスト：法定会議システム　株主P1 起動')

WebUI.comment('Top画面へ遷移')

//------------------------------------------------------------------------------------------------------------
// Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
WebUI.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)

//------------------------------------------------------------------------------------------------------------
// To -> 事務局１の議題登録
//------------------------------------------------------------------------------------------------------------
'事務局１の議題登録'
WebUI.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : '3'], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 1)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 20)

//------------------------------------------------------------------------------------------------------------
// To -> 議題詳細
//------------------------------------------------------------------------------------------------------------

'議題追加'
JvOpt.addTask("議題追加")

'提案書送付待ちへ'
JvUtil.clickElement(SK_Object.SK_提案書送付待ちへ)

'提案書送付確認_はいボタン'
JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_btn_task_file_update_YES')
WebUI.delay(5)

'UpLoad　同意書押印無し'
String[] fileList = [C_Object.C_同意書押印無し, C_Object.C_同意書押印無し];
for(int i=1; i<=fileList.length; i++) {
	'アップロードボタン'
	JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_R'+i+'_upLoad')
	JvOpt.dialogFileUpdate(fileList[i-1]);
}

'UpLoad　書面決議提案書'
JvOpt.materialsFileUpdate(C_Object.C_書面決議提案書)

'提案書送付'
JvUtil.clickElement(SK_Object.SK_提案書送付)

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

'送信'
JvUtil.clickElement(SK_Object.SK_書面決議提案書_送信ボタン)
WebUI.delay(5)

'UpLoad　同意書押印有り'
String[] fileList1 = [C_Object.C_同意書押印有り, C_Object.C_同意書押印有り];
for(int i=1; i<=fileList1.length; i++) {
	'アップロードボタン'
	JvUtil.clickElement('Page_MeetingOfDetails/K_Component/K_Company_R'+i+'/K_R'+i+'_upLoad')
	JvOpt.dialogFileUpdate(fileList1[i-1]);
}

'可決報告'
JvOpt.canReportBook()

WebUI.comment('自動化テスト：法定会議システム　株主P1 終了')