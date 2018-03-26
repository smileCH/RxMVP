package com.smile.ch.rxmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smile.ch.rxmvp.R;
import com.smile.ch.rxmvp.bean.MeizhiBean;

import java.util.List;

/**
 * Author：CHENHAO
 * date：2018/3/25
 * desc：妹纸图适配器
 */

public class MzImagsAdapter extends RecyclerView.Adapter<MzImagsAdapter.MzImgsViewHolder>{

    private Context context;
    private List<MeizhiBean> lists;
    private LayoutInflater inflater;

    public MzImagsAdapter(Context context, List<MeizhiBean> lists) {
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
    }

    public void refreshDatas(List<MeizhiBean> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public MzImgsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_meizhi_rv_layout, parent, false);
        MzImgsViewHolder holder = new MzImgsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MzImgsViewHolder holder, int position) {
        MeizhiBean bean = lists.get(position);
        holder.item_mz_tv.setText(bean.getDesc());
        Glide.with(context)
                .load(bean.getUrl())
                .into(holder.item_mz_iv);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }

    public class MzImgsViewHolder extends RecyclerView.ViewHolder{

        ImageView item_mz_iv;
        TextView item_mz_tv;
        public MzImgsViewHolder(View itemView) {
            super(itemView);
            item_mz_iv = itemView.findViewById(R.id.item_mz_iv);
            item_mz_tv = itemView.findViewById(R.id.item_mz_tv);
        }
    }
}
