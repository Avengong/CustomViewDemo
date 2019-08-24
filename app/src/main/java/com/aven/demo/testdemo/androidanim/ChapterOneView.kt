package com.aven.demo.testdemo.androidanim

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by ${Aven.Gong} on 2019/8/9 0009.
 */
class ChapterOneView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.argb(255, 255, 0, 0)
        mPaint.strokeWidth = 4f

        //rectf +arcTo
//        var mPath = Path()
//        var rect = RectF(0f, 0f, 200f, 200f)
//        mPath.moveTo(10f,10f)
//        mPath.arcTo(rect, -90f, 90f,false)
////        mPath.close()
//        canvas!!.drawPath(mPath, mPaint)
//        mPaint.color=Color.BLUE
//        canvas!!.drawRect(rect,mPaint)


        //region
//        mPaint.style = Paint.Style.FILL
        //那么为何要这么构造出region？而不是直接rect呢
        //是可以的。这也就说明region根本就不是用来绘图
//        val region = Region(Rect(10, 10, 200, 100))
//        val region1 = Region(Rect(30, 30, 300, 300))
//        region.set(region1)

        //region 的 setMPath(Path mPath,Region clip)
//        val mPath = Path()
        //counter-clockwise --ccw; clockwise--cw 跟后续文字顺序有关
//        mPath.addOval(RectF(30f, 30f, 100f, 300f), Path.Direction.CCW)
//        val region = Region()
//        mPaint.color = Color.BLUE
//        canvas?.drawPath(mPath, mPaint)
//        region.setMPath(mPath, Region(30, 30, 100, 100))
//        drawRegion(canvas, region)

        //region 最重要的功能是用来做区域相交操作
//        mPaint.style = Paint.Style.FILL
//        val rect1 = Rect(100, 100, 400, 200)
//        mPaint.color = Color.GRAY
//        val rect2 = Rect(200, 0, 300, 300)
//        mPaint.style = Paint.Style.STROKE
//        canvas!!.drawRect(rect1, mPaint)
//        canvas.drawRect(rect2, mPaint)
//        mPaint.style = Paint.Style.FILL
//        val region1 = Region(rect1)
//        val region2 = Region(rect2)
//        region1.op(region2, Region.Op.XOR)
//        region1.op(region2, Region.Op.INTERSECT)
//        drawRegion(canvas, region1)

        //路径生成文字
//        val ccwPath = Path()
//        val cwPath = Path()
//
//        val ccwRect = RectF(50f, 50f, 250f, 250f)
//        val cwRect = RectF(300f, 50f, 500f, 250f)
//        ccwPath.addRect(ccwRect, Path.Direction.CCW)
//        cwPath.addRect(cwRect, Path.Direction.CW)
//        canvas!!.drawPath(ccwPath, mPaint)
//        canvas!!.drawPath(cwPath, mPaint)
//        val text = "心无杂念，全力前进"
//        mPaint.textSize = 40f
//        val textRect = Rect()
//        mPaint.getTextBounds(text, 0, text.length, textRect)
//        canvas.drawTextOnPath(text, ccwPath, 0f, 0f, mPaint)
//        canvas.drawTextOnPath(text, cwPath, 0f, (textRect.height() / 2).toFloat(), mPaint)

        //圆角矩形路径
//        val ccwRect = RectF(50f, 50f, 250f, 250f)
//        val cwRect = RectF(300f, 50f, 500f, 250f)
//        val mPath = Path()
//
//        mPath.addRoundRect(ccwRect,20f,20f,Path.Direction.CCW)
//        //第一个左上角x y椭圆半径 一次类推
//        val array = floatArrayOf(20f, 20f, 10f, 10f, 5f, 5f, 30f, 5f)
//        mPath.addRoundRect(cwRect,array,Path.Direction.CCW)
//        canvas!!.drawPath(mPath,mPaint)

        //mPath 的填充模式fox
//        mPaint.style=Paint.Style.FILL
//        mPaint.color=Color.RED
//        val mPath=Path()
//        mPath.addRect(RectF(100f,100f,200f,200f),Path.Direction.CCW)
//        mPath.addCircle(200f,200f,50f,Path.Direction.CCW)
//        mPath.fillType=Path.FillType.INVERSE_WINDING
//        canvas!!.drawPath(mPath,mPaint)

        //mPath 重置

        //会清除数据，但不会清楚filltype
//        mPath.reset()
        //不会清除数据，但会清楚filltype
//        mPath.rewind()
//
        //蜘蛛网效果--spiderview


        //canvas 变换
//        val rect = Rect(0, 0, 100, 100)
//        mPaint.style = Paint.Style.FILL
//        mPaint.color = Color.GRAY
//        canvas!!.drawRect(rect, mPaint)
//        canvas.translate(150f, 150f)
//        mPaint.color = Color.RED
//        canvas.drawRect(rect, mPaint)

        //region
//        var ovalPath=Path()
//        ovalPath.addOval(RectF(10f,10f,100f,500f),Path.Direction.CCW)
//
//        var region=Region()
//        region.setPath(ovalPath, Region(10,10,200,100))
//
//        drawRegion(canvas,region)


        //裁剪动画






    }

    private fun drawRegion(canvas: Canvas?, region: Region) {

        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        while (regionIterator.next(rect)) {
            canvas?.drawRect(rect, mPaint)
        }
    }
}