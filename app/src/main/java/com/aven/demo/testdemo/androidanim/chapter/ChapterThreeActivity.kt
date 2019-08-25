package com.aven.demo.testdemo.androidanim.chapter

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import com.aven.demo.testdemo.R
import com.aven.demo.testdemo.androidanim.evaluator.CharEvaluator
import com.aven.demo.testdemo.androidanim.evaluator.FreeFallEvaluator
import com.aven.demo.testdemo.androidanim.evaluator.Point
import kotlinx.android.synthetic.main.chapter_three_activity.*

class ChapterThreeActivity : AppCompatActivity() {

    val TAG = "ChapterThreeActivity_l"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_three_activity)


        btn_value_click.setOnClickListener {
            doValueAnim()
        }

        btn_value_argb.setOnClickListener {
            doArgbChange()
        }

        btn_value_char.setOnClickListener {
            doCharChange()
        }

        btn_value_free_fail.setOnClickListener {
            doFreefail()
        }

    }

    private fun doFreefail() {
        val point1 = Point()
        point1.x = 0
        point1.y = 0

        val point2 = Point()
        point2.x = 600
        point2.y = 500

        val ofObject = ValueAnimator.ofObject(FreeFallEvaluator(), point1, point2)
        ofObject.duration = 3000
        ofObject.interpolator = AccelerateInterpolator()
        ofObject.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Point
            Log.d(TAG, "doFreefail x :${animatedValue.x}  , y:${animatedValue.y},  " +
                    "fraction:${animation
                            .animatedFraction}")
            iv_free_fail.layout(animatedValue.x, animatedValue.y, animatedValue.x + iv_free_fail
                    .width, animatedValue.y + iv_free_fail.height)
        }

        ofObject.start()

    }

    private fun doCharChange() {

        val ofObject = ValueAnimator.ofObject(CharEvaluator(), 'a', 'z')

        ofObject.duration = 10000
        ofObject.interpolator = AccelerateInterpolator()
        ofObject.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Char
            Log.d(TAG, "doCharChange value :$animatedValue  fraction:${animation.animatedFraction}")
            tv_value_content.text = animatedValue.toString()
        }
        ofObject.start()


    }

    private fun doArgbChange() {
        val ofInt = ValueAnimator.ofInt(0xffffff00.toInt(), 0xff0000ff.toInt())
        ofInt.setEvaluator(ArgbEvaluator())
        ofInt.duration = 5000
        ofInt.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            Log.d(TAG, "doArgbChange value :$animatedValue  fraction:${animation.animatedFraction}")
            tv_value_content.setBackgroundColor(animatedValue)
        }

        ofInt.start()


    }

    private fun doValueAnim() {

        val ofInt = ValueAnimator.ofInt(0, 400)
        ofInt.duration = 5000
        ofInt.interpolator = LinearInterpolator()
        ofInt.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            //在原来的基础上改变位置
//            tv_value_content.layout(tv_value_content.left+animatedValue,tv_value_content
//                    .top+animatedValue,tv_value_content.right+animatedValue,tv_value_content
//                    .bottom+animatedValue)

            //直接设定tv控件的左上右下，
            Log.d(TAG, "animatedValue :$animatedValue  fraction:${animation.animatedFraction}")

            tv_value_content.layout(animatedValue, animatedValue, tv_value_content.width + animatedValue,
                    tv_value_content
                            .height + animatedValue)


        }
        ofInt.start()


    }


}