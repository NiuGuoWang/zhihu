package com.example.geekmvp.mygeek.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.model.bean.zhihu.DailyListBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class RibaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DailyListBean.StoriesBean> list;
    private Context context;
    List<String> imgList;
    List<String> titleList;
    public RibaoAdapter(List<DailyListBean.StoriesBean> list, Context context, List<String> imgList, List<String> titleList) {
        this.list = list;
        this.context = context;
        this.imgList = imgList;
        this.titleList = titleList;
    }

    private static final int TYPE_HEAD = 1;
    private static final int TYPE_BODY = 2;

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_HEAD;
            default:
                 return TYPE_BODY;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case TYPE_HEAD:
                View banner = LayoutInflater.from(context).inflate(R.layout.banner_item, viewGroup, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(banner);
                return headViewHolder;
            case TYPE_BODY:
                View inflate = LayoutInflater.from(context).inflate(R.layout.ribaoitem, viewGroup, false);
                MyViewHolder myViewHolder = new MyViewHolder(inflate);
                return myViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        //圆角
        if(viewHolder instanceof MyViewHolder){
            MyViewHolder holder = (MyViewHolder) viewHolder;
            RoundedCorners roundedCorners = new RoundedCorners(10);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300,300);
            Glide.with(context).load(list.get(i).getImages().get(0)).apply(options).into(holder.mIvRibaoItem);

            holder.mTvRibaoItem.setText(list.get(i).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(i);
                }
            });
        } else if(viewHolder instanceof HeadViewHolder){
            HeadViewHolder holder = (HeadViewHolder) viewHolder;
            holder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            holder.banner.setImages(imgList);
            holder.banner.setBannerTitles(titleList);
            holder.banner.start();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvRibaoItem;
        TextView mTvRibaoItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvRibaoItem = itemView.findViewById(R.id.iv_ribao_item);
            this.mTvRibaoItem = itemView.findViewById(R.id.tv_ribao_item);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            this.banner = itemView.findViewById(R.id.banner);
        }
    }

    public OnItemClickListener listener;
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
