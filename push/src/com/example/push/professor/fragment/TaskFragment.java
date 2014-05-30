package com.example.push.professor.fragment;


import com.example.push.R;

import com.example.push.widget.Calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class TaskFragment extends Calender  {
	
	private Button mPushbtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.calendar_fragment, container,false);

		setLayout(v);
		
		calendarInit("TaskFragment");
		
		setCalender();
		
		return v;

	}

	void setLayout(View v) {
		
		setCalendarLayout(v);
		setUseInfo("��¥ ���� -> ","�ð� ���� -> ","�޽��� �Է� -> ");
		mPushbtn = (Button) v.findViewById(R.id.pushTrans);
		mPushbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (selectCount > 0) {
					
					getSubjectListDialog("���� ����","���� : "+ task_message,mSelectDayList);
					calendarInit("TaskFragment");
					setCalender();
				
				} else {
					
					Toast.makeText(getActivity(), "���� ���� ��¥�� �ð��� �����Ͽ� �ֽʽÿ�.",
							Toast.LENGTH_SHORT).show();
				}	
			}

		});
	
	}
	
}
