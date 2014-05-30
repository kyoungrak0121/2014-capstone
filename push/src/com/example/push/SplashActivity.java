package com.example.push;

import com.example.push.R;
import com.example.push.db.DBManager;
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
import android.view.View;
import android.view.ViewGroup;

public class SplashActivity extends SuperActivity {
	
	Globals globals ;

	private static final String TAG = "GCM";
	//자신의 Project ID 를 넣어주세요
	private static final String SENDER_ID = "858542600044";
	
	//세진이
	private String reg_id = "APA91bHAV3oobSzSbgP3KLO9Gsw3FYiTAzEAFohamqXlAEf3dVuuW3DGroO_bUoJKmS2wOGxoYfD7KuZQ2JrX3GO9nLVw9P67Q1mlMUkHSN_2XX2szBW2W_UKX02hF5BbSjSSzi4WzYi";
	//혜인이
	private String reg2_id = "APA91bFf0wfhH0qGks8NN6Orbo2bWpIJrPsXNiIHdyI9LbxFnVIXG5N_uXOVq3m0JpKSPdNjJ5NRnRVom3IaG1_iecZSK6pRnyMhkg1Fsss78Iu-MYIfqNGpQkKFwNwbsKfbtA14xVQdaDqy6pST0kx2Yn2d6ztxmQ";
	//경락이
	private String reg3_id = "APA91bFmaq1e1XZdcnV4C7QmmFwvAZcjKIgIkPsuATrPEsHgd7ZjdqNGFm1v1_NSbn3QuHfCry-4EluN_PSk_9nQZLJpCrOF3h_UhP0weYXluCvHqj5x63VpZQFyX3r450PRRfTVdATrx17j2XHOHtt3Oo4Uq3wifg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);
		
		globals = Globals.getInstance();	

		initialize();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		 unbindDrawables(findViewById(R.id.container));
	        System.gc();
	};
	 private void unbindDrawables(View view) {
	        if (view.getBackground() != null) {
	            view.getBackground().setCallback(null);
	        }
	        if (view instanceof ViewGroup) {
	            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
	                unbindDrawables(((ViewGroup) view).getChildAt(i));
	            }
	            ((ViewGroup) view).removeAllViews();
	        }
	    }

	protected void initialize() {
		

		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {

				setGCM();
				
				if(isFirst()){
					todoBeforeLaunch(); // 값 넣기  
				}
				getLaunchData(); // 초기화 
				
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

		DBManager manager = new DBManager(this);

		setStudent(manager);
		setProfessor(manager);
		setSubject(manager);
		setSubject_info(manager);
		setStClass(manager);
		setProfClass(manager);
		
	}
	
	private void setProfClass(DBManager manager) {
		// TODO Auto-generated method stub
		//manager.insert_profClass(prof_num, subject_num);
		
		manager.insert_profClass("p1","1038");
		manager.insert_profClass("p2","1039");
		manager.insert_profClass("p2","1040");
		manager.insert_profClass("p3","1041");
		manager.insert_profClass("p3","1042");
		manager.insert_profClass("p2","1048");
		manager.insert_profClass("p4","1034");
		manager.insert_profClass("p5","1036");
		manager.insert_profClass("p5","1037");
		manager.insert_profClass("p6","1043");
		manager.insert_profClass("p7","1045");
		manager.insert_profClass("p8","1046");
		manager.insert_profClass("p8","1047");
		manager.insert_profClass("p9","1049");
		manager.insert_profClass("p9","1050");
		manager.insert_profClass("p10","1051");
		manager.insert_profClass("p10","1052");
		manager.insert_profClass("p11","1053");
		manager.insert_profClass("p11","1054");
		manager.insert_profClass("p12","1055");
		manager.insert_profClass("p12","1056");
		manager.insert_profClass("p13","1057");
		manager.insert_profClass("p14","1058");
		manager.insert_profClass("p14","1059");
		manager.insert_profClass("p15","1060");
		manager.insert_profClass("p16","1061");
		manager.insert_profClass("p16","1062");
		manager.insert_profClass("p17","1063");
		manager.insert_profClass("p17","1064");
		manager.insert_profClass("p18","1067");
		manager.insert_profClass("p19","1068");
		manager.insert_profClass("p19","1069");
		manager.insert_profClass("p18","1575");
		manager.insert_profClass("p6","1077");
		manager.insert_profClass("p6","1078");
		manager.insert_profClass("p20","1079");
		manager.insert_profClass("p17","1497");
		manager.insert_profClass("p21","1498");
		manager.insert_profClass("p21","1499");
		manager.insert_profClass("p1","1065");
		manager.insert_profClass("p1","1066");
		manager.insert_profClass("p22","1070");
		manager.insert_profClass("p22","1071");
		manager.insert_profClass("p4","1072");
		manager.insert_profClass("p12","1073");
		manager.insert_profClass("p14","1074");
		manager.insert_profClass("p9","1075");
		manager.insert_profClass("p23","1080");
		manager.insert_profClass("p23","1081");
		manager.insert_profClass("p23","1082");
	}

	private void setStClass(DBManager manager) {
		// TODO Auto-generated method stub
		//manager.insert_stClass(stu_id, subject_num);
		
		//혜인
		manager.insert_stClass("60112367", "1053");
		manager.insert_stClass("60112367", "1071");
		manager.insert_stClass("60112367", "1073");// 캡스톤s
		
		//경락
		manager.insert_stClass("60092437", "1073"); // 캡스톤
		manager.insert_stClass("60092437", "1067");
		manager.insert_stClass("60092437", "1053");
		manager.insert_stClass("60092437", "1041");
		
		//세진 
		manager.insert_stClass("60092385", "1073");//캡스톤
		manager.insert_stClass("60092385", "1068");
		manager.insert_stClass("60092385", "1070");
		
		manager.insert_stClass("60090001", "1073");
		
	}

	private void setSubject_info(DBManager manager) {
		// TODO Auto-generated method stub
		//insert_subject_info(String subject_num ,String subject_day , String subject_period ,String subject_time,  String subject_where )
		
		manager.insert_subject_info("1038","수","2교시","2	시간","Y5401");
		manager.insert_subject_info("1038","목","5교시","2	시간","Y5445");
		manager.insert_subject_info("1039","화","5교시","2	시간","Y5420");
		manager.insert_subject_info("1039","목","5교시","2	시간","Y5420");
		manager.insert_subject_info("1040","화","7교시","2	시간","Y5420");
		manager.insert_subject_info("1040","목","7교시","2	시간","Y5420");
		manager.insert_subject_info("1041","화","5교시","2	시간","Y5437");
		manager.insert_subject_info("1041","목","5교시","2	시간","Y5437");
		manager.insert_subject_info("1042","화","7교시","2	시간","Y5437");
		manager.insert_subject_info("1042","목","7교시","2	시간","Y5437");
		manager.insert_subject_info("1048","수","10교시","	2시간","Y5420	");
		manager.insert_subject_info("1034","월","7교시","2	시간","Y5407");
		manager.insert_subject_info("1034","수","7교시","1	시간","Y5407");
		manager.insert_subject_info("1036","월","6교시","3	시간","Y5437");
		manager.insert_subject_info("1037","수","6교시","3	시간","Y5437");
		manager.insert_subject_info("1043","월","2교시","2	시간","Y5445");
		manager.insert_subject_info("1043","수","2교시","1	시간","Y5445");
		manager.insert_subject_info("1045","화","2교시","2	시간","Y5420");
		manager.insert_subject_info("1045","목","3교시","1	시간","Y5420");
		manager.insert_subject_info("1046","화","1교시","1	시간","Y5437");
		manager.insert_subject_info("1046","목","1교시","2	시간","Y5437");
		manager.insert_subject_info("1047","화","2교시","2	시간","Y5437");
		manager.insert_subject_info("1047","목","3교시","1	시간","Y5437");
		manager.insert_subject_info("1049","월","3교시","1	시간","Y5401");
		manager.insert_subject_info("1049","수","3교시","2	시간","Y5445");
		manager.insert_subject_info("1050","월","6교시","2	시간","Y5120");
		manager.insert_subject_info("1050","수","7교시","1	시간","Y5120");
		manager.insert_subject_info("1051","화","2교시","2	시간","Y5407");
		manager.insert_subject_info("1051","목","3교시","1	시간","Y5407");
		manager.insert_subject_info("1052","화","5교시","2	시간","Y5407");
		manager.insert_subject_info("1052","목","5교시","1	시간","Y5407");
		manager.insert_subject_info("1053","월","2교시","2	시간","Y5441");
		manager.insert_subject_info("1053","수","2교시","1	시간","Y5441");
		manager.insert_subject_info("1054","월","5교시","2	시간","Y5441");
		manager.insert_subject_info("1054","수","5교시","1	시간","Y5441");
		manager.insert_subject_info("1055","월","2교시","2	시간","Y5407");
		manager.insert_subject_info("1055","수","6교시","1	시간","Y5407");
		manager.insert_subject_info("1056","월","6교시","1	시간","Y5407");
		manager.insert_subject_info("1056","수","2교시","2	시간","Y5407");
		manager.insert_subject_info("1057","화","5교시","3	시간","Y5401");
		manager.insert_subject_info("1058","월","2교시","2	시간","Y5420");
		manager.insert_subject_info("1058","수","2교시","1	시간","Y5420");
		manager.insert_subject_info("1059","월","5교시","2	시간","Y5420");
		manager.insert_subject_info("1059","수","3교시","1	시간","Y5420");
		manager.insert_subject_info("1060","수","6교시","3	시간","Y5401");
		manager.insert_subject_info("1061","월","2교시","1	시간","Y5411");
		manager.insert_subject_info("1061","수","2교시","2	시간","Y5411");
		manager.insert_subject_info("1062","월","7교시","1	시간","Y5411");
		manager.insert_subject_info("1062","수","7교시","2	시간","Y5411");
		manager.insert_subject_info("1063","월","6교시","1	시간","Y5401");
		manager.insert_subject_info("1063","목","2교시","2	시간","Y5401");
		manager.insert_subject_info("1064","월","7교시","1	시간","Y5401");
		manager.insert_subject_info("1064","목","5교시","2	시간","Y5401");
		manager.insert_subject_info("1067","화","8교시","1	시간","Y5445");
		manager.insert_subject_info("1067","목","7교시","2	시간","Y5445");
		manager.insert_subject_info("1068","화","6교시","2	시간","Y5445");
		manager.insert_subject_info("1068","목","6교시","1	시간","Y5407");
		manager.insert_subject_info("1069","화","8교시","1	시간","Y5401");
		manager.insert_subject_info("1069","목","7교시","2	시간","Y5401");
		manager.insert_subject_info("1575","화","4교시","1	시간","Y5401");
		manager.insert_subject_info("1575","금","3교시","2	시간","Y5401");
		manager.insert_subject_info("1077","화","2교시","2	시간","Y5445");
		manager.insert_subject_info("1077","목","2교시","1	시간","Y5445");
		manager.insert_subject_info("1078","화","4교시","1	시간","Y5445");
		manager.insert_subject_info("1078","목","3교시","2	시간","Y5445");
		manager.insert_subject_info("1079","금","6교시","3	시간","Y5120");
		manager.insert_subject_info("1497","금","2교시","3	시간","Y5407");
		manager.insert_subject_info("1498","금","2교시","3	시간","Y5411");
		manager.insert_subject_info("1499","금","6교시","3	시간","Y5411");
		manager.insert_subject_info("1065","월","5교시","2	시간","Y5445");
		manager.insert_subject_info("1065","수","6교시","1	시간","Y5445");
		manager.insert_subject_info("1066","월","7교시","2	시간","Y5445");
		manager.insert_subject_info("1066","수","7교시","1	시간","Y5445");
		manager.insert_subject_info("1070","금","2교시","3	시간","Y5437");
		manager.insert_subject_info("1071","금","6교시","3	시간","Y5437");
		manager.insert_subject_info("1072","화","9교시","3	시간","Y5401");
		manager.insert_subject_info("1073","월","9교시","3	시간","Y5407");
		manager.insert_subject_info("1074","화","9교시","3	시간","Y5420");
		manager.insert_subject_info("1075","화","9교시","3	시간","Y5437");
		manager.insert_subject_info("1080","월","1교시","1	시간","Y5437");
		manager.insert_subject_info("1080","수","1교시","2	시간","Y5437");
		manager.insert_subject_info("1081","월","2교시","2	시간","Y5437");
		manager.insert_subject_info("1081","수","3교시","1	시간","Y5437");
		manager.insert_subject_info("1082","월","5교시","1	시간","Y5437");
		manager.insert_subject_info("1082","수","4교시","2	시간","Y5437");
		
		
	}

	private void setSubject(DBManager manager) {
		// TODO Auto-generated method stub
		//insert_subject(String subject_num ,String subject_name)
		
		manager.insert_subject("1038","C언어프로그래밍" );
		manager.insert_subject("1039","C언어프로그래밍" );
		manager.insert_subject("1040","C언어프로그래밍" );
		manager.insert_subject("1041","C언어프로그래밍" );
		manager.insert_subject("1042","C언어프로그래밍" );
		manager.insert_subject("1048","C언어연습" );
		manager.insert_subject("1034","자료구조" );
		manager.insert_subject("1036","자료구조" );
		manager.insert_subject("1037","자료구조" );
		manager.insert_subject("1043","객체지향 프로그래밍2" );
		manager.insert_subject("1045","객체지향 프로그래밍2" );
		manager.insert_subject("1046","객체지향 프로그래밍2" );
		manager.insert_subject("1047","객체지향 프로그래밍2" );
		manager.insert_subject("1049","컴퓨터하드웨어" );
		manager.insert_subject("1050","컴퓨터하드웨어" );
		manager.insert_subject("1051","컴퓨터하드웨어" );
		manager.insert_subject("1052","컴퓨터하드웨어" );
		manager.insert_subject("1053","컴퓨터아키텍쳐" );
		manager.insert_subject("1054","컴퓨터아키텍쳐" );
		manager.insert_subject("1055","컴퓨터아키텍쳐" );
		manager.insert_subject("1056","컴퓨터아키텍쳐" );
		manager.insert_subject("1057","데이터베이스" );
		manager.insert_subject("1058","데이터베이스" );
		manager.insert_subject("1059","데이터베이스" );
		manager.insert_subject("1060","데이터베이스" );
		manager.insert_subject("1061","운영체제" );
		manager.insert_subject("1062","운영체제" );
		manager.insert_subject("1063","운영체제" );
		manager.insert_subject("1064","운영체제" );
		manager.insert_subject("1067","컴퓨터 네트워크" );
		manager.insert_subject("1068","컴퓨터 네트워크" );
		manager.insert_subject("1069","컴퓨터 네트워크" );
		manager.insert_subject("1575","컴퓨터 네트워크" );
		manager.insert_subject("1077","소프트웨어 공학" );
		manager.insert_subject("1078","소프트웨어 공학" );
		manager.insert_subject("1079","소프트웨어 공학" );
		manager.insert_subject("1497","컴퓨터 공학특론1" );
		manager.insert_subject("1498","컴퓨터 공학특론1" );
		manager.insert_subject("1499","컴퓨터 공학특론1" );
		manager.insert_subject("1065","컴퓨터그래픽스" );
		manager.insert_subject("1066","컴퓨터그래픽스" );
		manager.insert_subject("1070","SW아키텍쳐" );
		manager.insert_subject("1071","SW아키텍쳐" );
		manager.insert_subject("1072","캡스톤디자인1" );
		manager.insert_subject("1073","캡스톤디자인1" );
		manager.insert_subject("1074","캡스톤디자인1" );
		manager.insert_subject("1075","캡스톤디자인1" );
		manager.insert_subject("1080","컴퓨터보안" );
		manager.insert_subject("1081","컴퓨터보안" );
		manager.insert_subject("1082","컴퓨터보안" );
		
		//manager.select_prof();
		
	}

	private void setProfessor(DBManager manager) {
		// TODO Auto-generated method stub
	
		//insert_prof(String id,String pwd,String subject_num)
		manager.insert_prof("p1", "1", "주우석");
		manager.insert_prof("p2", "2", "조세형");
		manager.insert_prof("p3", "3", "노재희");
		manager.insert_prof("p4", "4", "윤병주");
		manager.insert_prof("p5", "5", "김상귀");
		manager.insert_prof("p6", "6", "최성운");
		manager.insert_prof("p7", "7", "이충기");
		manager.insert_prof("p8", "8", "장희정");
		manager.insert_prof("p9", "9", "이명호");
		manager.insert_prof("p10", "10", "김상균");
		manager.insert_prof("p11", "11", "김상운"); // 캡스톤1
		manager.insert_prof("p12", "12", "장혁수");
		manager.insert_prof("p13", "13", "박영배");
		manager.insert_prof("p14", "14", "전종훈");
		manager.insert_prof("p15", "15", "이경미");
		manager.insert_prof("p16", "16", "류연승");
		manager.insert_prof("p17", "17", "신민호");
		manager.insert_prof("p18", "18", "홍석원");
		manager.insert_prof("p19", "19", "박현민");
		manager.insert_prof("p20", "20", "김진홍");
		manager.insert_prof("p21", "21", "박승진");
		manager.insert_prof("p22", "22", "김유경");
		manager.insert_prof("p23", "23", "한승철");
		
		
		//manager.select_prof();
	}

	private void setStudent(DBManager manager) {
		// TODO Auto-generated method stub
		
		//insert_stu(String id, String pwd,String name,String phone,String reg_id)
		
	//	manager.insert_stu("60092385","1234","박세진","01033413321",reg_id);
		
		//manager.insert_stu("60112367","1234","서혜인","01043080309",reg2_id);
	//		manager.insert_stu("60112367","1234","서혜인","01043080309","");
		
		manager.insert_stu("60092437","1234","정경락","01092886788",reg3_id);
		
		//testId
		manager.insert_stu("60090001","1234","홍길동","01092886788","");
		
	}

	private void getLaunchData(){
		globals.setPerson(read_person());
		
	}
	
	
}
