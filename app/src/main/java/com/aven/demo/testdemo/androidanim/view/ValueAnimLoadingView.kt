package com.aven.demo.testdemo.androidanim.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

class ValueAnimLoadingView : ImageView {


    var mTop: Int = 0
    var mBottom: Int = 0
    private var ofFloat: ValueAnimator

    constructor (context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context,
            attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {

        ofFloat = ValueAnimator.ofFloat(0f, 100f, 0f)

        ofFloat.duration = 2000
        ofFloat.repeatCount = ValueAnimator.INFINITE
        ofFloat.repeatMode = ValueAnimator.REVERSE
        ofFloat.interpolator = AccelerateDecelerateInterpolator()
        ofFloat.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            //方法一 这个不会导致重新布局,只会重绘
//            top = mTop - animatedValue.toInt()
//            bottom = mBottom - animatedValue.toInt()
            //方法二
            translationY = -animatedValue //一样的效果


            Log.d("weizhi", "top:$top, bottom:$bottom ,animvalue: ${animatedValue.toInt()}")
//            if (mTop>0){
//这个会导致onlayout，重新布局
//            layout(left,mTop-animatedValue.toInt(),right,mBottom-animatedValue.toInt())
//
//            }
        }

        ofFloat.start()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("weizhi", "onDraw  top:$top, bottom:$bottom ")
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mTop = top
        mBottom = bottom
        Log.d("weizhi", "---------------onLayout----top:$mTop, bottom:$mBottom")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ofFloat.removeAllUpdateListeners()
    }

}

