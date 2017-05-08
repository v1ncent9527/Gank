package com.v1ncent.io.gank.widget.popup;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.daily.DailyDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class CustomBubblePopup extends BaseBubblePopup<CustomBubblePopup> implements View.OnClickListener {


    @BindView(R.id.pupop_collect)
    LinearLayout pupopCollect;
    @BindView(R.id.pupop_share)
    LinearLayout pupopShare;
    @BindView(R.id.pupop_link)
    LinearLayout pupopLink;
    @BindView(R.id.pupop_web)
    LinearLayout pupopWeb;
    @BindView(R.id.pupop_root)
    ScrollView pupopRoot;

    private Unbinder unbinder;
    public CustomBubblePopup(Context context) {
        super(context);
    }
    private int PUPOP_COLLECT = 0;
    private int PUPOP_SHARE = 1;
    private int PUPOP_LINK = 2;
    private int PUPOP_WEB = 3;

    @Override
    public View onCreateBubbleView() {
        View inflate = View.inflate(mContext, R.layout.pupop_gank_details, null);
        unbinder = ButterKnife.bind(this, inflate);//返回unbinder对象
        OverScrollDecoratorHelper.setUpOverScroll(pupopRoot);//设置滑动

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
    }

    @OnClick({R.id.pupop_collect,
            R.id.pupop_share,
            R.id.pupop_link,
            R.id.pupop_web})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pupop_collect:
                Logger.i("pupop_collect");
                sendMessage("pupop_collect" , PUPOP_COLLECT);
                break;
            case R.id.pupop_share:
                sendMessage("pupop_share" , PUPOP_SHARE);
                break;
            case R.id.pupop_link:
                sendMessage("pupop_link" , PUPOP_LINK);
                break;
            case R.id.pupop_web:
                sendMessage("pupop_web" , PUPOP_WEB);
                break;
        }
    }

    private void sendMessage(String data, int what) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = data;
        DailyDetailsActivity.sendMessage(msg);
    }
}
