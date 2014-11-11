package com.hanproject.timetable;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanproject.R;

public class DFragment extends DialogFragment{
	
	Data_LP data;
	String memo;
	TextView info;
	EditText Memo;
	String tableName;
	Button close;
	Button save;
	public DFragment(Data_LP data,String memo,String tableName) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.memo=memo;
		this.tableName=tableName;
	}

	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstaceState)
	{
		View rootView = inflater.inflate(R.layout.dialogfragment, 
				container,false);
		getDialog().setTitle(data.subjectName);
		info= (TextView)rootView.findViewById(R.id.info);
		Memo=(EditText)rootView.findViewById(R.id.Memo);
		Memo.setText(memo);
		close=(Button)rootView.findViewById(R.id.close);
		save=(Button)rootView.findViewById(R.id.save);
		
		
		close.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
			
		});
		
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sql="UPDATE "+tableName+" set memo='"+Memo.getText().toString()+"' ";
				sql+="where day="+data.day+" and starttime='"+data.startTime+"'";
				TimeTableActivity.db.execSQL(sql);
				dismiss();
			}
			
		});
		
		String str="";
		switch(data.day){
		case 1: str+="Mon ";break;
		case 2: str+="Tue ";break;
		case 3: str+="Wed ";break;
		case 4: str+="Thu ";break;
		case 5: str+="Fri ";break;
		default:
		}
		str+=data.startTime+" ~ "+data.finishTime+"\n";
		str+="Professor : "+data.professorName+"\n";
		str+="Classroom : "+data.classNumber+"\n";
		
		info.setText(str);
		
		return rootView;
	}
	

}
