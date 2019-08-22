package com.example.otademo.view;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.otademo.R;

public class AlertDialogView implements OnClickListener {
    private AlertDialog dialog;
    private Context context;
    private static AlertDialogView dialogView;
    private ChooseOptionCallBack optionCallBack;

    private AlertDialogView(Context context) {
        this.context = context;
    }

    public static AlertDialogView getInstance(Context context) {
        if (dialogView == null) {
            dialogView = new AlertDialogView(context);
        }

        return dialogView;
    }

    public void show(String tilte, String msg, String sureTip, String cancleTip, ChooseOptionCallBack callBack) {
        this.optionCallBack = callBack;
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        Builder builder = new Builder(this.context);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.layout_alert_dialog, (ViewGroup)null);
        TextView titleText = (TextView)view.findViewById(R.id.title);
        TextView messageText = (TextView)view.findViewById(R.id.message);
        titleText.setText(tilte);
        messageText.setText(msg);
        builder.setCancelable(false);
        Button cancleButton = (Button)view.findViewById(R.id.cancleButton);
        cancleButton.setText(cancleTip);
        if (TextUtils.isEmpty(cancleTip)) {
            cancleButton.setVisibility(View.GONE);
        }

        cancleButton.setOnClickListener(this);
        Button sureButton = (Button)view.findViewById(R.id.sureButton);
        sureButton.setText(sureTip);
        sureButton.setOnClickListener(this);
        builder.setView(view);
        this.dialog = builder.create();
        this.dialog.show();
    }

    public boolean isShowing() {
        return this.dialog != null ? this.dialog.isShowing() : false;
    }

    public void dismiss() {
        if (this.dialog != null) {
            this.dialog.dismiss();
        }

    }

    public static void dismiss_() {
        if (dialogView != null && dialogView.dialog != null) {
            dialogView.dialog.dismiss();
        }

    }

    public void onClick(View v) {
        if (v.getId() == R.id.cancleButton) {
            if (this.optionCallBack != null) {
                this.optionCallBack.chooseOption(0);
            }

            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
                this.dialog = null;
            }
        } else if (v.getId() == R.id.sureButton) {
            if (this.optionCallBack != null) {
                this.optionCallBack.chooseOption(1);
            }

            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
                this.dialog = null;
            }
        }

    }
}
