package com.hanproject.timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.hanproject.R;

public class TT_TimeTableActivity extends Activity {

	String databaseName = "timeplus.db";
	String tableName = "base";
	String tableName1 = "base_change";
	String tableName2 = "base_minus";
	String tableName3 = "base_add";
	String date="";

	List<TT_LP_Data> datas = new ArrayList<TT_LP_Data>();

	FragmentManager fm = getFragmentManager();
	static SQLiteDatabase db;
	static boolean mode = false;
	static int screenWidth;
	public static int screenHeight;
	static int day = 0;
	static int time2;
	GridView timeTable;
	int viewWidth;
	float viewHeight;
	boolean setting = false;
	Button btn;
	final int SCHEDULE_MENU_REQUEST = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Date oridate=new Date();
		date+=(1900+oridate.getYear())+".";
		date+=(oridate.getMonth()+1)+".";
		date+=oridate.getDate();
		try {
			date = addDate(date, -(oridate.getDay()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		init();
		
		

	}
	void init()
	{	
		setContentView(R.layout.tt_timetable);

		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode==false) {
					btn.setBackgroundResource(R.drawable.unlock);
					mode = true;
				} else {
					btn.setBackgroundResource(R.drawable.lock);;
					mode = false;
				}
			}

		});

		TT_TimeTableAdapter adapter = new TT_TimeTableAdapter(this);
		timeTable = (GridView) findViewById(R.id.timetable);
		
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;


		adapter.addItem(new TT_TableItem("", 0, 0));
		adapter.addItem(new TT_TableItem("월", 0, 0));
		adapter.addItem(new TT_TableItem("화", 0, 0));
		adapter.addItem(new TT_TableItem("수", 0, 0));
		adapter.addItem(new TT_TableItem("목", 0, 0));
		adapter.addItem(new TT_TableItem("금", 0, 0));

		int time = 8;
		for (int i = 0; i < 66; i++) {
			day = i % 6;
			if (i % 6 == 0) {
				adapter.addItem(new TT_TableItem((time++) + "시", 0, 0));
			} else
				adapter.addItem(new TT_TableItem("", time, day));
		}

		timeTable.setAdapter(adapter);
	}

	protected void onResume() {
		super.onResume();
		
		init();
		
		if(setting==true)
		{
			btn = (Button) findViewById(R.id.btn);
			if (mode == true)
				btn.setBackgroundResource(R.drawable.unlock);
			else
				btn.setBackgroundResource(R.drawable.lock);
			
			LayoutParams params= new LayoutParams(viewWidth,
					(int)viewHeight);
			btn.setLayoutParams(params);
			
			createDataBase();
			createTable();
			queryTotalData();
		}
		
		

		
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (setting == false) {
			viewWidth = timeTable.getWidth() / 6;
			viewHeight = timeTable.getHeight() / 12;
			setting = true;
			
			LayoutParams params= new LayoutParams(viewWidth,
					(int)viewHeight);
			btn.setLayoutParams(params);
			
			createDataBase();
			createTable();
			queryTotalData();
		}
	}

	String numberTwo(int s) {
		String st = String.valueOf(s);
		if (s < 10) {
			st = "0" + st;
		}
		return st;
	}

	void createDataBase() {
		db = openOrCreateDatabase(databaseName, MODE_WORLD_WRITEABLE, null);
	}

	void createTable() {
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ tableName
				+ "(subject text,professor text,classroom text,"
				+ "starttime text,finishtime text,day integer,color integer,memo text)");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName2
				+ "(starttime text" + ",day integer,date text)");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName1
				+ "(starttime text" + ",day integer,date text,classroom text)");
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName3+ "(info text,starttime text,finishtime text,"
	    			+ "classroom text,day integer,date text)");
	}

	void queryTotalData() {
		TT_LP_Data data;
		DI_CC_Data data1;
		DI_CM_Data data2;
		DI_CP_Data data3;

		String sql = "Select day, subject, starttime, finishtime, color, professor,classroom from "
				+ tableName;
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				int day = cursor.getInt(0);
				String subject = cursor.getString(1);
				String professor = cursor.getString(5);
				String classroom = cursor.getString(6);
				String starttime = cursor.getString(2);
				String finishtime = cursor.getString(3);
				int color = cursor.getInt(4);

				data = new TT_LP_Data(subject, professor, color, day,
						classroom, starttime, finishtime);
				datas.add(data);
			}
		}
		
		sql = "Select classroom,day,starttime from "+ tableName1;
		sql+=" where date='"+date+"'";
		cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				int day = cursor.getInt(1);
				String classroom = cursor.getString(0);
				String starttime = cursor.getString(2);

				data1 = new DI_CC_Data(classroom, starttime,day,date);
				
				for(int j=0;j<datas.size();j++)
					if(datas.get(j).day==data1.day
					&&datas.get(j).startTime.equals(data1.starttime))
					{
						datas.get(j).changeclassroom=data1.classroom;
						datas.get(j).subjectName="*"+datas.get(j).subjectName;
						datas.get(j).mode2=2;
					}
			}
		}
		
		sql = "Select day,starttime from "+ tableName2;
		sql+=" where date='"+date+"'";
		cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				int day = cursor.getInt(0);
				String starttime = cursor.getString(1);

				data2 = new DI_CM_Data( starttime,day,date);
				
				for(int j=0;j<datas.size();j++)
					if(datas.get(j).day==data2.day
					&&datas.get(j).startTime.equals(data2.starttime))
					{
						datas.get(j).color+=100;
						datas.get(j).mode2=1;
					}
			}
		}
		
		sql = "Select info,classroom,starttime,finishtime,day from "+ tableName3;
		sql+=" where date='"+date+"'";
		cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				String subject = cursor.getString(0);
				String classroom = cursor.getString(1);
				String starttime = cursor.getString(2);
				String finishtime = cursor.getString(3);
				int day = cursor.getInt(4);

				data3 = new DI_CP_Data(subject,classroom,starttime,finishtime,day,date);
				StringTokenizer st = new StringTokenizer(data3.starttime, ":");
				int startHours = Integer.parseInt(st.nextToken());
				int startMinute = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(data3.finishtime, ":");
				int finishHours = Integer.parseInt(st.nextToken());
				int finishMinute = Integer.parseInt(st.nextToken());
				int duration = caldur(startHours, startMinute, finishHours,
						finishMinute);
				
				setSubjectName(data3.day - 1, data3.info
						, startHours, startMinute, duration, 0,3,"",data3);
				
			}
		}
		
		for (int i = 0;i< datas.size(); i++) 
		{
			StringTokenizer st = new StringTokenizer(datas.get(i).startTime, ":");
			int startHours = Integer.parseInt(st.nextToken());
			int startMinute = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(datas.get(i).finishTime, ":");
			int finishHours = Integer.parseInt(st.nextToken());
			int finishMinute = Integer.parseInt(st.nextToken());
			int duration = caldur(startHours, startMinute, finishHours,
					finishMinute);

			setSubjectName(datas.get(i).day - 1, datas.get(i).subjectName
					, startHours, startMinute, duration, datas.get(i).color,datas.get(i).mode2
					,datas.get(i).changeclassroom,null);
		}
		datas= new ArrayList<TT_LP_Data>();
	}

	TT_LP_Data queryEditData(String name, int startHours, int startMinute,
			int dayOfWeek) {
		TT_LP_Data data = null;
		String startTime = startHours + ":" + numberTwo(startMinute);
		String sql = "Select subject, professor, day ,  classroom,starttime,finishtime, color from "
				+ name + " where starttime = '" + startTime + "' and ";
		sql += "day = " + (dayOfWeek + 1);

		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				String subject = cursor.getString(0);
				String professor = cursor.getString(1);
				int day = cursor.getInt(2);
				int color = cursor.getInt(6);
				String classroom = cursor.getString(3);
				String starttime = cursor.getString(4);
				String finishtime = cursor.getString(5);
				data = new TT_LP_Data(subject, professor, color, day,
						classroom, starttime, finishtime);
			}
		}
		return data;
	}

	String queryMemoData(String name, int startHours, int startMinute,
			int dayOfWeek) {
		String memo = "";
		String startTime = startHours + ":" + numberTwo(startMinute);
		String sql = "Select memo from " + name + " where starttime = '"
				+ startTime + "' and ";
		sql += "day = " + (dayOfWeek + 1);
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				memo = cursor.getString(0);
			}
		}
		return memo;
	}

	private int caldur(int startHours, int startMinute, int finishHours,
			int finishMinute) {
		// TODO Auto-generated method stub
		int duration;
		if (finishMinute > startMinute)
			duration = (finishMinute - startMinute) + 60
					* (finishHours - startHours);
		else
			duration = (60 + finishMinute - startMinute) + 60
					* (finishHours - startHours - 1);
		return duration;
	}
//mode2->정상 0 휴강 1 강의실변경 2 보강 3
	public void setSubjectName(final int dayOfWeek, String SubjectName,
			final int startHours, final int startMinute, float durationMinute,
			int color,final int mode2,final String changeclassroom,final DI_CP_Data cpdata) {

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

		TextView subjectNameView = new TextView(this);
		subjectNameView.setText(SubjectName);
		subjectNameView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TT_LP_Data data;
				data = queryEditData("base", startHours, startMinute, dayOfWeek);
				if (mode == true && mode2 != 3) {
					Intent intent = new Intent(getApplicationContext(),
							TT_LP_MainActivity.class);
					intent.putExtra("mode", 1);
					intent.putExtra("subject", data.subjectName);
					intent.putExtra("professor", data.professorName);
					intent.putExtra("color", data.color);
					intent.putExtra("classroom", data.classNumber);
					intent.putExtra("day", data.day);
					intent.putExtra("starttime", data.startTime);
					intent.putExtra("finishtime", data.finishTime);
					startActivity(intent);
				} else {
					TT_InfoFragment dFragment;
					if(mode2!=3){
						String memo = queryMemoData("base", startHours,
								startMinute, dayOfWeek);
						if(mode2==2)
							 dFragment = new TT_InfoFragment(data, memo,
									"base",mode2,changeclassroom);
						else
							 dFragment = new TT_InfoFragment(data, memo,
									"base",mode2);
						dFragment.show(fm, "Dialog Fragment");}
					else{
						 dFragment = new TT_InfoFragment(cpdata);
						dFragment.show(fm, "Dialog Fragment");}
					}
				}
			});

		// 색 설정
		if (color % 100 == 0)
			subjectNameView.setBackgroundColor(Color.rgb(85,119, 85));
		else if (color % 100 == 1)
			subjectNameView.setBackgroundColor(Color.rgb(255, 0, 0));
		else if (color % 100 == 2)
			subjectNameView.setBackgroundColor(Color.rgb(0, 255, 0));
		else if (color % 100 == 3)
			subjectNameView.setBackgroundColor(Color.rgb(0, 0, 255));
		else if (color % 100 == 4)
			subjectNameView.setBackgroundColor(Color.rgb(255, 255, 0));
		else if (color % 100 == 5)
			subjectNameView.setBackgroundColor(Color.rgb(255, 0, 255));
		else if (color % 100 == 6)
			subjectNameView.setBackgroundColor(Color.rgb(0, 255, 255));

		if (color > 100)
		{
			Drawable d=subjectNameView.getBackground();
			d.setAlpha(50);
		}

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// 요일 설정
		params.leftMargin = viewWidth + (dayOfWeek * viewWidth) + 1;

		// 시간에 따른 높이 설정
		double aMinuteHeight = viewHeight / 60;
		params.height = (int) (aMinuteHeight * durationMinute);

		// 한 과목의 최대 넓이, 줄 수, 가운데 정렬 설정
		subjectNameView.setMaxWidth(viewWidth - 2);
		subjectNameView.setWidth(viewWidth - 2);
		subjectNameView.setGravity(Gravity.CENTER_HORIZONTAL);
		subjectNameView.setMaxLines(2);

		// 시작 위치 설정.
		params.topMargin = (int) (viewHeight + ((startHours - 8) * viewHeight)
				+ caculateHeight(startMinute) + 2);
		if (startHours >= 15)
			params.topMargin += 5;

		subjectNameView.setLayoutParams(params);
		layout.addView(subjectNameView);
	}

	public int caculateHeight(float minute) {
		int answer = 0;
		for (int i = 1; i <= minute; i++) {
			if (i % 2 == 0)
				answer += 2;
			else
				answer += 1;
		}

		return answer;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.timetable_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.schedule:
			intent = new Intent(getApplicationContext(),
					com.hanproject.timetable.CM_CalendarMonthViewActivity.class);
			startActivityForResult(intent, SCHEDULE_MENU_REQUEST);
			break;
		}

		return true;
	}
	public String addDate(String da, int dd) throws ParseException 
	{

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date date = format.parse(da);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);


        calendar.add(Calendar.DATE, dd);


        return format.format(calendar.getTime());

    }

}