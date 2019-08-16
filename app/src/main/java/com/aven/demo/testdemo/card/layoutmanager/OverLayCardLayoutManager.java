package com.aven.demo.testdemo.card.layoutmanager;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aven.demo.testdemo.card.CdImageView;

/**
 * 介绍：参考人人影视 最多排列四个
 * 重叠卡片布局
 */

public class OverLayCardLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "swipecard";

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void detachView(@NonNull View child) {
        super.detachView(child);

    }

    @Override
    public void attachView(@NonNull View child) {
        super.attachView(child);

    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e(TAG, "onLayoutChildren() called with: recycler = [" + recycler + "], state = [" + state + "]");
        Log.d("cdView", "onLayoutChildren: ");
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount < 1) {
            return;
        }
        //top-3View的position ---最底层的position ，
        /*
         * if(孩子个数< 最大显示数){
         *   //此时肯定显示全部
         *   从0开始显示
         * }else{
         * //此时只能倒着来显示
         * 比如孩子数8，最大数是3.那么只能显示
         * 8-3=5； 6  7 最后三个。
         * }
         * */
        int bottomPosition;
        //边界处理
        if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            bottomPosition = 0;
        } else {
            bottomPosition = itemCount - CardConfig.MAX_SHOW_COUNT;
        }

        //从可见的最底层View开始layout，依次层叠上去
        for (int position = bottomPosition; position < itemCount; position++) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            //测量子view
            measureChildWithMargins(view, 0, 0);
            //获取包含分割线的子view的宽度。然后用整个宽度-子view宽度
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            //我们在布局时，将childView居中处理，这里也可以改为只水平居中
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));
            /**
             * TopView的Scale 为1，translationY 0
             * 每一级Scale相差0.05f，translationY相差7dp左右
             *
             * 观察人人影视的UI，拖动时，topView被拖动，Scale不变，一直为1.
             * top-1View 的Scale慢慢变化至1，translation也慢慢恢复0
             * top-2View的Scale慢慢变化至 top-1View的Scale，translation 也慢慢变化只top-1View的translation
             * top-3View的Scale要变化，translation岿然不动
             */
            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = itemCount - position - 1;
            //除了顶层不需要缩小和位移 ；从第二层开始
            if (level > 0 /*&& level < mShowCount - 1*/) {
                //每一层都需要X方向的缩小
                if (view instanceof LinearLayout) {
                    LinearLayout newView = (LinearLayout) (view);
                    CdImageView cdView = (CdImageView) newView.getChildAt(0);
                    cdView.setBgAlpha(255 / level);
                    cdView.setImageAlpha(0);
                }
//                if (level == CardConfig.MAX_SHOW_COUNT - 1) {
//                    view.setAlpha(0);
//                }
                //前N层，依次向下位移和Y方向的缩小
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    view.setScaleX(1 - CardConfig.SCALE_GAP * level);
                    view.setScaleY(1 - CardConfig.SCALE_GAP * level);
                    view.setTranslationX(CardConfig.TRANS_X_GAP * level);
                } else {//第N层在 向下位移和Y方向的缩小的成都与 N-1层保持一致
                    view.setScaleX(1 - CardConfig.SCALE_GAP * (level - 1));
                    view.setScaleY(1 - CardConfig.SCALE_GAP * (level - 1));
                    view.setTranslationX(CardConfig.TRANS_X_GAP * (level - 1));
                }

//                if (level < CardConfig.MAX_SHOW_COUNT - 1) {//可见的最后一张
//                    view.setTranslationY(CardConfig.TRANS_X_GAP * level)
//                    view.setScaleY(1 - CardConfig.SCALE_GAP * level);
//                } else {//第N层在 向下位移和Y方向的缩小的成都与 N-1层保持一致?
//                    view.setTranslationY(CardConfig.TRANS_X_GAP * (level - 1));
//                    view.setScaleY(1 - CardConfig.SCALE_GAP * (level - 1));
//                }
            } else {
//                view.clearAnimation();
//                view.startAnimation(CardConfig.getAnim());

                ObjectAnimator objectAnim = CardConfig.getObjectAnim(view);
                if (!objectAnim.isRunning()) {
                    objectAnim.start();
                }
            }
        }
    }

}