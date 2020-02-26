package com.aven.demo.testdemo.androidanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

import com.aven.demo.testdemo.R;
import com.aven.demo.testdemo.androidanim.chapter.ChapterThreeActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${Aven.Gong} on 2019/8/8 0008.
 */
public class AnimActivity extends Activity {

    private static final String TAG = "AnimActivity";
    float[] ff = new float[2];
    Matrix mMatrix = new Matrix();
    float[] ss = new float[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });


//        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d("", "");
//                return false;
//            }
//        });


        int unit = 10;
        int total = 1000;
        for (int i = unit; i < total; i = i + unit) {

        }

        File file = new File("");
        File[] files = file.listFiles();

        for (int i = 0; i < file.length(); i++) {

        }


        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 0, new int[]{Color.RED, Color
                .BLUE,
                Color
                        .GREEN}, new
                float[]{0, 30, 50, 100},
                Shader
                        .TileMode.CLAMP);

    }


    public void valueAnimator(View view) {


        startActivity(new Intent(this, ChapterThreeActivity.class));


    }


    public void setFf(InputStream is) throws IOException {


        byte[] bytes = new byte[1022];
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len = is.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);


        }

    }
}
