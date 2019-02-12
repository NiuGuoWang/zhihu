package com.example.geekmvp.mygeek.ui.wx.adapter;

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
import com.example.geekmvp.mygeek.model.bean.wx.WXItemBean;

import java.util.List;

public class WxAdapter extends RecyclerView.Adapter<WxAdapter.MyViewHolder> {

    private List<WXItemBean.NewslistBean> list;
    private Context context;

    public WxAdapter(List<WXItemBean.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wx_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        RequestOptions options = RequestOptions.centerCropTransform().override(120,120).centerCrop();
        Glide.with(context).load(list.get(i).getPicUrl()).apply(options).into(myViewHolder.mIvWx);
        myViewHolder.mTvCtimeWx.setText(list.get(i).getCtime());
        myViewHolder.mTvDescriptionWx.setText(list.get(i).getDescription());
        myViewHolder.mTvTitleWx.setText(list.get(i).getTitle());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.wxItemOnClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvWx;
        TextView mTvTitleWx;
        TextView mTvDescriptionWx;
        TextView mTvCtimeWx;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvWx = itemView.findViewById(R.id.iv_wx);
            this.mTvTitleWx = itemView.findViewById(R.id.tv_title_wx);
            this.mTvDescriptionWx = itemView.findViewById(R.id.tv_description_wx);
            this.mTvCtimeWx = itemView.findViewById(R.id.tv_ctime_wx);
        }
    }
    WxItemOnClickListener listener;
    public interface WxItemOnClickListener{
        void wxItemOnClick(int position);
    }
    public void setWxItemOnClickListener(WxItemOnClickListener listener) {
        this.listener = listener;
    }
}
