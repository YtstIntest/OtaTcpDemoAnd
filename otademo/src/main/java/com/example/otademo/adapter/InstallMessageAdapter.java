package com.example.otademo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.otademo.R;
import com.example.update.hmi.bean.ArrayBean;

import java.util.List;

public class InstallMessageAdapter extends BaseQuickAdapter<ArrayBean, BaseViewHolder> {


    public InstallMessageAdapter(int layoutResId, @Nullable List<ArrayBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ArrayBean item) {
        helper.setText(R.id.title, item.getDevice() + "更新内容：（版本" + item.getVersion() + "  大小：" + item.getSize() + "B）")
                .setText(R.id.msg, item.getDescription());
    }

}

