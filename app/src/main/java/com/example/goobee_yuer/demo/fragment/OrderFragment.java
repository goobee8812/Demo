package com.example.goobee_yuer.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goobee_yuer.demo.OrderAdapter;
import com.example.goobee_yuer.demo.R;
import com.example.goobee_yuer.demo.bean.Order;
import com.example.goobee_yuer.demo.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class OrderFragment extends Fragment implements View.OnClickListener{
    private ImageView order_daifukuan_im;
    private ImageView order_daifahuo_im;
    private ImageView order_daishouhuo_im;
    private ImageView order_daipingjia_im;
    private ImageView order_shouhou_im;
    private ListView mListView;

    List<Order> mOrSderList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        initView(view);
        initDatas();
        OrderAdapter adapter = new OrderAdapter(MyApplication.getContext(),R.layout.order_item,mOrSderList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = mOrSderList.get(position);
                Toast.makeText(MyApplication.getContext(),order.getName() + "被点击了！",Toast.LENGTH_SHORT).show();
            }
        });
        showFukuanFragment();
        return view;
    }

    private void initDatas() {
        for (int i=0;i<10;i++){
            Order order = new Order("订单"+ i,R.drawable.channel);
            mOrSderList.add(order);
        }
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.id_listview);
        order_daifukuan_im = (ImageView) view.findViewById(R.id.id_order_fukuan_im);
        order_daifahuo_im = (ImageView) view.findViewById(R.id.id_order_fahuo_im);
        order_daishouhuo_im = (ImageView) view.findViewById(R.id.id_order_shouhuo_im);
        order_daipingjia_im = (ImageView) view.findViewById(R.id.id_order_pingjia_im);
        order_shouhou_im = (ImageView) view.findViewById(R.id.id_order_shouhou_im);
        //
        order_daifukuan_im.setOnClickListener(this);
        order_daifahuo_im.setOnClickListener(this);
        order_daishouhuo_im.setOnClickListener(this);
        order_daipingjia_im.setOnClickListener(this);
        order_shouhou_im.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_order_fukuan_im:
                showFukuanFragment();
                break;
            case R.id.id_order_fahuo_im:
                showFahuoFragment();
                break;
            case R.id.id_order_shouhuo_im:
                showShouhuoFragment();
                break;
            case R.id.id_order_pingjia_im:
                showPingjiaFragment();
                break;
            case R.id.id_order_shouhou_im:
                showShouhouFragment();
                break;
            default:
                break;
        }
    }

    private void showShouhouFragment() {
        resetImage();
        order_shouhou_im.setImageResource(R.drawable.shouhou_selected);
    }

    private void showPingjiaFragment() {
        resetImage();
        order_daipingjia_im.setImageResource(R.drawable.daipingjia_selected);
    }

    private void showShouhuoFragment() {
        resetImage();
        order_daishouhuo_im.setImageResource(R.drawable.daishouhuo_selected);
    }

    private void showFahuoFragment() {
        resetImage();
        order_daifahuo_im.setImageResource(R.drawable.daifahuo_selected);
    }

    private void showFukuanFragment() {
        resetImage();
        order_daifukuan_im.setImageResource(R.drawable.daifukuan_selected);
    }

    /**
     * 重置所有的图标
     */
    private void resetImage(){
        order_daifukuan_im.setImageResource(R.drawable.daifukuan_unselected);
        order_daifahuo_im.setImageResource(R.drawable.daifahuo_unselected);
        order_daishouhuo_im.setImageResource(R.drawable.daishouhuo_unselected);
        order_daipingjia_im.setImageResource(R.drawable.daipingjia_unselected);
        order_shouhou_im.setImageResource(R.drawable.shouhou_unselected);
    }
}
