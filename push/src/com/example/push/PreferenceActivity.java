package com.example.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.push.R;
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

	private final String size_student = "s_student";
	private final String size_professor = "s_professor";
	private final String login = "login";
	private final String size_regId = "s_regId";
	
	private SlideHolder mSlideHolder;
	private ListView listView;
	
	
	static final String[] SETTING = new String[] {"setting", "Logout","push setting"};
	
	private boolean mPressFirstBackKey = false;      // Back의 상태값을 저장하기 위한 변수
	private Timer timer;
	
	
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
                	/*
                
    				if(isChecked){
    					
    					GCMRegistrar.checkDevice(mContext);
    					GCMRegistrar.checkManifest(mContext);
    					if(GCMRegistrar.getRegistrationId(mContext).equals("")){
    						GCMRegistrar.register(mContext, PROJECT_ID);
    					}else{
    					
    						GCMRegistrar.unregister(mContext);
    						GCMRegistrar.register(mContext, PROJECT_ID);
    					}
    				}
    				
    				else{
    					
    					GCMRegistrar.unregister(mContext);
    				}
    				*/
                }
                            
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });
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
	
	public boolean insert_student(String id, String pw){
		int size = GetPrefInt(size_student);
		
		try{
			SharedPreferences prefs = getSharedPreferences("student", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("id_" + size, id);
			editor.putString("pw_" + size, pw);
			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_student, size+1);
		return true;
	}
	
	public boolean insert_professor(String id, String pw){
		int size = GetPrefInt(size_professor);
		
		try{
			SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("id_" + size, id);
			editor.putString("pw_" + size, pw);

			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_professor, size+1);
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
	
	public boolean delete_student(String id){
		
		final int size = GetPrefInt(size_student);
		try{
			SharedPreferences prefs = getSharedPreferences("student",
					Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			
			for( int i = 0; i < size; i++ ){
				String src = prefs.getString("id_" + i, null);
				if( src.equals(id) == true ){
					editor.remove("id_" + i);
					editor.remove("pw_" + i);
					
					for( int j = i+1; j <= size; j++ ){
						editor.putString("id_" + (j-1), prefs.getString("id_"+j, null));
						editor.putString("pw_" + (j-1), prefs.getString("pw_"+j, null));
					}
				}
			}
			editor.commit();
		} catch(Exception e){
			return false;
		}
		
		SetPref(size_student, size-1);
		return true;
	}
	
	public boolean delete_professor(String id){
		final int size = GetPrefInt(size_professor);
		
		try{
			SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			
			for( int i = 0; i < size; i++ ){
				String src = prefs.getString("id_" + i, null);
				if( src.equals(id) == true ){
					editor.remove("id_" + i);
					editor.remove("pw_" + i);
					
					for( int j = i+1; j <= size; j++ ){
						editor.putString("id_" + (j-1), prefs.getString("id_" + j, null));
						editor.putString("pw_" + (j-1), prefs.getString("pw_" + j, null));
					}
				}
			}
			editor.commit();
		} catch(Exception e){
			return false;
		}
		

		SetPref(size_student, size-1);
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
		Map<String,Student> sList = new HashMap<String,Student>();
		
		int size = GetPrefInt(size_student);
		
		SharedPreferences prefs = getSharedPreferences("student", Activity.MODE_PRIVATE);
		
		for( int i = 0; i < size; i++ ){
			
			String id = prefs.getString("id_" + i, null);		
			String pw = prefs.getString("pw_" + i, null);
	
			sList.put(id,new Student(id,pw));
		}
		
		return sList;
	}
	
	public  Map<String,Professor> read_professor(){
		 Map<String,Professor> pList = new HashMap<String,Professor>();
		
		int size = GetPrefInt(size_professor);
		
		SharedPreferences prefs = getSharedPreferences("professor", Activity.MODE_PRIVATE);
		
		for( int i = 0; i < size; i++ ){
			String id = prefs.getString("id_" + i, null);
			String pw = prefs.getString("pw_" + i, null);

			pList.put(id,new Professor(id, pw));
		}
		return pList;
	}

	
	public void SetPref(String key, int value) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public int GetPrefInt(String key) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		return prefs.getInt(key, 0);
	}

	public void DeletePref(String key) {
		SharedPreferences prefs = getSharedPreferences("noname",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public boolean hideSoftInputWindow(View edit_view, boolean bState) {

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		if (bState)
			return imm.showSoftInput(edit_view, 0);
		else
			return imm.hideSoftInputFromWindow(edit_view.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

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
