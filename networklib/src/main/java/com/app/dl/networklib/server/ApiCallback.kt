package com.app.dl.networklib.server

import android.annotation.SuppressLint
import android.util.Log
import com.app.dl.networklib.domain.model.base.ComResult
import retrofit2.HttpException
import rx.Subscriber

/**
 * Created by duanlei on 2018/2/26.
 *
 */
abstract class ApiCallback<T> : Subscriber<T>() {

    abstract fun onSuccess(t: T)
    abstract fun onFailure(errorCode: Int, msg: String?)
    abstract fun onFinish()

    override fun onCompleted() {
        onFinish()
    }

    @SuppressLint("ShowToast")
    override fun onNext(t: T) {
        if (t is ComResult) {
            when (t.code) {
                ErrorCode.RESP_SUCCESS -> {
                    onSuccess(t)
                }
                ErrorCode.RESP_NOEXIST -> {
                    //ToastUtil.showShort(Constants.instance, "帐号不存在，请先注册")
                }
                ErrorCode.RESP_UNLOGIN -> {
                    //EventBus.getDefault().post(MessageEvent(MessageEvent.NOT_LOGIN))
                    //EventBusActivityScope.getDefault(activity).post(MessageEvent(MessageEvent.NOT_LOGIN))
                }
            }

            if (t.code != ErrorCode.RESP_SUCCESS) {
                onFailure(t.code, t.msg)
                //ToastUtil.showShort(Constants.instance, t.msg)
                //LogUtil.e("ApiCallback", "errorMsg:" + t.msg + ", ErrorCode:" + t.code)
            }
        } else {
            //LogUtil.e("ApiCallback", "数据格式错误----------------")
        }
    }

    @SuppressLint("ShowToast")
    override fun onError(e: Throwable?) {
        if (e != null) {
            //LogUtil.e("ApiCallback", "onError----------------" + e.message)
        }

        //ToastUtil.showShort(Constants.instance, "网络错误")

        if (e is HttpException) {
            val code = e.code()
            var msg = e.message

            Log.d("wxl", "code=$code")

            if (code == 504) {
                msg = "网络不给力"
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试"
            }
            onFailure(code, msg)
        } else {
            onFailure(-10000, e.toString())
        }

        onFinish()
    }
}