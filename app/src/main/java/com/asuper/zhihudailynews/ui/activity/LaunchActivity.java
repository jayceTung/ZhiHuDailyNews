package com.asuper.zhihudailynews.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.asuper.zhihudailynews.Bean.LaunchImageBean;
import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.Constant;
import com.asuper.zhihudailynews.base.ExitAppReceiver;
import com.asuper.zhihudailynews.presenter.impl.LaunchPresenter;
import com.asuper.zhihudailynews.utils.ConfigConstants;
import com.asuper.zhihudailynews.utils.Log;
import com.asuper.zhihudailynews.view.LaunchView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Super on 2016/8/23.
 */
public class LaunchActivity extends Activity
        implements LaunchView {
    private static final String TAG = "LaunchActivity";

    public static String RESOLUTION;
    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

    private ExitAppReceiver mExitAppReceiver = new ExitAppReceiver();
    private LaunchPresenter mLaunchPresenter;

    @Bind(R.id.iv_luanch)
    SimpleDraweeView mLaunchImage;

    @Bind(R.id.tv_form)
    TextView mFormText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        RESOLUTION = getResources().getDisplayMetrics().widthPixels + "*" + getResources().getDisplayMetrics().heightPixels;
        setContentView(R.layout.activity_luanch);
        ButterKnife.bind(this);
        mLaunchPresenter = new LaunchPresenter(this);
        mLaunchPresenter.loadData();
        registerReceiver();
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

    @Override
    public void showData(LaunchImageBean launchImageBean) {
        String img = launchImageBean.getImg();
        mLaunchImage.setController(ConfigConstants.getDraweeController(img));
        mFormText.setText(launchImageBean.getText());

        new Handler().postDelayed(new Runnable() {

            public void run() {
                animateImage();
            }

        }, 1000);
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mLaunchImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mLaunchImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
