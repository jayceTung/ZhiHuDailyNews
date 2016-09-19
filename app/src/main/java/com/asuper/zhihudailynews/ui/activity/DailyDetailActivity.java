package com.asuper.zhihudailynews.ui.activity;

import android.os.Bundle;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.BaseSwipeBackActivity;

/**
 * Created by Super on 2016/9/19.
 */
public class DailyDetailActivity extends BaseSwipeBackActivity {
    private static final String TAG = "DailyDetailActivity";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {

    }
}
