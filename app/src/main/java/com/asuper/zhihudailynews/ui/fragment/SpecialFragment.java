package com.asuper.zhihudailynews.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.LazyFragment;

import butterknife.Bind;

/**
 * Created by Super on 2016/9/26.
 */
public class SpecialFragment extends LazyFragment {
    private static final String TAG = "SpecialFragment";

    @Bind(R.layout.toolbar_layout)
    Toolbar mToolbar;

    @Bind(R.id.special_view)
    RecyclerView mSpecialView;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initLoadData() {

    }
}
