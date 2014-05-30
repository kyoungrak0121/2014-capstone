package com.example.push.widget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.push.R;
import com.example.push.adapter.CalendarAdapter;
import com.example.push.adapter.CalendarWeekAdapter;
import com.example.push.professor.fragment.SuperFragment;
import com.example.push.table.DayInfo;

public class Calender extends SuperFragment {
	
	public String FragmentName;
	public final int SUNDAY = 1;
	public final int MONDAY = 2;
	public final int TUESDAY = 3;
	public final int WEDNSESDAY = 4;
	public final int THURSDAY = 5;
	public final int FRIDAY = 6;
	public final int SATURDAY = 7;
	public final String[] week = { "S","M","T","W","T","F","S" };
	
	protected ArrayList<DayInfo> mDayList;
	
	private CalendarAdapter mCalendarAdapter;
	private CalendarWeekAdapter mCalenderWeekAdapter;

	Calendar mLastMonthCalendar;
	Calendar mThisMonthCalendar;
	Calendar mNextMonthCalendar;

	Button bLastMonth;
	Button bNextMonth;
	
	private TextView mTvCalenderSelect;
	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;
	private GridView mGvCalenderWeek;
	
	protected Map<Integer, Map<Integer, String>> mSelectDayList;
	
	private TextView inputDate;
	private TextView inputTime;
	private TextView inputMessage;
	
	
	String selectData;
	protected int selectCount;
	protected String task_message;
	
	 /**
     * 달력을 셋팅한다.
     * 
     * @param calendar 달력에 보여지는 이번달의 Calendar 객체
     */
	
	
	protected void calendarInit(String name){
		
		this.FragmentName = name;
		
		mDayList = new ArrayList<DayInfo>();
		mSelectDayList = null;
		mThisMonthCalendar = Calendar.getInstance();
		mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
		selectData = "";
		mTvCalenderSelect.setText("");
		task_message = "";
		selectCount = 0;
		
		inputDate.setTextColor(Color.parseColor("#5D5D5D"));
		inputMessage.setTextColor(Color.parseColor("#5D5D5D"));
		inputTime.setTextColor(Color.parseColor("#5D5D5D"));
		
	}
  
	protected void setCalendarLayout(View v){
		
		bLastMonth = (Button) v.findViewById(R.id.gv_calendar_activity_b_last);
		bNextMonth = (Button) v.findViewById(R.id.gv_calendar_activity_b_next);
		mTvCalenderSelect = (TextView) v
				.findViewById(R.id.gv_calendar_select_day);
		mTvCalendarTitle = (TextView) v
				.findViewById(R.id.gv_calendar_activity_tv_title);
		mGvCalendar = (GridView) v
				.findViewById(R.id.gv_calendar_activity_gv_calendar);
		mGvCalenderWeek = (GridView) v
				.findViewById(R.id.gv_calendar_activity_gv_calendar_week);
		
		inputDate = (TextView) v.findViewById(R.id.inputDate);
		inputTime = (TextView) v.findViewById(R.id.inputTime);
		inputMessage = (TextView) v.findViewById(R.id.inputMessage);
		
		
		bLastMonth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
				getCalendar(mThisMonthCalendar);
				setCalender();
				
			}
		});
		bNextMonth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
				getCalendar(mThisMonthCalendar);
				setCalender();
			}
		});
		mGvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				int month;
				month = mThisMonthCalendar.get(Calendar.MONTH) + 1;

				DayInfo day = (DayInfo) mCalendarAdapter.getItem(position);

				if (day.isInMonth()) {
					setSelectData(parent, v, month, day);
				}
			}
		});
	}
	
	protected void setUseInfo(String data,String time ,String message){
		inputDate.setText(data);
		inputTime.setText(time);
		inputMessage.setText(message);
	
	}

	protected void showSelectList() {
		selectData = "";
		String type ="";
		
		if (task_message.equals("")) {
			task_message = "미입력";
		}
		if (!(getMessage(mSelectDayList).equals("") || getMessage(
				mSelectDayList).equals(null))) {
			
			if(FragmentName.equals("TaskFragment")){
				type = "내용 : ";
			}else if(FragmentName.equals("CancleClassFragment")){
				type = "";
				task_message = "";
			}else if(FragmentName.equals("SupplementFragment")){
				type = "장소 : ";
			}
			selectData = "날짜 :" + getMessage(mSelectDayList) + "\n" + type 
					+ task_message;
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
			
		
			if (!FragmentName.equals("CancleClassFragment")) {
				if (selectCount < 1) {
					
					v.setBackgroundColor(Color.parseColor("#F08080"));
					inputDate.setTextColor(Color.parseColor("#F08080"));
					inputTime.setTextColor(Color.parseColor("#F08080"));
					
					insertDate(parent,month,day,v);
					
					showTimeDialog(parent, month, day, v);
					
				} else {
					Toast.makeText(getActivity(), "이미 입력 하셨습니다.",
							Toast.LENGTH_SHORT).show();
				}
				
			} else {
				insertDate(parent,month,day,v);
				inputTime.setTextColor(Color.parseColor("#F08080"));
				v.setBackgroundColor(Color.parseColor("#F08080"));
				mSelectDayList.get(month).put(
						Integer.parseInt(day.getDay()), day.getDay());
				showSelectList();
			}
		} else {

			removeDate(parent, month, day, v);
			v.setBackgroundDrawable(parent.getBackground());
			
			inputDate.setTextColor(Color.parseColor("#5D5D5D"));
			inputMessage.setTextColor(Color.parseColor("#5D5D5D"));
			inputTime.setTextColor(Color.parseColor("#5D5D5D"));
			
			showSelectList();

		}
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
		task_message = "";
		mSelectDayList.get(month).remove(Integer.parseInt(day.getDay()));

	}
	
	protected void setCalender() {
		// 캘린더 타이틀(년월 표시)을 세팅한다.
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
		getCalendarWeek();
		getCalendar(mThisMonthCalendar);
		initCalendarAdapter();
	}
	
	private void getCalendarWeek() {

		mCalenderWeekAdapter = new CalendarWeekAdapter(week, R.layout.week,
				getActivity());
		mGvCalenderWeek.setAdapter(mCalenderWeekAdapter);
	}

	private void initCalendarAdapter() {
		mCalendarAdapter = new CalendarAdapter(getActivity()
				.getApplicationContext(), R.layout.day, mDayList);
		mGvCalendar.setAdapter(mCalendarAdapter);
	}
	
	protected void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;
         
        mDayList.clear();
       
         
        // 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH)+"");
 
        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
 
        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH)+"");
         
        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }
         
        lastMonthStartDay -= (dayOfMonth-1)-1;   
        
        DayInfo day;
         
        Log.e("DayOfMOnth", dayOfMonth+"");
         
        for(int i=0; i<dayOfMonth-1; i++)
        {
            int date = lastMonthStartDay+i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);
             
            mDayList.add(day);
        }
        for(int i=1; i <= thisMonthLastDay; i++)
        {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);
             
            mDayList.add(day);
        }
        for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++)
        {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);
        }
    }

    /**
     * 지난달의 Calendar 객체를 반환합니다.
     * 
     * @param calendar
     * @return LastMonthCalendar
     */
	
    protected Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
   
        return calendar;
    }
 
    /**
     * 다음달의 Calendar 객체를 반환합니다.
     * 
     * @param calendar
     * @return NextMonthCalendar
     */
    protected Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);

        return calendar;
    }
    

	private void showTimeDialog(final AdapterView<?> parent,final int month ,final DayInfo day,final View v){
   		   		
   		long now = System.currentTimeMillis();
   		Date date = new Date(now);
   		
   		SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
   		SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");
   		
   		final int strCurHour = Integer.parseInt(CurHourFormat.format(date));
   		final int strCurMinute = Integer.parseInt(CurMinuteFormat.format(date));
   		
   		TimePickerDialog dialog = new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker sendMessageview, int hourOfDay, int minute) {
				// TODO Auto-generated method stub				
				
				String type = null;
				
				mSelectDayList.get(month).put(Integer.parseInt(day.getDay()), "" + hourOfDay + " : " + minute );
				
				if(FragmentName.equals("TaskFragment")){
					type = "과제 공지";
				}else if(FragmentName.equals("CancleClassFragment")){
					type = "휴강 공지";
				}else if(FragmentName.equals("SupplementFragment")){
					type = "보강 장소 공지";
				}
				
				TextDialog(type);
			}
			
		},strCurHour,strCurMinute,false);
		dialog.setTitle("시간 설정");
		
		dialog.show();
   	}

	private void TextDialog(String title) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.message_dialog);
		dialog.setTitle(title);
		Button ok = (Button) dialog.findViewById(R.id.buttonOK);
		Button no = (Button) dialog.findViewById(R.id.buttonNO);
		final TextView tv = (TextView) dialog.findViewById(R.id.task_message);

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				task_message = tv.getText().toString();
				inputMessage.setTextColor(Color.parseColor("#F08080"));
				showSelectList();
				dialog.dismiss();
			}
		});
		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				showSelectList();
				task_message = "";
				dialog.dismiss();
			}
		});
		dialog.show();

	}
}
