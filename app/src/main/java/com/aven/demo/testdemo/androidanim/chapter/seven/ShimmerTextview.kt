package com.aven.demo.testdemo.androidanim.chapter.seven

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView

//发光文字view
class ShimmerTextview : TextView {

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
    var mPaint: Paint? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
            attrs, defStyleAttr) {
        init()
    }

    private fun init() {

        setLayerType(View.LAYER_TYPE_SOFTWARE, null)


        mPaint = paint
        val textLen = mPaint!!.measureText(text.toString())
        createValueAnim(textLen)

        createLinearGradient(textLen)

    }

    lateinit var mLinearGradient: LinearGradient
    private fun createLinearGradient(textLen: Float) {

        /**
         * 文字原色--绿色--文字原色
         */
        mLinearGradient = LinearGradient(-textLen, 0f, 0f, 0f, intArrayOf(currentTextColor, Color.GREEN,
                currentTextColor), floatArrayOf(0f, 0.5f, 1f), Shader.TileMode.CLAMP)
    }

    private fun createValueAnim(textLen: Float) {
        val value = ValueAnimator.ofFloat(0f, 2 * textLen)
        value.addUpdateListener {
            mDX = it.animatedValue as Float

            postInvalidate()
        }
        value.repeatCount = ValueAnimator.INFINITE

        value.duration = 2000
        value.interpolator = LinearInterpolator()
        value.start()

    }

    /**
     * 动态文字闪光效果，如何实现？
     * shader的原理就是你画什么就显示什么。前提是我的画笔已经被着色了。
     * 画笔着色什么效果（比如：bitmapShader位图，线性渐变、发散渐变），画什么形状就显示什么形状。
     * 因此，我只要一动文字后面的渐变着色，是不是就可以实现动态闪光效果呢？
     * 关键如何移动？ 关于一个类怎么实现某个功能，应该从它提供的API入手，shader类有两个方法setLocalMatrix（），所以可以通过矩阵来完成
     */

    override fun onDraw(canvas: Canvas?) {

        val matrix = Matrix()

        matrix.setTranslate(mDX, 0f)
        mLinearGradient.setLocalMatrix(matrix)
        mPaint!!.shader = mLinearGradient

        super.onDraw(canvas)

    }

}