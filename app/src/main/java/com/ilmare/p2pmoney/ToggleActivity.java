package com.ilmare.p2pmoney;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ilmare.p2pmoney.Utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/11/2016 8:20 PM
 * 版本号： 1.0
 * 版权所有(C) 6/11/2016
 * 描述：
 * ===============================
 */

public class ToggleActivity extends BaseActivity {
    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.tg)
    ToggleButton tg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle;
    }

    @Override
    protected void initData() {
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //密码已经开启
                    UIUtils.Toast("密码已经开启", false);
                } else {
                    UIUtils.Toast("密码已经关闭", false);
                }
            }
        });
    }

    @Override
    protected void initTitleBar() {

    }


    @OnClick(R.id.tg)
    public void onClick() {

    }



    @OnClick(R.id.title_left)
    public void back() {

    }
}
