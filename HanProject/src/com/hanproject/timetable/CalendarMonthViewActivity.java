package com.hanproject.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.hanproject.R;


public class CalendarMonthViewActivity extends Activity {

	CalendarMonthView monthView;
	int monthInfoHeight;
	
	static CalendarMonthAdapter monthViewAdapter;

	TextView monthText;

	int curYear;
	int curMonth;
	static int mPosition;
	static int day, columnIndex, changing, selectedMonth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		monthView = (CalendarMonthView) findViewById(R.id.monthView);
		monthViewAdapter = new CalendarMonthAdapter(this);
		monthView.setAdapter(monthViewAdapter);

		// set listener
		monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				MonthItem curItem = (MonthItem) monthViewAdapter
						.getItem(position);
				day = curItem.getDay();
				Intent intent = new Intent(getBaseContext(),
						ListViewActivity.class);
				mPosition = position;
				columnIndex = position % 7;
				selectedMonth = curMonth;

				startActivityForResult(intent, 1001);
				Log.d("CalendarMonthViewActivity", "Selected : " + day);
			}
		});

		monthText = (TextView) findViewById(R.id.monthText);
		setMonthText();

		Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
		monthPrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthViewAdapter.setPreviousMonth();
				monthViewAdapter.notifyDataSetChanged();

				setMonthText();
			}
		});

		Button monthNext = (Button) findViewById(R.id.monthNext);
		monthNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthViewAdapter.setNextMonth();
				monthViewAdapter.notifyDataSetChanged();

				setMonthText();
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
		super.onActivityResult(requestCode, resultCode, Data);

		try {
			if (requestCode == 1001) {
				changing = Data.getExtras().getInt("changing");
				if (changing > 0) {
					MonthItem curItem = (MonthItem) monthViewAdapter
							.getItem(mPosition);
					curItem.setChanging(1);

					changing = monthViewAdapter.setDayChangedMonth(curItem,
							mPosition);

				}
			}
		} catch (Exception ex) {
		}
	}

	private void setMonthText() {
		curYear = monthViewAdapter.getCurYear();
		curMonth = monthViewAdapter.getCurMonth();

		monthText.setText(curYear + "³â " + (curMonth + 1) + "¿ù");
	}

}