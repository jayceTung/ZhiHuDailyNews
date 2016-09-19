package com.asuper.zhihudailynews.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.AbsBaseActivity;
import com.asuper.zhihudailynews.ui.fragment.DailyListFragment;
import com.asuper.zhihudailynews.utils.Log;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Super on 2016/8/24.
 */
public class MainActivity extends AbsBaseActivity {
    private static final String TAG = "MainActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    BottomBar mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        addFragment();
        showFragment(0);
    }

    private void addFragment() {
        fragments.clear();
        fragments.add(new DailyListFragment());
    }

    @Override
    public void initToolBar() {
        Log.i(TAG, "initToolBar");
        getWindow().setBackgroundDrawable(null);
        mToolbar.setTitle(R.string.app_name);

        mNavigationView.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.item_one:
                        showFragment(0);
                        break;
                    case R.id.item_two:
                        showFragment(0);
                        break;
                    case R.id.item_three:
                        showFragment(0);
                        break;
                }
            }
        });
    }

    private void showFragment(int position) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragments.get(position)).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
