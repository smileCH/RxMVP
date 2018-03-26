package com.smile.ch.rxmvp;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smile.ch.rxmvp.adapter.MzImagsAdapter;
import com.smile.ch.rxmvp.base.BaseBean;
import com.smile.ch.rxmvp.base.BaseMvpActivity;
import com.smile.ch.rxmvp.bean.MeizhiBean;
import com.smile.ch.rxmvp.contract.MeizhiContract;
import com.smile.ch.rxmvp.presenter.MZPresenterImpl;

import java.util.List;

public class MainActivity extends BaseMvpActivity<MeizhiContract.MZView, MZPresenterImpl> implements MeizhiContract.MZView{

    private RecyclerView recyclerView;
    private MzImagsAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MZPresenterImpl createPresenter() {
        return new MZPresenterImpl();
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.mz_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MzImagsAdapter(context, null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void getDatas() {
        getPresenter().requestMZDatas(context, "福利", 10, 1);
    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void onSuccessInfo(BaseBean<List<MeizhiBean>> response) {
        adapter.refreshDatas(response.getResults());
    }

    @Override
    public void onFaile(BaseBean<List<MeizhiBean>> response) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消请求
        getPresenter().cancleHttpRequest();
    }
}
