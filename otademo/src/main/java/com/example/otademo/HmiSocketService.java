package com.example.otademo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.otademo.JApp.App;
import com.example.otademo.event.UpdateEventType;
import com.example.otademo.event.UpdateTagEvent;
import com.example.update.hmi.api.HmiInstallManager;
import com.example.update.hmi.bean.DownloadCompleteBean;
import com.example.update.hmi.bean.InstallCompleteBean;
import com.example.update.hmi.bean.ProgressBean;
import com.example.update.hmi.bean.QueryUpgradeBean;
import com.example.update.hmi.bean.QueryUpgradeTypeBean;
import com.example.update.hmi.bean.UpgradePagageBean;
import com.example.update.hmi.interfaces.HmiReplyCallback;

import org.greenrobot.eventbus.EventBus;


public class HmiSocketService extends Service implements HmiReplyCallback {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        HmiInstallManager.starHmiServer();
        HmiInstallManager.setHmiReplyCallback(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HmiInstallManager.closeHmiServer();
    }

    @Override
    public void onQueryUpgradeType(QueryUpgradeTypeBean bean) {
        App.queryUpgradeTypeBean = bean;
        switch (bean.getTriggerMode()) {
            case 0:
                EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_DATA_MESSAGE));
                break;
        }
    }

    @Override
    public void onHmiQueryUpgrade(QueryUpgradeBean bean) {
        App.queryUpgradeBean = bean;
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_DATA_MESSAGE_QUERY));

    }

    @Override
    public void onHmiUpgradePagage(UpgradePagageBean bean) {
        App.upgradePagageBean = bean;
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_DATA_MESSAGE_UPDATE));
    }

    @Override
    public void onNetworkSelecter() {
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.HMI2TBOX_ICC_DL_TYPE_REQ));
    }

    @Override
    public void onHmiDownloadProgress(ProgressBean bean) {
        if (bean.getErrno() == 0) {
            App.progressBean = bean;
            EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_DATA_MESSAGE_PROGRESS));
        }
    }

    @Override
    public void onHmiDownloadComplete(DownloadCompleteBean bean) {
        App.installBean = bean;
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_DOWNLOAD_RESULT));
    }

    @Override
    public void onSubscribeCallback() {
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_INSTALL_CONFIRM));
    }

    @Override
    public void onHmiInstallProgress(ProgressBean bean) {
        App.progressBean = bean;
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_INSTALL_PROGRESS));
    }

    @Override
    public void onHmiInstallComplete(InstallCompleteBean bean) {
        App.installCompleteBean = bean;
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_INSTALL_RESULT));
    }

    @Override
    public void onHmiCanclePopWindow() {
        EventBus.getDefault().post(new UpdateTagEvent(UpdateEventType.TBOX2HMI_CANCLE_POPWINDOWS));

    }
}
