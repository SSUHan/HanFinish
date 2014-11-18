package com.hanproject.timetable;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

public class CM_MonthItemView extends TextView {

	private CM_MonthItem item;

	public CM_MonthItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		init();
	}

	public CM_MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		init();
	}

	private void init() {
		setBackgroundColor(Color.WHITE);
	}

	public CM_MonthItem getItem() {
		return item;
	}

	public void setItem(CM_MonthItem item) {
		this.item = item;

		int day = item.getDay();
		if (day != 0) {
			setText(String.valueOf(day));

		} else {
			setText("");
		}

	}
}
