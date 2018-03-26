package com.smile.ch.rxmvp.contract;

import android.content.Context;

import com.smile.ch.rxmvp.base.BaseBean;
import com.smile.ch.rxmvp.base.BaseView;
import com.smile.ch.rxmvp.bean.MeizhiBean;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：契约类
 */

public class MeizhiContract {
    public interface MZView extends BaseView{
        void onSuccessInfo(BaseBean<List<MeizhiBean>> response);//200的情况
        void onFaile(BaseBean<List<MeizhiBean>> response);//非200的情况，比如302、301
    }

    public interface IMzModel{
        //请求数据，回调
        void getMZDatas(Context context, String type, int count, int page, IMzModelCallback callback);
        //取消请求
        void cancleHttpRequest();
    }

    public interface IMzModelCallback{
        void onSuccess(BaseBean<List<MeizhiBean>> response);
        void onFaile(BaseBean<List<MeizhiBean>> response);
    }
}
