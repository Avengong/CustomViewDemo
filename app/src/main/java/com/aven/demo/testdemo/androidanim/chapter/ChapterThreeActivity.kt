package com.aven.demo.testdemo.androidanim.chapter

import android.animation.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
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


        btn_value_z_rotation.setOnClickListener { doZRotation() }
        btn_object_free_fall.setOnClickListener { doObjectFreeFall() }

        btn_keyframe.setOnClickListener { doKeyFrameAnim() }

    }

    private fun doValueLoader() {

        val loadAnimator = AnimatorInflater.loadAnimator(this, R.animator.value_anim) as ValueAnimator
        loadAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            tv_value_content.layout(animatedValue, animatedValue, animatedValue + tv_value_content
                    .width, animatedValue + tv_value_content.height)
        }
        loadAnimator.start()

        btn_keyframeA_Z.setOnClickListener { doKeyFrameA_Z() }

    }

    private fun doKeyFrameA_Z() {
        //
        val keyfrmeA = Keyframe.ofObject(0f, 'A')
        val keyfrmeZ = Keyframe.ofObject(1f, 'Z')

        val valuesHolder = PropertyValuesHolder.ofKeyframe("CharText", keyfrmeA, keyfrmeZ)
        valuesHolder.setEvaluator(CharEvaluator())
        val anima = ObjectAnimator.ofPropertyValuesHolder(tv_charset, valuesHolder)
        anima.duration = 2000
//        anima.start()

        //ViewPropertyAnimator 使用
        //这个方法是相对于当前的alpha值来说的。假设当前是0（透明），那么就会变成0.5f。如果当前是1，则变成1.5，那么不会有变化。
        tv_charset.animate().alphaBy(-0.5f).x(5f)


    }

    private fun doKeyFrameAnim() {

        val keyframe0 = Keyframe.ofFloat(0f, 0f)
        val keyframe2 = Keyframe.ofFloat(0.2f, -20f)
        val keyframe4 = Keyframe.ofFloat(0.4f, 20f)
        val keyframe6 = Keyframe.ofFloat(0.6f, -20f)
        val keyframe8 = Keyframe.ofFloat(0.8f, 20f)
        val keyframe10 = Keyframe.ofFloat(1f, 0f)
        keyframe10.interpolator = BounceInterpolator()
        val valuesHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe0, keyframe2, keyframe4, keyframe6, keyframe8, keyframe10)

        val animator = ObjectAnimator.ofPropertyValuesHolder(tv_value_content, valuesHolder)
        animator.duration = 1000
        animator.start()

        tv_value_content.animate().translationX(100f).translationY(100f).rotation(90f).alpha(0.2f)
        var string: StringBuilder = StringBuilder()
        for (i in 1..3) {
            string.append(i).append(",")
        }
        for (i in 21..60) {
            string.append(i).append(",")
        }
        for (i in 81..100) {
            string.append(i).append(",")
        }
        for (i in 120..129) {
            string.append(i).append(",")
        }
        for (i in 200..230) {
            string.append(i).append(",")
        }
        string.append("9999")
        Log.d("hahah", "string: $string")
    }


    private fun doObjectFreeFall() {
        val point1 = Point(0, 0)
        val point2 = Point(600, 600)
        val ofObject = ObjectAnimator.ofObject(iv_free_fail, "fallingPos", FreeFallEvaluator(), point1, point2)
        ofObject.duration = 3000
        ofObject.start()


    }

    private fun doZRotation() {

        //Z
        val ofFloat = ObjectAnimator.ofFloat(tv_value_content, "rotation", 0f, 270f, 0f)
        ofFloat.duration = 3000
        ofFloat.start()

    }

    private fun doFreefail() {
        val point1 = Point(0, 0)
        val point2 = Point(600, 600)

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
        //这个颜色必须要16进制开头
        val ofInt = ValueAnimator.ofInt(0xffff0000.toInt(), 0xff00ff00.toInt(), 0xff0000ff.toInt())
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