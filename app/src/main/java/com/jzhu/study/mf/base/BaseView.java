package com.jzhu.study.mf.base;

/**
 * Created by jzhu on 2016/11/22.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void onError(String msg);
    void onSuccess(String msg);

}
