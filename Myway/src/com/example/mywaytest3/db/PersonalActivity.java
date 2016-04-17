package com.example.mywaytest3.db;
//package com.lg.sw26a2.myway.db;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class PersonalActivity extends Activity {
//	ListView FavoriteList = null;
//	ListViewAdapter mAdapter = null;
//	
//	TextView walkvel;
//	Button addLocation;
//	Button regpersonal;
//	AlertDialog levelDialog;
//	AlertDialog.Builder builder;
//	SQLiteDatabase db;
//	
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_personal);
//		walkvel = (TextView) findViewById(R.id.walkvel);
//		addLocation = (Button) findViewById(R.id.addLocation);
//		regpersonal = (Button) findViewById(R.id.regpersonal);
//		FavoriteList = (ListView) findViewById(R.id.FavoriteList);
//		
//		mAdapter = new ListViewAdapter(this);
//		FavoriteList.setAdapter(mAdapter);
//		
//		mAdapter.addItem("집","가산동",5);
//		
//		
//		db = (new MyWayDBHelper(this)).getWritableDatabase();
//		Cursor c = db.query(MyWayDBHelper.DB_TABLE_PERSONAL, new String[]{"_id","walkvel","location"},null,null,null,null,null);
//
//		// Strings to Show In Dialog with Radio Buttons
//		final CharSequence[] items = {" FAST"," USAUL "," SLOW "};
//
//		// Creating and Building the Dialog 
//		builder = new AlertDialog.Builder(this);
//		builder.setTitle("걷는 속도를 설정해주세요");
//		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int item) {
//				
//				switch(item){
//				
//				case 0:
//
//					walkvel.setText(" FAST");
//
//					break;
//				case 1:
//					walkvel.setText(" USAUL");
//					break;
//				case 2:
//					walkvel.setText(" SLOW");
//					break;
//
//				}
//				levelDialog.dismiss();    
//			}
//		});
//
//
//		View.OnClickListener listener = new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(v.getId()==R.id.walkvel){
//					levelDialog = builder.create();
//					levelDialog.show();
//				}
//				if(v.getId()==R.id.regpersonal){
//					//put the DB
//					ContentValues value = new ContentValues();
//					value.put("walkvel", walkvel.getText().toString());
//					//value.put("location", loc);
//					db.insert(MyWayDBHelper.DB_TABLE_PERSONAL, null, value);
//				}
//			}
//		};
//		walkvel.setOnClickListener(listener);
//
//		//put the DB
//		//ContentValues value = new ContentValues();
//
//
//	}
//	private class ViewHolder{
//		public TextView Locname;
//		public TextView LocDetail;
//		public TextView LocTime;
//	}
//
//	public class ListViewAdapter extends BaseAdapter {
//		private Context mContext = null;
//		private ArrayList<ListData> mListData = new ArrayList<ListData>();
//		
//		public ListViewAdapter(Context mContext){
//			super();
//			this.mContext = mContext;
//		}
//		
//		public void addItem(String mTitle,String mLoc, int mTime){
//			ListData addInfo = null;
//			addInfo = new ListData();
//			addInfo.mTitle = mTitle;
//			addInfo.mLoc = mLoc;
//			addInfo.mTime = mTime;
//			
//			mListData.add(addInfo);
//		}
//
//		public void remove(int position){
//			mListData.remove(position);
//			dataChange();
//		}
//		
//		public void dataChange(){
//			mAdapter.notifyDataSetChanged();
//		}
//		
//		@Override
//		public int getCount() {
//			return mListData.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return mListData.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder holder;
//			if(convertView == null){
//				holder = new ViewHolder();
//				
//				LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//				convertView = inflater.inflate(R.layout.item,null);
//				
//				holder.Locname = (TextView) convertView.findViewById(R.id.Locname);
//				holder.LocDetail = (TextView) convertView.findViewById(R.id.LocDetail);
//				holder.LocTime = (TextView) convertView.findViewById(R.id.LocTime);
//				convertView.setTag(holder);
//			}else{
//				holder = (ViewHolder) convertView.getTag();
//			}
//			
//			ListData mData = mListData.get(position);
//			
//			holder.Locname.setText(mData.mTitle);
//			holder.LocDetail.setText(mData.mLoc);
//			holder.LocTime.setText(mData.mTime);
//			return convertView;
//		}
//		
//	}
//
//}
