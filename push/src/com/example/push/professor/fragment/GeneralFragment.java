package com.example.push.professor.fragment;

import com.example.push.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GeneralFragment extends SuperFragment {

	private EditText pushTitle;
	private EditText pushMessage;
	private TextView pushLength;
	private Button pushTrans;
	
	String regId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.genenal_fragment, container, false);

		setLayout(v);
		init();
		
		pushTrans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pushTitle.length() == 0 || pushMessage.length() == 0){
					Toast.makeText(getActivity().getApplicationContext(), "제목과 내용을 모두 입력 해주세요.", Toast.LENGTH_LONG).show();
				}else{
					
					setMessage("공지사항",pushTitle.getText().toString(),pushMessage.getText().toString());
					
					new Thread(new Runnable() {	
						@Override
						public void run() {
							// TODO Auto-generated method stub
							sendMessage(regId);		
						}
					}).start();				
					init();
				}
			}
		});
		return v;
	}
	
	void init(){
		pushTitle.setText("");
		pushMessage.setText("");
		
		// //////////////////////////////////
		// /////id 나중에 DB로 변경!!
		SharedPreferences prefs = this.getActivity().getSharedPreferences(
				"regId", Activity.MODE_PRIVATE);
		regId = prefs.getString("regId", null);
	}

	void setLayout(View v) {

		pushTitle = (EditText) v.findViewById(R.id.pushTitle);
		pushMessage = (EditText) v.findViewById(R.id.pushMessage);
		pushTrans = (Button) v.findViewById(R.id.pushTrans);

	}
	
	
	
}
