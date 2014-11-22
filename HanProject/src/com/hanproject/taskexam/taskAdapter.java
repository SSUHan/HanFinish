package com.hanproject.taskexam;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class taskAdapter extends BaseAdapter {
	
	//문자열을 보관할 ArrayList
	private ArrayList<String> m_list;

	
	//생성자
	public taskAdapter() {
		// TODO Auto-generated constructor stub
		m_list=new ArrayList<String>();
	}
	
	//현재 아이템수를 리턴
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_list.size();
	}

	
	// 현재 아이템의 오브젝트를 리턴, object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_list.get(position);
	}

	
	
	//
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
