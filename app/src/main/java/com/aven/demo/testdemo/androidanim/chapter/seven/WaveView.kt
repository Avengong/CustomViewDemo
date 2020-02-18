package com.aven.demo.testdemo.androidanim.chapter.seven

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.aven.demo.testdemo.R

class WaveView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mDx: Float = 0f


    var mArrowBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()


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

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.left_arrow)

        val valueCal = ValueAnimator.ofFloat(0f, mWaveWidth)

        valueCal.addUpdateListener {
            mDx = it.animatedValue as Float
            it.animatedValue

            invalidate()
        }
        valueCal.interpolator = LinearInterpolator()
        valueCal.duration = 1000
        valueCal.repeatCount = ValueAnimator.INFINITE
        valueCal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {

            }
        })

        valueCal.start()
    }


    var mWaveHeight: Float = 50f
    var mWaveWidth: Float = 300f
    var mBaseHeight: Float = 0f


    /**
     *水波纹效果
     *
     * 如何实现
     *
     */
    override fun onDraw(canvas: Canvas?) {

        canvas!!.drawColor(Color.WHITE)
        mPaint.style = Paint.Style.FILL
        mBaseHeight = height * 1 / 3f
        mDstPah.reset()

        mPaint.color = Color.GREEN
        val halfWaveLen = mWaveWidth / 2


        mDstPah.moveTo(-mWaveWidth + mDx, mBaseHeight)

        var i = -mWaveWidth
        while (i <= (width + mWaveWidth)) {
            i += mWaveWidth

            mDstPah.rQuadTo(halfWaveLen / 2, -mWaveHeight, halfWaveLen, 0f)
            mDstPah.rQuadTo(halfWaveLen / 2, mWaveHeight, halfWaveLen, 0f)

        }

        mDstPah.lineTo(width.toFloat(), height.toFloat())
        mDstPah.lineTo(0f, height.toFloat())
        mDstPah.close()

        canvas.drawPath(mDstPah, mPaint)

    }


}