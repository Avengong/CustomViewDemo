package com.aven.demo.testdemo.androidanim.chapter.four

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.aven.demo.testdemo.R

class PahtLoaderView : View {

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


        mCirclePah.addCircle(100f, 100f, 50f, Path.Direction.CW)
        pathMeasure.setPath(mCirclePah, false)

        val valueCal = ValueAnimator.ofFloat(0f, 1f)

        valueCal.addUpdateListener {
            mFraction = it.animatedFraction

            invalidate()
        }
        valueCal.duration = 5000
        valueCal.repeatCount = ValueAnimator.INFINITE
        valueCal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {

            }
        })

        valueCal.start()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawColor(Color.YELLOW)

        mDstPah.reset()
        val stopD = mFraction * pathMeasure.length
        //普通的转圈效果
//        pathMeasure.getSegment(0f, mDx * pathMeasure.length, mDstPah, true)
        //超过一半后的追赶转圈
        var startD = getStart()
        pathMeasure.getSegment(startD * pathMeasure.length, stopD, mDstPah, true)
        canvas!!.drawPath(mDstPah, mPaint)

        //2，增加箭头
        pathMeasure.getPosTan(stopD, mPosFloat, mTanFloat)

        //利用矩阵来控制边上图形的位置和角度

        mMetrics.reset()

        val degree = Math.atan2(mTanFloat[1].toDouble(), mTanFloat[0].toDouble()) * 180 / Math.PI
        Log.d("loadview", " degree:$degree")
//        mMetrics.postRotate((degree-180).toFloat(),mArrowBitmap!!.width/2.toFloat(),
//                mArrowBitmap!!.height/2.toFloat())
//        //平移必须在旋转后！！！！
//        mMetrics.postTranslate(mPosFloat[0] - mArrowBitmap!!.width / 2,
//                mPosFloat[1] - mArrowBitmap!!.height / 2)

        //直接通过矩阵---调试通过
        pathMeasure.getMatrix(stopD, mMetrics, PathMeasure.POSITION_MATRIX_FLAG or PathMeasure.TANGENT_MATRIX_FLAG)
        mMetrics.preRotate(180f) //nb ！！通过了
        //这里注意是pre，跟posttranslate有何关系
        mMetrics.preTranslate(-(mArrowBitmap!!.width / 2).toFloat(), -
        (mArrowBitmap!!.height / 2).toFloat())

        canvas.drawBitmap(mArrowBitmap, mMetrics,
                mPaint)


    }

    private fun getStart(): Float {
        return if (mFraction < 0.5) {
            0f
        } else {
            2 * mFraction - 1
        }

    }
}