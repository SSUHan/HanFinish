package com.hanproject.timetable;

public class TableItem {

	private String subjectName;
	private int starttime;
	private int day;
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public TableItem(String subjectName,int starttime,int day){
		this.subjectName = subjectName;
		this.starttime=starttime;
		this.day=day-1;
	}

	public int getStarttime() {
		return starttime;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}