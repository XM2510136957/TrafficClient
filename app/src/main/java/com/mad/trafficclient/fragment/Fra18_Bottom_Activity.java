package com.mad.trafficclient.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.adaper.ViewPagerAdaper;
import com.mad.trafficclient.R;

import java.util.ArrayList;

public class Fra18_Bottom_Activity extends Fragment {
    private ViewPager viewPager;
    private TextView news1,news2;
    private ArrayList<Fragment> list;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fra18__bottom, container, false);
        initData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        viewPager = view.findViewById(R.id.viewPager);
        news1 = view.findViewById(R.id.newsquery);
        news2 = view.findViewById(R.id.newsanalyze);
        list = new ArrayList<>();
        list.add(new Fragment_18());
        list.add(new Fra18_Pie());
        viewPager.setAdapter(new ViewPagerAdaper(getChildFragmentManager(),list));
        viewPager.setCurrentItem(0);
        news1.setBackground(getActivity().getDrawable(R.drawable.border_write2));
        news2.setBackground(getActivity().getDrawable(R.drawable.border_write));
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                news1.setBackground(getActivity().getDrawable(R.drawable.border_write2));
                news2.setBackground(getActivity().getDrawable(R.drawable.border_write));
            }
        });
        news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                news2.setBackground(getActivity().getDrawable(R.drawable.border_write2));
                news1.setBackground(getActivity().getDrawable(R.drawable.border_write));
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 1:
                        news2.setBackground(getActivity().getDrawable(R.drawable.border_write2));
                        news1.setBackground(getActivity().getDrawable(R.drawable.border_write));
                        break;
                    case 0:
                        news1.setBackground(getActivity().getDrawable(R.drawable.border_write2));
                        news2.setBackground(getActivity().getDrawable(R.drawable.border_write));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
