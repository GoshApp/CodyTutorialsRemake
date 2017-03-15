package com.app.codytutorials;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final String LOG_TAG = "myLogs"; // добавлем логи

    Intent intent;
    Intent intentPref;
    ActivityTabs activityTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.open_next, R.anim.close_next);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.openDrawer(GravityCompat.START);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }// Create

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.settings:
                intentPref = new Intent(this, PreferencesActivity.class);
                startActivity(intentPref);
                overridePendingTransition(R.anim.open_next, R.anim.close_next);
                break;
            case R.id.about:
                intentPref = new Intent(this, ActivityAbout.class);
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
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_java) {
            Log.d(LOG_TAG, "номер id кнопки: " + R.id.nav_java);
            intent = new Intent(this, ActivityTabs.class);
            intent.putExtra("fname", R.id.nav_java);
            startActivity(intent);
            overridePendingTransition(R.anim.open_next, R.anim.close_next);

        } else if (id == R.id.nav_javaScript) {
//            Log.d(LOG_TAG, "!!!!!!!!!!!!!!!!!!номер id кнопки: " + item.getItemId());
//            intent = new Intent(this, ActivityTabs.class);
//            intent.putExtra("fname", item.getItemId());
//            startActivity(intent);
//            overridePendingTransition(R.anim.open_next, R.anim.close_next);
        } else if (id == R.id.nav_python) {

        } else if (id == R.id.nav_web) {

        } else if (id == R.id.nav_share) {
            // поделиться приложением
            Intent sendInt = new Intent(Intent.ACTION_SEND);
            sendInt.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            sendInt.putExtra(Intent.EXTRA_TEXT, "Привет, советую скачать это приложение\n\"" + getString(R.string.app_name)
                    + "\" \n по ссылке https://github.com/Jack220591/CodyTutorials?id=" + getPackageName());
            // важный момент, нужно добавить ?id= в концедля нормального отображения ссылки
            sendInt.setType("text/plain");
            startActivity(Intent.createChooser(sendInt, "Поделиться"));

        } else if (id == R.id.nav_send) {
            intent = new Intent(MainActivity.this, ActivityContactUs.class);
            startActivity(intent);
            overridePendingTransition(R.anim.open_next, R.anim.close_next);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}// AudioPlayerFragment