package com.aven.demo.testdemo.androidanim.chapter.nineandten

import android.graphics.*
import android.graphics.drawable.Drawable

class CustomDrawable : Drawable() {
    override fun draw(canvas: Canvas) {


    }

    override fun setAlpha(alpha: Int) {


    }

    override fun getOpacity(): Int {

        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f


    var mArrowBitmap: Bitmap? = null
    var mBgBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()


    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 4f
//        mPaint.style = Paint.Style.STROKE


    }


}