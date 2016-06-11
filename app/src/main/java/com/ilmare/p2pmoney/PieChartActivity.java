package com.ilmare.p2pmoney;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/11/2016 8:01 PM
 * 版本号： 1.0
 * 版权所有(C) 6/11/2016
 * 描述：
 * ===============================
 */

public class PieChartActivity extends BaseActivity {
    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.chart)
    PieChart chart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_piechart;
    }

    @Override
    protected void initData() {
        // apply styling
        chart.setDescription("饼图示例");
        //绘制最内层的圆的半径
        chart.setHoleRadius(42f);
        //绘制包裹最内层圆的外层圆的半径
        chart.setTransparentCircleRadius(50f);
        chart.setCenterText("资产分配");
        chart.setCenterTextSize(14f);

        //是否适用百分比显示值
        chart.setUsePercentValues(true);

        PieData mChartData = generateDataPie();
//        mChartData.setValueFormatter(new PercentFormatter());
        mChartData.setValueTextSize(14f);
        mChartData.setValueTextColor(Color.RED);
        // set data
        chart.setData((PieData) mChartData);

        //饼图指示器绘制
        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateXY(900, 900);
    }


    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        //绘制图表数据集合的时候指定属性
        d.setSliceSpace(1f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");

        return q;
    }

    @Override
    protected void initTitleBar() {
        titleRight.setVisibility(View.INVISIBLE);
        titleTv.setText("饼状图");
    }


    @OnClick(R.id.title_left)
    public void onClick() {
        closeCurrent();
    }
}
