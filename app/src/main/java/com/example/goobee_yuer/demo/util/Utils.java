/**
 *
 */
package com.example.goobee_yuer.demo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 辅助工具类
 */
public class Utils {

    public final static String KEY_PORTRAIT = "KEY_PORTRAIT";
    public final static String URL_H5LOCATION = "file:///android_asset/location.html";
    public static final String LOGIN_SP = "LOGIN_SP";				  //SP保存KEY
    public static final String LOGIN_STATUS = "LOGIN_STATUS";	  //是否登录KEY
    public static final String LOGIN_USER = "LOGIN_USER";			  //账号KEY
    public static final String LOGIN_PASSWORD = "LOGIN_PASSWORD"; //密码KEY
    public static final String LOGIN_EMAIL = "LOGIN_EMAIL"; 			//邮箱KEY
    public static final String LOGIN_REMEMBER = "LOGIN_REMEMBER"; //是否记住密码KEY

    public static final String SAVE_SOMETHING = "SAVE_SOMETHING";  //SP保存一些东西
    public static final String KEY_BITMAP = "KEY_BITMAP";				//bitmap的key


    public static String bitmapToString(Bitmap bitmap) {
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        return imageString;
    }

    public static Bitmap stringToBitmapp(String s) {
        //第1步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray=Base64.decode(s, Base64.DEFAULT);
        if(byteArray.length==0){
            //
            return null;
        }else{
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
            //第2步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            return bitmap;
        }
    }


}
