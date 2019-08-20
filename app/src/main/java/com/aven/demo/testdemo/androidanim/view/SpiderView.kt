package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by ${Aven.Gong} on 2019/8/14 0014.
 */
class SpiderView : View {

    private val TAG: String = "SpiderView"
    private lateinit var mPaint: Paint
    private var mCenterX: Int = 0
    private var mCenterY: Int = 0
    private var mRadius: Float = 0f
    private var mSides: Int = 6
    private var mAngel: Double = 0.0
    private var mList = ArrayList<SpiderPoint>()

    //继承三个构造方法
    constructor (context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    init {
        init()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = w / 2
        mCenterY = h / 2
        mRadius = (Math.min(mCenterX, mCenterY) / 2 * 0.9f)
        mList.clear()
        for (i in 1..mSides) {//1  2  ..6
            var spiderPoint = SpiderPoint()
            spiderPoint.mX = (mCenterX + mRadius * Math.cos((mAngel * i))).toFloat()
            spiderPoint.mY = (mCenterY + mRadius * Math.sin((mAngel * i))).toFloat()
            mList.add(spiderPoint)
            Log.d(TAG, "spiderPointX: ${spiderPoint.mX} spiderPointY:${spiderPoint.mY} i:$i")
        }
        postInvalidate()
    }

    private fun init() {
        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4f
        mPaint.color = Color.BLACK
        mAngel = 360.0 / mSides
        mAngel = 60.0

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCenterX = width / 2
        mCenterY = height / 2
        mRadius = Math.min(mCenterX, mCenterY) / 2 * 0.5f

        var path = Path()
        path.reset()
        for (i in 0 until mSides) {
            if (i == 0) {
                path.moveTo(mCenterX + mRadius, mCenterY.toFloat())
            } else {
                if (i == 1 || i == 2) {
                    var x: Float = (mCenterX + mRadius * Math.cos((mAngel * i))).toFloat()
                    var y: Float = (mCenterY + mRadius * Math.sin((mAngel * i))).toFloat()
                    Log.d(TAG, " i:$i  angle:${mAngel * i} cos:${mRadius * Math.cos(mAngel * i)}  sin:${mRadius * Math.sin(mAngel * i)}")
                    path.lineTo(x, y)
                }
            }
        }
//        path.close()
        canvas?.drawPath(path, mPaint)
    }


    class SpiderPoint {
        var mX: Float = 0f
        var mY: Float = 0f
    }

}