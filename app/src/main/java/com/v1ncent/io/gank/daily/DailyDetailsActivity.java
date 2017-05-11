package com.v1ncent.io.gank.daily;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseActivity;
import com.v1ncent.io.gank.daily.widget.WebHeader;
import com.v1ncent.io.gank.ui.LoadingWebView;
import com.v1ncent.io.gank.widget.popup.amin.AnimEnter;
import com.v1ncent.io.gank.widget.popup.amin.AnimExit;
import com.v1ncent.io.gank.widget.popup.v1ncentPupop;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.SpringView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

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
    private boolean isLoveorN = false;
    private View menuPupop;
    private v1ncentPupop pupop;
    private LinearLayout pupopCollect;
    private LinearLayout pupopShare;
    private LinearLayout pupopLink;
    private LinearLayout pupopWeb;
    private LinearLayout pupopRefresh;
    private ScrollView pupopRoot;
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
        mContext = this;
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
        setStatusBarColor(getResources().getColor(R.color.tab_1));

        title.setBackgroundColor(getResources().getColor(R.color.tab_1));
        titleText.setText("干货详情");
        rightImg.setImageResource(R.mipmap.title_more);
        rightImg.setVisibility(View.VISIBLE);

        detailsWebview.addProgressBar();
        detailsWebview.loadMessageUrl(url);

        webSpring.setHeader(new WebHeader(mContext, url));
    }
    @Nullable
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
                menuPupop = View.inflate(mContext, R.layout.pupop_gank_details, null);
                pupopCollect = (LinearLayout) menuPupop.findViewById(R.id.pupop_collect);
                pupopShare = (LinearLayout) menuPupop.findViewById(R.id.pupop_share);
                pupopLink = (LinearLayout) menuPupop.findViewById(R.id.pupop_link);
                pupopWeb = (LinearLayout) menuPupop.findViewById(R.id.pupop_web);
                pupopRefresh = (LinearLayout) menuPupop.findViewById(R.id.pupop_refresh);
                pupopRoot = (ScrollView) menuPupop.findViewById(R.id.pupop_root);

                pupop = new v1ncentPupop(mContext, menuPupop)
                        .gravity(Gravity.BOTTOM)
                        .anchorView(rightImg)
                        .bubbleColor(Color.parseColor("#646464"))
                        .triangleWidth(15)
                        .triangleHeight(10)
                        .showAnim(new AnimEnter())
                        .dismissAnim(new AnimExit());
                pupop.show();
                pupopCollect.setOnClickListener(mContext);
                pupopShare.setOnClickListener(mContext);
                pupopLink.setOnClickListener(mContext);
                pupopWeb.setOnClickListener(mContext);
                pupopRefresh.setOnClickListener(mContext);

                OverScrollDecoratorHelper.setUpOverScroll(pupopRoot);//设置滑动

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

                pupop.dismiss();
                break;
            case R.id.pupop_collect:
                pupop.dismiss();
                break;
            case R.id.pupop_share:
                pupop.dismiss();
                break;
            case R.id.pupop_link:
                pupop.dismiss();
                break;
            case R.id.pupop_web:
                pupop.dismiss();
                break;
            case R.id.pupop_refresh:
                detailsWebview.reload();
                pupop.dismiss();
                break;

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
