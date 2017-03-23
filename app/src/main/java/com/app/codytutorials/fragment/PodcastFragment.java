package com.app.codytutorials.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.codytutorials.adapter.ArticlesListAdapter;
import com.app.codytutorials.R;
import com.app.codytutorials.dto.DTO;

import java.util.ArrayList;
import java.util.List;


public class PodcastFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_podcast;

    public static ManualsFragment getInstance(Context context){
        Bundle args = new Bundle();
        ManualsFragment fragment = new ManualsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_player));

        return fragment;
    }// WorldFragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new ArticlesListAdapter(context, createPodcastData()));
        return view;
    }// onCreateView




    public void setContext(Context context) {
        this.context = context;
    }

    private ArrayList<DTO> createPodcastData() {
        ArrayList<DTO> data = new ArrayList<>();
//        data.add(new DTO("Item 1"));
//        data.add(new DTO("Item 2"));
//        data.add(new DTO("Item 3"));
//        data.add(new DTO("Item 4"));
//        data.add(new DTO("Item 5"));
//        data.add(new DTO("Item 6"));
        return data;
    }
}
