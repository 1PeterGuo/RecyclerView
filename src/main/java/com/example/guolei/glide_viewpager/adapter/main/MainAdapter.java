package com.example.guolei.glide_viewpager.adapter.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.guolei.glide_viewpager.R;
import com.example.guolei.glide_viewpager.bean.weal.Result;
import com.example.guolei.glide_viewpager.utils.DisplayUtils;

import java.util.List;

/**
 * Created on 2019/5/17 10:39
 *
 * @author Q.PeterGuo
 * @version 1.0.0
 * @Description
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Context mContext;
    private List<Result> mData;

    public MainAdapter(Context context, List<Result> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<Result> data) {
        mData = data;
        setImageScale();
    }

    private void setImageScale() {
        for (final Result girlBean : mData) {
            if (girlBean.getScale() == 0) {
                Glide.with(mContext).load(girlBean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        float scale = resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight();
                        girlBean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            } else {
                notifyDataSetChanged();
            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_main, null);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Result result = mData.get(position);

        final ViewGroup.LayoutParams layoutParams = holder.mIv.getLayoutParams();
        layoutParams.width = DisplayUtils.getScreenWidth((Activity) mContext) / 2 - DisplayUtils.dp2px(mContext, 8);
        if (result.getScale() != 0) {
            layoutParams.height = (int) (layoutParams.width / result.getScale());
        }
        holder.mIv.setBackgroundColor(Color.BLUE);


        Glide.with(mContext).load(result.getUrl()).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }
}
