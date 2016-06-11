package com.ilmare.p2pmoney.Fragement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilmare.p2pmoney.Beans.UserInfo;
import com.ilmare.p2pmoney.Common.AppNetConfig;
import com.ilmare.p2pmoney.LineChartActivity;
import com.ilmare.p2pmoney.LoginActivity;
import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.UserInfoActivity;
import com.ilmare.p2pmoney.Utils.BitMapUtil;
import com.ilmare.p2pmoney.ChongZhiActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/7/2016 1:44 PM
 * 版本号： 1.0
 * 版权所有(C) 6/7/2016
 * 描述：
 * ===============================
 */

public class AssetFragment extends BaseFragment {


    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;


    @InjectView(R.id.imageView1)
    ImageView imageView1;
    @InjectView(R.id.icon_time)
    RelativeLayout iconTime;
    @InjectView(R.id.textView11)
    TextView textView11;
    @InjectView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;

    @InjectView(R.id.chongzhi)
    ImageView chongzhi;
    @InjectView(R.id.tixian)
    ImageView tixian;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichang)
    TextView llZichang;
    @InjectView(R.id.ll_zhanquan)
    TextView llZhanquan;
    private SharedPreferences sharedPreferences;


    @Override
    public int getLayoutID() {
        return R.layout.fragment_asset;
    }


    @Override
    protected String getFragmentUrl() {
        return null;
    }


    @Override
    protected void initData(String content) {
        System.out.println(AppNetConfig.LOGIN);

        if(isLogin()){
            UserInfo info=getLoginUser();
            Picasso.with(getActivity()).load(info.getUF_AVATAR_URL()).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    Bitmap bitmap = BitMapUtil.circleBitMap(source);
                    source.recycle();
                    return bitmap;
                }

                @Override
                public String key() {
                    return "";
                }
            }).into(imageView1);
        }else{
            doLogin();
        }
    }

    private UserInfo getLoginUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUF_PHONE(sharedPreferences.getString("UF_PHONE",""));
        userInfo.setUF_ACC(sharedPreferences.getString("UF_ACC", ""));
        userInfo.setUF_AVATAR_URL(sharedPreferences.getString("UF_AVATAR_URL", ""));
        return userInfo;
    }

    private void doLogin() {
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }


    @Override
    protected void initTitleBar() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_right)
    public void setUserImage(View view){
        Intent intent=new Intent(getActivity(), UserInfoActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick({R.id.ll_touzi,R.id.chongzhi,R.id.ll_touzi_zhiguan, R.id.ll_zichang, R.id.ll_zhanquan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_touzi:
                getActivity().startActivity(new Intent(getActivity(), LineChartActivity.class));
                break;
            case R.id.ll_touzi_zhiguan:
                break;
            case R.id.ll_zichang:
                break;
            case R.id.ll_zhanquan:
                break;
            case R.id.chongzhi:
                getActivity().startActivity(new Intent(getActivity(), ChongZhiActivity.class));
                break;
        }
    }

    public boolean isLogin() {

        sharedPreferences = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        String UF_ACC=sharedPreferences.getString("UF_ACC",null);
        return !TextUtils.isEmpty(UF_ACC);
    }
}
