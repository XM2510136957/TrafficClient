package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.content.Intent;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;

import org.w3c.dom.Text;

public class Fragment_5 extends Fragment {
    private ScrollView scrollView1;
    private ScrollView scrollView2;
    private ViewPager viewPager;
    private LinearLayout btnSP;
    private LinearLayout btnTP;
    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if(position == 0){
                container.addView(scrollView1);
                return scrollView1;
            }else {
                container.addView(scrollView2);
                return scrollView2;
            }
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    };
    private BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return imgIds.length;
        }

        @Override
        public Object getItem(int position) {
            return imgIds[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;
            if(convertView == null){
                imageView = new ImageView(getActivity());
            }else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgIds[position]);
            return imageView;
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout05, container, false);

        requestData();
        initUI(view);
        return view;
    }

    //模拟数据
    private int[] imgIds;
    private int[] spIds;
    private String[] spName;
    private void requestData() {
        imgIds = new int[]{
                R.drawable.weizhang01,
                R.drawable.weizhang02,
                R.drawable.weizhang03,
                R.drawable.weizhang04,
        };
        spName = new String[]{
                "视频1",
                "视频2",
                "视频3",
                "视频4",
        };
    }

    private void initUI(View view) {
        btnSP = view.findViewById(R.id.btn_sp);
        btnTP = view.findViewById(R.id.btn_tp);
        btnTP.setBackgroundColor(Color.parseColor("#cccccc"));
        scrollView1 = new ScrollView(getActivity());
        scrollView2 = new ScrollView(getActivity());
        viewPager = view.findViewById(R.id.vp);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    setColor(btnSP);
                }else{
                    setColor(btnTP);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSP();
                setColor(v);
                viewPager.setCurrentItem(0);
            }
        });
        btnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(v);
                viewPager.setCurrentItem(1);
            }
        });
        updateSP();
        updateTP();
    }

    private void updateSP() {
        scrollView1.removeAllViews();
        GridView gridView = new GridView(getActivity());
        gridView.setNumColumns(4);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return spName.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout view = null;
                if(convertView == null){
                    view = (LinearLayout) View.inflate(getActivity(),R.layout.item_weizhang,null);
                    view.setGravity(Gravity.CENTER);
                    (view.findViewById(R.id.img)).setBackgroundResource(R.drawable.video_icon);
                    ((TextView) view.findViewById(R.id.txt)).setText(spName[position]);
                }else{
                    view = (LinearLayout) convertView;
                }
                return view;
            }
        });
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        scrollView1.addView(gridView);
    }
    private void updateTP() {
        scrollView2.removeAllViews();
        GridView gridView = new GridView(getActivity());
        gridView.setNumColumns(4);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(baseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ImageActivity.class);
                intent.putExtra("img",imgIds[position]);
                startActivity(intent);
            }
        });
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        scrollView2.addView(gridView);
    }
    private void setColor(View view){
        view.setBackgroundColor(Color.parseColor("#f1f1f1"));
        if(view != btnSP) {
            btnSP.setBackgroundColor(Color.parseColor("#cccccc"));
        }else{
            btnTP.setBackgroundColor(Color.parseColor("#cccccc"));
        }
    }
}
