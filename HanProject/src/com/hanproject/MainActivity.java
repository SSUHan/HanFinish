package com.hanproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	Button goTimeTable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_start);
		
		/*SQLiteDatabase db;
		db=openOrCreateDatabase("timeplus.db",MODE_WORLD_WRITEABLE, null);
		String sql="delete from base where subject='심리학' and day=3";
    	db.execSQL(sql);
		sql="delete from base where subject='컴구' and day=3";
		db.execSQL(sql);
		
		SQLiteDatabase db;
		db=openOrCreateDatabase("timeplus.db",MODE_WORLD_WRITEABLE, null);
		String sql="drop table base_break";
		db.execSQL(sql);*/
		
		goTimeTable = (Button)findViewById(R.id.button1);
		goTimeTable.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new Intent(getApplicationContext(),com.hanproject.timetable.TT_TimeTableActivity.class);
			    startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
