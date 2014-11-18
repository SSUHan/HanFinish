package com.hanproject.timetable;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class CM_CalendarMonthView extends GridView {

	/**
	 * Listener for data selection
	 */
	private CM_OnDataSelectionListener selectionListener;
	
	CM_CalendarMonthAdapter adapter;
	
	public CM_CalendarMonthView(Context context) {
		super(context);

		init();
	}

	public CM_CalendarMonthView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	
	/**
	 * set initial properties
	 */
	private void init() {
		setBackgroundColor(Color.GRAY);
        setVerticalSpacing(1);
        setHorizontalSpacing(1);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        setNumColumns(7);
        
        // set OnItemClickListener for processing OnDataSelectionListener
        setOnItemClickListener(new OnItemClickAdapter());
	}

	/**
	 * set DataAdapter
	 * 
	 * @param adapter
	 */
	public void setAdapter(BaseAdapter adapter) {
		super.setAdapter(adapter);
		
		this.adapter = (CM_CalendarMonthAdapter) adapter;
	}

	/**
	 * get DataAdapter
	 * 
	 * @return
	 */
	public BaseAdapter getAdapter() {
		return (BaseAdapter)super.getAdapter();
	}
	
	/**
	 * set OnDataSelectionListener
	 * 
	 * @param listener
	 */
	public void setOnDataSelectionListener(CM_OnDataSelectionListener listener) {
		this.selectionListener = listener;
	}

	/**
	 * get OnDataSelectionListener
	 * 
	 * @return
	 */
	public CM_OnDataSelectionListener getOnDataSelectionListener() {
		return selectionListener;
	}
	
	class OnItemClickAdapter implements OnItemClickListener {
		
		public OnItemClickAdapter() {
			
		}

		public void onItemClick(AdapterView parent, View v, int position, long id) {
			 
			if (adapter != null) {
				adapter.setSelectedPosition(position);
				adapter.notifyDataSetInvalidated();
			}
			
			// call the OnDataSelectionListener method
			if (selectionListener != null) {
				selectionListener.onDataSelected(parent, v, position, id);
			}
			
		}
		
	}
}