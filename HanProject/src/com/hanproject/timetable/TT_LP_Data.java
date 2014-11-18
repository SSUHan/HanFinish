package com.hanproject.timetable;

import java.util.ArrayList;

public class TT_LP_Data 
{
	
	
	String subjectName="";
	String professorName="";
	int mode2=0;
	int color=1;
	int day=1;
	String classNumber="";
	String startTime="";
	String finishTime="";
	String changeclassroom="";
	ArrayList<Integer> extraDay=new ArrayList<Integer>();
	ArrayList<String> extraClassNumber=new ArrayList<String>();
	ArrayList<String> extraStartTime=new ArrayList<String>();
	ArrayList<String> extraFinishTime=new ArrayList<String>();
	TT_LP_Data(String a,String b,int c,int d,String e,String f,String g)
	{
		subjectName=a;
		professorName=b;
		color=c;
		day=d;
		classNumber=e;
		startTime=f;
		finishTime=g;
	}
	TT_LP_Data()
	{
		
	}
	

}
