package com.app.codytutorials;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ActivityTabs extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    final String LOG_TAG = "myLogs"; // добавлем логи
    private ActionBar actionBar;
    private AdapterProfile tabPagerAdapter;
    private String[] tabs = { "Плейлист", "Методичка" };
    // массив для табов

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipetab); // подгружаем хml для табов


        viewPager = (ViewPager) findViewById(R.id.pager);
        tabPagerAdapter = new AdapterProfile(getSupportFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);

        actionBar = getActionBar();
        actionBar.setTitle("Как будем учиться"); // заголовок

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * on swipe select the respective tab
             * */
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                // в зависимости от позиции
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        Log.d(LOG_TAG, "reselected tab: " + tab.getText());
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // при переходе на новый таб, присваеваем  pos чтобы обновить даннвые
        viewPager.setCurrentItem(tab.getPosition());
        Log.d(LOG_TAG, "selected tab: " + tab.getText());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        Log.d(LOG_TAG, "unselected tab: " + tab.getText());
    }
    // по возврату назад
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // по кнопке назад запускаем анимацию
        overridePendingTransition (R.anim.open_main, R.anim.close_next);
    }
}
