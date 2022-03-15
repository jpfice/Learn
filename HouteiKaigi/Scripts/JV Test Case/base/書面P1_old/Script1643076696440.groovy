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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
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

WebUI.comment('自動化テスト：法定会議システム　書面P1 起動')

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
WebUI.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : '2'], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Page_MeetingOfDetails/bastiframe/iframe__fullscreen-app-host'), 1)

//------------------------------------------------------------------------------------------------------------
// To -> 議題詳細
//------------------------------------------------------------------------------------------------------------
String[] Y1 = [C_Object.C_議題あり, C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_事務局テスト０１,C_Object.C_資料添付_なし, C_Object.C_Submit_登録]
String[] Y2 = [C_Object.C_議題追加, C_Object.C_議題区分_報告, "T2", C_Object.C_資料登録者_議題登録者テスト０１,C_Object.C_資料添付_あり, C_Object.C_Submit_登録]
//String[] Y3 = [C_Object.C_議題追加, C_Object.C_議題区分_討議, "T3", C_Object.C_資料登録者_議題登録者テスト０２,C_Object.C_資料添付_あり, C_Object.C_Submit_登録]
//String[] Y4 = [C_Object.C_議題追加, C_Object.C_議題区分_決議, "T4", C_Object.C_資料登録者_事務局テスト０２,C_Object.C_資料添付_なし, C_Object.C_Submit_閉じる]

'議題追加'
JvOpt.addTask(C_Object.C_議題あり, C_Object.C_議題区分_決議, "T1", C_Object.C_資料登録者_事務局テスト０１,C_Object.C_資料添付_なし, C_Object.C_Submit_登録)
WebUI.closeBrowser()

WebUI.comment("会議名 "+ GlobalVariable.G_MeetName)
JvOpt.reStart(GlobalVariable.G_User_J02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()

JvOpt.reStart(GlobalVariable.G_User_G01, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()

JvOpt.reStart(GlobalVariable.G_User_G02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()

//------------------------------------------------------------------------------------------------------------
// Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
WebUI.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)

//------------------------------------------------------------------------------------------------------------
// To -> 事務局１の議題登録
//------------------------------------------------------------------------------------------------------------
'事務局１の議題登録'
WebUI.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : '2'], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Page_MeetingOfDetails/bastiframe/iframe__fullscreen-app-host'), 1)
WebUI.delay(5)

//------------------------------------------------------------------------------------------------------------
// To -> 招集通知送付待ちへ
//------------------------------------------------------------------------------------------------------------
'提案書送付待ちへ'
JvUtil.clickElement(SK_Object.SK_提案書送付待ちへ)

'提案書送付確認_はいボタン'
JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_btn_task_file_update_YES')
WebUI.delay(8)

while(CustomKeywords.'com.jv.key.JvUtil.checkAnswerType'("S_Component" , "ボタン回答")) {

	for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
		try {
			String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
			WebUI.comment("ObjectStr " + jobStr)
			WebElement jobelement = WebUI.findWebElement(findTestObject(jobStr), 20)
			String jobName = jobelement.getAttribute("value")
			
			String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
			WebUI.comment("answerTypeStr " + answerTypeStr)
			WebElement answerTypeElement = WebUI.findWebElement(findTestObject(answerTypeStr), 20)
			String answerType = answerTypeElement.getText()
			
			WebUI.comment("第"+i+"行 jobName " + jobName + " AnswerType " + answerType)
			
			if (answerType == "ボタン回答") {
				WebUI.waitForElementClickable(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_ansower'+ i), 20)
				WebUI.enhancedClick(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_ansower'+ i))
				'同意書を選択'
				WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_radio_agree'))
				'回答方法を登録'
				WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_alert_btn_ansowe_save'))
				WebUI.delay(3)
				break
			}
		}catch (Exception e) {
			e.printStackTrace()
		}
	}
}

//Role 取締役/監査役
JvOpt.getfileUpdate()
WebUI.delay(5)

'UpLoad　書面決議提案書'
JvOpt.materialsFileUpdate(C_Object.C_書面決議提案書)

'提案書送付'
JvUtil.clickElement(SK_Object.SK_提案書送付)
WebUI.delay(5)

for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
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
WebUI.delay(8)

'異議なし'
for (int i=1; i<=GlobalVariable.G_JobCount; i++) {
	
	String jobStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_btn_R'+ i +'_job'
	WebUI.comment("ObjectStr " + jobStr)
	WebElement jobelement = WebUI.findWebElement(findTestObject(jobStr), 20)
	String jobName = jobelement.getAttribute("value")
	
	String answerTypeStr = 'Page_MeetingOfDetails/S_Component/S_Job_R'+ i +'/S_text_answerType'+ i
	WebUI.comment("answerTypeStr " + answerTypeStr)
	WebElement answerTypeElement = WebUI.findWebElement(findTestObject(answerTypeStr), 20)
	String answerType = answerTypeElement.getText()
	
	if(jobName == "監査役" && answerType == "同意書") {
		WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i))
		WebUI.delay(2)
		WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_Common/S_diff_no'))
		WebUI.delay(3)
	}
	
	if(jobName == "取締役" && answerType == "同意書") {
		WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_R'+i+'/S_dropdown_meanshow' + i))
		WebUI.delay(2)
		WebUI.click(findTestObject('Page_MeetingOfDetails/S_Component/S_Job_Common/S_agree_have'))
		WebUI.delay(3)
	}
	
}

JvOpt.updateFileInHave()

'可決報告'
JvOpt.canReportBook()

WebUI.comment('自動化テスト：法定会議システム　書面P1 終了')

