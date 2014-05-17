package com.example.push.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
	public MySQLiteOpenHelper(Context context, String name,
		CursorFactory factory,int version){
		super(context, name, factory , version);
		
	}
	public void onCreate(SQLiteDatabase db){
		String sql  = "create table student( " +
					"_id integer primary key autoincrement , "+
					"name text ," + 
					"age integer , " + 
					"address text);";
		db.execSQL(sql);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		String sql = "drop table if exist student";
		db.execSQL(sql);
		
		onCreate(db);
	}
	

}
