package com.example.push.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
 
 
/**
 * BaseAdapter를 상속받아 구현한 CalendarAdapter
 * 
 * @author croute
 * @since 2011.03.08
 */
public class GridviewAdapter extends BaseAdapter {
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
 
    public class DayViewHolde
    {
        public LinearLayout llBackground;
        public TextView tvDay;
         
    }
 
    protected int getCellWidthDP()
    {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 480/7;
         
        return cellWidth;
    }
     
    protected int getRestCellWidthDP()
    {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 480%7;
         
        return cellWidth;
    }
     
    protected int getCellHeightDP()
    {
//      int height = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = 480/6;
         
        return cellHeight;
    }
     
}