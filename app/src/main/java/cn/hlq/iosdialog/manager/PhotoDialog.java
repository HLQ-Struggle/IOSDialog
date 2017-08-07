package cn.hlq.iosdialog.manager;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.hlq.iosdialog.R;

/**
 * 高仿IOS拍照 选择相册 Created by HLQ on 2017/8/1
 */
public class PhotoDialog extends DialogFragment {

    private TextView tvTakeCamera; // 拍照
    private TextView tvChoosePhoto; // 选择相册
    private TextView tvCancel; // 取消

    private PhotoCameraCallback mPhotoCameraCallback;
    private ChoosePhotoCallback mChoosePhotoCallback;
    private PhoneCancelCallback mPhoneCancelCallback;

    /**
     * 默认点击外面无效
     */
    private boolean onTouchOutside = false;

    /**
     * 设置是否允许点击外面取消
     *
     * @param onTouchOutside
     * @return
     */
    public PhotoDialog setOnTouchOutside(boolean onTouchOutside) {
        this.onTouchOutside = onTouchOutside;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams mLayoutParams = getDialog().getWindow().getAttributes();
        mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(mLayoutParams);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 设置底部显示
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题 死恶心死恶心的
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set cancel on touch outside
        getDialog().setCanceledOnTouchOutside(onTouchOutside);
        View photoView = inflater.inflate(R.layout.hlq_ios_dialog_photo, null);
        initView(photoView);
        return photoView;
    }

    private void initView(View photoView) {
        tvTakeCamera = photoView.findViewById(R.id.tv_take_camera);
        tvChoosePhoto = photoView.findViewById(R.id.tv_choose_photo);
        tvCancel = photoView.findViewById(R.id.tv_cancel);

        tvTakeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhotoCameraCallback.onClick();
            }
        });
        tvChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoosePhotoCallback.onClick();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneCancelCallback.onClick();
            }
        });
    }

    /**
     * 拍照
     *
     * @param photoCameraCallback
     * @return
     */
    public PhotoDialog setOnCameraClickListener(PhotoCameraCallback photoCameraCallback) {
        this.mPhotoCameraCallback = photoCameraCallback;
        return this;
    }

    /**
     * 选择相册
     *
     * @param choosePhotoCallback
     * @return
     */
    public PhotoDialog setOnChoosePhotoClickListener(ChoosePhotoCallback choosePhotoCallback) {
        this.mChoosePhotoCallback = choosePhotoCallback;
        return this;
    }

    /**
     * 取消
     *
     * @param phoneCancelCallback
     * @return
     */
    public PhotoDialog setOnCancleClickListener(PhoneCancelCallback phoneCancelCallback) {
        this.mPhoneCancelCallback = phoneCancelCallback;
        return this;
    }

    /**
     * 拍照
     */
    public interface PhotoCameraCallback {

        void onClick();

    }

    /**
     * 选取相册
     */
    public interface ChoosePhotoCallback {

        void onClick();

    }

    /**
     * 取消
     */
    public interface PhoneCancelCallback {

        void onClick();

    }

}
