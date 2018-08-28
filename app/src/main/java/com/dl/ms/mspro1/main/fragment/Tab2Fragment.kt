package com.dl.ms.mspro1.main.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dl.networklib.domain.model.Car
import com.app.dl.networklib.domain.model.Match
import com.app.dl.networklib.domain.model.base.Result
import com.app.dl.networklib.imageLoad.GlideImageLoad
import com.app.dl.networklib.server.ApiCallback
import com.app.dl.networklib.server.ApiClient
import com.app.dl.uilibrary.recycler.CommonRecyclerAdapter
import com.app.dl.uilibrary.recycler.MyViewHolder
import com.dl.ms.mspro1.R
import com.dl.ms.mspro1.base.BaseFragment
import com.dl.ms.mspro1.main.DetailActivity
import kotlinx.android.synthetic.main.fragment_tab2.*

class Tab2Fragment :  BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    lateinit var mAdapter: CommonRecyclerAdapter<Match>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_match.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL,false)

        mAdapter = object : CommonRecyclerAdapter<Match>(activity!!,
                R.layout.item_car_icons, mutableListOf()) {
            override fun convert(holder: MyViewHolder, t: Match, position: Int) {

                //holder.setText()
            }
        }
        rv_match.adapter = mAdapter

        getDataMatch()
    }

    private fun getDataMatch() {
        mPresenter.addSubscription(ApiClient.retrofit().queryMatch(),
                object : ApiCallback<Result<List<Match>>>() {
                    override fun onSuccess(t: Result<List<Match>>) {
                        //mAdapter.addAllC(t.data)
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
