package com.example.mywaytest3;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PersonalSettingFragment extends Fragment {
	
	public PersonalSettingFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		MainActivity.actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
		MainActivity.actionBar.show();
		
        View rootView = inflater.inflate(R.layout.fragment_personalsetting, container, false);
         
        
        LinearLayout layoutPersonalPlaces;
        layoutPersonalPlaces = (LinearLayout) rootView.findViewById(R.id.layoutPersonalPlaces);
        
        
        
        layoutPersonalPlaces.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), FavoriteLocationActivity.class) );
			}
		});
        
        return rootView;
    }
}
