package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by ${Aven.Gong} on 2019/8/26 0026.
 */
class CircleImageView : ImageView {

    var mPath = Path()

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onDraw(canvas: Canvas?) {

        mPath.addCircle(width / 2.toFloat(), height / 2.toFloat(), Math.min(width / 2, height / 2).toFloat(), Path.Direction.CCW)
        canvas!!.clipPath(mPath)
        super.onDraw(canvas)
    }

}