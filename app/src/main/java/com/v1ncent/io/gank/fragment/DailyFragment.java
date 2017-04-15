package com.v1ncent.io.gank.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.app.BaseFragment;
import com.v1ncent.io.gank.daily.adapter.DailyPageAdapter;
import com.v1ncent.io.gank.widget.viewpager.CrystalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class DailyFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private TextView dailyDate;
    private TextView dailyWeather;
    @BindView(R.id.viewPager)
    CrystalViewPager viewPager;
    private List<View> list_view;
    private DailyPageAdapter dailyPageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_daily, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.white), 32);
        //这里只设置了4.因为在实现应用中，我们在页面加载的时候，你会根据数据的多少，而知道这个页面的数量
        //一般情况下，我们会根据list<>或是string[]这样的数组的数量来判断要有多少页
        list_view = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.daily_page, null);
            TextView txt_num = (TextView) view.findViewById(R.id.daily_page);
            dailyDate = (TextView) view.findViewById(R.id.daily_date);
            dailyWeather = (TextView) view.findViewById(R.id.daily_weather);

            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "thinfont-thin.ttf"); // create a typeface from the raw ttf
            dailyDate.setTypeface(typeface); // apply the typeface to the textview
            dailyDate.setText("2017  /  04  /  04");

            dailyWeather.setText("多云 天津");
            txt_num.setText(i + "");
            if (i % 2 == 0) {
                txt_num.setBackgroundColor(getResources().getColor(R.color.tab_4));
            }
            list_view.add(view);
        }
        dailyPageAdapter = new DailyPageAdapter(list_view);
        viewPager.setTransition(CrystalViewPager.Transition.STACK);
        viewPager.setAdapter(dailyPageAdapter);
        viewPager.setOnPageChangeListener(this);
    }


    /**
     * arg0 :当前页面，及你点击滑动的页面
     * arg1 :当前页面偏移的百分比
     * arg2 :当前页面偏移的像素位置
     * (int, float, int) pagerNum:第几个界面（从0开始计数） offset:偏移量，计算页面滑动的距离
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * (int) 判断当前是哪个view
     */
    @Override
    public void onPageSelected(int position) {
        Logger.i(String.valueOf(position));
        showInfo(position + "we");
    }

    /**
     * onPageScrollStateChanged(int) 此方法是在状态改变的时候调用，其中arg0这个参数
     * 有三种状态（0，1，2）。
     * arg0 == 1的时辰默示正在滑动，
     * arg0 == 2的时辰默示滑动完毕了，
     * arg0 == 0的时辰默示什么都没做。
     * 当页面开始滑动的时候，三种状态的变化顺序为（1，2，0），演示如下：
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClickListener(View v) {

    }
}
