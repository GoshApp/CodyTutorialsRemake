package com.app.codytutorials.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.app.codytutorials.R;
import com.app.codytutorials.dto.DTO;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView wView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        wView = (WebView)findViewById(R.id.wView);
        wView.setBackgroundColor(0x00000000);
        initToolbar();

        if (getIntent().getSerializableExtra("article") != null){
            showProgressBar();
            DTO dto = (DTO)getIntent().getSerializableExtra("article");
            String articleUrl = "file:///android_asset/test/" + dto.getTitle();

            wView.loadUrl(articleUrl);
        }
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
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
        toolbar.setTitle(R.string.title_toolbar_java);
    }// initToolbar

    private void showProgressBar() {
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(2000);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}
