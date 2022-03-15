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
import com.jv.key.JvLOpt as JvLOpt
import com.jv.constants.L_Object
import com.jv.constants.C_Object
import com.jv.constants.SK_Object

'パターン番号'
String CaseNumber = "P1"

'起動ログ'
JvLOpt.log(CaseNumber, C_Object.C_起動);
//------------------------------------------------------------------------------------------------------------
// Page　－＞　アカウントログイン、Top画面へ遷移
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
JvOpt.accountLogin();

//------------------------------------------------------------------------------------------------------------
// Page －＞　議題登録依頼
//------------------------------------------------------------------------------------------------------------
'議題登録依頼'
JvOpt.taskInputDepend(C_Object.C_臨場)

//------------------------------------------------------------------------------------------------------------
// Page －＞　 （議題登録依頼中）議題詳細
//------------------------------------------------------------------------------------------------------------
'議題追加'
JvOpt.taskInput(C_Object.C_新ユーザー議題追加, "4")

//------------------------------------------------------------------------------------------------------------
// Step　－＞　事務局01再登録
//------------------------------------------------------------------------------------------------------------
'アカウントログイン'
JvOpt.accountLogin();

'議題登録依頼'
JvOpt.meetList(GlobalVariable.G_MeetName)

'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "1")

'開催中止ボタン'
JvLOpt.meetStop("");

'招集通知送付待ちへ'
JvLOpt.conveneInformSendWait(C_Object.C_はい)
//------------------------------------------------------------------------------------------------------------
// Status －＞　会議詳細（招集通知送付待ち)
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvLOpt.autoRemind(C_Object.C_OFF)

'会議情報更新ボタン'
JvLOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'リマインド送信ボタン'
JvLOpt.remindSend(L_Object.L_リマインド送信)

'議題追加ボタン'
JvOpt.taskInput(C_Object.C_議題追加, "1")

'議題編集ボタン'
JvOpt.taskInput(C_Object.C_議題編集, "1")
//------------------------------------------------------------------------------------------------------------
//Step 資料 －＞ 招集通知テンプレート と　招集通知の添付
//------------------------------------------------------------------------------------------------------------
'招集通知テンプレート'

'招集通知の添付'
JvLOpt.fileUpdate(C_Object.C_招集通知書);

'開催中止ボタン'
JvLOpt.meetStop("");

'招集通知送付へ'
JvLOpt.conveneInformSend(C_Object.C_送信)
//------------------------------------------------------------------------------------------------------------
//Status 会議詳細（資料登録待ち）
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvLOpt.autoRemind(C_Object.C_OFF)

'会議情報更新ボタン'
JvLOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'リマインド送信ボタン'
JvLOpt.remindSend(L_Object.L_リマインド送信)

'議題追加'
JvOpt.taskInput(C_Object.C_議題追加, "1")
'議題編集'
JvOpt.taskInput(C_Object.C_議題編集, "1")

'招集通知修正ボタン'
JvLOpt.conveneInformUpdate(C_Object.C_送信)

'資料送付待ちへボタン'
JvLOpt.materialSendWait(C_Object.C_はい)

//------------------------------------------------------------------------------------------------------------
//Status 会議詳細（資料送付待ち）
//------------------------------------------------------------------------------------------------------------
'自動リマインド'
JvLOpt.autoRemind(C_Object.C_OFF)

JvOpt.taskInput(C_Object.C_議題編集, "1")

'会議情報更新ボタン'
JvLOpt.meetInfoUpdate(L_Object.L_会議情報更新);

'リマインド送信ボタン'
JvLOpt.remindSend(L_Object.L_リマインド送信)

'資料の添付'
JvLOpt.fileUpdate(C_Object.C_資料);

'ロックボタン'
JvLOpt.lock(C_Object.C_ロックボタン)

'ロック解除ボタン'
JvLOpt.lock(C_Object.C_ロック解除ボタン)

'開催中止ボタン'
JvLOpt.meetStop("");

'資料送付'
JvLOpt.materialSend(L_Object.L_資料送付)

'招集通知修正'
JvLOpt.conveneInformUpdate(C_Object.C_送信)

//------------------------------------------------------------------------------------------------------------
//Status 会議詳細（会議開催待ち）
//------------------------------------------------------------------------------------------------------------

'資料再送ボタン'
JvLOpt.materialAgainSend(C_Object.C_送信)

'パスワードメール送付  チェックOn or チェックOff'
JvLOpt.passwordCheck(C_Object.C_ON)

'送信'
JvLOpt.commontBtn(C_Object.C_送信)

'開催しましたボタン'
JvLOpt.openMeet(C_Object.C_いいえandはい)
//------------------------------------------------------------------------------------------------------------
//Status 会議詳細（議事録作成中））
//------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------
//Step 資料  －＞  議事録押印無しの添付
//------------------------------------------------------------------------------------------------------------
'参加者'
JvLOpt.participants("1", C_Object.C_ON)

'ロックボタン'
JvLOpt.lock(C_Object.C_ロックボタン)

'ロック解除ボタン'
JvLOpt.lock(C_Object.C_ロック解除ボタン)

'議事録押印無しの添付'
JvLOpt.fileUpdate(C_Object.C_議事録押印無し);

'議事録押印無登録ボタン'
JvLOpt.hansardInNotLand(C_Object.C_はい);
//------------------------------------------------------------------------------------------------------------
// Step 資料  －＞  議事録押印有りの添付
//------------------------------------------------------------------------------------------------------------
'議事録押印有り'
JvLOpt.fileUpdate(C_Object.C_議事録押印有り);

'参加者'
JvLOpt.participants("2", C_Object.C_ON)

'ロックボタン'
JvLOpt.lock(C_Object.C_ロックボタン)

'ロック解除ボタン'
JvLOpt.lock(C_Object.C_ロック解除ボタン)

'完了'
JvLOpt.over(C_Object.C_いいえandはい)

'終了ログ'
JvLOpt.log(CaseNumber, C_Object.C_終了);

