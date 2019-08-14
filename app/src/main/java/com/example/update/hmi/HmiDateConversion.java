package com.example.update.hmi;

import com.example.update.communication.socket.CommServerSocket;
import com.example.update.hmi.bean.QueryUpgradeBean;
import com.example.update.hmi.bean.QueryUpgradeTypeBean;
import com.example.update.hmi.interfaces.HmiUtils;
import com.example.update.icc.bean.ICCNewPagUpdateBean;
import com.example.update.communication.interfaces.onSendDataListener;
import com.example.update.hmi.bean.DownloadCompleteBean;
import com.example.update.hmi.bean.DownloadResultBean;
import com.example.update.hmi.bean.InstallCompleteBean;
import com.example.update.hmi.bean.ProgressBean;
import com.example.update.hmi.bean.UpgradePagageBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class HmiDateConversion implements onSendDataListener {
    private Gson gson = new Gson();
    private CommServerSocket hmiSocket;
    private boolean isConnect = false;



    public HmiDateConversion() {
    }

    @Override
    public void onIDataMessageCallbck(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int event = jsonObject.getInt("event");
            switch (event) {
                case 10: //  TBOX2HMI_READY TBOX启动后，发送此通知表示进入就绪状态。
                    isConnect = true;
                    break;

                case 12://  TBOX2HMI_DOWNLOAD_PROGRESS 下载进度通知，可能包含多种设备的下载进度。
                    HmiUtils.onHmiDownloadProgress(gson.fromJson(msg, ProgressBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1012)));
                    break;
                case 14://  TBOX2HMI_INSTALL_PROGRESS  安装进度通知。
                    HmiUtils.onHmiInstallProgress(gson.fromJson(msg, ProgressBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1014)));
                    break;

                case 17://确认下载。
                    HmiUtils.onHmiUpgradePagage(gson.fromJson(msg, UpgradePagageBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1017)));

                    break;
                case 19://TBOX2HMI_INSTALL_CONFIRM  安装确认
                    HmiUtils.onHmiDownloadComplete(gson.fromJson(msg, DownloadCompleteBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1019)));
                    break;


                case 20://  TBOX2HMI_NOTIFY_OTA  检测到新的OTA包
                    HmiUtils.onQueryUpgradeType(gson.fromJson(msg, QueryUpgradeTypeBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1020)));
                    break;
//
                case 21://  TBOX2HMI_REBOOT_NOTIFY  重启请求
//                    DownloadResultBean reBootResultBean = new DownloadResultBean(1021, 0);
//                    sendMsg(HMIrSocket, new Gson().toJson(reBootResultBean))
                    break;

                case 23: //安装结果
                    HmiUtils.onHmiInstallComplete(gson.fromJson(msg, InstallCompleteBean.class));
                    hmiSocket.sendDateMsg(gson.toJson(new DownloadResultBean(1023, 0,"1234")));
                    break;
                case 26://连接类型
                    HmiUtils.onNetworkSelecter();
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1026)));
                    break;

                case 27:
                    HmiUtils.onSubscribeCallback();
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1027)));
                    break;

                case 28://取消弹窗
                    HmiUtils.onHmiCanclePopWindow();
                    hmiSocket.sendDateMsg(gson.toJson(new ICCNewPagUpdateBean(1028)));
                    break;
                case 4100://查询升级回包
                    HmiUtils.onHmiQueryUpgrade(gson.fromJson(msg, QueryUpgradeBean.class));
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSocket(CommServerSocket socket) {
        hmiSocket=socket;
    }

    public<T> void sendDate(T bean){
        hmiSocket.sendDateMsg(gson.toJson(bean));
    }


    public boolean isConnect() {
        return isConnect;
    }

    public void onClose(){
        hmiSocket.onClosed();
    }



}
