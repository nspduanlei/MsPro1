package com.daque.apilibrary.data.server

import com.app.dl.networklib.domain.model.Car
import com.app.dl.networklib.domain.model.base.Result
import retrofit2.http.GET
import rx.Observable

/**
 * Created by duanlei on 2018/2/26.
 *
 */
interface ApiStores {
    companion object {
        const val API_SERVER_URL_TEST = "" //测试服务器地址
        const val API_SERVER_URL = "" //正式服务器地址
        const val PAGE_SIZE = 15
    }

    @GET("testapi/test")
    fun queryTest(): Observable<Result<List<Car>>>

    @GET("testapi/test1")
    fun queryTest1(): Observable<Result<List<Car>>>

}