package com.dl.ms.mspro1.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dl.ms.mspro1.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_top_bar.*


class DetailActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val detailStr = intent.getStringExtra("detail")
        tv_detail.text = detailStr
        setTopBar()
    }

    private fun setTopBar() {
        tv_title.text = "品牌介绍"
        iv_back.setOnClickListener { this.finish() }
    }
}