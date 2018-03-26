package com.smile.ch.rxmvp.http;

import android.util.Log;

import com.smile.ch.rxmvp.constant.Constants;
import com.smile.ch.rxmvp.http.api.RetrofitServer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：网络请求管理器
 */

public class RxRetrofitManager {

    private Retrofit retrofit;
    private RetrofitServer server;

    private RxRetrofitManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String msg = URLDecoder.decode(message, "utf-8");
                    Log.i("OkHttpInterceptor----->", msg);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.i("OkHttpInterceptor----->", message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /** OKHttp默认三个超时时间是10s，有些请求时间比较长，需要重新设置下 **/
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        server = retrofit.create(RetrofitServer.class);
    }

    /** 单例模式 */
    private static class Singleton{
        private static RxRetrofitManager manager = new RxRetrofitManager();
    }

    public static RetrofitServer getInstance(){
        return Singleton.manager.server;
    }
}
