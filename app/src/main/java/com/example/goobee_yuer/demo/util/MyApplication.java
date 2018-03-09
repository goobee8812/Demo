package com.example.goobee_yuer.demo.util;

import android.app.Application;
import android.content.Context;

/**
 * 全局获取context
 * Created by Goobee_yuer on 2018/3/7.
 */

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        sContext = getApplicationContext();
    }
    public static Context getContext(){
        return sContext;
    }
}
