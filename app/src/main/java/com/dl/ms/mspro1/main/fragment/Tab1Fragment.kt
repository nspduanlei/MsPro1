package com.dl.ms.mspro1.main.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dl.networklib.domain.model.Car
import com.app.dl.networklib.domain.model.base.Result
import com.app.dl.networklib.imageLoad.GlideImageLoad
import com.app.dl.networklib.server.ApiCallback
import com.app.dl.networklib.server.ApiClient
import com.app.dl.networklib.utils.SPUtils
import com.app.dl.uilibrary.recycler.CommonRecyclerAdapter
import com.app.dl.uilibrary.recycler.MyViewHolder
import com.dl.ms.mspro1.R
import com.dl.ms.mspro1.base.BaseFragment
import com.dl.ms.mspro1.main.DetailActivity
import kotlinx.android.synthetic.main.fragment_tab1.*


class Tab1Fragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    lateinit var mAdapter: CommonRecyclerAdapter<Car>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_icons.layoutManager = GridLayoutManager(activity,4)

        mAdapter = object : CommonRecyclerAdapter<Car>(activity!!,
                R.layout.item_car_icons, mutableListOf()) {
            override fun convert(holder: MyViewHolder, t: Car, position: Int) {

                holder.setText(R.id.tv_title, t.name)
                holder.setImageUrl(R.id.iv_icon, t.img, GlideImageLoad())
                holder.setOnItemClickListener(object : MyViewHolder.OnItemClickListener {
                            override fun onItemClick(v: View) {
                                val intent = Intent(activity, DetailActivity::class.java)
                                intent.putExtra("detail", t.detail)
                                startActivity(intent)
                            }
                        })
            }
        }
        rv_icons.adapter = mAdapter

        getData()
    }

    private fun getData() {
        mPresenter.addSubscription(ApiClient.retrofit().queryTest(),
                object : ApiCallback<Result<List<Car>>>() {
                    override fun onSuccess(t: Result<List<Car>>) {
                        mAdapter.addAllC(t.data)
                    }
                    override fun onFailure(errorCode: Int, msg: String?) {
                        Log.e("testdl", "onFailure--------")
                    }
                    override fun onFinish() {
                        Log.e("testdl", "onFinish--------")
                    }
                })
    }
}
