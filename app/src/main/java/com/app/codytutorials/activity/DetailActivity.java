package com.app.codytutorials.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.codytutorials.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private TextView detailTitle;
    private TextView detailContent;
    private DatabaseReference mRef;
    private Context context;
    private String article_key = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(50);
        progressDialog.setMessage("Загрузка....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailContent = (TextView)findViewById(R.id.detailContent);

        article_key = getIntent().getExtras().getString("article_id");
        Toast.makeText(DetailActivity.this, article_key, Toast.LENGTH_SHORT).show();
        mRef = FirebaseDatabase.getInstance().getReference().child("Java");

        mRef.child(article_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mTitle = (String) dataSnapshot.child("title").getValue();
                String mContent = (String) dataSnapshot.child("fullText").getValue();

                detailTitle.setText(mTitle);
                detailContent.setText(mContent);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // инициализация Toolbar
    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.title_toolbar_java);
    }// initToolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.open_next, R.anim.close_next);
        }
        return super.onOptionsItemSelected(item);
    }
}
