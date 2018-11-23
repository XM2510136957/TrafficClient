package com.mad.trafficclient.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.Entity.InfoEntity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UserSqlite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_9data1 extends Fragment {
    private TextView carid,money1,money2,money3,money;
    private Button pays;
    private View view;
    private List<InfoEntity> list;
    private UserSqlite userSqlite;
    private int j;
    private int mon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_9data1, container, false);
        initData();
        return view;
    }

    private void initData() {
        carid = view.findViewById(R.id.carid);
        money1 = view.findViewById(R.id.money1);
        money = view.findViewById(R.id.money);
        money2 = view.findViewById(R.id.money2);
        money3 = view.findViewById(R.id.money3);
        pays = view.findViewById(R.id.payup);
        ArrayList<InfoEntity> arrayList = (ArrayList<InfoEntity>) query();
        j = (int) (Math.random()*3);
        carid.setText(list.get(j).getCardId());


        money1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = money1.getText().toString().trim();
                mon = Integer.parseInt(m.substring(0,2));
                money.setText(mon+"");
            }
        });
        money2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = money2.getText().toString().trim();
                mon = Integer.parseInt(m.substring(0,2));
                money.setText(mon+"");
            }
        });
        money3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = money3.getText().toString().trim();
                mon = Integer.parseInt(m.substring(0,2));
                money.setText(mon+"");
            }
        });
        pays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int paymoney = Integer.parseInt(money.getText().toString());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
                String s = sdf.format(date);
                if(paymoney<0||paymoney>999){
                    Toast.makeText(getActivity(),"请输入1-999的充值金额",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = userSqlite.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("carid",list.get(j).getCardId());
                    contentValues.put("money",mon);
                    contentValues.put("time",s);
                    long insert = db.insert("carinfo",null,contentValues);
                    if(insert>0){
                        Toast.makeText(getActivity(),"充值成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"充值失败",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    private List<InfoEntity> query(){
        list = new ArrayList<>();
        userSqlite = new UserSqlite(getActivity());
        SQLiteDatabase db = userSqlite.getReadableDatabase();
        Cursor cursor = db.query("carinfo",null,null,null,null,null,null);
        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String carid = cursor.getString(1);
                int money = cursor.getInt(2);
                int aftermoney = cursor.getInt(3);
                String name = cursor.getString(4);
                String time = cursor.getString(5);
                InfoEntity infoEntity = new InfoEntity(id,carid,money,name,time);
                list.add(infoEntity);
            }
            return list;
        }
        return null;
    }
}
