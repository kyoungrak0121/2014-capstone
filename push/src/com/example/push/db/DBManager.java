package com.example.push.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBManager {
	// DB관련 상수
	private static final String dbName = "APinfo.db";
	private static final String table_stu = "student";
	private static final String table_prof = "professor";
	public static final int dbVersion = 1;

	// DB관련 객체 선언
	private OpenHelper opener; // DB opener
	private SQLiteDatabase db; // DB controller

	// 부가적인 객체들
	private Context context;

	// 생성자
	public DBManager(Context context) {
		this.context = context;
		this.opener = new OpenHelper(context, dbName, null, dbVersion);
		db = opener.getWritableDatabase();
	}

	// Opener of DB and Table
	private class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, null, version);
			// TODO Auto-generated constructor stub
		}

		// 생성된 DB가 없을 경우에 한번만 호출됨
		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// String dropSql = "drop table if exists " + table_stu;
			//db.execSQL(dropSql);

			String create_stu = "create table " + table_stu + " ("
					+ "id integer , " + "SSID text, "
					+ "pwd text)";
			arg0.execSQL(create_stu);
			Toast.makeText(context, "student DB is opened", 0).show();
			
			String create_prof = "create table " + table_prof + " ("
					+ "prof_ID text, "
					+ "porf_pwd text)";
			arg0.execSQL(create_prof);
			Toast.makeText(context, "professor DB is opened", 0).show();
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
		}
	}

	// 데이터 추가
	public void insert_stu(int num,String id, String pwd) {
		String sql = "insert into " + table_stu + " values("+num+",'" + id
				+ "', '" + pwd + "');";
		db.execSQL(sql);
		Log.d("STUDENTS INSERT ", "completed");
	}
	
	
	public void insert_prof(String id,String pwd){
		String sql = "insert into " + table_prof + " values('" + id
				+ "', '" + pwd + "');";
		db.execSQL(sql);
		Log.d("PROFESSORS INSERT ", "completed");
	}

//	public ArrayList<apinfo> selectAll() {
//		String sql = "select * from " + tableName + ";";
//		Cursor results = db.rawQuery(sql, null);
//
//		results.moveToFirst();
//		ArrayList<apinfo> infos = new ArrayList<apinfo>();
//
//		while (!results.isAfterLast()) {
//			APinfo info = new APinfo(results.getInt(0), results.getString(1),
//					results.getInt(2), results.getString(3));
//			infos.add(info);
//			results.moveToNext();
//		}
//		results.close();
//		return infos;
//	}

}
