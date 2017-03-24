package com.app.codytutorials.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.codytutorials.R;

public class PreferencesActivity extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        initToolbar();
    }

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
        toolbar.setTitle(R.string.setting);

    }// initToolbar


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }//onBackPressed
}
