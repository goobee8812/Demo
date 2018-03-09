package com.example.goobee_yuer.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goobee_yuer.demo.R;
import com.example.goobee_yuer.demo.RecyclerAdapter;
import com.example.goobee_yuer.demo.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Integer> girlList;
    private List<String> normalList;
    private RecyclerAdapter adapter;
    private int[] banner_image_data = {R.drawable.module_grid_battery,R.drawable.module_grid_charr,R.drawable.module_grid_chouyou,
            R.drawable.module_grid_fan,R.drawable.module_grid_jidianqi,R.drawable.module_grid_pc,R.drawable.module_grid_phone,R.drawable.module_grid_shop,
            R.drawable.module_grid_system,R.drawable.module_grid_zhuji};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        picList = new ArrayList<>();
        channelList = new ArrayList<>();
        girlList = new ArrayList<>();
        normalList = new ArrayList<>();
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        initData();
        return view;
    }

    /**
     * 模拟添加数据
     */
    private void initData() {
        //轮播图需要的图片地址
        String picPath = "http://fdfs.xmcdn.com/group27/M04/D4/24/wKgJW1j11VzTmYOeAAG9Mld0icA505_android_large.jpg";
        String picPath1 = "http://fdfs.xmcdn.com/group27/M0A/D4/81/wKgJR1j13gKTWVXaAALwrIB1AVY346_android_large.jpg";
        String picPath2 = "http://fdfs.xmcdn.com/group26/M05/D8/97/wKgJRlj13vqRHDmVAASRJaudX3I424_android_large.jpg";
        picList.add(picPath);
        picList.add(picPath1);
        picList.add(picPath2);
        //频道数据
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", "种类" + i);
            map.put("pic", banner_image_data[i]);
            channelList.add(map);
        }
        for (int i = 0; i < 6; i++) {
            girlList.add(R.mipmap.ic_launcher);
        }
        for (int i = 0; i < 20; i++) {
            normalList.add("正常布局" + i);
        }
        adapter = new RecyclerAdapter(MyApplication.getContext(),picList,channelList,girlList,normalList);
        recyclerView.setAdapter(adapter);

    }
}
