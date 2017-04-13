package com.v1ncent.io.gank.daily.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by v1ncent on 2017/4/12.
 */

public class DailyPageAdapter extends PagerAdapter {
    private List<View> list_view;
    private int pagerNum = 0;

    public DailyPageAdapter(List<View> list_view) {
        this.list_view = list_view;
    }

    @Override
    public int getCount() {
        return list_view.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            if (list_view.get(position).getParent() == null) {
                container.addView(list_view.get(position), 0);
            } else {
                    /**
                     * 很难理解新添加进来的view会自动绑定一个父类，由于一个儿子view不能与两个父类相关，
                     * 所以得解绑不这样做否则会产生 viewpager java.lang.IllegalStateException:
                     * The specified child already has a parent. You must call
                     * removeView() on the child's parent first.
                     */
                ((ViewGroup) list_view.get(position).getParent())
                        .removeView(list_view.get(position));
                container.addView(list_view.get(position), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pagerNum = position;
        }
        return list_view.get(position);

    }

    public int getPagerNum() {
        return pagerNum;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (list_view.get(position) != null) {
            container.removeView(list_view.get(position));
        }
    }
}
