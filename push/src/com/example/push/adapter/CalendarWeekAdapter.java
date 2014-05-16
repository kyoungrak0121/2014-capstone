package com.example.push.adapter;


import com.example.push.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * BaseAdapter를 상속받아 구현한 CalendarAdapter
 * 
 * @author croute
 * @since 2011.03.08
 */
public class CalendarWeekAdapter extends BaseAdapter {
	String[] weekList =  null;
	Context contxt;

	private int mResource;

	    
	public CalendarWeekAdapter(String[] weekList,int textResource, Context context) {
		this.weekList = weekList;
		this.contxt = context;
		this.mResource = textResource;
        
	}

	@Override
	public int getCount() {

		return weekList.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		DayViewHolde dayViewHolder;
				
		if (convertView == null) {
			// create a new LayoutInflater
			LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(mResource, null);
			
			
			if(position % 7 == 6){    
				convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()+getRestCellWidthDP(), getCellHeightDP()));
	        }
	        else{        
	        	convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));    
	        }
			

			dayViewHolder = new DayViewHolde();
			   
	        dayViewHolder.llBackground = (LinearLayout)convertView.findViewById(R.id.week_cell_ll_background);
	        dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.calendar_week);
	             
			// set value into textview
		
	        convertView.setTag(dayViewHolder);
	       
		}else {
            dayViewHolder = (DayViewHolde) convertView.getTag();
        }

		if (weekList != null) {
			dayViewHolder.tvDay.setText(weekList[position]);

			if (position % 7 == 0) {
				dayViewHolder.tvDay.setTextColor(Color.RED);
			} else if (position % 7 == 6) {
				dayViewHolder.tvDay.setTextColor(Color.BLUE);
			} else {
				dayViewHolder.tvDay.setTextColor(Color.BLACK);
			}
		}

		return convertView;
	}
	
	public class DayViewHolde {
		public LinearLayout llBackground;
		public TextView tvDay;

	} 

    private int getCellWidthDP()
    {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 480/7;
         
        return cellWidth;
    }
     
    private int getRestCellWidthDP()
    {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 480%7;
         
        return cellWidth;
    }
     
    private int getCellHeightDP()
    {
//      int height = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = 480/6;
         
        return cellHeight;
    }
}