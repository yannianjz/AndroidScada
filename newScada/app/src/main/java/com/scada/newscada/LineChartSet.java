package com.scada.newscada;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LineChartSet {
    LineChart LiquidLineChar;
    public LineChartSet(LineChart aLinechart){

        LiquidLineChar = aLinechart;
        //SetLineChart();
    }
;    private List<Entry> entries;

    private void SetData(){
        entries = new ArrayList<>();
        for(int i=0;i<24;i++){
            entries.add(new Entry(i,new Random().nextInt(30)));
        }
    }

    void SetLineChart(){
        SetData();
        LineDataSet dataSet = new LineDataSet(entries,"液位");
        dataSet.setColor(Color.parseColor("#FF0000"));
        dataSet.setCircleColor(Color.parseColor("#000000"));
        dataSet.setDrawValues(false);

        dataSet.setCircleRadius(1f);
        dataSet.setLineWidth(1.5f);
        dataSet.setValueTextSize(0f);

        XAxis xAxis = LiquidLineChar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis yAxis = LiquidLineChar.getAxisLeft();
        yAxis.setLabelCount(5,false);

    }

}
