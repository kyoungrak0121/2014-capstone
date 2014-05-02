package com.example.push.activity;

import com.example.push.R;
import com.example.push.table.Globals;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends PreferenceActivity {
	private Globals globals ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);

		globals =Globals.getInstance();
		
		initialize();
	}

	protected void initialize() {
		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
			    
				todoBeforeLaunch();
			   
				getLaunchData();
				
			    finish();
			}
		};
		
		handler.sendEmptyMessageDelayed(0, 2000); // 2√ ∞£ µÙ∑π¿Ã
	}
	
	private void todoBeforeLaunch(){
		
		if( GetPrefInt("first") == 0 ){
			
			insert_student("s1", "1");
			insert_student("s2", "2");
			insert_student("s3", "3");
			insert_student("s4", "4");
			insert_student("s5", "5");
				
			insert_professor("p1", "1");
			insert_professor("p2", "2");
			insert_professor("p3", "3");
			insert_professor("p4", "4");
			
			SetPref("first", 1);
		}
	}
	
	private void getLaunchData(){
		
		globals.setPerson(read_person());
		
	}
}
