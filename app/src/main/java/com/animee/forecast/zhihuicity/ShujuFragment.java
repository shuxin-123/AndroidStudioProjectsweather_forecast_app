package com.animee.forecast.zhihuicity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.animee.forecast.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ShujuFragment extends Fragment {

    private BarChart barChart;
    private LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuju, container, false);

        // 初始化图表
        barChart = view.findViewById(R.id.barChart);
        lineChart = view.findViewById(R.id.lineChart);

        // 设置柱状图
        setupBarChart();

        // 设置折线图
        setupLineChart();

        return view;
    }

    private void setupBarChart() {
        // 模拟数据 - 前5条新闻的点赞数
        String[] newsTitles = new String[]{
                "甘肃天水幼儿血铅异常",
                "青年大有可为",
                "《淬心》第二集",
                "俄海军副总司令阵亡",
                "特朗普签署法案"
        };

        int[] likes = new int[]{1250, 980, 1560, 870, 1120};

        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < newsTitles.length; i++) {
            entries.add(new BarEntry(i, likes[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "点赞数");
        dataSet.setColor(Color.parseColor("#FF5722")); // 橙色
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.5f);

        // 配置图表
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.animateY(1000);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(newsTitles));
        xAxis.setLabelRotationAngle(-45);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setGranularity(200f);

        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);
    }

    private void setupLineChart() {
        // 模拟数据 - 月度诉求数量
        String[] months = new String[]{"1月", "2月", "3月", "4月", "5月", "6月"};
        int[] requests = new int[]{320, 450, 380, 520, 480, 600};

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            entries.add(new Entry(i, requests[i]));
        }

        LineDataSet dataSet = new LineDataSet(entries, "诉求量");
        dataSet.setColor(Color.parseColor("#03A9F4")); // 蓝色
        dataSet.setCircleColor(Color.parseColor("#03A9F4"));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);
        lineChart.animateX(1000);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setGranularity(100f);

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
    }
}