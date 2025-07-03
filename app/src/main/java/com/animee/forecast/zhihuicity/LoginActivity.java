package com.animee.forecast.zhihuicity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.animee.forecast.Dao.UserDao;
import com.animee.forecast.R;


public class LoginActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button register;
    private Button login;

    private UserDao userDao;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // 检查是否已登录
        if (sharedPreferences.getBoolean("is_logged_in", false)) {
            navigateToMainActivity();
            finish();
            return;
        }

        userDao = new UserDao(this);

        // 获取界面元素
        accountEdit = findViewById(R.id.accountEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();

                if (account.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 检查账号是否已存在
                if (userDao.isAccountExists(account)) {
                    Toast.makeText(LoginActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 添加用户到数据库
                long result = userDao.addUser(account, password);

                if (result != -1) {
                    accountEdit.setText("");
                    passwordEdit.setText("");
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = accountEdit.getText().toString().trim();
                String inputPassword = passwordEdit.getText().toString().trim();

                if (inputAccount.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证用户
                if (userDao.validateUser(inputAccount, inputPassword)) {
                    // 保存登录状态和用户信息
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_logged_in", true);
                    editor.putString("username", inputAccount);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, CityMainActivity.class);
        // 可以传递用户名到主页面
        intent.putExtra("username", sharedPreferences.getString("username", ""));
        startActivity(intent);
        finish(); // 结束当前Activity，防止用户按返回键回到登录页
    }

    @Override
    protected void onDestroy() {
        // 关闭数据库连接
        if (userDao != null) {
            userDao.close();
        }
        super.onDestroy();
    }
}