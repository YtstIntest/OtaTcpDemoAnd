package com.example.otademo.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.otademo.JApp.App;
import com.example.otademo.R;
import com.example.otademo.adapter.InstallMessageAdapter;
import com.example.otademo.adapter.UpdateMessageAdapter;
import com.example.otademo.event.UpdateTagEvent;
import com.example.otademo.view.AlertDialogView;
import com.example.otademo.view.ProgressDialogView;
import com.example.update.hmi.api.HmiInstallManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateActivity extends Activity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.recyclerOne)
    RecyclerView recyclerOne;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.cancleBtn)
    Button cancleBtn;
    @BindView(R.id.delayBtn)
    Button delayBtn;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.progressTv)
    TextView progressTv;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.msgIco)
    ImageView msgIco;
    @BindView(R.id.msgTv)
    TextView msgTv;
    @BindView(R.id.progressMsgTv)
    TextView progressMsgTv;
    @BindView(R.id.updateBtn)
    Button updateBtn;
    @BindView(R.id.cancleButn)
    Button cancleButn;
    @BindView(R.id.zero)
    LinearLayout zero;


    @BindView(R.id.cancleUpdateButn)
    Button cancleUpdateButn;

    private int mode = 0;

    private String min = "";
    private String hour = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        switch (getIntent().getIntExtra("type", -1)) {
            case 0:
                showUpdateView();
                break;
            case 1:
                showInstallView();
                break;
            default:
                showMainView();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void showMainView() {
        title.setText("系统更新");
        zero.setVisibility(View.VISIBLE);
        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        three.setVisibility(View.GONE);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HmiInstallManager.isConnect()) {
                    ProgressDialogView.show(UpdateActivity.this, "升级文件查询中", "升级文件查询中,请稍后...", false);
                    HmiInstallManager.queryUpgrade();

                } else {
                    Toast.makeText(UpdateActivity.this, "车机未连接，请等待！！！", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancleButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showUpdateView() {
        title.setText("检查到最新更新");
        zero.setVisibility(View.GONE);
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.GONE);
        three.setVisibility(View.GONE);
        delayBtn.setVisibility(View.GONE);
        startBtn.setText("立即下载");
        cancleBtn.setText("取消");
        cancleBtn.setVisibility(View.VISIBLE);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialogView.show(UpdateActivity.this, "升级文件准备中", "升级文件准备中,请稍后...", true);
                HmiInstallManager.selectUpgradeMode(1, "0509");
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmiInstallManager.selectUpgradeMode(2, "0509");
                showMainView();
            }
        });

        recyclerOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerOne.setItemAnimator(new DefaultItemAnimator());
        UpdateMessageAdapter adapter = new UpdateMessageAdapter(R.layout.update_message_item, null);
        adapter.addData(App.upgradePagageBean.getArray());
        recyclerOne.setAdapter(adapter);
    }

    private AlertDialog dialog = null;

    public void showInstallView() {
        title.setText("系统安装");
        two.setVisibility(View.GONE);
        one.setVisibility(View.VISIBLE);
        three.setVisibility(View.GONE);
        zero.setVisibility(View.GONE);
        delayBtn.setVisibility(View.VISIBLE);
        cancleBtn.setText("稍后提醒");
        startBtn.setText("立即安装");
        cancleBtn.setVisibility(View.GONE);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmiInstallManager.selectInstallMode(1, "000000000509");
                ProgressDialogView.show(UpdateActivity.this, "安装环境检查中", "安装环境检查中,请稍后...");

            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmiInstallManager.selectInstallMode(2, "000000000509");
                showMainView();
            }
        });
        delayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new AlertDialog.Builder(UpdateActivity.this).setCancelable(false).setTitle("预约时间").setMessage("请选择！！！").setPositiveButton("修改预约时间", null).setNegativeButton("立即升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HmiInstallManager.selectInstallMode(1, "000000000509");
                        ProgressDialogView.show(UpdateActivity.this, "安装环境检查中", "安装环境检查中,请稍后...");
                    }
                }).create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (minute < 10) {
                                    min = "0" + minute;
                                } else {
                                    min = minute + "";

                                }
                                if (hourOfDay < 10) {
                                    hour = "0" + hourOfDay;
                                } else {
                                    hour = hourOfDay + "";
                                }
                                HmiInstallManager.selectInstallMode(3, "00000000" + hour + min);
                            }
                        }, 0, 0, true).show();

                    }
                });


            }
        });
        recyclerOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerOne.setItemAnimator(new DefaultItemAnimator());
        InstallMessageAdapter adapter = new InstallMessageAdapter(R.layout.update_message_item, null);
        adapter.addData(App.installBean.getArray());
        recyclerOne.setAdapter(adapter);
    }

    public void showInstallViewSecond() {
        two.setVisibility(View.GONE);
        one.setVisibility(View.VISIBLE);
        three.setVisibility(View.GONE);
        zero.setVisibility(View.GONE);
        delayBtn.setVisibility(View.GONE);
        title.setText("系统安装确认");
        cancleBtn.setText("取消");
        startBtn.setText("确认安装");
        cancleBtn.setText("返回上一级");
        cancleBtn.setVisibility(View.VISIBLE);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == 1) {
                    new AlertDialog.Builder(UpdateActivity.this).setTitle("预约成功").setMessage("您预约成功！！！！").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showMainView();
                        }
                    }).show();
                    mode = 0;
                } else {
                    ProgressDialogView.show(UpdateActivity.this, "安装环境检查中", "安装环境检查中,请稍后...");
                }
                HmiInstallManager.confirmInstall(1, "000000000509");
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmiInstallManager.confirmInstall(2, "000000000509");
                showInstallView();
            }
        });

        recyclerOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerOne.setItemAnimator(new DefaultItemAnimator());
        InstallMessageAdapter adapter = new InstallMessageAdapter(R.layout.update_message_item, null);
        adapter.addData(App.installBean.getArray());
        recyclerOne.setAdapter(adapter);
    }


    public void showProgressView(int type) {
        zero.setVisibility(View.GONE);
        one.setVisibility(View.GONE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.GONE);
        progress.setProgress(0);
        if (type == 1) {
            title.setText("更新下载");
            cancleUpdateButn.setVisibility(View.VISIBLE);
            progressMsgTv.setText("正在下载中，请稍后......");
        } else {
            title.setText("系统安装");
            cancleUpdateButn.setVisibility(View.GONE);
            progressMsgTv.setText("正在安装中，请稍后......");
        }
        cancleUpdateButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmiInstallManager.canleUpdate();
                showMainView();
            }
        });
    }


    public void shouwSuccesDialong() {
        title.setText("升级结果");
        zero.setVisibility(View.GONE);
        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        three.setVisibility(View.VISIBLE);
        if (App.installCompleteBean.getArray().get(0).getResult() != 0) {
            msgIco.setBackgroundResource(R.drawable.cuowu);
            msgTv.setText("本次升级失败（" + App.installCompleteBean.getArray().get(0).getDescription() + ")");
        } else {
            msgIco.setBackgroundResource(R.drawable.install_success);
            msgTv.setText("本次升级成功！！！");
        }
        handler.sendEmptyMessageDelayed(0x888, 5000);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x888:
                    showMainView();
                    break;
                case 0x665:
                    ProgressDialogView.dismiss();
                    break;
            }

        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRegionEvent(UpdateTagEvent event) {
        if (dialog != null) {
            dialog.dismiss();
        }
        switch (event.eventType) {
            case TBOX2HMI_DATA_MESSAGE_QUERY:
                if (App.queryUpgradeBean.getReturnX() == 1) {
                    ProgressDialogView.updateMessage("已有升级任务！！！");
                    handler.sendEmptyMessageDelayed(0x665,3000);
                } else if (App.queryUpgradeBean.getReturnX() == 2) {
                    ProgressDialogView.updateMessage("检查到当前有新版本升级任务，文件准备中请稍等！！！");
                } else {
                    ProgressDialogView.updateMessage("没有升级任务！！！");
                    handler.sendEmptyMessageDelayed(0x665,3000);
                }
                break;
            case TBOX2HMI_DATA_MESSAGE_UPDATE:
                ProgressDialogView.dismiss();
                showUpdateView();
                break;
            case HMI2TBOX_ICC_DL_TYPE_REQ:
                new AlertDialog.Builder(this).setCancelable(false).setTitle("下载网络模式选择").setMessage("请选择当前下载的网络！！！").setPositiveButton("Wi-Fi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HmiInstallManager.selectNetworkMode(1);
                    }
                }).setNegativeButton("皆可", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HmiInstallManager.selectNetworkMode(2);
                    }
                }).create().show();
                break;
            case TBOX2HMI_DATA_MESSAGE_PROGRESS:
                ProgressDialogView.dismiss();
                showProgressView(1);
                progress.setProgress(App.progressBean.getArray().get(0).getProgress());
                progressTv.setText(App.progressBean.getArray().get(0).getProgress() + "%");
                break;
            case TBOX2HMI_DOWNLOAD_RESULT:
                ProgressDialogView.dismiss();
                showInstallView();
                break;

            case TBOX2HMI_INSTALL_PROGRESS:
                ProgressDialogView.dismiss();
                showProgressView(2);
                progress.setProgress(App.progressBean.getArray().get(0).getProgress());
                progressTv.setText(App.progressBean.getArray().get(0).getProgress() + "%");
                break;
            case TBOX2HMI_INSTALL_RESULT:
                ProgressDialogView.dismiss();
                shouwSuccesDialong();
                break;
            case TBOX2HMI_INSTALL_CONFIRM:
                ProgressDialogView.dismiss();
                showInstallViewSecond();
                break;
            case TBOX2HMI_CANCLE_POPWINDOWS:
                ProgressDialogView.dismiss();
                showMainView();
                break;
        }

    }

}
