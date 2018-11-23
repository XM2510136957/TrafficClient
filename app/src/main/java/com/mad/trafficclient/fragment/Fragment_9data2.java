package com.mad.trafficclient.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mad.trafficclient.Entity.InfoEntity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UserSqlite;

import java.util.ArrayList;
import java.util.List;

public class Fragment_9data2 extends Fragment {
    private View view;
    private EditText et_id;
    private TextView tv_money;
    private Button bt_query;
    private List<InfoEntity> list;
    private List<InfoEntity> list1;
    private UserSqlite userSqlite;
    private String carid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_9data2, container, false);
        initData();
        return view;
    }

    private void initData() {
        et_id = view.findViewById(R.id.id);
        tv_money = view.findViewById(R.id.money);
        bt_query = view.findViewById(R.id.query);
        bt_query.setOnClickListener(new View.OnClickListener() {
            int sum = 0;
            int j = 0;
            int allmoney = 0;
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(et_id.getText().toString());
                ArrayList<InfoEntity> arrayList = (ArrayList<InfoEntity>) query();
                for (int i =0;i<arrayList.size();i++){
                    if(id == arrayList.get(i).getNO()){
                        carid = arrayList.get(i).getCardId();
                        j = i;
                        break;
                    }
                }
                for (int i = 0;i<list1.size();i++ ){
                    if(list.get(i).getCardId().equals(carid)){
                        sum+=list1.get(i).getMoney2();
                    }
                }
                allmoney = list.get(j).getMoney()+sum;
                tv_money.setText(allmoney+"");
            }
        });

    }
    private List<InfoEntity> query(){
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        userSqlite = new UserSqlite(getActivity());
        SQLiteDatabase database = userSqlite.getReadableDatabase();
        Cursor cursor = database.query("carinfo", null, null, null, null, null, null);
        if(cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String carid = cursor.getString(1);
                int money = cursor.getInt(2);
                int aftermoney = cursor.getInt(3);
                String name = cursor.getString(4);
                String time = cursor.getString(5);
                InfoEntity infoEntity = new InfoEntity(id,carid,aftermoney,name,time);
                InfoEntity infoEntity1 = new InfoEntity(money);
                list.add(infoEntity);
                list1.add(infoEntity1);
            }
            return list;
        }
       return null;
    }


}
