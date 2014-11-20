package com.hanproject.timetable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

import com.hanproject.R;

public class DI_SelectChangeActivity extends ActionBarActivity {

	SQLiteDatabase db = DI_ListViewActivity.database;
	Switch swc;
	EditText classroomedit;
	Button check;
	Button classcancel;
	Button cancel;
	String tableName1 = "base_minus";
	String tableName2 = "base_change";
	String starttime;
	String date;
	String oriclassroom;
	int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.di_select_change);

		try {
			getActionBar().setTitle("변경 정보");
			getActionBar().setDisplayShowHomeEnabled(false);
		} catch (Exception e) {

		}
		classroomedit = (EditText) findViewById(R.id.classroomedit);
		check = (Button) findViewById(R.id.check);
		classcancel=(Button)findViewById(R.id.classcancel);
		cancel=(Button)findViewById(R.id.cancel);
		swc = (Switch) findViewById(R.id.switch1);
		createTable1();
		createTable2();
		
		open();
		
		swc.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) // on일때
				{
					insertData1();
				} 
				else
				{
					deleteData1();
				}
			}

		});
		check.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String classroom=classroomedit.getText().toString();
				if(queryData2bool())
					if(classroom.equals(oriclassroom))
						deleteData2();
					else
						changeData2(classroom);
				else
					if(classroom.equals(oriclassroom))
					{}
					else{
						insertData2(classroom);
					}
				Toast.makeText(getApplicationContext(), 
						date+"의 강의실이 "+classroom+"으로 변경되었습니다.", Toast.LENGTH_LONG).show();
				finish();
			}
			
		});
		classcancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(queryData2bool()){
						deleteData2();
						classroomedit.setText(oriclassroom);}
					
				else
					classroomedit.setText(oriclassroom);
			}
			
		});
		
		
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});

	}
	
	public void open()
	{
		starttime=getIntent().getExtras().getString("starttime");
		date=getIntent().getExtras().getString("date");
		day=getIntent().getExtras().getInt("day");
		swc.setChecked(queryData1());
		
		oriclassroom=getIntent().getExtras().getString("classroom");
		classroomedit.setText(queryData2());
	}

	boolean queryData1() 
	{
		boolean check=false;
		
		String sql = "select starttime from " + tableName1
				+" where day=" + day + " and date='" + date + "'"
				+ " and starttime='" +starttime + "'";
		Cursor cursor = db.rawQuery(sql, null);
		int count = cursor.getCount();
		
		if(count!=0)
			check=true;
		
		return check;
	}
	
	boolean queryData2bool() 
	{
		boolean check=false;
		
		String sql = "select classroom from " + tableName2
				+" where day=" + day + " and date='" + date + "'"
				+ " and starttime='" +starttime + "'";
		Cursor cursor = db.rawQuery(sql, null);
		int count = cursor.getCount();
		
		if(count!=0)
			check=true;
		
		return check;
	}
	
	String queryData2()
	{
		String result=oriclassroom;
		
		String sql = "select classroom from " + tableName2
				+" where day=" + day + " and date='" + date + "'"
				+ " and starttime='" +starttime + "'";
		
		Cursor cursor = db.rawQuery(sql, null);
		int count = cursor.getCount();
		
		if(count!=0){
			cursor.moveToNext();
			result=cursor.getString(0);
		}
		
		Log.d("sql",sql);
		Log.d("sql","ss"+count);
		
		return result;
	}

	void createTable1() {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName1
				+ "(starttime text" + ",day integer,date text)");
	}

	void createTable2() {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName2
				+ "(starttime text" + ",day integer,date text,classroom text)");
	}
	
	void changeData2(String classroom) {
		String sql = "update " + tableName2 + " set classroom='" + classroom
				+"' where day=" + day + " and date='" + date + "'"
				+ " and starttime='" +starttime + "'";
		Log.d("sql", sql);
		db.execSQL(sql);
	}

	void insertData1() {
		String sql = "insert into " + tableName1
				+ " (starttime,date,day) values ";
		sql += "('" + starttime + "','" + date + "'," + day + ")";
		db.execSQL(sql);
	}
	
	void insertData2(String classroom) {
		String sql = "insert into " + tableName2
				+ " (starttime,classroom,date,day) values ";
		sql += "('" + starttime + "','"+ classroom + "','"
				+ date + "'," + day + ")";
		Log.d("sql",sql);
		db.execSQL(sql);
	}

	void deleteData1() {
		String sql = "delete from " + tableName1 + " where starttime = " + "'"
				+ starttime + "' and ";
		sql += "day = " + day + " and date='" + date + "'";
		db.execSQL(sql);
	}
	
	void deleteData2() {
		String sql = "delete from " + tableName2 + " where starttime = " + "'"
				+ starttime + "' and ";
		sql += "day = " + day + " and date='" + date + "'";
		db.execSQL(sql);
	}
	

}
