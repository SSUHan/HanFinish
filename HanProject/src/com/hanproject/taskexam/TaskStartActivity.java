package com.hanproject.taskexam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanproject.R;

public class TaskStartActivity extends ActionBarActivity {

	Button addTask,deleteTask;
 	EditText editDelete;
	
	TextView textView01;
 	MyDBHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.te_taskstart);
		
		handler = MyDBHandler.open(getApplicationContext(),"task");
		addTask = (Button)findViewById(R.id.addTask);
		addTask.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),addTaskActivity.class);
				startActivity(intent);
			}
		});
		deleteTask = (Button)findViewById(R.id.deleteTask);
		deleteTask.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editDelete = (EditText)findViewById(R.id.editDelete);
				String title = editDelete.getText().toString();
				handler.delete(title);
				editDelete.setText("");
				showTask();
			}
		});
		showTask();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showTask();
	}
	
	private void showTask(){
		textView01 = (TextView)findViewById(R.id.textView01);
		String data="";
		Cursor c = handler.select();
		startManagingCursor(c);
		while(c.moveToNext()){
			int _id = c.getInt(c.getColumnIndex("_id"));
			String subject = c.getString(c.getColumnIndex("subject"));
			String title = c.getString(c.getColumnIndex("title"));
			String contents = c.getString(c.getColumnIndex("contents"));
			data += _id+" "+subject+" "+title+" "+contents+"\n";
		}
		textView01.setText(data);
	}
}
