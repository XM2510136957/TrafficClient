package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_10_tab1 extends Fragment {

    private View view;
    private TextView yue1;
    private LinearLayout line1;
    private TextView tv_status;
    private TextView zhuangtai2;
    private TextView zhuangtai3;
    private TextView zhuangtai4;
    private LinearLayout lin2;
    private LinearLayout lin3;
    private LinearLayout lin4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment10__wodeyue, container, false);
        initview();
        initdata();
        return view;
    }

    private void initdata() {


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int v = (int)(Math.random() * 100);
                int s = (int)(Math.random() * 100);
                int d = (int)(Math.random() * 100);
                int m = (int)(Math.random() * 100);


                Message message = new Message();

                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("v",v);
                bundle.putInt("s",s);
                bundle.putInt("d",d);
                bundle.putInt("m",m);

                message.setData(bundle);

                handler.sendMessage(message);
            }

        }, 1000, 5000);


    }

    private void initview() {

        yue1 = view.findViewById(R.id.tv_frag10_yue1);
        line1 = view.findViewById(R.id.line1_frag01);
        tv_status = view.findViewById(R.id.tv_zhuagtai);
        zhuangtai2 = view.findViewById(R.id.tv_zhuagtai2);
        zhuangtai3 = view.findViewById(R.id.tv_zhuagtai3);
        zhuangtai4 = view.findViewById(R.id.tv_zhuagtai4);
        lin2 = view.findViewById(R.id.lin2_frag01);
        lin3 = view.findViewById(R.id.lin3_frag01);
        lin4 = view.findViewById(R.id.lin4_frag01);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Bundle data = msg.getData();
            int v = data.getInt("v");
            Log.i("cs",String.valueOf(v));
            int s = data.getInt("s");
            int d = data.getInt("d");
            int m = data.getInt("m");

            yue1.setText(String.valueOf(v));
                if (v < 60) {
                    line1.setBackgroundColor(Color.RED);
                    lin2.setBackgroundColor(Color.RED);
                    lin3.setBackgroundColor(Color.BLUE);
                    lin4.setBackgroundColor(Color.GREEN);
                    tv_status.setText("警告");
                } else {
                    line1.setBackgroundColor(Color.GREEN);
                    lin2.setBackgroundColor(Color.GREEN);
                    lin3.setBackgroundColor(Color.GREEN);
                    lin4.setBackgroundColor(Color.YELLOW);
                    tv_status.setText("正常");
                }

        }
    };

}
