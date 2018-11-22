package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.ArrayList;

public class Fragment_6_ref2 extends Fragment {
    private View view;
    private ListView listView;
    private ArrayList<String> strings;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_6_listview, container, false);
        init();
        inindata();

        return view;
    }

    private void inindata() {
        strings = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            strings.add("教育新闻GNBGN" + i);
    }

        listView.setAdapter(new MyBaseAdapter());
    }

    private void init() {
        listView = view.findViewById(R.id.ls_xinwen);
    }

    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int i) {
            return strings.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler=null;
            if (view==null){
                view= LayoutInflater.from(getActivity()).inflate(R.layout.fragmen_6_item, viewGroup, false);
                viewHodler=new ViewHodler();

                viewHodler.title = view.findViewById(R.id.item_title01);

                view.setTag(viewHodler);
            }else {
                viewHodler=(ViewHodler)view.getTag();
            }

            String d = (String)getItem(i);

            viewHodler.title.setText(d);

            return view;
        }
    }

    class ViewHodler{
        TextView title;
//        TextView context;

    }
}
