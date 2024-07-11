package com.scada.newscada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

public class sql {

    // 数据库连接信息
    //private static final String DB_URL = "jdbc:jtds:sqlserver://39.102.208.175:1433;DatabaseName=mydatabase";
    //private static final String USER = "sa";
    //private static final String PASS = "123456";

    private static final String DB_URL = "jdbc:jtds:sqlserver://39.102.208.175:1433;databaseName=mydatabase;useunicode=true;characterEncoding=UTF-8";
    private static final String USER = "sa";
    private static final String PASS = "123456";

    private static List<Entry> entries;

    static Connection getSQLConnection() {
        Connection con = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            //con = DriverManager.getConnection("jdbc:jtds:sqlserver://39.102.208.175:1433;databaseName=mydatabase;user=sa;password=123456");
        } catch (ClassNotFoundException e) {
            System.out.println("连接失败notfound");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("连接失败sqlexce");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("连接失败sbexcep");
        }
        return con;
    }

    public static float Query(TextView aFadeback) {
        //String result = "";
        float result = 0;
        try {
            Connection conn = getSQLConnection();
            //conn = getSQLConnection();
            if (conn != null){
                aFadeback.setText("连接成功");
            }
            else {
                aFadeback.setText("连接失败");
            }
            //String sql = "select * from Liquidbin";
            String sql = "SELECT TOP 1 * FROM liq ORDER BY DATA DESC";
            Statement stmt = conn.createStatement();//
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
               // result = rs.getString("cola_shuiwei1");
                result = rs.getFloat("VALUE");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //result += "查询数据异常!" + e.getMessage();
        }
        return result;
    }

    public static List<Entry> GetHistoricalData() {
        entries = new ArrayList<>();
        entries.clear();
        float a = 0;
        try {
            Connection conn = getSQLConnection();
            //conn = getSQLConnection();
            if (conn == null)
                System.out.println("连接历史失败");
            else
                System.out.println("连接历史成功");
            //String sql = "select * from Liquidbin";
            //String sql = "SELECT TOP 16 * FROM DIV_HISTRECORD1 ORDER BY Triggertime DESC";
            String sql = "SELECT TOP 19 * FROM DIV_HISTRECORD1 ORDER BY Triggertime DESC";
            Statement stmt = conn.createStatement();//
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while(rs.next()) {
                a = rs.getFloat(3);
                entries.add(new Entry(i, a));
                i++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //result += "查询数据异常!" + e.getMessage();
        }
        return entries;
    }


}

