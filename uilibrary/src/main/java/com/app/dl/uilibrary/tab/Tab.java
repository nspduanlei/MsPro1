package com.app.dl.uilibrary.tab;

public class Tab{
    private int mIconNormalResId;
    private int mIconPressedResId;
    private int mNormalColor;
    private int mSelectColor;
    private String mText;

    public Tab setText(String text){
        mText = text;
        return this;
    }

    public Tab setNormalIcon(int res){
        mIconNormalResId = res;
        return this;
    }

    public Tab setPressedIcon(int res){
        mIconPressedResId = res;
        return this;
    }

    public Tab setColor(int color){
        mNormalColor = color;
        return this;
    }

    public Tab setCheckedColor(int color){
        mSelectColor = color;
        return this;
    }

    public int getIconNormalResId() {
        return mIconNormalResId;
    }

    public int getIconPressedResId() {
        return mIconPressedResId;
    }

    public int getNormalColor() {
        return mNormalColor;
    }

    public int getSelectColor() {
        return mSelectColor;
    }

    public String getText() {
        return mText;
    }
}
