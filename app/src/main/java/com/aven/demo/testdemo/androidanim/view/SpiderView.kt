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
    val gapRadius = 40
    var mPath = Path()
    var mDataPath = Path()
    //技能级别
    private val mdata = intArrayOf(6, 2, 3, 1, 5, 3)

    //继承三个构造方法
    constructor (context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mList.clear()
        for (i in 1..mSides) {//1  2  ..6
            var spiderPoint = SpiderPoint()
            spiderPoint.mX = (mCenterX + mRadius * Math.cos((mAngel * i))).toFloat()
            spiderPoint.mY = (mCenterY + mRadius * Math.sin((mAngel * i))).toFloat()
            mList.add(spiderPoint)
//            Log.d(TAG, "spiderPointX: ${spiderPoint.mX} spiderPointY:${spiderPoint.mY} i:$i")
        }
//        postInvalidate()
    }

    private fun init() {
        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 4f
        mPaint.color = Color.BLACK
        mAngel = Math.PI * 2 / mSides
//        mAngel = 60.0


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCenterX = width / 2
        mCenterY = height / 2
        mRadius = (Math.min(mCenterX, mCenterY) / 2 * 0.9f)
        Log.d(TAG, " centx:$mCenterX  centy:$mCenterY ")
        //绘制网格

        mPath.reset()
        mPaint.style = Paint.Style.STROKE
        for (k in 0..5) {
            var changeRadius = mRadius - k * gapRadius
            Log.d(TAG, " k:$k  ")
            for (i in 0 until mSides) {
                if (i == 0) {
                    mPath.moveTo(mCenterX + changeRadius.toFloat(), mCenterY.toFloat())
                } else {
                    var x = 0f
                    var y = 0f
                    val angel = mAngel * i
                    x = (mCenterX + changeRadius * Math.cos(-angel)).toFloat()
                    y = (mCenterY + changeRadius * Math.sin(-angel)).toFloat()
//                    Log.d(TAG, " i:$i  angle:$angel cos:${mRadius * Math.cos(angel)}  sin:${mRadius * Math.sin(angel)}")
                    mPath.lineTo(x, y)

                }
            }
            mPath.close()
            canvas!!.drawPath(mPath, mPaint)
        }

        //绘制网格中线
//        mPath.moveTo(mCenterX.toFloat(), mCenterY.toFloat())
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 33f
        for (i in 0 until mSides) {
            val angel = mAngel * i
            var x = (mCenterX + mRadius * Math.cos(-angel)).toFloat()
            var y = (mCenterY + mRadius * Math.sin(-angel)).toFloat()
//                    Log.d(TAG, " i:$i  angle:$angel cos:${mRadius * Math.cos(angel)}  sin:${mRadius * Math.sin(angel)}")
            canvas!!.drawLine(mCenterX.toFloat(), mCenterY.toFloat(), x, y, mPaint)
//            canvas.drawText("技能$i", x, y, mPaint)

            val str = "abcd"
            mPaint.textAlign = Paint.Align.RIGHT
            canvas.drawText(str, 0, str.length, x, y, mPaint)
        }
        //画数据-4
        mPaint.style = Paint.Style.FILL
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 20f
        mDataPath.reset()
        for (i in 0 until mSides) {
            var changeRadius = mRadius - (mSides - mdata[i]) * gapRadius
            val angel = mAngel * i
            var x = (mCenterX + changeRadius * Math.cos(-angel)).toFloat()
            var y = (mCenterY + changeRadius * Math.sin(-angel)).toFloat()
            canvas!!.drawCircle(x, y, 10f, mPaint)
            if (i == 0) {
                mDataPath.moveTo(x, y)
            } else {
                mDataPath.lineTo(x, y)
            }
        }
        mDataPath.close()
        mPaint.color = Color.GREEN
        mPaint.alpha = 100
        canvas!!.drawPath(mDataPath, mPaint)

    }

    class SpiderPoint {
        var mX: Float = 0f
        var mY: Float = 0f
    }


}