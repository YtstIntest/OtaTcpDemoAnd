package com.example.update.hmi.interfaces;

import com.example.update.hmi.bean.DownloadCompleteBean;
import com.example.update.hmi.bean.InstallCompleteBean;
import com.example.update.hmi.bean.ProgressBean;
import com.example.update.hmi.bean.QueryUpgradeBean;
import com.example.update.hmi.bean.QueryUpgradeTypeBean;
import com.example.update.hmi.bean.UpgradePagageBean;

public interface HmiReplyCallback {

    /**
     * 查询升级类型
     */
    void onQueryUpgradeType(QueryUpgradeTypeBean bean);

    /**
     * 查询升级状态
     */
    void onHmiQueryUpgrade(QueryUpgradeBean bean);

    /**
     * 升级包回调
     */
    void onHmiUpgradePagage(UpgradePagageBean bean);

    /**
     * 下载网络选择
     */
    void onNetworkSelecter();

    /**
     * 下载进度回调
     */
    void onHmiDownloadProgress(ProgressBean bean);

    /**
     * 下载完成回调
     */
    void onHmiDownloadComplete(DownloadCompleteBean bean);

    /**
     * 安装进度回调
     */
    void onSubscribeCallback();

    /**
     * 安装进度回调
     */
    void onHmiInstallProgress(ProgressBean bean);

    /**
     * 安装结果回调
     */
    void onHmiInstallComplete(InstallCompleteBean bean);

    /**
     * 取消弹出窗口
     */
    void onHmiCanclePopWindow();
}
