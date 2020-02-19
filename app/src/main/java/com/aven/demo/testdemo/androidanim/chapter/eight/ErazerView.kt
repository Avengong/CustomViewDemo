package com.aven.demo.testdemo.androidanim.chapter.eight

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.aven.demo.testdemo.R

class ErazerView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f


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
        mPaint.style = Paint.Style.STROKE  //这是关键


    }

    var preX: Float = 0f
    var preY: Float = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {


            MotionEvent.ACTION_DOWN -> {
                mDstPah.moveTo(event.x, event.y)
                preX = event.x
                preY = event.y
                postInvalidate()
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


    override fun onDraw(canvas: Canvas?) {

//        canvas!!.drawColor(Color.RED)
        val s = "阳戌香这个宝宝菜，^_^"
        mPaint.textSize = 40f
        mPaint.strokeWidth = 5f
        val textLen = mPaint.measureText(s)
        canvas!!.drawText(s, width / 2f - textLen / 2f, height / 2f, mPaint)
        val saveLayer = canvas!!.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        mPaint.strokeWidth = 50f
        val w = width
        val h = w * height / width
        canvas.drawBitmap(mArrowBitmap, null, Rect(0, 0, w, h), mPaint)
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        canvas.drawPath(mDstPah, mPaint)
        mPaint.xfermode = null

        canvas.restoreToCount(saveLayer)


    }
}