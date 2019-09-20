package com.aven.demo.testdemo.androidanim.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Created by ${Aven.Gong} on 2019/9/20 0020.
 */
class GetSegmentView : View {


    var mPath = Path()
    var mDstPath = Path()
    var mPaint = Paint()
    var mPathMeasure = PathMeasure()
    var mFraction: Float = 0f


    constructor (context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context,
            attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        mPaint.strokeWidth = 4f

        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener { animation ->
            val animatedFraction = animation.animatedFraction
            val animatedValue = animation.animatedValue
            mFraction = animatedFraction
            Log.d("GetSegmentView", "animatedFraction:$animatedFraction, animatedValue:$animatedValue")
            invalidate()

        }
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 2000
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.start()

    }

    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawLine(0f, 0f, width.toFloat(), 0f, mPaint)
        canvas.drawLine(0f, 0f, 0f, height.toFloat(), mPaint)
        canvas.translate(100f, 100f)
        mPaint.color = Color.BLACK
        canvas!!.drawLine(0f, 0f, width.toFloat(), 0f, mPaint)
        canvas.drawLine(0f, 0f, 0f, height.toFloat(), mPaint)
        mPath.addCircle(0f, 0f, 50f, Path.Direction.CW)
        mPathMeasure.setPath(mPath, true)
        val length = mPathMeasure.length
        Log.d("GetSegmentView", "mPathMeasure lenth:$length")
        mDstPath.reset()
        var stopD = mFraction * length
        var startD = getStartD(mFraction) * length
//        var startD = stopD - (0.5 - Math.abs(mFraction - 0.5)) * length
        mPathMeasure.getSegment(startD.toFloat(), stopD, mDstPath, true)
        canvas.drawPath(mDstPath, mPaint)

    }

    private fun getStartD(mFraction: Float): Float {

        return if (mFraction < 0.5) {
            0f
        } else {
            //慢慢从0开始逐渐接近终点。此时不要考虑stop
            2f * mFraction - 1
        }

    }

}