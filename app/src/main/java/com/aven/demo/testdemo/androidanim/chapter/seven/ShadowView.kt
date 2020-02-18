package com.aven.demo.testdemo.androidanim.chapter.seven

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.aven.demo.testdemo.R

class ShadowView : View {


    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mDx: Float = 0f


    var mArrowBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()
    var mExtractAlphaBitmap: Bitmap? = null

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
        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_login)

        mExtractAlphaBitmap = mArrowBitmap!!.extractAlpha()
    }

    /**
     * 阴影效果
     */
    override fun onDraw(canvas: Canvas?) {

        canvas!!.drawColor(Color.BLUE)
        //简单阴影
//        mPaint.setShadowLayer(1f,10f,10f,Color.GREEN)
//        mPaint.textSize = 40f
        //发光效果 blurMaskFilter,
//        mPaint.maskFilter = BlurMaskFilter(80f, BlurMaskFilter.Blur.SOLID)


//        canvas!!.drawText("我是阴影文字", 100f, 100f, mPaint)
//        mPaint.style = Paint.Style.FILL
//        canvas.drawCircle(150f, 300f, 100f, mPaint)
        //        canvas.drawBitmap(mArrowBitmap, null, Rect(100, 400, 100 + mArrowBitmap!!.width,
//                400 + mArrowBitmap!!.height), mPaint)
        //给图片增加阴影
        //给图片设置纯色背景
        val width = 400
        val height = width * mExtractAlphaBitmap!!.height / mExtractAlphaBitmap!!.width
        mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
        //绘制黑色空白图像
        mPaint.color = Color.GREEN
        canvas.translate(width.toFloat(), 0f)

        canvas.drawBitmap(mExtractAlphaBitmap, null, Rect(10, 10, width, height), mPaint)
        canvas.translate(-10f, -10f)
        mPaint.maskFilter = null
        canvas.drawBitmap(mArrowBitmap, null, Rect(0, 0, width,
                height), mPaint)


    }
}