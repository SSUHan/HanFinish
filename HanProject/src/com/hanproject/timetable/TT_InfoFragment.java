package com.hanproject.timetable;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanproject.R;

public class TT_InfoFragment extends DialogFragment {

	TT_LP_Data data;
	DI_CP_Data data2;
	int mode2;
	String memo;
	TextView info;
	EditText Memo;
	String tableName;
	Button close;
	Button save;
	String changeClassroom = "";

	public TT_InfoFragment(TT_LP_Data data, String memo, String tableName,
			int mode2) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.memo = memo;
		this.tableName = tableName;
		this.mode2 = mode2;
	}

	public TT_InfoFragment(TT_LP_Data data, String memo, String tableName,
			int mode2, String classroom) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.memo = memo;
		this.tableName = tableName;
		this.mode2 = mode2;
		this.changeClassroom = classroom;
	}

	public TT_InfoFragment(DI_CP_Data data2) {
		// TODO Auto-generated constructor stub
		this.data2=data2;
		this.mode2=3;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstaceState) {
		View rootView = inflater.inflate(R.layout.tt_infofragment, container,
				false);
		if (mode2 != 3) {
			Log.d("mode2",""+mode2);
			String sstr = data.subjectName;

			if (mode2 != 0)
				if (mode2 == 1)
					sstr += "(휴강)";
				else
					sstr += "(강의실 변경)";
			
			getDialog().setTitle(sstr);

			info = (TextView) rootView.findViewById(R.id.info);
			Memo = (EditText) rootView.findViewById(R.id.Memo);
			Memo.setText(memo);
			close = (Button) rootView.findViewById(R.id.close);
			save = (Button) rootView.findViewById(R.id.save);

			close.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dismiss();
				}

			});

			save.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String sql = "UPDATE " + tableName + " set memo='"
							+ Memo.getText().toString() + "' ";
					sql += "where day=" + data.day + " and starttime='"
							+ data.startTime + "'";
					TT_TimeTableActivity.db.execSQL(sql);
					dismiss();
				}

			});

			String str = "";
			switch (data.day) {
			case 1:
				str += "Mon ";
				break;
			case 2:
				str += "Tue ";
				break;
			case 3:
				str += "Wed ";
				break;
			case 4:
				str += "Thu ";
				break;
			case 5:
				str += "Fri ";
				break;
			default:
			}
			str += data.startTime + " ~ " + data.finishTime + "\n";
			str += "Professor : " + data.professorName + "\n";
			if (mode2 != 2)
				str += "Classroom : " + data.classNumber + "\n";
			else
				str += "*Classroom : " + changeClassroom + "\n";

			info.setText(str);
		}
		else{
			getDialog().setTitle(data2.info+"(보강)");

			info = (TextView) rootView.findViewById(R.id.info);
			Memo = (EditText) rootView.findViewById(R.id.Memo);
			Memo.setVisibility(View.GONE);
			close = (Button) rootView.findViewById(R.id.close);
			save = (Button) rootView.findViewById(R.id.save);
			save.setVisibility(View.GONE);
			close.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dismiss();
				}

			});


			String str = "";
			switch (data2.day) {
			case 1:
				str += "Mon ";
				break;
			case 2:
				str += "Tue ";
				break;
			case 3:
				str += "Wed ";
				break;
			case 4:
				str += "Thu ";
				break;
			case 5:
				str += "Fri ";
				break;
			default:
			}
			str += data2.starttime + " ~ " + data2.finishtime + "\n";
			str += "Classroom : " + data2.classroom + "\n";

			info.setText(str);
		}
		return rootView;
	}

}
