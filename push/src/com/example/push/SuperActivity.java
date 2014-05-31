package com.example.push;


import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.example.push.R;
import com.example.push.db.DBManager;
import com.example.push.table.Person;
import com.example.push.table.Professor;
import com.example.push.table.Student;
import com.example.push.widget.SlideHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SuperActivity extends Activity{
	
	private SlideHolder mSlideHolder;
	private LinearLayout slideAboutBtn;
	private LinearLayout slideSettingBtn;
	private LinearLayout slideLoginBtn;
	private TextView slideLogin;

	private boolean mPressFirstBackKey = false;      // Back의 상태값을 저장하기 위한 변수
	private Timer timer;
	
	private DBManager db;
	
	public static Context context;
	
	public void setSlideHolder(){

		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
	    mSlideHolder.setAllowInterceptTouch(false);
	    setLayout();
	}
	
	private void setLayout() {
		// TODO Auto-generated method stub
		
		slideAboutBtn = (LinearLayout)findViewById(R.id.slide_aboutbtn);
		slideSettingBtn = (LinearLayout)findViewById(R.id.slide_settingbtn);
		slideLoginBtn =  (LinearLayout)findViewById(R.id.slide_loginbtn);
		slideLogin =  (TextView)findViewById(R.id.slide_login);
		
		slideAboutBtn.setClickable(true); 
		slideLoginBtn.setClickable(true); 
		slideSettingBtn.setClickable(true); 
		
		slideAboutBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aboutDialog();
				//Toast.makeText(getApplication(), "About btn", Toast.LENGTH_SHORT).show();
			}
		});
		
		slideSettingBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				settingDialog();
			}
		});
		slideLoginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isCheck_login()){
					slideLogin.setText("Login");
					delete_login();
					startActivity(new Intent(getApplicationContext(),MainActivity.class));   	
			    	finish();
				}
			 
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
	
	public void setLoginText(){
		slideLogin.setText("Logout");
	}
	
	private void aboutDialog() {
		
		Context mContext = SuperActivity.context;
		
	    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.about_dialog,(ViewGroup) findViewById(R.id.popup));
		
	    AlertDialog.Builder aDialog = new AlertDialog.Builder(SuperActivity.context);

		aDialog.setTitle("About");
		aDialog.setView(layout);
		
		aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		// 팝업창 생성
		AlertDialog ad = aDialog.create();
		ad.show();// 보여줌!
	}
	
	private void settingDialog(){
	
		boolean check = isSMSSend();
		final CharSequence[] items = {"SMS 보내기"};
		final boolean[] itemsChecked = {check};

		AlertDialog.Builder builder = new AlertDialog.Builder(SuperActivity.context);
		builder.setTitle("Push Setting");
		builder.setMultiChoiceItems( items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(getApplicationContext(),items[which] + "  " + Boolean.toString(isChecked),Toast.LENGTH_SHORT).show();
				setSMSSend(isChecked);
			}
		})
		.setNegativeButton("설정", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {		
				Toast.makeText(getApplicationContext(),"설정 완료",Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});	

		AlertDialog alert = builder.create();
		alert.show();

	}
	
	public boolean isSMSSend(){
		
		boolean isSMSCheck;
		
		SharedPreferences prefs = getSharedPreferences("SMSCheck", Activity.MODE_PRIVATE);
		isSMSCheck = prefs.getBoolean("SMSCheck", true);
		
		if(isSMSCheck){
			return true; // 보내기 
		}
		return false; // false 면 안 보내기 
	}
	public void setSMSSend(boolean isChecked){
		SharedPreferences prefs = getSharedPreferences("SMSCheck", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("SMSCheck", isChecked);
		editor.commit();
	}

}
