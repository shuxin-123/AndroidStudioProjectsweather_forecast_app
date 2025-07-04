package com.animee.forecast.zhihuicity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.animee.forecast.R;
import com.animee.forecast.MainActivity;
import com.animee.forecast.zhihuicity.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GridView gridView;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    private final String[] itemNames = {
            "功能1", "功能2", "功能3", "功能4",
            "功能5", "功能6", "注销", "天气"
    };

    private final int[] itemIcons = {
            R.drawable.kc, R.drawable.bd, R.drawable.cj, R.drawable.js,
            R.drawable.fx, R.drawable.tg, R.drawable.tx, R.mipmap.icon
    };

    private String mParam1;
    private String mParam2;
    private List<NewsItem> newsList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initGridView(view);
        initRecyclerView(view);
        return view;
    }

    private void initGridView(View view) {
        gridView = view.findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(getContext(), itemNames, itemIcons);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                case 6: // 注销项
                    performLogout();
                    break;
                case 7: // 天气项
                    navigateToWeather();
                    break;
                default:
                    showItemToast(position);
                    break;
            }
        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(newsAdapter);
        loadNewsData();
    }

    private void navigateToWeather() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void showItemToast(int position) {
        Toast.makeText(getContext(), "点击了: " + itemNames[position], Toast.LENGTH_SHORT).show();
    }

    private void loadNewsData() {
        String typeId = "518";
        int page = 1; // 页码
        String appId = "bftkublmvrvqsgin";
        String appSecret = "LYkYJ7KaGHaXMvqV3VBKZwMcRJFD8rpl";

        HttpUtil.getNewsList(typeId, page, appId, appSecret, new HttpUtil.NewsCallback() {
            @Override
            public void onSuccess(String result) {
                if (getActivity() == null) return;

                getActivity().runOnUiThread(() -> {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<BaseResponse<List<NewsItem>>>(){}.getType();
                        BaseResponse<List<NewsItem>> response = gson.fromJson(result, type);

                        if (response != null && response.getData() != null) {
                            newsList.clear();
                            newsList.addAll(response.getData());
                            newsAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "数据解析异常", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                if (getActivity() == null) return;

                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "请求失败: " + error, Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    private void performLogout() {
        SharedPreferences preferences = requireContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    private static class GridAdapter extends BaseAdapter {
        private final Context context;
        private final String[] names;
        private final int[] icons;
        private final LayoutInflater inflater;
        private int selectedPosition = -1;

        public GridAdapter(Context context, String[] names, int[] icons) {
            this.context = context;
            this.names = names;
            this.icons = icons;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_item, parent, false);
                holder = new ViewHolder();
                holder.radioButton = (RadioButton) convertView;
                holder.radioButton.setClickable(false);
                holder.radioButton.setFocusable(false);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.radioButton.setText(names[position]);
            holder.radioButton.setCompoundDrawablesWithIntrinsicBounds(0, icons[position], 0, 0);
            holder.radioButton.setChecked(position == selectedPosition);

            return convertView;
        }

        private static class ViewHolder {
            RadioButton radioButton;
        }
    }
}
class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}