package com.mad.trafficclient.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mad.trafficclient.Entity.InfoEntity;
import com.mad.trafficclient.bean.Billing;
import com.mad.trafficclient.util.DBConnection;

public class UserDao {
    private SQLiteDatabase db = null;
    private DBConnection dbc = null;
    private Context context = null;

    public UserDao(Context context){
        this.context = context;
    }

    public UserDao open(){
        dbc = new DBConnection(context);
        db = dbc.getWritableDatabase();
        return this;
    }

    public Cursor query(String sql){
        return db.rawQuery(sql,null);
    }

    public long insert(Billing billing){
        ContentValues values = new ContentValues();
        values.put("carNumber",billing.getCarId());
        values.put("charge",billing.getRecharge());
        values.put("userName",billing.getOperator());
        values.put("chargeDate",billing.getTime());
        return db.insert("chargeInfo",null,values);
    }

    public void close(){
        dbc.close();
        db.close();
    }
}
