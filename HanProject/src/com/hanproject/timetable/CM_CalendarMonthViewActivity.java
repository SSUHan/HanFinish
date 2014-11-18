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


public class CM_CalendarMonthViewActivity extends Activity {

	CM_CalendarMonthView monthView;
	int monthInfoHeight;
	
	static CM_CalendarMonthAdapter monthViewAdapter;

	TextView monthText;

	int curYear;
	int curMonth;
	static int mPosition;
	static int selectedYear,day, columnIndex, changing, selectedMonth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cm_activity_calendar);

		monthView = (CM_CalendarMonthView) findViewById(R.id.monthView);
		monthViewAdapter = new CM_CalendarMonthAdapter(this);
		monthView.setAdapter(monthViewAdapter);

		// set listener
		monthView.setOnDataSelectionListener(new CM_OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				CM_MonthItem curItem = (CM_MonthItem) monthViewAdapter
						.getItem(position);
				day = curItem.getDay();
				Intent intent = new Intent(getBaseContext(),
						DI_ListViewActivity.class);
				mPosition = position;
				columnIndex = position % 7;
				selectedMonth = curMonth;
				selectedYear=curYear;

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
					CM_MonthItem curItem = (CM_MonthItem) monthViewAdapter
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