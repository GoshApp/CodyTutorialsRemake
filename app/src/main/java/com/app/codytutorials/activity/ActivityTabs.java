package com.app.codytutorials.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.app.codytutorials.Constans;
import com.app.codytutorials.R;
import com.app.codytutorials.adapter.TabsFragmentAdapter;
import com.app.codytutorials.fragment.AndroidBuildingMusicPlayerFragment;


public class ActivityTabs extends AppCompatActivity {
    AndroidBuildingMusicPlayerFragment playerFragment;
    private static final int LAYOUT = R.layout.activity_tabs;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;
    Intent intentPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initTabs();

    }//onCreate

    // инициализация Toolbar
    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_bg));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerFragment.isUpdate = false;
                finish();
                overridePendingTransition(R.anim.open_main, R.anim.close_next);
            }
        });
        toolbar.setTitle(R.string.title_toolbar_java);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                //noinspection SimplifiableIfStatement
                switch(id) {
                    case R.id.settings:
                        intentPref = new Intent(getApplicationContext(), PreferencesActivity.class);
                        startActivity(intentPref);
                        overridePendingTransition(R.anim.open_next, R.anim.close_next);
                        break;
                    case R.id.about:
                        intentPref = new Intent(getApplicationContext(), ActivityAbout.class);
                        startActivity(intentPref);
                        overridePendingTransition(R.anim.open_next, R.anim.close_next);
                        break;
                    case R.id.rate_app:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                        break;
                    case R.id.more_app:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.more_apps))));
                        break;
                }
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.main);

    }// initToolbar

    // инициализация Tabs
    private void initTabs() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Constans.TAB_ONE);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }// initTabs

    @Override
    public void onBackPressed() {
//        playerFragment.mp.release();
//        AndroidBuildingMusicPlayerFragment.isUpdate = false;
        super.onBackPressed();
        Intent intent = new Intent(ActivityTabs.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
        finish();
    }//onBackPressed

}
