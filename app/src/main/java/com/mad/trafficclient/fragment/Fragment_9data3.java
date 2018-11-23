package com.mad.trafficclient.fragment;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mad.trafficclient.Entity.InfoEntity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UserSqlite;

import java.util.ArrayList;
import java.util.List;

public class Fragment_9data3 extends Fragment {
    private TextView tv_money;
    private TableLayout tb;
    private TableRow row;
    private List<InfoEntity> list;
    private int allmoney;
    private View view;
    private UserSqlite userSqlite;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_9data3, container, false);
        initData();
        return view;
    }

    private void initData() {
        tv_money = view.findViewById(R.id.all);
        tb = view.findViewById(R.id.tb);
        ArrayList<InfoEntity> list = (ArrayList<InfoEntity>) query();
        newTable(list);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void newTable(List<InfoEntity> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMoney()==0){
                continue;
            }else{
                allmoney+=list.get(i).getMoney();
                tv_money.setText(allmoney+"");

                row = new TableRow(view.getContext());

                TextView text = new TextView(view.getContext());
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f);
                text.setLayoutParams(layoutParams);
                text.setText(list.get(i).getNO()+"");
                text.setTextSize(20);
                text.setGravity(Gravity.CENTER);
                text.setBackground(getActivity().getDrawable(R.drawable.border_write));
                row.addView(text);

                TextView text2 = new TextView(view.getContext());
                text2.setLayoutParams(layoutParams);
                text2.setText(list.get(i).getCardId()+"");
                text2.setTextSize(20);
                text2.setGravity(Gravity.CENTER);
                text2.setBackground(getActivity().getDrawable(R.drawable.border_write));
                row.addView(text2);

                TextView text3 = new TextView(view.getContext());
                text3.setLayoutParams(layoutParams);
                text3.setText(list.get(i).getMoney()+"");
                text3.setTextSize(20);
                text3.setGravity(Gravity.CENTER);
                text3.setBackground(getActivity().getDrawable(R.drawable.border_write));
                row.addView(text3);

                TextView text4 = new TextView(view.getContext());
                text4.setLayoutParams(layoutParams);
                text4.setText(list.get(i).getTime()+"");
                text4.setTextSize(20);
                text4.setGravity(Gravity.CENTER);
                text4.setBackground(getActivity().getDrawable(R.drawable.border_write));
                row.addView(text4);

                tb.addView(row);
            }

        }
    }
    private List<InfoEntity> query(){
        list = new ArrayList<>();
        userSqlite = new UserSqlite(getActivity());
        db = userSqlite.getReadableDatabase();
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
