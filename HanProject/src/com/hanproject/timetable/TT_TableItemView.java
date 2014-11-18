package com.hanproject.timetable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class TT_TableItemView extends TextView {

	TT_TableItem item;
	int time=8;
	int day;
	
	public TT_TableItemView(Context context,int time,int day){
		super(context);
		this.time=time;
		this.day=day;
		init();
	}

	public void init(){
		setBackgroundColor(Color.WHITE);
		setTextSize(12);
		setTextColor(Color.BLACK);
		this.setGravity(Gravity.CENTER);
		if(time!=0)
		{
		setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(TT_TimeTableActivity.mode==true)
				{
					Intent intent=new Intent(getContext(),TT_LP_MainActivity.class);
					intent.putExtra("starttime", (time-1)+":00");
					intent.putExtra("finishtime",time+":00");
					intent.putExtra("day",day+1);
					intent.putExtra("mode",0);
					getContext().startActivity(intent);
				}
			}
			
		});
		}
	}
	
	public void setItem(){
		if(item.getSubjectName() == null)
			setText("");
		else
			setText(item.getSubjectName());
	}
}