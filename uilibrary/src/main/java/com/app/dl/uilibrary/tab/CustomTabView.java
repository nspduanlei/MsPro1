package com.app.dl.uilibrary.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.dl.uilibrary.R;

import java.util.ArrayList;
import java.util.List;

public class CustomTabView extends LinearLayout implements View.OnClickListener {
    private List<View> mTabViews;//保存TabView
    private List<Tab> mTabs;// 保存Tab
    private OnTabCheckListener mOnTabCheckListener;

    private int mNormalColor;
    private int mSelectedColor;

    public void setOnTabCheckListener(OnTabCheckListener onTabCheckListener) {
        mOnTabCheckListener = onTabCheckListener;
    }

    public CustomTabView(Context context) {
        this(context, null, 0);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        //获取自定义属性。
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.CustomTabView);
        mNormalColor = ta.getColor(R.styleable.CustomTabView_normalColor, Color.RED);
        mSelectedColor = ta.getColor(R.styleable.CustomTabView_selectedColor, Color.RED);
        ta.recycle();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        mTabViews = new ArrayList<>();
        mTabs = new ArrayList<>();
    }

    /**
     * 添加Tab
     *
     * @param tab
     */
    public void addTab(Tab tab) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_item_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
        imageView.setImageResource(tab.getIconNormalResId());
        textView.setText(tab.getText());
        textView.setTextColor(mNormalColor);

        view.setTag(mTabViews.size());
        view.setOnClickListener(this);

        mTabViews.add(view);
        mTabs.add(tab);

        addView(view);
    }

    /**
     * 设置选中Tab
     *
     * @param position
     */
    public void setCurrentItem(int position) {
        if (position >= mTabs.size() || position < 0) {
            position = 0;
        }
        mTabViews.get(position).performClick();
        updateState(position);
    }

    /**
     * 更新状态
     *
     * @param position
     */
    private void updateState(int position) {
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            TextView textView = (TextView) view.findViewById(R.id.custom_tab_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.custom_tab_icon);
            if (i == position) {
                imageView.setImageResource(mTabs.get(i).getIconPressedResId());
                textView.setTextColor(mSelectedColor);
            } else {
                imageView.setImageResource(mTabs.get(i).getIconNormalResId());
                textView.setTextColor(mSelectedColor);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnTabCheckListener != null) {
            mOnTabCheckListener.onTabSelected(v, position);
        }
        updateState(position);
    }

    public interface OnTabCheckListener {
        void onTabSelected(View v, int position);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabViews != null) {
            mTabViews.clear();
        }
        if (mTabs != null) {
            mTabs.clear();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 调整每个Tab的大小
        for (int i = 0; i < mTabViews.size(); i++) {
            View view = mTabViews.get(i);
            int width = getResources().getDisplayMetrics().widthPixels / (mTabs.size());
            LayoutParams params =
                    new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
        }

    }
}