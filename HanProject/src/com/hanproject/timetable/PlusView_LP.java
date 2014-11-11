package com.hanproject.timetable;

import java.util.StringTokenizer;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hanproject.R;

public class PlusView_LP extends LinearLayout {
	
	
	Button deleteBtn;
	ListViewData_LP aItem;
	Context context;
	int position;
	
	
	
 
    public PlusView_LP(Context mcontext,ListViewData_LP bItem) 
    {
    	super(mcontext);

    	
    	aItem=bItem;
    	context=mcontext;
    	
       
        
        
        
    	Log.d("Ww","d");
        
         
        
        
    }
    public void inflate()
    {
    	Log.d("Ww","e");
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.plus_lp, this, true);
    }




	public void setView(int pos) {
		position=pos;
		
		aItem.dayBtn[0]=(Button)findViewById(R.id.dayplus1);
        aItem.dayBtn[0].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[0].setBackgroundColor(Color.RED);
				aItem.setDay(1);
			}
        	
        });
        aItem.dayBtn[1]=(Button)findViewById(R.id.dayplus2);
        aItem.dayBtn[1].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[1].setBackgroundColor(Color.RED);
				aItem.setDay(2);
			}
        	
        });
        aItem.dayBtn[2]=(Button)findViewById(R.id.dayplus3);
        aItem.dayBtn[2].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[2].setBackgroundColor(Color.RED);
				aItem.setDay(3);
			}
        	
        });
        aItem.dayBtn[3]=(Button)findViewById(R.id.dayplus4);
        aItem.dayBtn[3].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[3].setBackgroundColor(Color.RED);
				aItem.setDay(4);
			}
        	
        });
        aItem.dayBtn[4]=(Button)findViewById(R.id.dayplus5);
        aItem.dayBtn[4].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[4].setBackgroundColor(Color.RED);
				aItem.setDay(5);
			}
        	
        });
        aItem.dayBtn[5]=(Button)findViewById(R.id.dayplus6);
        aItem.dayBtn[5].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[5].setBackgroundColor(Color.RED);
				aItem.setDay(6);
			}
        	
        });
        aItem.dayBtn[6]=(Button)findViewById(R.id.dayplus7);
        aItem.dayBtn[6].setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				for(int i=0;i<7;i++)
					aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
				aItem.dayBtn[6].setBackgroundColor(Color.RED);
				aItem.setDay(7);
			}
        	
        });
        aItem.start=(TextView)findViewById(R.id.timeplus1);
        aItem.finish=(TextView)findViewById(R.id.timeplus2);
        aItem.classNum=(EditText)findViewById(R.id.plusClassNum);
        aItem.classNum.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				aItem.setClassNumber(aItem.classNum.getText().toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        final OnTimeSetListener listener1 = new OnTimeSetListener() {		
        	@Override
        	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        		aItem.start.setText(hourOfDay+":"+numberTwo(minute));
        		aItem.setStartTime(hourOfDay+":"+numberTwo(minute));
        	}
        	};
        	StringTokenizer st1=new StringTokenizer(aItem.startTime,":");
            final TimePickerDialog dialog1 = new TimePickerDialog(context,listener1,Integer.parseInt(st1.nextToken()),
            		Integer.parseInt(st1.nextToken()) , false);
        aItem.start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog1.show();
				aItem.setStartTime(
						aItem.start.getText().toString());
			}
        	
        });
        final OnTimeSetListener listener2 = new OnTimeSetListener() {		
        	@Override
        	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        		aItem.finish.setText(hourOfDay+":"+numberTwo(minute));
        		aItem.setFinishTime(hourOfDay+":"+numberTwo(minute));
        	}
        	};
        	StringTokenizer st2=new StringTokenizer(aItem.finishTime,":");
            final TimePickerDialog dialog2 = new TimePickerDialog(context,listener2,Integer.parseInt(st2.nextToken()),
            		Integer.parseInt(st2.nextToken()) , false);
        	aItem.finish.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog2.show();
				
			}
        	
        });
        	
        aItem.deleteBtn=(Button)findViewById(R.id.deleteBtn);
        aItem.deleteBtn.setOnClickListener(new OnClickListener(){
    			@Override
    			public void onClick(View v) {
    				
    				MainActivity_LP.adapter.deleteItem(position);
    
    						
    				MainActivity_LP.adapter.notifyDataSetChanged();
    				
    				MainActivity_LP.deleteLisetViewHeight(MainActivity_LP.plus,
    						MainActivity_LP.adapter.getCount());
    	  	        
    	  	        
    	  	       
    			}
            	
            });
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




	public void setData() 
	{
		// TODO Auto-generated method stub
		for(int i=0;i<7;i++)
        {
        	aItem.dayBtn[i].setBackgroundColor(Color.GREEN);
        	if(aItem.getDay()==(i+1))
        		aItem.dayBtn[i].setBackgroundColor(Color.RED);
        }
        aItem.classNum.setText(aItem.getClassNumber());
        aItem.start.setText(aItem.getStartTime());
        aItem.finish.setText(aItem.getFinishTime());
	}
 
}