package com.asuper.zhihudailynews.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.asuper.zhihudailynews.utils.StatusBarCompat;
import com.asuper.zhihudailynews.widget.swipebacklayout.SwipeBackActivity;
import com.asuper.zhihudailynews.widget.swipebacklayout.SwipeBackLayout;

import butterknife.ButterKnife;

/**
 * Created by Super on 2016/9/19.
 */
public abstract class BaseSwipeBackActivity extends SwipeBackActivity {
    public SwipeBackLayout mSwipeBackLayout;
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

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //初始化View
        initView(savedInstanceState);
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        unregisterReceiver(mExitAppReceiver);
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.EXIT_APP_ACTION);
        registerReceiver(mExitAppReceiver, filter);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);


    public abstract void initEvent();
}
