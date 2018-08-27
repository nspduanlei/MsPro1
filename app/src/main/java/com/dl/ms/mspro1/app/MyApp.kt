package com.dl.ms.mspro1.app

import android.app.Application
import com.app.dl.baselib.Constants


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Constants.instance = this
    }
}


