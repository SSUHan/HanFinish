package com.hanproject.taskexam;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hanproject.R;

public class addTaskActivity extends ActionBarActivity {
	
	MyDBHandler handler;
	
	EditText editSubject;
	EditText editTitle;
	EditText editContents;
	
	Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.te_addtask);
		
		
		save = (Button)findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//데이터베이스 열기
				handler = MyDBHandler.open(getApplicationContext(),"task");
				
				editSubject = (EditText)findViewById(R.id.editSubject);
				editTitle = (EditText)findViewById(R.id.editTitle);
				editContents = (EditText)findViewById(R.id.editContents);
				
				String subject = editSubject.getText().toString();
				String title = editTitle.getText().toString();
				String contents = editContents.getText().toString();
				
				handler.insert(subject, title, contents);
				handler.close();
				finish();
			}
		});
		
		
	}

}
