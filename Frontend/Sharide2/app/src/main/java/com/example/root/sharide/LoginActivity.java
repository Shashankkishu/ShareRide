package com.example.root.sharide;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private ViewPager mLoginViewPager;
    private ActionBar mLoginActionBar;
    private List<Fragment> mLoginFragments;
    private List<String> mLoginTabTitles;
    private SwipeTabsAdapter swipeTabsAdapter;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)this.findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        mLoginActionBar = getSupportActionBar();
        mLoginActionBar.setDisplayShowTitleEnabled(false);
        mLoginActionBar.hide();
        this.setTabs();
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

    public void setTabs(){

        this.mLoginTabTitles = new ArrayList<String>();
        this.mLoginFragments = new ArrayList<Fragment>();
        this.mLoginFragments.add(new SignInFragment());
        this.mLoginFragments.add(new SignUpFragment());
        this.mLoginTabTitles.add("Signin");
        this.mLoginTabTitles.add("Signup");
        this.swipeTabsAdapter = new SwipeTabsAdapter(getSupportFragmentManager(), this.mLoginFragments, this.mLoginTabTitles, this);
        this.mLoginViewPager= (ViewPager) this.findViewById(R.id.loginViewPager);
        this.mLoginViewPager.setAdapter(swipeTabsAdapter);
        this.mTabs = (SlidingTabLayout)this.findViewById(R.id.loginSwipeTabs);
        this.mTabs.setDistributeEvenly(true);
        this.mTabs.setViewPager(mLoginViewPager);

    }
}
