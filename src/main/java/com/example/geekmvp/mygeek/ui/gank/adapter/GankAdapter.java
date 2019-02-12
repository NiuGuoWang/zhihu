package com.example.geekmvp.mygeek.ui.gank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;

import java.util.List;

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GankItemBean.ResultsBean> list;
    private Context context;
    private int type;
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_BODY = 2;

    public GankAdapter(List<GankItemBean.ResultsBean> list, Context context, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
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
                View head = LayoutInflater.from(context).inflate(R.layout.gank_head_item, viewGroup, false);
                HeadViewHoder headViewHoder = new HeadViewHoder(head);
                return headViewHoder;
            case TYPE_BODY:
                View inflate = LayoutInflater.from(context).inflate(R.layout.gank_item, viewGroup, false);
                MyViewHolder myViewHolder = new MyViewHolder(inflate);
                return myViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if(viewHolder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.mTvTimeGank.setText(list.get(i).getPublishedAt());
            myViewHolder.mTvTitleGank.setText(list.get(i).getDesc());
            myViewHolder.mTvWhoGank.setText(list.get(i).getWho());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.gankItemOnclick(i);
                }
            });
            if(type==1){
               myViewHolder.mIvGank.setImageResource(R.mipmap.ic_android);
            }else if(type == 2){
                myViewHolder.mIvGank.setImageResource(R.mipmap.ic_ios);
            }else if(type == 3){
                myViewHolder.mIvGank.setImageResource(R.mipmap.ic_web);
            }
        }else if(viewHolder instanceof HeadViewHoder){

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvGank;
        TextView mTvTitleGank;
        TextView mTvWhoGank;
        TextView mTvTimeGank;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvGank = itemView.findViewById(R.id.iv_gank);
            this.mTvTitleGank = itemView.findViewById(R.id.tv_title_gank);
            this.mTvWhoGank = itemView.findViewById(R.id.tv_who_gank);
            this.mTvTimeGank = itemView.findViewById(R.id.tv_time_gank);
        }
    }

    class HeadViewHoder extends RecyclerView.ViewHolder {
        ImageView mIvGankHeadItem;
        TextView mTvGankHeadItem;
        public HeadViewHoder(@NonNull View itemView) {
            super(itemView);
            this.mIvGankHeadItem = itemView.findViewById(R.id.iv_gank_head_item);
            this.mTvGankHeadItem = itemView.findViewById(R.id.tv_gank_head_item);
        }
    }

    GankItemOnclickListener listener;
    public interface GankItemOnclickListener{
        void gankItemOnclick(int position);
    }
    public void setGankItemOnclickListener(GankItemOnclickListener listener){
        this.listener = listener;
    }
}
