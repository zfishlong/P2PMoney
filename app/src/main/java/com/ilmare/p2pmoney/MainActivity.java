package com.ilmare.p2pmoney;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ilmare.p2pmoney.Fragement.AssetFragment;
import com.ilmare.p2pmoney.Fragement.HomeFragment;
import com.ilmare.p2pmoney.Fragement.MoreFragment;
import com.ilmare.p2pmoney.Fragement.TouziFragment;
import com.ilmare.p2pmoney.Utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @InjectView(R.id.content)
    FrameLayout content;
    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.tv_hoem)
    TextView tvHoem;
    @InjectView(R.id.iv_touzi)
    ImageView ivTouzi;
    @InjectView(R.id.tv_touzi)
    TextView tvTouzi;
    @InjectView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @InjectView(R.id.iv_asset)
    ImageView ivAsset;
    @InjectView(R.id.tv_asset)
    TextView tvAsset;
    @InjectView(R.id.ll_asset)
    LinearLayout llAsset;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.ll_more)
    LinearLayout llMore;
    @InjectView(R.id.ll_home)
    LinearLayout llHome;


    private FragmentManager supportFragmentManager;

    private HomeFragment homeFragment;
    private TouziFragment touziFragment;
    private AssetFragment assetFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        supportFragmentManager = getSupportFragmentManager();
        llHome.callOnClick();
    }

    @OnClick({R.id.ll_touzi, R.id.ll_asset, R.id.ll_more, R.id.ll_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_touzi:  //投资
                select(1);
                break;
            case R.id.ll_asset:  //我的资产
                select(2);
                break;
            case R.id.ll_more:   //更多
                select(3);
                break;
            case R.id.ll_home:   //首页
                select(0);
                break;
        }
    }

    private void select(int i) {
        ft=supportFragmentManager.beginTransaction();
        resetBottom();
        hideFragment();
        switch (i){
            case 0:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    ft.add(R.id.content,homeFragment);
                }
                ft.show(homeFragment);
                ivHome.setBackgroundResource(R.mipmap.bid01);
                tvHoem.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 1:
                if(touziFragment==null){
                    touziFragment=new TouziFragment();
                    ft.add(R.id.content,touziFragment);
                }
                ivTouzi.setBackgroundResource(R.mipmap.bid03);
                tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(touziFragment);
                break;
            case 2:
                if(assetFragment==null){
                    assetFragment=new AssetFragment();
                    ft.add(R.id.content,assetFragment);
                }

                ivAsset.setBackgroundResource(R.mipmap.bid05);
                tvAsset.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(assetFragment);
                break;
            case 3:
                if(moreFragment==null){
                    moreFragment=new MoreFragment();
                    ft.add(R.id.content,moreFragment);
                }


                ivMore.setBackgroundResource(R.mipmap.bid07);
                tvMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                ft.show(moreFragment);
                break;
        }
        ft.commit();
    }

    private void resetBottom() {
        //图片
        ivHome.setBackgroundResource(R.mipmap.bid02);
        ivTouzi.setBackgroundResource(R.mipmap.bid04);
        ivAsset.setBackgroundResource(R.mipmap.bid06);
        ivMore.setBackgroundResource(R.mipmap.bid08);

        //文字
        tvHoem.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvAsset.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (touziFragment != null) {
            ft.hide(touziFragment);
        }
        if (assetFragment != null) {
            ft.hide(assetFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }

}
