package com.dl.ms.mspro1.view;

import android.support.v7.widget.RecyclerView;

public class NoScrollRecyclerView extends RecyclerView {

    public NoScrollRecyclerView(android.content.Context context,
                                android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}