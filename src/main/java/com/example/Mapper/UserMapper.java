package com.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.form.Result;
import com.example.form.User;

@Mapper
public interface UserMapper {
	/*ユーザー1件取得*/
	public User userFindOne(String userId);
	/*出勤1件登録*/
	public void gotimeOne(String userId,String resultId);
	/*退勤1件登録*/
	public void outTimeOne(String resultId);
	/*結果IDのMAX値検索*/
	public Result maxResultId();
	/*結果テーブルの最後に登録されたものを検索する*/
	public Result fainalResult(String userId);
	/*ユーザーIDと紐付いた結果を全て取得A*/
	public User resultUIdAll(String userId);
	/*ユーザーIDと紐付いた結果を全て取得B*/
	public List<Result> resultFindAllByUserId(String userId);
	/*ユーザー全件表示*/
	public List<User> userFindAll();
}
