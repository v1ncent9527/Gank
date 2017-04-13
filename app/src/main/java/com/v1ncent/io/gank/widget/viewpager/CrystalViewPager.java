package com.v1ncent.io.gank.widget.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.v1ncent.io.gank.R;

/**
 * Created by owais.ali on 7/28/2016.
 */
public class CrystalViewPager extends ViewPager {

    private static final int DEFAULT = 0;

    private BaseTransformer transformer;
    private int transition;

    public static final class Transition {
        public static final int STACK = 1;
    }

    public CrystalViewPager(Context context) {
        this(context, null);
    }

    public CrystalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CrystalViewPager);
        try {
            transition = getTransition(typedArray);
        } finally {
            typedArray.recycle();
        }

        init();
    }

    private void init() {
        updateTransformer();
    }

    public void setTransition(int transition) {
        this.transition = transition;
        updateTransformer();
    }

    protected void updateTransformer() {
        switch (transition) {
            case Transition.STACK:
                transformer = new StackTransformer(this);
                break;
            default:
                transformer = null;
                break;
        }

        if (getAdapter() != null) {
            getAdapter().notifyDataSetChanged();
        }

        setPageTransformer(true, transformer);
    }

    protected int getTransition(final TypedArray typedArray) {
        return typedArray.getInt(R.styleable.CrystalViewPager_ctl_vp_transition, DEFAULT);
    }
}
