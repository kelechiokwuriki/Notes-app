package com.example.notebookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp.Bean.News;
import com.example.notebookapp.Bean.NewsList;
import com.example.notebookapp.R;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsRecyclerAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.newsTitle.setText(newsList.get(i).getWebTitle());
        viewHolder.newsUrl.setText(newsList.get(i).getWebUrl());
        viewHolder.newsDate.setText(newsList.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitle, newsUrl, newsDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitleId);
            newsUrl = itemView.findViewById(R.id.newsUrlId);
            newsDate = itemView.findViewById(R.id.newsDateId);
        }
    }
}
