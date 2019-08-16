package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by ${Aven.Gong} on 2019/8/9 0009.
 */
class FingerRedView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val rect = Rect(0, 0, 100, 100)
    var mX: Int = -1
    var mY: Int = -1

    init {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL
        if (rect.contains(mX, mY)) {
            paint.color = Color.RED
        } else {
            paint.color = Color.GRAY
        }

        canvas!!.drawRect(rect, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when {
            event!!.action == MotionEvent.ACTION_DOWN -> {
                mX = event.x.toInt()
                mY = event.y.toInt()
                invalidate()
                return true
            }
            event.action == MotionEvent.ACTION_UP -> {
                mX = -1
                mY = -1
                postInvalidate()
            }
            event.action == MotionEvent.ACTION_MOVE -> {

            }
        }
        return super.onTouchEvent(event)

    }

}