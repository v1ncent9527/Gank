package com.v1ncent.io.gank.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.fragment.DailyFragment;
import com.v1ncent.io.gank.fragment.FindFragment;
import com.v1ncent.io.gank.fragment.FollowFragment;
import com.v1ncent.io.gank.fragment.MeFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * Created by v1ncent on 2017/4/11.
 */

public class IndexActivity extends BaseActivity {


    List<Fragment> mFragments;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.tab)
    PageBottomTabLayout tab;
    @BindView(R.id.layout)
    RelativeLayout layout;
    private FragmentManager fragmentManager;
    private DailyFragment dailyFragment;
    private FindFragment findFragment;
    private FollowFragment followFragment;
    private MeFragment meFragment;
    private NavigationController navigationController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        transaction.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
        if (dailyFragment == null) {
            dailyFragment = new DailyFragment();
            transaction.add(R.id.frameLayout, dailyFragment);
        } else {
            transaction.show(dailyFragment);
        }
        transaction.commit();

        initView();
    }

    private void initView() {
        navigationController = tab.material()
                .addItem(R.mipmap.icon_tabbar_subscription, R.mipmap.icon_tabbar_subscription_active, "首页", getResources().getColor(R.color.tab_1))
                .addItem(R.mipmap.icon_tabbar_home, R.mipmap.icon_tabbar_home_active, "发现", getResources().getColor(R.color.tab_2))
                .addItem(R.mipmap.icon_tabbar_notification, R.mipmap.icon_tabbar_notification_active, "关注", getResources().getColor(R.color.tab_3))
                .addItem(R.mipmap.icon_tabbar_me, R.mipmap.icon_tabbar_me_active, "我的", getResources().getColor(R.color.tab_4))
                .setDefaultColor(getResources().getColor(R.color.tab_default))
                .build();
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);

                hideFragments(transaction);

                switch (index) {
                    case 0:
                        if (dailyFragment == null) {
                            dailyFragment = new DailyFragment();
                            transaction.add(R.id.frameLayout, dailyFragment);
                        } else {
                            transaction.show(dailyFragment);
                        }
                        break;
                    case 1:
                        if (findFragment == null) {
                            findFragment = new FindFragment();
                            transaction.add(R.id.frameLayout, findFragment);
                        } else {
                            transaction.show(findFragment);
                        }
                        break;
                    case 2:
                        if (followFragment == null) {
                            followFragment = new FollowFragment();
                            transaction.add(R.id.frameLayout, followFragment);
                        } else {
                            transaction.show(followFragment);
                        }
                        break;
                    case 3:
                        if (meFragment == null) {
                            meFragment = new MeFragment();
                            transaction.add(R.id.frameLayout, meFragment);
                        } else {
                            transaction.show(meFragment);
                        }
                        break;
                }
                transaction.commit();

                //选中时触发
//                Logger.i("onSelected", "onSelected:" + index + "   TAG: " + old);

//                transaction.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
//                transaction.replace(R.id.frameLayout, mFragments.get(index));
//                transaction.commit();
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
//                Logger.i("asd", "onRepeatClick:" + index + "   TAG: " + tag);

            }
        });

    }

    /**
     * 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (dailyFragment != null) {
            transaction.hide(dailyFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (followFragment != null) {
            transaction.hide(followFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }

    }

    @Override
    public void onClickListener(View v) {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
