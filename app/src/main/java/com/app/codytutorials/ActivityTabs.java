package com.app.codytutorials;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

public class ActivityTabs extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    final String LOG_TAG = "myLogs";                     // добавлем логи
    public  static int buttonID;                         // переменная для иницциализации переменной в класе AdapterProfile
    private ActionBar actionBar;                         // создаем ActionBar
    private AdapterProfile tabPagerAdapter;              // создаем адаптер
    private String[] tabs = { "Плейлист", "Методичка" }; // подставляем названия и авт создается количество табов
                                                         // массив для табов

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipetab); // подгружаем хml для табов


        viewPager = (ViewPager) findViewById(R.id.pager);   // создаем  страницу

        tabPagerAdapter = new AdapterProfile(getSupportFragmentManager()); // вызов адаптера!!! Важно!
        viewPager.setAdapter(tabPagerAdapter);                             // подключаем адаптер с нужными параметрами

        actionBar = getActionBar();                     // заголовок
        actionBar.setTitle("Как будем учиться?");

        getActionBar().setDisplayHomeAsUpEnabled(true); // добавляем иконку назад на ActionBar
        getActionBar().setHomeButtonEnabled(true);      // делаем иконку кликабельной
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }


         // Следим за изминениями табов и ставим id позиции, для передачи в адаптер и зменением
         // седержимого таба,  через адаптер.

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                // в зависимости от позиции передаем id таба
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });

        // присваиваем полученую кнопку  с  Extra
        buttonID = getIntent().getIntExtra("fname", 0);

        Log.d(LOG_TAG, "номер индекса активити: " + buttonID);

    } // OnCreate

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
        super.onBackPressed(); // по кнопке назад  в телефоне запускаем анимацию
        overridePendingTransition (R.anim.open_main, R.anim.close_next);
    }//onBackPressed

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // нажатие по иконке назад  в экшей баре, влозвращаемся  вглавное активити
                this.finish();
                overridePendingTransition (R.anim.open_main, R.anim.close_next);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }// switch
    }//onOptionsItemSelected


}//ActivityTabs
