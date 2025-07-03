package com.animee.forecast.zhihuicity.dang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.animee.forecast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dang_StudyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dang_StudyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dang_StudyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Dang_StudyFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static Dang_StudyFragment newInstance() {
        return new Dang_StudyFragment();
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
        View view = inflater.inflate(R.layout.fragment_dang__study, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("党员学习");

        // 这里可以添加更多的初始化代码

        return view;
    }
}