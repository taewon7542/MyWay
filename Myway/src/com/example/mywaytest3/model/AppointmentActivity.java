package com.example.mywaytest3.model;
//package com.lg.sw26a2.myway.model;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import com.lg.sw26a2.myway.R;
//import com.lg.sw26a2.myway.db.MyWayDBManager;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.database.Cursor;
//import android.location.Address;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class AppointmentActivity extends Activity {
//	//TEST CODE
//	//private ListView apListView = null;
//	//private ListViewAdapter apAdapter = null;
//	//private ArrayList<AppointmentItem> mListData = new ArrayList<AppointmentItem>();
//	LocationManager lm;
//	WeeklyManager wm;
//	AppointmentManager am;
//	MyWayDBManager DBman;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		//setContentView(R.layout.activity_appointment);
//		
//		ArrayList<AppointmentItem> retlist = new ArrayList<AppointmentItem>();	
//		
//		lm = LocationManager.getInstance();
//		wm = WeeklyManager.getInstance();
//		am = AppointmentManager.getInstance();
//		
//		am.addItem(1,"meeting", 20150316, 1, 1, 0, 1);
//		am.addItem(2,"eunbi2", 20151205, 3, 4, 5, 0);
//		am.addItem(2,"daily", 20151111, 6, 7, 0, 0);
//		wm.addItem(1, "meeting", 2, 1, 1, 1, 1);
//		wm.addItem(2, "meeting2", 2, 3, 3, 3, 3);
//		lm.addItem(1, "office", "gasan", 3, 1, 4, 1);
//		lm.addItem(2, "office2", "gasan2", 4, 14, 4, 1);
//		lm.addItem(3, "office3", "home", 4, 14, 4, 1);
//		lm.addItem(4, "office4", "gasan4", 4, 14, 4, 1);
//
//		lm.printlist();
//		wm.printlist();
//		am.printlist();
//		retlist = am.SearchKeyword("gasan");
//		for(AppointmentItem ap : retlist){
//			Log.e("search",ap.getId()+" "+ap.getName()+" "+ap.getDate()+" "+ap.getTime()+" "+ap.getTransport()+" "+ap.getFrom()+" "+ap.getTo()); 
//		}
////		DBman = MyWayDBManager.getInstance(this);
////		apListView = (ListView) findViewById(R.id.aplistview);
////		apAdapter = new ListViewAdapter(this);
////		apListView.setAdapter(apAdapter);
////		
////		Cursor ca = DBman.searchAppointment();
////		while(ca.moveToNext()){
////			int id = ca.getInt(0);
////			String name = ca.getString(1);
////			int date = ca.getInt(2);
////			int time = ca.getInt(3);
////			int transport = ca.getInt(4);
////			int from = ca.getInt(5);
////			int to = ca.getInt(6);
////			int location = 0;
////			Log.e("tag", "Appointment "+id+" "+name+" "+date+" "+time+" "+transport+" "+from+" "+to+"\n");
////			
////			apAdapter.addItem(id, date, time, name, location);
////			apAdapter.sort();
////			apAdapter.dataChange();
////			
////		}
////				
////	}
////	private class ViewHolder{
////		public TextView apdate;
////		public TextView aptime;
////		public TextView apname;
////		public TextView aplocation;
////	}
////	
////	public class ListViewAdapter extends BaseAdapter{
////		private Context mContext = null;
////		
////		public void addItem(int id, int date, int time, String name, int location){
////			AppointmentItem addInfo = null;
////			addInfo = new AppointmentItem();
////			addInfo.id = id;
////			addInfo.date = date;
////			addInfo.time = time;
////			addInfo.name = name;
////			addInfo.location = location;
////			mListData.add(addInfo);
////		}
////		
////		public void remove(int position){
////			mListData.remove(position);
////			dataChange();
////		}
////		
////		public void sort(){
////			Collections.sort(mListData,AppointmentItem.comp);
////			dataChange();
////		}
////		
////		public void dataChange(){
////		apAdapter.notifyDataSetChanged();
////			
////		}
////		
////		public ListViewAdapter(Context mContext) {
////			super();
////			this.mContext = mContext;
////		}
////
////		@Override
////		public int getCount() {
////			
////			return mListData.size();
////		}
////
////		@Override
////		public Object getItem(int position) {
////			return mListData.get(position);
////		}
////
////		@Override
////		public long getItemId(int position) {
////			return position;
////		}
////
////		@Override
////		public View getView(int position, View convertView, ViewGroup parent) {
////			ViewHolder holder;
////			if(convertView == null){
////				holder = new ViewHolder();
////				
////				LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////				convertView = inflater.inflate(R.layout.appoint,null);
////				
////				holder.apdate = (TextView) convertView.findViewById(R.id.apdate);
////				holder.aplocation = (TextView) convertView.findViewById(R.id.aplocation);
////				holder.apname = (TextView) convertView.findViewById(R.id.apname);
////				holder.aptime = (TextView) convertView.findViewById(R.id.aptime);
////				convertView.setTag(holder);
////			}else{
////				holder = (ViewHolder) convertView.getTag();
////			}
////			
////			AppointmentItem mData = mListData.get(position);
////			
////			holder.apdate.setText(mData.date);
////			holder.aplocation.setText(mData.location);
////			holder.apname.setText(mData.name);
////			holder.aptime.setText(mData.time);
////			return convertView;
////		}
////		
//	}
//}
