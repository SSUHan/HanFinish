package com.hanproject.timetable;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hanproject.R;

public class DI_CP_PlusClassFragment extends Activity {
	
	SQLiteDatabase db = DI_ListViewActivity.database;
	String oristarttime;
	TextView info;
	EditText infoedit;
	EditText classroomedit;
	String dbName="timeplus.db";
	String tableName="base_add";
	Button close;
	Button change;
	Button delete;
	Button save;
	TextView time1;
	TextView time2;
	DI_CP_Data data;

	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.di_addclass);
		infoedit=(EditText)findViewById(R.id.info);
		classroomedit=(EditText)findViewById(R.id.classRoom);
		delete=(Button)findViewById(R.id.delete);
		change= (Button)findViewById(R.id.change);
		close=(Button)findViewById(R.id.close);
		save=(Button)findViewById(R.id.save);
		time1=(TextView)findViewById(R.id.starttime);
		time2=(TextView)findViewById(R.id.finishtime);
		
		open();
		
		OnTimeSetListener listener1 = new OnTimeSetListener() {		
        	@Override
        	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	
        		time1.setText(hourOfDay+":"+numberTwo(minute));
        	}
        	};
        StringTokenizer st=new StringTokenizer(time1.getText().toString(),":");
        String hour=st.nextToken();
        String minute=st.nextToken();
        final TimePickerDialog dialog1 = new TimePickerDialog(this,listener1,Integer.parseInt(hour)
        		, Integer.parseInt(minute), false);
        time1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				dialog1.show();
			}
        	
        });
        final OnTimeSetListener listener2 = new OnTimeSetListener() {		
        	@Override
        	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	
        		time2.setText(hourOfDay+":"+numberTwo(minute));
        	}
        	};
        st=new StringTokenizer(time2.getText().toString(),":");
        hour=st.nextToken();
        minute=st.nextToken();
        final TimePickerDialog dialog2 = new TimePickerDialog(this, listener2, Integer.parseInt(hour)
        		, Integer.parseInt(minute), false);
        time2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				dialog2.show();
			}
        	
        });
       
		
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createTable();
				data.info=infoedit.getText().toString();
				data.classroom=classroomedit.getText().toString();
				data.starttime=time1.getText().toString();
				data.finishtime=time2.getText().toString();
				insertData(data.info,data.starttime,data.finishtime,
						data.classroom,data.day,data.date);
				Log.d("saved",data.info);
				finish();
			}
			
		});
		
		
		close.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		
		change.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createTable();
				data.info=infoedit.getText().toString();
				data.classroom=classroomedit.getText().toString();
				data.starttime=time1.getText().toString();
				data.finishtime=time2.getText().toString();
				changeData(data.info,data.starttime,data.finishtime,
						data.classroom,data.day,data.date,oristarttime);
				finish();
			}
			
		});
		
		delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteData(data.date,data.day,oristarttime);
				finish();
			}
			
		});
		
		
	}
    void createTable()
    {
    	db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+ "(info text,starttime text,finishtime text,"
    			+ "classroom text,day integer,date text)");
    }
    void changeData(String info,String starttime,String finishtime,String classroom,
    		int day,String date,String oristarttime)
    {
    	String sql="update "+tableName+" set info='"+info+"' , classroom='"+classroom+"' , starttime='"+
    			starttime+"', finishtime='"+finishtime+"'";
    	sql+=" where day="+day+" and date='"+date+"'" +" and starttime='"+oristarttime+"'";
    	Log.d("sql",sql);
    	db.execSQL(sql);
    }
    void insertData(String info,String starttime,String finishtime,String classroom,
    		int day,String date)
    {
    	String sql="insert into "+tableName+" (info,starttime,finishtime,classroom,date,day) values ";
    	sql+="('"+info+"','"+starttime+"','"+finishtime+"','"
    	+classroom+"','"+date+"',"+day+")";
    	db.execSQL(sql);
    }
    void deleteData(String date,int day,String starttime)
    {
    	String sql="delete from "+tableName+" where starttime = "+"'"+starttime+"' and ";
    	sql+="day = "+day+" and date='"+date+"'";
    	db.execSQL(sql);
    }
    public void open() //0: newmode 1: changemode
    {
    	int mode=getIntent().getExtras().getInt("mode");
    	if(mode==0)
    	{
    		data=new DI_CP_Data("","","9:00","10:00",1,"");
    		data.day=getIntent().getExtras().getInt("day");
    		data.date=getIntent().getExtras().getString("date");
    		time1.setText("9:00");
        	time2.setText("10:00");
        	save.setVisibility(View.VISIBLE);
    	}
    	else
    	{
    		data=new DI_CP_Data(getIntent().getExtras().getString("info"),getIntent().getExtras().getString("classroom"),
    				getIntent().getExtras().getString("starttime"),getIntent().getExtras().getString("finishtime"),
    				getIntent().getExtras().getInt("day"),getIntent().getExtras().getString("date"));
    		time1.setText(getIntent().getExtras().getString("starttime"));
    		oristarttime = getIntent().getExtras().getString("starttime");
        	time2.setText(getIntent().getExtras().getString("finishtime"));
        	infoedit.setText(getIntent().getExtras().getString("info"));
        	classroomedit.setText(getIntent().getExtras().getString("classroom"));
        	change.setVisibility(View.VISIBLE);
			delete.setVisibility(View.VISIBLE);
    	}
    	
    }
    String numberTwo(int s)
    {
    	String st=String.valueOf(s);
    	if(s<10)
    	{
    		st="0"+st;
    	}
    	return st;
    }

}
