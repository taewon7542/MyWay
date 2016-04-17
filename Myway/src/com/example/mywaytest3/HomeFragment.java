package com.example.mywaytest3;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mywaytest3.adapter.AppointmentListAdapter;
import com.example.mywaytest3.googlemap.GpsInfo;
import com.example.mywaytest3.model.AppointmentItem;
import com.example.mywaytest3.model.AppointmentManager;
import com.example.mywaytest3.model.LocationItem;
import com.example.mywaytest3.model.LocationManager;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("RtlHardcoded")
public class HomeFragment extends Fragment {

	protected  static View mView = null;
	public static View rootView = null;
	public static ImageButton btnDrawer, btnSearch;
	public EditText etSearchKey;
	private GoogleMap mMap;
	private FragmentActivity mContext;

	private AppointmentManager appointmentManager = AppointmentManager.getInstance();
	private AppointmentListAdapter appointmentListAdapter;
	//Dummy code
	public ArrayList<AppointmentItem> list = new ArrayList<AppointmentItem>();

	public ListView lv;


	public HomeFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		MainActivity.actionBar.hide();

		if(rootView == null)
		{
			rootView = inflater.inflate(R.layout.fragment_home, container, false);
			etSearchKey = (EditText) rootView.findViewById(R.id.etSearchKey);
			btnDrawer = (ImageButton) rootView.findViewById(R.id.btnDrawer);
			btnSearch = (ImageButton) rootView.findViewById(R.id.btnSearch);		
			lv = (ListView) rootView.findViewById(R.id.lvCommingEvent);
			this.mView = rootView;
		}
		
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		mContext = (FragmentActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		btnDrawer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		appointmentManager.sort();
		
		list = appointmentManager.getList();
		
		//Gps
		// BitmapDescriptorFactory 생성하기 위한 소스 
		MapsInitializer.initialize(getActivity().getApplicationContext());

		GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		mMap = ((SupportMapFragment) mContext.getSupportFragmentManager().findFragmentById(R.id.map_view_current)).getMap();

		// 맵의 이동 
		GpsInfo gps = new GpsInfo(getActivity());
		// GPS 사용유무 가져오기
		if (gps.isGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			com.example.mywaytest3.model.LocationManager.locCurrent.setX((float) latitude);
			com.example.mywaytest3.model.LocationManager.locCurrent.setY((float) longitude);
			

			LatLng latLng = new LatLng(latitude, longitude);

			mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
			mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

			// 마커 설정.
			MarkerOptions optFirst = new MarkerOptions();
			optFirst.position(latLng);
			optFirst.title("현재 위치");
			mMap.addMarker(optFirst).showInfoWindow();
		}
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
	        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	        listView.setLayoutParams(params);
	        listView.requestLayout();
	    }

	 @Override
	public void onResume() {
		super.onResume();
		appointmentManager.sort();
		
		list = appointmentManager.getList();
		
		appointmentListAdapter = new AppointmentListAdapter(getActivity(), list);
		lv.setAdapter(appointmentListAdapter);
		appointmentListAdapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(lv);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppointmentItem l = list.get(position);
				Intent i = new Intent(getActivity(), RouteActivity.class);
				
								
				i.putExtra("olat", (l.getFrom() == -2)?LocationManager.locCurrent.getX():l.getFromX());
				i.putExtra("olng", (l.getFrom() == -2)?LocationManager.locCurrent.getY():l.getFromY());
				i.putExtra("dlat", l.getToX());
				i.putExtra("dlng", l.getToY());
				
				startActivity(i);
			}
		});
	}
	
}
