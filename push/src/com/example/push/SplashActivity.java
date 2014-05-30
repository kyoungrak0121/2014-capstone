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
	//�ڽ��� Project ID �� �־��ּ���
	private static final String SENDER_ID = "858542600044";
	
	//������
	private String reg_id = "APA91bHAV3oobSzSbgP3KLO9Gsw3FYiTAzEAFohamqXlAEf3dVuuW3DGroO_bUoJKmS2wOGxoYfD7KuZQ2JrX3GO9nLVw9P67Q1mlMUkHSN_2XX2szBW2W_UKX02hF5BbSjSSzi4WzYi";
	//������
	private String reg2_id = "APA91bFf0wfhH0qGks8NN6Orbo2bWpIJrPsXNiIHdyI9LbxFnVIXG5N_uXOVq3m0JpKSPdNjJ5NRnRVom3IaG1_iecZSK6pRnyMhkg1Fsss78Iu-MYIfqNGpQkKFwNwbsKfbtA14xVQdaDqy6pST0kx2Yn2d6ztxmQ";
	//�����
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
					todoBeforeLaunch(); // �� �ֱ�  
				}
				getLaunchData(); // �ʱ�ȭ 
				
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
		
		//����
		manager.insert_stClass("60112367", "1053");
		manager.insert_stClass("60112367", "1071");
		manager.insert_stClass("60112367", "1073");// ĸ����s
		
		//���
		manager.insert_stClass("60092437", "1073"); // ĸ����
		manager.insert_stClass("60092437", "1067");
		manager.insert_stClass("60092437", "1053");
		manager.insert_stClass("60092437", "1041");
		
		//���� 
		manager.insert_stClass("60092385", "1073");//ĸ����
		manager.insert_stClass("60092385", "1068");
		manager.insert_stClass("60092385", "1070");
		
		manager.insert_stClass("60090001", "1073");
		
	}

	private void setSubject_info(DBManager manager) {
		// TODO Auto-generated method stub
		//insert_subject_info(String subject_num ,String subject_day , String subject_period ,String subject_time,  String subject_where )
		
		manager.insert_subject_info("1038","��","2����","2	�ð�","Y5401");
		manager.insert_subject_info("1038","��","5����","2	�ð�","Y5445");
		manager.insert_subject_info("1039","ȭ","5����","2	�ð�","Y5420");
		manager.insert_subject_info("1039","��","5����","2	�ð�","Y5420");
		manager.insert_subject_info("1040","ȭ","7����","2	�ð�","Y5420");
		manager.insert_subject_info("1040","��","7����","2	�ð�","Y5420");
		manager.insert_subject_info("1041","ȭ","5����","2	�ð�","Y5437");
		manager.insert_subject_info("1041","��","5����","2	�ð�","Y5437");
		manager.insert_subject_info("1042","ȭ","7����","2	�ð�","Y5437");
		manager.insert_subject_info("1042","��","7����","2	�ð�","Y5437");
		manager.insert_subject_info("1048","��","10����","	2�ð�","Y5420	");
		manager.insert_subject_info("1034","��","7����","2	�ð�","Y5407");
		manager.insert_subject_info("1034","��","7����","1	�ð�","Y5407");
		manager.insert_subject_info("1036","��","6����","3	�ð�","Y5437");
		manager.insert_subject_info("1037","��","6����","3	�ð�","Y5437");
		manager.insert_subject_info("1043","��","2����","2	�ð�","Y5445");
		manager.insert_subject_info("1043","��","2����","1	�ð�","Y5445");
		manager.insert_subject_info("1045","ȭ","2����","2	�ð�","Y5420");
		manager.insert_subject_info("1045","��","3����","1	�ð�","Y5420");
		manager.insert_subject_info("1046","ȭ","1����","1	�ð�","Y5437");
		manager.insert_subject_info("1046","��","1����","2	�ð�","Y5437");
		manager.insert_subject_info("1047","ȭ","2����","2	�ð�","Y5437");
		manager.insert_subject_info("1047","��","3����","1	�ð�","Y5437");
		manager.insert_subject_info("1049","��","3����","1	�ð�","Y5401");
		manager.insert_subject_info("1049","��","3����","2	�ð�","Y5445");
		manager.insert_subject_info("1050","��","6����","2	�ð�","Y5120");
		manager.insert_subject_info("1050","��","7����","1	�ð�","Y5120");
		manager.insert_subject_info("1051","ȭ","2����","2	�ð�","Y5407");
		manager.insert_subject_info("1051","��","3����","1	�ð�","Y5407");
		manager.insert_subject_info("1052","ȭ","5����","2	�ð�","Y5407");
		manager.insert_subject_info("1052","��","5����","1	�ð�","Y5407");
		manager.insert_subject_info("1053","��","2����","2	�ð�","Y5441");
		manager.insert_subject_info("1053","��","2����","1	�ð�","Y5441");
		manager.insert_subject_info("1054","��","5����","2	�ð�","Y5441");
		manager.insert_subject_info("1054","��","5����","1	�ð�","Y5441");
		manager.insert_subject_info("1055","��","2����","2	�ð�","Y5407");
		manager.insert_subject_info("1055","��","6����","1	�ð�","Y5407");
		manager.insert_subject_info("1056","��","6����","1	�ð�","Y5407");
		manager.insert_subject_info("1056","��","2����","2	�ð�","Y5407");
		manager.insert_subject_info("1057","ȭ","5����","3	�ð�","Y5401");
		manager.insert_subject_info("1058","��","2����","2	�ð�","Y5420");
		manager.insert_subject_info("1058","��","2����","1	�ð�","Y5420");
		manager.insert_subject_info("1059","��","5����","2	�ð�","Y5420");
		manager.insert_subject_info("1059","��","3����","1	�ð�","Y5420");
		manager.insert_subject_info("1060","��","6����","3	�ð�","Y5401");
		manager.insert_subject_info("1061","��","2����","1	�ð�","Y5411");
		manager.insert_subject_info("1061","��","2����","2	�ð�","Y5411");
		manager.insert_subject_info("1062","��","7����","1	�ð�","Y5411");
		manager.insert_subject_info("1062","��","7����","2	�ð�","Y5411");
		manager.insert_subject_info("1063","��","6����","1	�ð�","Y5401");
		manager.insert_subject_info("1063","��","2����","2	�ð�","Y5401");
		manager.insert_subject_info("1064","��","7����","1	�ð�","Y5401");
		manager.insert_subject_info("1064","��","5����","2	�ð�","Y5401");
		manager.insert_subject_info("1067","ȭ","8����","1	�ð�","Y5445");
		manager.insert_subject_info("1067","��","7����","2	�ð�","Y5445");
		manager.insert_subject_info("1068","ȭ","6����","2	�ð�","Y5445");
		manager.insert_subject_info("1068","��","6����","1	�ð�","Y5407");
		manager.insert_subject_info("1069","ȭ","8����","1	�ð�","Y5401");
		manager.insert_subject_info("1069","��","7����","2	�ð�","Y5401");
		manager.insert_subject_info("1575","ȭ","4����","1	�ð�","Y5401");
		manager.insert_subject_info("1575","��","3����","2	�ð�","Y5401");
		manager.insert_subject_info("1077","ȭ","2����","2	�ð�","Y5445");
		manager.insert_subject_info("1077","��","2����","1	�ð�","Y5445");
		manager.insert_subject_info("1078","ȭ","4����","1	�ð�","Y5445");
		manager.insert_subject_info("1078","��","3����","2	�ð�","Y5445");
		manager.insert_subject_info("1079","��","6����","3	�ð�","Y5120");
		manager.insert_subject_info("1497","��","2����","3	�ð�","Y5407");
		manager.insert_subject_info("1498","��","2����","3	�ð�","Y5411");
		manager.insert_subject_info("1499","��","6����","3	�ð�","Y5411");
		manager.insert_subject_info("1065","��","5����","2	�ð�","Y5445");
		manager.insert_subject_info("1065","��","6����","1	�ð�","Y5445");
		manager.insert_subject_info("1066","��","7����","2	�ð�","Y5445");
		manager.insert_subject_info("1066","��","7����","1	�ð�","Y5445");
		manager.insert_subject_info("1070","��","2����","3	�ð�","Y5437");
		manager.insert_subject_info("1071","��","6����","3	�ð�","Y5437");
		manager.insert_subject_info("1072","ȭ","9����","3	�ð�","Y5401");
		manager.insert_subject_info("1073","��","9����","3	�ð�","Y5407");
		manager.insert_subject_info("1074","ȭ","9����","3	�ð�","Y5420");
		manager.insert_subject_info("1075","ȭ","9����","3	�ð�","Y5437");
		manager.insert_subject_info("1080","��","1����","1	�ð�","Y5437");
		manager.insert_subject_info("1080","��","1����","2	�ð�","Y5437");
		manager.insert_subject_info("1081","��","2����","2	�ð�","Y5437");
		manager.insert_subject_info("1081","��","3����","1	�ð�","Y5437");
		manager.insert_subject_info("1082","��","5����","1	�ð�","Y5437");
		manager.insert_subject_info("1082","��","4����","2	�ð�","Y5437");
		
		
	}

	private void setSubject(DBManager manager) {
		// TODO Auto-generated method stub
		//insert_subject(String subject_num ,String subject_name)
		
		manager.insert_subject("1038","C������α׷���" );
		manager.insert_subject("1039","C������α׷���" );
		manager.insert_subject("1040","C������α׷���" );
		manager.insert_subject("1041","C������α׷���" );
		manager.insert_subject("1042","C������α׷���" );
		manager.insert_subject("1048","C����" );
		manager.insert_subject("1034","�ڷᱸ��" );
		manager.insert_subject("1036","�ڷᱸ��" );
		manager.insert_subject("1037","�ڷᱸ��" );
		manager.insert_subject("1043","��ü���� ���α׷���2" );
		manager.insert_subject("1045","��ü���� ���α׷���2" );
		manager.insert_subject("1046","��ü���� ���α׷���2" );
		manager.insert_subject("1047","��ü���� ���α׷���2" );
		manager.insert_subject("1049","��ǻ���ϵ����" );
		manager.insert_subject("1050","��ǻ���ϵ����" );
		manager.insert_subject("1051","��ǻ���ϵ����" );
		manager.insert_subject("1052","��ǻ���ϵ����" );
		manager.insert_subject("1053","��ǻ�;�Ű����" );
		manager.insert_subject("1054","��ǻ�;�Ű����" );
		manager.insert_subject("1055","��ǻ�;�Ű����" );
		manager.insert_subject("1056","��ǻ�;�Ű����" );
		manager.insert_subject("1057","�����ͺ��̽�" );
		manager.insert_subject("1058","�����ͺ��̽�" );
		manager.insert_subject("1059","�����ͺ��̽�" );
		manager.insert_subject("1060","�����ͺ��̽�" );
		manager.insert_subject("1061","�ü��" );
		manager.insert_subject("1062","�ü��" );
		manager.insert_subject("1063","�ü��" );
		manager.insert_subject("1064","�ü��" );
		manager.insert_subject("1067","��ǻ�� ��Ʈ��ũ" );
		manager.insert_subject("1068","��ǻ�� ��Ʈ��ũ" );
		manager.insert_subject("1069","��ǻ�� ��Ʈ��ũ" );
		manager.insert_subject("1575","��ǻ�� ��Ʈ��ũ" );
		manager.insert_subject("1077","����Ʈ���� ����" );
		manager.insert_subject("1078","����Ʈ���� ����" );
		manager.insert_subject("1079","����Ʈ���� ����" );
		manager.insert_subject("1497","��ǻ�� ����Ư��1" );
		manager.insert_subject("1498","��ǻ�� ����Ư��1" );
		manager.insert_subject("1499","��ǻ�� ����Ư��1" );
		manager.insert_subject("1065","��ǻ�ͱ׷��Ƚ�" );
		manager.insert_subject("1066","��ǻ�ͱ׷��Ƚ�" );
		manager.insert_subject("1070","SW��Ű����" );
		manager.insert_subject("1071","SW��Ű����" );
		manager.insert_subject("1072","ĸ���������1" );
		manager.insert_subject("1073","ĸ���������1" );
		manager.insert_subject("1074","ĸ���������1" );
		manager.insert_subject("1075","ĸ���������1" );
		manager.insert_subject("1080","��ǻ�ͺ���" );
		manager.insert_subject("1081","��ǻ�ͺ���" );
		manager.insert_subject("1082","��ǻ�ͺ���" );
		
		//manager.select_prof();
		
	}

	private void setProfessor(DBManager manager) {
		// TODO Auto-generated method stub
	
		//insert_prof(String id,String pwd,String subject_num)
		manager.insert_prof("p1", "1", "�ֿ켮");
		manager.insert_prof("p2", "2", "������");
		manager.insert_prof("p3", "3", "������");
		manager.insert_prof("p4", "4", "������");
		manager.insert_prof("p5", "5", "����");
		manager.insert_prof("p6", "6", "�ּ���");
		manager.insert_prof("p7", "7", "�����");
		manager.insert_prof("p8", "8", "������");
		manager.insert_prof("p9", "9", "�̸�ȣ");
		manager.insert_prof("p10", "10", "����");
		manager.insert_prof("p11", "11", "����"); // ĸ����1
		manager.insert_prof("p12", "12", "������");
		manager.insert_prof("p13", "13", "�ڿ���");
		manager.insert_prof("p14", "14", "������");
		manager.insert_prof("p15", "15", "�̰��");
		manager.insert_prof("p16", "16", "������");
		manager.insert_prof("p17", "17", "�Ź�ȣ");
		manager.insert_prof("p18", "18", "ȫ����");
		manager.insert_prof("p19", "19", "������");
		manager.insert_prof("p20", "20", "����ȫ");
		manager.insert_prof("p21", "21", "�ڽ���");
		manager.insert_prof("p22", "22", "������");
		manager.insert_prof("p23", "23", "�ѽ�ö");
		
		
		//manager.select_prof();
	}

	private void setStudent(DBManager manager) {
		// TODO Auto-generated method stub
		
		//insert_stu(String id, String pwd,String name,String phone,String reg_id)
		
	//	manager.insert_stu("60092385","1234","�ڼ���","01033413321",reg_id);
		
		//manager.insert_stu("60112367","1234","������","01043080309",reg2_id);
	//		manager.insert_stu("60112367","1234","������","01043080309","");
		
		manager.insert_stu("60092437","1234","�����","01092886788",reg3_id);
		
		//testId
		manager.insert_stu("60090001","1234","ȫ�浿","01092886788","");
		
	}

	private void getLaunchData(){
		globals.setPerson(read_person());
		
	}
	
	
}
