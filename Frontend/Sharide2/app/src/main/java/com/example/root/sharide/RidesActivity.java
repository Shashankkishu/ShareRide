package com.example.root.sharide;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RidesActivity extends AppCompatActivity implements  View.OnClickListener {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout headerFragment;
    private FloatingActionButton addNewRide;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

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
        RideGet ride1 = new RideGet();

        ride1.setUserName("Shashank Bhushan");
        ride1.setUserTime("11:45 AM");
        ride1.setUserDate("12/07/2015");
        ride1.setOrigin("Hostel 16, IIT Powai, Mumbai");
        ride1.setDestination("Oberoi , Estate Garden , Mumbai");

        RideGet ride2 = new RideGet();


        RideGet ride3 = new RideGet();
        ride3.setUserName("Sidharth Dangi");
        ride3.setUserTime("05:20 PM");
        ride3.setUserDate("03/07/2015");
        ride3.setOrigin("Gallaria , Heernandani Gardens, Mumbai");
        ride3.setDestination("R City Mall, Ghatkopar West, Mumbai");

        ride2.setUserName("R sundrarajan");
        ride2.setUserTime("10:30 AM");
        ride2.setUserDate("24/07/2015");
        ride2.setOrigin("pune, Maharashtra");
        ride2.setDestination("Kanpur , Uttar Pradesh");

        List<RideGet> rideGetList = new ArrayList<>();
        rideGetList.add(ride1);
        rideGetList.add(ride2);
        rideGetList.add(ride3);
        rideGetList.add(ride1);
        rideGetList.add(ride2);
        rideGetList.add(ride3);

        RidesListAdapter ridesListAdapter = new RidesListAdapter(rideGetList);
        mRecyclerView.setAdapter(ridesListAdapter);
        headerFragment = (RelativeLayout) this.findViewById(R.id.drawer_fragment);
        mTitle = "test";
        mPlanetTitles = new String[]{"Requests", "My Rides", "Show Autos"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_layout_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.abc_btn_check_to_on_mtrl_015,  /* nav drawer icon to replace 'Up' caret */
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

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
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
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(headerFragment);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), RideDedicatedPage.class));

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}
