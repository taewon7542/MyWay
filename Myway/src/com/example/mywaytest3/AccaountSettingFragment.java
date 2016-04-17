package com.example.mywaytest3;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccaountSettingFragment extends Fragment {

	public AccaountSettingFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		MainActivity.actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9800")));
		MainActivity.actionBar.show();

		View rootView = inflater.inflate(R.layout.fragment_account, container, false);

		return rootView;
	}
}
