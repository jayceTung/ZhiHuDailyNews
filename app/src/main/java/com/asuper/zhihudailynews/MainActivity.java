package com.asuper.zhihudailynews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.asuper.zhihudailynews.base.AbsBaseActivity;
import com.asuper.zhihudailynews.ui.activity.LaunchActivity;

public class MainActivity extends AbsBaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = (TextView) findViewById(R.id.view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.setClass(MainActivity.this, LaunchActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {

    }
}
