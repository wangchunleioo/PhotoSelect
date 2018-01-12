package com.photoselect.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class MylvAdapter extends BaseAdapter {
    private Context mContext;
    private List<MediaEntity> mList;

    public MylvAdapter(Context context, List<MediaEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) view.findViewById(R.id.iv_img_item);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (mList.get(i).getCompressPath() != null) {
            try {
                viewHolder.iv.setImageBitmap(BitmapFactory.decodeStream(new FileInputStream(new File(mList.get(i).getCompressPath()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    class ViewHolder {
        ImageView iv;
    }
}
