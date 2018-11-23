package com.mad.trafficclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;

public class Fragment_9 extends Fragment {
    private View view;
    private TextView one,two,three;
    private GotoFragmnet gotoFragmnet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout09, container, false);
        initData();
        return view;
    }

    private void initData() {
        one = view.findViewById(R.id.pay);
        two = view.findViewById(R.id.money);
        three = view.findViewById(R.id.record);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragmnet.init();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragmnet.init2();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragmnet.init3();
            }
        });
    }
    public interface GotoFragmnet{
        void init();
        void init2();
        void init3();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        gotoFragmnet = (GotoFragmnet) context;
    }
}
