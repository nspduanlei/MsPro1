package com.app.dl.networklib.imageLoad;

import android.content.Context;
import android.widget.ImageView;

import com.app.dl.baselib.fun.ImageLoad;
import com.app.dl.networklib.utils.StringUtils;
import com.bumptech.glide.Glide;

/**
 * Created by duanlei on 2016/11/8.
 */
public class GlideImageLoad implements ImageLoad {

    public void loadUrl(Context context, ImageView iv, String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
//                .crossFade()
                .into(iv);

    }

    public void loadUrlDefault(Context context, ImageView iv, String url, int resId) {
        if (StringUtils.isNullOrEmpty(url)) {
            return;
        }
//        Glide.with(context)
//                .load(url)
//                .placeholder(resId)
//                .crossFade()
//                .into(iv);
    }

    public void loadLargeUrl(Context context, ImageView iv, String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            return;
        }
//        Glide.with(context)
//                .load(url)
//                .fitCenter()
//                .into(iv);


    }

    public void loadUrlCircle(Context context, ImageView iv, String url) {
        if (StringUtils.isNullOrEmpty(url)) {
            return;
        }
//        Glide.with(context)
//                .load(url)
//                .transform(new GlideCircleTransform(context))
//                .into(iv);
    }

    public void loadUrlRound(Context context, ImageView iv, String url, int dp) {
        if (StringUtils.isNullOrEmpty(url)) {
            return;
        }
//        Glide.with(context)
//                .load(url)
//                .centerCrop()
//                .crossFade()
//                .transform(new GlideRoundTransform(context, dp))
//                .into(iv);
    }
}

