package com.jv.constants;

/**
 * Object 書面会議と株主会議の共通部品
 * 
 */
public class SK_Object {
	
	private static String BaseUrl = "Page_MeetingOfDetails/SK_Component/";
	
	//-----------------------------------------Page_議題詳細画面---------------------------------------------------------------------------------------------------
	//------Status 決議待ち
	
	//SK_可決報告
	public static final String S_可決報告 = BaseUrl + "CanReport/SK_btn_can_report_book";
	
	//SK_可決報告はいボタン
	public static final String SK_可決報告はいボタン = BaseUrl + "CanReport/SK_btn_can_report_book_YES";
	
	//SK_可決報告いいえボタン
	public static final String SK_可決報告いいえボタン = BaseUrl + "CanReport/SK_btn_can_report_book_NO";
	
	//SK_否決報告
	public static final String SK_否決報告 = BaseUrl + "CanNotReport/SK_btn_can_not_report_book";
	
	//SK_否決報告はいボタン
	public static final String SK_否決報告はいボタン = BaseUrl + "CanNotReport/SK_btn_can_not_report_book_YES";
	
	//SK_否決報告いいえボタン
	public static final String SK_否決報告いいえボタン = BaseUrl + "CanNotReport/SK_btn_can_report_book_NO";
	
	//SK_決議待ちの送信ボタン
	public static final String SK_決議待ちの送信ボタン = BaseUrl + "SK_btn_confirm_wait_sendmail";
	
	
	//-----------------------------------------Page_議題詳細画面---------------------------------------------------------------------------------------------------
	//------Model 資料
	
	//SK_資料_DropDown
	public static final String SK_資料_DropDown = BaseUrl + "DropDown/SK_dropdown_filelist";

	//SK_資料_書面決議提案書
	public static String SK_資料_書面決議提案書 = BaseUrl + "DropDown/SK_file_book_confirm_proposal";
	
	//SK_資料_議事録押印無し
	public static String SK_資料_議事録押印無し = BaseUrl + "DropDown/SK_confirmbook_in_nohave";
	
	//SK_資料_資料
	public static String SK_資料_資料 = BaseUrl + "DropDown/SK_file";
	
	//SK_資料_議事録押印無し
	public static String SK_資料_議事録押印有り = BaseUrl + "DropDown/SK_confirmbook_in_have";
	
	//SK_資料ファイルを追加
	public static final String SK_資料ファイル_追加 = BaseUrl + "SK_input_MaterialsAdd";
	
	//SK_資料ファイル_アップロード
	public static final String SK_資料ファイル_アップロード = BaseUrl + "SK_btn_book_confirm_proposal_Update";
		
	//SK_議事録押印無し登録
	public static final String SK_議事録押印無し登録 = BaseUrl + "ConfirmBook/SK_btn_confirm_in_nohave";
	
	//SK_議事録押印無し確認_いいえボタン
	public static final String SK_議事録押印無し確認_いいえボタン = BaseUrl + "ConfirmBook/SK_btn_confirm_in_nohave_NO";
	
	//SK_議事録押印無し確認_はいボタン
	public static final String SK_議事録押印無し確認_はいボタン = BaseUrl + "ConfirmBook/SK_btn_confirm_in_nohave_YES";
	
	//-----------------------------------------Page_議題詳細画面---------------------------------------------------------------------------------------------------
	//------Status 押印依頼中
	
	//SK_完了ボタン
	public static final String SK_完了ボタン = BaseUrl + "Over/SK_btn_over";
	//SK_完了確認_完了ボタン
	public static final String SK_完了確認_完了ボタン = BaseUrl + "Over/SK_btn_over_YES";
	//SK_完了確認_閉じるボタン
	public static final String SK_完了確認_閉じるボタン = BaseUrl + "Over/SK_btn_over_NO";
	
	//SK_否決完了ボタン
	public static final String SK_否決完了ボタン = BaseUrl + "Over/SK_btn_noOver";
	//SK_否決完了_はいボタン
	public static final String SK_否決完了_はいボタン = BaseUrl + "Over/SK_btn_noOVer_YES";
	//SK_否決完了_いいえボタン
	public static final String SK_否決完了_いいえボタン = BaseUrl + "Over/SK_btn_noOVer_NO";
	
	//-----------------------------------------Page_議題詳細画面---------------------------------------------------------------------------------------------------
	//------File 同意書
	
	private static String SubBaseUrl = "Dialog/";
	
	//Dialog_添付ファイル
	public static String SK_Dialog_添付ファイル = BaseUrl + SubBaseUrl + "SK_upload_dialog_addFile";
	
	//SK_Dialog_同意書押印有り
	public static String SK_Dialog_同意書押印有り = BaseUrl + SubBaseUrl + "SK_upload_dialog_agree_in_have";
	
	//SK_Dialog_同意書押印無し
	public static String SK_Dialog_同意書押印無し =  BaseUrl + SubBaseUrl + "SK_upload_dialog_agree_in_no";
	
	//SK_Dialog_確認書押印有り
	public static String SK_Dialog_確認書押印有り = BaseUrl + SubBaseUrl + "SK_upload_dialog_confirm_in_have";
	
	//SK_Dialog_確認書押印無し
	public static String SK_Dialog_確認書押印無し =  BaseUrl + SubBaseUrl + "SK_upload_dialog_confirm_in_no";
		
	//SK_Dialog_ファイルのアップロード
	public static String SK_Dialog_ファイルのアップロード = BaseUrl + SubBaseUrl + "SK_upload_dialog_btn_upload";
	
	//SK_Dialog_DropDown
	public static String SK_Dialog_DropDown = BaseUrl + SubBaseUrl + "SK_upload_dialog_dropdown";
	
	//SK_Dalog閉じる
	public static String SK_Dialog_閉じる = BaseUrl + SubBaseUrl + "SK_upload_dialog_btn_close";
	
	
	//-----------------------------------------Page_議題詳細画面---------------------------------------------------------------------------------------------------
	//------File 同意書
	
	//SK_提案書送付
	public static String SK_提案書送付 = BaseUrl + "SK_btn_proposal_book_send";
	
	//SK_提案書送付待ちへ
	public static String SK_提案書送付待ちへ = BaseUrl + "SK_btn_ProposalSendWait";
	
	//SK_書_議題資料更新確認_はいボタン
	public static String SK_書_議題資料更新確認_はいボタン = BaseUrl + "S_btn_task_file_update_YES";

	//SK_書面決議提案書_送信ボタン
	public static String SK_書面決議提案書_送信ボタン = BaseUrl + "SK_btn_sendmail";
	
	
	
	//SK_議題登録依頼ボタン
	public static String SK_議題登録依頼 = "Page_MeetingList/btn_MeetingLandDepend";
	//SK_議題申請ボタン
	public static String SK_議題申請 = "Page_MeetingList/btn_MeetingRequest";
	//SK_過去開催閲覧タン
	public static String SK_過去開催閲覧 = "Page_MeetingList/btn_BeforeMeetingList";
	//SK_関係者設定ボタン
	public static String SK_関係者設定 = "Page_MeetingList/btn_RelatePersonSet";
	//SK_定型文設定ボタン
	public static String SK_定型文設定 = "Page_MeetingList/btn_MailTemplant";
	//SK_リマインド設定ボタン
	public static String SK_リマインド設定 = "Page_MeetingList/btn_RemaindSet";
	//SK_検索フィルターボタン
	public static String SK_検索フィルター = "Page_MeetingList/btn_SearchFilter";
	
	
	
	//SK_会議一覧のソート
	public static String SK_会議一覧のソート = "Page_MeetingList/dropdown_time_sort";
	//SK_開催日時降順
	public static String SK_開催日時降順 = "Page_MeetingList/dropdown_MeetTimeDesc";
	//SK_決議日降順
	public static String SK_決議日降順 = "Page_MeetingList/dropdown_RelateTimeDesc";
		

}