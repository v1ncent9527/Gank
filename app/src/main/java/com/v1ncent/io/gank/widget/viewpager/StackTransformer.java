package com.v1ncent.io.gank.widget.viewpager;

import android.view.View;

/**
 * Created by owais.ali on 7/31/2016.
 */
public class StackTransformer extends BaseTransformer {

    public StackTransformer(CrystalViewPager crystalViewPager) {
        super(crystalViewPager);
    }

    @Override
    protected void onTransform(View view, float position, int pageWidth, int pageHeight) {
        if(position >= 0){
            view.setTranslationX(pageWidth * -position);
        }
    }
}
