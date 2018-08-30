package com.app.dl.baselib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

//import com.daque.corelibrary.view.XToast;

//Toast统一管理类
public class ToastUtil {

  private static Toast sToast;

//  private static XToast sToast;

  private ToastUtil() {
        /* cannot be instantiated */
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  public static boolean isShow = true;

  /**
   * 短时间显示Toast
   *
   * @param context
   * @param message
   */
  @SuppressLint("ShowToast")
  public static void showShort(Context context, String message) {

    if (isShow && message != null && message.length() > 0) {
      if (sToast == null) {
        sToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        sToast.setGravity(Gravity.CENTER, 0, 0);
      } else {
        sToast.setText(message);
      }
      sToast.show();
    }
  }

  /**
   * 短时间显示Toast
   *
   * @param context
   * @param message
   */
  @SuppressLint("ShowToast")
  public static void showShort(Context context, int message) {
    if (isShow) {
      if (sToast == null) {
        sToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        sToast.setGravity(Gravity.CENTER, 0, 0);
      } else {
        sToast.setText(message);
      }
      sToast.show();
    }
  }

  /**
   * 长时间显示Toast
   *
   * @param context
   * @param message
   */
  public static void showLong(Context context, CharSequence message) {
    if (isShow) {
      Toast toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
  }

  /**
   * 长时间显示Toast
   *
   * @param context
   * @param message
   */
  public static void showLong(Context context, int message) {
    if (isShow) {
      Toast toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
  }

  /**
   * 自定义显示Toast时间
   *
   * @param context
   * @param message
   * @param duration
   */
  public static void show(Context context, CharSequence message, int duration) {
    if (isShow)
      Toast.makeText(context.getApplicationContext(), message, duration).show();
  }

  /**
   * 自定义显示Toast时间
   *
   * @param context
   * @param message
   * @param duration
   */
  public static void show(Context context, int message, int duration) {
    if (isShow)
      Toast.makeText(context.getApplicationContext(), message, duration).show();
  }

}