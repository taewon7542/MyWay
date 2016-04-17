package com.example.mywaytest3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.mywaytest3.db.MyWayDBManager;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


public class WeeklyManager {
	private static WeeklyManager instance = new WeeklyManager();
	private ArrayList<WeeklyItem> wlist = new ArrayList<WeeklyItem>();	
	static MyWayDBManager DBman;
	private WeeklyManager() {
		super();
	}
	
	public static WeeklyManager getInstance(){
		return instance;
	}

	public void sort(){
		Collections.sort(wlist, comp);
	}
	
	int seq=0;
	
	public void init(Context context){
		//get database to store in manager list
		
		DBman = MyWayDBManager.getInstance(context);
		Cursor c = DBman.searchWeekSchedule();
		while(c.moveToNext()){
			int id = c.getInt(0);
			String name = c.getString(1);
			int dayofweek = c.getInt(2);
			int time = c.getInt(3);
			int transport = c.getInt(4);
			int from = c.getInt(5);
			String fromName = c.getString(6);
			String fromAddress = c.getString(7);
			float fromX = c.getFloat(8);
			float fromY = c.getFloat(9);
			int to = c.getInt(10);
			String toName = c.getString(11);
			String toAddress = c.getString(12);
			float toX = c.getFloat(13);
			float toY = c.getFloat(14);
			getInstance().addItem(id, name, dayofweek, time, transport, from, fromName, fromAddress, fromX, fromY, to, toName, toAddress, toX, toY);
		}
	}
	
	
	public ArrayList<WeeklyItem> getList()
	{
		return wlist;
	}
	
	public void addItem(int id, String name, int dayofweek, int time, int transport,  int from, String fromName, String fromAddress, float fromx, float fromy
			, int to, String toName, String toAddress, float tox, float toy){
		WeeklyItem item = null;
		item = new WeeklyItem();
		item.setId(id);
		item.setDayofweek(dayofweek);
		item.setName(name);
		item.setTime(time);
		item.setTransport(transport);
		item.setFrom(from);
		item.setFromName(fromName);
		item.setFromAddress(fromAddress);
		item.setFromX(fromx);
		item.setFromY(fromy);
		item.setTo(to);
		item.setToName(toName);
		item.setToAddress(toAddress);
		item.setToX(tox);
		item.setToY(toy);		
		
		wlist.add(item);
		
		if(id > seq) seq=id;
	}
	
	public void addItem(String name, int dayofweek, int time, int transport,  int from, String fromName, String fromAddress, float fromx, float fromy
			, int to, String toName, String toAddress, float tox, float toy){
		WeeklyItem item = null;
		item = new WeeklyItem();
		seq++;
		item.setId(seq);
		item.setDayofweek(dayofweek);
		item.setName(name);
		item.setTime(time);
		item.setTransport(transport);
		item.setFrom(from);
		item.setFromName(fromName);
		item.setFromAddress(fromAddress);
		item.setFromX(fromx);
		item.setFromY(fromy);
		item.setTo(to);
		item.setToName(toName);
		item.setToAddress(toAddress);
		item.setToX(tox);
		item.setToY(toy);		
		
		wlist.add(item);
		
		DBman.insertWeekSchedule(name, dayofweek, time, transport, from, fromName, fromAddress, fromx, fromy, to, toName, toAddress, tox, toy);
	}
	
	
	public void updateItem(int id, String name, int dayofweek, int time, int transport, int from, int to){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				w.setName(name);
				w.setDayofweek(dayofweek);
				w.setTime(time);
				w.setTransport(transport);
				w.setFrom(from);
				w.setTo(to);
			}
		}
	}
	
	public void deleteItem(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				wlist.remove(w);
			}
		}
	}
	
	public String getWeeklyName(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getName();
			}
		}
		return null;
	}
	
	public int getWeeklydayofweek(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getDayofweek();
			}
		}
		return 0;
	}
	
	public int getWeeklyTime(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getTime();
			}
		}
		return 0;
	}
	
	public int getWeeklyTransport(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getTransport();
			}
		}
		return 0;
	}
	
	public int getWeeklyFrom(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getFrom();
			}
		}
		return 0;
	}
	
	public int getWeeklyTo(int id){
		for(WeeklyItem w : wlist){
			if(w.getId() == id){
				return w.getTo();
			}
		}
		return 0;
	}
	
	public void printlist(){
		for(WeeklyItem w : wlist){
			Log.e("pweek",w.getId()+" "+w.getName()+" "+w.getDayofweek()+" "+w.getTime()+" "+w.getTransport()+" "+w.getFrom()+" "+w.getTo()); 
		}
	}
	
	public int listsize(){
		return wlist.size();
	}
	
	public static Comparator<WeeklyItem> comp = new Comparator<WeeklyItem>() {

			@Override
			public int compare(WeeklyItem a1, WeeklyItem a2) {
				int day1 = a1.getDayofweek();
				int day2 = a2.getDayofweek();
				int time1 = a1.getTime();
				int time2 = a2.getTime();
				if((day1-day2) == 0){
					return time1 - time2;
				}
				return day1-day2;
			}};
}
