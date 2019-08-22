package com.example.otademo.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import java.util.Timer;
import java.util.TimerTask;

public class ProgressDialogView {
    private static ProgressDialog progressDialog;
    private static Timer timer;
    private static int seconds = 20;

    private ProgressDialogView() {
    }

    public static void show(Context context, String title, String message) {
        show(context, title, message, false);
    }

    public static void show(Context context, String title, String message, boolean autoDissmiss) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(0);
        progressDialog.setProgressDrawable(new BitmapDrawable());
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            if (autoDissmiss) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        if (ProgressDialogView.progressDialog != null && ProgressDialogView.progressDialog.isShowing()) {
                            ProgressDialogView.progressDialog.dismiss();
                            ProgressDialogView.setSeconds(20);
                        }

                    }
                }, (long)(seconds * 1000));
            }
        }

    }

    public static void updateMessage(String message) {
        if (progressDialog != null) {
            progressDialog.setMessage(message);
        }

    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            setSeconds(20);
            if (timer != null) {
                timer.cancel();
            }
        }

    }

    public static void setSeconds(int seconds) {
        seconds = seconds;
    }
}