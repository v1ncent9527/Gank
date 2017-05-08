package com.v1ncent.io.gank.widget.popup.amin;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.v1ncent.io.gank.widget.dialog.BaseAnimatorSet;

/**
 * Created by v1ncent on 2017/5/8.
 */

public class AnimEnter extends BaseAnimatorSet {
    public AnimEnter() {
        duration = 1000;
    }

    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int h = view.getMeasuredHeight();

//        /*BounceTopEnter*/
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1),//
                ObjectAnimator.ofFloat(view, "translationY", -250 * dm.density, 30, -10, 0));
//        /*FadeEnter*/
//        animatorSet.playTogether(//
//                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(duration));
        /*FallEnter*/
//        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
//                ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
//                ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(duration));
//        /*FlipTopEnter*/
//        animatorSet.playTogether(//
//                ObjectAnimator.ofFloat(view, "rotationX", 90, 0),//
//                ObjectAnimator.ofFloat(view, "translationY", -200 * dm.density, 0), //
//                ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1));
//        /*SlideTopEnter*/
//        animatorSet.playTogether(//
//                ObjectAnimator.ofFloat(view, "translationY", -250 * dm.density, 0), //
//                ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1));
//        /*ZoomInTopEnter*/
//        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1),//
//                ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1),//
//                ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1),//
//                ObjectAnimator.ofFloat(view, "translationY", -h, 60, 0));
    }
}
