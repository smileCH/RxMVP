package com.smile.ch.rxmvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：activity基类
 */

public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity {
    private P presenter;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.context = this;
        //实例化Presenter
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter不能为空!!");
        }
        //绑定
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        //初始化控件
        initViews();
        //获取数据
        getDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected abstract int getLayoutId();
    protected abstract P createPresenter();
    protected abstract void initViews();
    protected void getDatas(){}

    /**
     * 获取presenter
     * @return
     */
    protected P getPresenter(){
        return presenter;
    }
}
