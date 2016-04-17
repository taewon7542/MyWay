package com.example.mywaytest3;

import com.example.mywaytest3.alarm.AlarmDBHelper;
import com.example.mywaytest3.alarm.AlarmManagerHelper;
import com.example.mywaytest3.alarm.AlarmModel;
import com.example.mywaytest3.model.LocationItem;
import com.example.mywaytest3.model.LocationManager;
import com.example.mywaytest3.model.WeeklyManager;
import com.github.jjobes.slidedaytimepicker.SlideDayTimeListener;
import com.github.jjobes.slidedaytimepicker.SlideDayTimePicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeeklyRegistrationActivity extends FragmentActivity{
	
	private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
	private AlarmModel alarmDetails;
	
	LinearLayout layoutWeekDay, layoutWeekTime, layoutWeekFrom, layoutWeekTo, layoutWeekTransport;
	
	TextView tvWeekDay, tvWeekTime, tvWeekFrom, tvWeekTo, tvWeekTransport;
	
	EditText etName;
	
	public static int day, hour, minute = 0; 
	final CharSequence[] itemsTrans = {" 대중교통"," 자동차"," 도보"};
	int trans=0;
	
	AlertDialog dlgTrans;
	AlertDialog.Builder builder2;

	
	public String sDay[] = new String[]{"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weeklyregistration);

		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f44336")));
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));   
		getActionBar().setTitle("새 주간일정 등록");
		//getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		alarmDetails = new AlarmModel();
		
		day = getIntent().getExtras().getInt("day", 0);
		hour = getIntent().getExtras().getInt("hour", 0);

		tvWeekDay = (TextView) findViewById(R.id.tvWeekDay);
		tvWeekTime = (TextView) findViewById(R.id.tvWeekTime);
		tvWeekFrom = (TextView) findViewById(R.id.tvWeekFrom);
		tvWeekTo = (TextView) findViewById(R.id.tvWeekTo);
		tvWeekTransport = (TextView) findViewById(R.id.tvWeekTransport);
		
		
		layoutWeekDay = (LinearLayout) findViewById(R.id.layoutWeekDay);
		layoutWeekTime = (LinearLayout) findViewById(R.id.layoutWeekTime);
		layoutWeekFrom = (LinearLayout) findViewById(R.id.layoutWeekFrom);
		layoutWeekTo = (LinearLayout) findViewById(R.id.layoutWeekTo);
		layoutWeekTransport = (LinearLayout) findViewById(R.id.layoutWeekTransport);
		
		etName = (EditText) findViewById(R.id.etName);
		
		
    	tvWeekDay.setText(sDay[day]);
    	tvWeekTime.setText(String.format("%s %02d:%02d", (hour >=12)?"오후":"오전", (hour > 12)?hour-12:hour, minute));
				
    	
    	LocationManager.locComm[1][0].setId(-2);
    	LocationManager.locComm[1][1].setId(-3);
		 final SlideDayTimeListener listener = new SlideDayTimeListener() {

	            @Override
	            public void onDayTimeSet(int day, int hour, int minute)
	            {
	            	WeeklyRegistrationActivity.day = day - 1;
	            	WeeklyRegistrationActivity.hour = hour;
	            	WeeklyRegistrationActivity.minute = minute;
	            	
	            	tvWeekDay.setText(sDay[WeeklyRegistrationActivity.day]);
	            	tvWeekTime.setText(String.format("%s %02d:%02d", (hour >=12)?"오후":"오전", (hour > 12)?hour-12:hour, minute));
	            	
	            }

	            @Override
	            public void onDayTimeCancel()
	            {
	            }
	        };

	        
	        
        layoutWeekDay.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v)
	            {
	                new SlideDayTimePicker.Builder(getSupportFragmentManager())
	                    .setListener(listener)
	                    .setInitialDay(day+1)
	                    .setInitialHour(hour)
	                    .setInitialMinute(minute)
	                    .build()
	                    .show();
	            }
	        });
        layoutWeekTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                new SlideDayTimePicker.Builder(getSupportFragmentManager())
                .setListener(listener)
                .setInitialDay(day+1)
                .setInitialHour(hour)
                .setInitialMinute(minute)
                .build()
                .show();
            }
        });
        
        layoutWeekFrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WeeklyRegistrationActivity.this, PlaceSelectActivity.class);
				i.putExtra("bDayorWeek", 1);
				i.putExtra("bType", 0);
				startActivity(i);
			}
		});

        
        layoutWeekTo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WeeklyRegistrationActivity.this, PlaceSelectActivity.class);
				i.putExtra("bDayorWeek", 1);
				i.putExtra("bType", 1);
				startActivity(i);
			}
		});
        
        tvWeekTransport.setText(itemsTrans[trans]);
        

		// Creating and Building the Dialog 
		builder2 = new AlertDialog.Builder(this);
		builder2.setTitle("교통수단 설정");
		builder2.setSingleChoiceItems(itemsTrans, -1, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				trans = item;
				tvWeekTransport.setText(itemsTrans[trans]);
				dlgTrans.dismiss();    
			}
		});
		
		layoutWeekTransport.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dlgTrans = builder2.create();
				dlgTrans.show();
			}
		});


        
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		if(menuItem.getItemId() == R.id.itemSave)
		{
			LocationItem l0 = LocationManager.locComm[1][0];
			LocationItem l1 = LocationManager.locComm[1][1];
			
			if(l0.getId() == -2)
				WeeklyManager.getInstance().addItem(etName.getText().toString(),day,hour*100 +minute, trans
						,-1,"", "", 0f, 0f
						,l1.getId(),l1.getName(), l1.getAddress(), l1.getX(), l1.getY());
			else
			WeeklyManager.getInstance().addItem(etName.getText().toString(),day,hour*100 +minute, trans
					,l0.getId(),l0.getName(), l0.getAddress(), l0.getX(), l0.getY()
					,l1.getId(),l1.getName(), l1.getAddress(), l1.getX(), l1.getY());
			Toast.makeText(WeeklyRegistrationActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
			updateModelFromLayout();
			
			AlarmManagerHelper.cancelAlarms(this);
			dbHelper.createAlarm(alarmDetails);
			
			AlarmManagerHelper.setAlarms(this);
		}
		
	    onBackPressed();
	    return true;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_registration, menu);
		return true;
	}


	@Override
	protected void onResume() {
		super.onResume();
		if(LocationManager.locComm[1][0].getId() == -2)
			tvWeekFrom.setText("실시간 위치");
		else if(LocationManager.locComm[1][0].getId() == -1)
			tvWeekFrom.setText(LocationManager.locComm[1][0].getAddress());
		else
		{
			LocationItem l = LocationManager.getInstance().getLocationItem(LocationManager.locComm[1][0].getId());
			tvWeekFrom.setText(l.getAddress());
			LocationManager.locComm[1][0] = l;
		}
			
		if(LocationManager.locComm[1][1].getId() == -3)
			tvWeekTo.setText("");
		else if(LocationManager.locComm[1][1].getId() == -1)
			tvWeekTo.setText(LocationManager.locComm[1][1].getAddress());
		else
		{
			LocationItem l = LocationManager.getInstance().getLocationItem(LocationManager.locComm[1][1].getId());
			tvWeekTo.setText(l.getAddress());
			LocationManager.locComm[1][1] = l;
			
		}
	}
	
	private void updateModelFromLayout() {		
		alarmDetails.timeMinute = minute;
		alarmDetails.timeHour = hour;
		alarmDetails.name = LocationManager.locComm[1][1].getAddress();
		alarmDetails.repeatWeekly = true;	
		alarmDetails.setRepeatingDay((day+1)%7, true);	

		alarmDetails.isEnabled = true;
	}
	
	
}
