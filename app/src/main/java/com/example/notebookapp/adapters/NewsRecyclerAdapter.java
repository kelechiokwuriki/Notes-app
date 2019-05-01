package com.example.notebookapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.view_news, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        //get the url from the news in newsList
//        final String news = newsList.get(viewHolder.getAdapterPosition()).getWebUrl();


//        TextView urlTextView = view.findViewById(R.id.newsUrlId);
//
//        urlTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news));
//                context.startActivity(intent);
//            }
//        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String news = newsList.get(viewHolder.getAdapterPosition()).getWebUrl();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news));
                context.startActivity(intent);


                Log.i("Clicked", String.valueOf(viewHolder.getAdapterPosition()));
            }
        });


        return viewHolder;
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

        //set onclick listener on itemview (the whole row)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitleId);
            newsUrl = itemView.findViewById(R.id.newsUrlId);
            newsDate = itemView.findViewById(R.id.newsDateId);

        }
    }
}
