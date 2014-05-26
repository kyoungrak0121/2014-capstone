package com.example.push.professor.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.example.push.R;
import com.example.push.adapter.CalendarAdapter;
import com.example.push.adapter.CalendarWeekAdapter;
import com.example.push.table.DayInfo;
import com.example.push.widget.Calender;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SupplementFragment extends Calender implements
		OnItemClickListener, OnClickListener {

	private TextView inputDate;
	private TextView inputTime;
	private TextView inputMessage;

	private TextView mTvCalenderSelect;
	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;
	private GridView mGvCalenderWeek;
	private Button mPushbtn;

	private Map<Integer, Map<Integer, String>> mSelectDayList;

	private CalendarAdapter mCalendarAdapter;
	private CalendarWeekAdapter mCalenderWeekAdapter;

	Calendar mLastMonthCalendar;
	Calendar mThisMonthCalendar;
	Calendar mNextMonthCalendar;

	Button bLastMonth;
	Button bNextMonth;

	String selectData;
	int selectCount;
	String sup_location;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.cancle_class_fragment, container,
				false);

		setLayout(v);

		init();

		return v;

	}

	void init() {

		mDayList = new ArrayList<DayInfo>();

		mSelectDayList = null;
		selectData = "";
		selectCount = 0;

		sup_location = "";

		mTvCalenderSelect.setText("");
		mThisMonthCalendar = Calendar.getInstance();
		mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);

		inputDate.setTextColor(Color.parseColor("#5D5D5D"));
		inputMessage.setTextColor(Color.parseColor("#5D5D5D"));
		inputTime.setTextColor(Color.parseColor("#5D5D5D"));

		setCalender();
	}

	void setCalender() {
		// 캘린더 타이틀(년월 표시)을 세팅한다.
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
		getCalendarWeek();
		getCalendar(mThisMonthCalendar);
		initCalendarAdapter();
	}

	void setLayout(View v) {
		bLastMonth = (Button) v.findViewById(R.id.gv_calendar_activity_b_last);
		bNextMonth = (Button) v.findViewById(R.id.gv_calendar_activity_b_next);

		mTvCalendarTitle = (TextView) v
				.findViewById(R.id.gv_calendar_activity_tv_title);
		mGvCalendar = (GridView) v
				.findViewById(R.id.gv_calendar_activity_gv_calendar);
		mGvCalenderWeek = (GridView) v
				.findViewById(R.id.gv_calendar_activity_gv_calendar_week);
		mTvCalenderSelect = (TextView) v
				.findViewById(R.id.gv_calendar_select_day);

		mPushbtn = (Button) v.findViewById(R.id.pushTrans);

		inputDate = (TextView) v.findViewById(R.id.inputDate);
		inputTime = (TextView) v.findViewById(R.id.inputTime);
		inputMessage = (TextView) v.findViewById(R.id.inputMessage);

		inputDate.setText("날짜 선택 -> ");
		inputTime.setText("시간 선택 -> ");
		inputMessage.setText("장소 입력 -> ");

		mPushbtn.setOnClickListener(this);
		bLastMonth.setOnClickListener(this);
		bNextMonth.setOnClickListener(this);
		mGvCalendar.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position,
			long arg3) {

		int month;
		month = mThisMonthCalendar.get(Calendar.MONTH) + 1;

		DayInfo day = (DayInfo) mCalendarAdapter.getItem(position);

		if (day.isInMonth()) {
			setSelectData(parent, v, month, day);

		}
	}

	void showSelectList() {
		selectData = "";
		
		if (sup_location.equals("")) {
			sup_location = "미입력";
		}
		if (!(getMessage(mSelectDayList).equals("") || getMessage(
				mSelectDayList).equals(null))) {
			selectData = "일시 : " + getMessage(mSelectDayList) + "\n장소 :   "
					+ sup_location;
		}

		mTvCalenderSelect.setText("" + selectData);
	}

	public void setSelectData(AdapterView<?> parent, View v, int month,
			DayInfo day) {

		if (mSelectDayList == null) {
			mSelectDayList = new TreeMap<Integer, Map<Integer, String>>();
		}

		if (!mSelectDayList.containsKey(month)) { // key 가 없으면
			Map<Integer, String> selectDay = new TreeMap<Integer, String>();
			mSelectDayList.put(month, selectDay);
		}

		// 날짜 가 없으면 넣음
		if (!mSelectDayList.get(month).containsKey(
				Integer.parseInt(day.getDay()))) {
			if (selectCount < 1) {
				showDialog(parent, month, day, v);

			} else {
				Toast.makeText(getActivity(), "이미 입력 하셨습니다.",
						Toast.LENGTH_SHORT).show();
			}
		} else {

			removeDate(parent, month, day, v);
			v.setBackgroundDrawable(parent.getBackground());
			if (!(selectCount > 0)) {
				inputDate.setTextColor(Color.parseColor("#5D5D5D"));
				inputMessage.setTextColor(Color.parseColor("#5D5D5D"));
				inputTime.setTextColor(Color.parseColor("#5D5D5D"));
			}
			
			showSelectList();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gv_calendar_activity_b_last:
			mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
			getCalendar(mThisMonthCalendar);
			setCalender();
			break;
		case R.id.gv_calendar_activity_b_next:
			mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
			getCalendar(mThisMonthCalendar);
			setCalender();
			break;
		case R.id.pushTrans:

			if (selectCount > 0) {

				// //////////////////////////////////
				// /////id 나중에 DB로 변경!
				// DB
				setPushMessage("보강공지", "XX 강의 보강 공지", mSelectDayList,
						sup_location + " 보강합니다.");
				setSMSMessage("보강공지", "XX 강의 보강 공지", mSelectDayList,
						sup_location + " 에서 보강합니다.");
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendPushMessage();
					}
				}).start();

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendSMSMessage();
					}
				}).start();

				init();

			} else {
				Toast.makeText(getActivity(), "보강 날짜를 선택하여 주십시오.",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	private void initCalendarAdapter() {
		mCalendarAdapter = new CalendarAdapter(getActivity()
				.getApplicationContext(), R.layout.day, mDayList);
		mGvCalendar.setAdapter(mCalendarAdapter);
	}

	protected void getCalendarWeek() {

		mCalenderWeekAdapter = new CalendarWeekAdapter(week, R.layout.week,
				getActivity());
		mGvCalenderWeek.setAdapter(mCalenderWeekAdapter);
	}

	private void insertDate(AdapterView<?> parent, int month, DayInfo day,
			View v) {
		// TODO Auto-generated method stub

		mSelectDayList.get(month).put(Integer.parseInt(day.getDay()),
				day.getDay());
		Log.w("Day : ", "" + day.getDay());
		selectCount++;

	}

	private void removeDate(AdapterView<?> parent, int month, DayInfo day,
			View v) {
		selectCount--;
		sup_location = "";
		mSelectDayList.get(month).remove(Integer.parseInt(day.getDay()));

	}

	void showDialog(final AdapterView<?> parent, final int month,
			final DayInfo day, final View v) {

		long now = System.currentTimeMillis();
		Date date = new Date(now);

		SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");

		final int strCurHour = Integer.parseInt(CurHourFormat.format(date));
		final int strCurMinute = Integer.parseInt(CurMinuteFormat.format(date));

		TimePickerDialog dialog = new TimePickerDialog(getActivity(),
				new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker sendMessageview,
							int hourOfDay, int minute) {
						// TODO Auto-generated method stub

						insertDate(parent, month, day, v);
						
						v.setBackgroundColor(Color.parseColor("#F08080"));
						
						inputDate.setTextColor(Color.parseColor("#F08080"));
						inputTime.setTextColor(Color.parseColor("#F08080"));

						mSelectDayList.get(month).put(
								Integer.parseInt(day.getDay()),
								"" + hourOfDay + " : " + minute);
						myTextDialog();
					}

				}, strCurHour, strCurMinute, false);
		dialog.setTitle("setting Time!!");

		dialog.show();
	}

	protected void myTextDialog() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.task_message_dialog);
		dialog.setTitle("보강 장소 공지");
		Button ok = (Button) dialog.findViewById(R.id.buttonOK);
		Button no = (Button) dialog.findViewById(R.id.buttonNO);
		final TextView tv = (TextView) dialog.findViewById(R.id.task_message);

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sup_location = "";
				sup_location += tv.getText().toString();
				inputMessage.setTextColor(Color.parseColor("#F08080"));
				showSelectList();
				dialog.dismiss();

			}
		});
		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				showSelectList();
				dialog.dismiss();
			}
		});

		dialog.show();

	}

}
