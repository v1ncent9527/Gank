package com.v1ncent.io.gank.daily.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.widget.loadingview.SlackLoadingView;
import com.v1ncent.io.gank.widget.refreshPlusLoadmore.BaseHeader;

/**
 * Created by v1ncent on 2017/4/28.
 */

public class DailyHeader extends BaseHeader {
    private Context context;
    private ImageView headerLogo;
    private int logoSrc;
    private SlackLoadingView slackLoadingView;
    private View frame;


    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.daily_header, viewGroup, true);
        headerLogo = (ImageView) view.findViewById(R.id.ali_header_logo);
        slackLoadingView = (SlackLoadingView) view.findViewById(R.id.loadingView);
        frame = view.findViewById(R.id.ali_frame);
        if(logoSrc!=0) headerLogo.setImageResource(logoSrc);

        return view;
    }

    public DailyHeader(Context context, int logoSrc) {
        this.context = context;
        this.logoSrc = logoSrc;
    }

    @Override
    public int getDragSpringHeight(View rootView) {
        return frame.getMeasuredHeight();
    }

    @Override
    public int getDragLimitHeight(View rootView) {
        return frame.getMeasuredHeight();
    }


    @Override
    public void onPreDrag(View rootView) {

    }

    @Override
    public void onDropAnim(View rootView, int dy) {

    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
    }

    @Override
    public void onStartAnim() {
        slackLoadingView.start();
    }

    @Override
    public void onFinishAnim() {
        slackLoadingView.reset();
    }
}
