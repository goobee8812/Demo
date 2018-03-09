package com.example.goobee_yuer.demo.bean;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class Order {
    private String mName;
    private int mImageId;

    public String getName() {
        return mName;
    }

    public int getImageId() {
        return mImageId;
    }

    public Order(String name, int imageId) {
        mName = name;
        mImageId = imageId;
    }
}
