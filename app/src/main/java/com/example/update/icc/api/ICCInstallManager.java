package com.example.update.icc.api;


import com.example.update.communication.socket.CommServerSocket;
import com.example.update.icc.IccDateConversion;
import com.example.update.icc.bean.ICCUpdateResBean;
import com.example.update.icc.bean.ProgressBean;
import com.example.update.icc.interfaces.ICCReplyCallback;
import com.example.update.until.IccSetting;
import com.google.gson.Gson;

public class ICCInstallManager {
    private static Gson gson = new Gson();
    private static IccDateConversion iccDateConversion = new IccDateConversion();
    private static CommServerSocket InstallerSocket = new CommServerSocket(IccSetting.ICC_PORT, IccSetting.ICC_TAG, iccDateConversion);


    /**
     * @param iIccCallBack icc回调接口
     * @desc 设置监听回调
     * @author XL
     * @create_time 2019/4/19
     */
    public static void setIIccCallBack(ICCReplyCallback iIccCallBack) {
        iccDateConversion.setIccReplyCallback(iIccCallBack);
    }



    /**
     * @desc 开启ICC服务
     * @author XL
     * @create_time 2019/4/19
     */
    public static void statIccSocket() {
        InstallerSocket.start();
    }

    /**
     * @desc 关闭ICC服务
     * @author XL
     * @create_time 2019/4/19
     */
    public static void closeIccSocket() {
        InstallerSocket.onClosed();
    }


    /**
     * @param handled 已下载大小
     * @param max     总大小
     * @desc 设置下载进度
     * @author XL
     * @create_time 2019/4/19
     */
    public static void sendDownloadProgress(int handled, int max) {
        InstallerSocket.sendDateMsg(gson.toJson(new ProgressBean(1004, handled, max, 0)));
    }

    /**
     * @param handled 已安装大小
     * @param max     总安装大小
     * @desc 设置安装进度
     * @author XL
     * @create_time 2019/4/19
     */
    public static void sendInstallProgress(int handled, int max) {
        InstallerSocket.sendDateMsg(gson.toJson(new ProgressBean(1008, handled, max, 0)));
    }

    /**
     * @param isRollback 回滚状态
     * @desc 设置版本回滚
     * @author XL
     * @create_time 2019/4/19
     */
    public static void setVersionRollback(boolean isRollback) {
        InstallerSocket.sendDateMsg(gson.toJson(new ICCUpdateResBean(1011, isRollback)));
    }

    /**
     * @param isResult 结果状态
     * @desc 设置升级结果
     * @author XL
     * @create_time 2019/4/19
     */
    public static void updateResult(boolean isResult) {
        InstallerSocket.sendDateMsg(gson.toJson(new ICCUpdateResBean(1012, isResult)));
    }


}
