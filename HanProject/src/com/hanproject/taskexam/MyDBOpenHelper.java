package com.hanproject.taskexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBOpenHelper extends SQLiteOpenHelper {

	String tableName;

	// 데이터베이스 생성
	public MyDBOpenHelper(Context context, String dbname, String tablename,
			CursorFactory factory, int version) {
		super(context, dbname, factory, version);
		// TODO Auto-generated constructor stub
		tableName = tablename;
	}

	// 테이블 생성
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d("myDBopenHelper", "onCreate called");

		if (tableName.equals("task")) {
			String sql = "create table task ( "
					+ " _id integer primary key autoincrement , "
					+ " subject text , " + " title text , "
					+ " contents text ) ";
			db.execSQL(sql);
		} else {
			String sql = "create table exam ( "
					+ " _id integer primary key autoincrement , "
					+ " subject text , " + " space text , "
					+ " hour integer , " + " minute integer ) ";
			db.execSQL(sql);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.d("myDBopenHelper", "onCreate called");
		if (tableName.equals("task")) {
			String sql = "drop table if exists task";
			db.execSQL(sql);
		} else {
			String sql = "drop table if exists exam";
			db.execSQL(sql);
		}
		onCreate(db);

	}

}
