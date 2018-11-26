package com.mad.trafficclient.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.Billing;
import com.mad.trafficclient.bean.CZJL;
import com.mad.trafficclient.dao.UserDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_11 extends Fragment {
    private ListView listView;
    private LinearLayout ll_layout;
    private List<CheckBox> checks = new ArrayList<>();
    private BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.itme_account,null);

                viewHolder = new ViewHolder();
                viewHolder.id = convertView.findViewById(R.id.car_id);
                viewHolder.img = convertView.findViewById(R.id.car_img);
                viewHolder.number = convertView.findViewById(R.id.tv_car_number);
                viewHolder.user = convertView.findViewById(R.id.tv_user);
                viewHolder.balance = convertView.findViewById(R.id.tv_balance);
                convertView.findViewById(R.id.btn_charge).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(new String[]{data.get(position).get("carNumber")},new String[]{data.get(position).get("userName")},new String[]{data.get(position).get("carCharge")});
                    }
                });
                checks.add((CheckBox) convertView.findViewById(R.id.cb_check));
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.id.setText(position+1+"");
            viewHolder.img.setImageResource(R.drawable.benchi);
            viewHolder.number.setText(data.get(position).get("carNumber"));
            viewHolder.user.setText("车主："+data.get(position).get("userName"));
            viewHolder.balance.setText("余额："+data.get(position).get("carCharge")+"元");
            return convertView;
        }
    };
    class ViewHolder{
        public TextView id;
        public ImageView img;
        public TextView number;
        public TextView user;
        public TextView balance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout11, container, false);

        requestData();
        initUI(view);
        return view;
    }

    private List<Map<String,String>> data = new ArrayList<>();
    private void requestData() {
        data.clear();
        UserDao userDao = new UserDao(getActivity());
        userDao.open();
        Cursor cursor = userDao.query("select c.carNumber,u.userName,c.carCharge from userInfo u,carInfo c where u.id = c.id");
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            Map<String,String> map = new HashMap<>();
            map.put("carNumber",cursor.getString(0));
            map.put("userName",cursor.getString(1));
            map.put("carCharge",cursor.getInt(2)+"");
            cursor.moveToNext();
            data.add(map);
        }
        cursor.close();
    }

    private void initUI(View view) {
        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(baseAdapter);

        ll_layout = getActivity().findViewById(R.id.ll_layout);
        TextView tv_plcl = new TextView(getActivity());
        tv_plcl.setText("批量处理");
        tv_plcl.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
        tv_plcl.setGravity(Gravity.CENTER);
        tv_plcl.setGravity(Gravity.BOTTOM);
        TextView tv_czjl = new TextView(getActivity());
        tv_czjl.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
        tv_czjl.setGravity(Gravity.CENTER);
        tv_czjl.setGravity(Gravity.BOTTOM);
        tv_czjl.setText("充值记录");

        tv_plcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                for(int i=0;i<checks.size();i++){
                    if(checks.get(i).isChecked()){
                        position++;
                    }
                }
                if(position <= 0){
                    Toast.makeText(getActivity(), "请选择需要充值的车辆！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] carNumbers = new String[position];
                String[] userNames = new String[position];
                String[] balances = new String[position];
                int j = 0;
                for(int i=0;i<checks.size();i++){
                    if(checks.get(i).isChecked()){
                        LinearLayout linearLayout = (LinearLayout) checks.get(i).getParent().getParent();
                        carNumbers[j] = ((TextView)linearLayout.findViewById(R.id.tv_car_number)).getText().toString();
                        userNames[j] = data.get(i).get("userName");
                        balances[j] = data.get(i).get("carCharge");
                        j++;
                    }
                }
                showDialog(carNumbers,userNames,balances);
            }
        });

        tv_czjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_2 fragment_2 = new Fragment_2();
                Bundle bundle = new Bundle();
                bundle.putInt("position",1);
                fragment_2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.maincontent,fragment_2).commit();
                ((TextView)getActivity().findViewById(R.id.tv_title)).setText("个人中心");
            }
        });
        ll_layout.addView(tv_plcl);
        ll_layout.addView(tv_czjl);
    }

    private String[] getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        switch (week){
            case 1:
                weekStr = "星期日";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
        }
        String str = year+"."+(month+1)+"."+date+";"+weekStr+";"+year+"."+(month+1)+"."+date+" "+hour+":"+minute;
        Log.e("time",str);
        return str.split(";");
    }

    private static String toString(String[] strings){
        String str = "";
        for(int i=0;i<strings.length;i++){
            if(i != strings.length-1) {
                str += strings[i] + ",";
            }else{
                str += strings[i];
            }
        }
        return str;
    }
    private void showDialog(final String[] number, final String[] userName, final String[] balance) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = View.inflate(getActivity(),R.layout.dialog_layout,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        ((TextView)view.findViewById(R.id.tv_car_number)).setText(toString(number));
        view.findViewById(R.id.btn_charge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chargeStr = ((EditText)view.findViewById(R.id.et_charge)).getText().toString();
                if(chargeStr.equals("") || chargeStr == null){
                    Toast.makeText(getActivity(), "请输入金额！", Toast.LENGTH_SHORT).show();
                    return;
                }
                int charge = Integer.parseInt(chargeStr);
                if(charge >= 1 && charge <= 999){
                    UserDao userDao = new UserDao(getActivity());
                    userDao.open();
                    boolean isFinish = true;
                    for(int i=0;i<number.length;i++){
                        CZJL czjl = new CZJL(userName[i],number[i],charge,Integer.parseInt(balance[i])+charge,getDate());
                        if(userDao.insertCZJL(czjl)>0){
                            userDao.updateCarInfo(Integer.parseInt(balance[i])+charge,number[i]);
                        }else{
                            isFinish = false;
                            Toast.makeText(getActivity(), "充值失败！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if(isFinish){
                        requestData();
                        listView.invalidateViews();
                        Toast.makeText(getActivity(), "充值成功！", Toast.LENGTH_SHORT).show();
                    }
                    userDao.close();
                    dialog.dismiss();
                }else{
                    ((TextView)view.findViewById(R.id.et_charge)).setText("");
                    Toast.makeText(getActivity(), "请输入正确金额！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onDestroy() {
        ll_layout.removeAllViews();
        super.onDestroy();
    }
}
