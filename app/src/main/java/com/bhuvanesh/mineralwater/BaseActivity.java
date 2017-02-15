package com.bhuvanesh.mineralwater;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bhuvanesh.mineralwater.util.LoggerUtil;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void setActionBar(int resId) {
        mToolbar = (Toolbar) findViewById(resId);
        setSupportActionBar(mToolbar);
    }

    protected void replace(int containerId, BaseFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, Integer.toString(getSupportFragmentManager().getBackStackEntryCount()));
        ft.addToBackStack(null);
        ft.commit();
        LoggerUtil.println("frag tag after replace = " + getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1)
            finish();
        else {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(Integer.toString(count));
            if (fragment != null)
                fragment.pop();
        }
        super.onBackPressed();
    }
}
