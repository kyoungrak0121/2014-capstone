package com.example.push.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter {
	Context mContext;
	 int count = 30;
	 String[] mWeekTitleIds ={
	  "일",
	  "월",
	  "화",
	  "수",
	  "목",
	  "금",
	  "토"
	 };
	 void ClassScheduleAdapter(Context context){
	  mContext = context;
	 }
	 @Override
	 public int getCount() {
	  // TODO Auto-generated method stub
	  return count;
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
	 public View getView(int position, View oldView, ViewGroup parent) {
	  // TODO Auto-generated method stub
	  View v=null;
	  if(oldView == null)
	  {
	   v = new TextView(mContext);
	   //view size(40 / 60)
	   v.setLayoutParams(new GridView.LayoutParams(40 , 60));
	  }
	  else if (position < 7) {
	   v = new TextView(mContext);
	   ((TextView)v).setGravity(Gravity.CENTER);
	   ((TextView)v).setText(mWeekTitleIds[position]);
	   v.setBackgroundColor(Color.GRAY);

	  }
	  else if (position >= 7 && position < count) {
	   v = new TextView(mContext);
	   ((TextView)v).setGravity(Gravity.CENTER);
	   ((TextView)v).setText("Sajo");
	   v.setBackgroundColor(Color.GRAY);

	  }  
	  else {
	   v = oldView;
	  }
	  
	  return v;
	 }
}
