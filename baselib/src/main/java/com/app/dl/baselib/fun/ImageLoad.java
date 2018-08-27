package com.app.dl.baselib.fun;

import android.content.Context;
import android.widget.ImageView;

public interface ImageLoad {
    void loadUrl(Context context, ImageView iv, String url);
    void loadUrlDefault(Context context, ImageView iv, String url, int resId);
    void loadLargeUrl(Context context, ImageView iv, String url);
    void loadUrlCircle(Context context, ImageView iv, String url);
    void loadUrlRound(Context context, ImageView iv, String url, int dp);
}
