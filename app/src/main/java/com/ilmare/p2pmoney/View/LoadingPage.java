package com.ilmare.p2pmoney.View;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.Utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 9:52 AM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public abstract class LoadingPage extends FrameLayout {


    private LayoutParams lp;
    private Context mContext;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initView();
    }



    private static final int PAGE_LOADING_STATE = 1;

    private static final int PAGE_ERROR_STATE = 2;

    private static final int PAGE_EMPTY_STATE = 3;

    private static final int PAGE_SUCCESS_STATE = 4;

    private int PAGE_CURRENT_STATE = 1;

    private View errorPage;
    private View emptyPage;
    private View loadingPage;
    private View successView;

    private AsyncHttpClient client=new AsyncHttpClient();

    private void initView() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if(errorPage==null){
            errorPage= UIUtils.getXmlView(R.layout.page_error);
            addView(errorPage, lp);
        }


        if(emptyPage==null){
            emptyPage=UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyPage, lp);
        }


        if(loadingPage==null){
            loadingPage=UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingPage,lp);
        }

        showSafePage();

    }

    private void showSafePage() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    //内部调用的方法
    private void showPage(){
            loadingPage.setVisibility(PAGE_CURRENT_STATE==PAGE_LOADING_STATE?VISIBLE:INVISIBLE);
            emptyPage.setVisibility(PAGE_CURRENT_STATE==PAGE_EMPTY_STATE?VISIBLE:INVISIBLE);
            errorPage.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? VISIBLE : INVISIBLE);
            if(PAGE_CURRENT_STATE==PAGE_SUCCESS_STATE){

                if(successView==null) {
                    successView=View.inflate(mContext, getLayoutId(), null);;
                    addView(successView,lp);
                    onCreateSuccessView(successView);
                }

                successView.setVisibility(PAGE_CURRENT_STATE==PAGE_SUCCESS_STATE?VISIBLE:INVISIBLE);
        }
    }

    protected abstract void onCreateSuccessView(View successView);

    //外部调用的方法
    public void show(){
        //重置
        if(PAGE_CURRENT_STATE!=PAGE_LOADING_STATE){
            PAGE_CURRENT_STATE=PAGE_LOADING_STATE;
        }

        if(TextUtils.isEmpty(getUrl())){
            PAGE_CURRENT_STATE=PAGE_SUCCESS_STATE;
            showSafePage();
            onGetDataSuccess("");
        }else{

            //创建网络请求
            client.get(mContext, getUrl(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    if (TextUtils.isEmpty(content)) {
                        PAGE_CURRENT_STATE = PAGE_EMPTY_STATE;
                    } else {
                        PAGE_CURRENT_STATE = PAGE_SUCCESS_STATE;
                    }
                    showSafePage();
                    onGetDataSuccess(content);
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    PAGE_CURRENT_STATE = PAGE_ERROR_STATE;
                    showSafePage();
                }
            });


        }

    }

    protected abstract void onGetDataSuccess(String content);

    public abstract int getLayoutId();

    public abstract  String getUrl();

}
