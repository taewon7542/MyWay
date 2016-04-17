package com.example.mywaytest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywaytest3.googlemap.GeocodeJSONParser;
import com.example.mywaytest3.model.LocationManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FavoriteLocationSelectActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener {

	private ImageButton btnFind;
	private GoogleMap mMap;
	private EditText etPlace, etName, etOutTime;
	private TextView tvAddress;

	//find lat and lon on click marker
	LatLng markerLocation;
	
	
	double lat = 0;
	double lng = 0;
	String newAddress;
	
	int bDayorWeek, bType;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritelocationselect);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
		
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));   
		getActionBar().setTitle("위치 선택");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		


		btnFind = (ImageButton) findViewById(R.id.btnSearch);
		etPlace = (EditText) findViewById(R.id.etTextQuery);
		etName = (EditText) findViewById(R.id.etName);
		etOutTime = (EditText) findViewById(R.id.etOutTime);
		tvAddress = (TextView) findViewById(R.id.tvAdress);
		
		
		tvAddress.setText("");
		
		
		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_view); 
		mMap = mapFragment.getMap();
		mMap.setOnMarkerClickListener(this);
		
		LatLng latLng = new LatLng(LocationManager.locCurrent.getX(), LocationManager.locCurrent.getY());

		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		
		etPlace.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if(keyCode == KeyEvent.KEYCODE_ENTER){
					Search();
					return true;
				}
				
				return false;
			}
		});
		
		btnFind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Search();
			}
		});
	}
	
	public void Search()
	{
		String location = etPlace.getText().toString();

		if(location==null || location.equals("")){
			Toast.makeText(getBaseContext(), "장소를 입력하세요", Toast.LENGTH_SHORT).show();
			return;
		}

		String url = "https://maps.googleapis.com/maps/api/geocode/json?";					

		try {
			location = URLEncoder.encode(location, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String address = "address=" + location;
		String sensor = "sensor=false";
		String lang = "&language=ko";
		url = url + address + "&" + sensor + lang;

		DownloadTask downloadTask = new DownloadTask();
		downloadTask.execute(url);
	}

	private String downloadUrl(String strUrl) throws IOException{
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try{
			URL url = new URL(strUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
			StringBuffer sb  = new StringBuffer();

			String line = "";
			while( ( line = br.readLine())  != null){
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		}catch(Exception e){
			Log.d("Exception while downloading url", e.toString());
		}finally{
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}


	/** A class, to download Places from Geocoding webservice */
	private class DownloadTask extends AsyncTask<String, Integer, String>{
		String data = null;
		@Override
		protected String doInBackground(String... url) {
			try{                    		
				data = downloadUrl(url[0]);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result){
			ParserTask parserTask = new ParserTask();
			parserTask.execute(result);
		}
	}

	/** A class to parse the Geocoding Places in non-ui thread */
	class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{
		JSONObject jObject;

		@Override
		protected List<HashMap<String,String>> doInBackground(String... jsonData) {

			List<HashMap<String, String>> places = null;			
			GeocodeJSONParser parser = new GeocodeJSONParser();

			try{
				jObject = new JSONObject(jsonData[0]);

				/** Getting the parsed data as a an ArrayList */
				places = parser.parse(jObject);

			}catch(Exception e){
				Log.d("Exception",e.toString());
			}
			return places;
		}

		@Override
		protected void onPostExecute(List<HashMap<String,String>> list){			
			mMap.clear();
			for(int i=0;i<list.size();i++){
				MarkerOptions markerOptions = new MarkerOptions();
				HashMap<String, String> hmPlace = list.get(i);
				lat = Double.parseDouble(hmPlace.get("lat"));	            
				lng = Double.parseDouble(hmPlace.get("lng"));

				newAddress = hmPlace.get("formatted_address");

				LatLng latLng = new LatLng(lat, lng);
				markerOptions.position(latLng);
				markerOptions.title(newAddress);
				
				tvAddress.setText(newAddress);

				mMap.addMarker(markerOptions);    

				if(i==0)
					mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
			}            
		}
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		markerLocation=marker.getPosition();
		newAddress = marker.getTitle();
		tvAddress.setText(marker.getTitle());
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		if(menuItem.getItemId() == R.id.itemSave)
		{
			if(etName.getText().toString().length() == 0)
				Toast.makeText(FavoriteLocationSelectActivity.this, "장소명을 입력하세요", Toast.LENGTH_SHORT).show();
			if(tvAddress.getText().toString().length() == 0)
				Toast.makeText(FavoriteLocationSelectActivity.this, "장소를 선택하세요", Toast.LENGTH_SHORT).show();
			int outtime = 0;
			try{
				outtime = Integer.parseInt(etOutTime.getText().toString());
			}catch(Exception e) {}
			
			LocationManager.getInstance().addItemWithoutID(etName.getText().toString(), tvAddress.getText().toString(), (float) lat, (float) lng, outtime, 0);
			Toast.makeText(FavoriteLocationSelectActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
			onBackPressed();
		}
		else 
			onBackPressed();
	    return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_registration, menu);
		return true;
	}
}