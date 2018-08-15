package com.example.xuzg.broadcastbastpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class LoginActivity extends BaseActivity {
    private  EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox rememberPass;
    private Button login;

    private SharedPreferences load;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 找到元素
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        load = getSharedPreferences("data", MODE_PRIVATE);
        String loadAccount = load.getString("account", "");
        accountEdit.setText(loadAccount);

        Boolean isRemember = load.getBoolean("isRemember", false);
        if (isRemember) {
            String loadPassword = load.getString("password", "");
            passwordEdit.setText(loadPassword);
            rememberPass.setChecked(true);
        }

        login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // 获取账号密码
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            if(account.equals("admin") && password.equals("111")) {
                editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("account", account);
                editor.putBoolean("isRemember", rememberPass.isChecked());
                // 如果勾选了记住密码
                if (rememberPass.isChecked()) {
                    editor.putString("password", password);
                } else {
                    editor.putString("password", "");
                }

                editor.apply();

                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}
