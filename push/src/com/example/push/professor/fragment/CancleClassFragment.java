package com.example.push.professor.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.example.push.R;
import com.example.push.R.id;
import com.example.push.R.layout;
import com.example.push.R.menu;
import com.example.push.adapter.CalendarAdapter;
import com.example.push.adapter.CalendarWeekAdapter;
import com.example.push.table.DayInfo;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class CancleClassFragment extends SuperFragment  implements OnItemClickListener, OnClickListener{

	
	public static int SUNDAY = 1;
	public static int MONDAY = 2;
	public static int TUESDAY = 3;
	public static int WEDNSESDAY = 4;
	public static int THURSDAY = 5;
	public static int FRIDAY = 6;
	public static int SATURDAY = 7;
	
	public static String[] week = { "S"	,"M","T","W","T","F","S" };

	private TextView mTvCalenderSelect;
	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;
	private GridView mGvCalenderWeek;
	private Button mPushbtn;

	private ArrayList<DayInfo> mDayList;
	private CalendarAdapter mCalendarAdapter;
	private CalendarWeekAdapter mCalenderWeekAdapter;

	private Map<Integer,Map<Integer, Integer>> mSelectDayList ;
	
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
 
        getCalendarWeek();
        getCalendar(mThisMonthCalendar);
		
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

    /**
     * �޷��� �����Ѵ�.
     * 
     * @param calendar �޷¿� �������� �̹����� Calendar ��ü
     */
    
	
	private void getCalendarWeek(){
		
		mCalenderWeekAdapter = new CalendarWeekAdapter(week, R.layout.week ,getActivity());
		mGvCalenderWeek.setAdapter(mCalenderWeekAdapter);
		
	}
	private void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;
         
        mDayList.clear();
       
         
        // �̹��� �������� ������ ���Ѵ�. �������� �Ͽ����� ��� �ε����� 1(�Ͽ���)���� 8(������ �Ͽ���)�� �ٲ۴�.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        calendar.add(Calendar.MONTH, -1);
        Log.e("������ ��������", calendar.get(Calendar.DAY_OF_MONTH)+"");
 
        // �������� ������ ���ڸ� ���Ѵ�.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
 
        calendar.add(Calendar.MONTH, 1);
        Log.e("�̹��� ������", calendar.get(Calendar.DAY_OF_MONTH)+"");
         
        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }
         
        lastMonthStartDay -= (dayOfMonth-1)-1;
         
        // Ķ���� Ÿ��Ʋ(��� ǥ��)�� �����Ѵ�. 
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
 
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
         
        initCalendarAdapter();
    }
 
    /**
     * �������� Calendar ��ü�� ��ȯ�մϴ�.
     * 
     * @param calendar
     * @return LastMonthCalendar
     */
    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
        return calendar;
    }
 
    /**
     * �������� Calendar ��ü�� ��ȯ�մϴ�.
     * 
     * @param calendar
     * @return NextMonthCalendar
     */
    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
        return calendar;
    }
     
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
    {

    	int month;
    	month =  mThisMonthCalendar.get(Calendar.MONTH) + 1;
    	
    	DayInfo day = (DayInfo)mCalendarAdapter.getItem(position);

    	selectData = "";
    	    	
    	if(day.isInMonth()){
    		
    		
    		setSelectData(parent,v,month,day);
    		
    		selectData = getMessage(mSelectDayList);
        	mTvCalenderSelect.setText(""+selectData);

        
    	}
    	
    
    }
    
    public void setSelectData(AdapterView<?> parent,View v ,int month, DayInfo day ){
    	
    	
    	
    	if(mSelectDayList == null){
    		mSelectDayList = new TreeMap<Integer, Map<Integer, Integer>>();
    	}
    	

    	if(!mSelectDayList.containsKey(month)){
    		Map<Integer,Integer> selectDay = new TreeMap<Integer, Integer>(); 
    		mSelectDayList.put(month, selectDay);
    	}
    	
    	// ��¥ �� ������ ����
    	if(!mSelectDayList.get(month).containsKey(Integer.parseInt(day.getDay()))){ 
    		
    		mSelectDayList.get(month).put(Integer.parseInt(day.getDay()),Integer.parseInt(day.getDay()));
    		Log.w("Day : " , ""+day.getDay() );
    		v.setBackgroundColor(Color.parseColor("#F08080"));
    		selectCount++;
    		
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
            break;
        case R.id.gv_calendar_activity_b_next:
            mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
            getCalendar(mThisMonthCalendar);
            break;
        case R.id.pushTrans :
        	
			if (selectCount > 0) {

				// //////////////////////////////////
				// /////id ���߿� DB�� ����!!
				SharedPreferences prefs = this.getActivity()
						.getSharedPreferences("regId", Activity.MODE_PRIVATE);
				final String regId = prefs.getString("regId", null);

				// DB
				setMessage("�ް�����", "XX ���� �ް� ����", mSelectDayList);

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sendMessage(regId);
					}
				}).start();
			
				init();
				
			}else{
				Toast.makeText(getActivity(),"��¥�� 1�� �̻� �����Ͽ� �ֽʽÿ�.", Toast.LENGTH_SHORT).show();
			}
			break;
		}
    }
 
    private void initCalendarAdapter()
    {
    	
    	mCalendarAdapter = new CalendarAdapter(getActivity().getApplicationContext(), R.layout.day, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
}
