package com.dl.ms.mspro1.main.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.app.dl.networklib.domain.model.Match
import com.app.dl.networklib.domain.model.Person
import com.app.dl.networklib.domain.model.base.Result
import com.app.dl.networklib.imageLoad.GlideImageLoad
import com.app.dl.networklib.server.ApiCallback
import com.app.dl.networklib.server.ApiClient
import com.app.dl.uilibrary.recycler.CommonRecyclerAdapter
import com.app.dl.uilibrary.recycler.MyViewHolder
import com.dl.ms.mspro1.R
import com.dl.ms.mspro1.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab2.*

class Tab2Fragment :  BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    lateinit var mAdapterMatch: CommonRecyclerAdapter<Match>
    lateinit var mAdapterPerson: CommonRecyclerAdapter<Person>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_match.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL,false)

        mAdapterMatch = object : CommonRecyclerAdapter<Match>(activity!!,
                R.layout.item_match, mutableListOf()) {
            override fun convert(holder: MyViewHolder, t: Match, position: Int) {
                holder.setText(R.id.tv_title, t.title)
                        .setText(R.id.tv_detail, t.detail)
                        .setText(R.id.tv_status, t.status)
                        .setImageUrl(R.id.iv_image, t.img, GlideImageLoad.getInstance())
            }
        }
        rv_match.adapter = mAdapterMatch


        rv_person.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,false)

        mAdapterPerson = object : CommonRecyclerAdapter<Person>(activity!!,
                R.layout.item_person, mutableListOf()) {
            override fun convert(holder: MyViewHolder, t: Person, position: Int) {

                holder.setText(R.id.tv_title, t.name)
                        .setImageUrl(R.id.iv_image, t.img, GlideImageLoad.getInstance())

                val textViews = arrayListOf<TextView>()
                for (tag in t.tags.split("-")) {
                    val textView = TextView(activity)
                    textView.text = tag
                    textView.setBackgroundResource(R.drawable.shape_text_frame)
                    textView.textSize = 10f
                    textView.setTextColor(Color.parseColor("#F26D44"))
                    textView.setPadding(10,10,10,10)

                    val layoutParams =
                            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(0,0,10,0)
                    textView.layoutParams = layoutParams
                    textViews.add(textView)
                }
                holder.setLinearLayout(R.id.ll_tags, textViews)
            }
        }
        rv_person.adapter = mAdapterPerson

        getDataMatch()
        getDataPerson()
    }

    private fun getDataMatch() {
        mPresenter.addSubscription(ApiClient.retrofit().queryMatch(),
                object : ApiCallback<Result<List<Match>>>() {
                    override fun onSuccess(t: Result<List<Match>>) {
                        mAdapterMatch.addAllC(t.data)
                    }
                    override fun onFailure(errorCode: Int, msg: String?) {
                        Log.e("testdl", "onFailure--------")
                    }
                    override fun onFinish() {
                        Log.e("testdl", "onFinish--------")
                    }
                })
    }

    private fun getDataPerson() {
        mPresenter.addSubscription(ApiClient.retrofit().queryPerson(),
                object : ApiCallback<Result<List<Person>>>() {
                    override fun onSuccess(t: Result<List<Person>>) {
                        mAdapterPerson.addAllC(t.data)
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
