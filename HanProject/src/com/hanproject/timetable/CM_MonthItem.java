package com.hanproject.timetable;

public class CM_MonthItem {

	private int dayValue;
	private int changing;

	public CM_MonthItem() {

	}

	public CM_MonthItem(int day) {
		dayValue = day;
	}

	public int getDay() {
		return dayValue;
	}

	public int getChanging() {
		return changing;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}

	public void setChanging(int changing) {
		this.changing = changing;
	}

}
