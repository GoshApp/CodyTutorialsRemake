package com.app.codytutorials.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.codytutorials.R;
import com.app.codytutorials.activity.DetailActivity;
import com.app.codytutorials.models.Article;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ManualsFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_manuals;

    private RecyclerView rv;
    private FirebaseRecyclerAdapter<Article, ArticleViewHolder> adapter;
    private DatabaseReference mRef;
    private ProgressDialog progressDialog;

    public static ManualsFragment getInstance(Context context){
        Bundle args = new Bundle();
        ManualsFragment fragment = new ManualsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_manuals));

        return fragment;
    }// WorldFragment

    public ManualsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        initialiseView();

        return view;
    }// onCreateView

    private void initialiseView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMax(50);
        progressDialog.setMessage("Загрузка....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        mRef = FirebaseDatabase.getInstance().getReference();
        rv = (RecyclerView)view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        setupAdapter();
        rv.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(
                Article.class, R.layout.list_item, ArticleViewHolder.class, mRef.child("Java")
        ) {
            @Override
            protected void populateViewHolder(ArticleViewHolder viewHolder, Article model, final int position) {

                final String article_key = getRef(position).getKey();

                progressDialog.dismiss();
                viewHolder.mTitle.setText(model.getTitle());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("article_id", article_key);
                        startActivity(intent);
                    }
                });
            }
        };
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.mTitle);
        }
    }
}// class ManualsFragment


