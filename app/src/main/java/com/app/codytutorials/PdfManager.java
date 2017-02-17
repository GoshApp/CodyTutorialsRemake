package com.app.codytutorials;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by 1 on 16.02.2017.
 */

public class PdfManager extends ListFragment {

    ArrayAdapter adapter;
    ArrayList listItems = new ArrayList();
    String[] pdflist;
    private File[] fileslist; // массив файлов

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        File pdf_files = Environment.getExternalStorageDirectory();
        // список файлов с расширением PDF
        fileslist = pdf_files.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return ((name.endsWith(".pdf")));
            }
        });
        pdflist = new String[fileslist.length];
        // формируем строковый массив имен файлов
        for (int i = 0; i < fileslist.length; i++) {
            pdflist[i] = fileslist[i].getName();
        }
        this.setListAdapter(new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, pdflist));
    }// onActivityCreated



    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("application/pdf");
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0 && fileslist[(int) id].isFile()) {
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(fileslist[(int) id].getAbsoluteFile());
            intent.setDataAndType(uri, "application/pdf");
            startActivity(intent);
        }
    }



}// class PdfManager
