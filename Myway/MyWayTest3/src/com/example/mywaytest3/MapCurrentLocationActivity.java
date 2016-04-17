package com.example.mywaytest3;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class MapCurrentLocationActivity extends FragmentActivity {

	private GoogleMap mMap;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_current_location);
         
        // BitmapDescriptorFactory 생성하기 위한 소스 
        MapsInitializer.initialize(getApplicationContext());
         
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapCurrentLocationActivity.this);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_view_current)).getMap();
         
        // 맵의 이동 
        GpsInfo gps = new GpsInfo(MapCurrentLocationActivity.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
             
            Toast.makeText(MapCurrentLocationActivity.this, "좌표 : " + longitude + ", " + latitude, Toast.LENGTH_SHORT).show();
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
}
