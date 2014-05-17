package com.example.push;

import java.util.Map;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.push.db.MySQLiteOpenHelper;
import com.example.push.table.Globals;
import com.example.push.table.Person;
import com.example.push.table.Professor;
import com.example.push.table.Student;

public class MainActivity extends PreferenceActivity {

	private Globals globals;

	private EditText login_id;
	private EditText login_pwd;
	private Button login_btn;
	private Intent intent;
	private Switch switch_btn;

	//database 전역 변수
	public SQLiteDatabase db;
	public MySQLiteOpenHelper helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		//database
	
//		helper = new MySQLiteOpenHelper(MainActivity.this,"person.db",null,1);
//		insert("유저1",18,"경기도");
//		insert("유저2",14,"갱기도");
//		insert("유저3",15,"각기도");
//		
//		select();
//			
		//database
		
		globals = Globals.getInstance();
		
		setSlideHolder();

		globals = Globals.getInstance();
		
		setLayout();
				

		switch_btn = (Switch) findViewById(R.id.switch_btn);

		if (isCheck_login()) {
	
			Toast.makeText(getApplicationContext(), "login ...", Toast.LENGTH_LONG).show();
			Person p = check_login();
			if(authCheck(p.getId(), p.getPw())){
				startActivity(intent);
				finish();	
			}
		}
		
		switch_btn.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton cd,
							boolean isChecking) {
						if (!isChecking) {
							delete_login();
						}
					}
				});

		login_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String id = login_id.getText().toString();
				String pw = login_pwd.getText().toString();
				
				Log.w("msg", "onClick start ");

				if (authCheck(id, pw)) {
					if (switch_btn.isChecked()) {
						Log.w("msg", "toggle ok insert login");
						insert_login(id, pw);
					}
					startActivity(intent);
					finish();
				}
			}
		});
		
	}
	
	private void setLayout() {
		login_id = (EditText) findViewById(R.id.login_id);
		login_pwd = (EditText) findViewById(R.id.login_pwd);
		login_btn = (Button) findViewById(R.id.login_btn);
	}

	private boolean authCheck(String id, String pw) {

		Person person = globals.getPerson();

		
		if (person.getsList().containsKey(id)
				|| person.getpList().containsKey(id)) {

			Map<String, Student> sList = person.getsList();
			Map<String, Professor> pList = person.getpList();

			if (sList.get(id) != null) {
				if (sList.get(id).getId().equals(id)
						&& sList.get(id).getPw().equals(pw)) {
					intent = new Intent(getApplicationContext(),
							StudentActivity.class);
					return true;
				}
			}
			if (pList.get(id) != null) {
				if (pList.get(id).getId().equals(id)
						&& pList.get(id).getPw().equals(pw)) {
					intent = new Intent(getApplicationContext(),
							ProfessorActivity.class);
					return true;
				}
			}
			Toast.makeText(this, " Login failed ( PWD Check ) ", Toast.LENGTH_LONG).show();
			return false;
		} else {
			Toast.makeText(this, " Login failed ( ID Check ) ", Toast.LENGTH_LONG).show();
			return false;
		}
	}
	// database 

	public void insert(String name, int age , String address){
		db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("age", age);
		values.put("address", address);
		db.insert("student", null, values);
	}
	
	public void select(){
		db = helper.getReadableDatabase();
		
		Cursor c = db.query("student", null, null, null, null, null, null);
		
		while(c.moveToNext()){
			int _id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex("name"));
			int age = c.getInt(c.getColumnIndex("age"));
			String address = c.getString(c.getColumnIndex("address"));
			Log.i("db" , "id : " + _id + ",name : "+ name + ",age :" + age + ",address : " +address);  
			
		}
	}
	
}
