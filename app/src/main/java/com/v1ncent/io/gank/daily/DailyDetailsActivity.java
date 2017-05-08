package com.v1ncent.io.gank.daily;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseActivity;
import com.v1ncent.io.gank.daily.widget.WebHeader;
import com.v1ncent.io.gank.ui.LoadingWebView;
import com.v1ncent.io.gank.widget.popup.CustomBubblePopup;
import com.v1ncent.io.gank.widget.popup.amin.AnimEnter;
import com.v1ncent.io.gank.widget.popup.amin.AnimExit;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.SpringView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v1ncent on 2017/5/4.
 */

public class DailyDetailsActivity extends BaseActivity {
    @BindView(R.id.leftImg)
    ImageView leftImg;
    @BindView(R.id.leftBtn)
    View leftBtn;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.rightText)
    TextView rightText;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.rightBtn)
    View rightBtn;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.details_webview)
    LoadingWebView detailsWebview;
    @BindView(R.id.web_spring)
    SpringView webSpring;
    @BindView(R.id.is_love)
    ImageView isLove;
    @BindView(R.id.share_out)
    ImageView shareOut;
    @BindView(R.id.love_num)
    TextView loveNum;

    private String url;
    private DailyDetailsActivity mContext;
    private static DailyDetailsHandler handler;
    private CustomBubblePopup customBubblePopup;
    private boolean isLoveorN = false;
    /**
     * 网页缓存目录
     */
    private static final String cacheDirPath = Environment
            .getExternalStorageDirectory() + "/LoadingWebViewDome/webCache/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_details);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        Logger.i(url);

        initWebViewSetting();
        initView();
    }

    /*webview相关配置*/
    private void initWebViewSetting() {
        WebSettings setting = detailsWebview.getSettings();

        detailsWebview.setInitialScale(39);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过Javascript打开新窗口
        setting.setJavaScriptEnabled(true);//设置WebView属性，能够执行Javascript脚本
        setting.setUseWideViewPort(true);//将图片调整到适合webview的大小
        setting.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        setting.setDomStorageEnabled(true);//设置是否启用了DOM Storage API
        setting.setDatabaseEnabled(true);//开启database storage API功能
        setting.setDatabasePath(cacheDirPath); //设置数据库缓存路径
        setting.setAppCachePath(cacheDirPath);//设置Application Caches缓存目录
        setting.setAppCacheEnabled(true);//开启Application Caches功能
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.white), 32);

        titleText.setText("干货详情");
        rightImg.setImageResource(R.mipmap.details_menu_big);
        rightImg.setVisibility(View.VISIBLE);

        detailsWebview.addProgressBar();
        detailsWebview.loadMessageUrl(url);

        if (handler == null) {
            handler = new DailyDetailsHandler();
        }

        webSpring.setHeader(new WebHeader(mContext, url));
    }

    @OnClick({R.id.leftBtn, R.id.rightBtn, R.id.is_love, R.id.share_out})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                if (detailsWebview.canGoBack()) {
                    detailsWebview.goBack();
                } else {
                    finish();
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                }
                break;
            case R.id.rightBtn:
                customBubblePopup = new CustomBubblePopup(this);
                customBubblePopup
                        .gravity(Gravity.BOTTOM)
                        .anchorView(rightImg)
                        .bubbleColor(Color.parseColor("#646464"))
                        .triangleWidth(15)
                        .triangleHeight(10)
                        .showAnim(new AnimEnter())
                        .dismissAnim(new AnimExit())
                        .show();
                break;

            case R.id.is_love:
                if (isLoveorN) {
                    isLove.setImageResource(R.mipmap.love_off);
                    isLoveorN = false;
                } else {
                    isLove.setImageResource(R.mipmap.love_on);
                    isLoveorN = true;
                }
                break;
            case R.id.share_out:
                break;
        }
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public class DailyDetailsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Logger.i(String.valueOf(msg.what));
            switch (msg.what) {
                case 0:
                    showSuccess("PUPOP_COLLECT");
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

            }
            customBubblePopup.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detailsWebview.destroyWebView();
    }

    /**
     * 按返回键时， 不退出程序而是返回WebView的上一页面
     */
    @Override
    public void onBackPressed() {
        if (detailsWebview.canGoBack())
            detailsWebview.goBack();
        else {
            super.onBackPressed();
        }
    }


}
