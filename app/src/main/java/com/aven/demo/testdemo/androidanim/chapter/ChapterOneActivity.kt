package com.aven.demo.testdemo.androidanim.chapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.aven.demo.testdemo.R

/**
 * Created by ${Aven.Gong} on 2019/8/8 0008.
 */
class ChapterOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.chapter_one)

    }
}