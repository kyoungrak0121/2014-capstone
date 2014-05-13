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
	//�ڽ��� Project ID �� �־��ּ���
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
				todoBeforeLaunch(); // �� �ֱ�  
				getLaunchData(); // �ʱ�ȭ 
				
				Log.w(TAG,"show ragid "+read_regId());
				
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				
				finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 3000); // 3s delay
		
	}
	private void setGCM(){
		Log.w(TAG, "Start Registered:");
	 
		//GCMRegistrar.unregister(this); // GCM ��� x 
		  try {
			  GCMRegistrar.checkDevice(this); // ����̽��� GCM ��� Ȯ�� 
	        } catch (Exception e) {
	            // TODO: handle exception
	            Log.e(TAG, "This device can't use GCM");
	            return;
	        }
	         
		
		GCMRegistrar.checkManifest(this); // �Ŵ��佺Ʈ ���� Ȯ��
        
		//GCM ��Ͽ���
		final String regId = GCMRegistrar.getRegistrationId(this); // �޾ƿ� 
      	//��ϵ� ID�� ������ ID���� ���ɴϴ�
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
