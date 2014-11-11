package com.hanproject.timetable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.hanproject.R;

public class ListViewActivity extends ActionBarActivity {

	List<String> names = new ArrayList<String>();
	List<Drawable> icons = new ArrayList<Drawable>();

	SQLiteDatabase database;
	subjectDatabase helper;

	String DatabaseName = "SUBJECT_INFO";
	String TableName = "SUBJECT_TABLE";
	int columnIndex, changing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		Resources res = getResources();

		icons.add(res.getDrawable(R.drawable.icon01));
		icons.add(res.getDrawable(R.drawable.icon02));
		icons.add(res.getDrawable(R.drawable.icon03));
		icons.add(res.getDrawable(R.drawable.icon04));
		icons.add(res.getDrawable(R.drawable.icon05));
		icons.add(res.getDrawable(R.drawable.icon06));
		icons.add(res.getDrawable(R.drawable.icon01));


		ListView list = (ListView) findViewById(R.id.subject_listview);
		ListAdapter adapter = new ListAdapter();
		list.setAdapter(adapter);

		columnIndex = CalendarMonthViewActivity.columnIndex;
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
		dateText.setText(CalendarMonthViewActivity.day + "일 " + dayOfWeek
				+ "요일");

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					Intent intent = new Intent(getApplicationContext(),
							SelectChangeActivity.class);
					startActivityForResult(intent, 1002);
				} catch (Exception ex) {
					Log.d("TAG", ex.getMessage());
				}
			}
		});

		Button deleteTable = (Button) findViewById(R.id.deleteTable);
		deleteTable.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dropTable();
			}
		});

		try {
			int version = 1;
			helper = new subjectDatabase(this, DatabaseName, null, version);
			database = helper.getWritableDatabase();
		} catch (Exception ex) {
			Log.d("Error in create Database", ex.getMessage());
		}
		queryDatabyWeek(columnIndex);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
		super.onActivityResult(requestCode, resultCode, Data);

		if (requestCode == 1002) {
			changing = Data.getExtras().getInt("changing");
			Intent resultIntent = new Intent();
			resultIntent.putExtra("changing", 1);
			setResult(RESULT_OK, resultIntent);
			CalendarMonthViewActivity.monthViewAdapter.notifyDataSetChanged();
			finish();
		}
	}

	public void updateData() {
		String sql = "update " + TableName + " set changing = " + changing
				+ " where changing ";
	}

	public void dropTable() {
		String sql = "drop table " + TableName;
		database.execSQL(sql);
		Toast.makeText(getApplicationContext(), TableName + " 테이블 삭제",
				Toast.LENGTH_LONG).show();
	}

	public void queryChangeData() {
		try {
			String sql = "select _id, week, name, changing from " + TableName
					+ " where changing > 0";

			Cursor cursor = database.rawQuery(sql, null);
			int count = cursor.getCount();

			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				
			}

		} catch (Exception ex) {
		}

	}

	public void queryDatabyWeek(int dayOfWeek) {

		try {
			String sql = "select _id, week, name from " + TableName
					+ " where week = " + dayOfWeek;

			Cursor cursor = database.rawQuery(sql, null);
			int count = cursor.getCount();

			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				String name = cursor.getString(2);
				names.add(name);
			}

		} catch (Exception ex) {
		}
	}

	private class subjectDatabase extends SQLiteOpenHelper {

		public subjectDatabase(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Toast.makeText(getApplicationContext(), "데이터베이스 생성",
					Toast.LENGTH_LONG).show();
			createTable(db);
			insertData(db);

		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);

			try {
				createTable(db);
				insertData(db);
			} catch (Exception ex) {
				Log.d("Error in open database", ex.getMessage());
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

		public void insertData(SQLiteDatabase db) {
			/*
			 * 요일 표시 일 ~ 토 = 0 ~ 6 변경 사항 : 휴강 = 0, 보강 = 1, 강의실 변경 = 2
			 */
			try {

				String sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(1, 1, 'JAVA - Yap', 5)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(2, 2, 'DataStructure - Shin', 1)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(3, 3, 'Linear Al - Choe', 2)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(4, 4, 'Android - Yap', 3)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(5, 5, 'statistics - whang', 5)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(6, 6, 'algoritm - Kil', 1)";
				db.execSQL(sql);
				sql = "insert into "
						+ TableName
						+ "(_id, week, name, changing) values(7, 0, 'interface - Kim', 3)";
				db.execSQL(sql);

			} catch (Exception ex) {
				Log.d("Error in insert data", ex.getMessage());
			}
		}

		public void createTable(SQLiteDatabase db) {
			try {

				String sql = "create table "
						+ TableName
						+ "(_id integer PRIMARY KEY autoincrement, week integer, name text, changing integer)";
				db.execSQL(sql);
				Toast.makeText(getApplicationContext(), "테이블 생성",
						Toast.LENGTH_LONG).show();
			} catch (Exception ex) {
				Log.d("Error in create table", ex.getMessage());
			}
		}
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

			ItemList layout = new ItemList(getApplicationContext());

			layout.setSubjectText(names.get(position));
			layout.setImage(icons.get(position));
			return layout;
		}
	}
}
