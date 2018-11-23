package com.mad.trafficclient.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSqlite extends SQLiteOpenHelper {


    public UserSqlite(Context context) {

        super(context, "UserDB",null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table carinfo(id Integer primary key autoincrement,carid nvarchar(20),money int,aftermoney int,name nvarchar(20),time date)");
        db.execSQL("insert into carinfo(carid,aftermoney) values(?,?)",new Object[]{"辽A10001",100});
        db.execSQL("insert into carinfo(carid,aftermoney) values(?,?)",new Object[]{"辽A10002",99});
        db.execSQL("insert into carinfo(carid,aftermoney) values(?,?)",new Object[]{"辽A10003",103});
        db.execSQL("insert into carinfo(carid,aftermoney) values(?,?)",new Object[]{"辽A10004",1});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
