package com.hanproject.taskexam;

public class taskItem {
	
	private String subject;
	private String title;
	private String contents;
	
	public taskItem(String s, String t, String c) {
		// TODO Auto-generated constructor stub
		subject = s;
		title=t;
		contents=c;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	

}
