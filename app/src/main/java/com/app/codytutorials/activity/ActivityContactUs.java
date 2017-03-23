package com.app.codytutorials.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.codytutorials.R;

public class ActivityContactUs extends AppCompatActivity {

	public Toolbar toolbar;
	private EditText body;
	public Button send;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme_NoActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);
		body = (EditText) findViewById(R.id.body);
		send = (Button) findViewById(R.id.send);
		initToolbar();
	}

	public void SendBtn(View v) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { getResources().getString(R.string.email_address) });

		intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));

		String body_email = body.getText().toString() + "\n\n Сообщение отправлено с приложения CodyTutorials";
		intent.putExtra(Intent.EXTRA_TEXT, body_email);
		startActivityForResult(Intent.createChooser(intent, "Send email..."), 100);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(ActivityContactUs.this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
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
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                //noinspection SimplifiableIfStatement
                switch(id) {
                    case android.R.id.home:
                        // app icon in action bar clicked; go home
                        intent = new Intent(ActivityContactUs.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.open_main, R.anim.close_next);
                        break;
                    case R.id.settings:
                        intent = new Intent(getApplicationContext(), PreferencesActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.open_next, R.anim.close_next);
                        break;
                    case R.id.about:
                        intent = new Intent(getApplicationContext(), ActivityAbout.class);
                        startActivity(intent);
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
        toolbar.inflateMenu(R.menu.menu_player);
    }// initToolbar

}
