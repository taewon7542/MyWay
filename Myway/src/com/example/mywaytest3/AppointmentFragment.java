package com.example.mywaytest3;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppointmentFragment extends Fragment {
	
	public AppointmentFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		MainActivity.actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4caf50")));

		MainActivity.actionBar.show();
		
        View rootView = inflater.inflate(R.layout.fragment_appointment, container, false);
         
        return rootView;
    }
}
