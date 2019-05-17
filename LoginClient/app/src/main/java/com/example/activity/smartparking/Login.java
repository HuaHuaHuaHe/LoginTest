package com.example.activity.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
封装代码步骤
* 1：拿到okHttpClient对象
* 2：构造Request
  2.1构造requestBody
  2.2包装requestBody
  3.call -> execute
* */

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText account,password;
    private Button login;
    private TextView register;
    private String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        account = (EditText)findViewById(R.id.account);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
//                tcpClient = new TcpClient();
//                tcpClient.tcpConnection(str1,str2);
                str1 = account.getText().toString();
                str2 = password.getText().toString();
                OkhttpTest okhttpTest = new OkhttpTest(str1,str2);
                try {
                    okhttpTest.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.register:
                //用户注册
                Intent intent = new Intent(Login.this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

