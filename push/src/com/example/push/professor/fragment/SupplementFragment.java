package com.example.push.professor.fragment;

import com.example.push.R;
import com.example.push.widget.Calender;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SupplementFragment extends Calender {

	private Button mPushbtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.calendar_fragment, container, false);

		setLayout(v);
		calendarInit("SupplementFragment");

		setCalender();

		return v;

	}

	void setLayout(View v) {

		setCalendarLayout(v);
		setUseInfo("날짜 선택 -> ", "시간 선택 -> ", "장소 입력 -> ");
		mPushbtn = (Button) v.findViewById(R.id.pushTrans);
		mPushbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (selectCount > 0) {

					getSubjectListDialog("보강공지", "장소 : " + task_message, mSelectDayList);
					calendarInit("SupplementFragment");
					setCalender();

				} else {
					Toast.makeText(getActivity(), "보강 날짜를 선택하여 주십시오.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
