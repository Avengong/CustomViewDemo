package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by ${Aven.Gong} on 2019/8/14 0014.
 */
class SpiderView : View {

    private lateinit var mPaint: Paint

    //继承三个构造方法
    constructor (context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    init {
        init()
    }

    private fun init() {
        mPaint = Paint()


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.style = Paint.Style.STROKE
    }

}