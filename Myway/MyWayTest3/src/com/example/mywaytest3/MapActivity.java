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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener {

	private Button mBtnFind;
	private GoogleMap mMap;
	private EditText etPlace;
	private Button mButtonAdd;

	//find lat and lon on click marker
	LatLng markerLocation;

	double lat = 0;
	double lng = 0;
	String newAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mBtnFind = (Button) findViewById(R.id.buttonSearch);
		mButtonAdd = (Button) findViewById(R.id.btn_add); // 검색버튼
		etPlace = (EditText) findViewById(R.id.editTextQuery);

		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_view); 
		mMap = mapFragment.getMap();
		mMap.setOnMarkerClickListener(this);

		mBtnFind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				// Getting the place entered 
				String location = etPlace.getText().toString();

				if(location==null || location.equals("")){
					Toast.makeText(getBaseContext(), "장소를 입력하세요", Toast.LENGTH_SHORT).show();
					return;
				}

				String url = "https://maps.googleapis.com/maps/api/geocode/json?";					

				try {
					// encoding special characters like space in the user input place
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
		});

		mButtonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(etPlace.getText().toString()==null || etPlace.getText().toString().equals("")){
					Toast.makeText(getBaseContext(), "장소를 입력하세요", Toast.LENGTH_SHORT).show();
					//////// 테스트!!!!
					Intent intent=new Intent(MapActivity.this, MapCurrentLocationActivity.class);
					startActivity(intent);
					///////
					return;
				}

				if(lat == 0 && lng == 0 && newAddress == null){
					Toast.makeText(getBaseContext(), "검색 버튼을 터치해 주세요", Toast.LENGTH_SHORT).show();
					return;
				}

				if(markerLocation == null){
					Toast.makeText(MapActivity.this, "좌표 :" + lng + ", " + lat +"\n주소 : " + newAddress, Toast.LENGTH_SHORT).show();
					return;
				}
				//마커가 여러개 일때 마커를 선택한 경우
				Toast.makeText(MapActivity.this, "좌표 :" + markerLocation.longitude + ", " + markerLocation.latitude+"\n주소 : " + newAddress, Toast.LENGTH_SHORT).show();
			}
		});
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
		return false;
	}
}
