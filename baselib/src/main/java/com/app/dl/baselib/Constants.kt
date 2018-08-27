package com.app.dl.baselib

import android.app.Application
import com.daque.corelibrary.extensions.DelegatesExt

/**
 * Created by duanlei on 2018/4/21.
 *
 */
class Constants {
    companion object {
        var instance: Application by DelegatesExt.notNullSingleValue()
    }
}