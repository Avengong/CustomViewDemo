package com.aven.demo.testdemo.androidanim.chapter.eight

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.aven.demo.testdemo.R

class PorterDuffXfermodeView : View {
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
     * 需求：把图片中的白色替换成红色
     * 思路：
     *
     *
     *
     *
     */
    private fun init() {

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mArrowBitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_login)


        mSrcBitmap = makeSrcBitmap(200, 200)
        mDstBitmap = makeDstBitmap(200, 200)


    }

    private fun makeSrcBitmap(w: Int, h: Int): Bitmap {

        val createBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(createBitmap)
        val paint = Paint()
        paint.color = Color.BLUE
        canvas.drawRect(0f, 0f, w.toFloat(), h.toFloat(), paint)
        return createBitmap
    }


    private fun makeDstBitmap(w: Int, h: Int): Bitmap {

        val createBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(createBitmap)
        val paint = Paint()
        paint.color = Color.YELLOW
        canvas.drawCircle(w / 2f, h / 2f, w / 2f, paint)
        return createBitmap
    }

    override fun onDraw(canvas: Canvas?) {

        val saveLayer = canvas!!.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas.drawBitmap(mDstBitmap, 0f, 0f, mPaint)
        val mode = PorterDuff.Mode.SRC_IN
        /**
         * 颜色叠加相关：
         * ADD: 相加  效果一样
         *LIGHTEN 变亮  效果一样   -重点
         *DARKEN  变暗  变黑了
         *MULTIPLY 源图像非相交没了，相交部分变黑了 --特殊
         *OVERLAY 相交才有效果
         * SCREEN
         *
         * 源图像模式：
         * SRC ，相交部分直接显示源图像，二者保留 ，src在dst上
         * SRC_IN,  相交部分直接显示源图像 ，只保留相交部分 --重点. dst的透明度改变会影响到src图像的透明度和饱和度
         * ：圆角图片效果；倒影效果
         *SRC_OUT, 相交部分透明，其他保持正常。 dst的透明度为0，那么相交部分则显示。dst透明度为1，橡胶部分则消失。
         * ：橡皮擦效果, 中奖效果，中间镂空效果
         *SRC_OVER: 跟SRC相同
         *SRC_ATOP: 跟 SRC_IN相同  ,
         *
         * 目标图像模式与其他模式
         * DST:  与SRC 相反，只显示dst图像
         *DST_IN: 只显示dst图像， 通过源图像透明度来改变目标图像的透明度和饱和度
         *
         *
         *
         *
         *
         */
        mPaint.xfermode = PorterDuffXfermode(mode)
        //以圆形的中心点为左上点画矩形
        canvas.drawBitmap(mSrcBitmap, mDstBitmap.width / 2f, mDstBitmap.height / 2f, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(saveLayer)


    }


}