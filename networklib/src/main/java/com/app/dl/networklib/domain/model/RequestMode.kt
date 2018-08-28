package com.app.dl.networklib.domain.model

/**
 * Created by duanlei on 2018/4/23.
 * 服务器接口实体
 */

data class Car(val name: String, val img: String, val detail: String)

data class Match(val title: String, val img: String,
                 val detail: String, val status: String)

data class Person(val name: String, val img: String, val tags: String)
