package com.hanproject.timetable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
 
public class TT_LP_Adapter extends BaseAdapter {
 
    
	private Context mContext;
 
    private List<TT_LP_ListViewData> mItems = new ArrayList<TT_LP_ListViewData>();
    
   
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
 
    public TT_LP_Adapter(Context context) {
        mContext = context;
    }
 
    public void addItem(TT_LP_ListViewData it) {
        mItems.add(it);
    }
    public void deleteItem(int i){
    	mItems.remove(i);
    }
   
 
    public void setListItems(List<TT_LP_ListViewData> lit) {
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
        TT_LP_PlusView itemView;
        
       
        itemView = new TT_LP_PlusView(mContext, mItems.get(position));
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