package com.hanproject.timetable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanproject.R;

public class DI_ItemListAdd extends LinearLayout{

	View image;
	TextView infoText;
	
	public DI_ItemListAdd(Context context) {
		super(context);
		
		init(context);
	}

	public DI_ItemListAdd(Context context, AttributeSet attrs) {
		super(context);
		
		init(context);
	}

	public void init(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.di_itemlist_add, this, true);
		
		infoText = (TextView) findViewById(R.id.info);
		
	}
	
	
	public void setSubjectText(String info){
		infoText.setText(info);
	}
}