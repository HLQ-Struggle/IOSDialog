package cn.hlq.struggle.manager;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import cn.hlq.struggle.R;

/**
 * create by heliquan at 2017年7月30日
 * Android 普通加载框
 */
public class LoadingDialog extends DialogFragment {

    /**
     * 默认点击外面无效
     */
    private boolean onTouchOutside = false;

    /**
     * 加载框提示信息 设置默认
     */
    private String hintMsg = "正在加载，请稍后...";

    /**
     * 设置是否允许点击外面取消
     *
     * @param onTouchOutside
     * @return
     */
    public LoadingDialog setOnTouchOutside(boolean onTouchOutside) {
        this.onTouchOutside = onTouchOutside;
        return this;
    }

    /**
     * 设置加载框提示信息
     *
     * @param hintMsg
     */
    public LoadingDialog setHintMsg(String hintMsg) {
        if (!hintMsg.isEmpty()) {
            this.hintMsg = hintMsg;
        }
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题 死恶心死恶心的
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set cancel on touch outside
        getDialog().setCanceledOnTouchOutside(onTouchOutside);
        View loadingView = inflater.inflate(R.layout.hlq_android_dialog_loading, container);
        TextView hintTextView = loadingView.findViewById(R.id.tv_loading_dialog_hint);
        hintTextView.setText(hintMsg);
        return loadingView;
    }

}
