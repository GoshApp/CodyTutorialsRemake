package com.app.codytutorials.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.codytutorials.fragment.AndroidBuildingMusicPlayerFragment;
import com.app.codytutorials.fragment.AbstractTabFragment;
import com.app.codytutorials.fragment.ManualsFragment;

import java.util.HashMap;
import java.util.Map;



public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap();
    }// TabsFragmentAdapter

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }// getItem

    @Override
    public CharSequence getPageTitle(int position) {

        return tabs.get(position).getTitle();
    }// getPageTitle

    @Override
    public int getCount() {
        return tabs.size();
    }// getCount

    private void initTabsMap() {
        tabs = new HashMap<>();
        tabs.put(0, ManualsFragment.getInstance(context));
        tabs.put(1, AndroidBuildingMusicPlayerFragment.getInstance(context));

    }// initTabsMap
}
