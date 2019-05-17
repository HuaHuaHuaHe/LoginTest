package com.example.activity.smartparking;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText account,password;
    private Button register;
    private String str1,str2;
    private static String url = "http://10.24.22.247:80/SmartParking/reg1";
    private Map<String,String> paramsMap = null;
    private CallBackUtil callBackUtil = null;
    private String jsonstr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        account = (EditText)findViewById(R.id.account_r);
        password = (EditText)findViewById(R.id.password_r);
        register = (Button)findViewById(R.id.register_r);
        register.setOnClickListener(this);
    }

    private Object register_f() {
        paramsMap = new HashMap<String, String>();
        paramsMap.put("uname",str1);
        paramsMap.put("pwd",str2);
        Gson gson = new Gson();
        jsonstr = gson.toJson(paramsMap);
        callBackUtil = new CallBackUtil() {
            @Override
            public Object onParseResponse(Call call, Response response) throws IOException {
                String resp = response.body().string();
                if(resp.equals("success")){
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,Login.class);
                    startActivity(intent);
                    Looper.loop();
                    return null;
                }else if (resp.equals("failure")){
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    return null;
                }
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e) {
                Looper.prepare();
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Object response) {

            }
        };
        return null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_r:
                str1 = account.getText().toString();
                str2 = password.getText().toString();
                register_f();
                OkhttpUtil.okHttpPostJson(url,jsonstr,callBackUtil);
        }
    }
}
