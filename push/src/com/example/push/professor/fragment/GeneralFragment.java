package com.example.push.professor.fragment;

import com.example.push.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GeneralFragment extends SuperFragment implements OnClickListener {

	private EditText pushTitle;
	private EditText pushMessage;
	private TextView inputTitle;
	private TextView inputMessage;
	private Button pushTrans;
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.genenal_fragment, container, false);

		setLayout(v);
		init();

		pushTrans.setOnClickListener(this);

		pushTitle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				// 텍스트가 변경될때마다 발생할 이벤트를 작성.
				if (pushTitle.isFocusable()) {
					// mXMLBuyCount EditText 가 포커스 되어있을 경우에만 실행됩니다.
					
					
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() <  1 ){
					inputTitle.setTextColor(Color.parseColor("#5D5D5D"));
				} else {
					inputTitle.setTextColor(Color.parseColor("#F08080"));
				}
				
			}
		});
		pushMessage.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() <  1 ){
					inputMessage.setTextColor(Color.parseColor("#5D5D5D"));
				} else {
					inputMessage.setTextColor(Color.parseColor("#F08080"));
				}
			}
		});

		return v;
	}

	void init() {
		pushTitle.setText("");
		pushMessage.setText("");
	}

	void setLayout(View v) {
		
		inputTitle = (TextView) v.findViewById(R.id.inputTitle);
		inputMessage = (TextView) v.findViewById(R.id.inputMessage);

		pushTitle = (EditText) v.findViewById(R.id.pushTitle);
		pushMessage = (EditText) v.findViewById(R.id.pushMessage);
		pushTrans = (Button) v.findViewById(R.id.pushTrans);

		//글자수 제한 
		pushTitle.setFilters(new InputFilter[]{
				new InputFilter.LengthFilter(15)
		});
		pushMessage.setFilters(new InputFilter[]{
				new InputFilter.LengthFilter(80)
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (pushTitle.length() == 0 || pushMessage.length() == 0) {
			Toast.makeText(getActivity().getApplicationContext(),
					"제목과 내용을 모두 입력 해주세요.", Toast.LENGTH_LONG).show();
		} else {
			
			getSubjectListDialog("공지사항", pushTitle.getText().toString(), pushMessage.getText().toString());
			
			init();
		}
	}

}
