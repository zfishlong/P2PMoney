package com.ilmare.p2pmoney;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ilmare.p2pmoney.Beans.UserInfo;
import com.ilmare.p2pmoney.Common.AppNetConfig;
import com.ilmare.p2pmoney.Utils.MD5Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 8:48 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class LoginActivity extends Activity {

    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;

    @InjectView(R.id.log_ed_mob)
    EditText logEdMob;
    @InjectView(R.id.log_ed_pad)
    EditText logEdPad;
    @InjectView(R.id.log_log_btn)
    Button logLogBtn;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        initTitleBar();
        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
    }


    private void initTitleBar() {
        titleTv.setText("登录");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.log_log_btn)
    public void onClick() {
        String username = logEdMob.getText().toString();
        String password = logEdPad.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }

        //MD5加密
        AsyncHttpClient httpClient =new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",username);
        params.put("password", MD5Utils.MD5(password));

        httpClient.post(this, AppNetConfig.LOGIN,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                JSONObject jsonObject= JSON.parseObject(content);
                String data=jsonObject.getString("data");
                UserInfo userInfo = JSON.parseObject(data, UserInfo.class);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("UF_ACC",userInfo.getUF_ACC());
                edit.putString("UF_AVATAR_URL",userInfo.getUF_AVATAR_URL());
                edit.putString("UF_PHONE",userInfo.getUF_PHONE());
                edit.commit();

                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
