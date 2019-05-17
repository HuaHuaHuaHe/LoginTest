package com.example.activity.smartparking;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpTest {

    private String username;
    private String pwd;

    public OkhttpTest(String str1,String str2) {
        this.username = str1;
        this.pwd = str2;
    }

    private final OkHttpClient client =new OkHttpClient();

    public void run() throws Exception {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username",username)
                .addFormDataPart("password",pwd)
                .addFormDataPart("method","login")
                .addFormDataPart("key","*******")
                .build();
        Request request=new Request.Builder()
                .url("148.70.192.71:8080/SmartParking/con")
                .post(requestBody)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    final  String content=response.body().string();
                    Log.d("Login.Activity","success");
                }
            }
    });
    }
}