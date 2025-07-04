package com.animee.forecast.zhihuicity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.animee.forecast.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsList;

    public NewsAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteam_newsshuju, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsItem news = newsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvSource.setText(news.getSource());
        holder.tvTime.setText(news.getPostTime());
        holder.tvDigest.setText(news.getDigest());
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void updateData(List<NewsItem> newList) {
        this.newsList = newList;
        notifyDataSetChanged();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvTime, tvDigest;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvDigest = itemView.findViewById(R.id.tv_digest);
        }
    }
}