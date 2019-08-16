package com.aven.demo.testdemo.card.layoutmanager;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;


public class CardConfig {
    //屏幕上最多同时显示几个Item
    public static int MAX_SHOW_COUNT;

    //每一级Scale相差0.05f，translationY相差7dp左右
    public static float SCALE_GAP;
    public static int TRANS_X_GAP;
    private static ObjectAnimator rotation;

    public static void initConfig(Context context) {
        MAX_SHOW_COUNT = 4;
        SCALE_GAP = 0.04f;
        TRANS_X_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, context.getResources().getDisplayMetrics());
    }

    public static Animation getAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(12000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        return rotateAnimation;
    }

    public static ObjectAnimator getObjectAnim(View target) {

        if (rotation==null){
            rotation = ObjectAnimator.ofFloat(target, "rotation", 0, 360);
            rotation.setDuration(12000);
            rotation.setInterpolator(new LinearInterpolator());
            rotation.setRepeatCount(Animation.INFINITE);
        }
        return rotation;
    }
}