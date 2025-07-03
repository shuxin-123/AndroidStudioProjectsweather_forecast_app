package com.animee.forecast.zhihuicity.dang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.animee.forecast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dang_SuggestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dang_SuggestFragment extends Fragment {
    private EditText etSuggestion;
    private Button btnSubmit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dang_SuggestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Dang_SuggestFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static Dang_SuggestFragment newInstance() {
        return new Dang_SuggestFragment();
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
        View view = inflater.inflate(R.layout.fragment_dang__suggest, container, false);

        // 初始化视图
        etSuggestion = view.findViewById(R.id.et_suggestion);
        btnSubmit = view.findViewById(R.id.btn_submit);

        // 设置提交按钮点击事件
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSuggestion();
            }
        });

        return view;
    }
    private void submitSuggestion() {
        String suggestion = etSuggestion.getText().toString().trim();

        if (suggestion.isEmpty()) {
            // 输入为空时提示
            Toast.makeText(getActivity(), "请输入您的建议", Toast.LENGTH_SHORT).show();
        } else {
            // 模拟提交成功
            etSuggestion.setText(""); // 清空输入框
            Toast.makeText(getActivity(), "提交成功！感谢您的建议", Toast.LENGTH_SHORT).show();

            // 这里可以添加实际的提交逻辑，如网络请求等
            // submitToServer(suggestion);
        }
    }
}