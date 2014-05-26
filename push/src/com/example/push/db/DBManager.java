package com.example.push.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.push.table.Globals;
import com.example.push.table.Professor;
import com.example.push.table.Student;




import com.example.push.table.Subject_Info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings.Global;
import android.util.Log;
import android.widget.Toast;

public class DBManager {
	// DB관련 상수
	private static final String dbName = "APinfo.db";
	private static final String table_stu = "student";
	private static final String table_prof = "professor";
	private static final String table_stClass = "stClass";
	private static final String table_subject_info = "subject_time";
	private static final String table_subject = "subject";
	private static final String table_message = "message";
	private static final String table_profClass = "profClass";
	public static final int dbVersion = 1;

	/*
	 * 강좌 : stClass
	 * 
	 * 학생 : student 교수 : professor
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
			// db.execSQL(dropSql);

			// 학생 테이블

			String create_stu = "create table " + table_stu + " ("
					+ "stu_num text , " + "stu_pwd text, " + "stu_name text, "
					+ "stu_phone text," + "stu_reg_id text)";
			arg0.execSQL(create_stu);
			Toast.makeText(context, "student DB is opened", 0).show();

			// 교수 테이블
			String create_prof = "create table " + table_prof + " ("
					+ "prof_num text, " + "prof_pwd text," + "prof_name text)";
			arg0.execSQL(create_prof);
			Toast.makeText(context, "professor DB is opened", 0).show();

			// 수강 학생 table
			String create_stClass = "create table " + table_stClass + " ("
					+ "stu_num text, " + "subject_num text)";
			arg0.execSQL(create_stClass);
			Toast.makeText(context, "class DB is opened", 0).show();

			// 과목 table
			String create_subject = "create table " + table_subject + " ("
					+ "subject_num text," + "subject_name text)";
			arg0.execSQL(create_subject);
			Toast.makeText(context, "subject DB is opened", 0).show();

			// 과목 시간 table
			String create_subject_info = "create table " + table_subject_info
					+ " (" + "subject_num text, " + "subject_day text, "
					+ "subject_period text, " + "subject_time text, "
					+ "subject_where text)";
			arg0.execSQL(create_subject_info);
			Toast.makeText(context, "subject_time DB is opened", 0).show();

			// message table
			String create_message = "create table " + table_message + " ("
					+ "message_num text, " + "subject_num text, "
					+ "message_content text)";
			arg0.execSQL(create_message);
			Toast.makeText(context, "message DB is opened", 0).show();

			// 교수 강좌 table

			String create_profClass = "create table " + table_profClass + " ("
					+ "prof_num text, " + "subject_num text)";
			arg0.execSQL(create_profClass);
			Toast.makeText(context, "message DB is opened", 0).show();

		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
		}
	}

	// 데이터 추가

	// 과목
	public void insert_subject(String subject_num, String subject_name) {
		String sql = "insert into " + table_subject + " values('" + subject_num
				+ "', '" + subject_name + "');";
		db.execSQL(sql);

		Log.d("insert_subject INSERT ", "completed");
	}

	// 과목 시간
	public void insert_subject_info(String subject_num, String subject_day,
			String subject_period, String subject_time, String subject_where) {
		String sql = "insert into " + table_subject_info + " values('"
				+ subject_num + "', '" + subject_day + "', '" + subject_period
				+ "', '" + subject_time + "', '" + subject_where + "');";
		db.execSQL(sql);

		Log.d("insert_subject_info ", "completed");
	}

	// 학생
	public void insert_stu(String stu_num, String stu_pwd, String stu_name,
			String stu_phone, String stu_reg_id) {
		String sql = "insert into " + table_stu + " values('" + stu_num
				+ "', '" + stu_pwd + "', '" + stu_name + "', '" + stu_phone
				+ "', '" + stu_reg_id + "');";
		db.execSQL(sql);
		Log.d("insert_stu", "completed");
	}

	// 교수
	public void insert_prof(String prof_id, String prof_pwd, String prof_name) {
		String sql = "insert into " + table_prof + " values('" + prof_id
				+ "', '" + prof_pwd + "', '" + prof_name + "');";
		db.execSQL(sql);

		Log.d("insert_prof", "completed");
	}

	// 학생 수강 데이터
	public void insert_stClass(String stu_id, String subject_num) {
		String sql = "insert into " + table_stClass + " values('" + stu_id
				+ "', '" + subject_num + "');";
		db.execSQL(sql);

		Log.d("insert_stClass", "completed");
	}

	// 교수 강좌 테이븡
	public void insert_profClass(String prof_num, String subject_num) {
		String sql = "insert into " + table_profClass + " values('" + prof_num
				+ "', '" + subject_num + "');";
		db.execSQL(sql);

		Log.d("insert_profClass ", "completed");
	}

	// 교수 강좌 테이븡
	public void insert_message(String message_num, String subject_num,
			String content) {
		String sql = "insert into " + table_message + " values('" + message_num
				+ "', '" + subject_num + "', '" + content + "');";
		db.execSQL(sql);

		Log.d("insert_message", "completed");
	}

	// DB 에서 data 가져오기

	public Map<String, Student> select_stu() {

		Map<String, Student> sList = new HashMap<String, Student>();

		Cursor cursor = null;

		try {
			cursor = db.query(true, table_stu, new String[] { "stu_num",
					"stu_pwd", "stu_name", "stu_phone", "stu_reg_id" }, null,
					null, null, null, null, null);

			// do some work with the cursor here.

			while (cursor.moveToNext()) {

				String id = cursor.getString(0);
				String pwd = cursor.getString(1);
				String name = cursor.getString(2);
				String phone = cursor.getString(3);
				String regid = cursor.getString(4);

				// String total = "his name is" + name +" and phone number is "
				// + phone;
				// Toast.makeText(context,total,0).show();

				sList.put(id, new Student(id, pwd, name, phone, regid));
			}

		} finally {
			// this gets called even if there is an exception somewhere above
			if (cursor != null)
				cursor.close();
		}
		return sList;
	}

	public Map<String, Professor> select_prof() {

		Map<String, Professor> pList = new HashMap<String, Professor>();

		Cursor cursor = null;

		try {
			cursor = db.query(true, table_prof, new String[] { "prof_num",
					"prof_pwd", "prof_name" }, null, null, null, null, null,
					null);

			while (cursor.moveToNext()) {
				String id = cursor.getString(0);
				String pwd = cursor.getString(1);
				String name = cursor.getString(2);
				/*
				Professor p = new Professor(id, pwd, name);
				p.setSubjectList(setProfSubList(id));
				*/
				pList.put(id,new Professor(id, pwd, name));
			
			}
			// do some work with the cursor here.
		} finally {
			// this gets called even if there is an exception somewhere above
			if (cursor != null)
				cursor.close();
		}

		return pList;
	}

	public List<String> getRegList(String profId) {
		List<String> regList = new ArrayList<String>();

		/*
		 * String sql = "select stu_reg_id " + "from "+table_prof +
		 * " p  inner join (" +table_profClass + " pc inner join (" +
		 * table_stClass + " sc inner join " + table_stu+
		 * " s on s.stu_num = sc.stu_num ) t on t.subject_num = pc.subject_num )"
		 * + " t2 on p.prof_num = t2.prof_num" + " where p.prof_num=?;";
		 */

		String sql = "select DISTINCT stu_reg_id "
				+ "from "
				+ table_prof
				+ " p  inner join ("
				+ table_profClass
				+ " pc inner join ("
				+ table_stClass
				+ " sc inner join "
				+ table_stu
				+ " s on s.stu_num = sc.stu_num ) t on t.subject_num = pc.subject_num )"
				+ " t2 on p.prof_num = t2.prof_num" + " where p.prof_num=?;";

		Cursor cursor = null;

		try {
			cursor = db.rawQuery(sql, new String[] { String.valueOf(profId) });
			// do some work with the cursor here.

			ArrayList<String> List = new ArrayList<String>(); // 검사하려하는 어레이리스트

			while (cursor.moveToNext()) {
				List.add(cursor.getString(0));
			}

			HashSet<String> hs = new HashSet<String>(List);
			Iterator<String> it = hs.iterator();
			while (it.hasNext()) {
				regList.add(it.next());
			}

		} finally {
			// this gets called even if there is an exception somewhere above
			if (cursor != null)
				cursor.close();
		}

		return regList;
	}

	public List<String> getPhoneList(String profId) {
		List<String> phoneList = new ArrayList<String>();

		String sql = "select DISTINCT stu_phone "
				+ "from "
				+ table_prof
				+ " p  inner join ("
				+ table_profClass
				+ " pc inner join ("
				+ table_stClass
				+ " sc inner join "
				+ table_stu
				+ " s on s.stu_num = sc.stu_num ) t on t.subject_num = pc.subject_num )"
				+ " t2 on p.prof_num = t2.prof_num"
				+ " where p.prof_num=? and stu_reg_id =?;";

		Cursor cursor = null;

		try {
			cursor = db.rawQuery(sql, new String[] { String.valueOf(profId),
					String.valueOf("") });
			// do some work with the cursor here.

			ArrayList<String> List = new ArrayList<String>(); // 검사하려하는 어레이리스트

			while (cursor.moveToNext()) {
				List.add(cursor.getString(0));
				Log.w("PHONE NUMBER!!! ",""+cursor.getString(0));
			}

			HashSet<String> hs = new HashSet<String>(List);
			Iterator<String> it = hs.iterator();
			while (it.hasNext()) {
				phoneList.add(it.next());
			}
		} finally {
			Log.w("PHONE NUMBER!!! ","error" );
			// this gets called even if there is an exception somewhere above
			if (cursor != null)
				cursor.close();
		}
		return phoneList;
	}
	
	public Map<String ,Subject_Info> setProfSubList(String profNum){
		
		
		
		Map<String ,Subject_Info> subList = new TreeMap<String, Subject_Info>();
		
		String sql = "select DISTINCT * "
				+ "from "
				+ table_profClass
				+ " pc  inner join ("
				+ table_subject
				+ " s inner join "
				+ table_subject_info
				+ " si on s.subject_num = si.subject_num) t"
				+ " on t.subject_num = pc.subject_num "				
				+ " where pc.prof_num=?";
		
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, new String[] { String.valueOf(profNum)});
			
			// do some work with the cursor here.
			
			while (cursor.moveToNext()) {
				//List.add(cursor.getString(0));
				Log.w("SubList !!! ",""+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" ");
				
				subList.put(cursor.getString(1), new Subject_Info(cursor.getString(3), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8)));
				
			}
			
			return subList;

		} finally {
			//Log.w("PHONE NUMBER!!! ","error" );
			// this gets called even if there is an exception somewhere above
			if (cursor != null)
				cursor.close();
		}
	}
	

	// public Cursor select_stu() {
	// String sql = "select * from " + table_stu+ ";";
	// Cursor results = db.rawQuery(sql, null);
	//
	// results.moveToFirst();
	// ArrayList<apinfo> infos = new ArrayList<apinfo>();
	//
	// while (!results.isAfterLast()) {
	// APinfo info = new APinfo(results.getInt(0), results.getString(1),
	// results.getInt(2), results.getString(3));
	// infos.add(info);
	// results.moveToNext();
	// }
	// results.close();
	// return infos;
	// }

}
