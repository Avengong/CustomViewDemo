package com.aven.demo.testdemo.androidanim.interpolator

import android.view.animation.Interpolator

class CustomInterpolator : Interpolator {


    override fun getInterpolation(input: Float): Float {

        return 2f

    }


}