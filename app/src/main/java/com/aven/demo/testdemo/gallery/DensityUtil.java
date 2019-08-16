package com.aven.demo.testdemo.gallery;

import android.content.Context;

/**
 * Created by ${Aven.Gong} on 2019/3/28 0028.
 */
class DensityUtil {
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }
}
