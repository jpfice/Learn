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

WebUI.comment('自動化テスト：法定会議システム　起動')

WebUI.comment('Top画面へ遷移')

//------------------------------------------------------------------------------------------------------------
// Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
WebUI.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)

//------------------------------------------------------------------------------------------------------------
// Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
//WebUI.click(findTestObject("//div[text()=\"Dev開発チーム用会社1\"]"))
'会議一覧画面へ遷移'
WebUI.click(findTestObject('Page_Top/company01'))
WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 1)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 20)

CustomKeywords.'com.jv.key.JvUtil.getHtmlTableRows'(findTestObject('Page_MeetingList/table_meeting'), '臨場_20211222151930', 
    '議題登録依頼中')

//if (findTestObject('Page_MeetingOfDetails/L_Component/btn_MeetHave') != null) {
//if (WebUI.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSend'), 3)) {
//	if (WebUI.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_MeetHave'), 3)) {
//		'議題有り'
//		WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/btn_MeetHave'))
//		'議題'
//		WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), FailureHandling.STOP_ON_FAILURE)
//		WebUI.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), 'T1')
//		'なし'
//		//WebUI.click(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_radio_nohave'))
//		'登録'
//		WebUI.click(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_submit'))
//		//WebUI.delay(5)
//	}
//	else {
//		'議題追加'
//		WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/btn_taskAdd'))
//		'議題'
//		WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), FailureHandling.STOP_ON_FAILURE)
//		WebUI.setText(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_input_task_name'), 'T1')
//		'なし'
//		//WebUI.click(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_radio_nohave'))
//		'登録'
//		WebUI.click(findTestObject('Page_MeetingOfDetails/Task_Add_Dalog/taskDialog_btn_submit'))
//	}
//}
//WebUI.waitForPageLoad(5)


//if (WebUI.verifyElementVisible(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSendWait'))) {
//if (WebUI.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSendWait'), 3)) {
//	'招集通知送付待ちへ'
//	WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSendWait'))
//	'MD_M_議題回答率_はいボタン'
//	WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/Alert/btn_Ans100_YES'))
//}

//if (!WebUI.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_MsgTemplate'), 3)) {
//	'招集通知テンプレート作成'
//	WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/btn_MsgTemplate'))
//	WebUI.delay(5)
//}

'DropDownをクリック'
WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/dropdown_Materials'))
'招集通知を選択'
WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/dropdown_Msg'))
WebUI.delay(2)
'招集通知ファイルを追加'
WebUI.uploadFile(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/input_MaterialsAdd'), 'C:\\soft\\file\\確認書_あり.docx')
'ファイルをアップロード'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_MaterialsUpLoad'))
//WebUI.delay(5)

//if (WebUI.verifyElementPresent(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSend'), 3)) {
//	'招集通知送付'
//	WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_Msg_mailSend'))
//	WebUI.delay(3)
//}

'MR_送信ボタン_臨場'
//WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_LmailSend'))
'資料登録待ち->MD_資料送付待ちへボタン'
//WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_MaterialsSendWait'))

'資料送付待ちステータスにはいボタン'
//WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/Alert/btn_MsgSendWaitStatus_YES'))
//WebUI.delay(3)

'DropDownをクリック'
WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/dropdown_Materials'))
'資料を選択'
WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/dropdown_Mate'))
WebUI.delay(2)
'資料ファイルを追加'
WebUI.uploadFile(findTestObject('Page_MeetingOfDetails/L_Component/Materials_Module/input_MaterialsAdd'), 'C:\\soft\\file\\確認書_あり.docx')
'ファイルをアップロード'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_MaterialsUpLoad'))
'資料送付'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_MaterialsSend'))
'MR_送信ボタン_臨場'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_LmailSend'))
'MD_開催しましたボタン'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/Alert/btn_MeetingOver'))
WebUI.delay(5)

'議事録テンプレート'
'議事録押印なし'
'議事録押印無登録'


