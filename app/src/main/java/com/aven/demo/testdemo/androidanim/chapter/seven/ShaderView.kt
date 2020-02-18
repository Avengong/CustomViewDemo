package com.aven.demo.testdemo.androidanim.chapter.seven

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.aven.demo.testdemo.R

class ShaderView : View {


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
        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_login)

        mExtractAlphaBitmap = mArrowBitmap!!.extractAlpha()


        //给画笔设置shader 为啥不行？>
        /**
         *因为画笔设置成了stroke模式了 ！！！！
         * 填充顺序：先y轴，后x轴
         */
//        mPaint.shader=BitmapShader(mArrowBitmap,Shader.TileMode.REPEAT,Shader.TileMode.MIRROR)

        mBitmapShader = BitmapShader(mArrowBitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR)


    }

    override fun onDraw(canvas: Canvas?) {


//    canvas!!.drawRect(0f,0f,width.toFloat(), height.toFloat(),mPaint)

        /**
         * 绘图位置与图像显示
         */

        //图像显示
/*        val left=width/3f
        val top=height/3f
        val right=width*2/3f
        val bottom=height*2/3f
        canvas!!.drawRect(left,top,right,bottom,mPaint)*/


        //把图片缩放到控件大小
        //绘制望远镜
        /*     if (mEmptyBitmap == null) {
                 mEmptyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                 val newCanvas = Canvas(mEmptyBitmap)
                 newCanvas.drawBitmap(mArrowBitmap, null, Rect(0, 0, width, height), mPaint)

             }
             //为啥不嫩直接这么画？
             */
        /**
         * 直接这么画，位图就直接显示出来了，我们要的是先不显示！！而是把原图拉伸在绘制到新建的空白位图上，然后在对空白位图进行操作。
         *//*
//        canvas!!.drawBitmap(mArrowBitmap, null, Rect(0, 0, width, height), mPaint)
        if (mDX != -1f && mDY != -1f) {
            */
        /**
         * 这里是重点！！注意是空白位图，而不是原图
         *//*
            mPaint.shader = BitmapShader(mEmptyBitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR)
            canvas!!.drawCircle(mDX, mDY, 100f, mPaint)
        }*/


        //显示不规则图像

        val matrix = Matrix()
        var scaleX = width * 1.0f / mArrowBitmap!!.width
        var scaleY = height * 1.0f / mArrowBitmap!!.height
        //>1 :放大； <1: 缩小  0:表示不显示
        Log.d("ShaderView", " scalex:$scaleX, scaleY:$scaleY")
        matrix.setScale(scaleX, scaleY)
        mBitmapShader!!.setLocalMatrix(matrix)

        mPaint.shader = mBitmapShader


        canvas!!.drawCircle(width / 2f, width / 2f, width / 2f, mPaint)

//        canvas!!.drawRoundRect(0f,0f,width.toFloat(),height.toFloat(),30f,30f,mPaint)


    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                mDX = event.x
                mDY = event.y
                postInvalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {

                mDX = event.x
                mDY = event.y
                postInvalidate()
            }
            MotionEvent.ACTION_UP or MotionEvent.ACTION_CANCEL -> {

                mDX = -1f
                mDY = -1f
                postInvalidate()
            }

        }
        return super.onTouchEvent(event)
    }

}