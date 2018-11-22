package com.mad.trafficclient.fragment;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.ArrayList;

import Entity.MyMsgEntity;

public class Fragment_18 extends Fragment {
    private TableLayout tb;
    private TableRow row;
    private View view;
    private ArrayList<MyMsgEntity> list;
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private String msg[] = {"全部", "湿度", "温度", "CO2", "光照", "PM2.5"};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_18, container, false);
        initData();
        Checked();
        return view;
    }

    private void initData() {
        tb = view.findViewById(R.id.tb);
        spinner = view.findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, msg);
        spinner.setAdapter(arrayAdapter);
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MyMsgEntity myMsgEntity = new MyMsgEntity((i+1), "湿度", 80, 85);
            list.add(myMsgEntity);
        }
        for (int i = 0; i < 3; i++) {
            MyMsgEntity myMsgEntity = new MyMsgEntity((i+1), "温度", 80, 85);
            list.add(myMsgEntity);
        }
        for (int i = 0; i < 3; i++) {
            MyMsgEntity myMsgEntity = new MyMsgEntity((i+1), "CO2", 80, 85);
            list.add(myMsgEntity);
        }
    }

    private void Checked() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showTable("全部");
                        break;
                    case 1:
                        showTable("湿度");
                        break;
                    case 2:
                        showTable("温度");
                        break;
                    case 3:
                        showTable("CO2");
                        break;
                    case 4:
                        showTable("光照");
                        break;
                    case 5:
                        showTable("PM2.5");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showTable(String type) {
        if (tb.getChildCount() > 1) {
            tb.removeViews(1, tb.getChildCount() - 1);
        }

        for (int i = 0; i < list.size(); i++) {
            if (type.equals(list.get(i).getType())) {
                row = new TableRow(view.getContext());

                TextView textView = new TextView(view.getContext());
                TableRow.LayoutParams weight = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                textView.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView.setTextSize(20);
                textView.setText(list.get(i).getNo() + "");
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(weight);
                row.addView(textView);

                TextView textView2 = new TextView(view.getContext());
                textView2.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView2.setTextSize(20);
                textView2.setText(list.get(i).getType() + "");
                textView2.setGravity(Gravity.CENTER);
                textView2.setLayoutParams(weight);
                row.addView(textView2);

                TextView textView3 = new TextView(view.getContext());
                textView3.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView3.setTextSize(20);
                textView3.setText(list.get(i).getValue() + "");
                textView3.setGravity(Gravity.CENTER);
                textView3.setLayoutParams(weight);
                row.addView(textView3);

                TextView textView4 = new TextView(view.getContext());
                textView4.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView4.setTextSize(20);
                textView4.setText(list.get(i).getNowValue() + "");
                textView4.setGravity(Gravity.CENTER);
                textView4.setLayoutParams(weight);
                row.addView(textView4);

                tb.addView(row);
            } else if (type.equals("全部")) {
                row = new TableRow(view.getContext());

                TextView textView = new TextView(view.getContext());
                TableRow.LayoutParams weight = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                textView.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView.setTextSize(20);
                textView.setText(list.get(i).getNo() + "");
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(weight);
                row.addView(textView);

                TextView textView2 = new TextView(view.getContext());
                textView2.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView2.setTextSize(20);
                textView2.setText(list.get(i).getType() + "");
                textView2.setGravity(Gravity.CENTER);
                textView2.setLayoutParams(weight);
                row.addView(textView2);

                TextView textView3 = new TextView(view.getContext());
                textView3.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView3.setTextSize(20);
                textView3.setText(list.get(i).getValue() + "");
                textView3.setGravity(Gravity.CENTER);
                textView3.setLayoutParams(weight);
                row.addView(textView3);

                TextView textView4 = new TextView(view.getContext());
                textView4.setBackground(getActivity().getDrawable(R.drawable.border_write));
                textView4.setTextSize(20);
                textView4.setText(list.get(i).getNowValue() + "");
                textView4.setGravity(Gravity.CENTER);
                textView4.setLayoutParams(weight);
                row.addView(textView4);

                tb.addView(row);
            }
        }
    }
}
