package com.example.goobee_yuer.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goobee_yuer.demo.R;

/**
 * Created by Goobee_yuer on 2018/3/9.
 */

public class ServiceSerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_ser,container,false);
        return view;
    }
}
