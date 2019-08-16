package com.aven.demo.testdemo.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aven.demo.testdemo.R;
import com.aven.demo.testdemo.databinding.MvvmDataActivityBinding;

/**
 * Created by ${Aven.Gong} on 2019/6/4 0004.
 */
public class DataBindingActivity extends AppCompatActivity {

    private static final String TAG = "DataBindingActivity";
    private MvvmDataActivityBinding mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.mvvm_data_activity);
        Person person = new Person();
        person.setName("mvvm");
        person.setSex("男");
        mViewDataBinding.setPerson(person);

        setListener();
    }

    private void setListener() {

        mViewDataBinding.btnMvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mvvm click ok  ");
            }
        });


    }


}
