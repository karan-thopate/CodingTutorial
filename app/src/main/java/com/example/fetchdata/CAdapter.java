package com.example.fetchdata;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fetchdata.utility.OnListViewClick;

import java.util.ArrayList;

public class CAdapter extends BaseAdapter {
    ArrayList<Url> urls;
    Context context;
    int cnt=0;
    TextView url1;
    private OnListViewClick onListViewClick;



    public CAdapter(ArrayList<Url> urls, Context context, OnListViewClick onListViewClick) {
        this.urls = urls;
        this.context = context;
        this.onListViewClick = onListViewClick;
    }

    @Override
    public int getCount() {
        return urls.size();
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Url url=urls.get(position);

        View view= LayoutInflater.from(context).inflate(R.layout.c_display_urls,null,false);

        TextView subject=view.findViewById(R.id.subject);
        url1=view.findViewById(R.id.urlC);

        subject.setText(url.getUserTopic());
        url1.setText(url.getUserUrl());


        url1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*cnt=cnt+1;
                Toast.makeText(context, "Count"+cnt, Toast.LENGTH_SHORT).show();*/


                onListViewClick.onLisViewClick(v,position);
                /**/
            }
        });



        return view;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
