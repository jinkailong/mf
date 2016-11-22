package com.jzhu.study.mf.base;

import android.net.ParseException;
import android.util.Log;

import com.jzhu.study.mf.data.exception.BusinessException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

public class BaseSubscriber<T> extends DefaultSubscriber<T> {

    private BaseView baseView;

    public BaseSubscriber(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        if (baseView != null) {
            baseView.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof BusinessException) {
            if (baseView != null) {
                baseView.hideLoading();
            }
        } else if (e instanceof ConnectException
                || e instanceof SocketTimeoutException) {// 超时
            baseView.onError("网络不畅，请稍后再试！");
        } else if (e instanceof HttpException) {// server 异常
            baseView.onError("服务器异常，请稍后再试！");
        } else if (e instanceof JSONException
                || e instanceof ParseException) {
            baseView.onError("数据解析异常");
        } else {
            baseView.onError("服务器繁忙");
            onOtherError(e);
        }

    }

    public void onOtherError(Throwable e) {
        super.onError(e);
        if (baseView != null) {
            baseView.hideLoading();
            if (e.getMessage() != null) {
                Log.e(baseView.getClass().getSimpleName(), "" + e.getMessage());
            }
            e.printStackTrace();
        }
    }
}
