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

public class DI_ItemList extends LinearLayout{

	View image;
	TextView subjectText;
	
	public DI_ItemList(Context context) {
		super(context);
		
		init(context);
	}

	public DI_ItemList(Context context, AttributeSet attrs) {
		super(context);
		
		init(context);
	}

	public void init(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.di_itemlist, this, true);
		
		image = (View) findViewById(R.id.icon);
		subjectText = (TextView) findViewById(R.id.name);
		
	}
	
	public void setImage(int i){
		switch(i)
		{
		case 1 :
			image.setBackgroundColor(Color.rgb(255, 0, 0));
			break;
		case 2 :
			image.setBackgroundColor(Color.rgb(0, 255, 0));
			break;
		case 3 :
			image.setBackgroundColor(Color.rgb(0, 0, 255));
			break;
		case 4:
			image.setBackgroundColor(Color.rgb(255, 255, 0));
			break;
		case 5:
			image.setBackgroundColor(Color.rgb(255, 0, 255));
			break;
		case 6:
			image.setBackgroundColor(Color.rgb(0, 255, 255));
			break;
		default : 
				
		}
	}
	
	public void setSubjectText(String name){
		subjectText.setText(name);
	}
}