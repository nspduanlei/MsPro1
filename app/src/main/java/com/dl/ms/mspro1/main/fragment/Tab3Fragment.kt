package com.dl.ms.mspro1.main.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.dl.networklib.domain.model.Car
import com.app.dl.networklib.domain.model.New
import com.app.dl.networklib.domain.model.base.Result
import com.app.dl.networklib.imageLoad.GlideImageLoad
import com.app.dl.networklib.server.ApiCallback
import com.app.dl.networklib.server.ApiClient
import com.app.dl.uilibrary.recycler.CommonRecyclerAdapter
import com.app.dl.uilibrary.recycler.MyViewHolder
import com.dl.ms.mspro1.R
import com.dl.ms.mspro1.base.BaseFragment
import com.dl.ms.mspro1.main.DetailActivity
import kotlinx.android.synthetic.main.fragment_tab3.*

class Tab3Fragment : BaseFragment() {

    lateinit var mAdapter: CommonRecyclerAdapter<Car>
    lateinit var mAdapterNews: CommonRecyclerAdapter<New>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_tab3, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_icons.layoutManager = GridLayoutManager(activity, 4)
                as RecyclerView.LayoutManager?

        mAdapter = object : CommonRecyclerAdapter<Car>(activity!!,
                R.layout.item_car_icons, mutableListOf()) {
            override fun convert(holder: MyViewHolder, t: Car, position: Int) {

                holder.setText(R.id.tv_title, t.name)
                holder.setImageUrl(R.id.iv_icon, t.img, GlideImageLoad.getInstance())
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



        rv_news.layoutManager = LinearLayoutManager(activity)

        var newsList = mutableListOf<New>()
        newsList.add(New("FE纽约站次回合：维尔格尼夺冠 奥迪年度车队冠军",
                "http://k.sinaimg.cn/n/sports/transform/283/w650h433/20180716/LOeR-hfkffak4275358.jpg/w180h135l50t1551.jpg",
                "2018赛季FE电动方程式锦标赛收官之战在纽约上演",
                "2018年07月16日 11:55"))

        newsList.add(New("FE纽约站首回合迪-格拉西夺冠 维尔格尼年度冠军",
                "http://k.sinaimg.cn/n/sports/transform/200/w600h400/20180715/zJi8-fzrwiaz8807916.jpg/w180h135l50t1d06.jpg",
                "迪-格拉西夺取分站冠军，维尔格尼提前1站加冕了年度车手总冠军。", "2018年07月15日 08:57"))

        newsList.add(New("FE电动方程式重返中国大陆 2019年落地三亚海棠湾",
                "http://k.sinaimg.cn/n/sports/transform/240/w650h390/20180703/ILBP-hevauxi6387595.jpg/w180h135l50t1ae8.jpg",
                "2018赛季FE电动方程式锦标赛收官之战在纽约上演", "2018年07月03日 16:00"))

        newsList.add(New("FE苏黎世站：奥迪车队迪格拉西夺冠",
                "http://k.sinaimg.cn/n/sports/transform/261/w650h411/20180611/CGjy-hcufqif5873147.jpg/w180h135l50t141e.jpg",
                "禁止汽车赛事60年之久的瑞士苏黎世为了电动方程式而亮绿灯。", "2018年06月11日 01:17"))

        mAdapterNews = object : CommonRecyclerAdapter<New>(activity!!,
                R.layout.news_item, newsList.toMutableList()) {
            override fun convert(holder: MyViewHolder, t: New, position: Int) {
                holder.setText(R.id.tv_name, t.name)
                holder.setText(R.id.tv_detail, t.detail)
                holder.setText(R.id.tv_time, t.time)
                holder.setImageUrl(R.id.iv_head, t.img, GlideImageLoad.getInstance())
            }
        }
        rv_news.adapter = mAdapterNews
        getData()
    }

    private fun getData() {
        mPresenter.addSubscription(ApiClient.retrofit().queryBrand(),
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