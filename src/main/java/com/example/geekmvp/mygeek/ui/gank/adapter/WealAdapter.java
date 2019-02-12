package com.example.geekmvp.mygeek.ui.gank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.model.bean.gank.GankItemBean;

import java.util.List;

public class WealAdapter extends RecyclerView.Adapter<WealAdapter.MyViewHolder> {

    private List<GankItemBean.ResultsBean> list;
    private Context context;

    public WealAdapter(List<GankItemBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.weal_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RequestOptions options = RequestOptions.centerCropTransform().centerCrop();
        Glide.with(context).load(list.get(i).getUrl()).apply(options).into(myViewHolder.iv_weal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_weal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_weal = itemView.findViewById(R.id.iv_weal);
        }
    }
}
