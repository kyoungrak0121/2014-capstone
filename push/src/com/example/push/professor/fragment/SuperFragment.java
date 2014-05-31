package com.example.push.professor.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.push.R;
import com.example.push.db.DBManager;
import com.example.push.table.Globals;
import com.example.push.table.Professor;
import com.example.push.table.Subject_Info;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SuperFragment extends Fragment {

	private final String TAG = "GCM";

	private Sender gcmSender = null; // GCM Sender
	private Message gcmMessage = null; // GCM Message
	private MulticastResult gcmResult = null; // GCM Result()

	private String smsMsg;
	private String pushMsg;

	private final String API_KEY = "AIzaSyC0IIOyF2A2Sg_Sto0BmF6-QQRn4qM0sq8";
	private final String COLLAPSE_KEY = String
			.valueOf(Math.random() % 100 + 1);
	private final boolean DELAY_WHILE_IDLE = true;
	private final int TIME_TO_LIVE = 3;
	private final int RETRY = 3;

	List<String> regId;
	List<String> phone;

	private String profId;
	private DBManager db;

	protected String sub_name = "";
	List<String> subNum;
	private String sub_num;
	private Activity myActivity;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		myActivity = activity;
	}
	
	public void setPushMessage(String kicker, String title, String message) {

		pushMsg = "";
		pushMsg = "" + sub_name + kicker +"\n"+ title + "\n" + message;

		gcmSender = new Sender(API_KEY);
		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.delayWhileIdle(false).addData("ticker",sub_name + " " + kicker)
				.addData("title", kicker).addData("msg", pushMsg).build();
	}

	public void setSMSMessage(String kicker, String title, String message) {
		smsMsg = "";
		smsMsg = "" + kicker + "\n" + sub_name + " \n" + "제목  : " + title
				+ "\n" + "내용 " + "\n" + message;

	}

	public void setPushMessage(String kicker, String title, String content,
			Map<Integer, Map<Integer, String>> mSelectDayList) {

		gcmSender = new Sender(API_KEY);

		pushMsg = "";
		pushMsg += sub_name +" " + kicker + "날짜 : " + getMessage(mSelectDayList);
		pushMsg += "\n" + "내용 : " + content;

		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.delayWhileIdle(false).addData("ticker", sub_name +" "+ kicker)
				.addData("title", kicker).addData("msg", pushMsg).build();
	}

	public void setSMSMessage(String kicker, String title, String content,
			Map<Integer, Map<Integer, String>> mSelectDayList) {

		smsMsg = "";
		smsMsg = "" + kicker + "\n" + "제목 : " + title + "\n" + "일시 : "
				+ getMessage(mSelectDayList) + "\n"  + content;
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

	public void setPushList(String sub_num) {

		regId = null;

		db = new DBManager(getActivity());

		regId = db.getRegList(sub_num);

	}

	public void sendPushMessage() {

		setPushList(sub_num);

		try {
			Log.w(TAG, "" + gcmMessage);
			Log.w(TAG, "" + regId);
			Log.w(TAG, "" + RETRY);

			if (!regId.isEmpty()) {
				gcmResult = gcmSender.send(gcmMessage, regId, RETRY);
				Log.w(TAG, "success " + gcmResult.getSuccess());
			}

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

	public void setPhoneList(String sub_num) {
		profId = null;
		profId = getArguments().getString("id");

		db = new DBManager(getActivity());

		phone = db.getPhoneList(sub_num);
	}

	protected void sendSMSMessage() {
		
		setPhoneList(sub_num);
		if (!phone.isEmpty()) {
			for (String phoneNumber : phone) {
				sendSMSMessage(phoneNumber);
			}
		} else {
			Log.w("sms", "if phone num null");
		}
	}

	@SuppressWarnings("deprecation")
	protected void sendSMSMessage(String phoneNumber) {
		Log.w("sms", "sendSMSMessage!!!");
		
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		// 문자 보내는 상태를 감지하는 PendingIntent
		PendingIntent sentPI = PendingIntent.getBroadcast(myActivity, 0,
				new Intent(SENT), 0);
		// 문자 받은 상태를 감지하는 PendingIntent
		PendingIntent deliveredPI = PendingIntent.getBroadcast(myActivity,
				0, new Intent(DELIVERED), 0);
	
		// 문자 보내는 상태를 감지하는 BroadcastReceiver를 등록한다.
		myActivity.registerReceiver(new BroadcastReceiver() {
			// 문자를 수신하면, 발생.
			@Override
			public void onReceive(Context context, Intent intent) {
				Log.w("sms", "onReceive");
				
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context, "SMS sent", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context, "Generic failure",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(context, "No service", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context, "Null PDU", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "Radio off", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					Toast.makeText(context, "SMS default", Toast.LENGTH_SHORT)
							.show();

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
					Toast.makeText(context, "SMS delivered", Toast.LENGTH_SHORT)
							.show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(context, "SMS not delivered",
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

	protected void getSubjectListDialog(final String kicker,
			final String title, final String message) {

		List<String> list = getSubjectList();
		final String[] items = list.toArray(new String[list.size()]);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("나의 강좌 List");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int item) {

						sub_name = items[item];
						sub_num = subNum.get(item);

						Log.w("select Subject_Num", "" + sub_num);

						Toast.makeText(getActivity(), items[item],
								Toast.LENGTH_SHORT).show();
					}
				});

		builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				setPushMessage(kicker, title, message);
				setSMSMessage(kicker, title, message);

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendPushMessage();
					}
				}).start();
				
				if (isSMSSend()){
					new Thread(new Runnable() {
						@Override
						public void run() {
							Log.w("sms", "SendSMSmessage runrunrun");
							// TODO Auto-generated method stub

							sendSMSMessage();
						}
					}).start();
				}	
			}
		});
		builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT)
						.show();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	protected void getSubjectListDialog(final String kicker, final String message, final Map<Integer, Map<Integer, String>> mSelectDayList) {
		
		List<String> list = getSubjectList();
		final String[] items = list.toArray(new String[list.size()]);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("나의 강좌 List");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						sub_name = items[item];
						sub_num = subNum.get(item);

						Log.w("select Subject_Num", "" + sub_num);

						Toast.makeText(getActivity(), items[item],
								Toast.LENGTH_SHORT).show();
					}
				});

		builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				setPushMessage(kicker, sub_name, message, mSelectDayList);
				setSMSMessage(kicker, sub_name, message, mSelectDayList);

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendPushMessage();
					}
				}).start();
				
				if (isSMSSend()){
					new Thread(new Runnable() {
						@Override
						public void run() {
							Log.w("sms", "SendSMSmessage runrunrun");
							// TODO Auto-generated method stub

							sendSMSMessage();
						}
					}).start();
				}	
			}
		});
		builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT)
						.show();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private List<String> getSubjectList() {
		// TODO Auto-generated method stub
		Globals g = Globals.getInstance();
		List<String> list = new ArrayList<String>();
		subNum = new ArrayList<String>();

		profId = getArguments().getString("id");
		Professor p = g.getPerson().getpList().get(profId);

		for (String key : p.getSubjectList().keySet()) {
			list.add("" + p.getSubjectList().get(key).getName() + " ( " + key
					+ " ) " + "\n시간 : " + p.getSubjectList().get(key).getDay()
					+ " " + p.getSubjectList().get(key).getPeriod());
			subNum.add(key);
		}
		return list;
	}

	protected Map<String, Subject_Info> getSubjectList(String profNum) {

		db = new DBManager(getActivity());
		return db.setProfSubList(profNum);

	}
	
	public boolean isSMSSend(){
		
		boolean isSMSCheck;
		
		SharedPreferences prefs = getActivity().getSharedPreferences("SMSCheck", Activity.MODE_PRIVATE);
		isSMSCheck = prefs.getBoolean("SMSCheck", true);
		
		if(isSMSCheck){
			return true; // 보내기 
		}
		return false; // false 면 안 보내기 
	}
}
