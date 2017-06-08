package com.v1ncent.io.gank.widget.refreshPlusLoadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v1ncent.io.gank.R;

/**
 * Created by v1ncent on 2017/5/8.
 */

public class WebHeader extends BaseHeader {
    private String url;
    private TextView urlTv;
    private Context context;

    public WebHeader(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.header_web, viewGroup, true);
        urlTv = (TextView) view.findViewById(R.id.textView2);
        urlTv.setText("网页由 " + url + " 提供");
        return view;
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

    }

    @Override
    public void onFinishAnim() {

    }
}
