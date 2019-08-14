package com.example.update.icc;


import com.example.update.communication.socket.CommServerSocket;
import com.example.update.icc.bean.ICCNewPagUpdateBean;
import com.example.update.communication.interfaces.onSendDataListener;
import com.example.update.icc.interfaces.ICCReplyCallback;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class IccDateConversion implements onSendDataListener {
    private Gson gson = new Gson();
    private CommServerSocket socket;

    private ICCReplyCallback iccReplyCallback;


    public IccDateConversion() {
    }

    @Override
    public void onIDataMessageCallbck(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int event = jsonObject.getInt("event");
            switch (event) {
                case 3:  //   TBOX2ICC_DOWNLOAD_REQUEST  安装包下载请求
                    iccReplyCallback.onIccUpgradePagage(gson.fromJson(msg, ICCNewPagUpdateBean.class));
                    socket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1003)));

                    break;
                case 4:  //  TBOX2ICC_DLD_PROGRESS_REQUEST 下载进度
                    iccReplyCallback.onIccDownloadProgress();
                    break;
                case 7:// TBOX2ICC_PREINSTALL_REQUEST  安装请求命令
                    iccReplyCallback.onIccInstallResult();
                    socket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1007)));
                    break;
                case 8://TBOX2ICC_INSTALL_REQUEST  安装进度请求
                    iccReplyCallback.onIccInstallProgress();
                    break;
                case 9://TBOX2ICC_REBOOT_REQUEST  复位请求
                    socket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1009)));
                    break;
                case 10://TBOX2ICC_CANCEL _REQUEST  取消请求
                    socket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1010)));
                    break;
                case 11:// TBOX2ICC_ROLLBACK_REQUEST 版本回滚请求
                    iccReplyCallback.onVersionRollback();
                    break;
                case 12://TBOX2ICC_UPGRADE_RESLUT_REQUEST ICC升级结果查询
                    iccReplyCallback.onUpdateResult();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSocket(CommServerSocket socket) {
        this.socket = socket;
    }

    public void setIccReplyCallback(ICCReplyCallback iccReplyCallback) {
        this.iccReplyCallback = iccReplyCallback;
    }

}
