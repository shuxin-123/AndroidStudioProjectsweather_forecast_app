package com.animee.forecast.zhihuicity.dang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.animee.forecast.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    // Fragment参数
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    public NewsFragment() {
        // 必需的空构造函数
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // 设置标题
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("党建动态");

        // 初始化RecyclerView
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);

        // 初始化新闻数据
        List<News> newsList = initNewsData();

        // 设置适配器
        setupRecyclerView(newsList);

        return view;
    }

    private List<News> initNewsData() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("关于《中共四川省委关于推进文化和旅游深度融合发展做大做强文化旅游业的决定》的说明", "2025-06-30"));
        newsList.add(new News("中共四川省委关于推进文化和旅游深度融合发展做大做强文化旅游业的决定", "2025-06-30"));
        newsList.add(new News("四川抓实党员集中培训 靶向提能育先锋", "2025-06-30"));
        newsList.add(new News("省委办公厅、省政府办公厅印发《四川省乡村全面振兴实施方案（2025—2027年）》", "2025-06-25"));
        newsList.add(new News("王晓晖会见来川出席第二十届西博会的部分外国重要嘉宾", "2025-05-21"));
        return newsList;
    }

    private void setupRecyclerView(List<News> newsList) {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    // News类（内部类或单独文件）
    public static class News {
        private String title;
        private String date;

        public News(String title, String date) {
            this.title = title;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }
    }

    // NewsAdapter类（内部类或单独文件）
    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.tvTitle.setText(news.getTitle());
            holder.tvDate.setText(news.getDate());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class NewsViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDate;

            public NewsViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.newsTitle);
                tvDate = itemView.findViewById(R.id.newsDate);
            }
        }
    }
}