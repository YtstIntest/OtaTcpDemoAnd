package com.example.update.hmi.api;


import com.example.update.communication.socket.CommServerSocket;
import com.example.update.hmi.HmiDateConversion;
import com.example.update.hmi.interfaces.HmiUtils;
import com.example.update.icc.bean.ICCNewPagUpdateBean;
import com.example.update.hmi.bean.DownloadResultBean;
import com.example.update.hmi.bean.SelectUpgradeModeBean;
import com.example.update.hmi.interfaces.HmiReplyCallback;
import com.example.update.until.HmiSetting;


public class HmiInstallManager  {

    private static HmiDateConversion hmiDateConversion = new HmiDateConversion();
    private static CommServerSocket HMIrSocket = new CommServerSocket(HmiSetting.HMI_PORT, HmiSetting.HMI_TAG, hmiDateConversion);


    public static void setHmiReplyCallback(HmiReplyCallback callback) {
        HmiUtils.setHmiReplyCallback(callback);
    }


    /**
     * @desc 判断当前hmi是否连接
     * @author XL
     * @create_time 2019/4/15
     */
    public static boolean isConnect() {
        return hmiDateConversion.isConnect();
    }


    /**
     * @desc 查询是否有hmi升级
     * @author XL
     * @create_time 2019/4/15
     */
    public static void queryUpgrade( ) {
        hmiDateConversion.sendDate(new ICCNewPagUpdateBean(100));
    }

    /**
     * @desc 选择升级模式
     * @author XL
     * @create_time 2019/4/15
     */
    public static void selectUpgradeMode(int mode, String scheduleTime) {
        hmiDateConversion.sendDate(new SelectUpgradeModeBean(106, mode, scheduleTime));
    }

    /**
     * @desc 选择下载网络模式
     * @author XL
     * @create_time 2019/4/15
     */
    public static void selectNetworkMode(int mode) {
        hmiDateConversion.sendDate(new DownloadResultBean(110, mode));
    }


    /**
     * @desc 选择下载网络模式
     * @author XL
     * @create_time 2019/4/15
     */
    public static void canleUpdate() {
        hmiDateConversion.sendDate(new ICCNewPagUpdateBean(104));
    }

    /**
     * @desc 选择安装模式
     * @author XL
     * @create_time 2019/4/15
     */
    public static void selectInstallMode(int mode, String scheduleTime) {
        hmiDateConversion.sendDate(new SelectUpgradeModeBean(107, mode, scheduleTime));
    }

    /**
     * @desc 确认安装请求
     * @author XL
     * @create_time 2019/4/18
     */
    public static void confirmInstall(int mode, String scheduleTime) {
        hmiDateConversion.sendDate(new DownloadResultBean(109, mode, scheduleTime));
    }


    /**
     * @desc 开启hmi服务
     * @author XL
     * @create_time 2019/4/15
     */
    public static void starHmiServer() {

        HMIrSocket.start();
    }

    /**
     * @desc 关闭hmi服务
     * @author XL
     * @create_time 2019/4/15
     */
    public static void closeHmiServer() {
        hmiDateConversion.onClose();
    }
}
