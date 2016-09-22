package com.asuper.zhihudailynews.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.BaseSwipeBackActivity;
import com.asuper.zhihudailynews.utils.Log;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;

/**
 * Created by Super on 2016/9/22.
 */
public class MoreActivity extends BaseSwipeBackActivity {
    private static final String TAG = "MoreActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.photo)
    SimpleDraweeView mPhotoView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Log.i(TAG, "initView");
        getWindow().setBackgroundDrawable(null);

        mSwipeBackLayout.setEdgeDp(120);
        mToolbar.setTitle(R.string.action_settings);
        mToolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mToolbar);

        mPhotoView.setImageURI(Uri.parse("res://" + getPackageName() + "/" + R.mipmap.ic_me));
    }

    @Override
    public void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
