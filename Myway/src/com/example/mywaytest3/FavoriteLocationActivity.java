package com.example.mywaytest3;

import java.util.ArrayList;

import com.example.mywaytest3.adapter.AppointmentListAdapter;
import com.example.mywaytest3.adapter.FavoriteLocationListAdapter;
import com.example.mywaytest3.model.AppointmentItem;
import com.example.mywaytest3.model.AppointmentManager;
import com.example.mywaytest3.model.LocationItem;
import com.example.mywaytest3.model.LocationManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FavoriteLocationActivity extends FragmentActivity{
	

	private static LocationManager locationManager = LocationManager.getInstance();
	private static FavoriteLocationListAdapter favoriteLocationListAdapter;
	//Dummy code
	public static ArrayList<LocationItem> list = new ArrayList<LocationItem>();

	public static ListView lv;
	public static Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritelocation);


		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));

		getActionBar().setIcon(
				new ColorDrawable(getResources().getColor(android.R.color.transparent)));   
		getActionBar().setTitle("즐겨 찾는 장소");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		list = locationManager.getList();
		lv = (ListView) findViewById(R.id.lvFavoriteLocation);
		favoriteLocationListAdapter = new FavoriteLocationListAdapter(this, list);
		lv.setAdapter(favoriteLocationListAdapter);
		favoriteLocationListAdapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(lv);

		handler = new Handler() {
            public void handleMessage(Message paramAnonymousMessage) {
                switch (paramAnonymousMessage.what) {
                case 1:
                	populateBill();
                    break;
                }
            }
        };
		
	}
	
	 public void populateBill() {
			favoriteLocationListAdapter = new FavoriteLocationListAdapter(this, list);
			lv.setAdapter(favoriteLocationListAdapter);
			setListViewHeightBasedOnChildren(lv);	
}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		if(menuItem.getItemId() == R.id.itemAdd)
		{
			startActivity(new Intent(FavoriteLocationActivity.this, FavoriteLocationSelectActivity.class));
		}
		else onBackPressed();
	    return true;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_favoritelocation, menu);
		return true;
	}


	@Override
	protected void onResume() {
		super.onResume();
		
		list = locationManager.getList();
		favoriteLocationListAdapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(lv);
	}
	
	public static void redraw(){
		list = locationManager.getList();
		favoriteLocationListAdapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(lv);		
	}
	
	 public static void setListViewHeightBasedOnChildren(ListView listView) {
	        ListAdapter listAdapter = listView.getAdapter(); 
	        if (listAdapter == null) {
	            // pre-condition
	            return;
	        }

	        int totalHeight = 0;
	        for (int i = 0; i < listAdapter.getCount(); i++) {
	            View listItem = listAdapter.getView(i, null, listView);
	            listItem.measure(0, 0);
	            totalHeight += listItem.getMeasuredHeight();
	        }

	        ViewGroup.LayoutParams params = listView.getLayoutParams();
	        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + 120;
	        listView.setLayoutParams(params);
	        listView.requestLayout();
	    }
	
}
