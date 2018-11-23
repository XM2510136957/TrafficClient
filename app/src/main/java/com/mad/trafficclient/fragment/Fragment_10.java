package com.mad.trafficclient.fragment;

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

public class Fragment_10 extends Fragment {

    private View view;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private TextView wodeyue;
    private TextView chongzhijilu;
    private TextView yuanchengkongzhi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout10, container, false);

        initview();
        initdata();


        return view;
    }

    private void initfragment() {
        fragmentArrayList.add(new Fragment_10_tab1());
        fragmentArrayList.add(new Fragment_10_tab2());
        fragmentArrayList.add(new Fragment_10_tab3());

    }

    private void initview() {

        viewPager = view.findViewById(R.id.vp_fragment10);
        wodeyue = view.findViewById(R.id.tv_fragment10_wodeyue);
        chongzhijilu = view.findViewById(R.id.tv_fragment10_chongzhijilu);
        yuanchengkongzhi = view.findViewById(R.id.tv_fragment10_yuanchengkongzhi);
    }

    private void initdata() {
        initfragment();
        initlinsenner();
        viewPager.setCurrentItem(0);
        wodeyue.setTextSize(35);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        FragmentManager manager = getChildFragmentManager();
        viewPager.setAdapter(new MyFragmentPagerAdapter(manager));

    }

    private void initlinsenner() {
        wodeyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                wodeyue.setTextSize(35);
                chongzhijilu.setTextSize(30);
                yuanchengkongzhi.setTextSize(30);
            }
        });
        yuanchengkongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                yuanchengkongzhi.setTextSize(35);
                wodeyue.setTextSize(30);
                chongzhijilu.setTextSize(30);
            }
        });

        chongzhijilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                yuanchengkongzhi.setTextSize(30);
                wodeyue.setTextSize(30);
                chongzhijilu.setTextSize(35);
            }
        });
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            switch (position){
                case 0:
                    wodeyue.setTextSize(35);
                    chongzhijilu.setTextSize(30);
                    yuanchengkongzhi.setTextSize(30);
                    break;
                case 1:
                    yuanchengkongzhi.setTextSize(35);
                    wodeyue.setTextSize(30);
                    chongzhijilu.setTextSize(30);
                    break;
                case 2:
                    yuanchengkongzhi.setTextSize(30);
                    wodeyue.setTextSize(30);
                    chongzhijilu.setTextSize(35);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

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
