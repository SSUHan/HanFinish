package com.hanproject.timetable;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TT_LP_ListViewData 
{
	


	public TextView getStart() {
		return start;
	}


	public void setStart(TextView start) {
		this.start = start;
	}


	public TextView getFinish() {
		return finish;
	}


	public void setFinish(TextView finish) {
		this.finish = finish;
	}


	public EditText getClassNum() {
		return classNum;
	}


	public void setClassNum(EditText classNum) {
		this.classNum = classNum;
	}


	public Button getDeleteBtn() {
		return deleteBtn;
	}


	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}
	int day;
	String classNumber;
	String startTime;
	String finishTime;
	Button[] dayBtn=new Button[7];
	TextView start;
	TextView finish;
	EditText classNum;
	Button deleteBtn;
	

	TT_LP_ListViewData()
	{
		day=1;
		classNumber="";
		startTime="9:30";
		finishTime="10:30";
	}
	


	public void setDay(int i)
	{
		day=i;
	}
	public void setClassNumber(String i)
	{
		classNumber=i;
	}
	public void setStartTime(String i)
	{
		startTime=i;
	}
	public void setFinishTime(String i)
	{
		finishTime=i;
	}
	
	public int getDay()
	{
		return day;
	}
	public String getClassNumber()
	{
		return classNumber;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public String getFinishTime()
	{
		return finishTime;
	}
	
}
