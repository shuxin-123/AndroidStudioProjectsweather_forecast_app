package com.animee.forecast.city_manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.animee.forecast.R;
import com.animee.forecast.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView errorIv,rightIv;
    ListView deleteLv;
    List<String>mDatas;   //listview的数据源
    List<String>deleteCitys;  //表示存储了删除的城市信息
    private DeleteCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_city);
        errorIv = findViewById(R.id.delete_iv_error);
        rightIv = findViewById(R.id.delete_iv_right);
        deleteLv = findViewById(R.id.delete_lv);
        mDatas = DBManager.queryAllCityName();
        deleteCitys = new ArrayList<>();
//        设置点击监听事件
        errorIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
//        适配器的设置
        adapter = new DeleteCityAdapter(this, mDatas, deleteCitys);
        deleteLv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int clickedId = v.getId();

        if (clickedId == R.id.delete_iv_error) {
            // 取消操作，显示确认对话框
            new AlertDialog.Builder(this)
                    .setTitle("提示信息")
                    .setMessage("您确定要舍弃更改么？")
                    .setPositiveButton("舍弃更改", (dialog, which) -> finish())
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
        } else if (clickedId == R.id.delete_iv_right) {
            // 确认删除操作
            for (String city : deleteCitys) {
                DBManager.deleteInfoByCity(city);
            }
            finish(); // 删除成功后返回
        }
    }
}
