package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.mad.trafficclient.R;

import java.util.ArrayList;

public class Fra18_Pie extends Fragment {
    private PieChart pieChart;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fra10__pie, container, false);
        initData();
        return view;
    }

    private void initData() {
        pieChart = view.findViewById(R.id.Pie);
        PieData pieData = getData(4,100);
        setPieChart(pieData,pieChart);
    }
    private void setPieChart(PieData pieData,PieChart pieChart){
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(0);
        pieChart.setDescription("");
        pieChart.setTouchEnabled(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(180);
        pieChart.setRotationEnabled(true);//可以手动旋转
        pieChart.setUsePercentValues(true);//显示成百分比


        pieChart.setData(pieData);
        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(7f);
        pieChart.animateXY(1000,1000);
    }
    private PieData getData(int count,int a){
        ArrayList<String> x = new ArrayList<>();
        x.add("温度");
        x.add("湿度");
        x.add("CO2");
        x.add("光照");
        x.add("PM2.5");

        ArrayList<Entry> y = new ArrayList<>();
        float y1 = 20;
        float y2 = 30;
        float y3 = 30;
        float y4 = 10;
        float y5 = 10;

        y.add(new Entry(y1,0));
        y.add(new Entry(y2,1));
        y.add(new Entry(y3,2));
        y.add(new Entry(y4,3));
        y.add(new Entry(y5,4));

        PieDataSet pieDataSet = new PieDataSet(y,null);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(0,250,100));
        colors.add(Color.rgb(100,200,0));
        colors.add(Color.rgb(0,250,100));
        colors.add(Color.rgb(250,0,100));
        colors.add(Color.rgb(100,200,100));

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(x,pieDataSet);
        return pieData;
    }
}
