package com.smile.ch.rxmvp.base;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：presenter基类
 */

public abstract class BasePresenter<V extends BaseView>{
    private V mvpView;

    /**
     * 绑定View
     * @param mvpView
     */
    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    /**
     * 解绑View
     */
    public void detachView(){
        mvpView = null;
    }

    /**
     * 获取View
     * @return
     */
    public V getMvpView(){
        return mvpView;
    }
}
