package com.hanproject.timetable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanproject.R;

public class ItemList extends LinearLayout{

	ImageView image;
	TextView subjectText;
	
	public ItemList(Context context) {
		super(context);
		
		init(context);
	}

	public ItemList(Context context, AttributeSet attrs) {
		super(context);
		
		init(context);
	}

	public void init(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.itemlist, this, true);
		
		image = (ImageView) findViewById(R.id.icon);
		subjectText = (TextView) findViewById(R.id.name);
		
	}
	
	public void setImage(Drawable icon){
		image.setImageDrawable(icon);
	}
	
	public void setSubjectText(String name){
		subjectText.setText(name);
	}
}