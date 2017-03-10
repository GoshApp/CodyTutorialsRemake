package com.app.codytutorials;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.app.codytutorials.adapter.TabsFragmentAdapter;


public class ActivityTabs extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_tabs;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;


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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.open_main, R.anim.close_next);
            }
        });
        toolbar.setTitle(R.string.title_toolbar_java);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_player);
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
        super.onBackPressed();
        Intent intent = new Intent(ActivityTabs.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }//onBackPressed

}
