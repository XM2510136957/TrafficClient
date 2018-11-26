/**
 * 
 */
package com.mad.trafficclient.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.bean.CZJL;
import com.mad.trafficclient.R;
import com.mad.trafficclient.dao.UserDao;

import java.util.ArrayList;
import java.util.List;


public class Fragment_2 extends Fragment{
	private LinearLayout ll_title;
	private ViewPager viewPager;
	private View[] layouts;
	private ListView listView;
	private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return titleName.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(layouts[position]);
            return layouts[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
	private List<CZJL> czjls = new ArrayList<>();
    private BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return czjls.size();
        }

        @Override
        public Object getItem(int position) {
            return czjls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(getActivity(),R.layout.item_charge,null);
            }
            ((TextView)convertView.findViewById(R.id.tv_time00)).setText(czjls.get(position).getTime()[0]);
            ((TextView)convertView.findViewById(R.id.tv_time01)).setText(czjls.get(position).getTime()[1]);
            ((TextView)convertView.findViewById(R.id.tv_user)).setText("充值人："+czjls.get(position).getUserName());
            ((TextView)convertView.findViewById(R.id.tv_car_number)).setText("车牌号："+czjls.get(position).getCarNumber());
            ((TextView)convertView.findViewById(R.id.tv_charge)).setText("充值："+czjls.get(position).getCharge());
            ((TextView)convertView.findViewById(R.id.tv_balance)).setText("余额："+czjls.get(position).getBalance());
            ((TextView)convertView.findViewById(R.id.tv_time02)).setText(czjls.get(position).getTime()[2]);
            return convertView;
        }
    };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layout02, container, false);
        requestData();
		initUI(view);
		return view;
	}

    private void requestData() {
	    czjls.clear();
        UserDao userDao = new UserDao(getActivity());
        userDao.open();
        Cursor cursor = userDao.query("select * from czjl");
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
	        CZJL czjl = new CZJL(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),cursor.getString(5).split(";"));
            czjls.add(czjl);
            cursor.moveToNext();
	    }
    }

    private String[] titleName = new String[]{
			"个人信息","充值记录","阈值设置"
	};
	private List<View> views = new ArrayList<>();
	private void initUI(View view) {
	    layouts = new View[]{
	            View.inflate(getActivity(),R.layout.fragment02_item01,null),
                View.inflate(getActivity(),R.layout.fragment02_item02,null),
                View.inflate(getActivity(),R.layout.fragment02_item03,null)
        };
		ll_title = view.findViewById(R.id.ll_title);
		viewPager = view.findViewById(R.id.vp);
		viewPager.setAdapter(pagerAdapter);
		for(int i=0;i<titleName.length;i++){
			View view1 = View.inflate(getActivity(),R.layout.item_btn,null);
			((TextView)view1.findViewById(R.id.title_name)).setText(titleName[i]);
			if(i == 0){
                view1.findViewById(R.id.title_u).setBackgroundColor(Color.parseColor("#ffffff"));
			}
			views.add(view1);
			view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setColor(viewToPosition(v));
                    viewPager.setCurrentItem(viewToPosition(v));
                }
            });
			ll_title.addView(view1);
		}
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        listView = layouts[1].findViewById(R.id.lv_charge);
        listView.setAdapter(baseAdapter);
        Bundle bundle = getArguments();
        if(bundle != null) {
            int position = bundle.getInt("position");
            viewPager.setCurrentItem(position);
            requestData();
            listView.invalidateViews();
        }
        if(czjls.size() == 0){
            Toast.makeText(getActivity(), "暂无充值记录", Toast.LENGTH_SHORT).show();
        }
	}
	private int viewToPosition(View view){
	    for(int i=0;i<views.size();i++){
	        if(views.get(i) == view){
	            return i;
            }
        }
        return 0;
    }
	private void setColor(int position){
        for(int i=0;i<views.size();i++){
            if(views.get(position) == views.get(i)){
                views.get(i).findViewById(R.id.title_u).setBackgroundColor(Color.parseColor("#ffffff"));
            }else{
                views.get(i).findViewById(R.id.title_u).setBackgroundColor(Color.parseColor("#aaaaaa"));
            }
        }
    }


}
