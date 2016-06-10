package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.Utils.UIUtils;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

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
public class TouziFragment extends BaseFragment {

    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @InjectView(R.id.pager)
    ViewPager pager;
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragments=new ArrayList<Fragment>();

    @Override
    public int getLayoutID() {
        return R.layout.fragment_touzi;
    }


    @Override
    protected String getFragmentUrl() {
        return null;
    }


    @Override
    protected void initData(String content) {
        //初始化数据
        fragmentManager = getFragmentManager();

        fragments.add(new ListProductFragment());
        fragments.add(new HotProductFragment());
        fragments.add(new RecomanderFragment());

        pager.setAdapter(new MyViewPagerAdapter(fragmentManager));
        tabIndictor.setViewPager(pager);
    }


    @Override
    protected void initTitleBar() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.touzi_tab)[position];
        }
    }

}
