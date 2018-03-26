package com.smile.ch.rxmvp.http.api;

import com.smile.ch.rxmvp.base.BaseBean;
import com.smile.ch.rxmvp.bean.MeizhiBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：
 */

public interface RetrofitServer {

    @GET("{type}/{count}/{page}")
    Observable<BaseBean<List<MeizhiBean>>> getMeizhiImgs(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
