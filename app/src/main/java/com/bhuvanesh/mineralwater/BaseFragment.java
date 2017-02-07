package com.bhuvanesh.mineralwater;

import android.support.v4.app.Fragment;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public class BaseFragment extends Fragment {

    protected void replace(int containerId, BaseFragment fragment) {
        ((BaseActivity)getActivity()).replace(containerId, fragment);
    }
}
