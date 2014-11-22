package com.hanproject.taskexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDBHandler {
	MyDBOpenHelper helper;
	SQLiteDatabase db;
	
	//초기화작업
	public MyDBHandler(Context context,String tablename) {
		// TODO Auto-generated constructor stub
		helper = new MyDBOpenHelper(context,"taskexam.db",tablename,null,1);
	}
	
	//open
	public static MyDBHandler open(Context context,String tablename){
		return new MyDBHandler(context,tablename);
	}
	
	//close
	public void close(){
		db.close();
	}
	
	//insert - task
	public void insert(String subject, String title, String contents){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("subject", subject);
		values.put("title", title);
		values.put("contents", contents);
		db.insert("task", null, values);
	}
	//insert - exam
	public void insert(String subject, String space, int hour, int minute){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("subject", subject);
		values.put("space", space);
		values.put("hour", hour);
		values.put("minute", minute);
		db.insert("exam", null, values);
	}
	
	public void update(){};
	
	//delete
	public void delete(String title){
		db = helper.getWritableDatabase();
		db.delete("task", "title=?", new String[]{title});
	}
	
	public Cursor select(){
		db = helper.getReadableDatabase();
		Cursor c = db.query("task", null, null, null, null, null, null);
		return c;
	}

}
