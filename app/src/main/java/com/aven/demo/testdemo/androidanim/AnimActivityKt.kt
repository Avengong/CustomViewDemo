package com.aven.demo.testdemo.androidanim

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.aven.demo.testdemo.R
import com.aven.demo.testdemo.androidanim.chapter.ChapterFiveActivity
import com.aven.demo.testdemo.androidanim.chapter.ChapterOneActivity
import com.aven.demo.testdemo.androidanim.chapter.ChapterThreeActivity
import com.aven.demo.testdemo.androidanim.chapter.ChapterTwoActivity
import com.aven.demo.testdemo.androidanim.chapter.four.ChapterFourActivity
import kotlinx.android.synthetic.main.anim_activity.*

/**
 * Created by ${Aven.Gong} on 2019/8/8 0008.
 */
class AnimActivityKt : Activity() {

    private val TAG: String = "AnimActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anim_activity)

        btn_chapter_four.setOnClickListener {
            startActivity(Intent(this, ChapterFourActivity::class.java))
        }

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
//        ofInt.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation: ValueAnimator? ->
//            Log.d(TAG, "fraction: ${animation?.animatedFraction} value:${animation?.animatedValue}")
//        })
//        ofInt.duration = 1000
//        ofInt.start()
        startActivity(Intent(this, ChapterThreeActivity::class.java))

    }

    fun chapterOne(view: View) {
        var intent = Intent(this, ChapterOneActivity::class.java)
        startActivity(intent)
    }

    fun chapterTwo_view_anim(view: View) {
        var intent = Intent(this, ChapterTwoActivity::class.java)
        startActivity(intent)
    }

    fun pathAnim(view: View) {
        var intent = Intent(this, ChapterFiveActivity::class.java)
        startActivity(intent)
    }
}