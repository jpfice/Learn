package com.jv.bean;

public class MeetInfo {
	
	//JV会議種別
	private String meetType = "";
	
	//JV会議名
	private String meetName = "";
	
	//JV開催日時From
	private String meetFromYmd = "";
	
	//JV開催日時To
	private String meetToYmd = "";
	
	//JVステータス
	private String meetStatus = "";
	
	//JV議題登録期限
	private String taskInputTimeLimit = "";
	
	//JV資料提出期限
	private String fileInputTimeLimit = "";
	
	//JV自動リマインド
	private String btnAutoRemind = "";
	
	//JV一時保存
	private String btnTempSave = "";
	
	//JVメール設定
	private String btnMailSet = "";
	
	//JVTo
	private String mailTo = "";
	
	//JV件名
	private String mailName = "";
	
	//JV本文
	private String mailContent = "";
	
	//JV戻る
	private String btnback = "";
	
	//JV議題登録依頼あり
	private String btnTaskHave = "";
	
	//JV議題登録依頼なし
	private String btnTaskNotHave = "";

	public String getMeetType() {
		return meetType;
	}

	public void setMeetType(String meetType) {
		this.meetType = meetType;
	}

	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}

	public String getMeetFromYmd() {
		return meetFromYmd;
	}

	public void setMeetFromYmd(String meetFromYmd) {
		this.meetFromYmd = meetFromYmd;
	}

	public String getMeetToYmd() {
		return meetToYmd;
	}

	public void setMeetToYmd(String meetToYmd) {
		this.meetToYmd = meetToYmd;
	}

	public String getMeetStatus() {
		return meetStatus;
	}

	public void setMeetStatus(String meetStatus) {
		this.meetStatus = meetStatus;
	}

	public String getTaskInputTimeLimit() {
		return taskInputTimeLimit;
	}

	public void setTaskInputTimeLimit(String taskInputTimeLimit) {
		this.taskInputTimeLimit = taskInputTimeLimit;
	}

	public String getFileInputTimeLimit() {
		return fileInputTimeLimit;
	}

	public void setFileInputTimeLimit(String fileInputTimeLimit) {
		this.fileInputTimeLimit = fileInputTimeLimit;
	}

	public String getBtnAutoRemind() {
		return btnAutoRemind;
	}

	public void setBtnAutoRemind(String btnAutoRemind) {
		this.btnAutoRemind = btnAutoRemind;
	}

	public String getBtnTempSave() {
		return btnTempSave;
	}

	public void setBtnTempSave(String btnTempSave) {
		this.btnTempSave = btnTempSave;
	}

	public String getBtnMailSet() {
		return btnMailSet;
	}

	public void setBtnMailSet(String btnMailSet) {
		this.btnMailSet = btnMailSet;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getBtnback() {
		return btnback;
	}

	public void setBtnback(String btnback) {
		this.btnback = btnback;
	}

	public String getBtnTaskHave() {
		return btnTaskHave;
	}

	public void setBtnTaskHave(String btnTaskHave) {
		this.btnTaskHave = btnTaskHave;
	}

	public String getBtnTaskNotHave() {
		return btnTaskNotHave;
	}

	public void setBtnTaskNotHave(String btnTaskNotHave) {
		this.btnTaskNotHave = btnTaskNotHave;
	}

}