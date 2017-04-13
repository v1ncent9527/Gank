package com.v1ncent.io.gank.widget;

/**
 * Created by v1ncent on 16/12/20.
 * 自定义loadingDialog
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.v1ncent.io.gank.R;


/**
 * 加载提醒对话框
 */
public class LoadingDialog extends ProgressDialog {
    private ObjectAnimator load;
    private String loadingMsg;
    private ImageView loadImg;
    private ImageView loadStatue;

    private TextView dialogMsg;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme, String loadingMsg) {
        super(context, theme);
        this.loadingMsg = loadingMsg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext(), loadingMsg);
    }

    private void init(Context context, String dialohMsg) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.view_load_dialog);

        /**
         * 设置loading图旋转
         */
        loadImg = (ImageView) findViewById(R.id.img_load_dialog);
        load = ObjectAnimator.ofFloat(loadImg, "rotation", 0f, 359f);
        load.setDuration(1600);
        load.setInterpolator(new LinearInterpolator());
        load.setRepeatCount(-1);
        load.setTarget(loadImg);

        /**
         * 设置dialog提示语
         */
        dialogMsg = (TextView) findViewById(R.id.tv_load_dialog);
        dialogMsg.setText(dialohMsg);

        loadStatue = (ImageView) findViewById(R.id.img_load_statue);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        /**
         * 给dialog设置show和cancel的动画，一定要放在getWindow().setAttributes(params)方法之后！
         */
        getWindow().setWindowAnimations(R.style.dialogWindowAnim);
    }

    /**
     * show
     */
    @Override
    public void show() {
        super.show();
        ShowProgressOrN(true);
        dialogMsg.setText(loadingMsg);
        load.start();
    }

    /**
     * cancel
     */
    @Override
    public void cancel() {
        super.cancel();
        //释放动画
        load.cancel();
    }

    /**
     * 加载成功
     *
     * @param success
     */
    public void success(String success) {
        load.cancel();
        ShowProgressOrN(false);
        loadStatue.setImageResource(R.mipmap.dialog_succeed);
        dialogMsg.setText(success);
    }

    /**
     * 加载失败
     *
     * @param failed
     */
    public void failed(String failed) {
        load.cancel();
        ShowProgressOrN(false);
        loadStatue.setImageResource(R.mipmap.dialog_failed);
        dialogMsg.setText(failed);
    }

    private void ShowProgressOrN(boolean show) {
        if (show) {
            loadImg.setVisibility(View.VISIBLE);
            loadStatue.setVisibility(View.GONE);
        } else {
            loadImg.setVisibility(View.GONE);
            loadStatue.setVisibility(View.VISIBLE);
        }
    }
}