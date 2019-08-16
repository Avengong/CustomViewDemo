package com.aven.demo.testdemo.card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.aven.demo.testdemo.R;

/**
 * Created by ${Aven.Gong} on 2019/7/2 0002.
 */
public class CdImageView extends android.support.v7.widget.AppCompatImageView {

    Paint mPaint = new Paint();
    PorterDuffXfermode dstOut = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    PorterDuffXfermode dstIn = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;
    private Bitmap mRoundBitmap;
    private final int W = dp2px(34);
    private final int H = dp2px(34);
    private int mBgAlpha = 255;

    public CdImageView(Context context) {
        this(context, null);
    }

    public CdImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public CdImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMaskBitmap = makeDst(W, H);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //取出attrs中我们为View设置的相关值
//        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
//        mBorderRadius = tArray.getDimensionPixelSize(R.styleable.CircleImageView_Radius, BODER_RADIUS_DEFAULT);
//        type = tArray.getInt(R.styleable.CircleImageView_type, TYPE_CIRCLE);
//        tArray.recycle();
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.YELLOW);
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        // bg
        mPaint.setColor(getResources().getColor(R.color.color7b7b7b));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(mBgAlpha);
        mPaint.setStrokeWidth(getWidth() / 2 - W / 2 - dp2px(2) / 2);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mPaint.getStrokeWidth() / 2, mPaint);

        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(dstOut);
        canvas.drawBitmap(mMaskBitmap, getWidth() / 2 - mMaskBitmap.getWidth() / 2, getHeight() / 2 - mMaskBitmap.getHeight() / 2, mPaint);
        if (mRoundBitmap == null) {
            mRoundBitmap = makeDst(getWidth(), getHeight());
        }
        mPaint.setXfermode(dstIn);
        canvas.drawBitmap(mRoundBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setColor(Color.WHITE);
        //inner
        mPaint.setAlpha(getImageAlpha());
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, W / 2, mPaint);
        //outeside
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mPaint.getStrokeWidth() / 2, mPaint);

    }

    public int dp2px(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.density * dp + 0.5f);
    }

    private Bitmap createImage(Bitmap backgroundBitmap) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap finalBmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(finalBmp);
        /**
         * 首先背景图片
         */
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        /**
         * 使用SRC_IN，取两层绘制交集，显示上层。
         */
        paint.setXfermode(dstOut);
        /**
         * 绘制前景图片
         */
        canvas.drawBitmap(mMaskBitmap, 0, 0, paint);
        return finalBmp;
    }

    /**
     * Drawable转Bitmap
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        super.setScaleType(ScaleType.CENTER_CROP);
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }


    public void setBgAlpha(int bgAlpha) {
        mBgAlpha = bgAlpha;
    }
}
