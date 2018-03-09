package com.example.goobee_yuer.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;
import java.util.Map;


/**
 * 本类功能：recyclerView的Adapter
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> picList;
    private List<Map<String, Object>> productList;
    private List<Integer> typeList;
    private List<String> normalList;
    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int PRODUCT_VIEW_TYPE = 1;//产品
    private final int TYPE_VIEW_TYPE = 2;//种类
    private final int NORMAL_VIEW_TYPE = 3;//正常布局

    public RecyclerAdapter(Context context, List<String> picList, List<Map<String, Object>> productList,
                           List<Integer> typeList, List<String> normalList) {
        this.context = context;
        this.picList = picList;
        this.productList = productList;
        this.typeList = typeList;
        this.normalList = normalList;
    }

    /**
     * 获取item的类型
     *
     * @param position item的位置
     * @return item的类型
     * 有几种type就回在onCreateViewHolder方法中引入几种布局,也就是创建几个ViewHolder
     */
    @Override
    public int getItemViewType(int position) {
        /*
        区分item类型,返回不同的int类型的值
        在onCreateViewHolder方法中用viewType来创建不同的ViewHolder
         */
        if (position == 0) {//第0个位置是轮播图
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {//第一个是产品布局
            return PRODUCT_VIEW_TYPE;
        } else if (position == 2) {//第2个位置是种类布局
            return TYPE_VIEW_TYPE;
        } else {//其他位置返回正常的布局
            return NORMAL_VIEW_TYPE;
        }
    }

    /**
     * 创建ViewHolder,根据getItemViewType方法里面返回的几种类型来创建
     * @param viewType 就是getItemViewType返回的type
     * @return 返回自己创建的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW_TYPE) {//如果viewType是轮播图就去创建轮播图的viewHolder
            view = getView(R.layout.item_banner);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        } else if (viewType == PRODUCT_VIEW_TYPE) {//产品的type
            view = getView(R.layout.item_product);
            return new ChannelHolder(view);
        } else if (viewType == TYPE_VIEW_TYPE) {//种类
            view = getView(R.layout.item_type);
            return new TypeHolder(view);
        } else {//正常
            view = getView(R.layout.item_normal);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new NormalHolder(view);
        }
    }

    /**
     * 用来引入布局的方法
     */
    private View getView(int view) {
        View view1 = View.inflate(context, view, null);
        return view1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BannerHolder) {//轮播图
            BannerHolder bannerHolder = (BannerHolder) holder;
            //设置轮播图相关
            setBanner(bannerHolder);
        } else if (holder instanceof ChannelHolder) {//产品
            ChannelHolder channelHolder = (ChannelHolder) holder;
            //设置产品
            setProduct(channelHolder);
        } else if (holder instanceof TypeHolder) {//种类
            TypeHolder girlHolder = (TypeHolder) holder;
            GridViewAdapter adapter = new GridViewAdapter(context, typeList);
            girlHolder.gridView.setAdapter(adapter);
            girlHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "种类"+ position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof NormalHolder) {//正常布局
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.textView.setText(normalList.get(position-3));
            normalHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了" + normalList.get(position-3), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    /**
     * 设置
     * @param channelHolder
     */
    private void setProduct(ChannelHolder channelHolder) {
        //动态添加View
        for (int i = 0; i < productList.size(); i++) {
            View view = View.inflate(context, R.layout.item_channel_view, null);
            ImageView ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
            TextView tvChannel = (TextView) view.findViewById(R.id.tv_channel);
            Glide.with(context).load(productList.get(i).get("pic")).into(ivLogo);
            tvChannel.setText(productList.get(i).get("title").toString());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            params.setMargins(24, 0, 24, 0);
            view.setTag(i);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, productList.get(finalI).get("title").toString(), Toast.LENGTH_SHORT).show();
                }
            });
            channelHolder.linearLayout.addView(view);
        }
    }

    /**
     * 设置轮播图
     * @param bannerHolder
     */
    private void setBanner(BannerHolder bannerHolder) {
        //设置banner样式
        bannerHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerHolder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerHolder.banner.setImages(picList);
        //设置banner动画效果
        bannerHolder.banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//            bannerHolder.banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerHolder.banner.isAutoPlay(true);
        //设置轮播时间
//            bannerHolder.banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        bannerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        bannerHolder.banner.start();
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return normalList.size()+3;
    }

    /*****************************************下面是为不同的布局创建不同的ViewHolder*******************************************************/
    /**
     * 轮播图的ViewHolder
     */
    public static class BannerHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);

        }
    }

    /**
     * 产品列表的ViewHolder
     */
    public static class ChannelHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;

        public ChannelHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_channel);
        }
    }

    /**
     * 种类的ViewHolder
     */
    public static class TypeHolder extends RecyclerView.ViewHolder {
        GridView gridView;
        public TypeHolder(View itemView) {
            super(itemView);
            gridView = (GridView) itemView.findViewById(R.id.gridview);
        }
    }

    /**
     * 正常布局的ViewHolder
     */
    public static class NormalHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public NormalHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
