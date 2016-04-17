package com.example.mywaytest3;


import java.util.ArrayList;

import com.example.mywaytest3.model.WeeklyItem;
import com.example.mywaytest3.model.WeeklyManager;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeeklyFragment extends Fragment {


	TextView tvCell[][] = new TextView[7][];
	static View rootView = null;
	WeeklyManager weeklyManager = WeeklyManager.getInstance();


	public WeeklyFragment(){}

	@SuppressLint("ResourceAsColor")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity.actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f44336")));
		MainActivity.actionBar.show();
		if(rootView == null)
		{
			rootView = inflater.inflate(R.layout.fragment_weekly, container, false);

			for(int i = 0; i <7; i++)
			{
				tvCell[i] = new TextView[24];
			}

			tvCell[0][0]= (TextView) rootView.findViewById(R.id.Mon00);
			tvCell[0][1]= (TextView) rootView.findViewById(R.id.Mon01);
			tvCell[0][2]= (TextView) rootView.findViewById(R.id.Mon02);
			tvCell[0][3]= (TextView) rootView.findViewById(R.id.Mon03);
			tvCell[0][4]= (TextView) rootView.findViewById(R.id.Mon04);
			tvCell[0][5]= (TextView) rootView.findViewById(R.id.Mon05);
			tvCell[0][6]= (TextView) rootView.findViewById(R.id.Mon06);
			tvCell[0][7]= (TextView) rootView.findViewById(R.id.Mon07);
			tvCell[0][8]= (TextView) rootView.findViewById(R.id.Mon08);
			tvCell[0][9]= (TextView) rootView.findViewById(R.id.Mon09);
			tvCell[0][10]= (TextView) rootView.findViewById(R.id.Mon10);
			tvCell[0][11]= (TextView) rootView.findViewById(R.id.Mon11);
			tvCell[0][12]= (TextView) rootView.findViewById(R.id.Mon12);
			tvCell[0][13]= (TextView) rootView.findViewById(R.id.Mon13);
			tvCell[0][14]= (TextView) rootView.findViewById(R.id.Mon14);
			tvCell[0][15]= (TextView) rootView.findViewById(R.id.Mon15);
			tvCell[0][16]= (TextView) rootView.findViewById(R.id.Mon16);
			tvCell[0][17]= (TextView) rootView.findViewById(R.id.Mon17);
			tvCell[0][18]= (TextView) rootView.findViewById(R.id.Mon18);
			tvCell[0][19]= (TextView) rootView.findViewById(R.id.Mon19);
			tvCell[0][20]= (TextView) rootView.findViewById(R.id.Mon20);
			tvCell[0][21]= (TextView) rootView.findViewById(R.id.Mon21);
			tvCell[0][22]= (TextView) rootView.findViewById(R.id.Mon22);
			tvCell[0][23]= (TextView) rootView.findViewById(R.id.Mon23);

			tvCell[1][0]= (TextView) rootView.findViewById(R.id.Tue00);
			tvCell[1][1]= (TextView) rootView.findViewById(R.id.Tue01);
			tvCell[1][2]= (TextView) rootView.findViewById(R.id.Tue02);
			tvCell[1][3]= (TextView) rootView.findViewById(R.id.Tue03);
			tvCell[1][4]= (TextView) rootView.findViewById(R.id.Tue04);
			tvCell[1][5]= (TextView) rootView.findViewById(R.id.Tue05);
			tvCell[1][6]= (TextView) rootView.findViewById(R.id.Tue06);
			tvCell[1][7]= (TextView) rootView.findViewById(R.id.Tue07);
			tvCell[1][8]= (TextView) rootView.findViewById(R.id.Tue08);
			tvCell[1][9]= (TextView) rootView.findViewById(R.id.Tue09);
			tvCell[1][10]= (TextView) rootView.findViewById(R.id.Tue10);
			tvCell[1][11]= (TextView) rootView.findViewById(R.id.Tue11);
			tvCell[1][12]= (TextView) rootView.findViewById(R.id.Tue12);
			tvCell[1][13]= (TextView) rootView.findViewById(R.id.Tue13);
			tvCell[1][14]= (TextView) rootView.findViewById(R.id.Tue14);
			tvCell[1][15]= (TextView) rootView.findViewById(R.id.Tue15);
			tvCell[1][16]= (TextView) rootView.findViewById(R.id.Tue16);
			tvCell[1][17]= (TextView) rootView.findViewById(R.id.Tue17);
			tvCell[1][18]= (TextView) rootView.findViewById(R.id.Tue18);
			tvCell[1][19]= (TextView) rootView.findViewById(R.id.Tue19);
			tvCell[1][20]= (TextView) rootView.findViewById(R.id.Tue20);
			tvCell[1][21]= (TextView) rootView.findViewById(R.id.Tue21);
			tvCell[1][22]= (TextView) rootView.findViewById(R.id.Tue22);
			tvCell[1][23]= (TextView) rootView.findViewById(R.id.Tue23);

			tvCell[2][0]= (TextView) rootView.findViewById(R.id.Wed00);
			tvCell[2][1]= (TextView) rootView.findViewById(R.id.Wed01);
			tvCell[2][2]= (TextView) rootView.findViewById(R.id.Wed02);
			tvCell[2][3]= (TextView) rootView.findViewById(R.id.Wed03);
			tvCell[2][4]= (TextView) rootView.findViewById(R.id.Wed04);
			tvCell[2][5]= (TextView) rootView.findViewById(R.id.Wed05);
			tvCell[2][6]= (TextView) rootView.findViewById(R.id.Wed06);
			tvCell[2][7]= (TextView) rootView.findViewById(R.id.Wed07);
			tvCell[2][8]= (TextView) rootView.findViewById(R.id.Wed08);
			tvCell[2][9]= (TextView) rootView.findViewById(R.id.Wed09);
			tvCell[2][10]= (TextView) rootView.findViewById(R.id.Wed10);
			tvCell[2][11]= (TextView) rootView.findViewById(R.id.Wed11);
			tvCell[2][12]= (TextView) rootView.findViewById(R.id.Wed12);
			tvCell[2][13]= (TextView) rootView.findViewById(R.id.Wed13);
			tvCell[2][14]= (TextView) rootView.findViewById(R.id.Wed14);
			tvCell[2][15]= (TextView) rootView.findViewById(R.id.Wed15);
			tvCell[2][16]= (TextView) rootView.findViewById(R.id.Wed16);
			tvCell[2][17]= (TextView) rootView.findViewById(R.id.Wed17);
			tvCell[2][18]= (TextView) rootView.findViewById(R.id.Wed18);
			tvCell[2][19]= (TextView) rootView.findViewById(R.id.Wed19);
			tvCell[2][20]= (TextView) rootView.findViewById(R.id.Wed20);
			tvCell[2][21]= (TextView) rootView.findViewById(R.id.Wed21);
			tvCell[2][22]= (TextView) rootView.findViewById(R.id.Wed22);
			tvCell[2][23]= (TextView) rootView.findViewById(R.id.Wed23);

			tvCell[3][0]= (TextView) rootView.findViewById(R.id.Thu00);
			tvCell[3][1]= (TextView) rootView.findViewById(R.id.Thu01);
			tvCell[3][2]= (TextView) rootView.findViewById(R.id.Thu02);
			tvCell[3][3]= (TextView) rootView.findViewById(R.id.Thu03);
			tvCell[3][4]= (TextView) rootView.findViewById(R.id.Thu04);
			tvCell[3][5]= (TextView) rootView.findViewById(R.id.Thu05);
			tvCell[3][6]= (TextView) rootView.findViewById(R.id.Thu06);
			tvCell[3][7]= (TextView) rootView.findViewById(R.id.Thu07);
			tvCell[3][8]= (TextView) rootView.findViewById(R.id.Thu08);
			tvCell[3][9]= (TextView) rootView.findViewById(R.id.Thu09);
			tvCell[3][10]= (TextView) rootView.findViewById(R.id.Thu10);
			tvCell[3][11]= (TextView) rootView.findViewById(R.id.Thu11);
			tvCell[3][12]= (TextView) rootView.findViewById(R.id.Thu12);
			tvCell[3][13]= (TextView) rootView.findViewById(R.id.Thu13);
			tvCell[3][14]= (TextView) rootView.findViewById(R.id.Thu14);
			tvCell[3][15]= (TextView) rootView.findViewById(R.id.Thu15);
			tvCell[3][16]= (TextView) rootView.findViewById(R.id.Thu16);
			tvCell[3][17]= (TextView) rootView.findViewById(R.id.Thu17);
			tvCell[3][18]= (TextView) rootView.findViewById(R.id.Thu18);
			tvCell[3][19]= (TextView) rootView.findViewById(R.id.Thu19);
			tvCell[3][20]= (TextView) rootView.findViewById(R.id.Thu20);
			tvCell[3][21]= (TextView) rootView.findViewById(R.id.Thu21);
			tvCell[3][22]= (TextView) rootView.findViewById(R.id.Thu22);
			tvCell[3][23]= (TextView) rootView.findViewById(R.id.Thu23);

			tvCell[4][0]= (TextView) rootView.findViewById(R.id.Fri00);
			tvCell[4][1]= (TextView) rootView.findViewById(R.id.Fri01);
			tvCell[4][2]= (TextView) rootView.findViewById(R.id.Fri02);
			tvCell[4][3]= (TextView) rootView.findViewById(R.id.Fri03);
			tvCell[4][4]= (TextView) rootView.findViewById(R.id.Fri04);
			tvCell[4][5]= (TextView) rootView.findViewById(R.id.Fri05);
			tvCell[4][6]= (TextView) rootView.findViewById(R.id.Fri06);
			tvCell[4][7]= (TextView) rootView.findViewById(R.id.Fri07);
			tvCell[4][8]= (TextView) rootView.findViewById(R.id.Fri08);
			tvCell[4][9]= (TextView) rootView.findViewById(R.id.Fri09);
			tvCell[4][10]= (TextView) rootView.findViewById(R.id.Fri10);
			tvCell[4][11]= (TextView) rootView.findViewById(R.id.Fri11);
			tvCell[4][12]= (TextView) rootView.findViewById(R.id.Fri12);
			tvCell[4][13]= (TextView) rootView.findViewById(R.id.Fri13);
			tvCell[4][14]= (TextView) rootView.findViewById(R.id.Fri14);
			tvCell[4][15]= (TextView) rootView.findViewById(R.id.Fri15);
			tvCell[4][16]= (TextView) rootView.findViewById(R.id.Fri16);
			tvCell[4][17]= (TextView) rootView.findViewById(R.id.Fri17);
			tvCell[4][18]= (TextView) rootView.findViewById(R.id.Fri18);
			tvCell[4][19]= (TextView) rootView.findViewById(R.id.Fri19);
			tvCell[4][20]= (TextView) rootView.findViewById(R.id.Fri20);
			tvCell[4][21]= (TextView) rootView.findViewById(R.id.Fri21);
			tvCell[4][22]= (TextView) rootView.findViewById(R.id.Fri22);
			tvCell[4][23]= (TextView) rootView.findViewById(R.id.Fri23);

			tvCell[5][0]= (TextView) rootView.findViewById(R.id.Sat00);
			tvCell[5][1]= (TextView) rootView.findViewById(R.id.Sat01);
			tvCell[5][2]= (TextView) rootView.findViewById(R.id.Sat02);
			tvCell[5][3]= (TextView) rootView.findViewById(R.id.Sat03);
			tvCell[5][4]= (TextView) rootView.findViewById(R.id.Sat04);
			tvCell[5][5]= (TextView) rootView.findViewById(R.id.Sat05);
			tvCell[5][6]= (TextView) rootView.findViewById(R.id.Sat06);
			tvCell[5][7]= (TextView) rootView.findViewById(R.id.Sat07);
			tvCell[5][8]= (TextView) rootView.findViewById(R.id.Sat08);
			tvCell[5][9]= (TextView) rootView.findViewById(R.id.Sat09);
			tvCell[5][10]= (TextView) rootView.findViewById(R.id.Sat10);
			tvCell[5][11]= (TextView) rootView.findViewById(R.id.Sat11);
			tvCell[5][12]= (TextView) rootView.findViewById(R.id.Sat12);
			tvCell[5][13]= (TextView) rootView.findViewById(R.id.Sat13);
			tvCell[5][14]= (TextView) rootView.findViewById(R.id.Sat14);
			tvCell[5][15]= (TextView) rootView.findViewById(R.id.Sat15);
			tvCell[5][16]= (TextView) rootView.findViewById(R.id.Sat16);
			tvCell[5][17]= (TextView) rootView.findViewById(R.id.Sat17);
			tvCell[5][18]= (TextView) rootView.findViewById(R.id.Sat18);
			tvCell[5][19]= (TextView) rootView.findViewById(R.id.Sat19);
			tvCell[5][20]= (TextView) rootView.findViewById(R.id.Sat20);
			tvCell[5][21]= (TextView) rootView.findViewById(R.id.Sat21);
			tvCell[5][22]= (TextView) rootView.findViewById(R.id.Sat22);
			tvCell[5][23]= (TextView) rootView.findViewById(R.id.Sat23);

			tvCell[6][0]= (TextView) rootView.findViewById(R.id.Sun00);
			tvCell[6][1]= (TextView) rootView.findViewById(R.id.Sun01);
			tvCell[6][2]= (TextView) rootView.findViewById(R.id.Sun02);
			tvCell[6][3]= (TextView) rootView.findViewById(R.id.Sun03);
			tvCell[6][4]= (TextView) rootView.findViewById(R.id.Sun04);
			tvCell[6][5]= (TextView) rootView.findViewById(R.id.Sun05);
			tvCell[6][6]= (TextView) rootView.findViewById(R.id.Sun06);
			tvCell[6][7]= (TextView) rootView.findViewById(R.id.Sun07);
			tvCell[6][8]= (TextView) rootView.findViewById(R.id.Sun08);
			tvCell[6][9]= (TextView) rootView.findViewById(R.id.Sun09);
			tvCell[6][10]= (TextView) rootView.findViewById(R.id.Sun10);
			tvCell[6][11]= (TextView) rootView.findViewById(R.id.Sun11);
			tvCell[6][12]= (TextView) rootView.findViewById(R.id.Sun12);
			tvCell[6][13]= (TextView) rootView.findViewById(R.id.Sun13);
			tvCell[6][14]= (TextView) rootView.findViewById(R.id.Sun14);
			tvCell[6][15]= (TextView) rootView.findViewById(R.id.Sun15);
			tvCell[6][16]= (TextView) rootView.findViewById(R.id.Sun16);
			tvCell[6][17]= (TextView) rootView.findViewById(R.id.Sun17);
			tvCell[6][18]= (TextView) rootView.findViewById(R.id.Sun18);
			tvCell[6][19]= (TextView) rootView.findViewById(R.id.Sun19);
			tvCell[6][20]= (TextView) rootView.findViewById(R.id.Sun20);
			tvCell[6][21]= (TextView) rootView.findViewById(R.id.Sun21);
			tvCell[6][22]= (TextView) rootView.findViewById(R.id.Sun22);
			tvCell[6][23]= (TextView) rootView.findViewById(R.id.Sun23);


			for(int i = 0;i <7; i++) {
				for(int j=0;j<24; j++) {
					tvCell[i][j].setTag("" + i + String.format("%02d", j));
					tvCell[i][j].setOnClickListener(new cellonClickListner());
					tvCell[i][j].setPadding(2, 2, 2, 2);
					tvCell[i][j].setTextColor(Color.parseColor("#ffffff"));
				}
			}
		}

		


		return rootView;
	}


	public class cellonClickListner implements OnClickListener{

		@Override
		public void onClick(View v) {
			int day, hour;
			String s = (String) v.getTag();
			day = Integer.parseInt(s)/100;
			hour = Integer.parseInt(s)%100;

			Intent i = new Intent(getActivity(), WeeklyRegistrationActivity.class);
			i.putExtra("day", day);
			i.putExtra("hour", hour);
			startActivity(i);

			tvCell[day][hour].setBackgroundResource(R.drawable.rounded_corner_week);

		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ArrayList<WeeklyItem> wlist = weeklyManager.getList();
		
		for (WeeklyItem weeklyItem : wlist) {
			tvCell[weeklyItem.getDayofweek()][weeklyItem.getTime()/100].setBackgroundResource(R.drawable.rounded_corner_week);
			tvCell[weeklyItem.getDayofweek()][weeklyItem.getTime()/100].setText(String.format("%s\n%02d:%02d", weeklyItem.getName().substring(0, (weeklyItem.getName().length()<3)?weeklyItem.getName().length():3), weeklyItem.getTime()/100, weeklyItem.getTime()%100));
		}
	}

}
