package com.hanproject.timetable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class TT_TimeTableAdapter extends BaseAdapter {

	private List<TT_TableItem> items = new ArrayList<TT_TableItem>();
	Context context;

	public TT_TimeTableAdapter(Context context) {
		this.context = context;
	}


	public void addItem(TT_TableItem aItem) {
		items.add(aItem);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TT_TableItemView aSubject;
		if (convertView == null)
			aSubject = new TT_TableItemView(context,items.get(position).getStarttime(),
					items.get(position).getDay());
		else
			aSubject = (TT_TableItemView) convertView;
		
		GridView.LayoutParams params = new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT, TT_TimeTableActivity.screenHeight/15);
		aSubject.setLayoutParams(params);
		
		aSubject.setText(items.get(position).getSubjectName());

		return aSubject;
	}
}