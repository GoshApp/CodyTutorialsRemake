package com.app.codytutorials.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.codytutorials.R;
import com.app.codytutorials.activity.DetailActivity;
import com.app.codytutorials.dto.DTO;

import java.util.ArrayList;


public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ArticlesViewHolder> {

    private ArrayList<DTO> data;
    private Context context;

    public ArticlesListAdapter(Context context, ArrayList<DTO> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        final DTO item = data.get(position);
        holder.title.setText(item.title);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("article", item);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ArticlesViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        public ArticlesViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }// ArticlesViewHolder
    }// class ArticlesViewHolder

}// class ArticlesListAdapter