package com.v1ncent.io.gank.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 带滚动监听的scrollview
 */
public class GradationScrollView extends ScrollView {
    private OnScrollListener onScrollListener;

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.onScrollListener = scrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface OnScrollListener {
        void onScrollChanged(int x, int y, int oldX, int oldY);
    }


}