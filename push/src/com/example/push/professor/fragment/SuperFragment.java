package com.example.push.professor.fragment;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.example.push.R;
import com.example.push.table.Globals;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SuperFragment  extends Fragment {
	
private static final String TAG = "GCM";
	
	private Sender 	gcmSender  =  null;				//GCM Sender
	private Message gcmMessage = null;			//GCM Message
	private Result 	gcmResult  =  null;				//GCM Result()
//	private MulticastResult 	gcmMultiResult;		

//GCM Multi Result()
	
	private Globals globals;
	
	//private static final String SELECT_PAGE = "http:// (server_address!!!) /select_registration.php";
	//private static String JSON = null;
	private static String 		API_KEY = "AIzaSyC0IIOyF2A2Sg_Sto0BmF6-QQRn4qM0sq8";
	private static String 		COLLAPSE_KEY =  String.valueOf(Math.random() % 100 + 1);
	private static boolean 	    DELAY_WHILE_IDLE = true;
	private static int	        TIME_TO_LIVE = 3;
	private static int 			RETRY = 3;

	

	public void setMessage(String kicker,String title, String message){
	
		gcmSender = new Sender(API_KEY);
		
		gcmMessage = new Message.Builder()
			.collapseKey(COLLAPSE_KEY)
			.delayWhileIdle(DELAY_WHILE_IDLE)
			.timeToLive(TIME_TO_LIVE)
			.delayWhileIdle(false)
			.addData("ticker", kicker)
			.addData("title", title)
			.addData("msg", message)
			.build();
	}
	
	public void setMessage(String kicker,String title,Map<Integer,Map<Integer, Integer>> mSelectDayList){
		
		gcmSender = new Sender(API_KEY);
		
		String msg = "";
	
		msg = getMessage(mSelectDayList);
		
		msg += "\n 휴강합니다.";
		
		
		gcmMessage = new Message.Builder()
			.collapseKey(COLLAPSE_KEY)
			.delayWhileIdle(DELAY_WHILE_IDLE)
			.timeToLive(TIME_TO_LIVE)
			.delayWhileIdle(false)
			.addData("ticker", kicker)
			.addData("title", title)
			.addData("msg",msg)
			.build();
		
	}
	
	public String getMessage(Map<Integer,Map<Integer, Integer>> mSelectDayList){
		
		String msg = "";
		Iterator mKeySet = mSelectDayList.keySet().iterator();
		
		while (mKeySet.hasNext()) {
			Integer mKey = (Integer) mKeySet.next();
			Iterator dKeySet = mSelectDayList.get(mKey).keySet().iterator();
			
			while(dKeySet.hasNext()){
				Integer dKey = (Integer)dKeySet.next();
				msg += "  "+mKey +"/" + mSelectDayList.get(mKey).get(dKey);
			}
		} // while
		return msg;
	}
	
	public void sendMessage(String regId){
		
//		try {
//			gcmMultiResult = gcmSender.send(gcmMessage, registrationIds, RETRY);
//		} catch (IOException e) {
//			Log.w(TAG, "IOException " + e.getMessage());
//		}
//		Log.w(TAG, "getCanonicalIds : " + gcmMultiResult.getCanonicalIds() + "\n" + 
//				"getSuccess : " + gcmMultiResult.getSuccess() + "\n" + 
//				"getTotal : " + gcmMultiResult.getTotal() + "\n" + 
//				"getMulticastId : " + gcmMultiResult.getMulticastId());
		
		try {
			
			Log.w(TAG,""+gcmMessage);
			Log.w(TAG,""+regId);
			Log.w(TAG,""+RETRY);
			
			//박세진 id 
			String id = "APA91bHAV3oobSzSbgP3KLO9Gsw3FYiTAzEAFohamqXlAEf3dVuuW3DGroO_bUoJKmS2wOGxoYfD7KuZQ2JrX3GO9nLVw9P67Q1mlMUkHSN_2XX2szBW2W_UKX02hF5BbSjSSzi4WzYi";
			
			//Log.w(TAG,""+id);
			
			//test 내 아이디 
			gcmResult = gcmSender.send(gcmMessage,regId,RETRY);
			//gcmResult = gcmSender.send(gcmMessage,id,RETRY);
			if (gcmResult.getMessageId() != null) {
                Log.w(TAG,"Send success");
               // Toast.makeText(getActivity(),"메세지를 보냈습니다.", Toast.LENGTH_SHORT).show();
			}
		}catch(IOException e) {
			Log.w(TAG, "IOException " + e.getMessage());
		}
	}
	
}
