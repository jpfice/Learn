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

'Browser起動'
WebUI.openBrowser(GlobalVariable.G_BaseUrl)
'窓口最大化'
WebUI.maximizeWindow()
'Userを入力'
WebUI.setText(findTestObject('AccountLogin/Page_/input__loginfmt'), Username)
'登録ボタン'
WebUI.click(findTestObject('AccountLogin/Page_/input__idSIButton9'))

WebUI.click(findTestObject('AccountLogin/Page_- Online Service Gate/section_OSG'))
'大文字に変換'
WebUI.setText(findTestObject('AccountLogin/Page_- Online Service Gate/input__Upn'), Username)

WebUI.click(findTestObject('AccountLogin/Page_- Online Service Gate/html_- Online Service Gate'))
'パスワードを入力'
WebUI.setEncryptedText(findTestObject('AccountLogin/Page_- Online Service Gate/input__UpnPassword'), Password)

WebUI.click(findTestObject('AccountLogin/Page_- Online Service Gate/input__mailSignIn'))

WebUI.click(findTestObject('AccountLogin/Page_/input__DontShowAgain'))
'ログイン'
WebUI.click(findTestObject('AccountLogin/Page_/input__idSIButton9'))

