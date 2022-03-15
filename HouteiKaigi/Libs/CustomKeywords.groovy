
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.testobject.TestObject

import org.openqa.selenium.WebElement


 /**
	 * Function 可決報告と否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvKOpt.ReportBook"(
    	String ReportType	) {
    (new com.jv.key.JvKOpt()).ReportBook(
        	ReportType)
}

 /**
	 * Function 可決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvKOpt.canReportBook"() {
    (new com.jv.key.JvKOpt()).canReportBook()
}

 /**
	 * Function 否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvKOpt.canNotReportBook"() {
    (new com.jv.key.JvKOpt()).canNotReportBook()
}

 /**
	 * Get SysDate
	 * @param null
	 * @return SysDate(yyyyMMddHHmmss)
	 */ 
def static "com.jv.key.JvOpt.getDateTime"() {
    (new com.jv.key.JvOpt()).getDateTime()
}

 /**
	 * Function 議題追加
	 * @param task　議題あり  or 議題無し
	 * @param taskType　議題区分（決議、報告、討議）
	 * @param taskName　議題名
	 * @param inputMember　資料登録者
	 * @param file　添付ファイルの有り or 無し
	 * @param submitType　登録、閉じる、一回閉じる（先に閉じしてから登録）
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.addTask"(
    	String task	
     , 	String taskType	
     , 	String taskName	
     , 	String inputMember	
     , 	String file	
     , 	String submitType	) {
    (new com.jv.key.JvOpt()).addTask(
        	task
         , 	taskType
         , 	taskName
         , 	inputMember
         , 	file
         , 	submitType)
}

 /**
	 * Function 議題編集
	 * @param taskNumber　議題番号
	 * @param taskType　議題区分（決議、報告、討議）
	 * @param taskName　議題名
	 * @param inputMember　資料登録者
	 * @param file　添付ファイルの有り or 無し
	 * @param submitType　登録、閉じる、一回閉じる（先に閉じしてから登録）
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.editTask"(
    	String taskNumber	
     , 	String taskType	
     , 	String taskName	
     , 	String inputMember	
     , 	String file	
     , 	String submitType	) {
    (new com.jv.key.JvOpt()).editTask(
        	taskNumber
         , 	taskType
         , 	taskName
         , 	inputMember
         , 	file
         , 	submitType)
}

 /**
	 * Function 議題追加
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.reStart"(
    	String userName	
     , 	String password	
     , 	String meetName	
     , 	String[] meetInfo	) {
    (new com.jv.key.JvOpt()).reStart(
        	userName
         , 	password
         , 	meetName
         , 	meetInfo)
}

 /**
	 * Function 議題追加
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.addTask"(
    	String outerTagName	) {
    (new com.jv.key.JvOpt()).addTask(
        	outerTagName)
}

 /**
	 * Function 添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.materialsFileUpdate"(
    	String outerTagName	) {
    (new com.jv.key.JvOpt()).materialsFileUpdate(
        	outerTagName)
}

 /**
	 * Function 添付ファイルのアップロード
	 * @param outerTagName　ファイル名
	 * @return 無し
	 */ 
def static "com.jv.key.JvOpt.dialogFileUpdate"(
    	String outerTagName	) {
    (new com.jv.key.JvOpt()).dialogFileUpdate(
        	outerTagName)
}


def static "com.jv.key.JvOpt.static　getStatus"(
    	String outerTagName	) {
    (new com.jv.key.JvOpt()).static　getStatus(
        	outerTagName)
}

 /**
	 * Function 可決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvOpt.canReportBook"() {
    (new com.jv.key.JvOpt()).canReportBook()
}


def static "com.jv.key.JvOpt.fileUpdate"() {
    (new com.jv.key.JvOpt()).fileUpdate()
}

 /**
	 * Refresh browser
	 */ 
def static "com.jv.key.JvOpt.setMeetName"(
    	String meetName	) {
    (new com.jv.key.JvOpt()).setMeetName(
        	meetName)
}

 /**
	 * Refresh browser
	 */ 
def static "com.jv.key.JvOpt.WebDriverCheck"() {
    (new com.jv.key.JvOpt()).WebDriverCheck()
}

 /**
	 * Refresh browser
	 */ 
def static "com.jv.key.JvDate.WebDriverCheck"() {
    (new com.jv.key.JvDate()).WebDriverCheck()
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */ 
def static "com.jv.key.JvSOpt.getAnswerType"(
    	String model	) {
    (new com.jv.key.JvSOpt()).getAnswerType(
        	model)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */ 
def static "com.jv.key.JvSOpt.checkAnswerType"(
    	String model	
     , 	String AnswerType	) {
    (new com.jv.key.JvSOpt()).checkAnswerType(
        	model
         , 	AnswerType)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return 取締役/監査役
	 */ 
def static "com.jv.key.JvSOpt.checkRole"(
    	String model	
     , 	String AnswerType	) {
    (new com.jv.key.JvSOpt()).checkRole(
        	model
         , 	AnswerType)
}


def static "com.jv.key.JvSOpt.getAnswerTypeClick"() {
    (new com.jv.key.JvSOpt()).getAnswerTypeClick()
}

 /**
	 * Function 可決報告と否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvSOpt.ReportBook"(
    	String ReportType	) {
    (new com.jv.key.JvSOpt()).ReportBook(
        	ReportType)
}

 /**
	 * Function 可決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvSOpt.canReportBook"() {
    (new com.jv.key.JvSOpt()).canReportBook()
}

 /**
	 * Function 否決報告の処理
	 * @param TestObject
	 * @param TimeOut
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvSOpt.canNotReportBook"() {
    (new com.jv.key.JvSOpt()).canNotReportBook()
}

 /**
	 * Check元素の存在
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.isElementPresent"(
    	TestObject to	
     , 	int timeout	) {
    (new com.jv.key.JvUtil()).isElementPresent(
        	to
         , 	timeout)
}

 /**
	 * Click元素
	 * @param TestObjectID
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.click"(
    	String TestObjectID	) {
    (new com.jv.key.JvUtil()).click(
        	TestObjectID)
}

 /**
	 * Click元素
	 * @param TestObjectID
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.clickElement"(
    	String TestObjectID	) {
    (new com.jv.key.JvUtil()).clickElement(
        	TestObjectID)
}

 /**
	 * Click元素
	 * @param TestObjectID
	 * @param ClickType 可点击、可见、可见可点击
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.clickElement"(
    	String TestObjectID	
     , 	String ClickType	) {
    (new com.jv.key.JvUtil()).clickElement(
        	TestObjectID
         , 	ClickType)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.clickClickAbleElement"(
    	String TestObjectID	) {
    (new com.jv.key.JvUtil()).clickClickAbleElement(
        	TestObjectID)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.clickVisibleElement"(
    	String TestObjectID	) {
    (new com.jv.key.JvUtil()).clickVisibleElement(
        	TestObjectID)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "com.jv.key.JvUtil.clickClickAbleAndVisibleElement"(
    	String TestObjectID	) {
    (new com.jv.key.JvUtil()).clickClickAbleAndVisibleElement(
        	TestObjectID)
}

 /**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */ 
def static "com.jv.key.JvUtil.getJvHtmlTableRows"(
    	TestObject table	
     , 	String meetName	
     , 	String status	) {
    (new com.jv.key.JvUtil()).getJvHtmlTableRows(
        	table
         , 	meetName
         , 	status)
}


def static "com.jv.key.JvUtil.getMailUrl"(
    	TestObject to	
     , 	int timeout	) {
    (new com.jv.key.JvUtil()).getMailUrl(
        	to
         , 	timeout)
}

 /**
	 * Get all cells of HTML table row
	 * @param row a WebElement instance represent for HTML table row
	 * @param tagName HTML column tag name, usually is TD/TH
	 * @return All cells inside HTML table row
	 */ 
def static "com.jv.key.JvUtil.getHtmlTableColumns"(
    	WebElement row	
     , 	String tagName	) {
    (new com.jv.key.JvUtil()).getHtmlTableColumns(
        	row
         , 	tagName)
}

 /**
	 * Refresh browser
	 */ 
def static "com.jv.key.JvUtil.refreshBrowser"() {
    (new com.jv.key.JvUtil()).refreshBrowser()
}

 /**
	 * Click element
	 * @param to Katalon test object
	 */ 
def static "com.jv.key.JvUtil.clickElement"(
    	TestObject to	) {
    (new com.jv.key.JvUtil()).clickElement(
        	to)
}

 /**
	 * Get SysDate
	 * @param null
	 * @return SysDate(yyyyMMddHHmmss)
	 */ 
def static "com.jv.key.JvUtil.getSystemDate"() {
    (new com.jv.key.JvUtil()).getSystemDate()
}

 /**
	 * Get clickUsingJS
	 * @param TestObject
	 * @return null
	 */ 
def static "com.jv.key.JvUtil.clickUsingJS"(
    	TestObject to	) {
    (new com.jv.key.JvUtil()).clickUsingJS(
        	to)
}

 /**
	 * getSData
	 * @param なし
	 * @return Map<String, String>
	 */ 
def static "com.jv.key.JvUtil.getSData"() {
    (new com.jv.key.JvUtil()).getSData()
}
