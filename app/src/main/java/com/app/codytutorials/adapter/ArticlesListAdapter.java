package com.app.codytutorials.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.codytutorials.R;
import com.app.codytutorials.dto.DTO;

import java.util.List;


public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ArticlesViewHolder> {

    private List<DTO> data;

    public ArticlesListAdapter(List<DTO> data) {
        this.data = data;
    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        DTO item = data.get(position);
        holder.title.setText(item.getTitle());
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