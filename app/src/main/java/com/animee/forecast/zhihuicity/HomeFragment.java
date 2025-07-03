package com.animee.forecast.zhihuicity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.animee.forecast.MainActivity;
import com.animee.forecast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private GridView gridView;
    private String[] itemNames = {"功能1", "功能2", "功能3", "功能4", "功能5", "功能6", "注销", "天气"};
    private int[] itemIcons = {R.drawable.kc, R.drawable.bd, R.drawable.cj, R.drawable.js,
            R.drawable.fx, R.drawable.tg, R.drawable.tx, R.mipmap.icon};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        gridView = view.findViewById(R.id.gridView);
        // 设置适配器
        GridAdapter adapter = new GridAdapter(getContext(), itemNames, itemIcons);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            if (position == 6) { // 注销项
                performLogout();
            } else if (position == 7) { // 天气项
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "点击了: " + itemNames[position], Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void performLogout() {
        // 1. 清除SharedPreferences中的登录状态
        SharedPreferences preferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // 或选择性清除：editor.remove("is_logged_in");
        editor.apply();

        // 2. 跳转到登录页面
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // 3. 关闭当前Activity（如果是Fragment所在的Activity）
        requireActivity().finish();
    }
    private class GridAdapter extends BaseAdapter {
        private Context context;
        private String[] names;
        private int[] icons;
        private LayoutInflater inflater;
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

        class ViewHolder {
            RadioButton radioButton;
        }
    }
}