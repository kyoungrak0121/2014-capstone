package com.example.push.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBManager {
	// DB���� ���
	private static final String dbName = "APinfo.db";
	private static final String table_stu = "student";
	private static final String table_prof = "professor";
	private static final String table_class = "class" ;
	private static final String table_subject_time = "subject_time" ;
	private static final String table_subject = "subject";
	private static final String table_message = "message";
	public static final int dbVersion = 1;

	
	/* ���� : class
	   �л� : student 
	   ���� : professor
	   
	*/
	// DB���� ��ü ����
	private OpenHelper opener; // DB opener
	private SQLiteDatabase db; // DB controller

	// �ΰ����� ��ü��
	private Context context;

	// ������
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

		// ������ DB�� ���� ��쿡 �ѹ��� ȣ���
		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// String dropSql = "drop table if exists " + table_stu;
			//db.execSQL(dropSql);

			// �л� ���̺� 
			String create_stu = "create table " + table_stu + " ("
					+ "stu_id text , " 
					+ "pwd text, "
					+ "name text, "
					+ "phone_num text," 
					+ "reg_id text)";
			arg0.execSQL(create_stu);
			Toast.makeText(context, "student DB is opened", 0).show();
			
			// ���� ���̺�
			String create_prof = "create table " + table_prof + " ("
					+ "prof_ID text, "
					+ "porf_pwd text,"
					+ "subject_num text)";
			arg0.execSQL(create_prof);
			Toast.makeText(context, "professor DB is opened", 0).show();
			
			// ���� �л� table 
			String create_class = "create table " + table_class + " ("
					+ "class_num text, "
					+ "subject_num text)";
			arg0.execSQL(create_class);
			Toast.makeText(context, "class DB is opened", 0).show();

			// ���� table
			String create_subject = "create table " + table_subject + " ("
					+ "class_num text, "
					+ "class_name text, "
					+ "class_where text)";
			arg0.execSQL(create_subject);
			Toast.makeText(context, "subject DB is opened", 0).show();
			
			// ���� table
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

	// ������ �߰�
	public void insert_stu(String id, String pwd,String name,String phone,String reg_id) {
		String sql = "insert into " + table_stu + " values('"+id+"','" +pwd
				+ "', '" + name + "', '" + phone + "', '" + reg_id + "');";
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
