package com.aven.demo.testdemo.androidanim.chapter.seven

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.aven.demo.testdemo.R

/**
 * 手势路径利用quadTo优化
 *
 * 手指一动，那么我就绘制路径
 *
 *
 */
class GestureOptimizeView : View {

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


        canvas!!.drawPath(mDstPah, mPaint)

    }

    var preX: Float = 0f
    var preY: Float = 0f

    var moveX: Float = 0f
    var moveY: Float = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {


            MotionEvent.ACTION_DOWN -> {
                mDstPah.moveTo(event.x, event.y)
                preX = event.x
                preY = event.y
                return true
            }

            MotionEvent.ACTION_MOVE -> {


                //问题出在哪里？ 减号为啥不行？
                /**
                 * 原因：这是求中点坐标，不是求两者的差值
                 */

                Log.d(this::class.java.name, " prex:$preX, prey:$preY, movex:${event.x}, " +
                        "movey:${event.y}")
                val endx = (event.x + preX) / 2
                val endy = (event.y + preY) / 2

                mDstPah.quadTo(preX, preY, endx, endy)
                preX = event.x
                preY = event.y

                postInvalidate()
            }

            MotionEvent.ACTION_UP -> {


            }

        }

        return super.onTouchEvent(event)
    }

}