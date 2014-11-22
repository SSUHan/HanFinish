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

	// ���ڿ��� ������ ArrayList
	private ArrayList<taskItem> m_list;

	// ������
	public taskAdapter() {
		// TODO Auto-generated constructor stub
		m_list = new ArrayList<taskItem>();
	}

	// ���� �����ۼ��� ����
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

	// ������ position�� id�� ����
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// ��µ� ������ ����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int pos = position;
		final Context context = parent.getContext();

		// ����Ʈ�� ������鼭 ���� ȭ�鿡 ������ �ʴ� �������� convertView �� null�� ���·� ����
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater
					.inflate(R.layout.te_task_item, parent, false);

			// TextView�� ���� position�� ���ڿ� �߰�
			TextView subject = (TextView) convertView.findViewById(R.id.tasksubject);
			subject.setText(m_list.get(pos).getSubject());
			
			TextView title = (TextView)convertView.findViewById(R.id.tasktitle);
			title.setText(m_list.get(pos).getTitle());

			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "����Ʈ Ŭ��: " +String.valueOf( m_list.get(pos)),
							Toast.LENGTH_SHORT).show();
				}
			});

			convertView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "����Ʈ ���Ŭ��: " +String.valueOf( m_list.get(pos)),
							Toast.LENGTH_SHORT).show();
					return true;
				}
			});
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
