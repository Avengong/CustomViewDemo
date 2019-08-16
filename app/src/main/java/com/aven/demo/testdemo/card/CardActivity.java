package com.aven.demo.testdemo.card;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.aven.demo.testdemo.R;
import com.aven.demo.testdemo.card.layoutmanager.CardConfig;
import com.aven.demo.testdemo.card.layoutmanager.OverLayCardLayoutManager;
import com.aven.demo.testdemo.card.layoutmanager.RenRenCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Aven.Gong} on 2019/7/2 0002.
 */
public class CardActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<Integer> mList = new ArrayList<>();
    private List<Integer> newList = new ArrayList<>();
    private TestCdAdapter mTestCdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity);
        for (int i = 0; i < 10; i++) {
            mList.add(R.drawable.login_bg222);
            mList.add(R.drawable.bg_login);
            mList.add(R.mipmap.ic_launcher);
            mList.add(R.mipmap.ic_launcher);
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mTestCdAdapter = new TestCdAdapter(mList);
        mRecyclerView.setLayoutManager(new OverLayCardLayoutManager());
        mRecyclerView.setAdapter(mTestCdAdapter);
        CardConfig.initConfig(this);
        RenRenCallback renRenCallback = new RenRenCallback(mRecyclerView, mTestCdAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(renRenCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }

    public void update(View view) {
        mList.clear();
        for (int i = 0; i < 2; i++) {
            mList.add(R.mipmap.ic_launcher);
            mList.add(R.drawable.login_bg222);
        }
        mTestCdAdapter.repalceAll(mList);
    }
}
