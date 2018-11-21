/**
 *
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;


public class Fragment_3 extends Fragment {

    private View view;
    private EditText title;
    private EditText yijian;
    private EditText phone;
    private Button bt_tijiao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout03, container, false);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.content_layout, container);
        Log.i("activity", String.valueOf(getActivity()));
        TextView tv_wodeyijian=view1.findViewById(R.id.tv_anoter);
        tv_wodeyijian.setText("我的意见");

        initview();
        initdata();
        return view;
    }

    private void initview() {

        title = view.findViewById(R.id.ed_yijian_title);
        yijian = view.findViewById(R.id.ed_yijian);
        phone = view.findViewById(R.id.ed_phone);
        bt_tijiao = view.findViewById(R.id.bt_tijiao_yijian);
    }

    private void initdata() {

        bt_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.getText().toString().trim();
                yijian.getText().toString().trim();
                Date time=new Date();
                long time1 = time.getTime();
            }
        });
    }

}
