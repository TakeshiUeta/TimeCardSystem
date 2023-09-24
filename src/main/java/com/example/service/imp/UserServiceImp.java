package com.example.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Mapper.UserMapper;
import com.example.form.Result;
import com.example.form.User;
import com.example.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserMapper mapper;

	/* ユーザー1件検索 */
	@Override
	public User getUserOne(String userId) {
		return mapper.userFindOne(userId);
	}

	/* 出勤1件登録 */
	@Override
	public void goTimeIn(String userId, String resultId) {
		mapper.gotimeOne(userId, resultId);
	}
	/*退勤1件登録 */
	@Override
	public void outTimeIn(String resultId) {
		mapper.outTimeOne(resultId);
	}

	/* 結果IDのMAX値検索 */
	@Override
	public Result getMaxResultId() {
		return mapper.maxResultId();
	}

	/* 結果テーブルの最後に登録されたものを検索する */
	@Override
	public Result getFainalResult(String userId) {
		return mapper.fainalResult(userId);
	}
	
	/*ユーザーIDと紐付いた結果を全て取得*/
	@Override
	public User getResultUIdAll(String userId){
		return mapper.resultUIdAll(userId);
	}
	
	/*ユーザーIDと紐付いた結果を全て取得B*/
	@Override
	public List<Result> getresultFindAllByUserId(String userId){
		return mapper.resultFindAllByUserId(userId);
	}

	
	/*ユーザー全件表示*/
	@Override
	public List<User> getUserFindAll(){
		return mapper.userFindAll();
	}
	
}
