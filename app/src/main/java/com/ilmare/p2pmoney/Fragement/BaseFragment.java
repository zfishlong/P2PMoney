package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilmare.p2pmoney.Utils.UIUtils;
import com.ilmare.p2pmoney.View.LoadingPage;

import butterknife.ButterKnife;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 9:02 AM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public abstract class BaseFragment extends Fragment {


    private LoadingPage page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        page = new LoadingPage(getActivity()) {

            @Override
            protected void onCreateSuccessView(View successView) {
                ButterKnife.inject(BaseFragment.this, successView);
            }

            @Override
            protected void onGetDataSuccess(String content) {
                initData(content);
                initTitleBar();
            }

            @Override
            public int getLayoutId() {
                return getLayoutID();
            }

            @Override
            public String getUrl() {
                return getFragmentUrl();
            }
        };

        return page;
    }

    public abstract int getLayoutID();

    protected abstract String getFragmentUrl();

    protected abstract void initData(String content);

    protected abstract void initTitleBar();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page.show();
            }
        }, 2000);
    }


}
