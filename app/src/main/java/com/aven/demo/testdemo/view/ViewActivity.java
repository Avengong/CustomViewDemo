package com.aven.demo.testdemo.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.aven.demo.testdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Aven.Gong} on 2019/6/5 0005.
 */
public class ViewActivity extends AppCompatActivity {

    private View mView;

    int SCREEN_HEIGHT;
    int SCREEN_WIDTH;
    int[] originLocation = new int[2];
    private RecyclerView mRecyclerView;
    private List<Integer> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        mView = findViewById(R.id.flt_app_float_exit_room);
        mRecyclerView = findViewById(R.id.recyclerView);
        SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
        for (int i = 0; i < 10; i++) {
            mList.add(R.drawable.login_bg222);
            mList.add(R.drawable.bg_login);
            mList.add(R.mipmap.ic_launcher);
            mList.add(R.mipmap.ic_launcher);
        }
        initRcycerView();
    }

    private void initRcycerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int targetSnapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                Log.d("recyclerTest", " findTargetSnapPosition  targetSnapPosition:" + targetSnapPosition);
                return targetSnapPosition;
            }

            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                View snapView = super.findSnapView(layoutManager);
                Log.d("recyclerTest", " findSnapView   snapView:" + snapView);
                return snapView;
            }
        };
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        TestViewAdapter testCdAdapter = new TestViewAdapter(mList);
        mRecyclerView.setAdapter(testCdAdapter);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void ddd(View view) {

        mView.getLocationOnScreen(originLocation);
        PropertyValuesHolder translationX1 = PropertyValuesHolder.ofFloat("translationX", mView.getWidth(), 0);
        PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat("translationY", mView.getHeight(), 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mView, translationX1, translationY1);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();

    }

    public void jjj(View view) {
        PropertyValuesHolder translationX1 = PropertyValuesHolder.ofFloat("translationX", 0, mView.getWidth());
        PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat("translationY", 0, mView.getHeight());
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mView, translationX1, translationY1);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }

}
