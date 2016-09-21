package com.asuper.zhihudailynews.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.LazyFragment;
import com.asuper.zhihudailynews.base.WrapContentLinearLayoutManager;
import com.asuper.zhihudailynews.presenter.ILatestNewsPresenter;
import com.asuper.zhihudailynews.presenter.impl.LatestNewsPresenter;
import com.asuper.zhihudailynews.ui.adapter.DailyListAdapter;
import com.asuper.zhihudailynews.utils.Log;
import com.asuper.zhihudailynews.view.LatestNewsView;

import butterknife.Bind;

/**
 * Created by Super on 2016/8/10.
 */
public class DailyListFragment extends LazyFragment
        implements LatestNewsView {
    private static final String TAG = "DailyListFragment";

    @Bind(R.id.daily_recycle)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ILatestNewsPresenter mPresenter;
    private DailyListAdapter mDailyListAdapter;

    @Override
    public int getLayoutId() {
        Log.i(TAG, "getLayoutId");
        return R.layout.fragment_daily_list;
    }

    @Override
    public void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        mDailyListAdapter = new DailyListAdapter(getActivity());
        mRecyclerView.setAdapter(mDailyListAdapter);
    }

    @Override
    public void initEvent() {
        mPresenter = new LatestNewsPresenter(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDailyListAdapter.allClear();
                mPresenter.loadData();
            }
        });
    }

    @Override
    public void initLoadData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.loadData();
    }

    @Override
    public void showData(DailyListBean dailyListBean) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mDailyListAdapter.addData(dailyListBean.getStories());
    }

    @Override
    public void loadFailure(Throwable e) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG)
                .setAction(R.string.snack_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadData();
                    }
                }).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDailyListAdapter != null) {
            mDailyListAdapter.destroy();
        }
    }
}
