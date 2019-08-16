package com.aven.demo.testdemo.carousellayoutmanager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

/**
 * Created by zg on 2018/11/23.
 */

public class AutoPollRecyclerView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 5000;
    AutoPollTask autoPollTask;
    private boolean running;  //标示是否正在自动轮询
    private boolean canRun;  //标示是否可以自动轮询,可在不需要的是否置false

    public AutoPollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        autoPollTask = new AutoPollTask(this);
    }

    static class AutoPollTask implements Runnable {
        private final WeakReference<AutoPollRecyclerView> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(AutoPollRecyclerView reference) {

            this.mReference = new WeakReference<AutoPollRecyclerView>(reference);
            AutoPollRecyclerView recyclerView = mReference.get();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                }
            });
        }

        @Override
        public void run() {
            try {
                AutoPollRecyclerView recyclerView = mReference.get();
                if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                    int currentIndex = ((CarouselLayoutManager) recyclerView.getLayoutManager()).getCenterItemPosition();
                    int itemCount = recyclerView.getAdapter().getItemCount();
                    if (currentIndex == itemCount - 1) {
                        currentIndex = -1;
                    }
                    recyclerView.smoothScrollToPosition(currentIndex + 1);
                    recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.TIME_AUTO_POLL);
                }
            }catch (Exception e){

            }

        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running) {
            stop();
        }
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

    float x1 = 0;
    float x2 = 0;
    boolean isFirst = true;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (running) {
                    stop();
                }
                //当手指按下的时候
                x1 = e.getX();
                float rawX1 = e.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (running) {
                    stop();
                }
                if (isFirst) {
                    isFirst = false;
                    x1 = e.getX();
                }
                break;
            case MotionEvent.ACTION_UP:
                isFirst = true;
                x2 = e.getX();
                float width = x1 - x2;
                if (width > 0) {//向左滑
                    int currentIndex = ((CarouselLayoutManager) getLayoutManager()).getCenterItemPosition();
                    int itemCount = getAdapter().getItemCount();
                    if (currentIndex == itemCount - 1) {
                        currentIndex = -1;
                    }
                    smoothScrollToPosition(currentIndex + 1);
                    if (canRun) {
                        start();
                    }
                    return true;
                } else if (width < 0) {//向右滑
                    int currentIndex = ((CarouselLayoutManager) getLayoutManager()).getCenterItemPosition();
                    int itemCount = getAdapter().getItemCount();
                    if (currentIndex == 0) {
                        currentIndex = itemCount;
                    }
                    smoothScrollToPosition(currentIndex - 1);
                    if (canRun) {
                        start();
                    }
                    return true;
                }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (canRun) {
                    start();
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX *= 0.2f;
        return super.fling(velocityX, velocityY);
    }
}

