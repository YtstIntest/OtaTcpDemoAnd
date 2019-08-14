package com.example.update.communication.interfaces;

import com.example.update.communication.socket.CommServerSocket;

public interface onSendDataListener {
    void onIDataMessageCallbck(String msg);
    void setSocket(CommServerSocket socket);


}
