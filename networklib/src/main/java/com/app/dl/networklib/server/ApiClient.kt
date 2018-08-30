package com.app.dl.networklib.server

import com.app.dl.baselib.Constants
import com.app.dl.baselib.utils.LogUtil
import com.app.dl.networklib.server.interceptors.MockInterceptor
import com.daque.apilibrary.data.server.ApiStores
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by duanlei on 2018/2/26.
 *
 */
object ApiClient {

    fun retrofit(): ApiStores {
        val builder = OkHttpClient.Builder()

        if (LogUtil.isDebug) {
            //log 信息拦截器
            val loggingInterface = HttpLoggingInterceptor()
            loggingInterface.level = HttpLoggingInterceptor.Level.BODY

            //设置Debug Log模式
            builder.addInterceptor(loggingInterface)
        }

        val okHttpClient = builder
                .retryOnConnectionFailure(true)
//                .addNetworkInterceptor(HeaderInterceptor())
//                .addNetworkInterceptor(MockInterceptor(Constants.instance))
                .addInterceptor(MockInterceptor(Constants.instance))
//                .addNetworkInterceptor(StethoInterceptor())
                .build()

        //根据渠道名使用不同的服务器环境
//        val baseUrl = if (ChannelUtils.isDev()) {
//            ApiStores.API_SERVER_URL_TEST
//        } else {
//            ApiStores.API_SERVER_URL
//        }
        val baseUrl = "http://app.27305.com/"

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(ApiStores::class.java)
    }

}