package com.aven.demo.testdemo.androidanim.chapter.four

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import com.aven.demo.testdemo.R

/**
 * 实现思路：
 * 包含两个部分：1，外圆路径；2，内部的勾勾
 * 利用pathMeasure的nextContour函数进行遍历
 * 利用pathMeasure的getSegment方法绘制路径
 *关键点：
 * 如何确定path多少条路径
 * mdstpath 是否需要重置
 * 这是一次性，不做无线循环
 *
 */
class AlipayView : View {


    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mAliPaylePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f


    var mArrowBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()
    val mRadius: Float = 50f
    var mCentX: Float = 100f
    var mCentY: Float = 100f


    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 4f
        mPaint.style = Paint.Style.STROKE


    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init()


    }


    private fun init() {

        setOnTouchListener(OnTouchListener { v, event ->

            init()
            false

        })


        setLayerType(LAYER_TYPE_SOFTWARE, null)

        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.left_arrow)

        mDstPah.reset()
        mNext = false
        mAliPaylePah.addCircle(mCentX, mCentY, mRadius, Path.Direction.CW)
        mAliPaylePah.moveTo(mCentX - mRadius / 2, mCentY)
        mAliPaylePah.lineTo(mCentX, mCentY + mRadius / 2f)
        mAliPaylePah.lineTo(mCentX + mRadius * 2 / 3f, mCentY - mRadius / 2)
        pathMeasure.setPath(mAliPaylePah, false)


        val valueCal = ValueAnimator.ofFloat(0f, 2f)

        valueCal.addUpdateListener {
            mFraction = it.animatedValue as Float //注意value，采坑了

            invalidate()
        }
        valueCal.duration = 3000
//        valueCal.repeatCount = ValueAnimator.INFINITE
        valueCal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {

            }
        })

        valueCal.start()


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    var mNext: Boolean = false
    override fun onDraw(canvas: Canvas?) {

        canvas!!.drawColor(Color.GRAY)


        var startD: Float
        var stopD: Float
        if (mFraction <= 1) {
            Log.d("alipayview", " mfraction <-= 1,lengh:${pathMeasure.length} ")
            startD = 0f
            stopD = pathMeasure.length * mFraction
            pathMeasure.getSegment(startD, stopD, mDstPah, true)
        } else {
            //疑问：以后再有其他的path加进来怎么办？

            if (!mNext) {
                mNext = true
                Log.d("alipayview", " mfraction > 1,lenth:${pathMeasure.length} ")

                startD = 0f
                stopD = pathMeasure.length
                pathMeasure.getSegment(startD, stopD, mDstPah, true)
                pathMeasure.nextContour()
            }

            pathMeasure.getSegment(0f, pathMeasure.length * (mFraction - 1), mDstPah, true)


        }
        canvas!!.drawPath(mDstPah, mPaint)


    }
}