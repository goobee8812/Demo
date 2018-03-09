package com.example.goobee_yuer.demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goobee_yuer.demo.R;
import com.example.goobee_yuer.demo.bean.Machine;
import com.example.goobee_yuer.demo.util.MyApplication;
import com.example.goobee_yuer.demo.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goobee_yuer on 2018/3/7.
 */

public class MineFragment extends Fragment {
    private CircleImageView icon_image = null;
    private ListView machine_listview;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    List<Machine> mList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        icon_image = (CircleImageView) view.findViewById(R.id.icon_image_status);
        getBitmapFromSharedPreferences();
        icon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
            }
        });
        machine_listview = (ListView) view.findViewById(R.id.id_machine_list);
        initDatas();
        MachineAdapter adapter = new MachineAdapter(MyApplication.getContext(),R.layout.machine_item,mList);
        machine_listview.setAdapter(adapter);
        machine_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return view;
    }

    private void initDatas() {
        mList.add(new Machine("监护仪1#","正常",true));
        mList.add(new Machine("监护仪2#","故障",true));
        mList.add(new Machine("监护仪3#","正常",false));
        mList.add(new Machine("心率机1#","正常",true));
        mList.add(new Machine("心率机2#","关闭",false));
        mList.add(new Machine("监护仪","正常",true));
        mList.add(new Machine("监护仪","故障",true));
        mList.add(new Machine("监护仪","正常",true));
    }

    /**
     * 剪裁图片
     * @param uri
     */
    protected void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        }
        else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                icon_image.setImageBitmap(bitmap);
                //eventbus发送事件
//                EventBus.getDefault().post(new MessageEvent(bitmap));
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 保存选择好的头像到SharedPreferences
     * @param bitmap
     */
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(Utils.SAVE_SOMETHING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Utils.KEY_BITMAP, imageString);
        editor.commit();
    }

    /**
     * 从SharedPreferences中获取保存的头像
     */
    private void getBitmapFromSharedPreferences(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(Utils.SAVE_SOMETHING, Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString(Utils.KEY_BITMAP, "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
        if(byteArray.length==0){
            Toast.makeText(MyApplication.getContext(),"No Image!",Toast.LENGTH_LONG).show();
            icon_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
            icon_image.setImageBitmap(bitmap);
        }
    }

    private class MachineAdapter extends ArrayAdapter<Machine>{
        private LayoutInflater mInflater;
        public MachineAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Machine> objects) {
            super(context, resource, objects);
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.machine_item,parent,false);
                holder.tv_machineName = (TextView) convertView.findViewById(R.id.id_machine_item_name);
                holder.tv_machineRun = (TextView) convertView.findViewById(R.id.id_machine_item_run);
                holder.sw_machineConn = (Switch) convertView.findViewById(R.id.id_machine_item_switch);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            Machine machine = getItem(position);
            holder.tv_machineName.setText(machine.getMachineName());
            holder.tv_machineRun.setText(machine.getMachineRun());
            holder.sw_machineConn.setChecked(machine.getMachineConn());
//            Log.d(TAG, "getView: " + machine.getMachineName());
//            Log.d(TAG, "getView: " + machine.getMachineRun());
            return convertView;
        }
        private class ViewHolder{
            TextView tv_machineName;
            TextView tv_machineRun;
            Switch sw_machineConn;
        }
    }
}
