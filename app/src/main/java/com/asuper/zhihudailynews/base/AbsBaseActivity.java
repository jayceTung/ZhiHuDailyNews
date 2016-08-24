package com.asuper.zhihudailynews.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.asuper.zhihudailynews.utils.StatusBarCompat;

import butterknife.ButterKnife;

/**
 * Created by Super on 2016/8/12.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    private ExitAppReceiver mExitAppReceiver = new ExitAppReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        registerReceiver();
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //适配4.4状态栏
        StatusBarCompat.compat(this);
        //初始化View
        initView(savedInstanceState);
        initToolBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mExitAppReceiver);
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.EXIT_APP_ACTION);
        registerReceiver(mExitAppReceiver, filter);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initToolBar();
}
