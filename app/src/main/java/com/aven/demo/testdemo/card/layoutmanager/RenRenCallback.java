package com.aven.demo.testdemo.card.layoutmanager;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aven.demo.testdemo.R;
import com.aven.demo.testdemo.card.CdImageView;
import com.aven.demo.testdemo.card.TestCdAdapter;


/**
 * 介绍：人人影视效果的Callback
 */

public class RenRenCallback extends ItemTouchHelper.SimpleCallback {

    protected RecyclerView mRv;
    protected TestCdAdapter mAdapter;

    public RenRenCallback(RecyclerView rv, TestCdAdapter adapter) {
        this(0,
                ItemTouchHelper.LEFT,
                rv, adapter);
    }

    public RenRenCallback(int dragDirs, int swipeDirs
            , RecyclerView rv, TestCdAdapter adapter) {
        super(dragDirs, swipeDirs);
        mRv = rv;
        mAdapter = adapter;
    }

    //水平方向是否可以被回收掉的阈值
    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        //2016 12 26 考虑 探探垂直上下方向滑动，不删除卡片，这里参照源码写死0.5f
        return mRv.getWidth()  /*getSwipeThreshold(viewHolder)*/;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d("cdView", "onMove: ");
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Log.e("swipecard", "onSwiped() called with: viewHolder = [" + viewHolder + "], direction = [" + direction + "]");
        //rollBack(viewHolder);
        Log.d("cdView", "onSwiped: ");
        //★实现循环的要点
        mAdapter.removeItem(viewHolder.getLayoutPosition());
        ImageView view = (ImageView) viewHolder.itemView.findViewById(R.id.imageView);
        ObjectAnimator objectAnim = CardConfig.getObjectAnim(view);
        objectAnim.end();
        objectAnim.cancel();
//        Object remove = mDatas.remove(viewHolder.getLayoutPosition());
//        mDatas.add(0, remove);
//        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        int layoutPosition = viewHolder.getLayoutPosition();
        Log.d("cdView", "onSelectedChanged: " + layoutPosition);
        ImageView view = (ImageView) viewHolder.itemView.findViewById(R.id.imageView);
        ObjectAnimator objectAnim = CardConfig.getObjectAnim(view);
        if (Build.VERSION.SDK_INT >= 19) {
            if (objectAnim.isRunning()) {
                objectAnim.pause();
            }
        } else {
            if (objectAnim.isRunning()) {
                objectAnim.cancel();
            }
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.d("cdView", "clearView: " + viewHolder.getLayoutPosition());
        ImageView view = (ImageView) viewHolder.itemView.findViewById(R.id.imageView);
//        view.startAnimation(CardConfig.getAnim());
        ObjectAnimator objectAnim = CardConfig.getObjectAnim(view);
        if (Build.VERSION.SDK_INT >= 19) {
            if (objectAnim.isPaused()) {
                objectAnim.resume();
            }
        } else {
            if (!objectAnim.isRunning()) {
                objectAnim.start();
            }
        }

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //先根据滑动的dxdy 算出现在动画的比例系数fraction
        double swipValue = Math.sqrt(dX * dX + dY * dY);
        double fraction = swipValue / getThreshold(viewHolder);//阈值
        if (fraction > 1) {
            fraction = 1;
        }
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = childCount - i - 1;
            if (level > 0) {
                if (child instanceof LinearLayout) {
                    LinearLayout newView = (LinearLayout) (child);
                    CdImageView cdView = (CdImageView) newView.getChildAt(0);
//                        cdView.setBgAlpha(255 / level);
                    if (level == 1) {
                        cdView.setImageAlpha((int) (fraction * 255));
                    }
                }
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
//                    Log.d("cdView", "fraction: " + fraction);
                    double value = 1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP;
                    child.setScaleX((float) (value));
                    child.setScaleY((float) (value));
                    child.setTranslationX((float) (CardConfig.TRANS_X_GAP * level - fraction * CardConfig.TRANS_X_GAP));
                } else {
                }
            } else {
                if (child.getAnimation() != null) {
                    child.getAnimation().cancel();
                }
            }
        }
    }
}