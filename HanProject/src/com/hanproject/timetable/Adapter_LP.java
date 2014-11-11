package com.hanproject.timetable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
 
public class Adapter_LP extends BaseAdapter {
 
    
	private Context mContext;
 
    private List<ListViewData_LP> mItems = new ArrayList<ListViewData_LP>();
    
   
    EditText getClassNum(int location)
    {
    	return mItems.get(location).getClassNum();
    }
    
    void setClassNum(int location,EditText e1)
    {
    	mItems.get(location).setClassNum(e1);
    }
    
   
    void setDay(int location,int i)
	{
		mItems.get(location).setDay(i);
	}
	void setClassNumber(int location,String i)
	{
		mItems.get(location).setClassNumber(i);
	}
	void setStartTime(int location,String i)
	{
		mItems.get(location).setStartTime(i);
	}
	void setFinishTime(int location,String i)
	{
		mItems.get(location).setFinishTime(i);
	}
	int getDay(int location)
	{
		return mItems.get(location).getDay();
	}
	String getClassNumber(int location)
	{
		return mItems.get(location).getClassNumber();
	}
	String getStartTime(int location)
	{
		return mItems.get(location).getStartTime();
	}
	String getFinishTime(int location)
	{
		return mItems.get(location).getFinishTime();
	}
 
    public Adapter_LP(Context context) {
        mContext = context;
    }
 
    public void addItem(ListViewData_LP it) {
        mItems.add(it);
    }
    public void deleteItem(int i){
    	mItems.remove(i);
    }
   
 
    public void setListItems(List<ListViewData_LP> lit) {
        mItems = lit;
    }
 
    public int getCount() {
        return mItems.size();
    }
 
    public Object getItem(int position) {
        return mItems.get(position);
    }
 
    public boolean areAllItemsSelectable() {
        return false;
    }
 
    
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        PlusView_LP itemView;
        
       
        itemView = new PlusView_LP(mContext, mItems.get(position));
        itemView.inflate();
        
        itemView.setView(position);
        
        itemView.setData();
        
        return itemView;
    }

	public int getSize() 
	{
		// TODO Auto-generated method stub
		return mItems.size();
	}
 
}