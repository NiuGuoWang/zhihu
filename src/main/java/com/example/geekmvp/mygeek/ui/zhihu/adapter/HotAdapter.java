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
import com.example.geekmvp.mygeek.model.bean.zhihu.HotListBean;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {
    private List<HotListBean.RecentBean> list;
    private Context context;

    public HotAdapter(List<HotListBean.RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ribaoitem, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        //圆角
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300,300);
        Glide.with(context).load(list.get(i).getThumbnail()).apply(options).into(myViewHolder.mIvRibaoItem);

        myViewHolder.mTvRibaoItem.setText(list.get(i).getTitle());

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
        ImageView mIvRibaoItem;
        TextView mTvRibaoItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvRibaoItem = itemView.findViewById(R.id.iv_ribao_item);
            this.mTvRibaoItem = itemView.findViewById(R.id.tv_ribao_item);
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
