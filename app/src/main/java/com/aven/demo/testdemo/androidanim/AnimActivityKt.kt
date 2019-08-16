package com.aven.demo.testdemo.androidanim

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.aven.demo.testdemo.R

/**
 * Created by ${Aven.Gong} on 2019/8/8 0008.
 */
class AnimActivityKt : Activity() {

    private val TAG: String = "AnimActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anim_activity)

    }

    //
    fun valueAnimator(view: View) {
        val ofInt = ValueAnimator.ofInt(0, 500)
//        ofInt.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
//            val animatedFraction = animation.animatedFraction
//            val animatedValue = animation.animatedValue
//            Log.d(TAG, "fraction:$animatedFraction value: $animatedValue")
//        })
//        ofInt.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
//            animation ->
//            Log.d(TAG, "fraction: ${animation.animatedFraction} value:${animation.animatedValue}")
//        })
        ofInt.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation: ValueAnimator? ->
            Log.d(TAG, "fraction: ${animation?.animatedFraction} value:${animation?.animatedValue}")
        })
        ofInt.duration = 1000
        ofInt.start()


    }

    fun chapterOne(view: View) {
        var intent = Intent(this, ChapterOne::class.java)
        startActivity(intent)
    }

}