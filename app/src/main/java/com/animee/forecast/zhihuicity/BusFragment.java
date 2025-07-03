package com.animee.forecast.zhihuicity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.animee.forecast.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BusFragment extends Fragment {

    // 视图组件
    private ImageView busIcon;
    private TextView tvNextBus;
    private RecyclerView stationList;
    private Button btnRefresh, btnAlarm;
    private StationAdapter stationAdapter;
    private Handler handler = new Handler();
    private Random random = new Random();

    // Fragment参数
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public BusFragment() {
        // 必需的空构造函数
    }

    public static BusFragment newInstance(String param1, String param2) {
        BusFragment fragment = new BusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        // 初始化视图
        initViews(view);

        // 设置站点列表
        setupStationList();

        // 模拟巴士移动
        simulateBusMovement();

        // 设置按钮点击事件
        setupButtonListeners();

        return view;
    }

    private void initViews(View view) {
        busIcon = view.findViewById(R.id.busIcon);
        tvNextBus = view.findViewById(R.id.tvNextBus);
        stationList = view.findViewById(R.id.stationList);
        btnRefresh = view.findViewById(R.id.btnRefresh);
        btnAlarm = view.findViewById(R.id.btnAlarm);

        // 返回按钮处理（Fragment中没有finish()，需要回调Activity）
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setupStationList() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("科技园站", "距离100米", "预计2分钟"));
        stations.add(new Station("创业大厦站", "距离800米", "预计5分钟"));
        stations.add(new Station("市民中心站", "距离1.5公里", "预计8分钟"));
        stations.add(new Station("市中心站", "距离3公里", "预计12分钟"));
        stations.add(new Station("火车站", "终点站", "预计18分钟"));

        stationAdapter = new StationAdapter(stations);
        stationList.setLayoutManager(new LinearLayoutManager(getContext()));
        stationList.setAdapter(stationAdapter);
    }

    private void simulateBusMovement() {
        handler.postDelayed(new Runnable() {
            int position = 0;
            final int maxPosition = 100;

            @Override
            public void run() {
                if (position < maxPosition && stationList != null && busIcon != null) {
                    // 更新巴士位置
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) busIcon.getLayoutParams();
                    params.leftMargin = (int) (position / 100f * (stationList.getWidth() - busIcon.getWidth()));
                    busIcon.setLayoutParams(params);

                    position += 1;
                    handler.postDelayed(this, 100);
                } else {
                    // 重置位置
                    position = 0;
                    handler.postDelayed(this, 100);
                }
            }
        }, 1000);
    }

    private void setupButtonListeners() {
        btnRefresh.setOnClickListener(v -> {
            refreshData();
            Toast.makeText(getContext(), "数据已刷新", Toast.LENGTH_SHORT).show();
        });

        btnAlarm.setOnClickListener(v -> {
            Toast.makeText(getContext(), "到站提醒已设置", Toast.LENGTH_SHORT).show();
        });
    }

    private void refreshData() {
        // 模拟刷新数据
        int nextBusTime = random.nextInt(10) + 1;
        tvNextBus.setText("下一班: 预计" + nextBusTime + "分钟后到达");

        // 更新站点信息
        if (stationAdapter != null) {
            List<Station> stations = stationAdapter.getStations();
            for (Station station : stations) {
                int distance = random.nextInt(2000) + 100;
                int time = distance / 200;
                station.setDistance("距离" + distance + "米");
                station.setTime("预计" + time + "分钟");
            }
            stationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 清除所有Handler回调
        handler.removeCallbacksAndMessages(null);
    }

    // Station类（内部类或单独文件）
    public static class Station {
        private String name;
        private String distance;
        private String time;

        public Station(String name, String distance, String time) {
            this.name = name;
            this.distance = distance;
            this.time = time;
        }

        // Getter和Setter方法
        public String getName() { return name; }
        public String getDistance() { return distance; }
        public String getTime() { return time; }
        public void setDistance(String distance) { this.distance = distance; }
        public void setTime(String time) { this.time = time; }
    }

    // StationAdapter类（内部类或单独文件）
    public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
        private List<Station> stations;

        public StationAdapter(List<Station> stations) {
            this.stations = stations;
        }

        @Override
        public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_station, parent, false);
            return new StationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StationViewHolder holder, int position) {
            Station station = stations.get(position);
            holder.tvName.setText(station.getName());
            holder.tvDistance.setText(station.getDistance());
            holder.tvTime.setText(station.getTime());
        }

        @Override
        public int getItemCount() {
            return stations.size();
        }

        public List<Station> getStations() {
            return stations;
        }

        class StationViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvDistance, tvTime;

            public StationViewHolder(View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvStationName);
                tvDistance = itemView.findViewById(R.id.tvStationDistance);
                tvTime = itemView.findViewById(R.id.tvStationTime);
            }
        }
    }
}