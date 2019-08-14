package com.example.update.communication.socket;

import android.util.Log;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommServerSocket extends Thread {
    private int port;
    private String name;
    private ServerSocket serverSocket = null;
    private com.example.update.communication.interfaces.onSendDataListener onSendDataListener;
    private boolean started = false;
    private ExecutorService exec = Executors.newCachedThreadPool();


    private Socket socket;
    private InputStream inp = null;
    private OutputStream ots = null;
    private InputStreamReader isr = null;
    private PrintWriter pw = null;
    private BufferedReader bfr = null;

    public CommServerSocket(int port, String name, com.example.update.communication.interfaces.onSendDataListener onSendDataListener) {
        this.port = port;
        this.name = name;
        this.onSendDataListener = onSendDataListener;
    }



    public void sendDateMsg(final String msg) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pw.println(msg);
                    pw.flush();
                } catch (Exception e) {
                    Log.e("ThreadLog", "客户端还未接入"+e.fillInStackTrace());
                }
            }
        });


    }


    @Override
    public void run() {
        try {
            onSendDataListener.setSocket(this);
            serverSocket = new ServerSocket(port);
            started = true;
            //调用accept()方法开始监听，等待客户端的连接
            System.out.println("**********" + name + "服务器即将启动，等待客户端的连接*************");
            while (started) {
                socket = serverSocket.accept();
                ClienRunnable clienRunnable = new ClienRunnable(socket);
                new Thread(clienRunnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClosed() {
        try {
            //关闭资源
            pw.close();
            ots.close();
            bfr.close();
            isr.close();
            inp.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class ClienRunnable implements Runnable {


        private ClienRunnable(Socket socket) {
            //获取输入流，并读取客户端信息
            try {
                inp = socket.getInputStream();
                ots = socket.getOutputStream();
                //把字节流转换成字符流
                isr = new InputStreamReader(inp);
                pw = new PrintWriter(ots);
                //为字符流增加缓冲区
                bfr = new BufferedReader(isr);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client closed!");
            }

        }




        @Override
        public void run() {
            try {
                char[] chs = new char[1024];
                int len = 0;
                while ((len = bfr.read(chs)) != -1) {
                    Log.e(name+"：",new String(chs, 0, len));
                    onSendDataListener.onIDataMessageCallbck(new String(chs, 0, len));

                }
            } catch (EOFException e) {
                System.out.println("Client closed!");
            } catch (IOException e) {
                System.out.println("Client closed!");
            } finally {
                onClosed();
            }
        }
    }


}
