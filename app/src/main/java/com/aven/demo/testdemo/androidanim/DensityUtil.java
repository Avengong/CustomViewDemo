package com.aven.demo.testdemo.androidanim;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DensityUtil {

    /**
     * 屏幕宽度（像素）
     *
     * @param ctx
     * @return
     */
    public static int getScreenWidthPx(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 屏幕宽度（dp）
     *
     * @param ctx
     * @return
     */
    public static int getScreenWidthDp(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)

        return screenWidth;
    }

    /**
     * 屏幕高度（像素）
     *
     * @param ctx
     * @return
     */
    public static int getScreenHeightPx(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 屏幕高度（dp）
     *
     * @param ctx
     * @return
     */
    public static int getScreenHeightDp(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        return screenHeight;
    }

    /**
     * 密度（0.75 / 1.0 / 1.5）
     *
     * @param ctx
     * @return
     */
    public static float getScreenDensity(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    /**
     * 像素密度dpi（120 / 160 / 240）
     *
     * @param ctx
     * @return
     */
    public static float getScreenDensityDpi(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }
}
