package com.aven.demo.testdemo.androidanim.chapter.nineandten

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.aven.demo.testdemo.R
import com.aven.demo.testdemo.androidanim.DensityUtil
import kotlinx.android.synthetic.main.activity_chapter_nine.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

class ChapterNineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_nine)

        testDpi()
        testBitmapFactory()

        testExtractAlpha()
    }

    private fun testExtractAlpha() {


    }

    private fun testBitmapFactory() {

        val filePath = Environment.getDataDirectory().absolutePath
        val downloadCacheDirectory = Environment.getDownloadCacheDirectory()
        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        val rootDirectory = Environment.getRootDirectory()

        val absolutePath = externalStorageDirectory.absolutePath
        Log.d("sdcard", "filePath:$filePath, " +
                "downloadCacheDirectory:${downloadCacheDirectory.absolutePath}," +
                "externalStorageDirectory:$absolutePath, " +
                "rootDirectory:${rootDirectory.absolutePath} ")

        /**
         * filePath:/data,
         * downloadCacheDirectory:/data/cache,
         * externalStorageDirectory:/storage/emulated/0,
         * rootDirectory:/system
         */

        val picPath = absolutePath + File.separator + "caramel"
//
        val picFile = File("$picPath/021816410145.jpg")

        val ins = FileInputStream(picFile)
//        val readStream = readStream(ins)

//            val decodeFile = BitmapFactory.decodeFile(picFile.path)
//        val decodeFile = BitmapFactory.decodeFileDescriptor(ins.fd)
//        val decodeFile = BitmapFactory.decodeByteArray(readStream,0,readStream.size)
        val decodeFile = BitmapFactory.decodeStream(ins)



        iv_imageview.setImageBitmap(decodeFile)

        val listFiles = File(picPath).listFiles()
        for (i in 0 until listFiles.size) {
            val absolutePath1 = listFiles[i].absolutePath
            //如果没有权限，则为空！！！！
            //主动申请权限
            //设置手动允许权限
            Log.d("sdcard", "file:$absolutePath1")
        }


//        /storage/emulated/0/021816410145.jpg


    }

    private fun readStream(ins: FileInputStream): ByteArray {

        val byteArrayOutputStream = ByteArrayOutputStream()

        val buffer = ByteArray(1024)
        var len = 0
        while (len != -1) {
            len = ins.read(buffer)
            byteArrayOutputStream.write(buffer, 0, len)

        }

        return byteArrayOutputStream.toByteArray()

    }


    private fun testDpi() {

        /**
         * 原图： 720*1280
         * ldpi:   density: 2.625 width: 2520 height:4480
         * mdpi：  density: 2.625 width: 1890 height:3360
         * hpdi:   density: 2.625 width: 1260 height:2240
         * xhpid:  density: 2.625 width: 945 height:1680
         *xxhpdi:  density: 2.625 width: 630 height:1120
         *
         * 计算原理： 缩放比例=屏幕当前分辨率/目标文件夹的分辨率 （都是以代为dp来计算）
         * 如：图片存在hdpi文件夹，对应的分辨率是densityDp=240dp（每英寸有多少个dip）---density=1.5倍。屏幕分辨率是420dp，
         * 所以 scale=420/240=1.75。 因此，缩放后的图片width=720*1.75=1260。高度原理一样。
         *
         * xxdpi： newWidth=720 dp*2.625/3=720*0.875=630 dp
         *
         * 总结：两种方式算比例：
         * 1，通过屏幕分辨率对应下的每英寸dp/文件夹对应的dp
         * 2，手机的density倍数/当前图片所在文件夹对应的density倍数
         *
         *
         *
        // return 0.75 if it's LDPI   120~ 160 , 其实就是120
        // return 1.0 if it's MDPI     160~ 320 , 其实就是160
        // return 1.5 if it's HDPI      240~ 320 , 其实就是240
        // return 2.0 if it's XHDPI     320~ 480 , 其实就是320
        // return 3.0 if it's XXHDPI    480~ 640, 其实就是480
        // return 4.0 if it's XXXHDPI   640~ 800 , 其实就是640
         *
         *总结： Android会自动根据图片所在的文件夹和手机本身的density来对图片进行缩放。比如：在mdpi中放了一张720px的图，那么在
         * 手机density为2.625的手机上首先会找xhpdi对应的文件下有没有，
         * 没有则会加载mdpi中的图片，此时的放大倍数是density/文件夹的density *720=1890
         */
        val density = getResources().getDisplayMetrics().density


        val decodeResource = BitmapFactory.decodeResource(resources, R.drawable.bg_login)

        Log.d("bitmap", "density: $density width: ${decodeResource.width} height:${decodeResource.height}")


        val screenWidthPx = DensityUtil.getScreenWidthPx(this)
        val screenWidthDp = DensityUtil.getScreenWidthDp(this)
        val screenDensityDpi = DensityUtil.getScreenDensityDpi(this)
        Log.d("bitmap", "screenWidthPx:$screenWidthPx, screenWidthDp:$screenWidthDp, " +
                "screenDensityDpi:$screenDensityDpi ")

        /**
         * screenWidthPx:1080, screenWidthDp:411, screenDensityDpi:420.0
         *
         * 411=1080/2.625
         */

    }
}