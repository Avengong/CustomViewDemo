package com.aven.demo.testdemo.androidanim.chapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.*
import com.aven.demo.testdemo.R
import kotlinx.android.synthetic.main.chapter_two_activity.*

class ChapterTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_two_activity)

        btn_bounceInterpolator.setOnClickListener {

            doBounceInterpolator()
        }

        btn_anticipateInterpolator.setOnClickListener {
            doAnticipateInterpolator()
        }

        btn_overoneshotInterpolator.setOnClickListener {
            doOverOneShot()
        }
        btn_antiOvershootInterpolator.setOnClickListener {
            doAntiOverShoot()
        }
        btn_cyclerInterpolator.setOnClickListener {
            doCycle()
        }
        btn_camera_scale.setOnClickListener {
            doScale()
        }

    }

    private fun doScale() {

        val scalAnimation = ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f)
        scalAnimation.duration = 3000
        scalAnimation.fillAfter = true
        scalAnimation.interpolator = AccelerateDecelerateInterpolator()
        iv_camera_scale.startAnimation(scalAnimation)
        scalAnimation.start()


    }

    private fun doCycle() {
        val translation = getTranslation()
        translation.interpolator = CycleInterpolator(4f)
        tv_anim_text.startAnimation(translation)
        translation.start()

    }

    private fun doAntiOverShoot() {
        val translation = getTranslation()
        translation.interpolator = AnticipateOvershootInterpolator(4f)
        tv_anim_text.startAnimation(translation)
        translation.start()

    }

    private fun doOverOneShot() {
        val translation = getTranslation()
        translation.interpolator = OvershootInterpolator(4f)
        tv_anim_text.startAnimation(translation)
        translation.start()

    }

    private fun doAnticipateInterpolator() {

        val translation = getTranslation()
        translation.interpolator = AnticipateInterpolator(3f)
        tv_anim_text.startAnimation(translation)
        translation.start()
    }

    private fun doBounceInterpolator() {


        val translateAnimation = getTranslation()
        translateAnimation.interpolator = BounceInterpolator()
        tv_anim_text.startAnimation(translateAnimation)

        translateAnimation.start()

    }

    private fun getTranslation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(10f, 300f, 0f, 0f)

        translateAnimation.duration = 2000


        translateAnimation.fillAfter = true
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {


            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
                tv_anim_text.visibility = View.VISIBLE
            }


        })
        return translateAnimation
    }
}