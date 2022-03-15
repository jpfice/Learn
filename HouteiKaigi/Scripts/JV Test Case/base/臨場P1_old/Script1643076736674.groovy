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
import com.jv.constants.L_Object
import com.jv.constants.C_Object
import com.jv.constants.SK_Object

WebUI.comment('自動化テスト：法定会議システム　臨場P1 起動')

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
WebUI.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : '1'], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 1)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_MeetingList/base_iframe'), 20)

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
// Top　－＞　事務局01再登録
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
WebUI.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)

'事務局１の議題登録'
WebUI.callTestCase(findTestCase('Common Test Cases/TaskInputDepend'), [('MeetType') : '2'], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

WebUI.switchToFrame(findTestObject('Page_MeetingOfDetails/bastiframe/iframe__fullscreen-app-host'), 1)
WebUI.delay(5)

//------------------------------------------------------------------------------------------------------------
// To -> 招集通知送付待ちへ
//------------------------------------------------------------------------------------------------------------
'招集通知送付待ちへ'
JvUtil.clickElement(L_Object.L_招集通知送付待ちへ);
'議題回答率_はいボタン'
JvUtil.clickElement(L_Object.L_回答率100);
'DropDownをクリック'
JvUtil.clickElement(L_Object.L_資料_DropDown);
'招集通知を選択'
JvUtil.clickElement(L_Object.L_資料_招集通知);
'招集通知ファイルを追加'
WebUI.delay(3)
WebUI.uploadFile(findTestObject(L_Object.L_添付ファイル), 'C:\\soft\\file\\確認書_押印あり.docx')
'ファイルをアップロード'
WebUI.delay(3)
JvUtil.clickElement(L_Object.L_ファイルをアップロード);
'招集通知送付'
JvUtil.clickElement(L_Object.L_招集通知送付);
'送信ボタン_臨場'
JvUtil.clickElement(L_Object.L_送信ボタン_臨場);
'資料送付待ちへボタン'
JvUtil.clickElement(L_Object.L_資料送付待ちへ);
'資料送付待ちステータスにはいボタン'
JvUtil.clickElement(L_Object.L_資料送付待ちへ_はいボタン);
'DropDownをクリック'
JvUtil.clickElement(L_Object.L_資料_DropDown);
'資料を選択'
WebUI.delay(3)
JvUtil.clickElement(L_Object.L_資料);
'資料ファイルを追加'
WebUI.delay(3)
WebUI.uploadFile(findTestObject(L_Object.L_添付ファイル), 'C:\\soft\\file\\確認書_押印あり.docx')
'ファイルをアップロード'
WebUI.delay(3)
JvUtil.clickElement(L_Object.L_ファイルをアップロード);
WebUI.delay(3)
'資料送付'
JvUtil.clickElement(L_Object.L_資料送付);

'MR_送信ボタン_臨場'
JvUtil.clickElement(L_Object.L_送信ボタン_臨場);

'MD_開催しましたボタン'
JvUtil.clickElement(L_Object.L_開催しましたボタン);

'MD_開催しました_はいボタン'
JvUtil.clickElement(L_Object.L_開催しました_はいボタン);

'DropDownをクリック'
JvUtil.clickElement(L_Object.L_資料_DropDown);

'DropDown議事録押印無'
JvUtil.clickElement(L_Object.L_DropDown議事録押印無);

'資料ファイルを追加'
WebUI.delay(5)

WebUI.uploadFile(findTestObject(L_Object.L_添付ファイル), 'C:\\soft\\file\\確認書_押印あり.docx')

'ファイルをアップロード'
WebUI.delay(7)
JvUtil.clickElement(L_Object.L_ファイルをアップロード);

WebUI.delay(7)
'MD_議事録押印無登録ボタン'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/btn_TaskIn'))

//------------------------------------------------------------------------------------------------------------
// To -> 押印依頼中ステータスに進んでよろしいですか？
//------------------------------------------------------------------------------------------------------------
'MD_議事録押印無し確認_はいボタン'
WebUI.click(findTestObject('Page_MeetingOfDetails/L_Component/Alert/btn_TaskIn_Yes'))

'DropDownをクリック'
JvUtil.clickElement(L_Object.L_資料_DropDown);

'DropDown議事録押印有'
JvUtil.clickElement(L_Object.L_DropDown議事録押印有);

'資料ファイルを追加'
WebUI.delay(5)
WebUI.uploadFile(findTestObject(L_Object.L_添付ファイル), 'C:\\soft\\file\\確認書_押印あり.docx')

JvUtil.clickElement(L_Object.L_ファイルをアップロード);
WebUI.delay(5)
'完了'
JvUtil.clickElement(L_Object.L_完了ボタン);
'取締完了'
JvUtil.clickElement(L_Object.L_取締完了ボタン);

WebUI.comment('自動化テスト：法定会議システム　臨場P1 終了')

