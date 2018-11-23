package com.mad.trafficclient.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import com.mad.trafficclient.R;

import org.w3c.dom.Text;

public class Fragment_8 extends Fragment {
    private TextView[] txts;
    private Switch[] switches;
    private TextView tv_date;
    private TextView tv_cx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout08, container, false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {
        tv_cx = view.findViewById(R.id.tv_cx);
        tv_date = view.findViewById(R.id.tv_date);
        txts = new TextView[]{
                view.findViewById(R.id.txt_1),
                view.findViewById(R.id.txt_2),
                view.findViewById(R.id.txt_3)
        };
        switches = new Switch[]{
                view.findViewById(R.id.switch1),
                view.findViewById(R.id.switch2),
                view.findViewById(R.id.switch3)
        };
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strs= ((TextView)v).getText().toString().split("年");
                int year = Integer.parseInt(strs[0]);
                String[] strs2 = strs[1].split("月");
                int month = Integer.parseInt(strs2[0]);
                final int day = Integer.parseInt(strs2[1].split("日")[0]);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_date.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                        setSwitche(dayOfMonth);
                    }
                },year,month-1,day).show();
            }
        });

        setSwitche(Integer.parseInt(tv_date.getText().toString().split("月")[1].split("日")[0]));
    }

    private void setSwitche(int day) {
        if(day%2 == 0){
            switches[1].setChecked(true);
            switches[1].setEnabled(true);
            txts[1].setText("开");

            switches[0].setChecked(false);
            switches[0].setEnabled(false);
            txts[0].setText("关");

            switches[2].setChecked(false);
            switches[2].setEnabled(false);
            txts[2].setText("关");

            tv_cx.setText("双号出行车辆：2");
        }else{
            switches[0].setChecked(true);
            switches[0].setEnabled(true);
            txts[0].setText("开");

            switches[1].setChecked(false);
            switches[1].setEnabled(false);
            txts[1].setText("关");

            switches[2].setChecked(true);
            switches[2].setEnabled(true);
            txts[2].setText("开");

            tv_cx.setText("单号出行车辆：1、3");
        }
    }
}
