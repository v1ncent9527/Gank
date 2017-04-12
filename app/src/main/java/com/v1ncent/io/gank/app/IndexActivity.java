package com.v1ncent.io.gank.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.v1ncent.io.gank.R;
import com.v1ncent.io.gank.fragment.DailyFragment;
import com.v1ncent.io.gank.fragment.FindFragment;
import com.v1ncent.io.gank.fragment.FollowFragment;
import com.v1ncent.io.gank.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

import static android.R.attr.tag;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);

        initFragment();
        initView();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        DailyFragment dailyFragment = new DailyFragment();
        FindFragment findFragment = new FindFragment();
        FollowFragment followFragment = new FollowFragment();
        MeFragment meFragment = new MeFragment();

        mFragments.add(dailyFragment);
        mFragments.add(findFragment);
        mFragments.add(followFragment);
        mFragments.add(meFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commit();
    }

    private void initView() {
        NavigationController navigationController = tab.material()
                .addItem(android.R.drawable.ic_menu_camera, "相机")
                .addItem(android.R.drawable.ic_menu_compass, "位置")
                .addItem(android.R.drawable.ic_menu_search, "搜索")
                .addItem(android.R.drawable.ic_menu_help, "帮助")
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                //选中时触发
                Logger.i("asd", "onSelected:" + index + "   TAG: " + old);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commit();
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
                Logger.i("asd", "onRepeatClick:" + index + "   TAG: " + tag);

            }
        });
    }


    @Override
    public void onClickListener(View v) {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}