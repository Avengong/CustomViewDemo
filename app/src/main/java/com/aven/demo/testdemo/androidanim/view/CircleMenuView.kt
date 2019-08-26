package com.aven.demo.testdemo.androidanim.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.aven.demo.testdemo.R
import kotlinx.android.synthetic.main.circle_menu_view.view.*

/**
 * Created by ${Aven.Gong} on 2019/8/26 0026.
 */
class CircleMenuView : FrameLayout {

    var mRadius = 40
    var mWidth: Int = 0

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        LayoutInflater.from(context).inflate(R.layout.circle_menu_view, this, true)

        civ_arrange_click.setOnClickListener { doShowArrangeUi() }
    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
    }

    private fun doShowArrangeUi() {

        var originX = civ_arrange_click.x
        var originY = civ_arrange_click.y

        var equaltDegree = Math.toRadians(90.0) / 4

        var toX = mWidth - mRadius * Math.sin(1 * equaltDegree)
        var toY = mWidth - mRadius * Math.cos(1 * equaltDegree)
        Log.d("circleMenuView", "width: $width, height:$height equaltDegree:$equaltDegree originx:$originX, originY:$originY tox:$toX, toY:$toY")
        val ofInt = ObjectAnimator.ofInt(civ_arrange_1, "translationX", originX.toInt(), x.toInt())
        ofInt.duration = 2000
        ofInt.interpolator = AccelerateDecelerateInterpolator()
        ofInt.start()

        var animatorSet = AnimatorSet()
//        animatorSet.play().with()

//        for (i in 0 until 5) {
//            var x = mRadius * Math.sin(i * equaltDegree)
//            var y = mRadius * Math.cos(i * equaltDegree)


//        }


    }


}