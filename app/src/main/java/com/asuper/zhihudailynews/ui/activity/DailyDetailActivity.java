package com.asuper.zhihudailynews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.BaseSwipeBackActivity;
import com.asuper.zhihudailynews.base.Constant;
import com.asuper.zhihudailynews.bean.DailyDetail;
import com.asuper.zhihudailynews.presenter.impl.DetailNewsPresenter;
import com.asuper.zhihudailynews.utils.ConfigConstants;
import com.asuper.zhihudailynews.utils.HtmlUtil;
import com.asuper.zhihudailynews.utils.Log;
import com.asuper.zhihudailynews.utils.NetWorkUtil;
import com.asuper.zhihudailynews.view.DetailNewsView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;

/**
 * Created by Super on 2016/9/19.
 */
public class DailyDetailActivity extends BaseSwipeBackActivity
        implements DetailNewsView {
    private static final String TAG = "DailyDetailActivity";

    @Bind(R.id.coll_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.detail_image)
    SimpleDraweeView mDetailImage;

    @Bind(R.id.detail_title)
    TextView mDetailTitle;

    @Bind(R.id.detail_source)
    TextView mDetailSource;

    @Bind(R.id.detail_web_view)
    WebView mWebView;

    private DetailNewsPresenter mPresent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Log.i(TAG, "initView");
        getWindow().setBackgroundDrawable(null);
        //设置侧滑返回触发范围
        mSwipeBackLayout.setEdgeDp(120);
        // 初始化ToolBar
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitleEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.back);
    }

    @Override
    public void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresent = new DetailNewsPresenter(this);
        mPresent.loadData(getIntent().getIntExtra(Constant.EXTRA_DETAIL, 0));
    }

    @Override
    public void showData(DailyDetail dailyDetail) {
        mDetailImage.setController(ConfigConstants.getDraweeController(dailyDetail.getImage()));
        //设置标题
        mDetailTitle.setText(dailyDetail.getTitle());
        mCollapsingToolbarLayout.setTitle(dailyDetail.getTitle());
        //设置图片来源
        mDetailSource.setText(dailyDetail.getImage_source());
        //设置web内容加载
        mWebView.loadData(HtmlUtil.createHtmlData(dailyDetail), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void loadFailure(Throwable e) {
        NetWorkUtil.showErrorCode(e, this, mCollapsingToolbarLayout);
    }
}
