package com.v1ncent.io.gank.me.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseActivity;
import com.v1ncent.io.gank.widget.ToggleSwitchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by v1ncent on 2017/4/17.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_me_info)
    LinearLayout settingMeInfo;
    @BindView(R.id.setting_load_image_switch)
    ToggleSwitchView settingLoadImageSwitch;
    @BindView(R.id.clear_cache_data)
    TextView clearCacheData;
    @BindView(R.id.setting_clear_cache)
    LinearLayout settingClearCache;
    @BindView(R.id.setting_about_us)
    LinearLayout settingAboutUs;
    @BindView(R.id.login_out)
    LinearLayout loginOut;
    @BindView(R.id.setting_root)
    ScrollView settingRoot;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        Logger.i("onCreate");
        initView();
    }

    private void initView() {
        titleText.setText("通用设置");
        setStatusBarColor(getResources().getColor(R.color.white), 32);
        OverScrollDecoratorHelper.setUpOverScroll(settingRoot);//设置滑动

    }

    @OnClick(R.id.leftBtn)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
    }
}
