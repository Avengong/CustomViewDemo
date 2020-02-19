package com.aven.demo.testdemo.androidanim.chapter.eight

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.aven.demo.testdemo.R

class TextWave_DSTINView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f

    var mDx: Float = 0f
    var mArrowBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()

    lateinit var mSrcBitmap: Bitmap
    lateinit var mDstBitmap: Bitmap


    init {
        mPaint.color = Color.RED
    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init()
    }

    /**
     * 需求：橡皮擦效果
     * 思路：让图像变成透明，利用src——out
     *
     */
    private fun init() {

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_login)
        mPaint.color = Color.RED
        mPaint.strokeWidth = 40f  //这是关键
        mPaint.style = Paint.Style.FILL  //这是关键

        mSrcBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        mDstBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)

        val valueCal = ValueAnimator.ofFloat(0f, 200f)

        valueCal.addUpdateListener {
            mDx = it.animatedValue as Float
            it.animatedValue

            invalidate()
        }
        valueCal.interpolator = LinearInterpolator()
        valueCal.duration = 3000
        valueCal.repeatCount = ValueAnimator.INFINITE
        valueCal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {

            }
        })

        valueCal.start()


    }

    override fun onDraw(canvas: Canvas?) {


        //创建波纹
        generateWave()
        mPaint.color = Color.GREEN

        val canvas1 = Canvas(mDstBitmap)
        canvas1.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        canvas1.drawPath(mDstPah, mPaint)
        //创建文字
        val canvas2 = Canvas(mSrcBitmap)
        mPaint.textSize = 60f
        val s = "阳戌香这个宝宝菜，^_^"
        mPaint.textSize = 40f
        mPaint.strokeWidth = 5f
        val textLen = mPaint.measureText(s)
        canvas2.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        canvas2.drawText(s, 100 - textLen / 2, 100f, mPaint)

        val saveLayer = canvas!!.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(mDstBitmap, 100f, 100f, mPaint)
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas.drawBitmap(mSrcBitmap, 100f, 100f, mPaint)
        mPaint.xfermode = null

        canvas.restoreToCount(saveLayer)


    }

    private fun generateWave() {

        val originY = 100f
        mDstPah.reset()

        val mWaveWidth = 150f
        val mWaveHeight = 10f
        val halfWaveLen = mWaveWidth / 2
        mDstPah.moveTo(-mWaveWidth + mDx, originY)
        var i = -mWaveWidth
        while (i <= (width + mWaveWidth)) {
            i += mWaveWidth

            mDstPah.rQuadTo(halfWaveLen / 2, -mWaveHeight, halfWaveLen, 0f)
            mDstPah.rQuadTo(halfWaveLen / 2, mWaveHeight, halfWaveLen, 0f)

        }

        mDstPah.lineTo(width.toFloat(), height.toFloat())
        mDstPah.lineTo(0f, height.toFloat())
        mDstPah.close()


    }


}