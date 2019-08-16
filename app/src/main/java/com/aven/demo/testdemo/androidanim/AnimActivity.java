package com.aven.demo.testdemo.androidanim;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aven.demo.testdemo.R;

/**
 * Created by ${Aven.Gong} on 2019/8/8 0008.
 */
public class AnimActivity extends Activity {

    private static final String TAG = "AnimActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);
    }


    public void valueAnimator(View view) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                Object animatedValue = animation.getAnimatedValue();
                Log.d(TAG, "fraction:" + animatedFraction + " value: " + animatedValue);

            }
        });
        valueAnimator.start();

        float[] array=new float[]{20,20,10,10,5,5,30,30};

    }

}
