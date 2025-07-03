package com.animee.forecast.zhihuicity.dang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.animee.forecast.R;
import com.animee.forecast.zhihuicity.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class DangFragment extends Fragment implements View.OnClickListener {

    private Button btnHome, btnNews, btnStudy, btnSuggest, btnPhoto;
    private Fragment currentFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang, container, false);

        // 初始化按钮
        btnHome = view.findViewById(R.id.btn_home);
        btnNews = view.findViewById(R.id.btn_news);
        btnStudy = view.findViewById(R.id.btn_study);
        btnSuggest = view.findViewById(R.id.btn_suggest);
        btnPhoto = view.findViewById(R.id.btn_photo);

        // 设置点击监听
        btnHome.setOnClickListener(this);
        btnNews.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnSuggest.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);

        // 默认显示首页
        switchFragment(DangHomeFragment.newInstance(), false);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_home) {
            switchFragment(DangHomeFragment.newInstance(), false);
        } else if (v.getId() == R.id.btn_news) {
            switchFragment(NewsFragment.newInstance(), true);
        } else if (v.getId() == R.id.btn_study) {
            switchFragment(Dang_StudyFragment.newInstance(), true);
        } else if (v.getId() == R.id.btn_suggest) {
            switchFragment(Dang_SuggestFragment.newInstance(), true);
        } else if (v.getId() == R.id.btn_photo) {
            switchFragment(DangPhotoFragment.newInstance(), true);
        }
    }

    private void switchFragment(Fragment fragment, boolean addToBackStack) {
        if (getActivity() == null || !isAdded()) return;

        FragmentManager fm = getChildFragmentManager();
        String tag = fragment.getClass().getSimpleName();

        // 先检查是否已存在该Fragment
        Fragment existingFragment = fm.findFragmentByTag(tag);

        FragmentTransaction ft = fm.beginTransaction();

        // 隐藏当前Fragment
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        if (existingFragment != null) {
            // 如果已存在则显示
            ft.show(existingFragment);
            currentFragment = existingFragment;
        } else {
            // 不存在则添加
            ft.add(R.id.fragment_container, fragment, tag);
            currentFragment = fragment;
        }

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }

        ft.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }
}