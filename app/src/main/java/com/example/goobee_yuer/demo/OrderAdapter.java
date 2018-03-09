package com.example.goobee_yuer.demo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goobee_yuer.demo.bean.Order;

import java.util.List;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private LayoutInflater mInflater;
    public OrderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null){
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.order_item,parent,false);
            mHolder.orderImg = (ImageView) convertView.findViewById(R.id.id_product_im);
            mHolder.orderName = (TextView) convertView.findViewById(R.id.id_product_tv);
            convertView.setTag(mHolder); //设置配置，下次调用可以直接使用
        }else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        //装载数据
        Order order = getItem(position);
        mHolder.orderImg.setImageResource(order.getImageId());
        mHolder.orderName.setText(order.getName());
        return convertView;
    }
    private class ViewHolder{
        ImageView orderImg;
        TextView  orderName;
    }
}
