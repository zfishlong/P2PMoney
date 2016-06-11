package com.ilmare.p2pmoney;

import android.app.Activity;
import android.os.Bundle;

import com.ilmare.p2pmoney.Common.AppManager;

import butterknife.ButterKnife;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/11/2016 5:30 PM
 * 版本号： 1.0
 * 版权所有(C) 6/11/2016
 * 描述：
 * ===============================
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.inject(this);
        AppManager.getInstance().addActivity(this);
        initTitleBar();
        initData();
    }
    public abstract   int getLayoutId();

    protected abstract void initData();

    protected abstract void initTitleBar();


    public void closeCurrent(){
        AppManager.getInstance().removeActivity(this);
        this.finish();
    }


}
