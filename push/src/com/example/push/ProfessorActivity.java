package com.example.push;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.push.R;
import com.example.push.table.Globals;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ProfessorActivity extends PreferenceActivity {

	private static final String TAG = "GCM";
	
	private Sender 			    gcmSender = null;				//GCM Sender
	private Message 			gcmMessage = null;			//GCM Message
	private Result 				gcmResult = null;				//GCM Result(���� ����)
//	private MulticastResult 	gcmMultiResult;		//GCM Multi Result(�ϰ� ����)
	
	private Globals globals;
	
	//�������ۿ� �ʿ��� ����
	private String  registrationId ;
	//DB���� RegID�� �������� ���� ������� ���� ������ �ּ� 
	//private static final String SELECT_PAGE = "http://�ڽ��� ���� ������/select_registration.php";
	//�Ľ��ϱ� ���� �����͸� ���� ����
	//private static String JSON = null;
	//������ �ֿܼ��� �߱޹��� API Key
	private static String 		API_KEY = "AIzaSyC0IIOyF2A2Sg_Sto0BmF6-QQRn4qM0sq8";
	//�޼����� ���� ID(?)������ �����ϸ� �˴ϴ�. �޼����� �ߺ������� ���� ���� �������� �����մϴ�
	private static String 		COLLAPSE_KEY = String.valueOf(Math.random() % 100 + 1);
	//��Ⱑ Ȱ��ȭ ������ �� ������ ������. 
	private static boolean 	DELAY_WHILE_IDLE = true;
	//��Ⱑ ��Ȱ��ȭ ������ �� GCM Storage�� �����Ǵ� �ð�
	private static int			TIME_TO_LIVE = 3;
	//�޼��� ���� ���н� ��õ��� Ƚ��
	private static int 			RETRY = 3;
	
	private EditText pushTitle;
	private EditText pushMessage;
	private TextView pushLength;
	private Button pushTrans;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_professor);

		globals = Globals.getInstance();
		
		pushTitle = (EditText) findViewById(R.id.pushTitle);
		pushMessage = (EditText) findViewById(R.id.pushMessage);
		pushLength = (TextView) findViewById(R.id.pushLength);

		pushTrans = (Button) findViewById(R.id.pushTrans);

		setSlideHolder();
		
		
		pushTrans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pushTitle.length() == 0 || pushMessage.length() == 0){
					Toast.makeText(getApplicationContext(), "�Է¸޽����� ���� �Է��ϼ���", Toast.LENGTH_LONG).show();
				}else{
					
					setMessage();
					
					new Thread(new Runnable() {	
						@Override
						public void run() {
							// TODO Auto-generated method stub
							sendMessage();		
						}
					}).start();
							
				}
			}
		});
	}
	
	public void setMessage(){
	
		gcmSender = new Sender(API_KEY);
		
		gcmMessage = new Message.Builder()
			.collapseKey(COLLAPSE_KEY)
			.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
			.addData("ticker", "��������")
			.addData("title", pushTitle.getText().toString())
			.addData("msg", pushMessage.getText().toString())
			.build();

	}
	
	public void sendMessage(){
		//�ϰ����۽ÿ� ���
//		try {
//			gcmMultiResult = gcmSender.send(gcmMessage, registrationIds, RETRY);
//		} catch (IOException e) {
//			Log.w(TAG, "IOException " + e.getMessage());
//		}
//		Log.w(TAG, "getCanonicalIds : " + gcmMultiResult.getCanonicalIds() + "\n" + 
//				"getSuccess : " + gcmMultiResult.getSuccess() + "\n" + 
//				"getTotal : " + gcmMultiResult.getTotal() + "\n" + 
//				"getMulticastId : " + gcmMultiResult.getMulticastId());
		
		//�������۽ÿ� ���
		try {
			Log.w(TAG," "+gcmMessage);
			//Log.w(TAG," "+globals.getRegistrationIds().get(0));
			Log.w(TAG," "+RETRY);
			
			String s = "APA91bFWrqz8nnRHxnsB8TqcgsIq5Pv3pFA37bITJYefutiUj0YDww5hq2It55KrYUXo6J1mmOgoHLr0DoNK2vaRjKo1qYiXT6iaKKinqxOQ6V3RpZl-fGEHP9alCUUWq4bmdF4gRQ9Wk1tBYmlUh1uPoQiyhQ0ZLQ";
			
			gcmResult = gcmSender.send(gcmMessage,s,RETRY);
		 	
		}catch(IOException e) {
			Log.w(TAG, "IOException " + e.getMessage());
		}
	}

}