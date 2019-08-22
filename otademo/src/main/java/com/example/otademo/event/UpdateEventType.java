package com.example.otademo.event;

/**
 * Created by zs on 2018/11/9.
 */

public enum UpdateEventType {
    TBOX2HMI_DATA_MESSAGE,
    /**
     * TBOX2HMI_DATA_MESSAGE_UPDATE  检测到新的OTA包
     */
    TBOX2HMI_DATA_MESSAGE_UPDATE,


    TBOX2HMI_DATA_MESSAGE_QUERY,
    /*
     * HMI2TBOX_ICC_DL_TYPE_REQ ICC下载网络类型选择。
     * */
    HMI2TBOX_ICC_DL_TYPE_REQ,
    /**
     * TBOX2HMI_DATA_MESSAGE_PROGRESS  下载更新进度
     */
    TBOX2HMI_DATA_MESSAGE_PROGRESS,
    /**
     * TBOX2HMI_DOWNLOAD_RESULT  下载结果确认
     */
    TBOX2HMI_DOWNLOAD_RESULT,

    /**
     * TBOX2HMI_INSTALL_PROGRESS  安装更新进度
     */
    TBOX2HMI_INSTALL_PROGRESS,
    /**
     * TBOX2HMI_INSTALL_CONFIRM  安装提醒
     */
    TBOX2HMI_INSTALL_CONFIRM,

    /**
     * TBOX2HMI_INSTALL_RESULT  安装结果
     */
    TBOX2HMI_INSTALL_RESULT,

    /**
     * TBOX2HMI_INSTALL_RESULT  取消弹出窗口
     */
    TBOX2HMI_CANCLE_POPWINDOWS


}
