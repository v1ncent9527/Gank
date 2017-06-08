package com.v1ncent.io.gank.widget.refreshPlusLoadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.widget.loadingview.AVLoadingIndicatorView;

/**
 * Created by v1ncent on 2017/5/24.
 */

public class V1ncentFooter extends BaseFooter {
    private Context context;
    private TextView footerTitle;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public V1ncentFooter(Context context) {
        this.context = context;
    }


    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.refresh_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(R.id.default_footer_title);
        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avLoadingIndicatorView);
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
        if (upORdown) {
            footerTitle.setText("松开载入更多");
        } else {
            footerTitle.setText("查看更多");
        }
    }

    @Override
    public void onStartAnim() {
        footerTitle.setVisibility(View.INVISIBLE);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText("查看更多");
        footerTitle.setVisibility(View.VISIBLE);
        avLoadingIndicatorView.setVisibility(View.INVISIBLE);
        avLoadingIndicatorView.hide();
    }

}
