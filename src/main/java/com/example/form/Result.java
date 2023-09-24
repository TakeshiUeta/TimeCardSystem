package com.example.form;

import lombok.Data;

@Data
public class Result {
	/* 結果ID */
	private String resultId;
	/* 出勤時間 */
	private String goTime;
	/* 退勤時間 */
	private String outTime;
	/* ユーザーID */
	private String userId;

	/**
	 * コンストラクタ
	 */
	Result() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param resultId 結果ID
	 * @param goTime   出勤時間
	 * @param outTime  退勤時間
	 */
	Result(String resultId, String goTime, String outTime, String userId) {
		this.resultId = resultId;
		this.goTime = goTime;
		this.outTime = outTime;
		this.userId = userId;
	}

}
