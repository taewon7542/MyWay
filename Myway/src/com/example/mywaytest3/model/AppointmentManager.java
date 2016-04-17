package com.example.mywaytest3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.mywaytest3.db.MyWayDBManager;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


public class AppointmentManager {
	private static AppointmentManager instance = new AppointmentManager();
	private ArrayList<AppointmentItem> aplist = new ArrayList<AppointmentItem>();	
	MyWayDBManager DBman;
	static int seq = 0;

	private AppointmentManager() {
		super();
	}
	public static AppointmentManager getInstance(){
		return instance;
	}

	public ArrayList<AppointmentItem> SearchKeyword(String key){
		ArrayList<AppointmentItem> retlist = new ArrayList<AppointmentItem>();	
		for(AppointmentItem ap : aplist){
			if(ap.getName().contains(key)){
				retlist.add(ap);
				continue;
			}
			LocationManager lm = LocationManager.getInstance();
			String addrfrom = lm.getLocationAddress(ap.getFrom());
			
			String addrto = lm.getLocationAddress(ap.getTo());
			
			String namefrom = lm.getLocationName(ap.getFrom());
			
			String nameto = lm.getLocationName(ap.getTo());
			
			if(addrfrom != null){
				if(addrfrom.contains(key)){
					retlist.add(ap);
				}
			}
			if(addrto != null){
				if(addrto.contains(key)){
					retlist.add(ap);
				}
			}
			if(namefrom != null){
				if(namefrom.contains(key)){
					retlist.add(ap);
				}
			}
			if(nameto != null){
				if(nameto.contains(key)){
					retlist.add(ap);
				}
			}
		}
		return retlist;

	}

	public void sort(){
		Collections.sort(aplist, comp);
	}
	
	public void init(Context context){
		
		DBman = MyWayDBManager.getInstance(context);
		Cursor c = DBman.searchAppointment();
		while(c.moveToNext()){
			int id = c.getInt(0);
			String name = c.getString(1);
			int date = c.getInt(2);
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
			getInstance().addItem(id, name, date, time, transport, from, fromName, fromAddress, fromX, fromY, to, toName, toAddress, toX, toY);
		}
	}
	
	public ArrayList<AppointmentItem> getList()
	{
		return aplist;
	}
	
	public void addItem(int id, String name, int date, int time, int transport,  int from, String fromName, String fromAddress, float fromx, float fromy
			, int to, String toName, String toAddress, float tox, float toy){
		AppointmentItem item = null;
		item = new AppointmentItem();
		item.setId(id);
		item.setDate(date);
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
		if(id > seq) seq = id;
		aplist.add(item);
	}
	
	public void addItem(String name, int date, int time, int transport,  int from, String fromName, String fromAddress, float fromx, float fromy
			, int to, String toName, String toAddress, float tox, float toy){
		AppointmentItem item = null;
		item = new AppointmentItem();
		seq++;
		item.setId(seq);
		item.setDate(date);
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
		aplist.add(item);
		

		DBman.insertAppointment(name, date, time, transport, from, fromName, fromAddress, fromx, fromy, to, toName, toAddress, tox, toy);
	}

	public void updateItem(int id, String name, int date, int time, int transport,  int from, String fromName, String fromAddress, float fromx, float fromy
			, int to, String toName, String toAddress, float tox, float toy){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				ap.setName(name);
				ap.setDate(date);
				ap.setTime(time);
				ap.setTransport(transport);
				ap.setFrom(from);
				ap.setTo(to);
			}
		}
	}

	public void deleteItem(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				aplist.remove(ap);
			}
		}
	}

	public String getAppointmentName(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				return ap.getName();
			}
		}
		return null;
	}

	public int getAppointmentDate(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				return ap.getDate();
			}
		}
		return 0;
	}

	public int getAppointmentTime(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				return ap.getTime();
			}
		}
		return 0;
	}

	public int getAppointmentTransport(int id){
		for(AppointmentItem w : aplist){
			if(w.getId() == id){
				return w.getTransport();
			}
		}
		return 0;
	}

	public int getAppointmentFrom(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				return ap.getFrom();
			}
		}
		return 0;
	}

	public int getAppointmentTo(int id){
		for(AppointmentItem ap : aplist){
			if(ap.getId() == id){
				return ap.getTo();
			}
		}
		return 0;
	}

	public void printlist(){
		for(AppointmentItem ap : aplist){
			Log.e("pap",ap.getId()+" "+ap.getName()+" "+ap.getDate()+" "+ap.getTime()+" "+ap.getTransport()+" "+ap.getFrom()+" "+ap.getTo()); 
		}
	}

	public int listsize(){
		return aplist.size();
	}

	public static Comparator<AppointmentItem> comp = new Comparator<AppointmentItem>() {

		@Override
		public int compare(AppointmentItem a1, AppointmentItem a2) {
			int day1 = a1.getDate();
			int day2 = a2.getDate();
			int time1 = a1.getTime();
			int time2 = a2.getTime();
			if((day1-day2) == 0){
				return time1 - time2;
			}
			return day1-day2;
		}};
}
