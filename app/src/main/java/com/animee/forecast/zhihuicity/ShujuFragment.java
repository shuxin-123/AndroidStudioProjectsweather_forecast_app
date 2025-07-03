package com.animee.forecast.zhihuicity;

import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.animee.forecast.R;

public class ShujuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // 捐赠相关视图
    private TextView projectTitle, projectDescription, projectProgress;
    private RadioGroup amountGroup;
    private EditText customAmount, donorName, donorPhone, donorMessage;
    private Button donateButton;
    private int selectedAmount = 0;

    public static ShujuFragment newInstance(String param1, String param2) {
        ShujuFragment fragment = new ShujuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuju, container, false);

        // 初始化视图
        initViews(view);

        // 设置项目信息
        setProjectDetails();

        // 设置金额选择监听
        setupAmountSelection();

        // 设置捐赠按钮点击事件
        setupDonationButton();

        return view;
    }

    private void initViews(View view) {
        projectTitle = view.findViewById(R.id.project_title);
        projectDescription = view.findViewById(R.id.project_description);
        projectProgress = view.findViewById(R.id.project_progress);
        amountGroup = view.findViewById(R.id.amount_group);
        customAmount = view.findViewById(R.id.custom_amount);
        donorName = view.findViewById(R.id.donor_name);
        donorPhone = view.findViewById(R.id.donor_phone);
        donorMessage = view.findViewById(R.id.donor_message);
        donateButton = view.findViewById(R.id.donate_button);
    }

    private void setProjectDetails() {
        projectTitle.setText("乡村教育支持计划");
        projectDescription.setText("该项目致力于为贫困山区的孩子们提供教育资源，包括图书、学习用品和教学设备，帮助他们获得更好的学习条件。");
        projectProgress.setText("已筹集: ¥125,800 / ¥200,000");
    }

    private void setupAmountSelection() {
        amountGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                customAmount.setText("");

                if (checkedId == R.id.amount_10) {
                    selectedAmount = 10;
                } else if (checkedId == R.id.amount_50) {
                    selectedAmount = 50;
                } else if (checkedId == R.id.amount_100) {
                    selectedAmount = 100;
                } else if (checkedId == R.id.amount_200) {
                    selectedAmount = 200;
                } else if (checkedId == R.id.custom_amount) {
                    selectedAmount = 0;
                }
            }
        });

        // 默认选择10元
        RadioButton defaultAmount = getView().findViewById(R.id.amount_10);
        defaultAmount.setChecked(true);
        selectedAmount = 10;
    }

    private void setupDonationButton() {
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    processDonation();
                }
            }
        });
    }

    private boolean validateForm() {
        if (selectedAmount == 0) {
            String customAmountText = customAmount.getText().toString();
            if (customAmountText.isEmpty()) {
                showToast("请选择或输入捐赠金额");
                return false;
            }

            try {
                selectedAmount = Integer.parseInt(customAmountText);
                if (selectedAmount <= 0) {
                    showToast("捐赠金额必须大于0");
                    return false;
                }
            } catch (NumberFormatException e) {
                showToast("请输入有效的金额");
                return false;
            }
        }

        String name = donorName.getText().toString();
        if (name.isEmpty()) {
            showToast("请输入您的姓名");
            return false;
        }

        String phone = donorPhone.getText().toString();
        if (phone.isEmpty() || phone.length() != 11) {
            showToast("请输入有效的手机号");
            return false;
        }

        return true;
    }

    private void processDonation() {
        String name = donorName.getText().toString();
        String phone = donorPhone.getText().toString();
        String message = donorMessage.getText().toString();

        String donationInfo = "感谢您的捐赠，" + name + "!\n" +
                "捐赠金额: ¥" + selectedAmount + "\n" +
                "我们将尽快与您联系，确认捐赠详情。";

        showToast(donationInfo);

        // 清空表单
        resetForm();
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void resetForm() {
        donorName.setText("");
        donorPhone.setText("");
        donorMessage.setText("");
        customAmount.setText("");
        RadioButton defaultAmount = getView().findViewById(R.id.amount_10);
        defaultAmount.setChecked(true);
        selectedAmount = 10;
    }
}