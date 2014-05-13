package com.example.push;

import com.example.push.R;
import com.example.push.table.Globals;
import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SplashActivity extends PreferenceActivity {
	
	Globals globals ;

	private static final String TAG = "GCM";
	//자신의 Project ID 를 넣어주세요
	private static final String SENDER_ID = "858542600044";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);
		
		globals = Globals.getInstance();	

		
		initialize();
	}

	protected void initialize() {
		

		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {

				setGCM();
				todoBeforeLaunch(); // 값 넣기  
				getLaunchData(); // 초기화 
				
				Log.w(TAG,"show ragid "+read_regId());
				
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				
				finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 3000); // 3s delay
		
	}
	private void setGCM(){
		Log.w(TAG, "Start Registered:");
	 
		//GCMRegistrar.unregister(this); // GCM 사용 x 
		  try {
			  GCMRegistrar.checkDevice(this); // 디바이스의 GCM 사용 확인 
	        } catch (Exception e) {
	            // TODO: handle exception
	            Log.e(TAG, "This device can't use GCM");
	            return;
	        }
	         
		
		GCMRegistrar.checkManifest(this); // 매니페스트 설정 확인
        
		//GCM 등록여부
		final String regId = GCMRegistrar.getRegistrationId(this); // 받아옴 
      	//등록된 ID가 없으면 ID값을 얻어옵니다
      	if (regId.equals("") || regId == null) {
      		GCMRegistrar.register(this, SENDER_ID);
      	}else{
      		Log.w(TAG, "Already Registered : " + regId);
      	}
      	
      	
    	if(isCheck_regId()){
    		GCMRegistrar.register(this, SENDER_ID);
      	}
      	
	}
	private void todoBeforeLaunch() {

		if (GetPrefInt("first") == 0) {

			insert_student("s1", "1");
			insert_student("s2", "2");
			insert_student("s3", "3");
			insert_student("s4", "4");
			insert_student("s5", "5");

			insert_professor("p1", "1");
			insert_professor("p2", "2");
			insert_professor("p3", "3");
			insert_professor("p4", "4");
			
		
		}
	}
	
	private void getLaunchData(){
		globals.setPerson(read_person());
	}
	
	
}
