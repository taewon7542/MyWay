package com.example.mywaytest3;

import java.net.URL;
import java.util.Calendar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager.WakeLock;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmScreenActivity extends Activity implements OnInitListener, AnimationListener {
	
	public final String TAG = this.getClass().getSimpleName();

	TextView txtView1, txtView2;
	Animation animFadeIn, animFadeOut;
	
	TextView tWeather;
	TextView tWeather2;
	TextView tTemp;
	Button btn;
	LinearLayout linear;
	ImageView img;
	
	int ntemp=9999;
	String name = "";
	String name2 = "";
	String weather = "";
	String msg = "";
	String msg2 = "";
	String temp = "읽어오는중..";
	
	String stratPoint = "내집";
	String destination = "회사";
	
	String latitude ="126.9752473";
	String longitude="37.5759225";
	

	
	String woeid = "1132599";
	
	Thread t;
	Thread tget;
	
	private TextToSpeech myTTS;
	private int mHour, mMinute;
	
	private WakeLock mWakeLock;

	private static final int WAKELOCK_TIMEOUT = 60 * 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Setup layout
		this.setContentView(R.layout.activity_alarm_screen);


//		animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
//	            R.animator.faidin);
//		
//		animFadeIn.setAnimationListener(this);
//		 
		getActionBar().hide();
		
		tTemp = (TextView) findViewById(R.id.textTemp);
		tWeather = (TextView) findViewById(R.id.textWeather);
		tWeather2 = (TextView) findViewById(R.id.textDstWeather);
		
		btn = (Button) findViewById(R.id.buttonFinish);
		linear=(LinearLayout)findViewById(R.id.linear);
		img = (ImageView)findViewById(R.id.imageWeather);
		myTTS = new TextToSpeech(this, this);

//		btn.startAnimation(animFadeIn);
		                 
		
		tget = new Thread() {
			public void run() {
				// 이곳으로 이동
				try {

					String there = "http://query.yahooapis.com/v1/public/yql?q=select+%2A+from+geo.placefinder+where+text%3D%22"+latitude+"%2C"+longitude+"%22+and+gflags%3D%22R%22&format=xml";

					URL url = new URL(there);
					XmlPullParserFactory parserCreator = XmlPullParserFactory
							.newInstance();
					XmlPullParser parser = parserCreator.newPullParser();
					parser.setInput(url.openStream(), "utf-8");

					int eventType = parser.getEventType();

					while (eventType != XmlPullParser.END_DOCUMENT) {

						name = parser.getName();
						if (name != null) {
							if (name.equals("woeid")) {
								eventType = parser.next();
								if (eventType == XmlPullParser.TEXT) {
									System.out.println(there);

									woeid = parser.getText().trim();
									System.out.println(woeid);
								}
							}
						}
						eventType = parser.next();
					}

				} catch (Exception e) {
					Log.e("다운로드 중 에러 발생", e.getMessage());
				}
				
			}
			
		};
		
		t = new Thread() {
			public void run() {
				// 이곳으로 이동
				try {

					String here = "http://weather.yahooapis.com/forecastrss?w="+woeid+"&u=c";
					URL url = new URL(here);
					XmlPullParserFactory parserCreator = XmlPullParserFactory
							.newInstance();
					XmlPullParser parser = parserCreator.newPullParser();
					parser.setInput(url.openStream(), "utf-8");

					int eventType = parser.getEventType();

					while (eventType != XmlPullParser.END_DOCUMENT) {

						name2 = parser.getName();
						if (name2 != null) {
							if (name2.equals("yweather:condition")) {

								int size = parser.getAttributeCount();
								for (int i = 0; i < size; i++) {
									if (i == 2){
										temp = parser.getAttributeValue(i);
									}
									if (i == 0){
										weather = parser.getAttributeValue(i);
									}
								}
							}
						}
						eventType = parser.next();
					}

				} catch (Exception e) {
					Log.e("다운로드 중 에러 발생", e.getMessage());
				}
				
			}
			
		};
		
		tget.start();
		try {
			tget.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		t.start();

		try {

			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Ensure wakelock release
		Runnable releaseWakelock = new Runnable() {

			@Override
			public void run() {
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

				if (mWakeLock != null && mWakeLock.isHeld()) {
					mWakeLock.release();
				}
			}
		};

		new Handler().postDelayed(releaseWakelock, WAKELOCK_TIMEOUT);
		
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlarmScreenActivity.this.finish();

			}
		});

		
		
	}
	
//	@SuppressWarnings("deprecation")
//	@Override
//	protected void onResume() {
//		super.onResume();
//
//		// Set the window to keep screen on
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//
//		// Acquire wakelock
//		PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//		if (mWakeLock == null) {
//			mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), TAG);
//		}
//
//		if (!mWakeLock.isHeld()) {
//			mWakeLock.acquire();
//			Log.i(TAG, "Wakelock aquired!!");
//		}
//
//	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mWakeLock != null && mWakeLock.isHeld()) {
			mWakeLock.release();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			if (myTTS != null) {
				myTTS.shutdown();
				myTTS = null;
			}
		} catch (Exception e) {
		}
	}

	@SuppressLint("NewApi")
	public void onInit(int status) {

		if(status !=  TextToSpeech.SUCCESS)
			Toast.makeText(AlarmScreenActivity.this, "TTS 초기화 실패", Toast.LENGTH_SHORT).show();
		
		final Calendar c = Calendar.getInstance();

		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

		if(mHour>17 || mHour<6){
			linear.setBackground(getResources().getDrawable(R.drawable.gradeition2));
		}
		
		tTemp.setText(temp);
		tWeather.setText(stratPoint + " 에서  " + destination);
		tWeather2.setText(destination + " / " +weather);

		
		if(!temp.equals("읽어오는중..")){
			ntemp = Integer.parseInt(tTemp.getText().toString());			
		}

		if (ntemp != 9999) {
			if (ntemp > 30) {
				msg = "많이 덥습니다. 야외 활동 자제해주세요.";
			} else if (Integer.parseInt(temp) > 25) {
				msg = "온도는 약간 더운 정도입니다.";
			} else if (Integer.parseInt(temp) > 18) {
				msg = "딱 좋은 온도입니다.";
			} else if (Integer.parseInt(temp) > 10) {
				msg = "가디건 같은 외투 챙기세요.";
			} else if (Integer.parseInt(temp) > 5) {
				msg = "단단하게 입고가세요.";
			} else if (Integer.parseInt(temp) < 5) {
				msg = "춥습니다. 실내에서 노세요.";
			}
		}
		
		if (weather.equals("Mostly Cloudy")) {
			msg2 = "구름이 많습니다.";
			img.setImageResource(R.drawable.mostlycloudy);
		} else if (weather.equals("Fog")) {
			msg2 = "안개가 많습니다.";
			img.setImageResource(R.drawable.haze);
		} else if (weather.equals("Partly Cloudy")) {
			msg2 = "구름이 조금있습니다.";
			img.setImageResource(R.drawable.mostlycloudy);
		} else if (weather.equals("Sunny")) {
			msg2 = "화창합니다.";
			img.setImageResource(R.drawable.sunny);
		} else if (weather.equals("Scattered Thunderstorms")) {
			msg2 = "천둥 번개가 칩니다. 마음 단디 먹으세요.";
			img.setImageResource(R.drawable.thunder);
		} else if (weather.equals("Showers")) {
			msg2 = "비가오니 우산 꼭 챙기세요.";
			img.setImageResource(R.drawable.drizzle);
		} else if (weather.equals("Mostly Sunny")) {
			msg2 = "꽤 화창합니다.";
			img.setImageResource(R.drawable.snow);
		} else if (weather.equals("Snowy")) {
			msg2 = "눈이옵니다.";
			img.setImageResource(R.drawable.snow);
		}
			
		textToSpeak("이제 출발하셔야 합니다." + mHour + "시" + mMinute + "분." + ". 기온"
				+ temp + "도." + msg + msg2);
		


	}

	@SuppressWarnings("deprecation")
	public void textToSpeak(String str) {
		myTTS.speak(str, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
	
}
