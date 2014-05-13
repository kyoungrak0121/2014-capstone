package com.example.push.professor.fragment;

import java.io.IOException;

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
			.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
			.addData("ticker", kicker)
			.addData("title", title)
			.addData("msg", message)
			.build();
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
					
			gcmResult = gcmSender.send(gcmMessage,regId,RETRY);
			
		}catch(IOException e) {
			Log.w(TAG, "IOException " + e.getMessage());
		}
	}
	
}
