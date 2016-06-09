package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ilmare.p2pmoney.Beans.Image;
import com.ilmare.p2pmoney.Beans.Index;
import com.ilmare.p2pmoney.Beans.ProInfo;
import com.ilmare.p2pmoney.Common.AppNetConfig;
import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.View.OverScrollView;
import com.ilmare.p2pmoney.View.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

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

public class HomeFragment extends Fragment {


    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.vp_barner)
    ViewPager vpBarner;
    @InjectView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @InjectView(R.id.textView1)
    TextView textView1;
    @InjectView(R.id.p_progresss)
    RoundProgress pProgresss;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.button1)
    Button button1;
    @InjectView(R.id.myscrollview)
    OverScrollView myscrollview;
    private Index index;
    private MyBanerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTitleBar();
        initData();
    }


    private void initData() {
        //加载数据
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(getActivity(), AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {

                index = new Index();
                JSONObject jsonObject = JSON.parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                ProInfo info = JSON.parseObject(proInfo, ProInfo.class);

                String imagArra = jsonObject.getString("imageArr");
                ArrayList<Image> images = (ArrayList<Image>) JSON.parseArray(imagArra, Image.class);

                index.setImageArr(images);
                index.setProInfo(info);

                adapter = new MyBanerAdapter();
                vpBarner.setAdapter(adapter);
                circleBarner.setViewPager(vpBarner);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);

            }
        });


    }


    private void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private class MyBanerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return index.getImageArr().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String imageUrl = index.getImageArr().get(position).getIMAURL();
            Picasso.with(getActivity()).load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
