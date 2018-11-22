package com.mad.trafficclient.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_7 extends Fragment {
    private TextView pm,warm,wet;
    private TextView fire,crowd,smooth;
    private TextView red,yellow,green;
    private View view;
    private int pmdata,warmdata,wetdata;
    private Timer timer = new Timer();
    private Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg!=null){
                updateUI();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout07, container, false);
        initData();
        setData();
        return view;
    }

    private void setData() {
        pmdata = (int) (Math.random()*200);
        warmdata = (int) (Math.random()*50);
        wetdata = (int) (Math.random()*100);
        pm.setText(pmdata+"μg/m³");
        warm.setText(warmdata+"℃");
        wet.setText(wetdata+"%");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pmdata = (int) (Math.random()*200);
                warmdata = (int) (Math.random()*50);
                wetdata = (int) (Math.random()*100);
                handler.sendMessage(new Message());
            }
        },1,3000);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUI(){
        pm.setText(pmdata+"μg/m³");
        warm.setText(warmdata+"℃");
        wet.setText(wetdata+"%");
        if(pmdata<20&&warmdata>20||wetdata<30){
            fire.setText("爆表");
            red.setBackground(getActivity().getDrawable(R.drawable.border_textred));
            smooth.setText("堵塞");
            green.setBackground(getActivity().getDrawable(R.drawable.border_textcrowd));
            crowd.setText("拥挤");
            yellow.setBackground(getActivity().getDrawable(R.drawable.border_textyellow));
        }
        else if(pmdata<60&&warmdata>10||wetdata<60){
            fire.setText("较拥挤");
            red.setBackground(getActivity().getDrawable(R.drawable.border_textlowdcrowd));
            smooth.setText("爆表");
            green.setBackground(getActivity().getDrawable(R.drawable.border_textred));
            crowd.setText("通畅");
            yellow.setBackground(getActivity().getDrawable(R.drawable.border_textgreen));
        }
       else if(pmdata<100&&warmdata>0||wetdata<98){
            fire.setText("拥挤");
            red.setBackground(getActivity().getDrawable(R.drawable.border_textyellow));
            crowd.setText("堵塞");
            yellow.setBackground(getActivity().getDrawable(R.drawable.border_textcrowd));
            smooth.setText("通畅");
            green.setBackground(getActivity().getDrawable(R.drawable.border_textgreen));
        }else {
            fire.setText("通畅");
            red.setBackground(getActivity().getDrawable(R.drawable.border_textgreen));
            smooth.setText("拥挤");
            green.setBackground(getActivity().getDrawable(R.drawable.border_textyellow));
            crowd.setText("爆表");
            yellow.setBackground(getActivity().getDrawable(R.drawable.border_textred));
        }

    }

    private void initData() {
        pm = view.findViewById(R.id.pm);
        warm = view.findViewById(R.id.warm);
        wet = view.findViewById(R.id.wet);
        fire = view.findViewById(R.id.fire);
        crowd = view.findViewById(R.id.crowd);
        smooth = view.findViewById(R.id.smooth);
        red = view.findViewById(R.id.color1);
        yellow = view.findViewById(R.id.color2);
        green = view.findViewById(R.id.color3);
    }
}
