package com.example.mywaytest3;


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mywaytest3.adapter.NavDrawerListAdapter;
import com.example.mywaytest3.model.AppointmentManager;
import com.example.mywaytest3.model.CalendarController;
import com.example.mywaytest3.model.CalendarController.EventHandler;
import com.example.mywaytest3.model.CalendarController.EventInfo;
import com.example.mywaytest3.model.CalendarController.EventType;
import com.example.mywaytest3.model.LocationManager;
import com.example.mywaytest3.model.NavDrawerItem;
import com.example.mywaytest3.model.WeeklyManager;

public class MainActivity extends FragmentActivity implements EventHandler{
	public static DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	public static ActionBarDrawerToggle mDrawerToggle;


	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	public static ActionBar actionBar;

	private AppointmentManager appointmentManager = AppointmentManager.getInstance();
	private WeeklyManager weeklyManager = WeeklyManager.getInstance();
	private LocationManager locationManager = LocationManager.getInstance();
	private EventInfo event;
	private boolean dayView;
	boolean eventView;


	private CalendarController mController;

	public static Fragment fragments[] = new Fragment[5];
	public static Fragment fragmentDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragments[0] = new HomeFragment();
		fragments[1] = new WeeklyFragment();
		fragments[2] = new MonthByWeekFragment(System.currentTimeMillis(), false);
		fragments[3] = new PersonalSettingFragment();
		fragments[4] = new AccaountSettingFragment();
		fragmentDay = new DayFragment();

		appointmentManager.init(this);
		weeklyManager.init(this);
		locationManager.init(this);

		actionBar = getActionBar();

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));


		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(
				new ColorDrawable(getResources().getColor(android.R.color.transparent)));   

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				//getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		
		mController = CalendarController.getInstance(this);
        mController.registerEventHandler(R.id.cal_frame, (EventHandler) fragments[2]);
        
        mController.registerFirstEventHandler(0, this);
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	private void displayView(int position) {
		
		
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
		.replace(R.id.frame_container, fragments[position]).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	
	
	@Override
	public long getSupportedEventTypes() {
		return EventType.GO_TO | EventType.VIEW_EVENT | EventType.UPDATE_TITLE;
	}

	@Override
	public void handleEvent(EventInfo event) {
		if (event.eventType == EventType.GO_TO) {
			this.event = event;
//			dayView = true;
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				fragmentDay = new DayFragment(event.startTime.toMillis(true),1);
//				ft.replace(R.id.frame_container, fragmentDay).addToBackStack(null).commit();
			
			Intent i = new Intent(MainActivity.this, AppointmentRegistrationActivity.class);
			i.putExtra("mils", event.startTime.toMillis(true));
			
			startActivity(i);
				
				
		}if(event.eventType == EventType.VIEW_EVENT){
			//TODO do something when an event is clicked
					dayView = false;
					eventView = true;
					this.event = event;
//					FragmentTransaction ft = getFragmentManager().beginTransaction();
//					edit = new EditEvent(event.id);
//					ft.replace(R.id.cal_frame, edit).addToBackStack(null).commit();

			
		}
		
	}

	@Override
	public void eventsChanged() {
	
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
	}

}
