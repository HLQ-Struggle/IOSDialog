package cn.hlq.iosdialog.manager;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.TextView;

import cn.hlq.iosdialog.R;

/**
 * 提示框 Created by HLQ on 2017/6/15
 */
public class HintDialog extends DialogFragment {

    private TextView tvTitle; // 标题
    private TextView tvContent; // 内容
    private TextView tvCancelTextView; // 取消
    private TextView tvConfirmTextView; // 确定
    private TextView tvSingleTextView; // 单个按钮

    /**
     * 确认回调
     */
    private HintConfirmCallback confirmCallback;
    /**
     * 取消回调
     */
    private HintCancelCallback cancelCallback;
    /**
     * 单选回调
     */
    private HintSingleCallback singleCallback;

    private boolean isSingleButton = false; // 是否启用单个按钮

    /**
     * 默认点击外面无效
     */
    private boolean onTouchOutside = false;

    /**
     * 标题
     */
    private String title = "提示";

    /**
     * 内容
     */
    private String content;

    private String confirm, cancel; // 确定 取消 可单独定制

    /**
     * 设置确定按钮内容
     *
     * @param confirmMsg
     * @return
     */
    public HintDialog setOnConfirmBtnText(String confirmMsg) {
        this.confirm = confirmMsg;
        return this;
    }

    /**
     * 设置取消按钮内容
     *
     * @param cancelMsg
     * @return
     */
    public HintDialog setOnCancelBtnText(String cancelMsg) {
        this.cancel = cancelMsg;
        return this;
    }

    /**
     * 设置是否启用单个按钮
     *
     * @param isSingle
     * @return
     */
    public HintDialog setIsSingleButton(boolean isSingle) {
        this.isSingleButton = isSingle;
        return this;
    }

    /**
     * 设置是否允许点击外面
     *
     * @param onTouchOutside
     * @return
     */
    public HintDialog setOnTouchOutside(boolean onTouchOutside) {
        this.onTouchOutside = onTouchOutside;
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public HintDialog setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public HintDialog setContent(String content) {
        this.content = content;
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
        View hintView = inflater.inflate(R.layout.hlq_dialog_hint, null);
        initView(hintView);
        return hintView;
    }

    /**
     * 初始化View
     *
     * @param hintView
     */
    private void initView(View hintView) {
        tvTitle = hintView.findViewById(R.id.tv_hint_dialog_title);
        tvContent = hintView.findViewById(R.id.tv_hint_dialog_content);
        tvTitle.setText(title);
        tvContent.setText(content);
        if (isSingleButton) {
            ViewStub vsSingleButton = hintView.findViewById(R.id.vs_single_button);
            vsSingleButton.inflate();
            tvSingleTextView = hintView.findViewById(R.id.tv_single);
            tvSingleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    singleCallback.onClick();
                }
            });
        } else {
            ViewStub vsDoubleButton = hintView.findViewById(R.id.vs_double_button);
            vsDoubleButton.inflate();
            tvCancelTextView = hintView.findViewById(R.id.btn_hint_dialog_cancle);
            tvConfirmTextView = hintView.findViewById(R.id.btn_hint_dialog_confirm);
            if (!"".equals(confirm) && confirm != null) {
                tvConfirmTextView.setText(confirm);
            }
            if (!"".equals(cancel) && cancel != null) {
                tvCancelTextView.setText(cancel);
            }
            tvConfirmTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmCallback.onClick();
                }
            });
            tvCancelTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelCallback.onClick();
                }
            });
        }
    }

    /**
     * 确定点击事件
     *
     * @param confirmCallback
     * @return
     */
    public HintDialog setOnConfirmClickListener(HintConfirmCallback confirmCallback) {
        this.confirmCallback = confirmCallback;
        return this;
    }

    /**
     * 取消点击事件
     *
     * @param cancelCallback
     * @return
     */
    public HintDialog setOnCancelClickListener(HintCancelCallback cancelCallback) {
        this.cancelCallback = cancelCallback;
        return this;
    }

    /**
     * 单个按钮点击事件
     *
     * @param singleCallback
     * @return
     */
    public HintDialog setOnSingleClickListener(HintSingleCallback singleCallback) {
        this.singleCallback = singleCallback;
        return this;
    }

    /**
     * 确认回调
     */
    public interface HintConfirmCallback {

        void onClick();

    }

    /**
     * 取消回调
     */
    public interface HintCancelCallback {

        void onClick();

    }

    /**
     * 单个按钮回调
     */
    public interface HintSingleCallback {

        void onClick();

    }

}
