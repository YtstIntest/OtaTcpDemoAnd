package com.example.otademo.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.otademo.HmiSocketService;
import com.example.otademo.IccSocketService;
import com.example.otademo.JApp.App;
import com.example.otademo.R;
import com.example.otademo.event.UpdateTagEvent;
import com.example.otademo.view.AlertDialogView;
import com.example.otademo.view.ChooseOptionCallBack;
import com.example.update.hmi.api.HmiInstallManager;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity  {

    @BindView(R.id.settingRel)
    RelativeLayout settingRel;
    @BindView(R.id.imageMusic)
    ImageView imageMusic;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle("检查到更新").setMessage("有新的版本更新，如需升级请点击确认。").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, UpdateActivity.class).putExtra("type", 2));
                }
            }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Intent intent1 = new Intent(this, IccSocketService.class);
        startService(intent1);
        Intent intent = new Intent(this, HmiSocketService.class);
        startService(intent);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.musicRel, R.id.settingRel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.musicRel:
                HmiInstallManager.canleUpdate();
                break;
            case R.id.settingRel:
                startActivity(new Intent(MainActivity.this, UpdateActivity.class));
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRegionEvent(UpdateTagEvent event) {
        switch (event.eventType) {
            case TBOX2HMI_DATA_MESSAGE:
                AlertDialogView.getInstance(this).show("检查到更新", "有新的版本更新，如需升级请点击确认！！！", "确认", null, new ChooseOptionCallBack() {
                    @Override
                    public void chooseOption(int var1) {
                        switch (var1) {
                            case 1:
                                MainActivity.this.startActivity(new Intent(MainActivity.this, UpdateActivity.class).putExtra("type", App.queryUpgradeTypeBean.getType()));
                                break;
                        }
                    }
                });
                break;
        }

    }
}
