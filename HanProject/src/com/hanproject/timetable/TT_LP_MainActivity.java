package com.hanproject.timetable;


import java.util.StringTokenizer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hanproject.R;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public class TT_LP_MainActivity extends Activity {
	
	SQLiteDatabase db;
	static Context context;
	static ListView plus;
	static TT_LP_Adapter adapter;
	static int size=0;
	Button plusBtn;
	Button changeBtn;
	Button deleteBtn;
	Button mainColor;
    Button color[]=new Button[6];
    Button saveBtn;
    Button closeBtn;
    Button[] day=new Button[7];
    TextView time1;
    TextView time2;
    EditText subName;
    EditText proName;
    EditText classNum;
    String oristarttime;
    int oriday;
    static TT_LP_Data lastData=new TT_LP_Data();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lp_activity_main);
        
        context=getBaseContext();
        adapter=new TT_LP_Adapter(this);
        plus=(ListView)findViewById(R.id.plus);
        plus.setAdapter(adapter);
        plusBtn=(Button)findViewById(R.id.plusBtn);
        deleteBtn=(Button)findViewById(R.id.deleteBtn);
        changeBtn=(Button)findViewById(R.id.changeBtn);
        time1=(TextView)findViewById(R.id.time1);
        time2=(TextView)findViewById(R.id.time2);
        saveBtn=(Button)findViewById(R.id.saveBtn);
        closeBtn=(Button)findViewById(R.id.closeBtn);
        subName=(EditText)findViewById(R.id.subName);
        proName=(EditText)findViewById(R.id.proName);
        classNum=(EditText)findViewById(R.id.classNum);
        
        
        
        mainColor=(Button)findViewById(R.id.mainColor);
        color[0]=(Button)findViewById(R.id.color1);
        color[1]=(Button)findViewById(R.id.color2);
        color[2]=(Button)findViewById(R.id.color3);
        color[3]=(Button)findViewById(R.id.color4);
        color[4]=(Button)findViewById(R.id.color5);
        color[5]=(Button)findViewById(R.id.color6);
        color[0].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[0].getBackground());
				lastData.color=1;
			}
        	
        });
        color[1].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[1].getBackground());
				lastData.color=2;
				}
        	
        });
        color[2].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[2].getBackground());
				lastData.color=3;
			}
        	
        });
        color[3].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[3].getBackground());
				lastData.color=4;
			}
        	
        });
        color[4].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[4].getBackground());
				lastData.color=5;
			}
        	
        });
        color[5].setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				mainColor.setBackground(color[5].getBackground());
				lastData.color=6;
			}
        	
        });
       
        
        day[0]=(Button)findViewById(R.id.day1);
        day[0].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[0].setBackgroundColor(Color.RED);
				lastData.day=1;
			}
        	
        });
        day[1]=(Button)findViewById(R.id.day2);
        day[1].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[1].setBackgroundColor(Color.RED);
				lastData.day=2;
			}
        	
        });
        day[2]=(Button)findViewById(R.id.day3);
        day[2].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[2].setBackgroundColor(Color.RED);
				lastData.day=3;
			}
        	
        });
        day[3]=(Button)findViewById(R.id.day4);
        day[3].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[3].setBackgroundColor(Color.RED);
				lastData.day=4;
			}
        	
        });
        day[4]=(Button)findViewById(R.id.day5);
        day[4].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[4].setBackgroundColor(Color.RED);
				lastData.day=5;
			}
        	
        });
        day[5]=(Button)findViewById(R.id.day6);
        day[5].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					day[i].setBackgroundColor(Color.GREEN);
				day[5].setBackgroundColor(Color.RED);
				lastData.day=6;
			}
        	
        });
        day[6]=(Button)findViewById(R.id.day7);
        day[6].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
    			day[i].setBackgroundColor(Color.GREEN);
				day[6].setBackgroundColor(Color.RED);
				lastData.day=7;
			}
        	
        });
        
        
       open(getIntent().getExtras().getInt("mode"));
        
        
        
        
        final OnTimeSetListener listener1 = new OnTimeSetListener() {		
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
        
        plusBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				adapter.addItem(new TT_LP_ListViewData());
				setLisetViewHeight(plus,adapter.getSize());
			}
        	
        });
        
        saveBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				
				lastData.subjectName= subName.getText().toString();
				lastData.professorName= proName.getText().toString();
				lastData.classNumber= classNum.getText().toString();
				lastData.startTime= time1.getText().toString();
				lastData.finishTime= time2.getText().toString();
				
				for(int i=0;i<adapter.getSize();i++)
				{
					lastData.extraDay.add(adapter.getDay(i));
					lastData.extraClassNumber.add(adapter.getClassNumber(i));
					lastData.extraStartTime.add(adapter.getStartTime(i));
					lastData.extraFinishTime.add(adapter.getFinishTime(i));
				}
				
				Toast.makeText(getApplicationContext(),
						"subject : "+lastData.subjectName+"\n" 
				        +"professor : "+lastData.professorName+"\n"
						+"color : "+lastData.color+"\n"
						+"day : "+lastData.day+"\n"
						+"classroom : "+lastData.classNumber+"\n"
						+"time : "+lastData.startTime+" ~ "+lastData.finishTime
						, Toast.LENGTH_LONG).show();
				
				for(int i=0;i<adapter.getSize();i++)
				{
					Toast.makeText(getApplicationContext(),
							"day"+i+" : "+lastData.extraDay.get(i)+"\n"
							+"classroom"+i+" : "+lastData.extraClassNumber.get(i)+"\n"
							+"time"+i+" : "+lastData.extraStartTime.get(i)
							+" ~ "+lastData.extraFinishTime.get(i)
							, Toast.LENGTH_LONG).show();
				}
				
				createDataBase("timeplus.db");
				createTable("base");
				insertData("base",lastData.subjectName,lastData.professorName,lastData.classNumber,
						lastData.startTime,lastData.finishTime,lastData.day,lastData.color);
				for(int i=0;i<adapter.getSize();i++)
				{
					insertData("base",lastData.subjectName,lastData.professorName,lastData.extraClassNumber.get(i),
							lastData.extraStartTime.get(i),lastData.extraFinishTime.get(i),lastData.extraDay.get(i),lastData.color);
				}
				
				lastData=new TT_LP_Data();
				
				finish();
			}
        	
        });
        
        changeBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				
				lastData.subjectName= subName.getText().toString();
				lastData.professorName= proName.getText().toString();
				lastData.classNumber= classNum.getText().toString();
				lastData.startTime= time1.getText().toString();
				lastData.finishTime= time2.getText().toString();
				
				createDataBase("timeplus.db");
				createTable("base");
				changeData("base",lastData.subjectName,lastData.professorName,lastData.classNumber,
						lastData.startTime,lastData.finishTime,lastData.day,lastData.color,oristarttime,oriday);
				
				lastData=new TT_LP_Data();
				
				finish();
			}
        	
        });
        
        deleteBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createDataBase("timeplus.db");
				createTable("base");
				deleteData("base",getIntent().getExtras().getInt("day"),
						getIntent().getExtras().getString("starttime"));
				
				lastData=new TT_LP_Data();
				
				finish();
			}
        	
        });
        
        closeBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        	
        
        
        
    }
    void createDataBase(String name)
    {
    	db=openOrCreateDatabase(name,MODE_WORLD_WRITEABLE, null);
    }
    void createTable(String name)
    {
    	db.execSQL("CREATE TABLE IF NOT EXISTS "+name+ "(subject text,professor text,classroom text,"
    			+ "starttime text,finishtime text,day integer,color integer,memo text)");
    }
    void changeData(String name,String subject,String professor,String classroom,String starttime,String finishtime,
    		int day,int color,String oristarttime,int oriday)
    {
    	String sql="update "+name+" set subject='"+subject+"' , professor='"+professor+"' , classroom='"+classroom+"' , starttime='"+
    starttime+"', finishtime='"+finishtime+"' , day="+day+" , color="+color;
    	sql+=" where day="+oriday+" and starttime='"+oristarttime+"'";
    	Log.d("sql",sql);
    	db.execSQL(sql);
    }
    void insertData(String name,String subject,String professor,String classroom,String starttime,String finishtime,
    		int day,int color)
    {
    	String sql="insert into "+name+" (subject,professor,classroom,starttime,finishtime,day,color) values ";
    	sql+="('"+subject+"','"+professor+"','"+classroom+"','"
    	+starttime+"','"+finishtime+"',"+day+","+color+")";
    	db.execSQL(sql);
    }
    void deleteData(String name,int day,String starttime)
    {
    	String sql="delete from "+name+" where starttime = "+"'"+starttime+"' and ";
    	sql+="day = "+day;
    	db.execSQL(sql);
    }
    public void open(int mode) //0: newmode 1: changemode
    {
    	if(mode==0)
    	{
    		Intent inte=getIntent();
            time1.setText(inte.getExtras().getString("starttime"));
        	time2.setText(inte.getExtras().getString("finishtime"));
        	for(int i=0;i<7;i++)
    			day[i].setBackgroundColor(Color.GREEN);
    		day[inte.getExtras().getInt("day")-1].setBackgroundColor(Color.RED);
    		lastData.day=inte.getExtras().getInt("day");
    		lastData.color=1;
    		plusBtn.setVisibility(View.VISIBLE);
    		saveBtn.setVisibility(View.VISIBLE);
    	}
    	else
    	{
    		Intent inte=getIntent();
    		
    		time1.setText(inte.getExtras().getString("starttime"));
    		oristarttime=inte.getExtras().getString("starttime");
        	time2.setText(inte.getExtras().getString("finishtime"));
        	subName.setText(inte.getExtras().getString("subject"));
        	proName.setText(inte.getExtras().getString("professor"));
        	classNum.setText(inte.getExtras().getString("classroom"));
        	for(int i=0;i<7;i++)
    			day[i].setBackgroundColor(Color.GREEN);
    		day[inte.getExtras().getInt("day")-1].setBackgroundColor(Color.RED);
    		lastData.day=inte.getExtras().getInt("day");
    		oriday=lastData.day;
    		Log.d("fdkj",""+inte.getExtras().getInt("color"));
    		mainColor.setBackground(color[inte.getExtras().getInt("color")-1].getBackground());
			lastData.color=inte.getExtras().getInt("color");
    		deleteBtn.setVisibility(View.VISIBLE);
    		changeBtn.setVisibility(View.VISIBLE);
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
    static void setLisetViewHeight(final ListView listView,int count){
    	  ListAdapter listAdapter = listView.getAdapter();  
    	  if (listAdapter == null) { 
    	            return; 
    	        } 
    	  int totalHeight = 0; 
    	  if(count == 0){
    	   count = listAdapter.getCount();
    	  }      
    	  	for (int i = count-1; i < listAdapter.getCount(); i++) { 
    	         if(i>=count)
    	          break;
    	         View listItem = listAdapter.getView(i, 
    	           		null, listView); 
    	            listItem.measure(0, 0);
    	            size=listItem.getMeasuredHeight();
    	        	for (int j = 0; j < listAdapter.getCount(); j++)
    	        		totalHeight += size;
    	  	}
    	        ViewGroup.LayoutParams params = listView.getLayoutParams(); 
    	        params.height = totalHeight + (listView.getDividerHeight() * (count - 1)); 
    	        listView.setLayoutParams(params); 
    }
    static void deleteLisetViewHeight(final ListView listView,int count){
  	  ListAdapter listAdapter = listView.getAdapter();  
  	  if (listAdapter == null) { 
  	            return; 
  	        } 
  	  int totalHeight = 0; 
  	  if(count == 0){
  	   count = listAdapter.getCount();
  	  }      
  	  	for (int i = 0; i < listAdapter.getCount(); i++) { 
  	         if(i>=count)
  	          break;
  	         View listItem = listAdapter.getView(i, 
  	           		null, listView); 
  	            listItem.measure(0, 0);
  	            size=listItem.getMeasuredHeight();
  	            totalHeight += size;
  	  	}
  	        ViewGroup.LayoutParams params = listView.getLayoutParams(); 
  	        params.height = totalHeight + (listView.getDividerHeight() * (count - 1)); 
  	        listView.setLayoutParams(params); 
  }
}
