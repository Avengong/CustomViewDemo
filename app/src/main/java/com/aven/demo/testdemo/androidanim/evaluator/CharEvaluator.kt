package com.aven.demo.testdemo.androidanim.evaluator

import android.animation.TypeEvaluator

class CharEvaluator : TypeEvaluator<Char> {


    override fun evaluate(fraction: Float, startValue: Char?, endValue: Char?): Char {

        return (startValue!!.toInt() + fraction * (endValue!!.toInt() - startValue.toInt())).toChar()

    }


}