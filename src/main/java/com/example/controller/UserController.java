package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.Result;
import com.example.form.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/timecard")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	/** ログイン画面を表示 */
	@GetMapping("/userlogin")
	public String getUserlogin(@ModelAttribute User user) {
		return "/timecard/userlogin";
	}

	/** top画面に遷移 */
	@GetMapping("/timecard")
	public String getTimecard(Model model, @ModelAttribute User user) {
		// 入力されたパスワードをゲットする
		String passwordA = user.getPassword();
		String userId = user.getUserId();
		// ユーザー1件検索
		user = userService.getUserOne(userId);
		// DBのパスワードをゲット
		String passwordB = user.getPassword();
		// 入力されたパスワードがDBのパスワード同一のものか確認する
		if (!passwordA.equals(passwordB)) {
			// 同一ではない場合リダイレクト
			return "redirect:timecard/userlogin";
		}
		// セッションにユーザーをセット
		session.setAttribute("user", user);
		return "/timecard/timecard";
	}
	
	/**ユーザー登録画面に遷移*/
	@GetMapping("/userRegist")
	public String getUserRegist(Model model,@ModelAttribute User user) {
		return "/timecard/userRegist";
	}
	/*ユーザー登録処理**/
	@PostMapping("/userRegist")
	public String postUserRegist(Model model,@ModelAttribute User user) {
		//現在のユーザーIDのMax値を求める
		String beginMaxuserId = userService.getFindainalUserId();
		//IDの文字列の長さを求める
		int uIdlength = beginMaxuserId.length();
		//数値部分だけ抜き出す
		String uIdValue = beginMaxuserId.substring(uIdlength-4,uIdlength); 
		//int型にキャスト
		int uIdInt = Integer.parseInt(uIdValue);
		//キャストしたものに+1した変数を作成
		int maxUidValueInt = uIdInt+1;
		// maxUidValueIntにつける"UR0_i"を格納する変数を作成
		String incValue = "";
		// maxUidValueIntが10未満、100未満、1000未満の場合の時、IDに付属する部分を作成する
		if (maxUidValueInt < 10) {
			incValue = "RE000";
		} else if (maxUidValueInt < 100) {
			incValue = "RE00";
		} else if (maxUidValueInt < 1000) {
			incValue = "RE0";
		} else {
			incValue = "RE";
		}
		//最大値を作成する
		String maxUId =incValue + Integer.toString(maxUidValueInt);
		//入力値をそれぞれGetする
		String password = user.getPassword();
		String name = user.getName();
		//登録処理
		userService.userRegist(maxUId, password, name);
		return "/timecard/userRegist";
	}
	
	
	
	/** 出勤処理 */
	@PostMapping(value = "/result", params = "goTime")
	public String goTimeResult(Model model) {
		// セッションからユーザーをゲットする
		User user = (User) session.getAttribute("user");
		// ユーザーIDをゲットする
		String userId = user.getUserId();
		/** resultIDのMAX値を求める */
		// 結果IDの現在のMax値を求める
		Result result = userService.getMaxResultId();
		// Resultから結果IDをゲットする
		String beginMaxResultId = result.getResultId();
		// 結果IDの文字列の文字数を取得
		int idLength = beginMaxResultId.length();
		// 結果IDの数字部分だけ抜き出す
		String rIdvalue = beginMaxResultId.substring(idLength - 4, idLength);
		// int型にキャスト
		int resultInt = Integer.parseInt(rIdvalue);
		// キャストしたものに+1した変数を作成
		int maxValueInt = resultInt + 1;
		// maxValueIntにつける"RE0_i"を格納する変数を作成
		String incValue = "";
		// maxValueIntが10未満、100未満、1000未満の場合の時、IDに付属する部分を作成する
		if (maxValueInt < 10) {
			incValue = "RE000";
		} else if (maxValueInt < 100) {
			incValue = "RE00";
		} else if (maxValueInt < 1000) {
			incValue = "RE0";
		} else {
			incValue = "RE";
		}

		// maxValueIntをString型にキャスト
		String maxValueString = Integer.toString(maxValueInt);

		// resultIDのMAX値を作成
		String maxResultId = incValue + maxValueString;

		// 出勤処理
		userService.goTimeIn(userId, maxResultId);

		/*
		 * //結果画面に表示する結果リストを取得 
		 * User userResult= userService.getResultUIdAll(userId);
		 */

		// 結果画面に表示する結果リストを取得
		List<Result> resultList = userService.getresultFindAllByUserId(userId);

		// modelに格納
		model.addAttribute("resultList", resultList);

		// 結果画面に遷移
		return "/timecard/result";
	}

	/** 退勤処理 */
	@PostMapping(value = "/result", params = "outTime")
	public String outTimeResult(Model model) {
		// セッションからユーザーを取り出す
		User user = (User) session.getAttribute("user");
		// ユーザーIDをゲット
		String userId = user.getUserId();
		// ユーザーIDと紐付いた一番最後に入力した結果を求める
		Result result = userService.getFainalResult(userId);
		// 結果IDをゲット
		String resultId = result.getResultId();
		// 退勤時間をゲット
		String outTime = result.getOutTime();
		// 退勤時間がnullではない場合timecard画面にリダイレクト＝まだ出勤を押していない。
		if (outTime != null) {
			return getTimecard(model, user);
		}
		// 退勤時間を登録
		userService.outTimeIn(resultId);

		/*
		 * /結果画面に表示する結果リストを取得 User userResult= userService.getResultUIdAll(userId);
		 */

		// 結果画面に表示する結果リストを取得
		List<Result> resultList = userService.getresultFindAllByUserId(userId);

		// modelに格納
		model.addAttribute("resultList", resultList);

		// 結果画面に遷移
		return "/timecard/result";
	}

	/** ログアウト */
	@GetMapping("/logout")
	public String getLogout() {
		// userを全て空にする
		User user = (User) session.getAttribute("user");
		user = null;
		// セッションを終了させる
		try {
			session.invalidate();	
		} catch (Exception e) {
			return getUserlogin(user);
		}
		// ログイン画面に遷移
		return "/timecard/logout";
	}
}
