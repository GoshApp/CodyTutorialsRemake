package com.app.codytutorials;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityContactUs extends Activity {

	EditText body;
	Button send;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);

		ActionBar bar = getActionBar();
		assert bar != null;
		bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
		bar.setTitle((Html.fromHtml("<font color=\"#ffffff\">" + "Связь с нами" + "</font>")));
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setHomeButtonEnabled(true);
		body = (EditText) findViewById(R.id.body);
		send = (Button) findViewById(R.id.send);

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
		switch (item.getItemId()) {

		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(ActivityContactUs.this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent);
			overridePendingTransition(R.anim.open_main, R.anim.close_next);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(ActivityContactUs.this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}

}
