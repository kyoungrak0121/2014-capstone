package com.example.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.push.R;
import com.example.push.db.DBManager;
import com.example.push.table.Person;
import com.example.push.table.Professor;
import com.example.push.table.Student;
import com.example.push.table.Subject_Info;
import com.example.push.widget.SlideHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class PreferenceActivity extends Activity{
	
	private SlideHolder mSlideHolder;
	private ListView listView;
	
	
	static final String[] SETTING = new String[] {"setting", "Logout","push setting"};
	
	private boolean mPressFirstBackKey = false;      // Back의 상태값을 저장하기 위한 변수
	private Timer timer;
	
	private DBManager db;
	
	public void setSlideHolder(){

		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
	    mSlideHolder.setAllowInterceptTouch(false);
		
	    listView = (ListView)findViewById(R.id.elv_list);
	    listView.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item,SETTING));
	    
	    listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
                String item = ((TextView)view).getText().toString();
                
                if(item.equals(SETTING[1])){
                	delete_login();
                	startActivity(new Intent(getApplicationContext(),MainActivity.class));
                	finish();
                }else if(item.equals(SETTING[2])){
                	
                }
                            
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });
	}
	
	public void firstExec(){
		
		SharedPreferences prefs = getSharedPreferences("first", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putBoolean("first", Boolean.getBoolean("false"));
		editor.commit();

	}
	
	public boolean isFirst(){
		SharedPreferences prefs = getSharedPreferences("first", Activity.MODE_PRIVATE);
		
		if(prefs.getBoolean("first", true)){
			firstExec();
			return true;
		}
		return false;
	}
	
	
	public boolean insert_login(String id, String pw){
		try{
			SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("login_id", id);
			editor.putString("login_pw", pw);
			editor.commit();
		} catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean delete_login(){
		
		try{
			SharedPreferences prefs = getSharedPreferences("login",
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
		
			editor.remove("login_id");
			editor.remove("login_pw");
				
			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		return true;
	}
	public boolean isCheck_regId(){
		
		SharedPreferences prefs = getSharedPreferences("regId", Activity.MODE_PRIVATE);
		
		if((prefs.getString("regId", null) != null )){
			return true;
		}
		return false;
	}
	
	
	public boolean isCheck_login(){
		
		SharedPreferences prefs = getSharedPreferences("login", Activity.MODE_PRIVATE);
		
		if((prefs.getString("login_id", null) != null ) && (prefs.getString("login_pw", null) != null)){
			return true;
		}
		return false;
	}
	

	public Person check_login(){
		
		SharedPreferences prefs = getSharedPreferences("login", Activity.MODE_PRIVATE);
		
		String id = prefs.getString("login_id",null);		
		String pw = prefs.getString("login_pw",null);
		
		return new Person(id,pw);
	}
	
	public  String read_regId(){
		
		SharedPreferences prefs = getSharedPreferences("regId", Activity.MODE_PRIVATE);
		String regId = prefs.getString("regId", null);
		
		return regId;
	}
	
	public Person read_person(){
		Person person = new Person();
		person.setsList(read_student());
		person.setpList(read_professor());
		return person;
	}
	
	public Map<String,Student> read_student(){
		//Map<String,Student> sList = new HashMap<String,Student>();
		
		db = new DBManager(getApplication());
		
		return db.select_stu();
	}
	
	public  Map<String,Professor> read_professor(){
		
		db = new DBManager(getApplication());

		return db.select_prof();

	}

	public void onBackPressed() {

		if (mPressFirstBackKey == false) { // Back 키가 첫번째로 눌린 경우
			Toast.makeText(getApplication(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_LONG).show();
			mPressFirstBackKey = true;
			// ------------------------------------------------------------------
			// Back키가 2초내에 두번 눌렸는지 감지
			TimerTask second = new TimerTask() {
				@Override
				public void run() {
					timer.cancel();
					timer = null;
					mPressFirstBackKey = false;
				}
			};
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
			timer = new Timer();
			timer.schedule(second, 2000);
		} else
			super.onBackPressed();

	}
	
}
