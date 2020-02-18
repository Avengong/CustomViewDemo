package com.aven.demo.testdemo.androidanim.chapter.seven

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.aven.demo.testdemo.R

class QuadToView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f


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


    }


    override fun onDraw(canvas: Canvas?) {

        canvas!!.drawColor(Color.GREEN)
        //简单的贝塞尔二阶曲线
        val halfH = height / 2f
        mDstPah.moveTo(0f, halfH)
        mDstPah.quadTo(100f, halfH / 2, 200f, halfH)
        mDstPah.quadTo(300f, halfH * 3 / 2, 400f, halfH)

        //手势view捕捉优化

        canvas!!.drawPath(mDstPah, mPaint)


    }
}