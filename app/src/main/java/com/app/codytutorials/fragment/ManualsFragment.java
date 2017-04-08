package com.app.codytutorials.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.codytutorials.adapter.ArticlesListAdapter;
import com.app.codytutorials.R;
import com.app.codytutorials.dto.DTO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ManualsFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_manuals;

    public static ManualsFragment getInstance(Context context){
        Bundle args = new Bundle();
        ManualsFragment fragment = new ManualsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_manuals));

        return fragment;
    }// WorldFragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new ArticlesListAdapter(context, data()));
        return view;
    }// onCreateView

    public void setContext(Context context) {
        this.context = context;
    }

    private ArrayList<DTO> data() {
        ArrayList<DTO> data = new ArrayList<>();
        AssetManager myAssetManager = getContext().getAssets();

        try {
            String[] files = myAssetManager.list("java"); // массив имен файлов
            for (String title : files){
                data.add(new DTO(title.substring(0, (title.length() -5))));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }// createManualsData

}// class ManualsFragment


