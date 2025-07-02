package com.animee.forecast;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.animee.forecast.db.DBManager;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bgTv,cacheTv,versionTv,shareTv;
    RadioGroup exbgRg;
    ImageView backIv;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bgTv = findViewById(R.id.more_tv_exchangebg);
        cacheTv = findViewById(R.id.more_tv_cache);
        versionTv = findViewById(R.id.more_tv_version);
        shareTv = findViewById(R.id.more_tv_share);
        backIv = findViewById(R.id.more_iv_back);
        exbgRg = findViewById(R.id.more_rg);
        bgTv.setOnClickListener(this);
        cacheTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        String versionName = getVersionName();
        versionTv.setText("当前版本:    v"+versionName);
        setRGListener();
    }

    private void setRGListener() {
        /* 设置改变背景图片的单选按钮的监听*/
        exbgRg.setOnCheckedChangeListener((group, checkedId) -> {
//                获取目前的默认壁纸
            int bg = pref.getInt("bg", 0);
            SharedPreferences.Editor editor = pref.edit();
            Intent intent = new Intent(MoreActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            if (checkedId == R.id.more_rb_green && bg != 0) {
                editor.putInt("bg", 0);
            } else if (checkedId == R.id.more_rb_pink && bg != 1) {
                editor.putInt("bg", 1);
            } else if (checkedId == R.id.more_rb_blue && bg != 2) {
                editor.putInt("bg", 2);
            } else {
                Toast.makeText(getApplicationContext(), "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT).show();
                return;
            }
            editor.apply();
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.more_iv_back) {
            finish();
        } else if (id == R.id.more_tv_cache) {
            clearCache();
        } else if (id == R.id.more_tv_share) {
            shareSoftwareMsg("mini天气系统app是一款实用的天气预报软件，画面简约，播报天气情况非常精准，快来下载吧！");
        } else if (id == R.id.more_tv_exchangebg) {
            if (exbgRg.getVisibility() == View.VISIBLE) {
                exbgRg.setVisibility(View.GONE);
            } else {
                exbgRg.setVisibility(View.VISIBLE);
            }
        }
    }

    private void shareSoftwareMsg(String s) {
        /* 分享软件的函数*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(intent,"说天气"));
    }

    private void clearCache() {
        /* 清除缓存的函数*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("确定要删除所有缓存么？");
        builder.setPositiveButton("确定", (dialog, which) -> {
            DBManager.deleteAllInfo();
            Toast.makeText(MoreActivity.this,"已清除全部缓存！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MoreActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }).setNegativeButton("取消",null);
        builder.create().show();
    }

    public String getVersionName() {
        /* 获取应用的版本名称*/
        PackageManager manager = getPackageManager();
        String versionName = null;
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
