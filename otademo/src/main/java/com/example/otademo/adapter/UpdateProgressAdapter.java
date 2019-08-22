package com.example.otademo.adapter;

import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.otademo.R;
import com.example.update.hmi.bean.ProgressBean;

import java.util.List;


public class UpdateProgressAdapter extends BaseQuickAdapter<ProgressBean.ArrayBean, BaseViewHolder> {

    public UpdateProgressAdapter(int layoutResId, @Nullable List<ProgressBean.ArrayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProgressBean.ArrayBean item) {
        ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progress);
        helper.setText(R.id.title, (item.getProgress() + "%"));
        progressBar.setProgress(item.getProgress());
    }


}
