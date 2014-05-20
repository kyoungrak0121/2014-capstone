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

public class SupplementFragment extends Calender  implements OnItemClickListener, OnClickListener{

	private TextView mTvCalenderSelect;
	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;
	private GridView mGvCalenderWeek;
	private Button mPushbtn;

	private Map<Integer,Map<Integer, String>> mSelectDayList ;
	
	private CalendarAdapter mCalendarAdapter;
	private CalendarWeekAdapter mCalenderWeekAdapter;
	
	Calendar mLastMonthCalendar;
	Calendar mThisMonthCalendar;
	Calendar mNextMonthCalendar;
	
	Button bLastMonth ;
	Button bNextMonth ;
	
	String selectData ; 
	int selectCount;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.cancle_class_fragment, container, false);
		
		setLayout(v);
		
		init();
		
        
		return v;
		
	}
	
	void init(){
		
		mDayList = new ArrayList<DayInfo>();
	     
		mSelectDayList = null;
		selectData  = "";
		selectCount = 0;
		
		mTvCalenderSelect.setText("");
		mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
   
        setCalender();
	}

	void setCalender(){
		// 캘린더 타이틀(년월 표시)을 세팅한다. 
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
               + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
		getCalendarWeek();
        getCalendar(mThisMonthCalendar);
        initCalendarAdapter();
	}
	void setLayout(View v){
		 bLastMonth = (Button)v.findViewById(R.id.gv_calendar_activity_b_last);
	     bNextMonth = (Button)v.findViewById(R.id.gv_calendar_activity_b_next);
	         
	     mTvCalendarTitle = (TextView)v.findViewById(R.id.gv_calendar_activity_tv_title);
	     mGvCalendar = (GridView)v.findViewById(R.id.gv_calendar_activity_gv_calendar);
	     mGvCalenderWeek = (GridView)v.findViewById(R.id.gv_calendar_activity_gv_calendar_week);
	     mTvCalenderSelect = (TextView)v.findViewById(R.id.gv_calendar_select_day);
	     
	     
	     mPushbtn = (Button)v.findViewById(R.id.pushTrans);
	    
	     mPushbtn.setOnClickListener(this);
	     bLastMonth.setOnClickListener(this);
	     bNextMonth.setOnClickListener(this);
	     mGvCalendar.setOnItemClickListener(this);	 
	     
	}

 
     
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
    {

    	int month;
    	month =  mThisMonthCalendar.get(Calendar.MONTH) + 1;
    	
    	DayInfo day = (DayInfo)mCalendarAdapter.getItem(position);

    	if(day.isInMonth()){    			
    		setSelectData(parent,v,month,day);
        
    	}
    }
    
    void showSelectList(){
    	selectData = "";
    	selectData = getMessage(mSelectDayList);
		
    	mTvCalenderSelect.setText(""+selectData);
    	
    }
    
    public void setSelectData(AdapterView<?> parent,View v ,int month, DayInfo day ){
    		
    	if(mSelectDayList == null){
    		mSelectDayList = new TreeMap<Integer, Map<Integer, String>>();
    	}
    	
    	if(!mSelectDayList.containsKey(month)){ // key 가 없으면 
    		Map<Integer,String> selectDay = new TreeMap<Integer, String>(); 
    		mSelectDayList.put(month, selectDay);
    	}
    	
    	// 날짜 가 없으면 넣음
    	if(!mSelectDayList.get(month).containsKey(Integer.parseInt(day.getDay()))){ 
    		
    		mSelectDayList.get(month).put(Integer.parseInt(day.getDay()),day.getDay());
    		Log.w("Day : " , ""+day.getDay() );
    		v.setBackgroundColor(Color.parseColor("#F08080"));
    		selectCount++;
    		
    		showDialog(month,day);
    		
    	}else{
    		
    		selectCount--;
    		mSelectDayList.get(month).remove(Integer.parseInt(day.getDay()));
    		
    		v.setBackgroundDrawable(parent.getBackground());
    		
    		//Toast.makeText(getActivity(),"delete : "+month+"/"+day.getDay(), Toast.LENGTH_SHORT).show();
        	
    	}
    	
    }
     
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
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
        case R.id.pushTrans :
        	
			if (selectCount > 0) {

				// //////////////////////////////////
				// /////id 나중에 DB로 변경!!
				SharedPreferences prefs = this.getActivity()
						.getSharedPreferences("regId", Activity.MODE_PRIVATE);
				final String regId = prefs.getString("regId", null);

				// DB
				setMessage("보강공지", "XX 강의 보강 공지", mSelectDayList);

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendMessage(regId);
					}
				}).start();
			
				init();
				
			}else{
				Toast.makeText(getActivity(),"날짜를 1개 이상 선택하여 주십시오.", Toast.LENGTH_SHORT).show();
			}
			break;
		}
    }
 
    private void initCalendarAdapter()
    {
    	mCalendarAdapter = new CalendarAdapter(getActivity().getApplicationContext(), R.layout.day, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
    
    
   	protected void getCalendarWeek(){
   		
   		mCalenderWeekAdapter = new CalendarWeekAdapter(week, R.layout.week ,getActivity());
   		mGvCalenderWeek.setAdapter(mCalenderWeekAdapter);	
   	}
   	
   	void showDialog(final int month ,final DayInfo day){
   		long now = System.currentTimeMillis();
   		Date date = new Date(now);
   		
   		SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
   		SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");
   		final int strCurHour = Integer.parseInt(CurHourFormat.format(date));
   		final int strCurMinute = Integer.parseInt(CurMinuteFormat.format(date));
   		
   		TimePickerDialog dialog = new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mSelectDayList.get(month).put(Integer.parseInt(day.getDay()), "" + strCurHour + " : " + strCurMinute  );
				showSelectList();
			}
		},strCurHour,strCurMinute,false);
		dialog.setTitle("setting Time!!");
		dialog.show();
   	}
}
