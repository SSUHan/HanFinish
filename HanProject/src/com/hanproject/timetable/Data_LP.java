package com.hanproject.timetable;

import java.util.ArrayList;

public class Data_LP 
{
	
	
	String subjectName="";
	String professorName="";
	int color=1;
	int day=1;
	String classNumber="";
	String startTime="";
	String finishTime="";
	ArrayList<Integer> extraDay=new ArrayList<Integer>();
	ArrayList<String> extraClassNumber=new ArrayList<String>();
	ArrayList<String> extraStartTime=new ArrayList<String>();
	ArrayList<String> extraFinishTime=new ArrayList<String>();
	Data_LP(String a,String b,int c,int d,String e,String f,String g)
	{
		subjectName=a;
		professorName=b;
		color=c;
		day=d;
		classNumber=e;
		startTime=f;
		finishTime=g;
	}
	Data_LP()
	{
		
	}
	

}
