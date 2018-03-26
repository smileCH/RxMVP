package com.smile.ch.rxmvp.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.smile.ch.rxmvp.base.BaseBean;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：
 */

public abstract class RxObserver<T extends BaseBean> implements Observer<T> {
    private Context context;
    //添加tag标签，统一管理请求(比如取消请求等操作)
    private String tag;
    private ProgressDialog progressDialog;

    public RxObserver(Context context, String addTag) {
        this.context = context;
        this.tag = addTag;
        //显示弹窗进度条
        startProgressDialog(context);
    }

    public RxObserver(Context context, boolean isShowProgress, String tag) {
        this.context = context;
        this.tag = tag;
        //显示弹窗进度条
        if (isShowProgress) {
            startProgressDialog(context);
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(tag)) {
            ApiCancleManager.getInstance().add(tag, d);
        }
    }

    @Override
    public void onNext(@NonNull T response) {
        //关闭弹窗进度条
        stopProgressDialog();
        /*if (response.getStatus() == 200) {
            onSuccess(response);
        }else {
            onFailed(response);
        }*/
        onSuccess(response);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        //关闭弹窗进度条
        stopProgressDialog();
        if (e instanceof UnknownHostException || e instanceof ConnectException) {//无网络
            solveException(ExceptionType.BAD_NETWORK);
        } else if (e instanceof JsonParseException || e instanceof JSONException ||
                e instanceof ParseException) {//解析异常
            solveException(ExceptionType.PARSE_DATA_ERROR);
        } else if (e instanceof HttpException) {//http异常，比如 404 500
            solveException(ExceptionType.UNFOUND_ERROR);
        } else if (e instanceof SocketTimeoutException) {//连接超时
            solveException(ExceptionType.TIMEOUT_ERROR);
        } else {//未知错误
            solveException(ExceptionType.UNKNOWN_ERROR);
        }
    }

    /**
     * 启动加载进度条
     */
    public void startProgressDialog(Context cxt) {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(cxt);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("正在拼命加载中...");
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭加载进度条
     */
    public void stopProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求成功回调
     * @param response 服务端返回的数据
     */
    public abstract void onSuccess(T response);

    /**
     * 请求失败的回调 (非 200的情况)，开发者手动去触发
     * @param response 数据
     */
    public void onFailed(T response){}

    /**
     * 连接异常时回调，手动触发
     */
    public void onError(){}

    /**
     * 对于异常情况的统一处理
     * @param type 异常的类型
     */
    public void solveException(ExceptionType type){
        switch (type){
            case BAD_NETWORK:
                Toast.makeText(context, "无网络", Toast.LENGTH_SHORT).show();
                break;
            case PARSE_DATA_ERROR:
                Toast.makeText(context, "数据解析异常", Toast.LENGTH_SHORT).show();
                break;
            case UNFOUND_ERROR:
                Toast.makeText(context, "未找到指定请求接口", Toast.LENGTH_SHORT).show();
                break;
            case TIMEOUT_ERROR:
                Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
                break;
            case UNKNOWN_ERROR:
                Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public enum ExceptionType {
        /** 无网络 */
        BAD_NETWORK,
        /** 数据解析异常 */
        PARSE_DATA_ERROR,
        /** 找不到相关连接 */
        UNFOUND_ERROR,
        /** 连接超时 */
        TIMEOUT_ERROR,
        /** 未知错误 */
        UNKNOWN_ERROR
    }
}
