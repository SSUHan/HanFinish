package com.hanproject.timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hanproject.R;

public class DI_ListViewActivity extends ActionBarActivity {

	Button addclass;

	List<String> names = new ArrayList<String>();
	List<String> starttimes = new ArrayList<String>();
	List<String> classrooms = new ArrayList<String>();
	List<Integer> icons = new ArrayList<Integer>();

	List<String> infos = new ArrayList<String>();
	List<String> times = new ArrayList<String>();

	String date;

	static SQLiteDatabase database;

	String DatabaseName = "timeplus.db";
	String TableName = "base";
	String TableName2 = "base_add";
	int columnIndex, changing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		columnIndex = CM_CalendarMonthViewActivity.columnIndex;

		date = CM_CalendarMonthViewActivity.selectedYear + ".";
		date += (CM_CalendarMonthViewActivity.selectedMonth + 1) + ".";
		date += CM_CalendarMonthViewActivity.day;
		try {
			date = addDate(date, -columnIndex);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("date2", date);
		
		init();
		
		/*setContentView(R.layout.di_listview);

		createDataBase();

		Resources res = getResources();

		ListView list = (ListView) findViewById(R.id.subject_listview);
		ListAdapter adapter = new ListAdapter();
		list.setAdapter(adapter);

		ListView list2 = (ListView) findViewById(R.id.addclass_listview);
		ListAdapter_Add adapter2 = new ListAdapter_Add();
		list2.setAdapter(adapter2);

		columnIndex = CM_CalendarMonthViewActivity.columnIndex;

		date = CM_CalendarMonthViewActivity.selectedYear + ".";
		date += (CM_CalendarMonthViewActivity.selectedMonth + 1) + ".";
		date += CM_CalendarMonthViewActivity.day;
		try {
			date = addDate(date, -columnIndex);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("date2", date);

		addclass = (Button) findViewById(R.id.plusclass);
		addclass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						DI_CP_PlusClassFragment.class);
				intent.putExtra("mode", 0);
				intent.putExtra("day", columnIndex);
				intent.putExtra("date", date);
				startActivity(intent);
			}

		});
		String dayOfWeek;
		if (columnIndex == 0) {
			dayOfWeek = "일";
		} else if (columnIndex == 1) {
			dayOfWeek = "월";
		} else if (columnIndex == 2) {
			dayOfWeek = "화";
		} else if (columnIndex == 3) {
			dayOfWeek = "수";
		} else if (columnIndex == 4) {
			dayOfWeek = "목";
		} else if (columnIndex == 5) {
			dayOfWeek = "금";
		} else {
			dayOfWeek = "토";
		}

		TextView dateText = (TextView) findViewById(R.id.dateText);
		dateText.setText(CM_CalendarMonthViewActivity.day + "일 " + dayOfWeek
				+ "요일");

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					Intent intent = new Intent(getApplicationContext(),
							DI_SelectChangeActivity.class);
					intent.putExtra("date", date);
					intent.putExtra("day", columnIndex);
					intent.putExtra("starttime", starttimes.get(position));
					intent.putExtra("classroom", classrooms.get(position));
					startActivityForResult(intent, 1002);
				} catch (Exception ex) {
					Log.d("TAG", ex.getMessage());
				}
			}
		});

		list2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					DI_CP_Data data = querybyTime(columnIndex,
							times.get(position));
					Intent intent = new Intent(getApplicationContext(),
							DI_CP_PlusClassFragment.class);
					intent.putExtra("mode", 1);
					intent.putExtra("info", data.info);
					intent.putExtra("classroom", data.classroom);
					intent.putExtra("starttime", data.starttime);
					intent.putExtra("finishtime", data.finishtime);
					intent.putExtra("day", data.day);
					intent.putExtra("date", data.date);
					startActivity(intent);
				} catch (Exception ex) {
					Log.d("TAG", ex.getMessage());
				}
			}
		});

		queryDatabyWeek(columnIndex);
		queryAddDatabyWeek(columnIndex);*/
	}
	public void onResume()
	{
		super.onResume();
		
		init();
	}

	void init() {
		setContentView(R.layout.di_listview);

		createDataBase();

		Resources res = getResources();

		ListView list = (ListView) findViewById(R.id.subject_listview);
		ListAdapter adapter = new ListAdapter();
		list.setAdapter(adapter);

		ListView list2 = (ListView) findViewById(R.id.addclass_listview);
		ListAdapter_Add adapter2 = new ListAdapter_Add();
		list2.setAdapter(adapter2);


		addclass = (Button) findViewById(R.id.plusclass);
		addclass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						DI_CP_PlusClassFragment.class);
				intent.putExtra("mode", 0);
				intent.putExtra("day", columnIndex);
				intent.putExtra("date", date);
				startActivity(intent);
			}

		});
		
		String dayOfWeek;
		if (columnIndex == 0) {
			dayOfWeek = "일";
		} else if (columnIndex == 1) {
			dayOfWeek = "월";
		} else if (columnIndex == 2) {
			dayOfWeek = "화";
		} else if (columnIndex == 3) {
			dayOfWeek = "수";
		} else if (columnIndex == 4) {
			dayOfWeek = "목";
		} else if (columnIndex == 5) {
			dayOfWeek = "금";
		} else {
			dayOfWeek = "토";
		}

		TextView dateText = (TextView) findViewById(R.id.dateText);
		dateText.setText(CM_CalendarMonthViewActivity.day + "일 " + dayOfWeek
				+ "요일");

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					Intent intent = new Intent(getApplicationContext(),
							DI_SelectChangeActivity.class);
					intent.putExtra("date", date);
					intent.putExtra("day", columnIndex);
					intent.putExtra("starttime", starttimes.get(position));
					intent.putExtra("classroom", classrooms.get(position));
					startActivityForResult(intent, 1002);
				} catch (Exception ex) {
					Log.d("TAG", ex.getMessage());
				}
			}
		});

		list2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					DI_CP_Data data = querybyTime(columnIndex,
							times.get(position));
					Intent intent = new Intent(getApplicationContext(),
							DI_CP_PlusClassFragment.class);
					intent.putExtra("mode", 1);
					intent.putExtra("info", data.info);
					intent.putExtra("classroom", data.classroom);
					intent.putExtra("starttime", data.starttime);
					intent.putExtra("finishtime", data.finishtime);
					intent.putExtra("day", data.day);
					intent.putExtra("date", data.date);
					startActivity(intent);
				} catch (Exception ex) {
					Log.d("TAG", ex.getMessage());
				}
			}
		});

		queryDatabyWeek(columnIndex);
		queryAddDatabyWeek(columnIndex);
	}

	void createDataBase() {
		database = openOrCreateDatabase(DatabaseName, MODE_WORLD_WRITEABLE,
				null);
	}

	void createTable() {
		database.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TableName
				+ "(subject text,professor text,classroom text,"
				+ "starttime text,finishtime text,day integer,color integer,memo text)");
	}

	void crateAddTable() {
		database.execSQL("CREATE TABLE IF NOT EXISTS " + TableName2
				+ "(info text,starttime text,finishtime text,"
				+ "classroom text,day integer,date text)");
	}

	public void queryDatabyWeek(int dayOfWeek) {

		names = new ArrayList<String>();
		starttimes = new ArrayList<String>();
		classrooms = new ArrayList<String>();
		icons = new ArrayList<Integer>();

		
		try {
			String sql = "select color, subject, professor , starttime, classroom from "
					+ TableName + " where day = " + dayOfWeek;

			Cursor cursor = database.rawQuery(sql, null);
			int count = cursor.getCount();

			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				int color = cursor.getInt(0);
				String name = cursor.getString(1) + " - " + cursor.getString(2);
				String starttime = cursor.getString(3);
				String classroom = cursor.getString(4);
				names.add(name);
				icons.add(color);
				classrooms.add(classroom);
				starttimes.add(starttime);
			}

		} catch (Exception ex) {
		}
	}

	public void queryAddDatabyWeek(int day) {


		infos = new ArrayList<String>();
		times = new ArrayList<String>();
		
		try {
			String sql = "select info, starttime, finishtime from "
					+ TableName2 + " where day = " + day + " and date='" + date
					+ "'";

			Log.d("sql", sql);

			Cursor cursor = database.rawQuery(sql, null);
			int count = cursor.getCount();

			String info;
			String time;

			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				info = cursor.getString(0);
				time = cursor.getString(1) + " ~ " + cursor.getString(2);
				infos.add(info + " - " + time);
				times.add(cursor.getString(1));
			}

		} catch (Exception ex) {
		}
	}

	public DI_CP_Data querybyTime(int day, String time) {

		DI_CP_Data data = null;
		try {
			String sql = "select info, finishtime, classroom from "
					+ TableName2 + " where starttime='" + time + "' and day = "
					+ day + " and date='" + date + "'";

			Log.d("sql", sql);

			Cursor cursor = database.rawQuery(sql, null);

			int count = cursor.getCount();

			String info = "";
			String finishtime = "";
			String classroom = "";

			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				info = cursor.getString(0);
				finishtime = cursor.getString(1);
				classroom = cursor.getString(2);
			}
			data = new DI_CP_Data(info, classroom, time, finishtime, day, date);

		} catch (Exception ex) {
		}
		return data;
	}

	class ListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return names.size();
		}

		@Override
		public Object getItem(int position) {
			return names.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			DI_ItemList layout = new DI_ItemList(getApplicationContext());

			layout.setSubjectText(names.get(position));
			layout.setImage(icons.get(position));
			return layout;
		}
	}

	class ListAdapter_Add extends BaseAdapter {

		@Override
		public int getCount() {
			return infos.size();
		}

		@Override
		public Object getItem(int position) {
			return infos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			DI_ItemListAdd layout = new DI_ItemListAdd(getApplicationContext());

			layout.setSubjectText(infos.get(position));
			return layout;
		}
	}

	public String addDate(String da, int dd) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		Date date = format.parse(da);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.DATE, dd);

		return format.format(calendar.getTime());

	}
}
