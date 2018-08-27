package com.dl.ms.mspro1.base;

import android.support.v4.app.Fragment;

import com.app.dl.networklib.server.helper.HttpReqPresenter;

public class BaseFragment extends Fragment {

    protected HttpReqPresenter mPresenter = new HttpReqPresenter();

    @Override
    public void onDestroy() {
        mPresenter.cancelRequests();
        super.onDestroy();
    }
}