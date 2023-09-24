/*ユーザーマスタ*/
CREATE TABLE IF NOT EXISTS t_user(
	 user_id VARCHAR(50) PRIMARY KEY
	,password VARCHAR(100)
	,user_name VARCHAR(50)
);

/*結果テーブル*/
CREATE TABLE IF NOT EXISTS t_result(
	 result_id VARCHAR(50) PRIMARY KEY
	,go_time timestamp
	,out_time timestamp
	,user_id VARCHAR(50)
);
