package com.airwhip.circle.tips.anim;

import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by Whiplash on 09.03.14.
 */
public class Fade extends AlphaAnimation {

    public Fade(View view, float newAlpha) {
        super(view.getAlpha(), newAlpha);
        this.setFillEnabled(true);
        this.setFillAfter(true);
        this.setDuration(300);
    }
}
