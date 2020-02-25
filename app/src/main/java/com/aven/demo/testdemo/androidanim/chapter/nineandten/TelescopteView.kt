package com.aven.demo.testdemo.androidanim.chapter.nineandten

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.aven.demo.testdemo.R

/**
 * 望远镜
 * 1,先按照控件的比例缩放绘制原图
 * 2，创建bitmapShader对象，把放到后的倍数图设置给shader。
 * 3，设置shapedrawable的paint的shader，
 * 4，因为shapedrawable是不能移动的，所以只能根据手指按下的位置x、y，以及放到的倍数和显示半径，来确定shader的移动距离。最后在确定移动距离后在重绘。
 *
 *
 */
class TelescopteView : View {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mCirclePah = Path()
    val mDstPah = Path()
    val pathMeasure = PathMeasure()
    var mFraction: Float = 0f


    var mArrowBitmap: Bitmap? = null
    var mBgBitmap: Bitmap? = null
    internal var mPosFloat = FloatArray(2)
    var mTanFloat = FloatArray(2)
    val mMetrics = Matrix()


    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 4f
//        mPaint.style = Paint.Style.STROKE


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


    }

    val SCALE: Int = 3
    val RADIUS: Int = 150
    var bitmapShader: BitmapShader? = null
    var mDrawable: ShapeDrawable? = null
    internal var mMatrix = Matrix()


    override fun onTouchEvent(event: MotionEvent?): Boolean {


        val dx = event!!.x
        val dy = event.y

        //移动shader
        mMatrix.setTranslate(-SCALE * dx + RADIUS, -SCALE * dy + RADIUS)

        bitmapShader?.setLocalMatrix(mMatrix)

        val left = dx.toInt()
        val top = dy.toInt() - RADIUS * 2
        mDrawable?.setBounds(left, top, left + RADIUS * 2, top + RADIUS * 2)
        when (event.action) {

            MotionEvent.ACTION_UP -> {
                //抬起后消失
                mDrawable?.setBounds(0, 0, 0, 0)
            }

        }
        postInvalidate()
        return true
    }


    override fun onDraw(canvas: Canvas?) {

        if (mBgBitmap == null) {

            mBgBitmap = Bitmap.createScaledBitmap(mArrowBitmap, width, height, false)

            bitmapShader = BitmapShader(Bitmap.createScaledBitmap(mBgBitmap, width * SCALE, height * SCALE, false),
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            mDrawable = ShapeDrawable(OvalShape())
            mDrawable!!.paint.shader = bitmapShader
            mDrawable?.setBounds(0, 0, RADIUS * 2, RADIUS * 2)

        }

        canvas!!.drawBitmap(mBgBitmap, 0f, 0f, mPaint)

        mDrawable!!.draw(canvas)


        /* //利用mpaint直接画圆好像也可以，为啥要用drawable呢？？
         mPaint.shader=bitmapShader
         canvas!!.drawCircle(width/2f,width/2f,width/2f,mPaint)
 */

    }


}