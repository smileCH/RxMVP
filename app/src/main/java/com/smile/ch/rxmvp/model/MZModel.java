package com.smile.ch.rxmvp.model;

import android.content.Context;

import com.smile.ch.rxmvp.base.BaseBean;
import com.smile.ch.rxmvp.bean.MeizhiBean;
import com.smile.ch.rxmvp.contract.MeizhiContract;
import com.smile.ch.rxmvp.http.ApiCancleManager;
import com.smile.ch.rxmvp.http.RxObserver;
import com.smile.ch.rxmvp.http.RxRetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：妹纸图片加载数据的model
 */

public class MZModel implements MeizhiContract.IMzModel{

    @Override
    public void getMZDatas(Context context, String type, int count, int page, final MeizhiContract.IMzModelCallback callback) {
        RxRetrofitManager.getInstance()
                .getMeizhiImgs(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<BaseBean<List<MeizhiBean>>>(context, "MZInterface") {
                    @Override
                    public void onSuccess(BaseBean<List<MeizhiBean>> response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onFailed(BaseBean<List<MeizhiBean>> response) {
                        callback.onFaile(response);
                    }
                });
    }

    @Override
    public void cancleHttpRequest() {
        ApiCancleManager.getInstance().cancel("MZInterface");
    }
}
