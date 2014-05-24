package com.example.push.db;

import android.content.Context;
import android.database.Cursor;
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
	private static final String table_class = "class" ;
	private static final String table_subject_time = "subject_time" ;
	private static final String table_subject = "subject";
	private static final String table_message = "message";
	public static final int dbVersion = 1;

	
	/* 강좌 : class
	   학생 : student 
	   교수 : professor
	   
	*/
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

			// 학생 테이블 
			String create_stu = "create table " + table_stu + " ("
					+ "stu_id text , " 
					+ "pwd text, "
					+ "name text, "
					+ "phone_num text," 
					+ "reg_id text)";
			arg0.execSQL(create_stu);
			Toast.makeText(context, "student DB is opened", 0).show();
			
			// 교수 테이블
			String create_prof = "create table " + table_prof + " ("
					+ "prof_id text, "
					+ "prof_pwd text,"
					+ "subject_num text)";
			arg0.execSQL(create_prof);
			Toast.makeText(context, "professor DB is opened", 0).show();
			
			// 수강 학생 table 
			String create_class = "create table " + table_class + " ("
					+ "class_num text, "
					+ "subject_num text)";
			arg0.execSQL(create_class);
			Toast.makeText(context, "class DB is opened", 0).show();

			// 과목 table
			String create_subject = "create table " + table_subject + " ("
					+ "class_num text, "
					+ "class_name text, "
					+ "class_where text)";
			arg0.execSQL(create_subject);
			Toast.makeText(context, "subject DB is opened", 0).show();
			
			// 과목 table
			String create_subject_time = "create table " + table_subject_time + " ("
					+ "class_num text, "
					+ "class_name text, "
					+ "class_where text)";
			arg0.execSQL(create_subject_time);
			Toast.makeText(context, "subject_time DB is opened", 0).show();
			
			// message table
			String create_message = "create table " + table_message + " ("
					+ "class_num text, "
					+ "class_name text, "
					+ "class_where text)";
			arg0.execSQL(create_message);
			Toast.makeText(context, "message DB is opened", 0).show();
			 
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
		}
	}

	// 데이터 추가
	public void insert_stu(String id, String pwd,String name,String phone,String reg_id) {
		String sql = "insert into " + table_stu + " values('"+id+"','" +pwd
				+ "', '" + name + "', '" + phone + "', '" + reg_id + "');";
		db.execSQL(sql);
		Log.d("STUDENTS INSERT ", "completed");
	}
	
	
	public void insert_prof(String id,String pwd,String subject_num){
		String sql = "insert into " + table_prof + " values('" + id
				+ "', '" + pwd + "',+'" + subject_num
				+ "');";
		db.execSQL(sql);
		Log.d("PROFESSORS INSERT ", "completed");
	}
	
	public void select_stu(){
		Cursor cursor = db.query(true,table_stu,new String[]{"stu_id","pwd","name","phone_num","reg_id"},
				null,null,null,null,null,null);
		
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String pwd = cursor.getString(1);
			String name = cursor.getString(2);
			String phone = cursor.getString(3);
			
			String total = "his name is" + name +" and phone number is " + phone;
			Toast.makeText(context,total,0).show();
		}
	}
		
	public void select_prof(){
		Cursor cursor = db.query(true,table_prof,new String[]{"prof_id","prof_pwd","subject_num"},
				null,null,null,null,null,null);
//		if (cursor ==null){
//			Toast.makeText(context, "no user", 0).show();
//		}
//		else{
			while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String pwd = cursor.getString(1);
			String subject_num = cursor.getString(2);
			
			String total = "his name is" + id +" and subject num is " + subject_num;
			Toast.makeText(context,total,0).show();
//		}
		}
	
		
	}
	
	

//	public Cursor select_stu() {
//		String sql = "select * from " + table_stu+ ";";
//		Cursor results = db.rawQuery(sql, null);
//
//		results.moveToFirst();
//		ArrayList<apinfo> infos = new ArrayList<apinfo>();
//
//		while (!results.isAfterLast()) {
//			APinfo info = new APinfo(results.getInt(0), results.getString(1),
//				 	results.getInt(2), results.getString(3));
//			infos.add(info);
//			results.moveToNext();
//		}
//		results.close();
//		return infos;
//	}

}
