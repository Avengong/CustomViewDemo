package com.aven.demo.testdemo.androidanim.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView

/**
 * Created by ${Aven.Gong} on 2019/9/19 0019.
 */
class CharTextView : TextView {

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    open fun setCharText(char: Character) {
        Log.d("CharEvaluator", "toChar:$char")
        text = char.toString()
    }


    open fun setCharText(str: String) {
        Log.d("CharEvaluator", "toChar:$str")
        text = str.toString()
    }
}