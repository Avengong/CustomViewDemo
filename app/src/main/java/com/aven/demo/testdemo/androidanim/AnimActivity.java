package com.aven.demo.testdemo.androidanim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aven.demo.testdemo.R;
import com.aven.demo.testdemo.androidanim.chapter.ChapterThreeActivity;

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


        startActivity(new Intent(this, ChapterThreeActivity.class));


    }

}
