package com.app.dl.networklib.server.interceptors;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duanlei on 2016/5/16.
 */
public class LoggingInterceptor implements Interceptor {

  Context mContext;

  public LoggingInterceptor(Context context) {
    mContext = context;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    long t1 = System.nanoTime();
    Response response = chain.proceed(request);
    long t2 = System.nanoTime();

    return response;
  }
}
