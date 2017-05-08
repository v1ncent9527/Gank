package com.v1ncent.io.gank.widget.popup.amin;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.v1ncent.io.gank.widget.dialog.BaseAnimatorSet;

/**
 * Created by v1ncent on 2017/5/8.
 */

public class AnimExit extends BaseAnimatorSet {

    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int h = view.getMeasuredHeight();
        /*FadeExit*/
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(duration));
//        /*FlipVerticalExit*/
//        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotationX", 0, 90),//
//                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
//        /*FlipHorizontalExit*/
//        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotationY", 0, 90),//
//                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
//        /*SlideTopExit*/
//        animatorSet.playTogether(//
//                ObjectAnimator.ofFloat(view, "translationY", 0, -250 * dm.density), //
//                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
//        /*ZoomOutTopExit*/
//        animatorSet.playTogether(//
//                ObjectAnimator.ofFloat(view, "alpha", 1, 1, 0),//
//                ObjectAnimator.ofFloat(view, "scaleX", 1, 0.475f, 0.1f),//
//                ObjectAnimator.ofFloat(view, "scaleY", 1, 0.475f, 0.1f),//
//                ObjectAnimator.ofFloat(view, "translationY", 0, 60, -h));
    }

}
