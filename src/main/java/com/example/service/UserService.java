package com.example.service;


import java.util.List;

import com.example.form.Result;
import com.example.form.User;

public interface UserService {
	/*ユーザー1件検索*/
	public User getUserOne(String userId);
	/*出勤1件登録*/
	public void goTimeIn(String userId,String resultId);
	/*退勤1件登録*/
	public void outTimeIn(String resultId);
	/*結果IDのMAX値検索*/
	public Result getMaxResultId();
	/*結果テーブルの最後に登録されたものを検索する*/
	public Result getFainalResult(String userId);
	/*ユーザーIDと紐付いた結果を全て取得A*/
	public User getResultUIdAll(String userId);
	/*ユーザーIDと紐付いた結果を全て取得B*/
	public List<Result> getresultFindAllByUserId(String userId);
	/*ユーザー全件表示*/
	public List<User> getUserFindAll();
}
