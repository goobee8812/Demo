package com.example.goobee_yuer.demo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.goobee_yuer.demo.R;

import java.lang.reflect.Field;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class ServiceFragment extends Fragment {
    private Button service_bt;
    private Button question_bt;
    private ServiceSerFragment mSerFragment;
    private ServiceQuestionFragment mQuestionFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service,container,false);
        service_bt = (Button) view.findViewById(R.id.id_service_bt);
        question_bt = (Button) view.findViewById(R.id.id_question_bt);
        switch2Service();
        service_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch2Service();
            }
        });
        question_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch2Question();
            }
        });
        return view;
    }

    /**
     * 切换至服务界面
     */
    private void switch2Service(){
        service_bt.setBackgroundResource(R.drawable.button_selected);
        question_bt.setBackgroundResource(R.drawable.button_un_selected);
        service_bt.setTextColor(getResources().getColor(R.color.textSelected));
        question_bt.setTextColor(getResources().getColor(R.color.textUnSelected));

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (mSerFragment == null){
            mSerFragment = new ServiceSerFragment();
            transaction.add(R.id.id_fragment_service,mSerFragment);
        }
        //隐藏所有fragment
        hideAllFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mSerFragment);
        //提交事务
        transaction.commit();
    }
    private void switch2Question(){
        service_bt.setBackgroundResource(R.drawable.button_un_selected);
        question_bt.setBackgroundResource(R.drawable.button_selected);
        question_bt.setTextColor(getResources().getColor(R.color.textSelected));
        service_bt.setTextColor(getResources().getColor(R.color.textUnSelected));
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (mQuestionFragment == null){
            mQuestionFragment = new ServiceQuestionFragment();
            transaction.add(R.id.id_fragment_service,mQuestionFragment);
        }
        //隐藏所有fragment
        hideAllFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mQuestionFragment);
        //提交事务
        transaction.commit();
    }
    private void hideAllFragment(FragmentTransaction transaction){
        if (mSerFragment != null){
            transaction.hide(mSerFragment);
        }
        if (mQuestionFragment != null){
            transaction.hide(mQuestionFragment);
        }
    }
}
