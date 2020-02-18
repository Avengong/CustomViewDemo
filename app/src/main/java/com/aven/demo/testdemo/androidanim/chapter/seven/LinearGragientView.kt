package com.aven.demo.testdemo.androidanim.chapter.seven

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LinearGragientView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mDX: Float = 100f
    var mDY: Float = 100f

    var mArrowBitmap: Bitmap? = null
    var mEmptyBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()
    var mExtractAlphaBitmap: Bitmap? = null
    var mBitmapShader: BitmapShader? = null

    init {
//        mPaint.color = Color.BLACK
//        mPaint.strokeWidth = 4f
//        mPaint.style = Paint.Style.FILL


    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init()
    }

    private fun init() {

        setLayerType(LAYER_TYPE_SOFTWARE, null)


    }

    override fun onDraw(canvas: Canvas?) {

//        mPaint.shader=LinearGradient(0f,height/2f,width.toFloat(),height/2f,Color.RED,Color.GREEN,Shader
//                .TileMode
//                .REPEAT)

        mPaint.shader = LinearGradient(0f, height / 2f, width.toFloat(), height / 2f, intArrayOf(Color.RED, Color.BLUE, Color
                .GREEN), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.REPEAT)
        mPaint.textSize = 40f

//        canvas!!.drawRect(0f,0f,width.toFloat(),height.toFloat(),mPaint)
        val s = "我是测试文字"
        val testLen = mPaint.measureText(s)
        /**
         * 静态文字闪光效果
         */
        canvas!!.drawText(s, width / 2f - testLen / 2f, height / 2f, mPaint)
        canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), mPaint)


    }
}