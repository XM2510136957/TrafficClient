package com.mad.trafficclient.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    private static String sql = "create table chargeInfo(" +
            "id integer primary key autoincrement," +
            "carNumber int not null," +
            "charge double not null," +
            "userName nvarchar(20) not null," +
            "chargeDate nvarchar(40) not null" +
            ")";
    private static String sql2 = "create table userInfo(" +
            "id integer primary key autoincrement," +
            "userName nvarchar(20) not null," +
            "sex nvarchar(2) not null," +
            "phone nvarchar(11) not null," +
            "sfz nvarchar(25) not null," +
            "date nvarchar(20) not null" +
            ")";
    private static String sql3 = "create table carInfo(" +
            "id integer primary key autoincrement," +
            "carNumber nvarchar(30) not null," +
            "carCharge integer not null," +
            "userId integer not null references userInfo(id)" +
            ")";
    private static String sql4 = "insert into userInfo(userName,sex,phone,sfz,date) values('张三','男','13813419341','1341341839401531','2018-02-08 12:00')," +
            "('李四','男','13875322221','1348987979401531','2018-02-01 13:00')," +
            "('王五','男','13811341341','1346246249401531','2018-01-22 14:00')," +
            "('赵六','女','13143393341','1341346729401531','2018-07-10 15:00')";
    private static String sql5 = "insert into carInfo(carNumber,carCharge,userId) values('辽A10001',100,1),('辽A10002',99,2),('辽10003',103,3),('辽A10004',1,4)";

    private static String sql6 = "create table czjl(" +
            "id integer primary key autoincrement," +
            "userName nvarchar(20) not null," +
            "carNumber nvarchar(30) not null," +
            "charge int not null," +
            "balance int not null," +
            "time nvarchar(30) not null" +
            ")";

    public DBConnection(@Nullable Context context) {
        super(context, "trafficDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
