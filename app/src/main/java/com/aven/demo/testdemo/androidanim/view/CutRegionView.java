package com.aven.demo.testdemo.androidanim.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aven.demo.testdemo.R;

public class CutRegionView extends View {

    private static final String TAG = "CutRegionView";
    private Bitmap mBitmap;
    private Paint mPaint;
    private Region mRegion;
    private int height = 30;
    private int mClipWidth = 20;
    private Path mPath;


    public CutRegionView(Context context) {
        this(context, null);
    }

    public CutRegionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CutRegionView(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_login);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

        mRegion = new Region();

        mPath = new Path();
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }


    @Override
    protected void onDraw(Canvas canvas) {

//        mRegion.setEmpty();
        mPath.reset();


        for (int i = 0; i < getHeight() / height; i++) {
            RectF rect;
            if (i % 2 == 0) {
                rect = new RectF(0, i * height, mClipWidth, (i + 1) * height);
            } else {
                rect = new RectF(getWidth() - mClipWidth, i * height, getWidth(), (i + 1) * height);

            }
            mPath.addRect(rect, Path.Direction.CCW);
//            mRegion.union(rect);
        }
        Log.d(TAG, "mclipwidth:" + mClipWidth);
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        if (mClipWidth > getWidth()) {
            return;
        }


        mClipWidth = mClipWidth + 20;
        invalidate();


    }

    private void drawRegion(Canvas canvas, Paint paint, Region region) {

        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }


//    private fun drawRegion(canvas: Canvas?, region: Region) {
//
//        mPaint.color = Color.BLACK
//        mPaint.style=Paint.Style.STROKE
//        val regionIterator = RegionIterator(region)
//        val rect = Rect()
//        while (regionIterator.next(rect)) {
//            canvas?.drawRect(rect, mPaint)
//        }
//    }
}
