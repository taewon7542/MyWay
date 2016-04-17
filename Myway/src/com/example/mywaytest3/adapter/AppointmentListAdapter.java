package com.example.mywaytest3.adapter;

import java.util.ArrayList;

import com.example.mywaytest3.R;
import com.example.mywaytest3.model.AppointmentItem;
import com.example.mywaytest3.model.LocationManager;
import com.example.mywaytest3.model.NavDrawerItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppointmentListAdapter extends BaseAdapter {

	private LocationManager lm = LocationManager.getInstance();
	private Context context;
	private ArrayList<AppointmentItem> appointmentItems;
	
	public AppointmentListAdapter(Context context, ArrayList<AppointmentItem> items){
		this.context = context;
		this.appointmentItems = items;
	}

	@Override
	public int getCount() {
		return appointmentItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return appointmentItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_listcard, null);
        }
         
        TextView itemtvDateTime = (TextView) convertView.findViewById(R.id.itemtvDateTime);
        TextView itemtvLocation = (TextView) convertView.findViewById(R.id.itemtvLocation);
        TextView itemtvName = (TextView) convertView.findViewById(R.id.itemtvName);
        TextView itemtvTakingTime = (TextView) convertView.findViewById(R.id.itemtvTakingTime);
        
        itemtvName.setText(appointmentItems.get(position).getName());
        itemtvLocation.setText(appointmentItems.get(position).getToAddress());
        itemtvDateTime.setText(appointmentItems.get(position).getDate()/10000 + "/" + (appointmentItems.get(position).getDate()/100)%100 + "/" + appointmentItems.get(position).getDate()%100 + " " + String.format("%02d:%02d", appointmentItems.get(position).getTime()/100, appointmentItems.get(position).getTime()%100));
       // itemtvTakingTime.setText("예상 소요시간 : " + appointmentItems.get(position).getEstimatedTime() + "분");
        itemtvTakingTime.setText("");

     
        return convertView;
	}

}
