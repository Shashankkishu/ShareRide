package com.example.root.sharide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 26/7/15.
 */

public class MyRides extends AppCompatActivity{
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.rideGetList);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

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
    }

}
