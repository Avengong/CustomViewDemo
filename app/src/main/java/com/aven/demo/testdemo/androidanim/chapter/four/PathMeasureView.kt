package com.aven.demo.testdemo.androidanim.chapter.four

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PathMeasureView @JvmOverloads constructor(context: Context, attributes: AttributeSet,
                                                defStyleAttr: Int = 0)
    : View(context, attributes, defStyleAttr) {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 4f
        mPaint.style = Paint.Style.STROKE

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawColor(Color.RED)

        canvas.translate(100f, 100f)
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, mPaint)
        canvas.drawLine(0f, 0f, 0f, height.toFloat(), mPaint)

        val rect = Path()
        rect.addRect(-50f, -50f, 50f, 50f, Path.Direction.CCW)

        val dst = Path()

        //dst 不为空情况
        dst.addCircle(width / 4f, height / 4f, width / 4f, Path.Direction.CCW)

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(rect, false)

        pathMeasure.getSegment(0f, 150f, dst, true)

        canvas.drawPath(dst, mPaint)


    }


}