package com.ilmare.p2pmoney.Fragement;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

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

public class HomeFragment extends BaseFragment {


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


    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getFragmentUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initData(String content) {
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

        final int progress = Integer.parseInt(index.getProInfo().getProgress());

        new Thread() {
            @Override
            public void run() {
                int i = 50;
                while (i != progress) {
                    i++;
                    pProgresss.setProgress(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public  void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
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
            System.out.println(imageUrl);
            imageUrl=imageUrl.replace("192.168.56.1:8080","ilmare.site");
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
