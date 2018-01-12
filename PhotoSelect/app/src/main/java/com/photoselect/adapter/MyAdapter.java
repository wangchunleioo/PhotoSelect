package com.photoselect.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.photoselect.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MediaEntity> mList;
    private Context mContext;
    ViewHolder viewHolder;

    public MyAdapter() {
    }

    public void addData(List<MediaEntity> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("onCreateViewHolder", "执行----------");
        //给上下文赋值
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, null);
//        if (viewHolder == null) {
        viewHolder = new ViewHolder(view);
//        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("onBindViewHolder", "执行============ ");
        if (mList != null) {
            try {
                if (mList.get(position).getCompressPath() != null) {
                    holder.iv.setImageBitmap(BitmapFactory.decodeStream(new FileInputStream(new File(mList.get(position).getCompressPath()))));
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("ViewHolder初始化执行", "88888888888888888");
            iv = (ImageView) itemView.findViewById(R.id.iv_img_item);
        }
    }
}
