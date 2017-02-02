package com.bhuvanesh.mineralwater.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.mineralwater.BaseFragment;
import com.bhuvanesh.mineralwater.R;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class CustomerListFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);

        return view;
    }
}
