package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.aven.demo.testdemo.androidanim.evaluator.Point

/**
 * Created by ${Aven.Gong} on 2019/8/26 0026.
 */
class ObjectFreeFallView : ImageView {

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    public fun setFallingPos(point: Point) {
        layout(point.x, point.y, point.x + width, point.y + height)
    }

}