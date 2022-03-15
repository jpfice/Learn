import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointCell
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

WebUI.comment('自動化テスト：法定会議システム　書面P2 起動')

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

'ステータス取得'
JvOpt.getStatus()

WebUI.closeBrowser()



WebUI.comment("会議名 "+ GlobalVariable.G_MeetName)

JvOpt.reStart(GlobalVariable.G_User_J02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()

JvOpt.reStart(GlobalVariable.G_User_G01, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()

JvOpt.reStart(GlobalVariable.G_User_G02, GlobalVariable.G_PassWord, GlobalVariable.G_MeetName, Y2);
WebUI.closeBrowser()



WebUI.comment('自動化テスト：法定会議システム　書面P2 終了')

