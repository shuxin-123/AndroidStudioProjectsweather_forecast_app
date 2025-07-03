package com.animee.forecast.zhihuicity.dang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.animee.forecast.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DangHomeFragment extends Fragment {
    private ViewPager viewPager;
    private ListView listView;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;
    private final long PERIOD_MS = 3000;

    public static DangHomeFragment newInstance() {
        return new DangHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_home, container, false);

        try {
            initBanner(view);
            initNewsList(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initBanner(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        if (viewPager == null || getContext() == null) return;

        List<Integer> imageResources = new ArrayList<>();
        imageResources.add(R.drawable.danglb1);
        imageResources.add(R.drawable.danglb2);
        imageResources.add(R.drawable.danglb3);
        imageResources.add(R.drawable.danglb4);
        imageResources.add(R.drawable.danglb5);

        BannerAdapter adapter = new BannerAdapter(getContext(), imageResources);
        viewPager.setAdapter(adapter);

        startAutoScroll();
    }

    private void startAutoScroll() {
        if (viewPager == null || viewPager.getAdapter() == null) return;

        final int count = viewPager.getAdapter().getCount();
        if (count == 0) return;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        if (viewPager != null) {
                            currentPage = (currentPage + 1) % count;
                            viewPager.setCurrentItem(currentPage, true);
                        }
                    });
                }
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void initNewsList(View view) {
        listView = view.findViewById(R.id.listView);
        if (listView == null || getContext() == null) return;

        List<String> newsList = new ArrayList<>();
        newsList.add("1. 庆祝中国共产党成立100周年大会隆重举行");
        // 添加其他新闻项...

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                newsList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}