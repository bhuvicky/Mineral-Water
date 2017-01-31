package com.bhuvanesh.mineralwater;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected void replace(int containerId, BaseFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, Integer.toString(getSupportFragmentManager().getBackStackEntryCount()));
        ft.addToBackStack(null);
        ft.commit();
    }
}
