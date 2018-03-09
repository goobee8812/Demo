package com.example.goobee_yuer.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.goobee_yuer.demo.fragment.HomeFragment;
import com.example.goobee_yuer.demo.fragment.MineFragment;
import com.example.goobee_yuer.demo.fragment.OrderFragment;
import com.example.goobee_yuer.demo.fragment.ServiceFragment;
import com.example.goobee_yuer.demo.util.Utils;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Fragment homeFragment;
    private Fragment orderFragment;
    private Fragment serviceFragment;
    private Fragment mineFragment;
    //
    private ImageView home_im;
    private ImageView order_im;
    private ImageView service_im;
    private ImageView mine_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化activity的控件
     */
    private void initView() {
        home_im = (ImageView) findViewById(R.id.id_home_im);
        order_im = (ImageView) findViewById(R.id.id_order_im);
        service_im = (ImageView) findViewById(R.id.id_service_im);
        mine_im = (ImageView) findViewById(R.id.id_mine_im);
        home_im.setOnClickListener(this);
        order_im.setOnClickListener(this);
        service_im.setOnClickListener(this);
        mine_im.setOnClickListener(this);
        //应用启动时候首先进入“首页”
        showHomeFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_home_im:
                showHomeFragment();
                break;
            case R.id.id_order_im:
                showOrderFragment();
                break;
            case R.id.id_service_im:
                showServiceFragment();
                break;
            case R.id.id_mine_im:
                showMineFragment();
                break;
            default:
                break;
        }
    }

    /**
     * 显示“首页”碎片
     */
    private void showHomeFragment(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(homeFragment == null){
            homeFragment = new HomeFragment();
            transaction.add(R.id.id_fragment_ly, homeFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(homeFragment);
        //提交事务
        transaction.commit();
        home_im.setImageResource(R.drawable.home_selected);
    }
    /**
     * 显示“订单”碎片
     */
    private void showOrderFragment(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(orderFragment == null){
            orderFragment = new OrderFragment();
            transaction.add(R.id.id_fragment_ly, orderFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(orderFragment);
        //提交事务
        transaction.commit();
        order_im.setImageResource(R.drawable.order_selected);
    }
    /**
     * 显示“服务”碎片
     */
    private void showServiceFragment(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(serviceFragment == null){
            serviceFragment = new ServiceFragment();
            transaction.add(R.id.id_fragment_ly, serviceFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(serviceFragment);
        //提交事务
        transaction.commit();
        service_im.setImageResource(R.drawable.service_selected);
    }
    /**
     * 显示“我的”碎片
     */
    private void showMineFragment(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(mineFragment == null){
            mineFragment = new MineFragment();
            transaction.add(R.id.id_fragment_ly, mineFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mineFragment);
        //提交事务
        transaction.commit();
        mine_im.setImageResource(R.drawable.mine_selected);
    }


    /**
     * 隐藏所有碎片，在显示某个碎片前调用一次，重置fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(orderFragment != null){
            transaction.hide(orderFragment);
        }
        if(serviceFragment != null){
            transaction.hide(serviceFragment);
        }
        if(mineFragment != null){
            transaction.hide(mineFragment);
        }
        //重置所有选择中的图标
        home_im.setImageResource(R.drawable.home_unselected);
        order_im.setImageResource(R.drawable.order_unselected);
        service_im.setImageResource(R.drawable.service_unselected);
        mine_im.setImageResource(R.drawable.mine_unselected);
    }
}
