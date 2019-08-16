package com.aven.demo.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.aven.demo.annotations.BindView;
import com.aven.demo.testdemo.androidanim.AnimActivityKt;
import com.aven.demo.testdemo.card.CardActivity;
import com.aven.demo.testdemo.gallery.CardScaleHelper;
import com.aven.demo.testdemo.view.ViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @BindView(value = R.id.recyclerView)
//    public RecyclerView mRecyclerView;

    private CardScaleHelper mCardScaleHelper;
    private List<Integer> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 4; i++) {
            mList.add(R.mipmap.ic_launcher);
        }
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setAdapter(new CardAdapter(mList));
        // mRecyclerView绑定scale效果
//        mCardScaleHelper = new CardScaleHelper();
//        mCardScaleHelper.setCurrentItemPos(2);
//        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
    }

    public void click(View view) {
//        Intent intent = new Intent(this, CarouselPreviewActivity.class);
//        startActivity(intent);

//        cal1();
//        cal2();
//
//        int i = 0;
//        int j = 0;

//        System.out.println("click i: " + (i++));
//        System.out.println("click i: " + (i));
//        System.out.println("click j: " + (++j));

        printChannel(21, 61);
        printChannel(120, 129);
        printChannel(200, 230);

//        startActivity(new Intent(this, DataBindingActivity.class));
//        startActivity(new Intent(this, SystraceTestActivity.class));
        startActivity(new Intent(this, ViewActivity.class));

        showFloatView();

    }

    private void showFloatView() {

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);


    }

    private void printChannel(int start, int end) {
        String string = "";
        for (int i = start; i <= end; i++) {
            if (i == end) {
                string = string + i;
            } else {
                string = string + i + ";";
            }
        }
        System.out.println("渠道" + string);
    }

    void cal1() {
        int a = 0;
        for (int i = 0; i < 10; i++) {
            a = a++;
        }
        System.out.println("click a: " + a);

    }

    void cal2() {
        int a = 0;
        for (int i = 0; i < 10; i++) {
            a = ++a;
        }
        System.out.println("click a: " + a);

    }

    public void pre(View view) {
        startActivity(new Intent(MainActivity.this, CardActivity.class));
    }

    public void anim(View view) {
        startActivity(new Intent(MainActivity.this, AnimActivityKt.class));
    }
}
