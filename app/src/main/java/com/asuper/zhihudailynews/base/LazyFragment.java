package com.asuper.zhihudailynews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Super on 2016/8/10.
 */
public abstract class LazyFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        initEvent();
        initLoadData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initEvent();

    public abstract void initLoadData();
}
