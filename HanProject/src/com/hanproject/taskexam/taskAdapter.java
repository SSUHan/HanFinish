package com.hanproject.taskexam;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class taskAdapter extends BaseAdapter {
	
	//���ڿ��� ������ ArrayList
	private ArrayList<String> m_list;

	
	//������
	public taskAdapter() {
		// TODO Auto-generated constructor stub
		m_list=new ArrayList<String>();
	}
	
	//���� �����ۼ��� ����
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_list.size();
	}

	
	// ���� �������� ������Ʈ�� ����, object�� ��Ȳ�� �°� �����ϰų� ���Ϲ��� ������Ʈ�� ĳ�����ؼ� ���
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
