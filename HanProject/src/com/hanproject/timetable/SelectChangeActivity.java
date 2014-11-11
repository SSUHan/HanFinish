package com.hanproject.timetable;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hanproject.R;

public class SelectChangeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_change);

		Button cancelBtn = (Button) findViewById(R.id.cancel_class);
		Button addBtn = (Button) findViewById(R.id.add_class);
		Button changeRoomBtn = (Button) findViewById(R.id.change_classroom);

		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				resultIntent.putExtra("changing", 1);
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				resultIntent.putExtra("changing", 2);
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});
		changeRoomBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				resultIntent.putExtra("changing", 3);
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});
		
		
	}

	public void queryData(SQLiteDatabase db){
		
	}
}
