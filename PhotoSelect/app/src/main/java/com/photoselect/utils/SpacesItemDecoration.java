package com.photoselect.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置recyclerview的间隔
 * Created by Administrator on 2018/1/4 0004.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mspace;

    public SpacesItemDecoration(int space) {
        mspace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mspace;
        outRect.right = mspace;
        outRect.bottom = mspace;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mspace;
        }
    }
}
