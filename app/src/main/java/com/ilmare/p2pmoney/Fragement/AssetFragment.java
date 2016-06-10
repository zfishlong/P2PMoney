package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilmare.p2pmoney.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
        
    }


    @Override
    protected void initTitleBar() {
        titleTv.setText("我要投资");
    }


}
