package com.example.push.professor.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.push.R;
import com.example.push.table.Globals;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SuperFragment extends Fragment {

	private static final String TAG = "GCM";

	private Sender gcmSender = null; // GCM Sender
	private Message gcmMessage = null; // GCM Message
	private MulticastResult gcmResult = null; // GCM Result()

	private String smsMsg;
	private String pushMsg;

	private Globals globals;

	// private static final String SELECT_PAGE =
	// "http:// (server_address!!!) /select_registration.php";
	// private static String JSON = null;
	private static String API_KEY = "AIzaSyC0IIOyF2A2Sg_Sto0BmF6-QQRn4qM0sq8";
	private static String COLLAPSE_KEY = String
			.valueOf(Math.random() % 100 + 1);
	private static boolean DELAY_WHILE_IDLE = true;
	private static int TIME_TO_LIVE = 3;
	private static int RETRY = 3;

	List<String> regId;

	public void setPushMessage(String kicker, String title, String message) {

		gcmSender = new Sender(API_KEY);
		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.delayWhileIdle(false).addData("ticker", kicker)
				.addData("title", title).addData("msg", message).build();
	}

	public void setSMSMessage(String kicker, String title, String message) {
		smsMsg = "";
		smsMsg = "" + kicker + "\n" + "제목 : " + title + "\n" + "내용 : " + message;
	}

	public void setPushMessage(String kicker, String title,
			Map<Integer, Map<Integer, String>> mSelectDayList, String content) {

		gcmSender = new Sender(API_KEY);
		pushMsg = "";
		pushMsg += "날짜 : "+ getMessage(mSelectDayList);
		pushMsg += "\n" + "내용 : " + content;

		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.delayWhileIdle(false).addData("ticker", kicker)
				.addData("title", title).addData("msg", pushMsg).build();
	}
	
	public void setSMSMessage(String kicker, String title,
			Map<Integer, Map<Integer, String>> mSelectDayList, String content) {

		smsMsg = "";
		smsMsg = "" + kicker + "\n" + "제목 : " + title + "\n" + "일시 : "
				+ getMessage(mSelectDayList) + "\n" + "내용 : " + content;
	}
	
	public String getMessage(Map<Integer, Map<Integer, String>> mSelectDayList) {

		String msg = "";
		Iterator mKeySet = mSelectDayList.keySet().iterator();

		while (mKeySet.hasNext()) {
			Integer mKey = (Integer) mKeySet.next();
			Iterator dKeySet = mSelectDayList.get(mKey).keySet().iterator();

			while (dKeySet.hasNext()) {
				Integer dKey = (Integer) dKeySet.next();

				if (dKey.toString().equals(mSelectDayList.get(mKey).get(dKey))) {
					msg += "" + mKey + "/" + dKey + "  ";
				} else {
					msg += "   " + mKey + "/" + dKey + " ("
							+ mSelectDayList.get(mKey).get(dKey) + ")";
				}
			}
		} // while
		return msg;
	}

	public void setPushList() {

		regId = new ArrayList();

		SharedPreferences prefs = this.getActivity().getSharedPreferences(
				"regId", Activity.MODE_PRIVATE);
		// 박세진 id
		regId.add("APA91bHAV3oobSzSbgP3KLO9Gsw3FYiTAzEAFohamqXlAEf3dVuuW3DGroO_bUoJKmS2wOGxoYfD7KuZQ2JrX3GO9nLVw9P67Q1mlMUkHSN_2XX2szBW2W_UKX02hF5BbSjSSzi4WzYi");
		// SharedPreferences에 저장된 아이디
		regId.add(prefs.getString("regId", null));

	}
	
	public void sendPushMessage() {

		setPushList();
		try {
			Log.w(TAG, "" + gcmMessage);
			Log.w(TAG, "" + regId);
			Log.w(TAG, "" + RETRY);

			// test 내 아이디
			gcmResult = gcmSender.send(gcmMessage, regId, RETRY);

			Log.w(TAG, "success " + gcmResult.getSuccess());
		} catch (IOException e) {
			Log.w(TAG, "IOException " + e.getMessage());
		}
	}

	/**
	 * 문자를 보내는 메소드
	 * 
	 * @param phoneNumber
	 * @param message
	 */
	protected void sendSMSMessage(String phoneNumber) {

		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		// 문자 보내는 상태를 감지하는 PendingIntent
		PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0,
				new Intent(SENT), 0);
		// 문자 받은 상태를 감지하는 PendingIntent
		PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(),
				0, new Intent(DELIVERED), 0);

		// 문자 보내는 상태를 감지하는 BroadcastReceiver를 등록한다.
		getActivity().registerReceiver(new BroadcastReceiver() {

			// 문자를 수신하면, 발생.
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getActivity(), "SMS sent",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getActivity(), "Generic failure",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getActivity(), "No service",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getActivity(), "Null PDU",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getActivity(), "Radio off",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		// 문자를 받는 상태를 확인하는 BroadcastReceiver를 등록.
		getActivity().registerReceiver(new BroadcastReceiver() {
			// 문자를 받게 되면, 불린다.
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getActivity(), "SMS delivered",
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getActivity(), "SMS not delivered",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		// SmsManager를 가져온다.
		SmsManager sms = SmsManager.getDefault();
		// sms를 보낸다.
		sms.sendTextMessage(phoneNumber, null, smsMsg, sentPI, deliveredPI);
	}
}
