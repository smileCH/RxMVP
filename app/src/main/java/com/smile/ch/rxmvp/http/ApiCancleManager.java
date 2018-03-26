package com.smile.ch.rxmvp.http;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.Disposable;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：通过tag标签取消网络请求
 */

public class ApiCancleManager {
    private static ApiCancleManager instance;
    //管理所有请求的集合
    private ConcurrentHashMap<Object, Disposable> maps;

    private ApiCancleManager(){
        maps = new ConcurrentHashMap<>();
    }

    public static ApiCancleManager getInstance(){
        if (instance == null) {
            synchronized (ApiCancleManager.class){
                if (instance == null) {
                    instance = new ApiCancleManager();
                }
            }
        }
        return instance;
    }

    /** 添加 */
    public void add(Object tag, Disposable disposable){
        maps.put(tag, disposable);
    }

    /** 根据tag标签将此请求移除出集合(只是移除集合，并不是真正的取消请求) */
    public void remove(Object tag){
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    /** 移除集合中的所有的请求 */
    public void removeAll(){
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    /** 取消指定Tag标签的请求 */
    public void cancel(Object tag){
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isDisposed()) {
            maps.get(tag).isDisposed();
            maps.remove(tag);
        }
    }

    /** 取消所有请求 */
    public void cancelAll(){
        if (maps.isEmpty()) {
            return;
        }
        for (Object tag : maps.keySet()) {
            cancel(tag);
        }
    }
}
