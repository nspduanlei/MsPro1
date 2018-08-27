package com.dl.ms.mspro1.base;

import android.support.v7.app.AppCompatActivity;

import com.app.dl.networklib.server.helper.HttpReqPresenter;

public class BaseActivity extends AppCompatActivity {

    protected HttpReqPresenter mPresenter = new HttpReqPresenter();

    @Override
    public void onDestroy() {
        mPresenter.cancelRequests();
        super.onDestroy();
    }
}