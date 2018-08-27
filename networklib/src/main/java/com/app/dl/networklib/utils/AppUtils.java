package com.app.dl.networklib.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

//跟App相关的辅助类
public class AppUtils {

  private AppUtils() {
        /* cannot be instantiated */
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  @SuppressLint("MissingPermission")
  public static String getPhoneNumber(Context context) {
    //创建电话管理
    TelephonyManager tm = (TelephonyManager) context
        .getSystemService(Context.TELEPHONY_SERVICE);
    if (tm == null) {
      return "";
    }
    //获取手机号码
    return tm.getLine1Number();
  }

  /**
   * 获取应用程序名称
   */
  public static String getAppName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
          context.getPackageName(), 0);
      int labelRes = packageInfo.applicationInfo.labelRes;
      return context.getResources().getString(labelRes);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取应用程序版本名称信息
   *
   * @param context
   * @return 当前应用的版本名称
   */
  public static String getVersionName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
          context.getPackageName(), 0);
      return packageInfo.versionName;

    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 返回当前程序版本号（内部版本号）
   */
  public static int getVersionCode(Context context) {
    int versionCode = 0;
    try {
      // ---get the package info---
      PackageManager packageManager = context.getPackageManager();
      // 这里的context.getPackageName()可以换成你要查看的程序的包名
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

      versionCode = packageInfo.versionCode;
    } catch (Exception e) {
      Log.e("VersionInfo", "Exception", e);
    }
    return versionCode;
  }

  /**
   * 获得设备识别认证码
   *
   * @return
   */
  @SuppressLint("HardwareIds")
  public static String getDeviceId(Context context) {

    //
    if (ContextCompat.checkSelfPermission(context,
        Manifest.permission.READ_PHONE_STATE)
        != PackageManager.PERMISSION_GRANTED) {
      return "000000000000000";
    }

    TelephonyManager tm = (TelephonyManager) context
        .getSystemService(Context.TELEPHONY_SERVICE);
    if (tm == null) {
      return "000000000000000";
    }

    if (tm.getDeviceId() == null) {
      return "000000000000000";
    }

    return tm.getDeviceId();
  }

  /**
   * 获得imsi
   *
   * @return
   */
  public static String getImsi(Context context) {
    TelephonyManager tm = (TelephonyManager) context
        .getSystemService(Context.TELEPHONY_SERVICE);
    if (tm == null) {
      return "000000000000000";
    }

    @SuppressLint("MissingPermission") String issi = tm.getSubscriberId();
    if (StringUtils.isNullOrEmpty(issi)) {
      return "000000000000000";
    }

    return issi;
  }

  @SuppressLint({"HardwareIds", "MissingPermission"})
  public static String getIccid(Context context) {
    TelephonyManager tm = (TelephonyManager) context
        .getSystemService(Context.TELEPHONY_SERVICE);
    if (tm == null) {
      return "000000000000000";
    }
    return tm.getSimSerialNumber();
  }

  /**
   * 获取sdk版本
   *
   * @return
   */
  public static int getAndroidSDKVersion() {
    return Build.VERSION.SDK_INT;
  }


  /**
   * 固件版本，系统版本
   *
   * @return
   */
  public static String getSysVersion() {
    return Build.VERSION.RELEASE;
  }

  /**
   * 机型
   *
   * @return
   */
  public static String getModel() {
    return Build.MODEL;
  }


  /**
   * 获取渠道号
   */
  public static String getChannelId(Context context, String name) {
    ApplicationInfo appInfo = null;
    try {
      appInfo = context.getPackageManager()
          .getApplicationInfo(context.getPackageName(),
              PackageManager.GET_META_DATA);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    assert appInfo != null;

    return appInfo.metaData.getString(name);
  }

  public static void sendSms(String mobile, String content, Context context) {
    //获取SmsManager
    SmsManager sms = SmsManager.getDefault();

    Intent sIntent = new Intent("send_sms");
    PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, sIntent, 0);//短信成功发送后才发送该广播

    Intent dIntent = new Intent("deliver_sms");
    PendingIntent deliveryIntent = PendingIntent.getBroadcast(context, 1, dIntent, 0);//短信成功接收后才发送该广播

    sms.sendTextMessage(mobile, null, content, sentIntent, deliveryIntent);
  }

  /**
   * 判断app是否在后台运行
   *
   * @param context
   * @return
   */
  public static boolean isBackground(Context context) {
    ActivityManager activityManager = (ActivityManager) context
        .getSystemService(Context.ACTIVITY_SERVICE);

    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
        .getRunningAppProcesses();

    for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
      if (appProcess.processName.equals(context.getPackageName())) {
        /*
        BACKGROUND=400 EMPTY=500 FOREGROUND=100
				GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */
        Log.i(context.getPackageName(), "此appimportace ="
            + appProcess.importance
            + ",context.getClass().getName()="
            + context.getClass().getName());

        if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
          Log.i(context.getPackageName(), "处于后台"
              + appProcess.processName);
          return true;
        } else {
          Log.i(context.getPackageName(), "处于前台"
              + appProcess.processName);
          return false;
        }
      }
    }
    return false;
  }

  /**
   * 获取ip地址
   */
  public static String getIP(Context application) {
    //获取wifi服务
    WifiManager wifiManager = (WifiManager) application.getSystemService(Context.WIFI_SERVICE);
    //判断wifi是否开启
    if (!wifiManager.isWifiEnabled()) {
      try {
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
          NetworkInterface intf = en.nextElement();
          for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
            InetAddress inetAddress = enumIpAddr.nextElement();
            if (!inetAddress.isLoopbackAddress()) {
              return inetAddress.getHostAddress().toString();
            }
          }
        }
      } catch (SocketException e) {
        e.printStackTrace();
      }
    } else {
      WifiInfo wifiInfo = wifiManager.getConnectionInfo();
      int ipAddress = wifiInfo.getIpAddress();
      String ip = intToIp(ipAddress);
      return ip;
    }
    return null;
  }


  private static String intToIp(int i) {

    return (i & 0xFF) + "." +
        ((i >> 8) & 0xFF) + "." +
        ((i >> 16) & 0xFF) + "." +
        (i >> 24 & 0xFF);
  }

}
