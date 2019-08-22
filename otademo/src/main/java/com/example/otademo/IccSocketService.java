package com.example.otademo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.update.icc.api.ICCInstallManager;
import com.example.update.icc.bean.ICCNewPagUpdateBean;
import com.example.update.icc.interfaces.ICCReplyCallback;


public class IccSocketService extends Service implements ICCReplyCallback {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                if(progress<=90){
                    progress+=10;
                }else{
                    handler.removeCallbacks(runnable);
                }

            }else if(msg.what==0x456){
                if(install<=90){
                    install+=10;
                }else{
                    handler.removeCallbacks(runnable2);
                }
            }

        }
    };
    int progress=0;
    int install=0;
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0x123);
            handler.postDelayed(runnable,2000);
        }
    };

    Runnable runnable2=new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0x456);
            handler.postDelayed(runnable2,2000);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        ICCInstallManager.setIIccCallBack(this);
        ICCInstallManager.statIccSocket();
    }



    @Override
    public void onDestroy() {
        ICCInstallManager.closeIccSocket();
    }


    @Override
    public void onIccUpgradePagage(ICCNewPagUpdateBean bean) {
        handler.postDelayed(runnable,1000);

    }

    @Override
    public void onIccDownloadProgress() {
        ICCInstallManager.sendDownloadProgress(progress,100);

    }

    @Override
    public void onIccInstallResult() {
        handler.postDelayed(runnable2,1000);
    }

    @Override
    public void onIccInstallProgress() {
        ICCInstallManager.sendInstallProgress(install,100);
    }

    @Override
    public void onVersionRollback() {
        ICCInstallManager.setVersionRollback(true);
    }

    @Override
    public void onUpdateResult() {
        progress=0;
        install=0;
        ICCInstallManager.updateResult(true);

    }
}
