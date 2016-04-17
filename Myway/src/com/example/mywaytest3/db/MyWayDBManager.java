package com.example.mywaytest3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyWayDBManager {
	
	private static MyWayDBManager instance = new MyWayDBManager();

	SQLiteDatabase db;
	
	private MyWayDBManager() {
	}
	
	public static MyWayDBManager getInstance(Context context){
		instance.db = (new MyWayDBHelper(context)).getWritableDatabase();
		return instance;
	}
	
	public void insertWeekSchedule(String name, int dayofweek, int time, int transport,  int from, String fromName, String fromAdress, float fromx, float fromy
			, int to, String toName, String toAdress, float tox, float toy)
	{
		ContentValues value = new ContentValues();
		value.put("_name",name);
		value.put("_dayofweek", dayofweek);
		value.put("_time", time);
		value.put("_transport", transport);
		value.put("_from", from);
		value.put("_fromName", fromName);
		value.put("_fromAdress", fromAdress);
		value.put("_fromX", fromx);
		value.put("_fromY", fromy);
		value.put("_to", to);
		value.put("_toName", toName);
		value.put("_toAdress", toAdress);
		value.put("_toX", tox);
		value.put("_toY", toy);
		db.insert(MyWayDBHelper.DB_TABLE_WEEKLY, null, value);
	}
	
	public void insertAppointment(String name,int date, int time, int transport, int from, String fromName, String fromAdress, float fromx, float fromy
			, int to, String toName, String toAdress, float tox, float toy)
	{
		ContentValues value = new ContentValues();
		value.put("_name",name);
		value.put("_date", date);
		value.put("_time", time);
		value.put("_transport", transport);
		value.put("_from", from);
		value.put("_fromName", fromName);
		value.put("_fromAdress", fromAdress);
		value.put("_fromX", fromx);
		value.put("_fromY", fromy);
		value.put("_to", to);
		value.put("_toName", toName);
		value.put("_toAdress", toAdress);
		value.put("_toX", tox);
		value.put("_toY", toy);
		db.insert(MyWayDBHelper.DB_TABLE_APPOINTMENT, null, value);
	}
	
	public void insertLocation(String name, String address, float x, float y, int outtime, int type)
	{
		ContentValues value = new ContentValues();
		value.put("_name", name);
		value.put("_address", address);
		value.put("_x", x);
		value.put("_y", y);
		value.put("_outtime", outtime);
		value.put("_type", type);
		db.insert(MyWayDBHelper.DB_TABLE_LOCATION, null, value);
	}
	
	public void updatePersonal(int _id, int walkvel)
	{
		ContentValues row = new ContentValues();
		row.put("_walkvel",walkvel);
		db.update(MyWayDBHelper.DB_TABLE_PERSONAL, row, "_id = ?", new String[]{_id+""});
	}	
	
	
	public void updateWeekSchdule(int _id, int dayofweek, int time, int transport,  int from, int to)
	{
		ContentValues row = new ContentValues();
		row.put("_dayofweek", dayofweek);
		row.put("_time", time);
		row.put("_transport", transport);
		row.put("_from", from);
		row.put("_to", to);
		db.update(MyWayDBHelper.DB_TABLE_WEEKLY, row, "_id = ?", new String[]{_id+""});
	}
	
	public void updateAppointment(int _id, int date, int time, int transport,  int from, int to)
	{
		ContentValues row = new ContentValues();
		row.put("_date", date);
		row.put("_time", time);
		row.put("_transport", transport);
		row.put("_from", from);
		row.put("_to", to);
		db.update(MyWayDBHelper.DB_TABLE_WEEKLY, row, "_id = ?", new String[]{_id+""});
	}	
	
	public void updateLocation(int _id, String name, String address, float x, float y, int outtime, int type)
	{
		ContentValues row = new ContentValues();
		row.put("_name", name);
		row.put("_address",address);
		row.put("_x", x);
		row.put("_y", y);
		row.put("_outtime", outtime);
		row.put("_type", type);
		db.update(MyWayDBHelper.DB_TABLE_LOCATION, row, "_id = ?", new String[]{_id+""});
	}
	
	public void deleteWeekSchdule(int _id)
	{
		db.delete(MyWayDBHelper.DB_TABLE_WEEKLY, "_id = ?", new String[]{_id+""});
	}
	
	public void deleteLocation(int _id)
	{
		db.delete(MyWayDBHelper.DB_TABLE_LOCATION, "_id = ?", new String[]{_id+""});
	}		
	
	public Cursor searchWeekSchedule()
	{
		Cursor c = db.query(MyWayDBHelper.DB_TABLE_WEEKLY,new String[]{"_id","_name","_dayofweek","_time","_transport","_from","_fromName", "_fromAdress", "_fromX", "_fromY", "_to", "_toName", "_toAdress", "_toX", "_toY"},null,null,null,null,null);
		return c;
	}
	public Cursor searchAppointment()
	{
		Cursor c = db.query(MyWayDBHelper.DB_TABLE_APPOINTMENT,new String[]{"_id","_name","_date","_time","_transport","_from","_fromName", "_fromAdress", "_fromX", "_fromY", "_to", "_toName", "_toAdress", "_toX", "_toY"},null,null,null,null,null);
		return c;
	}
	public Cursor searchLocation()
	{
		Cursor c = db.query(MyWayDBHelper.DB_TABLE_LOCATION,new String[]{"_id","_name","_address","_x","_y","_outtime","_type"},null,null,null,null,null);
		return c;
	}

}
