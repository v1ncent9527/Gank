package com.v1ncent.io.gank.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v1ncent.io.gank.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class text extends BaseActivity {
    @BindView(R.id.TEXT)
    TextView TEXT;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.TEXT)
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.TEXT:
                    showSuccess("登陆成功！");
                break;
        }
    }
}
