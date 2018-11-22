package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.ArrayList;

//宋明 39题
public class Fragment_6 extends Fragment {

    private View view;
    private ViewPager vp;
    private ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private TextView xinwen;
    private TextView jiaoyu;
    private TextView tiyu;
    private View line;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout06, container, false);
        init();
        inindata();
        return view;
    }

    private void init() {
        vp = view.findViewById(R.id.viewPager1);
        xinwen = view.findViewById(R.id.tv_xinwen);
        jiaoyu = view.findViewById(R.id.tv_jiaoyu);
        tiyu = view.findViewById(R.id.tv_tiyu);
        line = this.view.findViewById(R.id.viewline);

    }

    private void inindata() {

        //添加fragment到viewpager
        fragmentArrayList.add(new Fragment_6_ref1());
        fragmentArrayList.add(new Fragment_6_ref2());
        fragmentArrayList.add(new Fragment_6_ref3());

//        tabHost.addTab(tabHost.newTabSpec("").setContent(R.id.collapseActionView));

        FragmentManager fragmentManager=getChildFragmentManager();
        xinwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);

                //#d7d3d3
            }
        });
        jiaoyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);

            }
        });

        tiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);

            }
        });
        vp.setAdapter(new MyFragmentPagerAdapter(fragmentManager));
        vp.setCurrentItem(0);
        xinwen.setTextColor(Color.RED);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        xinwen.setTextColor(Color.RED);
                        jiaoyu.setTextColor(Color.BLACK);
                        tiyu.setTextColor(Color.BLACK);
                        line.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        jiaoyu.setTextColor(Color.RED);
                        xinwen.setTextColor(Color.BLACK);
                        tiyu.setTextColor(Color.BLACK);
                        line.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        tiyu.setTextColor(Color.RED);
                        jiaoyu.setTextColor(Color.BLACK);
                        xinwen.setTextColor(Color.BLACK);
                        line.setBackgroundColor(Color.BLACK);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //自定义adapter
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

    }



}
