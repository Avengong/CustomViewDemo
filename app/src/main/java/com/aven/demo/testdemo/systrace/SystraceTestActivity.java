package com.aven.demo.testdemo.systrace;

import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aven.demo.testdemo.R;

/**
 * Created by ${Aven.Gong} on 2019/6/5 0005.
 */
public class SystraceTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Trace.beginSection("draw");
        setContentView(R.layout.systrace_test_activity);
        Trace.endSection();
    }
}
