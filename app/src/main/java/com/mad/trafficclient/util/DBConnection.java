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
    public DBConnection(@Nullable Context context) {
        super(context, "trafficDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
