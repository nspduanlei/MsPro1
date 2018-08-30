package com.dl.ms.mspro1.main.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.dl.networklib.domain.model.Car
import com.app.dl.networklib.domain.model.base.Result
import com.app.dl.networklib.imageLoad.GlideImageLoad
import com.app.dl.networklib.server.ApiCallback
import com.app.dl.networklib.server.ApiClient
import com.app.dl.uilibrary.recycler.CommonRecyclerAdapter
import com.app.dl.uilibrary.recycler.MyViewHolder
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.dl.ms.mspro1.R
import com.dl.ms.mspro1.base.BaseFragment
import com.dl.ms.mspro1.main.DetailActivity
import com.dl.ms.mspro1.model.MyBanner
import kotlinx.android.synthetic.main.fragment_tab1.*


class Tab1Fragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    lateinit var mAdapter: CommonRecyclerAdapter<Car>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_icons.layoutManager = GridLayoutManager(activity,4) as RecyclerView.LayoutManager?

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

        getData()

        initBanner()
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


    /***************轮播 start ***************/
    private var mLocalImages = arrayListOf<MyBanner>()

    private fun initBanner() {
        mLocalImages.add(MyBanner("", "http://sportspic.oss-cn-shanghai.aliyuncs.com/20180430100657-374432.png?x-oss-process=image/crop,x_1,y_0,w_649,h_365",
                "官方推荐 2018赛季F1阿塞拜疆站官方赛后总结"))
        mLocalImages.add(MyBanner("", "http://img.lesports.com/20180711132722-750985/169.jpg",
                "李先祥沈有江备战张掖 加紧练体能誓要进三甲"))
        mLocalImages.add(MyBanner("", "http://sportspic.oss-cn-shanghai.aliyuncs.com/20180509121129-442155.jpg?x-oss-process=image/crop,x_0,y_0,w_1280,h_720",
                "经典对抗 2018赛季WRC科西嘉站历史与现代的对决"))
        mLocalImages.add(MyBanner("", "http://sportspic.oss-cn-shanghai.aliyuncs.com/20180817144658-522662.jpg?x-oss-process=image/crop,x_0,y_0,w_1080,h_608",
                "燕山南麓渤海之滨 中国秦皇岛首钢赛车谷盛大开启"))

        convenientBanner.setPages(
                object : CBViewHolderCreator {
                    override fun createHolder(itemView: View): LocalImageHolderView {
                        return LocalImageHolderView(itemView)
                    }
                    override fun getLayoutId(): Int {
                        return R.layout.item_localimage
                    }
                },
                mLocalImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                //.setPageIndicator(intArrayOf(R.drawable.shape_page_indicator, R.drawable.shape_page_indicator_focused))
                //设置指示器的方向
                .startTurning(4000) //4s自动滚动
                //.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener { position ->
                    val myBanner = mLocalImages[position]
                }

    }

    inner class LocalImageHolderView(itemView: View?) : Holder<MyBanner>(itemView) {

        private lateinit var imageView: ImageView
        private lateinit var tvDetail: TextView

        override fun updateUI(data: MyBanner) {
            GlideImageLoad.getInstance().loadUrlDefault(activity,
                    imageView, data.url, R.drawable.banner_default)

            tvDetail.text = data.detail
        }

        override fun initView(itemView: View) {
            imageView = itemView.findViewById(R.id.iv_image)
            tvDetail = itemView.findViewById(R.id.tv_detail)
        }
    }
    /***************轮播 end*********************/
}
