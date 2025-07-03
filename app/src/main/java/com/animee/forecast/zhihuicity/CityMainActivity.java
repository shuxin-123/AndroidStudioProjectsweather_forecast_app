package com.animee.forecast.zhihuicity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.animee.forecast.R;
import com.animee.forecast.zhihuicity.dang.DangFragment;


public class CityMainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);
        radioGroup = findViewById(R.id.radioGroup);

        // 设置默认选中首页
        radioGroup.check(R.id.rb_home);
        switchFragment(new HomeFragment());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId==R.id.rb_home){
                switchFragment(new HomeFragment());
            } else if (checkedId==R.id.rb_shuju) {
                switchFragment(new ShujuFragment());
            } else if (checkedId==R.id.rb_aixin) {
                switchFragment(new AixinFragment());
            }else if (checkedId==R.id.rb_bus) {
                switchFragment(new BusFragment());
            }else if (checkedId==R.id.rb_dang) {
                switchFragment(new DangFragment());
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        if (currentFragment==null||!currentFragment.getClass().equals(fragment.getClass())){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            currentFragment=fragment;
        }
    }

}