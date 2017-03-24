package com.app.codytutorials.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;

import com.app.codytutorials.R;

public class ActivityAbout extends AppCompatActivity {

	private Toolbar toolbar;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme_NoActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		initToolbar();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.open_main, R.anim.close_next);
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
}