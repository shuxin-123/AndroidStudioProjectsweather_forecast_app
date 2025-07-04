package com.animee.forecast.zhihuicity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.animee.forecast.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AixinFragment extends Fragment {

    private EditText etName, etAmount;
    private Button btnDonate;
    private ListView lvDonations;
    private ArrayAdapter<String> donationAdapter;
    private ArrayList<String> donationList;

    // SharedPreferences键名
    private static final String PREFS_NAME = "DonationPrefs";
    private static final String KEY_DONATIONS = "donations";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aixin, container, false);

        // 初始化视图
        etName = view.findViewById(R.id.et_name);
        etAmount = view.findViewById(R.id.et_amount);
        btnDonate = view.findViewById(R.id.btn_donate);
        lvDonations = view.findViewById(R.id.lv_donations);

        // 初始化捐赠列表
        donationList = new ArrayList<>();
        donationAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                donationList);
        lvDonations.setAdapter(donationAdapter);

        // 加载保存的捐赠记录
        loadDonations();

        // 设置捐赠按钮点击事件
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDonation();
            }
        });

        return view;
    }

    private void processDonation() {
        String name = etName.getText().toString().trim();
        String amountStr = etAmount.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "请输入您的姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (amountStr.isEmpty()) {
            Toast.makeText(getActivity(), "请输入捐赠金额", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                Toast.makeText(getActivity(), "捐赠金额必须大于0", Toast.LENGTH_SHORT).show();
                return;
            }

            // 格式化捐赠信息
            String donationInfo = String.format("%s 捐赠了 %.2f 元", name, amount);

            // 添加到列表
            donationList.add(0, donationInfo); // 添加到开头
            donationAdapter.notifyDataSetChanged();

            // 保存到SharedPreferences
            saveDonations();

            // 清空输入框
            etName.setText("");
            etAmount.setText("");

            // 显示感谢信息
            Toast.makeText(getActivity(), "感谢您的爱心捐赠！", Toast.LENGTH_LONG).show();

        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "请输入有效的金额", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDonations() {
        Set<String> donationsSet = new HashSet<>(donationList);
        getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(KEY_DONATIONS, donationsSet)
                .apply();
    }

    private void loadDonations() {
        Set<String> donationsSet = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getStringSet(KEY_DONATIONS, new HashSet<String>());

        donationList.clear();
        donationList.addAll(donationsSet);
        donationAdapter.notifyDataSetChanged();
    }
}