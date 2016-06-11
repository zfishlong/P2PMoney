package com.ilmare.p2pmoney;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/11/2016 7:49 PM
 * 版本号： 1.0
 * 版权所有(C) 6/11/2016
 * 描述：
 * ===============================
 */

public class BarChatActivity extends BaseActivity {
    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.chart)
    BarChart chart;

    private Typeface mTf;
    @Override
    public int getLayoutId() {
        return R.layout.activity_barchart;
    }

    @Override
    protected void initData() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        chart.setDescription("柱状图示例");
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        //绘制Y轴方向上最高的顶部的值距离整个图表的top距离
        leftAxis.setSpaceTop(30f);

        chart.getAxisRight().setEnabled(false); //禁掉右边的x轴 因为不好看

//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setSpaceTop(20f);

        BarData mChartData = generateDataBar();
        mChartData.setValueTypeface(mTf);

        // set data
        chart.setData(mChartData);
        // do not forget to refresh the chart
//       chart.invalidate();
        chart.animateY(700);

    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet 1");
        //绘制数据集合时指定属性
        d.setBarSpacePercent(30f); //柱状块之间的相隔距离
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);
        BarData cd = new BarData(getMonths(), d);

        return cd;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");
        return m;
    }

    @Override
    protected void initTitleBar() {
        titleRight.setVisibility(View.INVISIBLE);
        titleTv.setText("柱状图");
    }

    @OnClick(R.id.title_left)
    public void onClick() {
        closeCurrent();
    }
}
