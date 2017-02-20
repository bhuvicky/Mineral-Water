package com.bhuvanesh.mineralwater.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.mineralwater.BaseFragment;
import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.database.MWDBManager;
import com.bhuvanesh.mineralwater.exception.MWException;
import com.bhuvanesh.mineralwater.model.Profile;
import com.bhuvanesh.mineralwater.profile.adapter.CustomerListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class CustomerListFragment extends BaseFragment {

    private CustomerListAdapter mCustomerListAdapter;
    private List<Profile> mProfileList = new ArrayList<>();
    private long mProfileCreatedTime;

    public static CustomerListFragment newInstance() {
        return newInstance(0);
    }

    public static CustomerListFragment newInstance(long profileCreatedTime) {
        CustomerListFragment fragment = new CustomerListFragment();
        fragment.mProfileCreatedTime = profileCreatedTime;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        setTitle(R.string.title_my_customers);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_new_profile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(R.id.rlayout_container, EditProfileFragment.newInstance());
            }
        });

        RecyclerView recyclerViewCustomerList = (RecyclerView) view.findViewById(R.id.recylerview_customer_list);
        recyclerViewCustomerList.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mCustomerListAdapter == null)
            mCustomerListAdapter = new CustomerListAdapter(mProfileCreatedTime);
        recyclerViewCustomerList.setAdapter(mCustomerListAdapter);

        mCustomerListAdapter.setOnCustomerItemClickListener(new CustomerListAdapter.OnCustomerItemClickListener() {
            @Override
            public void onProfileClick(Profile profile) {
                replace(R.id.rlayout_container, ProfileViewFragment.newInstance(profile));
            }

            @Override
            public void onItemClick(Profile profile) {

            }
        });

        MWDBManager manager = new MWDBManager();
        manager.setOnMWDBManagerListener(new MWDBManager.OnMWDBManagerListener<List<Profile>>() {
            @Override
            public void onDBManagerSuccess(List<Profile> obj) {
                mProfileList.clear();
                mProfileList.addAll(obj);
                mCustomerListAdapter.setData(mProfileList);
            }

            @Override
            public void onDBManagerError(MWException exception) {

            }
        });
        manager.getCustomerProfileList(mProfileCreatedTime);

        return view;
    }
}
