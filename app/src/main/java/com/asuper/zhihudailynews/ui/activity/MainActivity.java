package com.asuper.zhihudailynews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.AbsBaseActivity;
import com.asuper.zhihudailynews.ui.fragment.DailyListFragment;
import com.asuper.zhihudailynews.utils.Log;

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
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

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
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.item_one:
                        showFragment(0);
                        mToolbar.setTitle(R.string.index);
                        break;
                    case R.id.item_two:
                        showFragment(0);
                        mToolbar.setTitle(R.string.news);
                        break;
                    case R.id.item_three:
                        showFragment(0);
                        mToolbar.setTitle(R.string.article);
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
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
