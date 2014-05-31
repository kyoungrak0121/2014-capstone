package com.example.push;



import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static final String TAG = "GCM";
	private static final String SENDER_ID = "41362468480";
	
	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
			showMessage(context, intent);
		}
	}

	@Override // ���ķ��߻�
	protected void onError(Context context, String msg) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onError!! " + msg);
	}


	@Override // ó�� ����Ҷ� 
	protected void onRegistered(Context context, String regID) {
		// TODO Auto-generated method stub
		
		if(!regID.equals("") || regID != null){
			
			SharedPreferences prefs = getSharedPreferences("regId", Activity.MODE_PRIVATE); 
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("regId",regID);
			editor.commit();
			showToast();
		}
	}

	@Override // GCM ���� ������ 
	protected void onUnregistered(Context context, String regID) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onUnregistered!! " + regID);
	}
	
	public void showToast(){
		Toast.makeText(this, "RegID ��� �Ϸ�", Toast.LENGTH_LONG).show();
	}
	
	private void showMessage(Context context, Intent intent){
		String title = intent.getStringExtra("title");
		String msg = intent.getStringExtra("msg");
		String ticker = intent.getStringExtra("ticker");

 		Log.w("show gcm message","title : " + title);
 		Log.w("show gcm message","msg : " + msg);
 		Log.w("show gcm message","ticker :" + ticker);
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Activity.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.logo,ticker, System.currentTimeMillis());
		//�ش� ������ �����ϴ� �̺�Ʈ�� �ϰ���� �� �Ʒ� �ּ��� Ǯ���ּ���
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, SplashActivity.class)
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP
								| Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		//����
		notification.vibrate = new long[] { 500, 100, 500, 100 };
		//�⺻ ���� ����
		notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		notification.setLatestEventInfo(context,title, msg, pendingIntent);
		notificationManager.notify(0, notification);
	}
}
