package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mad.trafficclient.Bean.Suggest;
import com.mad.trafficclient.R;

import java.util.ArrayList;

public class Fragment_3_suggest extends Fragment {
    private View view;
    private ListView listView;
    private ArrayList<Suggest> list = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout03, container, false);

        initview();
        initdata();
        return view;
    }


    private void initview() {
        listView = view.findViewById(R.id.lv_wodejianyi);
        listView.setDividerHeight(5);
    }


    private void initdata() {

        list.add(new Suggest("标题1","2018-12-06",1,"修改bug","2018-12-11"));
        list.add(new Suggest("标题2","2018-12-06",2,"网络问题","2018-12-11"));
        list.add(new Suggest("标题3","2018-12-06",1,"服务器故障","2018-12-11"));
        list.add(new Suggest("标题4","2018-12-06",2,"更新慢","2018-12-11"));
        list.add(new Suggest("标题5","2018-12-06",1,"什么鬼..","2018-12-11"));


        listView.setAdapter(new MyBaseAdapter());
        listView.invalidate();
    }

    //自定义适配器
    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHoder viewHoder = null;

            if (view == null) {
                view= LayoutInflater.from(getActivity()).inflate(R.layout.item_listview_1,viewGroup,false);
                viewHoder = new ViewHoder();
                viewHoder.textView1= (TextView) view.findViewById(R.id.tv_yijian_title);
                viewHoder.textView2= (TextView)view.findViewById(R.id.tv_yj_status);
                viewHoder.textView3=(TextView) view.findViewById(R.id.tv_huifu_txt);
                viewHoder.textView4= (TextView)view.findViewById(R.id.tv_tj_time);
                viewHoder.textView5= (TextView)view.findViewById(R.id.tv_huifu_time);

                view.setTag(viewHoder);
            } else {
            viewHoder = (ViewHoder) view.getTag();
            }

            Suggest suggest=(Suggest) getItem(i);
            viewHoder.textView1.setText(suggest.getTitle());
            if (suggest.getStatus()==1){
                viewHoder.textView2.setTextColor(Color.RED);
                viewHoder.textView2.setText("未处理");
            }else {
                viewHoder.textView2.setTextColor(Color.GREEN);
                viewHoder.textView2.setText("以处理");
            }

            viewHoder.textView3.setText(suggest.getContext());
            viewHoder.textView4.setText(suggest.getTime());
            viewHoder.textView5.setText(suggest.getHuifutime());

            return view;
        }
    }

    class ViewHoder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
    }
}
