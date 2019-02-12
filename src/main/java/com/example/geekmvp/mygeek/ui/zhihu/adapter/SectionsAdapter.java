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
import com.example.geekmvp.mygeek.model.bean.zhihu.SectionListBean;

import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.MyViewHolder> {

    private List<SectionListBean.DataBean> list;
    private Context context;

    public SectionsAdapter(List<SectionListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sections_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        //圆角
        RoundedCorners roundedCorners = new RoundedCorners(30);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).centerCrop();
        Glide.with(context).load(list.get(i).getThumbnail()).apply(options).into(myViewHolder.mIvSections);

        myViewHolder.mTvNameSections.setText(list.get(i).getName());
        myViewHolder.mTvTitleSections.setText(list.get(i).getDescription());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvSections;
        TextView mTvTitleSections;
        TextView mTvNameSections;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvSections = itemView.findViewById(R.id.iv_sections);
            this.mTvTitleSections = itemView.findViewById(R.id.tv_title_sections);
            this.mTvNameSections = itemView.findViewById(R.id.tv_name_sections);
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
