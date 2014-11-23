package com.hanproject.taskexam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hanproject.R;

public class taskAdapter extends BaseAdapter {

	// 문자열을 보관할 ArrayList
	private ArrayList<taskItem> m_list;

	public taskAdapter() {
		m_list = new ArrayList<taskItem>();
	}
	
	// 현재 아이템수를 리턴
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

	// 아이템 position의 id값 리턴
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 출력될 아이템 관리
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int pos = position;
		final Context context = parent.getContext();

		// 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 convertView 가 null인 상태로 들어옴
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater
					.inflate(R.layout.te_task_item, parent, false);

			// TextView에 현재 position의 문자열 추가
			TextView subject = (TextView) convertView.findViewById(R.id.tasksubject);
			subject.setText(m_list.get(pos).getSubject());
			
			TextView title = (TextView)convertView.findViewById(R.id.tasktitle);
			title.setText(m_list.get(pos).getTitle());

		}
		return convertView;
	}

	public void add(String subject, String title, String contents) {
		taskItem ti = new taskItem(subject,title,contents);
		m_list.add(ti);
	}
	
	public void add(taskItem ti){
		m_list.add(ti);
	}

	public void remove(int position) {
		m_list.remove(position);
	}

}