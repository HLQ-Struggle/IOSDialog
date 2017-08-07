package cn.hlq.iosdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.hlq.iosdialog.manager.HintDialog;
import cn.hlq.iosdialog.manager.IOSLoadingDialog;
import cn.hlq.iosdialog.manager.LoadingDialog;
import cn.hlq.iosdialog.manager.PhotoDialog;

public class MainActivity extends Activity {

    private MainActivity selfActivity = MainActivity.this;

    private HintDialog hintDialog = new HintDialog(); // 提示框
    private HintDialog singleHintDialog = new HintDialog(); // 单个提示框
    private PhotoDialog photoDialog = new PhotoDialog(); // 拍照 选择相册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // AlertDialog
        findViewById(R.id.btn_alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(selfActivity).setTitle("测试标题").setMessage("Hello Word~!").setPositiveButton("哈喽", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
        // Android 加载框
        findViewById(R.id.btn_android_loading_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog().setOnTouchOutside(true);
                loadingDialog.show(selfActivity.getFragmentManager(), "loadingDialog");
            }
        });
        // 高仿IOS加载框
        findViewById(R.id.btn_ios_loading_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOSLoadingDialog iosLoadingDialog = new IOSLoadingDialog().setOnTouchOutside(true);
                iosLoadingDialog.show(selfActivity.getFragmentManager(), "iosLoadingDialog");
            }
        });
        // 提示框
        findViewById(R.id.btn_ios_hint_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintDialog.setContent("确定要离开吗？");
                hintDialog.setOnConfirmClickListener(new HintDialog.HintConfirmCallback() {
                    @Override
                    public void onClick() {
                        hintDialog.dismiss();
                        Toast.makeText(selfActivity, "点击确定", Toast.LENGTH_SHORT).show();
                    }
                });
                hintDialog.setOnCancelClickListener(new HintDialog.HintCancelCallback() {
                    @Override
                    public void onClick() {
                        hintDialog.dismiss();
                        Toast.makeText(selfActivity, "点击取消", Toast.LENGTH_SHORT).show();
                    }
                });
                hintDialog.show(selfActivity.getFragmentManager(), "hintDialog");
            }
        });
        // 单个提示框
        findViewById(R.id.btn_ios_single_hint_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleHintDialog.setContent("请认真填写相关信息，谢谢合作~").setIsSingleButton(true);
                singleHintDialog.setOnSingleClickListener(new HintDialog.HintSingleCallback() {
                    @Override
                    public void onClick() {
                        singleHintDialog.dismiss();
                        Toast.makeText(selfActivity, "点击确认~", Toast.LENGTH_SHORT).show();
                    }
                });
                singleHintDialog.show(selfActivity.getFragmentManager(), "singleHintDialog");
            }
        });
        // 拍照 选取相册
        findViewById(R.id.btn_ios_camera_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoDialog.setOnCameraClickListener(new PhotoDialog.PhotoCameraCallback() {
                    @Override
                    public void onClick() {
                        photoDialog.dismiss();
                        Toast.makeText(selfActivity, "点击拍照", Toast.LENGTH_SHORT).show();
                    }
                });
                photoDialog.setOnChoosePhotoClickListener(new PhotoDialog.ChoosePhotoCallback() {
                    @Override
                    public void onClick() {
                        photoDialog.dismiss();
                        Toast.makeText(selfActivity, "点击选取相册", Toast.LENGTH_SHORT).show();
                    }
                });
                photoDialog.setOnCancleClickListener(new PhotoDialog.PhoneCancelCallback() {
                    @Override
                    public void onClick() {
                        photoDialog.dismiss();
                        Toast.makeText(selfActivity, "点击取消", Toast.LENGTH_SHORT).show();
                    }
                });
                photoDialog.show(selfActivity.getFragmentManager(), "");
            }
        });
    }

}
