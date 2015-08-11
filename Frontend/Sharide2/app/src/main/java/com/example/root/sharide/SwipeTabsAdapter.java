package com.example.root.sharide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by root on 1/6/15.
 */
public class SwipeTabsAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> tabTitles;
    Context context;

    public SwipeTabsAdapter(FragmentManager fm){
        super(fm);
    }

    public SwipeTabsAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabTitles, Context context){
        super(fm);

        this.fragments = fragments;
        this.tabTitles = tabTitles;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i){

        return Fragment.instantiate(this.context, (fragments.get(i).getClass()).getName());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public int getCount(){

        return fragments.size();
    }
}
