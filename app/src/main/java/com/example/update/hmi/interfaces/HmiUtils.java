package com.example.update.hmi.interfaces;

import com.example.update.hmi.bean.DownloadCompleteBean;
import com.example.update.hmi.bean.InstallCompleteBean;
import com.example.update.hmi.bean.ProgressBean;
import com.example.update.hmi.bean.QueryUpgradeBean;
import com.example.update.hmi.bean.QueryUpgradeTypeBean;
import com.example.update.hmi.bean.UpgradePagageBean;

public class HmiUtils {


    public static HmiReplyCallback hmiReplyCallback;


    public static void setHmiReplyCallback(HmiReplyCallback hmiReplyCallback) {
        HmiUtils.hmiReplyCallback = hmiReplyCallback;
    }


    public static void onQueryUpgradeType(QueryUpgradeTypeBean bean) {
        hmiReplyCallback.onQueryUpgradeType(bean);
    }

    public static void onHmiQueryUpgrade(QueryUpgradeBean bean) {
        hmiReplyCallback.onHmiQueryUpgrade(bean);
    }

    public static void onHmiUpgradePagage(UpgradePagageBean bean) {
        hmiReplyCallback.onHmiUpgradePagage(bean);
    }

    public static void onNetworkSelecter() {
        hmiReplyCallback.onNetworkSelecter();
    }


    public static void onHmiDownloadProgress(ProgressBean bean) {
        hmiReplyCallback.onHmiDownloadProgress(bean);
    }


    public static void onHmiDownloadComplete(DownloadCompleteBean bean) {
        hmiReplyCallback.onHmiDownloadComplete(bean);
    }

    public static void onSubscribeCallback() {
        hmiReplyCallback.onSubscribeCallback();
    }

    public static void onHmiInstallProgress(ProgressBean bean) {
        hmiReplyCallback.onHmiInstallProgress(bean);
    }

    public static void onHmiInstallComplete(InstallCompleteBean bean) {
        hmiReplyCallback.onHmiInstallComplete(bean);
    }

    public static void onHmiCanclePopWindow( ) {
        hmiReplyCallback.onHmiCanclePopWindow();
    }
}
