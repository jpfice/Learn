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
import com.jv.key.JvSOpt as JvSOpt
import com.jv.key.JvOpt as JvOpt
import com.jv.constants.C_Object
import com.jv.constants.L_Object
import com.jv.constants.SK_Object

WebUI.comment('自動化テスト：法定会議システム　書面P17 起動')

party = "P17"

//------------------------------------------------------------------------------------------------------------
// Page　－＞　アカウントログイン、Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
JvOpt.accountLogin();

//------------------------------------------------------------------------------------------------------------
// Page －＞　議題登録依頼
//------------------------------------------------------------------------------------------------------------
'議題登録依頼'
JvOpt.taskInputDepend(C_Object.C_書面)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （議題・資料登録待ち）議題詳細
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvSOpt.autoRemind(C_Object.C_OFF)

'会議情報更新ボタン'
JvSOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'議題追加'
JvOpt.taskInput(C_Object.C_新ユーザー議題追加, "4")

'アカウントログイン'
JvOpt.accountLogin();

'議題登録依頼'
JvOpt.meetList(GlobalVariable.G_MeetName)

'提案書送付待ちへ'
JvUtil.clickClickAbleAndVisibleElement(SK_Object.SK_提案書送付待ちへ)

'提案書送付確認_はいボタン'
JvUtil.clickElement('Page_MeetingOfDetails/S_Component/S_btn_task_file_update_YES')
WebUI.delay(8)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （書面決議提案書送付待ち）議題詳細
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvSOpt.autoRemind(C_Object.C_OFF)

'会議情報更新ボタン'
JvSOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "1")

'役員部品の回答ボタンが全て「同意書」に変更'
JvSOpt.changeToAgreeBook();

//Role 取締役/監査役
JvOpt.fileUpdate()
WebUI.delay(5)

'UpLoad　書面決議提案書'
JvOpt.materialsFileUpdate(C_Object.C_書面決議提案書)

'提案書送付'
JvUtil.clickElement(SK_Object.SK_提案書送付)
WebUI.delay(5)

'メールのパスワード設定'
JvSOpt.emailPassWordCheck()

'送信'
JvUtil.clickElement(SK_Object.SK_書面決議提案書_送信ボタン)
WebUI.delay(20)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （決議待ち）議題詳細
//------------------------------------------------------------------------------------------------------------

'異議なし'
JvSOpt.allAgree()

'同意書と確認書の押印有り'
JvOpt.updateFileInHave()

'提案書再送'

'資料'
//JvOpt.materialsFileUpdate(C_Object.C_資料)

'可決報告'
JvSOpt.ReportBook(C_Object.C_可決報告)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （議事録作成中）議題詳細
//------------------------------------------------------------------------------------------------------------

'議事録押印無しのファイルをアップロード'
JvOpt.materialsFileUpdate(C_Object.C_議事録押印無し);
'議事録押印無し登録'
JvUtil.clickElement(SK_Object.SK_議事録押印無し登録)
'議事録押印無し確認_はいボタン'
JvUtil.clickElement(SK_Object.SK_議事録押印無し確認_はいボタン)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （押印依頼中）議題詳細
//------------------------------------------------------------------------------------------------------------

'議事録押印有りのファイルをアップロード'
JvOpt.materialsFileUpdate(C_Object.C_議事録押印有り);
'完了'
JvUtil.clickElement(SK_Object.SK_完了ボタン)
'完了確認_完了ボタン'
JvUtil.clickElement(SK_Object.SK_完了確認_完了ボタン, C_Object.C_可点击)

WebUI.comment('自動化テスト：法定会議システム　書面P17 終了')

