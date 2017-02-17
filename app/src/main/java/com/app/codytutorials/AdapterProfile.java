package com.app.codytutorials;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class AdapterProfile extends FragmentPagerAdapter {

    final String LOG_TAG = "myLogs"; // добавлем логи


    public AdapterProfile(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int index) {
        Bundle bundle = new Bundle();

        // можно положить в фрагмент то что понадобится,
        // а именно - строковый ресурс, изображение, цвет
        Log.d(LOG_TAG, "номер bundle: " + bundle);
        int imgResId = 0;
        int tab = 0;
        int colorResId = 0;


            // передаем id кнопки с ActivityTabs и далее
              int i =+ ActivityTabs.buttonID;        // присваиваем переменную переданую через
                                                     // публичное статическое поле
              Log.d(LOG_TAG, "номер индекса: " + i); // логи
            switch (i){
                case 2131689634: // id нажатого меню Java
                    if(index == 0){                           // таб 1
                        tab = R.string.profile_tab1;
                        imgResId = R.drawable.icon_camera;    // иконка с ресурсов, заглушка-тест
                    }
                    if(index == 1){                           // таб 2
                        tab = R.string.profile_tab2;

                    }
                    break;
                case 2131689635: // id нажатого меню javaScript
                    if(index == 0){
                        tab = R.string.profile_tab3;
                        // здесь также будет путь к файлу
                    }
                    if(index == 1){
                        tab = R.string.profile_tab4;
                    }
                    break;
                case 2131689636: // id нажатого меню nav_python
                    if(index == 0){
                        tab = R.string.profile_tab3;    // заглушки
                    }
                    if(index == 1){
                        tab = R.string.profile_tab4;
                    }
                    break;
                case 2131558564: // id нажатого меню nav_web
                    if(index == 0){
                        tab = R.string.profile_tab3;
                    }
                    if(index == 1){
                        tab = R.string.profile_tab4;
                    }
                    break;
            }

        bundle.putInt("image", imgResId);
        bundle.putInt("tab",tab);
        bundle.putInt("color", colorResId);

        SwipeTabFragment swipeTabFragment = new SwipeTabFragment();
        swipeTabFragment.setArguments(bundle);
        return swipeTabFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}