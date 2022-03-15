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
import com.jv.constants.C_Object
import com.jv.constants.L_Object
import com.jv.constants.S_Object
import com.jv.constants.K_Object
import com.jv.constants.SK_Object
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import com.jv.key.JvUtil as JvUtil
import com.jv.key.JvOpt as JvOpt

WebUI.comment('議題登録依頼画面  起動')

def sdata = JvUtil.getSData()
Binding binding = new Binding(sdata);
GlobalVariable.putAt("sData", binding)

List<List<Object>> testData = findTestData('JvTestData_S').getAllData();

//------------------------------------------------------------------------------------------------------------
// To -> 議題登録依頼画面
//------------------------------------------------------------------------------------------------------------
if (!(CustomKeywords.'com.jv.key.JvDate.WebDriverCheck'())) {
    'アカウントログイン'
    WebUI.callTestCase(findTestCase('Common Test Cases/AccountLogin'), null, FailureHandling.STOP_ON_FAILURE)
}

//------------------------------------------------------------------------------------------------------------
// To -> 会議一覧画面
//------------------------------------------------------------------------------------------------------------
'会議一覧画面へ遷移'
JvUtil.clickElement('Page_Top/company01', C_Object.C_可点击)

//------------------------------------------------------------------------------------------------------------
// To -> 議題登録依頼画面
//------------------------------------------------------------------------------------------------------------
WebUI.comment('議題登録依頼画面へ遷移')

'議題登録依頼画面へ遷移'
JvOpt.pageForward(C_Object.C_議題登録依頼)

String meetname = ''

switch (MeetType) {
    //To　－＞　臨場の場合'
    case '1':

		'会議種類の選択'
		JvOpt.setMeetType(C_Object.C_臨場);
        '会議名を取得'
		JvOpt.getYmd(C_Object.C_臨場)
		'会議名を入力'
		JvOpt.setMeetName(GlobalVariable.G_MeetName)
		'開催日時Fromを入力'
		JvOpt.ymdSet(C_Object.C_開催日時From)
		'開催日時Toを入力'
		JvOpt.ymdSet(C_Object.C_開催日時To)
        '議題登録期限の設定'
		JvOpt.ymdSet(C_Object.C_議題登録期限)
		'資料提出期限の設定'
		JvOpt.ymdSet(C_Object.C_資料提出期限)

        'メール設定'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_mail_set'))

		WebUI.delay(3)
        '一時保存'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_temporary_save'))
		
		WebUI.delay(3)
		
		'自動リマインド'
		WebUI.click(findTestObject('Page_MeetingLandDepend/switch_autoRemaind'))

        '依頼あり'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_depend_have'))

        '閉じる'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_close'))

        '依頼あり'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_depend_have'))

        '続ける'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_continue'))

        break
    case '2':
	
        '会議種類の選択'
		JvOpt.setMeetType(C_Object.C_書面);
		
		'会議名取得'
		JvOpt.getYmd(C_Object.C_書面)
        
		'会議名を入力'
		JvOpt.setMeetName(GlobalVariable.G_MeetName)

		'提案日の設定'
		JvOpt.ymdSet(C_Object.C_提案日)
		
		'決議日の設定'
		JvOpt.ymdSet(C_Object.C_決議日)

        '議題登録期限の設定'
		JvOpt.ymdSet(C_Object.C_議題登録期限)

		'資料提出期限の設定'
		JvOpt.ymdSet(C_Object.C_資料提出期限)

		//jvClick("S_自動リマインド");
		'自動リマインド'
		WebUI.click(findTestObject('Page_MeetingLandDepend/switch_autoRemaind'))

		jvClick("S_メール設定"); jvWait(8);

		jvClick("S_一時保存");

		jvClick("S_議題登録依頼あり");

		jvClick("S_閉じる");

		jvClick("S_議題登録依頼あり");

		jvClick("S_続ける");
		
//		'依頼あり'
//		JvOpt.dependHaveOrNo(C_Object.C_依頼あり)

        break
    case '3':
	
		'会議種類の選択'
		JvOpt.setMeetType(C_Object.C_株主);
		
		'会議名取得'
		JvOpt.getYmd(C_Object.C_株主)
		
		'会議名を入力'
		JvOpt.setMeetName(GlobalVariable.G_MeetName)
		
		'提案日の設定'
		JvOpt.ymdSet(C_Object.C_提案日)
		
		'決議日の設定'
		JvOpt.ymdSet(C_Object.C_決議日)

		'議題登録期限の設定'
		JvOpt.ymdSet(C_Object.C_議題登録期限)

		'資料提出期限の設定'
		JvOpt.ymdSet(C_Object.C_資料提出期限)

        WebUI.comment('自動リマインドの選択')
		
		'自動リマインド'
		WebUI.click(findTestObject('Page_MeetingLandDepend/switch_autoRemaind'))

        'メール設定'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_mail_set'))
        jvWait(8)

        '一時保存'
        WebUI.click(findTestObject('Page_MeetingLandDepend/L_Present_meeting/L_btn_temporary_save'))
		jvWait(5)

		'依頼あり'
		JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have', C_Object.C_可见可点击)
		'閉じる'
		JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_close', C_Object.C_可见可点击)
		'依頼あり'
		JvUtil.clickElement('Page_MeetingLandDepend/S_Present_meeting/S_btn_depend_have', C_Object.C_可见可点击)
		'続ける'
		JvUtil.clickElement('Page_MeetingLandDepend/K_Present_meeting/K_btn_continue', C_Object.C_可见可点击)
		break
    default:
        meetname = '会議種類未確定'
        break
}


def jvClick(String sObject) {
	Binding binding = GlobalVariable.getAt("sData");
    WebUI.click(findTestObject(binding.getVariable(sObject).toString()))
}

def jvPresentCheck(String sObject){
	Binding binding = GlobalVariable.getAt("sData");
	WebUI.verifyElementPresent(findTestObject(binding.getVariable(sObject).toString()), 3)
}

def jvSetText(String sObject, String text) {
	Binding binding = GlobalVariable.getAt("sData");
	WebUI.setText(findTestObject(binding.getVariable(sObject).toString()), text)
}

def jvWait(Number second) {
	WebUI.delay(second)
}

