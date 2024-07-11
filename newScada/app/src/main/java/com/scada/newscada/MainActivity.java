package com.scada.newscada;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.scada.newscada.sql;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements Runnable{
    //private final int DelayChangeTime = 5;
    public static SharedPreferences sp;
    public static final int VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Thread(this).start();
        //sql Con = new sql();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(3000);//4秒跳转
            sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
            Intent intent=new Intent(MainActivity.this,LiquidBinSysremActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception ignored) {
        }


    }




}