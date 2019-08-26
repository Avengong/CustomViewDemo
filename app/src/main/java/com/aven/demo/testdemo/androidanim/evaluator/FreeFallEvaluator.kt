package com.aven.demo.testdemo.androidanim.evaluator

import android.animation.TypeEvaluator
import android.util.Log

class FreeFallEvaluator : TypeEvaluator<Point> {

    var point = Point(0, 0)
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        point.x = (startValue!!.x + fraction * (endValue!!.x - startValue.x)).toInt()

        if (fraction <= 1f / 2) {
            //大
            point.y = (startValue.y + 2 * fraction * (endValue.y - startValue.y)).toInt()

            Log.d("jisuan", "starty:${startValue.y} endy:${endValue.y} fraction:$fraction")
        } else {
            point.y = endValue.y
        }
        return point
    }
}