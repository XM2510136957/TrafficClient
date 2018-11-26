package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.Billing;
import com.mad.trafficclient.dao.UserDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_4 extends Fragment {
    private TextView tv_balance;
    private Spinner s_car_number;
    private EditText et_charge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout04, container, false);
        requestData();
        initUI(view);
        return view;
    }

    //用户所对应车辆余额
    private int[] charges;
    private void requestData() {
        charges = new int[]{
                55, 83, 100
        };
    }

    private void initUI(View view) {
        tv_balance = view.findViewById(R.id.tv_balance);
        s_car_number = view.findViewById(R.id.s_car_number);
        et_charge = view.findViewById(R.id.et_charge);
        //初始化小车编号
        final String[] carNumber = new String[charges.length];
        for(int i=0;i<charges.length;i++){
            carNumber[i] = ""+(i+1);
        }
        s_car_number.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,carNumber));
        //查询按钮
        view.findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCharge();
                Toast.makeText(getActivity(),"查询成功！",Toast.LENGTH_SHORT).show();
            }
        });
        //充值按钮
        view.findViewById(R.id.btn_charge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] charArray = et_charge.getText().toString().trim().toCharArray();
                //判断是否为字符串
                boolean isString = false;
                for(int i=0;i<charArray.length;i++){
                    if(charArray[i]< '0' || charArray[i] > '9'){
                        isString = true;
                    }
                }
                //不为字符串逻辑
                if(!isString){
                    int charge = Integer.parseInt(et_charge.getText().toString().trim());
                    //判断输入金额1~999元，并且不为浮点数（是否为整数）
                    if (charge >= 1 && charge <= 999 && charge == Double.parseDouble(et_charge.getText().toString().trim())) {
                        int position = s_car_number.getSelectedItemPosition();
                        charges[position] += charge;
                        updateCharge();
                        record(position, charge);
                        Toast.makeText(getActivity(), "充值成功！", Toast.LENGTH_SHORT).show();
                    }
                }else{//否则提示输入错误
                    Toast.makeText(getActivity(), "请输入金额1~999元整数", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //保存充值记录到SQLite数据库
    private void record(int position,int charge) {
        Billing billing = new Billing(position+1,charge,"刘聪",getDate());
        UserDao userDao = new UserDao(getActivity());
        userDao.open();
        if(userDao.insert(billing) > 0){
            Log.e("添加记录","成功！");
        }else{
            Log.e("添加记录","失败！");
        }
        userDao.close();
    }

    //获取当前时间
    private String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return simpleDateFormat.format(new Date());
    }

    //更新当前余额
    private void updateCharge() {
        int charge = charges[s_car_number.getSelectedItemPosition()];
        tv_balance.setText(charge+"元");
        et_charge.setText("");
    }
}
