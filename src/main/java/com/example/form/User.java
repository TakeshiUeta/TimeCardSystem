package com.example.form;

import java.util.List;

import lombok.Data;

@Data
public class User {
	/* ユーザーID */
	private String userId;
	/* パスワード */
	private String password;
	/* 名前 */
	private String name;
	/* 結果リスト */
	private List<Result> resultList;

	/**
	 * コンストラクタ
	 */
	 public User() {
	}

	/**
	 * コンストラクタ
	 * @param userId   ユーザーID
	 * @param password パスワード
	 * @param name     名前
	 */
	 public User(String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}

	/**
	 * コンストラクタ
	 * @param userId     ユーザーID
	 * @param password   パスワード
	 * @param name       名前
	 * @param resultList 結果リスト
	 */
	User(String userId, String password, String name, List<Result> resultList) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.resultList = resultList;
	}

}
