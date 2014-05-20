package com.example.push.adapter;

import java.util.ArrayList;
 
import com.example.push.R;
import com.example.push.table.DayInfo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class CalendarAdapter extends GridviewAdapter
{
    private ArrayList<DayInfo> mDayList;
    private Context mContext;
    private int mResource;
    private LayoutInflater mLiInflater;
 
    /**
     * Adpater ������
     * 
     * @param context
     *            ���ؽ�Ʈ
     * @param textResource
     *            ���̾ƿ� ���ҽ�
     * @param dayList
     *            ��¥������ ����ִ� ����Ʈ
     */
    public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList)
    {
        this.mContext = context;
        this.mDayList = dayList;
        this.mResource = textResource;
        this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return mDayList.size();
    }
 
    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
    	
        return mDayList.get(position);
    }
 
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        DayInfo day = mDayList.get(position);
 
        DayViewHolde dayViewHolder;
 
        if(convertView == null)
        {
            convertView = mLiInflater.inflate(mResource, null);
   
            if(position % 7 == 6)
            {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()+getRestCellWidthDP(), getCellHeightDP()));
            }
            else
            {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));    
            }
             
             
            dayViewHolder = new DayViewHolde();
 
            dayViewHolder.llBackground = (LinearLayout)convertView.findViewById(R.id.day_cell_ll_background);
            dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.day_cell_tv_day);
             
            convertView.setTag(dayViewHolder);
        }else {
            dayViewHolder = (DayViewHolde) convertView.getTag();
        }
 
        if(day != null)
        {
            dayViewHolder.tvDay.setText(day.getDay());
 
            if(day.isInMonth())
            {
                if(position % 7 == 0)
                {
                    dayViewHolder.tvDay.setTextColor(Color.RED);
                }
                else if(position % 7 == 6)
                {
                    dayViewHolder.tvDay.setTextColor(Color.BLUE);
                }
                else
                {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            }
            else
            {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }
 
        }
 
        return convertView;
    }
     
}