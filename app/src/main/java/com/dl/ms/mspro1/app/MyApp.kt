package com.dl.ms.mspro1.app

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.app.dl.baselib.Constants


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Constants.instance = this

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}


