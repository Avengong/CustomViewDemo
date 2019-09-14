package com.aven.demo.testdemo.androidanim.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import android.widget.Toast
import com.aven.demo.testdemo.R
import kotlinx.android.synthetic.main.circle_menu_view.view.*

/**
 * Created by ${Aven.Gong} on 2019/8/26 0026.
 */
class CircleMenuView : FrameLayout {

    var mRadius = 40
    var mWidth: Int = 0
    val mRadiusFraction = 0.7f
    lateinit var mIconsContainer: FrameLayout
    var mIsShow: Boolean = false
    var mIconNum: Int = 5
    var mSpaceNum = mIconNum - 1
    var mEqualtDegree = Math.toRadians(90.0) / mSpaceNum

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        LayoutInflater.from(context).inflate(R.layout.circle_menu_view, this, true)
        mIconsContainer = findViewById(R.id.flt_icons)
        initIcons()
        civ_arrange_click.setOnClickListener { doShowArrangeUi() }


    }

    private fun initIcons() {


        for (i in 0..mSpaceNum) {

            var circleMenuView = CircleImageView(context)

            var lp = FrameLayout.LayoutParams(80, 80)
            lp.gravity = Gravity.BOTTOM or Gravity.RIGHT
            circleMenuView.setBackgroundResource(R.mipmap.ic_launcher)
            //注意父容器
            circleMenuView.setOnClickListener {
                Toast.makeText(context, "我是第 ${i} 个icon哦 ", Toast.LENGTH_SHORT).show()
            }
            mIconsContainer.addView(circleMenuView, lp)

        }


    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
        mRadius = width
    }

    private fun doShowArrangeUi() {

        if (!mIsShow) {
            showIcons()
        } else {
            hiddenIcons()
        }

        mIsShow = !mIsShow

    }

    private fun showIcons() {

        var originX = 0f
        var originY = 0f


        for (i in 0..mSpaceNum) {

            var circleMenuView = mIconsContainer.getChildAt(i)

            var toX = -mRadius * mRadiusFraction * Math.sin(i * mEqualtDegree)
            var toY = -mRadius * mRadiusFraction * Math.cos(i * mEqualtDegree)

//            Log.d("circleMenuView", "width: $width, height:$height " +
//                    "mEqualtDegree:$mEqualtDegree originx:$originX, originY:$originY tox:$toX, toY:$toY")

            val translationX = ObjectAnimator.ofFloat(circleMenuView, "translationX", originX, toX.toFloat())
            val translationY = ObjectAnimator.ofFloat(circleMenuView, "translationY", originY, toY.toFloat())
            val alpha = ObjectAnimator.ofFloat(circleMenuView, "alpha", 0f, 1f)
            val scalex = ObjectAnimator.ofFloat(circleMenuView, "scaleX", 0f, 1f)
            val scaley = ObjectAnimator.ofFloat(circleMenuView, "scaleY", 0f, 1f)


            var animatorSet = AnimatorSet()
            animatorSet.interpolator = BounceInterpolator()
            animatorSet.duration = 1000
            animatorSet.playTogether(translationX, translationY, alpha, scalex, scaley)
            animatorSet.start()

        }

    }

    private fun hiddenIcons() {

        var originX = 0f
        var originY = 0f


        for (i in 0..mSpaceNum) {

            var circleMenuView = mIconsContainer.getChildAt(i)

            var toX = -mRadius * mRadiusFraction * Math.sin(i * mEqualtDegree)
            var toY = -mRadius * mRadiusFraction * Math.cos(i * mEqualtDegree)

//            Log.d("circleMenuView", "width: $width, height:$height " +
//                    "mEqualtDegree:$mEqualtDegree originx:$originX, originY:$originY tox:$toX, toY:$toY")

            val translationX = ObjectAnimator.ofFloat(circleMenuView, "translationX", toX.toFloat
            (), originX)
            val translationY = ObjectAnimator.ofFloat(circleMenuView, "translationY", toY.toFloat
            (), originY)
            val alpha = ObjectAnimator.ofFloat(circleMenuView, "alpha", 1f, 0f)
            //scalex 到0有坑？？

            val scalex = ObjectAnimator.ofFloat(circleMenuView, "scaleX", 1f, 0f)
            val scaley = ObjectAnimator.ofFloat(circleMenuView, "scaleY", 1f, 0f)

            var animatorSet = AnimatorSet()
            animatorSet.interpolator = AccelerateInterpolator()
            animatorSet.duration = 200
            animatorSet.playTogether(translationX, translationY, alpha, scalex, scaley)
            animatorSet.start()

        }


    }


}