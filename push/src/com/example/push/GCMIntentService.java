package com.example.push;



import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static final String TAG = "GCM";
//	private static final String INSERT_PAGE = "http://자신의 서버 아이피/insert_registration.php";
	private static final String SENDER_ID = "41362468480";
//	private GCMHttpConnect httpConnect = null;
	
	
	/*
	private GCMHttpConnect.Request httpRequest = new GCMHttpConnect.Request() {
		
	@Override
	public void OnComplete() {
			// TODO Auto-generated method stub
			showToast();
		}
	};
	*/
	
	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
			showMessage(context, intent);
		}
	}

	@Override // ㅇㅔ러발생
	protected void onError(Context context, String msg) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onError!! " + msg);
	}


	@Override // 처음 등록할때 
	protected void onRegistered(Context context, String regID) {
		// TODO Auto-generated method stub
		

		if(!regID.equals("") || regID != null){
			
			SharedPreferences prefs = getSharedPreferences("regId", Activity.MODE_PRIVATE); 
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("regId",regID);
			editor.commit();
			
//			단일전송일때 주석처리
//			insertRegistrationID(regID);
		}
	}

	@Override // GCM 해제 했을때 
	protected void onUnregistered(Context context, String regID) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onUnregistered!! " + regID);
	}
	
	public void showToast(){
		Toast.makeText(this, "RegID 등록 완료", Toast.LENGTH_LONG).show();
	}
	
	private void showMessage(Context context, Intent intent){
		String title = intent.getStringExtra("title");
		String msg = intent.getStringExtra("msg");
		String ticker = intent.getStringExtra("ticker");
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Activity.NOTIFICATION_SERVICE);
		
//		해당 어플을 실행하는 이벤트를 하고싶을 때 아래 주석을 풀어주세요
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, 
				new Intent(context, SplashActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0);
//		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
		
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = ticker;
		notification.when = System.currentTimeMillis();
		notification.vibrate = new long[] { 500, 100, 500, 100 };
		notification.sound = Uri.parse("/system/media/audio/notifications/20_Cloud.ogg");
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, title, msg, pendingIntent);
		
		notificationManager.notify(0, notification);
	}
	
	/*
	public void insertRegistrationID(String id){
		httpConnect = new GCMHttpConnect(INSERT_PAGE + "?regID=" + id, httpRequest);
		httpConnect.start();
	}
	*/
}
