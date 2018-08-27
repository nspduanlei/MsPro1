package com.app.dl.networklib.server.helper;

import com.app.dl.networklib.domain.model.base.ComResult;
import com.app.dl.networklib.server.ErrorCode;
import com.app.dl.networklib.server.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by duanlei on 2018/4/10.
 */
public class HttpReqPresenter {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public <T> void addSubscription(Observable<T> observable, Subscriber<T> subscriber) {
        mCompositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber));
    }

    public void cancelRequests() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void twoObservable(Observable<ComResult> firstObservable,
                              final Observable<ComResult> secondObservable,
                              Subscriber<ComResult> subscriber) {
        mCompositeSubscription.add(
                firstObservable.flatMap(new Func1<ComResult, Observable<ComResult>>() {
                    @Override
                    public Observable<ComResult> call(ComResult result) {
                        if (result.getCode() == ErrorCode.RESP_SUCCESS) {
                            return secondObservable;
                        }
                        return Observable.error(new ApiException(String.valueOf(result.getCode()),
                                result.getMsg()));
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber));
    }
}
