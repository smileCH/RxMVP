package com.smile.ch.rxmvp.presenter;

import android.content.Context;

import com.smile.ch.rxmvp.base.BaseBean;
import com.smile.ch.rxmvp.base.BasePresenter;
import com.smile.ch.rxmvp.bean.MeizhiBean;
import com.smile.ch.rxmvp.contract.MeizhiContract;
import com.smile.ch.rxmvp.model.MZModel;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：妹纸图片的presenter实现类
 */

public class MZPresenterImpl extends BasePresenter<MeizhiContract.MZView> {
    private MZModel mzModel;

    public MZPresenterImpl(){
        mzModel = new MZModel();
    }

    public void requestMZDatas(Context context, String type, int count, int page){
        mzModel.getMZDatas(context, type, count, page, new MeizhiContract.IMzModelCallback() {
            @Override
            public void onSuccess(BaseBean<List<MeizhiBean>> response) {
                if (getMvpView() != null) {
                    getMvpView().onSuccessInfo(response);
                }
            }

            @Override
            public void onFaile(BaseBean<List<MeizhiBean>> response) {
                if (getMvpView() != null) {
                    getMvpView().onFaile(response);
                }
            }
        });
    }

    public void cancleHttpRequest(){
        mzModel.cancleHttpRequest();
    }
}
