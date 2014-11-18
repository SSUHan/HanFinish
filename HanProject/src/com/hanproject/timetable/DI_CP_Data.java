package com.hanproject.timetable;

public class DI_CP_Data 
{
	String info;
	String classroom;
	String starttime;
	String finishtime;
	int day;
	String date;
	DI_CP_Data(String info,String classroom,
			String starttime,String finishtime,int day,String date)
	{
		this.info=info;
		this.classroom=classroom;
		this.starttime=starttime;
		this.finishtime=finishtime;
		this.day=day;
		this.date=date;
	}
}
