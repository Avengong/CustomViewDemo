package com.aven.demo.testdemo.systrace;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by ${Aven.Gong} on 2019/6/5 0005.
 */
public class OverDrawView extends android.support.v7.widget.AppCompatTextView {
    public OverDrawView(Context context) {
        this(context,null);
    }

    public OverDrawView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OverDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<200000;i++){
            calNull();
        }
    }

    private void calNull() {
        String s = new String("555");
        Log.d("systrace","draw much  test ");
    }
}
