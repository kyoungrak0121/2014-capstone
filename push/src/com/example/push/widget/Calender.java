package com.example.push.widget;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Fragment;
import android.util.Log;

import com.example.push.R;
import com.example.push.adapter.CalendarAdapter;
import com.example.push.adapter.CalendarWeekAdapter;
import com.example.push.professor.fragment.SuperFragment;
import com.example.push.table.DayInfo;

public class Calender extends SuperFragment {
	
	public static int SUNDAY = 1;
	public static int MONDAY = 2;
	public static int TUESDAY = 3;
	public static int WEDNSESDAY = 4;
	public static int THURSDAY = 5;
	public static int FRIDAY = 6;
	public static int SATURDAY = 7;
	public static String[] week = { "S"	,"M","T","W","T","F","S" };
	
	

	
	protected ArrayList<DayInfo> mDayList;

	
	   /**
     * �޷��� �����Ѵ�.
     * 
     * @param calendar �޷¿� �������� �̹����� Calendar ��ü
     */
   
	
	protected void getCalendar(Calendar calendar)
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
     * �������� Calendar ��ü�� ��ȯ�մϴ�.
     * 
     * @param calendar
     * @return LastMonthCalendar
     */
	
    protected Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
    /*    mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
    */
        return calendar;
    }
 
    /**
     * �������� Calendar ��ü�� ��ȯ�մϴ�.
     * 
     * @param calendar
     * @return NextMonthCalendar
     */
    protected Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
     /*   mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
     */
        return calendar;
    }

}
