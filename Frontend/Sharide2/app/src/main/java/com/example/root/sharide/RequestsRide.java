package com.example.root.sharide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 25/7/15.
 */
public class RequestsRide extends AppCompatActivity{
    Context mContext;
    private ViewPager mRequestViewPager;
    private ActionBar mRequestActionBar;
    private List<Fragment> mRequestFragmet;
    private List<String> mRequestTabTitles;
    private SwipeTabsAdapter RequestswipeTabsAdapter;
    private SlidingTabLayout mTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_request);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setLogoDescription("Description");
        toolbar.setTitle("Tittle");
        setSupportActionBar(toolbar);
//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.my_awesome_toolbar);
//        setSupportActionBar(toolbar);
        mRequestActionBar = getSupportActionBar();
        mRequestActionBar.setDisplayShowTitleEnabled(false);
        mRequestActionBar.hide();
        this.setTab();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setTab() {
        this.mRequestTabTitles = new ArrayList<String>();
        this.mRequestFragmet = new ArrayList<Fragment>();
        this.mRequestFragmet.add(new RecievedRequestFragment());
        this.mRequestFragmet.add(new SentRequestFragment());
        this.mRequestTabTitles.add("Recieved");
        this.mRequestTabTitles.add("Sent");
        this.RequestswipeTabsAdapter = new SwipeTabsAdapter(getSupportFragmentManager(), this.mRequestFragmet, this.mRequestTabTitles, this);
        this.mRequestViewPager= (ViewPager) this.findViewById(R.id.requestsViewPager);
        this.mRequestViewPager.setAdapter(RequestswipeTabsAdapter);
        this.mTabs = (SlidingTabLayout)this.findViewById(R.id.requestsSwipeTabs);
        this.mTabs.setDistributeEvenly(true);
        this.mTabs.setViewPager(mRequestViewPager);

    }


}