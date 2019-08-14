package com.example.update.icc.interfaces;

import com.example.update.icc.bean.ICCNewPagUpdateBean;

public interface ICCReplyCallback {
    /**
     * ICC安装包下载请求
     */
    void onIccUpgradePagage(ICCNewPagUpdateBean bean);

    /**
     * ICC下载进度确认
     * */
    void onIccDownloadProgress();


    /**
     * ICC安装请求
     */
    void onIccInstallResult();

    /**
     * ICC安装进度确认
     * */
    void onIccInstallProgress();

    /**
     * ICC版本回滚
     * */
    void onVersionRollback();

    /**
     * ICC升级结果回调接口
     * */
    void onUpdateResult();

}
