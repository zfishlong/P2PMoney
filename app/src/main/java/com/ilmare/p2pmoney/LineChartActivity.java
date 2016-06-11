package com.ilmare.p2pmoney;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/11/2016 7:15 PM
 * 版本号： 1.0
 * 版权所有(C) 6/11/2016
 * 描述：
 * ===============================
 */

public class LineChartActivity extends BaseActivity {
    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;

    @InjectView(R.id.chart)
    LineChart chart;

    private Typeface mTf;

    @Override
    public int getLayoutId() {
        return R.layout.activity_linechart;
    }

    @Override
    protected void initData() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        //绘制图表右下角文字描述信息
        chart.setDescription("折线图demo");
        chart.setDrawGridBackground(true);

        //绘制图表的X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //绘制图表的Y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        //false:代表值是平均分配的;
        leftAxis.setLabelCount(7, false);
        chart.getAxisRight().setEnabled(false);

        chart.setData(generateDataLine());
        chart.animateX(750);

    }
    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet 1");
        //指定数据集合绘制时候的属性
        d1.setLineWidth(2f);
        d1.setCircleSize(3f);
        d1.setHighLightColor(Color.BLACK);
        d1.setDrawValues(true);

        LineData cd = new LineData(getMonths(), d1);
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

        titleTv.setText("折线图");
        titleRight.setVisibility(View.INVISIBLE);

    }


}
