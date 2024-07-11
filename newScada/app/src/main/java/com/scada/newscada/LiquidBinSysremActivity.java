package com.scada.newscada;
import static com.scada.newscada.sql.getSQLConnection;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.LineChart;

import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.scada.newscada.LineChartSet;


public class LiquidBinSysremActivity extends AppCompatActivity implements Runnable{
    private final float LiquidLevel = 0;
    private final float PipeStress = 0;
    private final float LiquidQ = 0;
    private final float MotorFrequence = 0;

    private List<Entry> entries;
    private List<Entry> useentries;

    private TextView ShowLiquidLevel;
    private TextView ShowPipeStress;
    private TextView ShowLiquidQ;
    private TextView ShowMotorFrequence;

    private LineChart aLiquidLineChar;

    private final Timer timer=new Timer();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liquid_bin_sysrem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ShowLiquidLevel = findViewById(R.id.textView_LiquidLevelNum);
        ShowPipeStress = findViewById(R.id.textView_PipeStressNum);
        aLiquidLineChar = findViewById(R.id.LiquidLineChar);
        ShowLiquidLevel.setText("23.1");
        new Thread(this).start();
        //LineChartSet Chart = new LineChartSet(aLiquidLineChar);
        //Chart.SetLineChart();
        //sql.Sqlconect(ShowLiquidLevel);

    }

//    protected void onStart() {
//
//        super.onStart();
//        SetLineChart(SetData());
//    }

    /*
    protected  void onStart() {
        super.onStart();
        update(); // 延迟 1 秒后第一次执行
    }
     */

    public void run() {
        entries = new ArrayList<>();
        int Xindex = 0;

        //Connection conn = getSQLConnection();
        while (true){
            try {
                float result = sql.Query(ShowPipeStress);

                entries.add(new Entry(Xindex, result)) ;
                Xindex++;
                if (result>25||result<5)
                    ShowLiquidLevel.setTextColor(Color.parseColor("#FF0000"));
                else
                    ShowLiquidLevel.setTextColor(Color.parseColor("#000000"));
                String text = String.valueOf(result);
                ShowLiquidLevel.setText(text);
                //ShowPipeStress.set
                //sql.anAttempt(ShowPipeStress);
            } catch (Exception e) {
                //ShowLiquidLevel.setText("查询失败");
                System.out.println("查询失败");
            }
            SetLineChart(entries);
//            try{
//                SetLineChart(sql.GetHistoricalData());
//
//            } catch (Exception e) {
//                //ShowLiquidLevel.setText("查询失败");
//                System.out.println("查询历史数据失败");
//            }
    }
    }

    /*
    private void update(){
        //启动定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Connection conn = getSQLConnection();
                float result = sql.Query(conn,ShowLiquidLevel);
                String text = String.valueOf(result);
                ShowPipeStress.setText(text);
            }
        },0,500); //延迟500毫秒后，执行一次task
    }

     */




    private List<Entry> SetData(){
        entries = new ArrayList<>();
        for(int i=0;i<12;i++){
            entries.add(new Entry(i,new Random().nextInt(30)));
            System.out.println(i);
        }
        return entries;
    }

    private void SetLineChart(List<Entry> entries){
        //SetData();
        LineDataSet dataSet = new LineDataSet(entries,"液位");
        dataSet.setColor(Color.parseColor("#000000"));
        dataSet.setCircleColor(Color.parseColor("#000000"));
        dataSet.setDrawValues(false);
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(false);
        dataSet.setLineWidth(1.5f);
        //dataSet.setValueTextSize(17f);

        XAxis xAxis = aLiquidLineChar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis yAxis = aLiquidLineChar.getAxisLeft();
        //yAxis.setAxisMinimum(0);
        //yAxis.setAxisMaxValue(40);
        //yAxis.setLabelCount(5,false);
        LineData linedata = new LineData(dataSet);
        aLiquidLineChar.setData(linedata);
        aLiquidLineChar.invalidate(); // 刷新图表显示

    }

    private void HistoricalData(){
        Connection conn = getSQLConnection();

    }

}