package com.example.root.sharide;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RidesActivity extends AppCompatActivity implements  View.OnClickListener {

    private String[] mLeftDraweritems;
    private DrawerLayout mDrawerLayout,mDrawerLayoutRight;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle,mDrawerToggleRight;
    private RelativeLayout headerFragment,headerFragmentright;
    private FloatingActionButton addNewRide;
    Spinner mOrigin , mEnd;
    TimePicker mTimePicker;
    private TextView mtimePicker,mdatePicker;
    final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR),mMonth = c.get(Calendar.MONTH),mDay = c.get(Calendar.DAY_OF_MONTH),mHour = c.get(Calendar.HOUR_OF_DAY),mMinute = c.get(Calendar.MINUTE);

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
         toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mOrigin = (Spinner)findViewById(R.id.originSpinner);//to set the starting point of the journey .
        ArrayAdapter<CharSequence> adapterorigin = ArrayAdapter.createFromResource(this,
                R.array.Stops1, android.R.layout.simple_spinner_item);
        adapterorigin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOrigin.setAdapter(adapterorigin);


        mEnd = (Spinner)findViewById(R.id.endSpinner);// to set the end point of the journey .
        ArrayAdapter<CharSequence> adapterend = ArrayAdapter.createFromResource(this,
                R.array.Stops2,android.R.layout.simple_spinner_item);
        adapterend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEnd.setAdapter(adapterend);


        mdatePicker = (TextView)findViewById(R.id.datePicker);//to set eh date of journey .
        mdatePicker.setOnClickListener(this);

        mtimePicker=(TextView)findViewById(R.id.timePicker);// to pick a time of the starting of the journey .
        mtimePicker.setOnClickListener(this);

        addNewRide = (FloatingActionButton) findViewById(R.id.shareRide);
        addNewRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RidesActivity.this, NewRideActivity_shashank.class));
            }
        });

        mRecyclerView = (RecyclerView) this.findViewById(R.id.rideGetList);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setOnClickListener(this);
//        RideGet ride1 = new RideGet();
//
//        ride1.setUserName("Shashank Bhushan");
//        ride1.setUserTime("11:45 AM");
//        ride1.setUserDate("12/07/2015");
//        ride1.setOrigin("Hostel 16, IIT Powai, Mumbai");
//        ride1.setDestination("Oberoi , Estate Garden , Mumbai");
//
//        RideGet ride2 = new RideGet();
//
//
//        RideGet ride3 = new RideGet();
//        ride3.setUserName("Sidharth Dangi");
//        ride3.setUserTime("05:20 PM");
//        ride3.setUserDate("03/07/2015");
//        ride3.setOrigin("Gallaria , Heernandani Gardens, Mumbai");
//        ride3.setDestination("R City Mall, Ghatkopar West, Mumbai");
//
//        ride2.setUserName("R sundrarajan");
//        ride2.setUserTime("10:30 AM");
//        ride2.setUserDate("24/07/2015");
//        ride2.setOrigin("pune, Maharashtra");
//        ride2.setDestination("Kanpur , Uttar Pradesh");

        List<RidePost> rideGetList = new ArrayList<>();
//        rideGetList.add(ride1);
//        rideGetList.add(ride2);
//        rideGetList.add(ride3);
//        rideGetList.add(ride1);
//        rideGetList.add(ride2);
//        rideGetList.add(ride3);
        AppClient.getrides(new AppClient.INetworkResponse<GetRidesModel>() {
            Context mContext;

            @Override
            public void onSuccess(GetRidesModel data) {
                RidesListAdapter ridesListAdapter = new RidesListAdapter(data.getRides());
                mRecyclerView.setAdapter(ridesListAdapter);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getBaseContext(), "Can not create account, Please try again.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        headerFragment = (RelativeLayout) this.findViewById(R.id.drawer_fragment);
        headerFragmentright = (RelativeLayout) this.findViewById(R.id.drawer_fragmentright);
        mTitle = "RIDES";
        mLeftDraweritems = new String[]{"Requests", "My Rides", "Show Autos"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayoutRight = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_layout_item, mLeftDraweritems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
            }
        };
        mDrawerToggleRight = new ActionBarDrawerToggle(this, mDrawerLayoutRight,
                R.drawable.ic_drawer,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close ) {

            @Override
            public boolean onOptionsItemSelected(MenuItem menu) {
                if (menu != null && menu.getItemId() == R.id.filter) {
                    if (mDrawerLayoutRight.isDrawerOpen(Gravity.RIGHT)) {
                        mDrawerLayoutRight.closeDrawer(Gravity.RIGHT);
                    } else {
                        mDrawerLayoutRight.openDrawer(Gravity.RIGHT);
                    }
                }
                return false;
            }
        };
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayoutRight.setDrawerListener(mDrawerToggleRight);

        getSupportActionBar().setTitle("Test");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rides, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
        mDrawerToggleRight.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
        mDrawerToggleRight.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (mDrawerToggleRight.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        switch (position) {
            case 0:
            startActivity(new Intent(getApplicationContext(), RequestsRide.class));
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), MyRides.class));
                break;
            case 2:
                Toast.makeText(this, "Contacts", Toast.LENGTH_SHORT).show();
                break;
        }
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mLeftDraweritems[position]);
        mDrawerLayout.closeDrawer(headerFragment);
//        mDrawerListright.setItemChecked(position, true);
//        setTitle(mRightDraweritems[position]);
        mDrawerLayoutRight.closeDrawer(headerFragmentright);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public void onClick(View v) {
        if (v == mdatePicker) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            mdatePicker.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        else if (v == mtimePicker) {
            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            CustomTimePickerDialog tpd = new CustomTimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            // Display Selected time in textbox
                            if(minute != 0)
                                mtimePicker.setText(hourOfDay + " : " + minute);
                            else
                                mtimePicker.setText(hourOfDay + " : " + minute+"0");

                        }
                    }, mHour, mMinute, false);
            tpd.show();

        }
        else
        startActivity(new Intent(getApplicationContext(), RideDedicatedPage.class));

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}
