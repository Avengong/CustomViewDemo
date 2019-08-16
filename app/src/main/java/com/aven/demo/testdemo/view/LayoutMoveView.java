package com.aven.demo.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by ${Aven.Gong} on 2019/6/5 0005.
 */
public class LayoutMoveView extends View {

    private int mFromX;
    private int mFromY;
    private Scroller mScroller = new Scroller(getContext());

    public LayoutMoveView(Context context) {
        this(context, null);
    }

    public LayoutMoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getX();
        int rawY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFromX = rawX;
                mFromY = rawY;
//                mScroller.startScroll(mFromX, mFromY, -200, -200, 3000);
//                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:

                int dix = (rawX - mFromX);
                int diy = (rawY - mFromY);
                //方法1 通过layout
//                int left = getLeft() + dix;
//                int right = getRight() + dix;
//                int top = getTop() + diy;
//                int bottom = getBottom() + diy;
//                if (left >= 0 && right <= getScreenWidth() && top >= 0 && bottom <= getScreenHeight()) {
//                    layout(left, (int) (getTop() + diy), right, (int) (getBottom() + diy));
//                }
                //方法2 直接通过offsetLeftAndRight
//                offsetLeftAndRight(dix);
//                offsetTopAndBottom(diy);
                //方法三通过scrollto和scrollby
                //为何要加复号? view是不会动，移动的是手机屏幕
//                ((View) getParent()).scrollBy(-dix, -diy);

                return true;
            default:

                break;
        }

        return true;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            View parent = (View) getParent();
            parent.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
