/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.Bean.Billing;
import com.mad.trafficclient.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


public class Fragment_1 extends Fragment {
	private Spinner s_sort;
	private TableLayout tl_layout;
	private List<Billing> mData = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layout01, container, false);

		requestData();
		initUI(view);
		return view;
	}

	private void requestData() {
		for(int i=0;i<10;i++){
			Billing billing = new Billing(i+1,100+i,"liucong","2018.11.20 14:0"+i);
			mData.add(billing);
		}
	}

	private void initUI(View view) {
		s_sort = view.findViewById(R.id.s_sort);
		tl_layout = view.findViewById(R.id.tl_layout);
		(view.findViewById(R.id.btn_query)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//排序
				sortData(mData);
				//更新数据
				update();
			}
		});
    }

    //更新数据
	private void update() {
		//除标题外，删除全部子视图
		if(tl_layout.getChildCount()>1){
			tl_layout.removeViews(1,tl_layout.getChildCount()-1);
		}
		//从新获取数据并显示
		for(int i=0;i<mData.size();i++){
			TableRow tableRow = new TableRow(getActivity());
			ArrayList<String> values = getValues(mData.get(i),i+1);
			for(int j=0;j<values.size();j++){
				TextView textView = new TextView(getActivity());
				textView.setText(values.get(j));
				textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
				textView.setBackgroundResource(R.drawable.table_border);
				textView.setGravity(Gravity.CENTER);
				tableRow.addView(textView);
			}
			tl_layout.addView(tableRow);
		}
		Toast.makeText(getActivity(), "1111111111", Toast.LENGTH_SHORT).show();
	}

	//字符串时间 转 Date对象
	private Date stringToDate(String dateString){
		ParsePosition position = new ParsePosition(0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm");
		Date dateValue = simpleDateFormat.parse(dateString,position);
		return dateValue;
	}

	//排序数据
	private void sortData(List<Billing> data) {
		Collections.sort(data, new Comparator<Billing>() {
			@Override
			public int compare(Billing o1, Billing o2) {
				Date date1 = stringToDate(o1.getTime());
				Date date2 = stringToDate(o2.getTime());
				if(s_sort.getSelectedItemPosition() == 0){
					//降序
					if(date1.after(date2)){
						return 1;
					}
				}else if(s_sort.getSelectedItemPosition() == 1){
					//升序
					if(date1.before(date2)){
						return 1;
					}
				}
				return -1;
			}
		});
	}

	//将 对象属性 序列化
	private ArrayList<String> getValues(Billing billing,int i) {
		ArrayList<String> data = new ArrayList<>();
		data.add(i+"");
		data.add(billing.getCarId()+"");
		data.add(billing.getRecharge()+"");
		data.add(billing.getOperator());
		data.add(billing.getTime());
		return data;
	}

}
