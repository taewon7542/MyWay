package com.example.mywaytest3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyWayDBHelper extends SQLiteOpenHelper{
	public static final String DB_NAME = "myway.db";
	public static final String DB_TABLE_PERSONAL = "personal";
	public static final String DB_TABLE_LOCATION = "location";
	public static final String DB_TABLE_APPOINTMENT = "appointment";	
	public static final String DB_TABLE_WEEKLY = "weekschedule";	
	

	public MyWayDBHelper(Context context) {
		super(context, DB_NAME, null, 8);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+ DB_TABLE_PERSONAL + 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, _walkvel INT);");
		db.execSQL("CREATE TABLE "+ DB_TABLE_LOCATION + 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, _name TEXT, _address TEXT, _x REAL, _y REAL, _outtime INT, _type INT);");
		db.execSQL("CREATE TABLE "+ DB_TABLE_APPOINTMENT + 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, _name TEXT, _date INT, _time INT, _transport INT, _from INT, _fromName TEXT, _fromAdress TEXT, _fromX REAL, _fromY REAL, _to INT, _toName TEXT, _toAdress TEXT, _toX REAL, _toY REAL);");
		db.execSQL("CREATE TABLE "+ DB_TABLE_WEEKLY + 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, _name TEXT, _dayofweek INT, _time INT, _transport INT, _from INT, _fromName TEXT, _fromAdress TEXT, _fromX REAL, _fromY REAL, _to INT, _toName TEXT, _toAdress TEXT, _toX REAL, _toY REAL);");

		
		
//		value.put("_name",name);
//		value.put("_dayofweek", dayofweek);
//		value.put("_time", time);
//		value.put("_transport", transport);
//		value.put("_from", from);
//		value.put("_fromName", fromName);
//		value.put("_fromAdress", fromAdress);
//		value.put("_fromX", fromx);
//		value.put("_fromY", fromy);
//		value.put("_to", to);
//		value.put("_toName", toName);
//		value.put("_toAdress", toAdress);
//		value.put("_toX", tox);
//		value.put("_toY", toy);
		
		
		/*TEST CODE*/
//		ContentValues cv = new ContentValues();
//		
//		cv.put("_walkvel", 0);
//		db.insert(DB_TABLE_PERSONAL,null,cv);
//		
//		cv.clear();
//		cv.put("_name","집");
//		cv.put("_address","서울시 동작구 흑석동");
//		cv.put("_outtime",7);
//		cv.put("_type",1);
//		db.insert(DB_TABLE_LOCATION, null, cv);
//		
//		cv.put("_name","회사");
//		cv.put("_address","서울시 금천구 디지털로 10길 56");
//		cv.put("_outtime",15);
//		cv.put("_type",1);
//		db.insert(DB_TABLE_LOCATION, null, cv);
//		
//		cv.put("_name","서울역");
//		cv.put("_address","서울특별시 용산구 한강대로 405 서울역");
//		cv.put("_outtime",0);
//		cv.put("_type",1);
//		db.insert(DB_TABLE_LOCATION, null, cv);
//		
//		ContentValues cv2 = new ContentValues();
//		cv2.put("_name","생일");
//		cv2.put("_date",20151125);
//		cv2.put("_time",1230);
//		cv2.put("_transport",1);
//		cv2.put("_from",1);
//		cv2.put("_to",2);
//		db.insert(DB_TABLE_APPOINTMENT, null, cv2);
//	
//		cv2.put("_name","빼빼로");
//		cv2.put("_date",20151111);
//		cv2.put("_time",1820);
//		cv2.put("_transport",2);
//		cv2.put("_from",2);
//		cv2.put("_to",1);
//		db.insert(DB_TABLE_APPOINTMENT, null, cv2);
//		
//		ContentValues cv3 = new ContentValues();
//		cv3.put("_name","출근");
//		cv3.put("_dayofweek",1);
//		cv3.put("_time",1205);
//		cv3.put("_transport",1);
//		cv3.put("_from",1);
//		cv3.put("_to",2);
//		db.insert(DB_TABLE_WEEKLY, null, cv3);
//		
//		cv3.put("_name","영어 학원");
//		cv3.put("_dayofweek",1);
//		cv3.put("_time",0702);
//		cv3.put("_transport",2);
//		cv3.put("_from",1);
//		cv3.put("_to",2);
//		db.insert(DB_TABLE_WEEKLY, null, cv3);
		/*TEST CODE END*/
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_PERSONAL);
		db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_LOCATION);
		db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_APPOINTMENT);
		db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_WEEKLY);
		onCreate(db);
	}
}
